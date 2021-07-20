package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
 * index.jspからゲームに参加するプレイヤー名を取得。
 * Gameインスタンスを作成し、セッションスコープに保存。
 * お題の種類の一覧を取得し({@link ThemeTypeDAO#findAll()})、リクエストスコープに保存。
 * decide3Props.jspにフォワード。
 * @see model.Game
 */
@WebServlet("/DecidePlayerServlet")
public class DecidePlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * index.jspからゲームに参加するプレイヤー名を取得。
	 * Gameインスタンスを作成し、セッションスコープに保存。
	 * お題の種類の一覧を取得し({@link ThemeTypeDAO#findAll()})、リクエストスコープに保存。
	 * decide3Props.jspにフォワード。
	 * @see model.Game
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String[] playerNames = request.getParameterValues("playerName");
		
		Game game = new Game(playerNames);
		
		//セッションスコープにGameインスタンスのgameを保存
		HttpSession session = request.getSession();
		session.setAttribute("game", game);
		
		//お題の種類の一覧を取得しリクエストスコープに保存
		try {
			request.setAttribute("themeTypeList", ThemeTypeDAO.findAll());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher
				("/jsp/decide3Props.jsp");
		
		dispatcher.forward(request, response);
	}
   
}
