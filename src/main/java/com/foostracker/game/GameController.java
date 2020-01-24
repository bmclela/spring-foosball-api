package com.foostracker.game;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foostracker.player.Player;
import com.foostracker.player.PlayerService;
import com.foostracker.team.Team;
import com.foostracker.team.TeamService;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping("/api/games")
	public List<Game> getAllGames() {
		return gameService.getAllGames();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/games")
	public JSONObject addGame(@RequestBody JSONObject json) {
		
		// Create and save new game
		String winner1 = (String) json.get("winner1");
		String winner2 = (String) json.get("winner2");
		String loser1 = (String) json.get("loser1");
		String loser2 = (String) json.get("loser2");
		Game game = new Game(winner1, winner2, loser1, loser2);
		Game newGame = gameService.addGame(game);
		
		// Calculate new teams and players elos
		teamService.calculateTeamElo(winner1, winner2, loser1, loser2);
		playerService.calculatePlayerElo(winner1, winner2, loser1, loser2);
		
		// Get new list of teams and players
		List<Player> newPlayers = playerService.getAllPlayers();
		List<Team> newTeams = teamService.getAllTeams();
		
		// Create return value
		JSONObject returnValue = new JSONObject();
		returnValue.put("newGame", newGame);
		returnValue.put("newTeams", newTeams);
		returnValue.put("newPlayers", newPlayers);
		return returnValue;
	}
}
