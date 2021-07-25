package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ゲームで使用する情報を保持するクラス。
 * １ゲームに一つだけセッションスコープに保存される。
 * @author 6C106
 *
 */
public class Game {
	/**
	 * プレイヤーのリスト
	 */
	private List<Player> players;
	
	/**
	 * トークタイム(秒)
	 */
	private int talkTime;
	
	/**
	 * 二つのお題
	 */
	private Themes themes;
	
	/**
	 * テスト用コンストラクタ。
	 */
	Game(){}
	
	/**
	 * コンストラクタ。this.playersをplayerNamesで初期化。
	 * @see servlet.DecidePlayerServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param playerNames
	 */
	public Game(String[] playerNames) {
		this.players = new ArrayList<Player>(playerNames.length);
		for(String playerName: playerNames) {
			this.players.add(new Player(playerName));
		}
	}
	
	/**
	 * ゲームに参加しているプレイヤーのリストを取得。
	 * @see model.Player
	 * @return players
	 */
	public List<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * numOfWolvesの人数分this.playersをwolfにする
	 * @see servlet.Decide3PropsServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param numOfWolves
	 */
	public void setWolves(int numOfWolves) {
		//this.playersを複製
		List<Player> clonePlayers = new ArrayList<Player>(this.players);
		//複製したリストをシャッフル
		Collections.shuffle(clonePlayers);
		//シャッフルしたリストの前からnumOfWolves分を切り出す。
		List<Player> players = clonePlayers.subList(0, numOfWolves);
		//切り出したリストの要素playerに"wolf"をセット
		for(Player player: players) {
			player.setRole("wolf");
		}
	}
	
	/**
	 * トーク時間(秒)を取得。
	 * @return talkTime
	 */
	public int getTalkTime() {
		return this.talkTime;
	}
	
	/**
	 * 引数timeは"分:秒"の形になっているので、それを秒に変換し、this.talkTimeに代入。
	 * @see servlet.Decide3PropsServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param time
	 */
	public void setTalkTime(String time) {
		String[] times = time.split(":");
		int minute = Integer.parseInt(times[0]);
		int second = Integer.parseInt(times[1]);
		this.talkTime = minute * 60 + second;
	}
	
	/**
	 * プレイヤーにそれぞれのroleごとにお題を設定。
	 * 引数themesは{@link dao.ThemeDAO#find(String name)}から受け取る。
	 * @see servlet.Decide3PropsServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param themes
	 */
	public void setThemes(List<String> themes) {
		this.themes = new Themes(themes.get(0), themes.get(1));
		for(Player player: this.players) {
			player.setTheme(this.themes.getTheme(player.getRole()));
		}
	}
	
	/**
	 * vote.jspから受け取ったプレーヤー名のリストのうち、
	 * "person"を持つプレイヤーの名前と"wolf"を持つプレイヤーの名前のどちらが多いかを調べ、
	 * "person"のほうが多いとき勝者グループである"wolf"を返し、
	 * "wolf"のほうが多いとき"person"を返す。
	 * どちらでもないときはnullを返す。
	 * @see servlet.VoteServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param wolfNames
	 * @return "person" または "wolf"
	 */
	public String decideWinner(List<String> wolfNames) {
		HashSet<String> playerNamesSet = new HashSet<>(wolfNames);
		System.out.println(playerNamesSet);
		
		HashMap<String, Integer> playerNamesMap = new HashMap<>();
		for(String playerName: playerNamesSet) {
			playerNamesMap.put(playerName, 0);
		}
		System.out.println(playerNamesMap);
		
		for(String wolfName: wolfNames) {
			playerNamesMap.put(wolfName, playerNamesMap.get(wolfName) + 1);
		}
		System.out.println(playerNamesMap);
		
		HashMap<Player, Integer> playersMap = new HashMap<>();
		Iterator<Entry<String, Integer>> it = playerNamesMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)it.next();
	        for(Player player: this.players) {
	        	if(player.getName().equals(entry.getKey())) {
	        		playersMap.put(player, entry.getValue());
	        	}
	        }
	    }
	    System.out.println(playersMap);
		
		HashMap<String, Integer> roleMap = new HashMap<>();
		roleMap.put("person", 0);
		roleMap.put("wolf", 0);
		Iterator<Entry<Player, Integer>> it2 = playersMap.entrySet().iterator();
	    while (it2.hasNext()) {
	        Map.Entry<Player, Integer> entry = (Map.Entry<Player, Integer>)it2.next();
	        String role = entry.getKey().getRole();
	        roleMap.put(role, roleMap.get(role) + entry.getValue());
	    }
	    System.out.println(roleMap);
	    
		if(roleMap.get("person") > roleMap.get("wolf"))
			return "person";
		else if(roleMap.get("person") < roleMap.get("wolf"))
			return "wolf";
		else
			return null;
	}
}
