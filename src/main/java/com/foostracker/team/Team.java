package com.foostracker.team;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Team {
	
	@Id
	private String id;
	private String name;
	private double elo;
	private int wins;
	private int losses;
	
	public Team(String name) {
		super();
		this.name = name;
		this.elo = 1000;
		this.wins = 0;
		this.losses = 0;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getElo() {
		return elo;
	}
	public void setElo(double elo) {
		this.elo = elo;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}

}
