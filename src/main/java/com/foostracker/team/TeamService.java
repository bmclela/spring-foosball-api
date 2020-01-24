package com.foostracker.team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
	
	final int K = 100;

	@Autowired
	private TeamRepository teamRepository;
	
	public List<Team> getAllTeams() {
		List<Team> teams = new ArrayList<>();
		teamRepository.findAll().forEach(teams::add);
		return teams;
	}

	public void addTeam(Team team) {
		teamRepository.save(team);
	}
	
	public void calculateTeamElo(String winner1, String winner2, String loser1, String loser2) {
		
		// Get team name
		String teamName1 = winner1.compareTo(winner2) == 1 ? winner2.concat(" and " + winner1) : winner1.concat(" and " + winner2);
		String teamName2 = loser1.compareTo(loser2) == 1 ? loser2.concat(" and " + loser1) : loser1.concat(" and " + loser2);
		
		// Get team if they exist and add them if not
		List<Team> teams = getAllTeams();
		
		Optional<Team> team1check = teams.stream().filter(team -> team.getName().equals(teamName1)).findFirst();
		Team team1 = team1check.isPresent() ? team1check.get() : new Team(teamName1);
		
		Optional<Team> team2check = teams.stream().filter(team -> team.getName().equals(teamName2)).findFirst();
		Team team2 = team2check.isPresent() ? team2check.get() : new Team(teamName2);
		
		// Calculate new elos
		double probability1 = 1.0 / (1.0 + Math.pow(10, (team2.getElo() - team1.getElo()) / 400));
		double probability2 = 1.0 / (1.0 + Math.pow(10, (team1.getElo() - team2.getElo()) / 400));
		team1.setElo(team1.getElo() + K * (1 - probability1));
		team2.setElo(team2.getElo() + K * (0 - probability2));
		team1.setWins(team1.getWins() + 1);
		team2.setLosses(team2.getLosses() + 1);
		
		// Update teams in database with new elos
		addTeam(team1);
		addTeam(team2);
	}
}
