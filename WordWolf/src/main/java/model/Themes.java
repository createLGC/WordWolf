package model;

public class Themes {
	private String personTheme;
	private String wolfTheme;
	
	public Themes(String personTheme, String wolfTheme) {
		this.personTheme = personTheme;
		this.wolfTheme = wolfTheme;
	}
	
	public String getTheme(String role) {
		if(role.equals("person")) {
			 return personTheme; 
		} else if(role.equals("wolf")){
			 return wolfTheme; 
		}
		
		return null;
	}
	
	public String getRole(String theme) {
		if(theme.equals(this.personTheme))
			return "person";
		if(theme.equals(this.wolfTheme))
			return "wolf";
		return null;
	}
}
