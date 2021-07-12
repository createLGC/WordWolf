package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ThemeTypeDAOTest {

	@Test
	void testFindAll() {
		assertEquals(ThemeTypeDAO.findAll(), this.findAllFromCSV());
	}
	
	private List<String> findAllFromCSV() {
		List<String> themeTypeList = new ArrayList<>();
		
		try (
			FileInputStream fi = new FileInputStream("src/main/sql/theme_type.csv");
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
					themeTypeList.add(data[1]);
				}
				//行数のインクリメント
				i++;	
			}
			
			return themeTypeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
