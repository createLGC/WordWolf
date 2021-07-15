package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * theme_typeテーブルを操作するクラス
 * {@link dao.parentDAO}を継承
 * @author 6C106
 *
 */
public class ThemeTypeDAO extends parentDAO {
    /**
     * theme_typeテーブルの一覧を取得
     * @return
     */
    public static List<String> findAll() {
        
        String sql = "SELECT * FROM theme_type;";
        
        List<String> themeTypeList = new ArrayList<>();
        
        try (PreparedStatement statement = getConnection().prepareStatement(sql);) {
                        
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
            	String name = result.getString("name");
            	themeTypeList.add(name);
            }
            
            return themeTypeList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 受け取ったお題の種類のリストでテーブルの中身を入れ替える。
     * @param themeTypeList
     */
    public static void replaceAll(List<List<String>> themeTypeList) {
    	String sql = "DELETE FROM theme_type";
    	
    	try(PreparedStatement statement = getConnection().prepareStatement(sql);) {
    		statement.executeUpdate();
    		for(List<String> row: themeTypeList) {
    			insert(row.get(0));
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * テーブルに新しいお題の種類を挿入。
     * @param value
     */
    private static void insert(String name){
    	String sql = "INSERT INTO theme_type (name) VALUES(?)";
    	try(PreparedStatement statement = getConnection().prepareStatement(sql);){
    		statement.setString(1, name);
    		statement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * お題の種類のidを取得。{@link ThemeDAO#insert}で使用。
     * エラーの時0を返す。
     * @param value
     * @return
     */
    static int getId(String name) { 
    	String sql = "SELECT id from theme_type WHERE name = ?";
    	try(PreparedStatement statement = getConnection().prepareStatement(sql);){
    		statement.setString(1, name);
    		ResultSet rs = statement.executeQuery();
    		rs.next();
    		return rs.getInt(1);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return 0;
    }
}