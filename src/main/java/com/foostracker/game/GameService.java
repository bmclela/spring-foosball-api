package com.foostracker.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	
	public List<Game> getAllGames() {
		List<Game> games = new ArrayList<>();
		gameRepository.findAll().forEach(games::add);
		return games;
	}
	
	public Game addGame(Game game) {
		return gameRepository.save(game);
	}

}
