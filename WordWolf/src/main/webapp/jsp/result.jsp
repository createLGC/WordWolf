<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String winner = (String) request.getAttribute("winner");
session.removeAttribute("game");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワードウルフ</title>
<body>
<h1>結果</h1>
<p>投票結果は・・・</p>
<% if(winner.equals("person")){ %>
<p>平民の勝ち</p>
<% } else if(winner.equals("wolf")){ %>
<p>ウルフの勝ち</p>

<% } %>
<br>
<br>
<a href="./jsp/decidePlayer.jsp">終了</a>
</body>
</html>
