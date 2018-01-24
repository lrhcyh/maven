function closeWin() {
	$('#myWindow').window('destroy');
	$('#windowParent').html("<div id='myWindow'></div><div id='mySubWindow'></div>");
}

function addTab(url, title) {
	
	$('#centerArea').panel({
		 href: url,
		 cache: false
	});
//	
//	if ($("#centerArea").tabs('exists', title)) {
//		$("#centerArea").tabs('getTab', title).panel('options').href=url;
//		$("#centerArea").tabs('select', title);
//	} else {
//		$("#centerArea").tabs("add", {
//			title : title,
//			href : url,
//			closable : true,
//			cache : false
//		});
//	}
}

//显示加载进度条
function showProgress(){
	$.messager.progress({
        msg: '努力加载中...',
		interval:10
	});
}

//关闭加载进度条
function closeProgress(){
	setTimeout(function() {
        $.messager.progress('close');
    }, 100);
}


function tabsWin(){
	
	$("#centerArea").panel('refresh');
//	var mytab = $("#centerArea").tabs('getSelected');
//	$("#centerArea").tabs('update', {tab : mytab,
//		options:{title : mytab.panel('options').title,
//			href : mytab.panel('options').href,
//			closable : true,
//			cache : false
//		}
//	});	
}

function showWindow(url,inwidth,inheight,intitle){
	$('#myWindow').window({
		collapsible : false,
		minimizable : false,
		maximizable : false,
		closable : true,
		href : url,
		width :inwidth,
		height :inheight,
		modal : true,
		cache : false,
		title : intitle
	});
}

function exitOperater(){
	$.messager.confirm('消息', '您确定要退出系统?', function(r){
		if(r){
			var url=basepath + "login/loginout.form";
			ajaxUtils.post(url, null,function(json) {//回调函数
				/*var json=eval('('+json+')');
				if (json.result=='success') {
					$.messager.alert('消息','注销成功','info');
					window.location.href=basepath + "login.jsp";
				}else {
					$.messager.alert('错误','操作失败','error');
				}*/
			});	
			window.location.href=basepath + "login.jsp";
		}
	});
}

function changePassword(){
	showWindow(basepath + '/login/changePassword.form',600,300,'修改密码');
}

function modifyPassword(){
	 var operatorPassword=$("#operatorPassword").val();
	 var newOperatorPassword=$("#newOperatorPassword").val();
	 var newOperatorPasswordConf=$("#newOperatorPasswordConf").val();
	 if(operatorPassword==''||operatorPassword==null){
		 $.messager.alert('消息',"原始密码不能为空！", 'info');
		 return false;
	 }else if(newOperatorPassword==''||newOperatorPassword==null){
		 $.messager.alert('消息',"新密码不能为空！", 'info');
		 return false;
	 }else if(newOperatorPasswordConf==''||newOperatorPasswordConf==null){
		 $.messager.alert('消息',"确认密码不能为空！", 'info');
		 return false;
	 }else if(newOperatorPassword!=newOperatorPasswordConf){
		 $.messager.alert('消息',"2次密码不相同！", 'info');
		 return false;
	 }else if(newOperatorPassword.length<6||newOperatorPassword.length>18){
		 $.messager.alert('消息',"新密码长度在6和18之间！", 'info');
		 return false;
	 }else {
		$.messager.confirm('消息', '确定要修改的密码?', function(r){
			if(r){
				
				ajaxUtils.post(basepath + "login/confirmPassword.form?id="+$("#operatorId").val()
						+'&psw='+$("#operatorPassword").val()
						+'&newpsw='+$("#newOperatorPassword").val(),null,function(json) {//回调函数
					closeProgress();
					var json=eval('('+json+')');
					if (json.result=='success') {
						$.messager.show({msg : '修改成功',title : '提示'});
						closeWin();
					}else {
						$.messager.alert('错误',json.result,'error');
					}
					showProgress();
				});
				
			}
		});
	 }
}


/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
	

