package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.ThemeDAO;

public class Game {
	private ArrayList<Player> players;
	private int talkTime;
	private Themes themes;
	
	public Game(){}
	
	public Game(String[] players) {
		System.out.println(players.length);
		this.players = new ArrayList<Player>(players.length);
		for(int i = 0; i < players.length; i++) {
			this.players.add(i, new Player(players[i]));
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
	
	public int getTalkTime() {
		return this.talkTime;
	}
	
	public void setTalkTime(String time) {
		String[] times = time.split(":");
		int minute = Integer.parseInt(times[0]);
		int second = Integer.parseInt(times[1]);
		this.talkTime = minute * 60 + second;
	}
	
	public void setThemes(String themeType) {
		List<String> themes = ThemeDAO.find(themeType);
		this.themes = new Themes(themes.get(0), themes.get(1));
	}
}
