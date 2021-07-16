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
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alignCenter.css">
<style>
.slider { right: 20px }
</style>
</head>
<body>
<h1>お題</h1>
<br>
<div>
	<ul class="slider">
		<% for(Player player : game.getPlayers()) { %>
	    <li><br><%= player.getName() %>さんのお題は、<b><%= player.getTheme() %></b>です。<br><br></li>
	    <% } %>
	</ul>
</div>
<br><br>
<a href="/WordWolf/jsp/playGame.jsp">ゲームスタート</a><br><br>
<img src="./images/wolf-icon.png">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.1.0/jquery-migrate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$('.slider').slick({
		prevArrow: "<button type='button'>←前のプレイヤーのお題へ</button>",
		nextArrow: "<button type='button'>次のプレイヤーのお題へ→</button>"
	});
</script>
</body>
</html>