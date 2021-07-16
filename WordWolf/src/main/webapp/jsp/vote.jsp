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
<meta charset="UTF-8">
<title>ワードウルフ</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alignCenter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css">
<style>
.slider {  
     width: 400px;
     height:150px;
     position: relative;
     margin: 0 auto 0.5rem; }
</style>
</head>
<body>
<h1>投票</h1>
<div id="numOfPlayers" style="display:none" numOfPlayers="<%= players.size() %>"></div>
<form action="/WordWolf/VoteServlet" method="post">
    <ul class="slider">
    <% for(int i = 0;i < players.size(); i++) { %>
		<li><br>
		<%= players.get(i).getName() %>さんの投票は、<br>
		<% for(Player player2: players) { %>
			<% if(!players.get(i).getName().equals(player2.getName())) {%>
			<input type="radio" name="<%= "wolfName" + i %>" value="<%= player2.getName() %>"><%= player2.getName() %>さん
			<% } %>
		<% } %>
		</li>
	<% } %>
	</ul>
	<br>
	<input type="submit" value="送信" class="button"><br><br>
	<img src="${pageContext.request.contextPath}/images/wolf-icon.png">
</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.1.0/jquery-migrate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$('.slider').slick({
		prevArrow: "<i class='fa-solid fa-arrow-left slick-prev'></i>",
		nextArrow: "<i class='fa-solid fa-arrow-right slick-next'></i>"
	});
	
	const length = document.getElementById('numOfPlayers').getAttribute('numOfPlayers');
	console.log(length);
	$('input[type=submit]').on('click', e=>{
		e.preventDefault();
		for(let i = 0; i < length; i++){
			const value = document.forms[0].elements['wolfName' + i].value;
			if(!value){
				return;
			}
		}
		document.forms[0].submit();
	});
</script>
</body>
</html>