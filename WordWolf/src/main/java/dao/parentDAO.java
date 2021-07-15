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
    
    private static Connection connection = null;
    
    /**
     * Connectionを取得する関数。子クラスはこれでConectionを使う。
     * connectionがnullの時connectionを初期化。
     * @return
     * @throws SQLException
     */
    protected static Connection getConnection() throws SQLException{
    	if(connection == null) {
    		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	}
    	return connection;
    }
    
    /**
     * JDBCドライバをロード。
     */
    static {
    	try{
    		Class.forName(DRIVER_PATH);
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
}
