package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * themeテーブルにアクセスするクラス
 * {@link dao.parentDAO}を継承
 * @author 6C106
 *
 */
public class ThemeDAO extends parentDAO {
    /**
     * theme_typeテーブル、themeテーブルにアクセスし、
     * お題の種類ごとにグループ化された(お題の種類、お題)の一覧を取得
     * @return
     */
	public static List<Map<String, String>> findAll() {
		String sql = "SELECT theme_type.name, theme.theme FROM theme JOIN theme_type ON theme.theme_type_id = theme_type.id ORDER BY theme_type.name";
        
		List<Map<String, String>> themeList = new ArrayList<>();
		
		try (PreparedStatement statement = getConnection().prepareStatement(sql);) {
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				String name = result.getString("theme_type.name");
				String theme = result.getString("theme.theme");
				themeList.add(new HashMap<String, String>(){
					{
						put("type", name);
						put("theme", theme);
					}
				});
			}
			return themeList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * themeテーブルにアクセスし、
     * お題の種類がthemeTypeのものをランダムに二つとってくる。
     * @param themeType
     * @return
     */
    public static List<String> find(String themeType) {
        String sql = "SELECT theme.theme FROM theme JOIN theme_type ON theme.theme_type_id = theme_type.id WHERE theme_type.name = ? ORDER BY RAND() LIMIT 2";
        
        List<String> themeList = new ArrayList<>(2);
        
        try (PreparedStatement statement = getConnection().prepareStatement(sql);) {
            
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
    
    /**
     * themeテーブルの中身を受け取ったお題のリストで入れ替える。
     * {@link ThemeTypeDAO#replaceAll()}をするときはその後に使用。
     * @param themeList
     */
    public static void replaceAll(List<List<String>> themeList) {
    	String sql = "DELETE FROM theme";
    	
    	try(PreparedStatement statement = getConnection().prepareStatement(sql);) {
    		statement.executeUpdate();
    		for(List<String> row: themeList) {
    			insert(row);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * themeテーブルに一行挿入。{@link ThemeTypeDAO.getId()}でtheme_type.idを取得。
     * @param theme
     */
    private static void insert(List<String> theme) {
    	int id = ThemeTypeDAO.getId(theme.get(0));
    	if(id == 0) {return;}
    	String sql = "INSERT INTO theme (theme_type_id, theme) VALUES(?, ?)";
    	try(PreparedStatement statement = getConnection().prepareStatement(sql);){
    		statement.setInt(1, id);
    		statement.setString(2, theme.get(1));
    		statement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
}


