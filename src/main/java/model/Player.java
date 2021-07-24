package model;

/**
 * プレーヤーの情報を保持。Game#playersになる。
 * @see model.Game
 * @author 6C106
 */
public class Player {
	/**
	 * プレイヤー名
	 */
	private String name;
	
	/**
	 * プレイヤーの役割。"person"または"wolf"。初期値は"person"
	 */
	private String role = "person";
	
	/**
	 * プレイヤーの持つお題。
	 */
	private String theme;
	
	/**
	 * プレイヤー名を受け取って初期化
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * プレイヤー名を返す関数
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * "person" または "wolf"を返す
	 * @return "person" または "wolf"
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
	 * @return theme
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
