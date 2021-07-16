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

			//1行ずつ読み込みを行う
			for (int i = 0; (line = br.readLine()) != null; i++) {
				//先頭行は列名
				if (i != 0) {
					String[] data = line.split(",");
					themeTypeList.add(data[1]);
				}
			}
			
			return themeTypeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
