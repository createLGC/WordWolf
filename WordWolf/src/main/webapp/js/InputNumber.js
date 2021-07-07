/**
 * jquery使用
 */
$(function(){
    let arySpinnerCtrl = {};
    const SPIN_SPEED = 20; //変動スピード
    
    //長押し押下時
    $('.btnspinner').on('touchstart mousedown click', function(e){
        if(arySpinnerCtrl.interval) return false;

        arySpinnerCtrl = {
        	target: $(this).data('target'),
        	timestamp: e.timestamp,
        	cal: Number($(this).data('cal'))
        };
        //クリックは単一の処理に留める
        if(e.type === 'click'){
            spinnerCal();
            arySpinnerCtrl = {};
            return false;
        }
        //長押し時の処理
        setTimeout(function(){
            //インターバル未実行中 + 長押しのイベントタイプスタンプ一致時に計算処理
            if(!arySpinnerCtrl.interval && arySpinnerCtrl.timestamp === e.timeStamp){
                arySpinnerCtrl.interval = setInterval(spinnerCal, SPIN_SPEED);
            }
        }, 500);
    });
    
    //長押し解除時 画面スクロールも解除に含む
    $(document).on('touchend mouseup scroll', function(){
        if(arySpinnerCtrl.interval){
            clearInterval(arySpinnerCtrl.interval);
            arySpinnerCtrl = {};
        }
    });
    
    //変動計算関数
    function spinnerCal(){
    	const target = $(arySpinnerCtrl.target);
    	const oldNum = Number(target.val())
		const newNum = oldNum + arySpinnerCtrl.cal;
        if(newNum > Number(target.data('max'))){
            target.val(Number(target.data('max')));
        }else if(Number(target.data('min')) > newNum){
            target.val(Number(target.data('min')));
        }else{
            target.val(newNum);
		}
		
		//プレーヤー名入力欄を増減させる
		onChangeNumber?.(target.val(), oldNum);
    }
  
});