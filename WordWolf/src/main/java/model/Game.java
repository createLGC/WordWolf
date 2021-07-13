package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ゲームで使用する情報を保持するクラス。
 * １ゲームに一つだけセッションスコープに保存される。
 * @author 6C106
 *
 */
public class Game {
	private List<Player> players;
	private int talkTime;
	private Themes themes;
	
	/**
	 * @see model.GameTest
	 * テスト用コンストラクタ
	 */
	Game(){}
	
	/**
	 * @see servlet.DecidePlayerServlet#doPost
	 * コンストラクタ
	 * this#playersをplayerNamesで初期化
	 * @param playerNames
	 */
	public Game(String[] playerNames) {
		this.players = new ArrayList<Player>(playerNames.length);
		for(int i = 0; i < playerNames.length; i++) {
			this.players.add(i, new Player(playerNames[i]));
		}
	}
	
	/**
	 * @see model.Player
	 * ゲームに参加しているプレイヤーのリストを取得
	 * @return
	 */
	public List<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * @see servlet.Decide3PropsServlet#doPost
	 * numOfWolvesの人数分this.playersをwolfにする
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
	 * @return
	 */
	public int getTalkTime() {
		return this.talkTime;
	}
	
	/**
	 * @see servlet.Decide3PropsServlet#doPost()
	 * 引数timeは"分:秒"の形になっているので、
	 * それを秒に変換し、this.talkTimeに代入
	 * @param time
	 */
	public void setTalkTime(String time) {
		String[] times = time.split(":");
		int minute = Integer.parseInt(times[0]);
		int second = Integer.parseInt(times[1]);
		this.talkTime = minute * 60 + second;
	}
	
	/**
	 * @see servlet.Decide3PropsServlet#doPost
	 * プレイヤーにそれぞれのroleごとにお題を設定。
	 * 引数themesは{@link dao.ThemeDAO#find()}から受け取る。
	 * @param themes
	 */
	public void setThemes(List<String> themes) {
		this.themes = new Themes(themes.get(0), themes.get(1));
		for(Player player: this.players) {
			player.setTheme(this.themes.getTheme(player.getRole()));
		}
	}
	
	/**
	 * @see servlet.VoteServlet#doPost
	 * vote.jspから受け取ったプレーヤー名のリストのうち、
	 * "person"を持つプレイヤーの名前と"wolf"を持つプレイヤーの名前のどちらが多いかを調べ、
	 * "person"のほうが多いとき勝者グループである"wolf"を返し、
	 * "wolf"のほうが多いとき"person"を返す。
	 * どちらでもないときはnullを返す。
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
