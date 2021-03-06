<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//セッションスコープからGameインスタンスを削除
session.removeAttribute("game");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ワードウルフ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/InputNumber.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/decidePlayer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alignCenter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/blackboard.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/slideimages.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#admin-link{
	position: fixed;
	top: 10px;
	right: 10px;
}
</style>
</head>
<body>
<a id="admin-link" href="javascript:goToAdmin()">開発者ページへ</a>
<h1>ワードウルフ</h1>
<div class="slide" id="makeImg">
  <img src="${pageContext.request.contextPath}/images/wordwolf-top.jpg">
  <img src="${pageContext.request.contextPath}/images/wordwolf-top2.jpg">
  <img src="${pageContext.request.contextPath}/images/wordwolf-top3.jpg">
</div>
<br>
<div class="blackboard">
<p>参加人数</p>
</div>
<div class="spinner_area">
    <input type="number" value="3" class="counter1" data-max="10" data-min="3" readonly>
    <input type="button" value="＋" class="btnspinner" data-cal="1" data-target=".counter1">
    <input type="button" value="－" class="btnspinner" data-cal="-1" data-target=".counter1">
</div>
<form action="${pageContext.request.contextPath}/DecidePlayerServlet" method="post">
	<div id="player_input_container"></div>
	<input type="submit" value="決定" class="button">
</form>
<template id="player_input">
	<p >プレイヤー名:<input type="text" name="playerName"></p>
</template>
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
function goToAdmin(){
	const pwd = window.prompt("パスワードを入力");
	location.href = encodeURI("${pageContext.request.contextPath}/admin?pwd=" + pwd);
}
$(window).on('load', ()=>{
	for(let i = 0; i < $('.counter1').first().val(); i++){
		addPlayerInput(i);
	}
});
$("input[type=submit]").on('click', e=>{
	e.preventDefault();
	const texts = document.querySelectorAll("input[type=text]");
	for(let i = 0; i < texts.length; i++){
		if(!texts[i].value){return;}
		for(let j = i + 1; j < texts.length; j++){
			if(texts[i].value === texts[j].value){return;}
		}
	}
	document.forms[0].submit();
});
function addPlayerInput(num){
	$(document.getElementById('player_input').content.cloneNode(true))
	.appendTo("#player_input_container");
}
function removePlayerInput(){
	$("#player_input_container")
	.children()
	.last()
	.remove();
}
function onChangeNumber(newNum, oldNum){
	if(newNum > oldNum){
		addPlayerInput(newNum);
	}else if(newNum < oldNum){
		removePlayerInput();
	}	
}     
</script>
<script src="${pageContext.request.contextPath}/js/InputNumber.js"></script>
</body>
</html>