function addSelfDemo(){
	showWindow(basepath + '/demo/goToPage.form?goToPage=insert&id=0',380,200,'增加信息');
}

function saveSelfDemo(flag){
	var formJson=getFormToJson("demoForm");//moduleset是表单form的id值
	var url;
	if(flag=='insert'){
		url=basepath + "/demo/insert.form";
	}else if (flag=='update'){
		url=basepath + "/demo/update.form";
	}
	var dataStr='{'+formJson+'}';
	var param = 'data='+ dataStr;
	ajaxUtils.post(url, param,function(json) {//回调函数
		var json=eval('('+json+')');
		if (json.result=='success') {
			$.messager.alert('消息','操作成功','info');
			closeWin();
			tabsWin();
		}else {
			$.messager.alert('错误','操作失败','error');
		}
	});	
}

function deleteSelfDemo(checkboxName){
	var flag=ifSelected(checkboxName);
	if(flag==false){
		alert("请选择一个进行删除");
		return false;
	}	
	var id=getCheckBoxID(checkboxName);	
	$.messager.confirm("提示","您确定要删除选中的demo吗？",function(r){
		if(r){
			ajaxUtils.post(basepath + "/demo/delete.form?id="+id,null,function(json) {//回调函数
				var json=eval('('+json+')');
				if (json.result=='success') {
					$.messager.alert('消息','操作成功','info');
					tabsWin();
				}else {
					$.messager.alert('错误','操作失败','error');
				}
			});	
		}
	});
}


function updateSelfDemo(checkboxName){
	
	var flag=ifSelected(checkboxName);
	if(flag==false){
		alert("请选择一个进行更新");
		return false;
	}
	var id=getCheckBoxID(checkboxName);	
	showWindow(basepath + '/demo/goToPage.form?goToPage=update&id='+id,380,200,'增加信息');
}

function updateSelfUser(id){
	showWindow(basepath + '/demo/goToPage.form?goToPage=update&id='+id,500,400,'冻结信息');
}


