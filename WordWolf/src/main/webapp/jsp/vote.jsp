<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Game,model.Player,java.util.List" %>
<%
// セッションスコープからインスタンスを取得
Game game = (Game) session.getAttribute("game");
List<Player> players = game.getPlayers();
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>ワードウルフ</title>
</head>
<body>
<h1>投票</h1>
<form action="/WordWolf/VoteServlet" method="post">
    <ul class="slider">
    <% for(int i = 0;i < players.size(); i++) { %>
		<li>
		<%= players.get(i).getName() %>さんの投票は、<br>
		<% for(Player player2: players) { %>
			<% if(!players.get(i).getName().equals(player2.getName())) {%>
			<input type="radio" name="<%= "wolfName" + i %>" value="<%= player2.getName() %>"><%= player2.getName() %>さん
			<% } %>
		<% } %>
		</li>
	<% } %>
	</ul>
	<input type="submit" value="送信">
</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.1.0/jquery-migrate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$('.slider').slick({
		prevArrow: "<p><i class=\"fa-solid fa-arrow-left slick-prev\"></i></p>",
		nextArrow: "<p><i class=\"fa-solid fa-arrow-right slick-next\"></i></p>"
	});
</script>
</body>
</html>