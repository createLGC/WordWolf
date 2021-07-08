package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ThemeTypeDAO;
import model.Game;

/**
 * Servlet implementation class DecidePlayerServlet
 */
@WebServlet("/DecidePlayerServlet")
public class DecidePlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] playerNames = request.getParameterValues("playerName");
		
		Game game = new Game(playerNames);
		
		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("game",game);
		
		List<String> themeTypeList = ThemeTypeDAO.findAll();
		
		request.setAttribute("themeTypeList", themeTypeList);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher
				("/jsp/decide3Props.jsp");
		
		dispatcher.forward(request, response);
	}
   
}
