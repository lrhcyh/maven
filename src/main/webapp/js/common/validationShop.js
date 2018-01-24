//用jquery实现无刷新验证
function ValidateRenameJQuery() {
    $.ajax({
        url: 'ValidateShopRenameJQuery.action', //访问路径
        data: 'shop.shopName=' + $("#shopName").val(), //需要验证的参数
        type: 'post', //传值的方式
        error: function () {//访问失败时调用的函数
            alert("链接服务器错误！");
        },
        success: function (msg) {//访问成功时调用的函数,这里的msg是Login.ashx返回的值
            //alert(msg);
        	$("#shopNameSpan").html(msg)
        }
    });
}

function validateShopName() {
	var shopName = document.getElementById("shopName").value;
	if ("" == shopName) {
		document.getElementById("shopNameSpan").innerHTML = "请输入店铺名";
		return false;
	}
	document.getElementById("shopNameSpan").innerHTML = "";
	return true;
}

function validatUpSendAmount() {
	var upSendAmount = document.getElementById("upSendAmount").value;
	if (""==upSendAmount) {
		document.getElementById("upSendAmountSpan").innerHTML = "请输入配送金额";
		return false;
	}
	for (var i = 0; i < upSendAmount.length; i++) { 
		var charUpS = upSendAmount.charAt(i); 
		if (!(charUpS >= 0 && charUpS <= 9)) { 
			document.getElementById("upSendAmountSpan").innerHTML = "配送金额必须为数字";  
			return false; 
		} 
	} 
	document.getElementById("upSendAmountSpan").innerHTML = "";
	return true;
}

function validateLongPoint(){
	var longPoint = document.getElementById("longPoint").value;
	if (""==longPoint) {
		document.getElementById("longPointSpan").innerHTML = "请输入经度值";
		return false;
	}
	for (var i = 0; i < longPoint.length; i++) { 
		var charLongP = longPoint.charAt(i); 
		if (!(charLongP >= 0 && charLongP <= 9)) { 
			document.getElementById("longPointSpan").innerHTML = "经度值必须为数字";  
			return false; 
		} 
	} 
	document.getElementById("longPointSpan").innerHTML = "";
	return true;
}

function validateLetPoint(){
	var letPoint = document.getElementById("letPoint").value;
	if (""==letPoint) {
		document.getElementById("letPointSpan").innerHTML = "请输入纬度值";
		return false;
	}
	for (var i = 0; i < letPoint.length; i++) { 
		var charLetP = letPoint.charAt(i); 
		if (!(charLetP >= 0 && charLetP <= 9)) { 
			document.getElementById("letPointSpan").innerHTML = "纬度值必须为数字";  
			return false; 
		} 
	} 
	document.getElementById("letPointSpan").innerHTML = "";
	return true;
}

function validateUpload() {
	var logoUrl = document.getElementById("upload").value;
	if (logoUrl == "") {  
        document.getElementById("uploadSpan").innerHTML = "请选择图片";  
        return false;  
    } else {  
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(logoUrl)) {  
        	 document.getElementById("uploadSpan").innerHTML = "图片类型必须是.gif,jpeg,jpg,png中的一种";  
            logoUrl.value = "";  
            return false;  
        }  
    } 
	document.getElementById("uploadSpan").innerHTML = "";
    return true;  
}

function validateAll() {
	if (validateShopName() && validatUpSendAmount()&&validateLongPoint()&&validateLetPoint()&&validateUpload()) {
		document.getElementById("checkAllDiv").innerHTML = "测试通过，可以尝试提交";
		return true;
	} else {
		document.getElementById("checkAllDiv").innerHTML = "未通过测试，无法提交";
		return false;
	}
}

function test_OnReset() {
	var shopName, remark, bussinessTimePeriod, upSendAmount,bussinessArea,longPoint,letPoint;

	shopName = document.getElementById("shopName");
	shopName.value = "";
	remark = document.getElementById("remark");
	remark.value = "";
	bussinessTimePeriod = document.getElementById("bussinessTimePeriod");
	bussinessTimePeriod.value = "";
	upSendAmount = document.getElementById("upSendAmount");
	upSendAmount.value = "";
	bussinessArea = document.getElementById("bussinessArea");
	bussinessArea.value = "";
	longPoint = document.getElementById("longPoint");
	longPoint.value = "";
	letPoint = document.getElementById("letPoint");
	letPoint.value = "";
	
}