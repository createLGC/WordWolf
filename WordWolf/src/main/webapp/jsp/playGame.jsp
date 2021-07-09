<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<p class="until">トーク時間</p>
<p class="timer">あと
<span id="hour"></span>時間
<span id="min"></span>分
<span id="sec"></span>秒
</p>トーク時間：<input type="button" value="-"><input type="button" value="+">
<p>
</p>
<form>
  <input type="button" value="ストップ">
<p>カウントダウン起動中</p>
<br><br>

<input type="submit" value="トーク終了">
</form>
<script>
function countdown(){
	const now=new Date();//今の時間
	const tomorrow=new Date(now.getFullYear(),now.getMonth(),now.getDate()+1);//明日の0:00
	const differ=tomorrow.getTime()-now.getTime();//あと何秒か計算


	const sec=Math.floor(differ/1000)%60;//ミリ秒を秒に直してから
	const min=Math.floor(differ/1000/60)%60;//1時間=60分だからね
	const hour=Math.floor(differ/1000/60/60);


	document.getElementById("hour").textContent=String(hour).padStart(2,"0"); //一桁になった時0がつくように
	document.getElementById("min").textContent=String(min).padStart(2,"0")
	document.getElementById("sec").textContent=String(sec).padStart(2,"0")
	setTimeout(countdown,1000);//1秒毎に繰り返す
}
countdown();
</script>
<script>
const button = document.querySelector('input');
const paragraph = document.querySelector('p');

button.addEventListener('click', updateButton);

function updateButton() {
  if (button.value === 'ストップ') {
    button.value = 'スタート';
    paragraph.textContent = 'カウントダウン停止中';
  } else {
    button.value = 'ストップ';
    paragraph.textContent = 'カウントダウン起動中';
  }
}
</script>
</body>
</html>