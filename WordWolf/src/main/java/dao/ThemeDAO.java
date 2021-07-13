package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * themeテーブルにアクセスするクラス
 * @author 6C106
 *
 */
public class ThemeDAO {
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/word_wolf?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    static {
    	try {
    		Class.forName(DRIVER_PATH);
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * themeテーブルにアクセスし、
     * お題の種類がthemeTypeのものをランダムに二つとってくる。
     * @param themeType
     * @return
     */
    public static List<String> find(String themeType) {
        String sql = "SELECT theme.theme FROM theme JOIN theme_type ON theme.theme_type_id = theme_type.id WHERE theme_type.name = ? ORDER BY RAND() LIMIT 2";
        
        List<String> themeList = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);) {
            
        	statement.setString(1, themeType);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
            	String theme = result.getString("theme.theme");
            	themeList.add(theme);
            }
            
            return themeList;
            
        } catch (SQLException e) {
        
            e.printStackTrace();
            
        }
        return null;
    }
    
}
