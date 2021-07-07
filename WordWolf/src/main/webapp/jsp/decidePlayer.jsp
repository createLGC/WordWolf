<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワードウルフ</title>
<style>
.btnspinner{
    -ms-user-select: none;
    -moz-user-select: -moz-none;
    -khtml-user-select: none;
    -webkit-user-select: none;
    user-select: none;
}

.spinner_area input{
    padding: 11px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 18px;
    width: auto;
    vertical-align: middle;
    /* デフォルトのスピナーを消す */
    -webkit-appearance: none;
    -moz-appearance:textfield;
}

.spinner_area input[type="button"]{
    cursor: pointer;
}
</style>
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
<script src="../js/decidePlayer.js"></script>
</body>
</html>