<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Game,model.Player" %>
<%
// セッションスコープからインスタンスを取得
//Game game = (Game) session.getAttribute("game");
Game game = new Game(new String[]{"a", "b", "c"});
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
<h1>お題</h1>
<div>
	<ul class="slider">
		<% for(Player player : game.getPlayers()) { %>
	    <li><%= player.getName() %>さんのお題は、<%= player.getTheme() %>です。</li>
	    <% } %>
	</ul>
</div>
<a href="./playGame.jsp">ゲームスタート</a>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.1.0/jquery-migrate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$('.slider').slick({
		autoplay: true,
		dots: true,
	});
</script>
</body>
</html>