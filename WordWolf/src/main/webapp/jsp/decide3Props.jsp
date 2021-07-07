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
</head>
<body>
<h1>ルール設定</h1>
<form method="POST" action="">
ウルフの人数<p class="spinner_area">
    <input type="number" value="a" class="counter1" data-max="a -1" data-min="1">
    <input type="button" value="＋" class="btnspinner" data-cal="1" data-target=".counter1">
    <input type="button" value="－" class="btnspinner" data-cal="-1" data-target=".counter1">
</p>

<p class="pleytime">トーク時間：<input type="time" name="example" list="data1">
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
</datalist></p>
お題の種類：<select name="example2">
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
<script>
$(function(){
	
	$('input[type=number]').on();
    
    var arySpinnerCtrl = [];
    var spin_speed = 20; //変動スピード
    
    //長押し押下時
    $('.btnspinner').on('touchstart mousedown click', function(e){
        if(arySpinnerCtrl['interval']) return false;
        var target = $(this).data('target');
        arySpinnerCtrl['target'] = target;
        arySpinnerCtrl['timestamp'] = e.timeStamp;
        arySpinnerCtrl['cal'] = Number($(this).data('cal'));
        //クリックは単一の処理に留める
        if(e.type == 'click'){
            spinnerCal();
            arySpinnerCtrl = [];
            return false;
        }
        //長押し時の処理
        setTimeout(function(){
            //インターバル未実行中 + 長押しのイベントタイプスタンプ一致時に計算処理
            if(!arySpinnerCtrl['interval'] && arySpinnerCtrl['timestamp'] == e.timeStamp){
                arySpinnerCtrl['interval'] = setInterval(spinnerCal, spin_speed);
            }
        }, 500);
    });
    
    //長押し解除時 画面スクロールも解除に含む
    $(document).on('touchend mouseup scroll', function(e){
        if(arySpinnerCtrl['interval']){
            clearInterval(arySpinnerCtrl['interval']);
            arySpinnerCtrl = [];
        }
    });
    
    //変動計算関数
    function spinnerCal(){
        var target = $(arySpinnerCtrl['target']);
        var num = Number(target.val());
        num = num + arySpinnerCtrl['cal'];
        if(num > Number(target.data('max'))){
            target.val(Number(target.data('max')));
        }else if(Number(target.data('min')) > num){
            target.val(Number(target.data('min')));
        }else{
            target.val(num);
        }
    }
    
});
</script>
</body>
</html>