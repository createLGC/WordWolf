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
    <input type="number" value="3" class="counter1" data-max="10" data-min="0">
    <input type="button" value="＋" class="btnspinner" data-cal="1" data-target=".counter1">
    <input type="button" value="－" class="btnspinner" data-cal="-1" data-target=".counter1">
</div>
<div id="player_input_container"></div>
<input type="submit" value="決定">
<template id="player_input">
	<p >プレイヤー名:<input type="text"></p>
</template>
<script>
function onChangeNumber(newNum, oldNum){
	if(newNum > oldNum){
		console.log("add");
		$(document.getElementById('player_input').content.cloneNode(true))
		.appendTo("#player_input_container");
	}else if(newNum < oldNum){
		console.log("remove");
		$("#player_input_container")
		.children()
		.last()
		.remove();
	}	
}
</script>
<script src="../js/InputNumber.js"></script>
</body>
</html>