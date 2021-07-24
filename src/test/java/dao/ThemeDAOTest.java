package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class ThemeDAOTest {

	@Test
	void testFind() throws SQLException {
		MatcherAssert.assertThat(this.findAllFromCSV(), CoreMatchers.hasItems(ThemeDAO.find("動物").toArray(new String[0])));
	}

	private List<String> findAllFromCSV() {
		List<String> themeList = new ArrayList<>();
		
		try (
			FileInputStream fi = new FileInputStream("src/main/sql/theme.csv");
			InputStreamReader is = new InputStreamReader(fi);
			BufferedReader br = new BufferedReader(is);
		){
			//読み込み行
			String line;

			//1行ずつ読み込みを行う
			for (int i = 0; (line = br.readLine()) != null; i++) {
				//先頭行は列名
				if (i != 0) {
					String[] data = line.split(",");
					themeList.add(data[2]);
				}
			}
			
			return themeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
