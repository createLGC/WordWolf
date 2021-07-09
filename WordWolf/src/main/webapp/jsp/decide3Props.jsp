<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Game" %>
<%@ page import="java.util.List" %>
<% 
// リクエストスコープからインスタンスを取得
List<String> themeTypeList = (List<String>) request.getAttribute("themeTypeList");
// セッションスコープからインスタンスを取得
Game game = (Game) session.getAttribute("game");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワードウルフ</title>
<link rel="stylesheet" href="../css/InputNumber.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>ルール設定</h1>
<form method="POST" action="/WordWolf/Decide3PropsServlet">
ウルフの人数
<p class="spinner_area">
    <input type="number" value="1" class="counter1" data-max="<%= game.getPlayers().size() / 2 %>" data-min="1" name="numOfWolves">
    <input type="button" value="＋" class="btnspinner" data-cal="1" data-target=".counter1">
    <input type="button" value="－" class="btnspinner" data-cal="-1" data-target=".counter1">
</p>

<p class="playtime">
トーク時間：<input type="time" name="talkTime" list="data1">
	<datalist id="data1">
		<option value="01:00"></option>
		<option value="02:00"></option>
		<option value="03:00"></option>
		<option value="04:00"></option>
		<option value="05:00"></option>
		<option value="10:00"></option>
		<option value="15:00"></option>
		<option value="20:00"></option>
		<option value="30:00"></option>
		<option value="60:00"></option>
		<option value="90:00"></option>
	</datalist>
</p>
お題の種類：
<select name="themeType">
	<option selected disabled>お題の種類を選択</option>
	<% for(String themeType : themeTypeList) { %>
		<option><%= themeType %></option>
	<% } %>
</select>
<br><br>
<input type="submit" value="ルール決定">
</form>
<script src="../js/InputNumber.js"></script>
</body>
</html>