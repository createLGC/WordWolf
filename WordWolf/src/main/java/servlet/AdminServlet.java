package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ThemeDAO;
import dao.ThemeTypeDAO;

/**
 * admin.jspにデータベースのデータを送ったり、
 * データを受け取ってデータベースのデータを入れ替えたりする。
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static class AdminJSON {
		private List<List<String>> themeTypeList;
		private List<List<String>> themeList;
		
		public List<List<String>> getThemeTypeList() {
			return this.themeTypeList;
		}
		
		public List<List<String>> getThemeList() {
			return this.themeList;
		}
	}
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * {@link ThemeTypeDAO#findAll()}でtheme_typeテーブルの一覧を取得。
	 * {@link ThemeDAO#findAll()}で(theme_typeテーブルからお題の種類、themeテーブルからお題)の一覧を取得、
	 * それぞれリクエストスコープに保存
	 * admin.jspにフォワードし、表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> themeTypeList = ThemeTypeDAO.findAll();
		List<Map<String, String>> themeList = ThemeDAO.findAll();
		request.setAttribute("themeTypeList", themeTypeList);
		request.setAttribute("themeList", themeList);
		
		request
			.getRequestDispatcher("/WEB-INF/admin.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * お題の種類の一覧とお題の一覧のjsonを受け取り、AdminJSONに変換。
	 * theme_typeテーブルとthemeテーブルの中身を受け取ったデータと入れ替える。
	 * 最後に{@link AdminServlet#doGet(HttpServletRequest, HttpServletResponse)}
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try(BufferedReader reader = request.getReader();){
			while ((line = reader.readLine()) != null)
				jb.append(line);
			
			ObjectMapper mapper = new ObjectMapper();
			
			AdminJSON json = mapper.readValue(jb.toString(), AdminJSON.class);
			ThemeTypeDAO.replaceAll(json.getThemeTypeList());
			ThemeDAO.replaceAll(json.getThemeList());
			System.out.println("tables replaced");
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
	    }
	}

}
