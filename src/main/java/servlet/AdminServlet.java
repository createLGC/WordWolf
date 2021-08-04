package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
	/**
	 * 受け取ったJSONを格納するクラス。
	 * @see AdminServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author 6C106
	 */
	private static class AdminJSON {
		/**
		 * お題の種類のリスト
		 */
		private List<List<String>> themeTypeList;
		
		/**
		 * お題のリスト
		 */
		private List<List<String>> themeList;
		
		/**
		 * @return themeTypeList
		 */
		public List<List<String>> getThemeTypeList() {
			return this.themeTypeList;
		}
		
		/**
		 * @return themeList
		 */
		public List<List<String>> getThemeList() {
			return this.themeList;
		}
	}
	
	private static final long serialVersionUID = 1L;

	/**
	 * {@link ThemeTypeDAO#findAll()}でtheme_typeテーブルの一覧を取得。
	 * {@link ThemeDAO#findAll()}で(theme_typeテーブルからお題の種類、themeテーブルからお題)の一覧を取得。
	 * それぞれリクエストスコープに保存。
	 * admin.jspにフォワードし、表示。
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(!request.getParameter("pwd").equals("admin")) {
			response.sendRedirect("/WordWolf/");
			return;
		}
		
		try {
			request.setAttribute("themeTypeList", ThemeTypeDAO.findAll());
			request.setAttribute("themeList", ThemeDAO.findAll());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		request
		.getRequestDispatcher("/WEB-INF/admin.jsp")
		.forward(request, response);
	}

	/**
	 * お題の種類の一覧とお題の一覧のjsonを受け取り、AdminJSONに変換。
	 * theme_typeテーブルとthemeテーブルの中身を受け取ったデータと入れ替える。
	 * 最後に{@link AdminServlet#doGet(HttpServletRequest request, HttpServletResponse response)}
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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
		}catch(SQLException e){
			e.printStackTrace();
	    }
	}

}
