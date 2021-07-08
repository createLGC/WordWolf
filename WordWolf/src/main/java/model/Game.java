package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
	private List<Player> players;
	private int talkTime;
	private Themes themes;
	
	public Game(String[] players) {
		Arras.asList(players).stream().map(player->new Player(player))
		this.players = Arrays.asList(players);
	}
	
	/**
	 * 
	 * @param numOfWolves
	 */
	public setWolves(int numOfWolves) {
		List<Player> clonePlayers = new List<>(this.players);
		Collections.shuffle(clonePlayers);
		List<Player> wolves = clonePlayers.subList(0, numOfWolves);
	}
}
