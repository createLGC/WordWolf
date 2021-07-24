package model;

/**
 * 村人のお題とウルフのお題を保持するクラス。Gameクラスのフィールドになる。
 * @see model.Game
 * @author 6C106
 */
public class Themes {
	/**
	 * 村人のお題。
	 */
	private String personTheme;
	
	/**
	 * ウルフのお題。
	 */
	private String wolfTheme;
	
	/**
	 * 村人用とウルフ用の二つのお題を受け取ってそれぞれフィールドにセット。
	 * @param personTheme
	 * @param wolfTheme
	 */
	public Themes(String personTheme, String wolfTheme) {
		this.personTheme = personTheme;
		this.wolfTheme = wolfTheme;
	}
	
	/**
	 * "person" または "wolf"を受け取ってそれぞれのお題を返す。
	 * roleが"person"でも"wolf"でもなかった場合nullを返す。
	 * @param role
	 * @return personTheme または wolfTheme
	 */
	public String getTheme(String role) {
		if(role.equals("person")) {
			 return personTheme; 
		} else if(role.equals("wolf")){
			 return wolfTheme; 
		}
		
		return null;
	}
}
