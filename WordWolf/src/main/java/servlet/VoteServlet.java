package servlet;

import java.io.IOException;

<<<<<<< HEAD
import javax.servlet.RequestDispatcher;
=======
>>>>>>> branch 'master' of https://github.com/Kota-T/WordWolf.git
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		//vote.jspからwolfNamesを受け取る
		String wolfNames = request.getParameter("wolfNames");
		//受け取ったwolfNamesをセッションスコープのgame.decideWinnerに渡し、結果をリクエストスコープに保存。
		request.setAttribute("winner", request.getSession().getAttribute("game").decideWinner(wolfNames));
		//result.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
=======
		
>>>>>>> branch 'master' of https://github.com/Kota-T/WordWolf.git
	}

}
