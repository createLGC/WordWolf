package model;

/**
 * @see model.Game
 * プレーヤーの情報を保持。Game#playersになる。
 * @author 6C106
 */
public class Player {
	private String name; // プレイヤー名
	private String role = "person"; // "person" または "wolf"
	private String theme; // お題
	
	/**
	 * プレイヤー名を受け取って初期化
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * プレイヤー名を返す関数
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * "person" または "wolf"を返す
	 * @return
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * "person" または "wolf"をroleにセット。
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * お題を返す関数
	 * @return
	 */
	public String getTheme() {
		return theme;
	}
	
	/**
	 * お題を受け取ってセット
	 * @param theme
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
}
