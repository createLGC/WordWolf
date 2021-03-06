package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * themeテーブルにアクセスするクラス。
 * {@link dao.parentDAO}を継承。
 * @author 6C106
 *
 */
public class ThemeDAO extends parentDAO {
    /**
     * theme_typeテーブル、themeテーブルにアクセスし、
     * お題の種類ごとにグループ化された(お題の種類、お題)の一覧を取得
     * @return themeList
     * @throws SQLException
     */
	public static List<Map<String, String>> findAll() throws SQLException {
		String sql = "SELECT theme_type.name, theme.theme FROM theme JOIN theme_type ON theme.theme_type_id = theme_type.id ORDER BY theme_type.name";
		
		List<Map<String, String>> themeList = new ArrayList<>();
		
		PreparedStatement statement = getConnection().prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			themeList.add(new HashMap<String, String>(){
				{
					put("type", result.getString("theme_type.name"));
					put("theme", result.getString("theme.theme"));
				}
			});
		}
			
		return themeList;
	}
	
	/**
     * themeテーブルにアクセスし、
     * お題の種類がthemeTypeのものをランダムに二つとってくる。
     * @param themeType
     * @return themeList
     * @throws SQLException
     */
    public static List<String> find(String themeType) throws SQLException {
        String sql = "SELECT theme.theme FROM theme JOIN theme_type ON theme.theme_type_id = theme_type.id WHERE theme_type.name = ? ORDER BY RAND() LIMIT 2";
        List<String> themeList = new ArrayList<>(2);
        
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, themeType);
        ResultSet result = statement.executeQuery();
        
        while(result.next()) {
        	themeList.add(result.getString("theme.theme"));
        }
        
        return themeList;
    }
    
    /**
     * themeテーブルの中身を受け取ったお題のリストで入れ替える。
     * {@link ThemeTypeDAO#replaceAll(List<List<String>> themeTypeList)}を使うときはその後に使用。
     * @param themeList
     * @throws SQLException
     */
    public static void replaceAll(List<List<String>> themeList) throws SQLException {
    	String sql = "DELETE FROM theme";
    	
    	PreparedStatement statement = getConnection().prepareStatement(sql);
    	statement.executeUpdate();
    	for(List<String> row: themeList) {
    		insert(row);
    	}
    }
    
    /**
     * themeテーブルに一行挿入。{@link ThemeTypeDAO#getId(String name)}でtheme_type.idを取得。
     * @param theme
     * @throws SQLException
     */
    private static void insert(List<String> theme) throws SQLException {
    	String sql = "INSERT INTO theme (theme_type_id, theme) VALUES(?, ?)";
    	
    	PreparedStatement statement = getConnection().prepareStatement(sql);
    	statement.setInt(1, ThemeTypeDAO.getId(theme.get(0)));
    	statement.setString(2, theme.get(1));
    	statement.executeUpdate();
    }
}