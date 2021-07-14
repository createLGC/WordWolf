package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ThemeDAO;
import dao.ThemeTypeDAO;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		String themeTypeJSON = request.getParameter("theme_type");
		String themeJSON = request.getParameter("theme_type");
		System.out.println(themeTypeJSON);
		System.out.println(themeJSON);
		doGet(request, response);
	}

}
