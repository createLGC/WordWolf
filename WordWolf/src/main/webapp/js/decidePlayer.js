/**
 * jquery使用
 */
$(function(){
	
	const playerInputs = [];
   
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
    $(document).on('touchend mouseup scroll', function(e){
        if(arySpinnerCtrl.interval){
            clearInterval(arySpinnerCtrl.interval);
            arySpinnerCtrl = {};
        }
    });
    
    //変動計算関数
    function spinnerCal(){
    	const target = $(arySpinnerCtrl.target);
    	const num = Number(target.val()) + arySpinnerCtrl.cal;
        if(num > Number(target.data('max'))){
            target.val(Number(target.data('max')));
        }else if(Number(target.data('min')) > num){
            target.val(Number(target.data('min')));
        }else{
            target.val(num);
		}
		
		//プレーヤー名入力欄を増減させる
		if(target.val() > num){
			for(let i = num; i < target.val(); i++){
				$("#player_input")
				.children()
				.first()
				.clone()
				.appendTo("#player_input_container");
			}
		}else if(target.val() < num){
			for(let i = num; i > target.val(); i--){
				$("#player_input")
				.children()
				.last()
				.remove();
			}
		}
		
    }
  
});