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
	
	public String decideWinner(String[] wolfNames) {
		//まず、Stringのリストを作成
		List<String> list1= new ArrayList<>();
		//wolfNamesをループして要素が上のリストになければ上のリストに入れ、あれば何もしない
		for(String wolfName: wolfNames) {
			if(list1.contains(wolfName)) {
				continue;
			}else {
				list1.add(wolfName);
		}
		//Stringのリストと同じ長さのIntegerのリストを作成し、要素を0で初期化
		List<Integer> list2  = list.size(list1);
		
		//wolfNamesをループし、その中でStringのリストをループし、それぞれの要素を比較して、一致するときにStringのリストの要素の番号のIntegerのリストの要素の値を1増やす。
		for(String wolfName: wolfNames) {
			for(String list1) {
			if(list1.contains(wolfName)) {
		//Integerのリストの要素の中で一番大きいものの番号のStringのリストの要素をreturn
				return 
	}
}
}
	}
}
}

