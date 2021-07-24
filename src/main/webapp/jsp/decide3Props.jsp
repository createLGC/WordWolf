<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Game,java.util.List" %>
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
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ワードウルフ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/InputNumber.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alignCenter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/decidePlayer.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>ルール設定</h1>
<form method="POST" action="${pageContext.request.contextPath}/Decide3PropsServlet">
ウルフの人数
<p class="spinner_area">
    <input type="number" value="1" class="counter1" data-max="<%= (game.getPlayers().size() - 1) / 2 %>" data-min="1" name="numOfWolves">
    <input type="button" value="＋" class="btnspinner" data-cal="1" data-target=".counter1">
    <input type="button" value="－" class="btnspinner" data-cal="-1" data-target=".counter1">
</p>

<p class="playtime">
トーク時間：<input type="time" name="talkTime" list="data1" value="10:00">
	<datalist id="data1">
		<option value="01:00"></option>
		<option value="02:00"></option>
		<option value="03:00"></option>
		<option value="04:00"></option>
		<option value="05:00"></option>
		<option value="10:00" selected></option>
		<option value="15:00"></option>
		<option value="20:00"></option>
		<option value="30:00"></option>
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
<input type="submit" value="ルール決定" class="button">
</form>
<p><b>※ルール説明</b><span class="ques">?<span class="ex">ワードウルフとは<br>
単語を使った人狼のようなゲームです。<br><br>
①ルール決定後にランダムで、村人（多数派）のワードかウルフ（少数派）のワードが配布されます。<br><br>
②時間切れまで参加者同士で配られたワードについて話し、誰がウルフ（のワードを配られた人）かを探ります。<br>
※自分が人狼だと思ったらそれを悟られないようにしましょう。<br><br>
③時間切れになったら投票タイムです。それぞれがウルフだと思う人に投票します。<br><br>
④最多票が村人だった場合はウルフの勝利です。</span></span></p>
<img src="${pageContext.request.contextPath}/images/wolf-icon.png">
<br><a href="${pageContext.request.contextPath}/jsp/help.jsp">ヘルプ</a>
<script>
$("input[type=submit]").on('click', e=>{
	e.preventDefault();
	if(!$("select[name=themeType]").val()) return;
	alert('最初のプレイヤーのお題を表示します。（※他のプレイヤーは見ないで下さい。）');
	document.forms[0].submit();
});
onChangeNumber = null;
</script>
<script src="${pageContext.request.contextPath}/js/InputNumber.js"></script>
</body>
</html>