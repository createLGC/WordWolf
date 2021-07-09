<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワードウルフ</title>
<link rel="stylesheet" href="../css/InputNumber.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
人数<div class="spinner_area">
    <input type="number" value="3" class="counter1" data-max="10" data-min="3">
    <input type="button" value="＋" class="btnspinner" data-cal="1" data-target=".counter1">
    <input type="button" value="－" class="btnspinner" data-cal="-1" data-target=".counter1">
</div>
<form action="/WordWolf/DecidePlayerServlet" method="post">
	<div id="player_input_container"></div>
	<input type="submit" value="決定">
</form>
<template id="player_input">
	<p >プレイヤー名:<input type="text" name="playerName"></p>
</template>
<script>
$(window).on('load', ()=>{
	for(let i = 0; i < $('.counter1').first().val(); i++){
		addPlayerInput(i);
	}
});
$("input[type=submit]").on('click', e=>{
	e.preventDefault();
	const texts = document.querySelectorAll("input[type=text]");
	for(let i = 0;i < texts.length; i++){
		if(!texts[i].value) return;
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
<script src="../js/InputNumber.js"></script>
</body>
</html>