package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Game;

/**
 * Servlet implementation class Decide3PropsServlet
 */
@WebServlet("/Decide3PropsServlet")
public class Decide3PropsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		g.setThemes(themeType);
		
		//フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher
				("/jsp/noticeThemes.jsp");
		
		dispatcher.forward(request, response);
	}
}
