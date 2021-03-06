package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Game;

/**
 * vote.jspから投票されたプレイヤー名を取得。
 * {@link Game#decideWinner(List<String> wolfNames)}で平民とウルフのどっちが勝ったかを判定。
 * 返り値を"winner"としてリクエストスコープに保存。
 * result.jspにフォワード。
 * @see model.Game
 */
@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * vote.jspから投票されたプレイヤー名を取得。
	 * {@link Game#decideWinner(List<String> wolfNames)}で平民とウルフのどっちが勝ったかを判定。
	 * 返り値を"winner"としてリクエストスコープに保存。
	 * result.jspにフォワード。
	 * @see model.Game
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vote.jspからwolfNamesを受け取る
		List<String> wolfNames = new ArrayList<>();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			wolfNames.add(request.getParameter(names.nextElement()));
		}
		//受け取ったwolfNamesをセッションスコープのgame.decideWinner(List<String> wolfNames)に渡し、結果をリクエストスコープに保存。
		request.setAttribute("winner", ((Game) request.getSession().getAttribute("game")).decideWinner(wolfNames));
		//result.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

}
