package dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
        
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);) {
                        
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
}