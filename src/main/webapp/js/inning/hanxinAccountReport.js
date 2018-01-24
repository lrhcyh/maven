$(document).ready( function() {
	

	 $.ajax({
 		url:"finance/getHanxinAccountReport.form",
 		type:"post",
 		data:{"random":1},
		dataType:'json',
 		success:function(data){
 			$("#val2").text(data.credit.toFixed(2)+"元");
 			$("#val3").text(data.withdraw.toFixed(2)+"元");
 			$("#val4").text(data.ajust.toFixed(2)+"元");
 			$("#num1").text(data.num1+"次");
 			$("#num2").text(data.num2+"次");
 			$("#val5").text(data.pay.toFixed(2)+"元");
 			$("#val6").text(data.broker.toFixed(2)+"元");
 			$("#val7").text(data.total.toFixed(2)+"元");
 		}
 	}); 
	
});
