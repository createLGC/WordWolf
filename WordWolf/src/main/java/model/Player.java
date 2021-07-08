package model;

// プレーヤーの情報を保持

public class Player {
	private String name; // プレイヤー名
	private String role; // "person" または "wolf"
	private String theme; // お題
	
	public Player(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

}
