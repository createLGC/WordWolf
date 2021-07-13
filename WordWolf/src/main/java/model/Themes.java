package model;

/**
 * @see model.Game
 * 平民のお題とウルフのお題をは維持するクラス。Gameクラスのフィールドになる。
 * @author 6C106
 *
 */
public class Themes {
	private String personTheme;
	private String wolfTheme;
	
	/**
	 * 平民用とウルフ用の二つのお題を受け取ってそれぞれフィールドにセット
	 * @param personTheme
	 * @param wolfTheme
	 */
	public Themes(String personTheme, String wolfTheme) {
		this.personTheme = personTheme;
		this.wolfTheme = wolfTheme;
	}
	
	/**
	 * "person" または "wolf"を受け取ってそれぞれのお題を返す。
	 * roleが"person"でも"wolfでもなかった場合nullを返す。
	 * @param role
	 * @return
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
