package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * word_wolfスキーマにアクセスする、DAOの抽象クラス。
 * テーブルにアクセスするときはこのクラスを継承。
 * @author 6C106
 */
abstract class parentDAO {
	/**
	 * JDBCドライバのFQDN
	 */
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * ローカルのデータベースのURL
	 */
    private static final String LOCAL_DATABASE_URL = "jdbc:mysql://localhost/word_wolf?user=root&password=root&useSSL=false&allowPublicKeyRetrieval=true";
    
    /**
     * 環境によって変わるデータベースのURLを保存する変数
     */
    private static String DATABASE_URL = null;
    
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
    		connection = DriverManager.getConnection(DATABASE_URL);
    	}
    	return connection;
    }
    
    /**
     * JDBCドライバをロード。データベースのURLを決定。
     */
    static {
    	try{
    		Class.forName(DRIVER_PATH);
    		final String jawsdb = System.getenv("JAWSDB_URL");
    		DATABASE_URL = jawsdb != null ? jawsdb : LOCAL_DATABASE_URL;
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
}
