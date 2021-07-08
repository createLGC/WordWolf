<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Game,model.Player" %>
<%
// セッションスコープからインスタンスを取得
Game game = (Game) session.getAttribute("game");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワードウルフ</title>
</head>
<body>
<h1>お題</h1>
<% for(Player player: game.getPlayers()) { %>
<p><%= player.getName() %>さんのお題は、<%= player.getTheme() %>です。</p>

<br>
<br>
<a>次のプレイヤーへ</a>
<% } %>
<a href="./playGame.jsp">ゲームスタート</a>
</body>
</html>