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
	/**
	 * JDBCドライバのFQDN
	 */
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * データベースのURL
	 */
    private static final String URL = "jdbc:mysql://localhost/word_wolf?useSSL=false&allowPublicKeyRetrieval=true";
    
    /**
     * データベースのユーザーネーム
     */
    private static final String USERNAME = "root";
    
    /**
     * データベースのパスワード
     */
    private static final String PASSWORD = "root";
    
    /**
     * データべースとのコネクション
     */
    private static Connection connection = null;
    
    /**
     * Connectionを取得する関数。子クラスはこれでConnectionを使う。
     * connectionがnullの時connectionを初期化。
     * @return connection
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
