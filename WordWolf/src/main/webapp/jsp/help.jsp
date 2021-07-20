<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>help</title>
<link rel="stylesheet" href="../css/alignCenter.css">
<style>
.accordion-container {
  position: relative;
  width: 100%;
  border: 1px solid #0079c1;
  border-top: none;
  outline: 0;
  cursor: pointer;
}

.accordion-container .accordion-title {
  display: block;
  position: relative;
  margin: 0;
  padding: 0.625em 0.625em 0.625em 0.625em;
  font-size: 1.25em;
  font-weight: normal;
  color: #fff;
  background: #000;
  cursor: pointer;
}

.accordion-container .accordion-title:hover,
.accordion-container .accordion-title:active,
.accordion-container .content-entry.open .accordion-title {
  background-color: #00aaa7;
  color: white;
}

.accordion-container .accordion-title:hover i:before,
.accordion-container .accordion-title:hover i:active,
.accordion-container .content-entry.open i {
  color: white;
}

.accordion-title{
  position: relative;
}

.accordion-title:after {
  content: "";
  position: absolute;
  right: 25px;
  top: 38%;
  transition: all 0.2s ease-in-out;
  display: block;
  width: 8px;
  height: 8px;
  border-top: solid 2px #fff;
  border-right: solid 2px #fff;
  -webkit-transform: rotate(135deg);
  transform: rotate(135deg);
}

.accordion-title.open:after {
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
  top: 45%;
}

.accordion-content {
  display: none;
}

/* CSS for CodePen */
.accordion-container {
  width: 300px;
  margin: 1.875em auto;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function ($) {
	$('.js-accordion-title').on('click', function () {
	  /*クリックでコンテンツを開閉*/
	  $(this).next().slideToggle(200);
	  /*矢印の向きを変更*/
	  $(this).toggleClass('open', 200);
	});
});
</script>
</head>
<body>
<h1>ヘルプ</h1>
<div id="accordion" class="accordion-container">
  <h4 class="accordion-title js-accordion-title">遊び方</h4>
  <div class="accordion-content">
    <p>ワードウルフとは<br>
単語を使った人狼のようなゲームです。<br><br>
①ルール決定後にランダムで、村人（多数派）のワードかウルフ（少数派）のワードが配布されます。<br><br>
②時間切れまで参加者同士で配られたワードについて話し、誰がウルフ（のワードを配られた人）かを探ります。<br>
※自分が人狼だと思ったらそれを悟られないようにしましょう。<br><br>
③時間切れになったら投票タイムです。それぞれがウルフだと思う人に投票します。<br><br>
④最多票が村人だった場合はウルフの勝利です。</p>
  </div><!--/.accordion-content-->

  <h4 class="accordion-title js-accordion-title">参加人数</h4>
  <div class="accordion-content">
    <p>参加できる人数は 3～10人 です。</p>
  </div><!--/.accordion-content-->

  <h4 class="accordion-title js-accordion-title">ウルフの人数</h4>
  <div class="accordion-content">
    <p>ウルフ（少数派）の人数は<br>
    参加人数の半数未満で設定できます。<br><br>
    (例)参加人数 5人 のときは<br>
    ウルフは 1～2人 です。
    </p>
  </div><!--/.accordion-content-->
  
  <h4 class="accordion-title js-accordion-title">お題の種類</h4>
  <div class="accordion-content">
    <p>話し合うお題の種類を決めます。<br>
    ルール決定後に、各プレイヤー1人ずつ<br>
    ランダムでお題が表示されます。</p>
  </div><!--/.accordion-content-->
  
  <h4 class="accordion-title js-accordion-title">投票</h4>
  <div class="accordion-content">
    <p>各プレイヤー1人ずつ、ウルフ（少数派）<br>
    だと思うプレイヤー1人に投票します。</p>
  </div><!--/.accordion-content-->

</div><!--/#accordion-->

<button type="button" onclick="history.back()">戻る</button>

</body>
</html>