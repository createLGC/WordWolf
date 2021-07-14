package dao;

/**
 * word_wolfスキーマにアクセスする、DAOの抽象クラス。
 * テーブルにアクセスするときはこのクラスを継承。
 * @author 6C106
 *
 */
abstract class parentDAO {
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    protected static final String URL = "jdbc:mysql://localhost/word_wolf?useSSL=false&allowPublicKeyRetrieval=true";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "root";
    
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
