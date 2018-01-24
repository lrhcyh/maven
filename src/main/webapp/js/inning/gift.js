function searchCouponUseDetail() {
	var id = $("#activity").datalist('getSelected').id;
	var number = $('#phoneNumber').textbox('getValue');
	$('#useDetailDg').datagrid('load', {
		activityId : id,
		phoneNumber : number
	});
}

function sendCoupon() {
	var row = $('#detailDg').datagrid('getSelected');
	// var row = $('#detailDg').datagrid('getData').rows[0];
	if (row == null) {
		$.messager.alert('消息', '请选择要发送的优惠券', 'info');
		return false;
	}
	/*if (row.couponScene != 9) {
		$.messager.alert('提示', '不符合领券场景的条件');
		return false;
	}*/

	showWindow(basepath + '/pages/gift/send.jsp', 420, 200, '赠送优惠券');
}

function checkCanSend() {
	var msg = "";

	var phone = $("#phone").val();

	if (phone == "") {
		msg = "<font color='red'>*电话不能为空！</font>";
		$("#msg").html(msg);
		return;
	}

	var rex = /^1[3-8]+\d{9}$/;
	if (!rex.test(phone)) {
		msg = "<font color='red'>*请输入正确的手机号</font>";
		$("#msg").html(msg);
		return;
	}

	var row = $('#detailDg').datagrid('getSelected');
	if (row.leaveCupon < 1) {
		msg = "<font color='red'>*优惠券已发完</font>";
		$("#msg").html(msg);
		return;
	}

	var win = $.messager.progress({
		title : '正在提交',
		msg : '正在生成优惠劵,请稍等...'
	});

	var activityId = $("#activity").datalist('getSelected').id;
	var ruleId = row.id;
	$.ajax({
		url : 'gift/sendCoupon.form',
		data : {
			"activityId" : activityId,
			"ruleId" : ruleId,
			"status" : status,
			"phone" : phone,
			"couponType":row.couponType
		},
		type : 'POST',
		success : function(json) {
			if (json.result == 1) {
				$.messager.progress('close');
				msg = "<font color='red'>*用户不存在！</font>";
				document.getElementById("msg").innerHTML = msg;
				return false;
			} else if (json.result == 2) {
				$.messager.progress('close');
				msg = "<font color='red'>*超过个人限领张数！</font>";
				document.getElementById("msg").innerHTML = msg;
				return false;
			} else if (json.result == 3) {
				$.messager.progress('close');
				$('#detailDg').datagrid('resize');
				$('#detailDg').datagrid('load', {
					activityId : activityId
				});
				$('#useDetailDg').datagrid('resize');
				$('#useDetailDg').datagrid('load', {
					activityId : activityId
				});
				$('#phoneNumber').textbox('reset');
				closeWin();
				$.messager.alert('提示', '券已发放');
			} else if (json.result == 4) {
				$.messager.progress('close');
				msg = "<font color='red'>*活动已结束，优惠券无法发放！</font>";
				document.getElementById("msg").innerHTML = msg;
				return false;
			}else if (json.result == 5) {
				$.messager.progress('close');
				msg = "<font color='red'>*企业用户不能发送优惠券！</font>";
				document.getElementById("msg").innerHTML = msg;
				return false;
			} else if (json.result == 6) {
				$.messager.progress('close');
				msg = "<font color='red'>*集团用户只能发送提现券！</font>";
				document.getElementById("msg").innerHTML = msg;
				return false;
			}  else {
				$.messager.progress('close');
				$.messager.alert("提示", "发放失败,请联系技术中心!", "info");
			}

		},
		error : function(a, b, c) {
			$.messager.progress('close');
			$.messager.alert("提示", "发放失败,请联系技术中心!", "info");
		}
	});
}

function checkPhoneNumber() {
	var phone = $("#phone").val();
	if (phone == null || phone == "") {
		$("#msg").html("<font color='red'>*电话号码不能为空！</font>");
		return;
	}
	var rex = /^1[3-8]+\d{9}$/;
	if (!rex.test(phone)) {
		$("#msg").html("<font color='red'>*请输入正确的电话号码！</font>");
		return;
	}
	$("#msg").html("");

}