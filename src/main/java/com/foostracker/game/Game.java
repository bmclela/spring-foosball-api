package com.foostracker.game;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document	
public class Game {

	@Id
	private String id;
	private String winner1;
	private String winner2;
	private String loser1;
	private String loser2;
	private LocalDateTime date;
	
	public Game(String winner1, String winner2, String loser1, String loser2) {
		super();
		this.winner1 = winner1;
		this.winner2 = winner2;
		this.loser1 = loser1;
		this.loser2 = loser2;
		this.date = LocalDateTime.now();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWinner1() {
		return winner1;
	}
	public void setWinner1(String winner1) {
		this.winner1 = winner1;
	}
	public String getWinner2() {
		return winner2;
	}
	public void setWinner2(String winner2) {
		this.winner2 = winner2;
	}
	public String getLoser1() {
		return loser1;
	}
	public void setLoser1(String loser1) {
		this.loser1 = loser1;
	}
	public String getLoser2() {
		return loser2;
	}
	public void setLoser2(String loser2) {
		this.loser2 = loser2;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
