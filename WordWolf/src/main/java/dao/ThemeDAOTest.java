package dao;

import static org.hamcrest.CoreMatchers.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class ThemeDAOTest {

	@Test
	void testFind() {
		List<String> list = ThemeDAO.find("ブランド");
		String[] array = list.toArray(new String[list.size()]);
		MatcherAssert.assertThat(this.findAllFromCSV(), hasItems(array));
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
			
			//読み込み行数の管理
			int i = 0;
			
			//列名を管理する為の配列
			String[] arr = null;

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				//先頭行は列名
				if (i == 0) {
					arr = line.split(",");
				} else {
					String[] data = line.split(",");
					themeList.add(data[2]);
				}
				//行数のインクリメント
				i++;	
			}
			
			return themeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
