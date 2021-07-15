package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * word_wolfスキーマにアクセスする、DAOの抽象クラス。
 * テーブルにアクセスするときはこのクラスを継承。
 * @author 6C106
 *
 */
abstract class parentDAO {
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/word_wolf?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    private static Connection conn = null;
    
    protected static Connection getConnection() throws SQLException{
    	if(conn == null) {
    		throw new SQLException("connection has disconnected");
    	}
    	return conn;
    }
    
    /**
     * JDBCドライバをロード。
     */
    static {
    	try{
    		Class.forName(DRIVER_PATH);
    		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
}
