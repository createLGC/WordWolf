package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ThemeDAO;
import model.Game;

/**
 * decide3Props.jspからウルフの人数、トーク時間、お題の種類を受け取り、
 * セッションスコープのGameインスタンスgameに設定。
 * noticeThemes.jspにフォワード。
 * @see model.Game
 */
@WebServlet("/Decide3PropsServlet")
public class Decide3PropsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * decide3Props.jspからウルフの人数、トーク時間、お題の種類を受け取り、
	 * セッションスコープのGameインスタンスgameに設定。
	 * noticeThemes.jspにフォワード。
	 * @see model.Game
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//numOfWolves, talkTime, themeTypeをgetParameterで取得。
		request.setCharacterEncoding("UTF-8");
		String numOfWolves = request.getParameter("numOfWolves");
		String talkTime = request.getParameter("talkTime");
		String themeType = request.getParameter("themeType");
		
		int strInt = Integer.parseInt(numOfWolves);
		
		//セッションスコープからGameインスタンスを取得。
		Game g = (Game)request.getSession().getAttribute("game");
	
		g.setWolves(strInt);
		g.setTalkTime(talkTime);
		try {
			g.setThemes(ThemeDAO.find(themeType));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher
				("/jsp/noticeThemes.jsp");
		
		dispatcher.forward(request, response);
	}
}
