package com.foostracker.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foostracker.team.Team;

@Service
public class PlayerService {
	
	final int K = 100;
	
	@Autowired
	private PlayerRepository playerRepository;

	public List<Player> getAllPlayers() {
		List<Player> players = new ArrayList<>();
		playerRepository.findAll().forEach(players::add);
		return players;
	}

	public Player getPlayer(String id) {
		return playerRepository.findById(id).orElse(null);
	}

	public Player addPlayer(Player player) {
		return playerRepository.save(player);
	}

	public void updatePlayer(String id, Player player) {
		playerRepository.save(player);
		
	}

	public void deletePlayer(String id) {
		playerRepository.deleteById(id);
	}
	
	public void calculatePlayerElo(String winner1, String winner2, String loser1, String loser2) {
		
		// Find players in database
		List<Player> players = getAllPlayers();
		
		Optional<Player> player1check = players.stream().filter(player -> player.getName().equals(winner1)).findFirst();
		Player player1 = player1check.isPresent() ? player1check.get() : null;
		
		Optional<Player> player2check = players.stream().filter(player -> player.getName().equals(winner2)).findFirst();
		Player player2 = player2check.isPresent() ? player2check.get() : null;
		
		Optional<Player> player3check = players.stream().filter(player -> player.getName().equals(loser1)).findFirst();
		Player player3 = player3check.isPresent() ? player3check.get() : null;
		
		Optional<Player> player4check = players.stream().filter(player -> player.getName().equals(loser2)).findFirst();
		Player player4 = player4check.isPresent() ? player4check.get() : null;
		
		// Calculate new elos
		double team1Elo = (player1.getElo() + player2.getElo()) /2;
		double team2Elo = (player3.getElo() + player4.getElo()) /2;
		double probability1 = 1.0 / (1.0 + Math.pow(10, (team2Elo - team1Elo) / 400));
		double probability2 = 1.0 / (1.0 + Math.pow(10, (team1Elo - team2Elo) / 400));
		
		player1.setElo(player1.getElo() + K * (1 - probability1));
		player2.setElo(player2.getElo() + K * (1 - probability1));
		player3.setElo(player3.getElo() + K * (0 - probability2));
		player4.setElo(player4.getElo() + K * (0 - probability2));
		
		player1.setWins(player1.getWins() + 1);
		player2.setWins(player2.getWins() + 1);
		player3.setLosses(player3.getLosses() + 1);
		player4.setLosses(player4.getLosses() + 1);
		
		// Update players in database
		addPlayer(player1);
		addPlayer(player2);
		addPlayer(player3);
		addPlayer(player4);
	}

}
