//用jquery实现无刷新验证
function ValidateRenameJQuery() {
    $.ajax({
        url: 'ValidateMerchantRenameJQuery.action', //访问路径
        data: 'merchant.merchantName=' + $("#merchantName").val(), //需要验证的参数
        type: 'post', //传值的方式
        error: function () {//访问失败时调用的函数
            alert("链接服务器错误！");
        },
        success: function (msg) {//访问成功时调用的函数,这里的msg是Login.ashx返回的值
            //alert(msg);
        	$("#merchantNameSpan").html(msg)
        }
    });
}

function validateMerchantName() {
	var merchantName = document.getElementById("merchantName").value;
	if ('' == merchantName) {
		document.getElementById("merchantNameSpan").innerHTML = "请输入用户名";
		return false;
	}
	document.getElementById("merchantNameSpan").innerHTML = "";
	return true;
}

function validatContactPhone() {
	var contactPhone = document.getElementById("contactPhone").value;
	var cellPLength = contactPhone.length;
	if (''==contactPhone) {
		document.getElementById("contactPhoneSpan").innerHTML = "请正确输入联系人电话号码";
		return false;
	}
	if(cellPLength <7|| cellPLength>15) {
		document.getElementById("contactPhoneSpan").innerHTML = "联系人电话号码位数不正确";
		return false;
	}
	for (var i = 0; i < contactPhone.length; i++) { 
		var charCellP = contactPhone.charAt(i); 
		if (!(charCellP >= 0 && charCellP <= 9)) { 
			div.innerHTML = "请正确输入联系人电话号码";  
			return false; 
		} 
	} 
	document.getElementById("contactPhoneSpan").innerHTML = "";
	return true;
}

function validateAll() {
	if (validateMerchantName()&&validatContactPhone()) {
		document.getElementById("checkAllDiv").innerHTML = "测试通过，可以尝试提交";
		return true;
	} else {
		document.getElementById("checkAllDiv").innerHTML = "未通过测试，无法提交";
		return false;
	}
}

function test_OnReset() {
	var merchantName, remark, contact, contactPhone;

	merchantName = document.getElementById("merchantName");
	merchantName.value = "";
	remark = document.getElementById("remark");
	remark.value = "";
	contact = document.getElementById("contact");
	contact.value = "";
	contactPhone = document.getElementById("contactPhone");
	contactPhone.value = "";
	
}