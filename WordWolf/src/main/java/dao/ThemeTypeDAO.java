package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * theme_typeテーブルを操作するクラス。
 * {@link dao.parentDAO}を継承。
 * @author 6C106
 *
 */
public class ThemeTypeDAO extends parentDAO {
    /**
     * theme_typeテーブルの一覧を取得。
     * @return themeTypeList
     * @throws SQLException
     */
    public static List<String> findAll() throws SQLException {
        String sql = "SELECT * FROM theme_type;";
        List<String> themeTypeList = new ArrayList<>();
        
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
        	String name = result.getString("name");
        	themeTypeList.add(name);
        }
        
        return themeTypeList;
    }
    
    /**
     * 受け取ったお題の種類のリストでテーブルの中身を入れ替える。
     * @param themeTypeList
     * @throws SQLException
     */
    public static void replaceAll(List<List<String>> themeTypeList) throws SQLException {
    	String sql = "DELETE FROM theme_type";
    	
    	PreparedStatement statement = getConnection().prepareStatement(sql);
    	statement.executeUpdate();
    	for(List<String> row: themeTypeList) {
    		insert(row.get(0));
    	}
    }
    
    /**
     * テーブルに新しいお題の種類を挿入。
     * @param name
     * @throws SQLException
     */
    private static void insert(String name) throws SQLException {
    	String sql = "INSERT INTO theme_type (name) VALUES(?)";
    	
    	PreparedStatement statement = getConnection().prepareStatement(sql);
    	statement.setString(1, name);
    	statement.executeUpdate();
    }
    
    /**
     * お題の種類のidを取得。{@link ThemeDAO#insert(List<String> theme)}で使用。
     * @param name
     * @return id
     * @throws SQLException
     */
    static int getId(String name) throws SQLException { 
    	String sql = "SELECT id from theme_type WHERE name = ?";
    	PreparedStatement statement = getConnection().prepareStatement(sql);
    	statement.setString(1, name);
    	ResultSet rs = statement.executeQuery();
    	rs.next();
    	return rs.getInt(1);
    }
}