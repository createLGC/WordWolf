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
<h1>ルール設定</h1>
<form method="POST" action="">
ウルフの人数
<p class="spinner_area">
    <input type="number" value="a" class="counter1" data-max="a -1" data-min="1" name="numOfWolves">
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
	<option value="">5つの選択肢を表示</option>
	<option value="選択肢2">選択肢2</option>
	<option value="選択肢3">選択肢3</option>
	<option value="選択肢4">選択肢4</option>
	<option value="選択肢5">選択肢5</option>
	<option value="選択肢6">選択肢6</option>
	<option value="選択肢7">選択肢7</option>
</select>
<br><br>
<input type="submit" value="ルール決定">
</form>
<script src="../js/InputNumber.css"></script>
</body>
</html>