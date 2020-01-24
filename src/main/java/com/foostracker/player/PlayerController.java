package com.foostracker.player;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("/api/players")
	public List<Player> getAllPlayers() {
		return playerService.getAllPlayers();
	}
	
	@RequestMapping("/api/players/{id}")
	public Player getPlayer(@PathVariable String id) {
		return playerService.getPlayer(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/players")
	public Player addPlayer(@RequestBody JSONObject json) {
		String name = (String) json.get("name");
		Player player = new Player(name);
		return playerService.addPlayer(player);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/api/players/{id}")
	public void updatePlayer(@PathVariable String id, @RequestBody Player player) {
		playerService.updatePlayer(id, player);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/players/{id}")
	public void deletePlayer(@PathVariable String id) {
		playerService.deletePlayer(id);
	}
}
