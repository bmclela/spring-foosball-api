package com.foostracker.team;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping("/api/teams")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/teams")
	public void addTeam(@RequestBody JSONObject json) {
		String name = (String) json.get("name");
		Team team = new Team(name);
		teamService.addTeam(team);
	}
}
