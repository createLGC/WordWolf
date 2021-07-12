package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import dao.ThemeDAO;

public class Game {
	private List<Player> players;
	private int talkTime;
	private Themes themes;
	
	public Game(){}
	
	public Game(String[] players) {
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
		for(Player player: this.players) {
			player.setTheme(this.themes.getTheme(player.getRole()));
		}
	}
	
	public String decideWinner(List<String> wolfNames) {
		List<String> playerNames = new ArrayList<>(new HashSet<>(wolfNames));
		List<Player> players = new ArrayList<>();
		for(String playerName: playerNames) {
			inner: for(Player player: this.players) {
				if(player.getName().equals(playerName)) {
					players.add(player);
					continue inner;
				}
			}
		}
		
		List<String> roles = new ArrayList<>();
		for(Player player: players) {
			roles.add(player.getRole());
		}
		HashMap<String, Integer> roleMap = new HashMap<>();
		roleMap.put("person", 0);
		roleMap.put("wolf", 0);
		for(String role: roles) {
			roleMap.put(role, roleMap.get(role) + 1);
		}
		if(roleMap.get("person") > roleMap.get("wolf"))
			return "person";
		else if(roleMap.get("person") < roleMap.get("wolf"))
			return "wolf";
		else
			return null;

	}
}

