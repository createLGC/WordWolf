<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Game" %>
<% 
Game game = (Game) session.getAttribute("game");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ワードウルフ</title>
<style>

</style>
</head>
<body>
<h1>カウントダウン</h1>
<p class="timer">あと
<span id="min"><%= game.getTalkTime() / 60 %></span>分
<span id="sec"><%= game.getTalkTime() % 60 %></span>秒
</p>トーク時間：<input type="button" value="-" id="subTime"><input type="button" value="+" id="addTime">
<p>
</p>
<input type="button" value="ストップ" id="startOrStop">
<p id="isCounting">カウントダウン起動中</p>
<br><br>



<a href="/WordWolf/jsp/vote.jsp"><input type="submit" value="トーク終了"></a>
<script>
let isCounting = true;
const min = document.getElementById("min");
const sec = document.getElementById("sec");
setInterval(function(){
	if(!isCounting) return;
	
	if(min.textContent == 0 && sec.textContent == 0){
		location.href = "vote.jsp";
		return;
	}
	sec.textContent--;
	if(sec.textContent == -1){
		sec.textContent = 59;
		min.textContent--;
	}
}, 1000);

document.getElementById('addTime').onclick = e=>{
	const minValue = Number.parseInt(min.textContent);
	const secValue = Number.parseInt(sec.textContent);
	sec.textContent = secValue + 10;
	if(sec.textContent >= 60){
		min.textContent = minValue + 1;
		sec.textContent -= 60;
	}
}

document.getElementById('subTime').onclick = e=>{
	const secValue = Number.parseInt(sec.textContent);
	sec.textContent -= 10;
	if(sec.textContent < 0){
		min.textContent--;
		sec.textContent = secValue + 60;
	}
}

const button = document.getElementById('startOrStop');
const paragraph = document.getElementById('isCounting');

button.addEventListener('click', function() {
  if (button.value === 'ストップ') {
    button.value = 'スタート';
    paragraph.textContent = 'カウントダウン停止中';
  } else {
    button.value = 'ストップ';
    paragraph.textContent = 'カウントダウン起動中';
  }
  isCounting = !isCounting;
});
</script>
</body>
</html>