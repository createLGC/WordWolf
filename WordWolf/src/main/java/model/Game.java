package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.ThemeDAO;

public class Game {
	private ArrayList<Player> players;
	private int talkTime;
	private Themes themes;
	
	Game(){}
	
	public Game(String[] players) {
		this.players = new ArrayList<Player>(players.length);
		for(int i = 0; i < players.length; i++) {
			this.players.set(i, new Player(players[i]));
		}
	}
	
	public List<Player> getPlayers(){
		return this.players;
	}
	/**
	 * 
	 * @param numOfWolves
	 */
	public void setWolves(int numOfWolves) {
		List<Player> clonePlayers = new ArrayList<Player>(this.players);
		Collections.shuffle(clonePlayers);
		List<Player> wolves = clonePlayers.subList(0, numOfWolves);
		for(Player wolf: wolves) {
			wolf.setRole("wolf");
		}
	}
	
	public void setTalkTime(String time) {
		
	}
	
	public void setThemes(String themeType) {
		List<String> themes = ThemeDAO.find(themeType);
		this.themes = new Themes(themes.get(0), themes.get(1));
	}
}
