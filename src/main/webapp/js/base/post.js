///保存单位/部门/班组信息
var opt;
var dialogType;
var tabIndex=0;
var tabType;
var insertType=0;
function save() {
	if ($("#saveBtn").linkbutton("options").disabled){
		return;
	}
	if(valideIsSpace('dataForm')==false)
		{return;}
	
	var temp = validBlank();
	
	if (temp=="undefined" || temp==null ||  temp==""){
		$.messager.alert('提示信息','至少填写一项信息');
		return;
	}
	var postName = $("#postname").textbox("getValue");
	if (postName=="undefined" || postName==null ||  postName==""){
		$.messager.alert('提示信息','职位名称不能为空');
		return;
	}
	var param;
	var url = "auto/base.form";
	if (opt == 'insertPost') {
		param = savePerson();
	} else if(opt == 'updatePost') {
		param =  getModifyPersonData();
	}
	
	
	
	
	ajaxUtils.post(url, param, function(json) {// 回调函数
		if (json == 'success') {
			$('#dataForm').html('');
			$('#dg').datagrid('reload');
			$("#addBtn").linkbutton('enable');  
			$("#editBtn").linkbutton('enable');  
			$("#removeBtn").linkbutton('enable');  
			$("#saveBtn").linkbutton('disable');  
			$("#cancelBtn").linkbutton('disable');  
			formAbled();
			tabType=0;
		} 
	})
}

/***
 * 验证表单不能全部为空
 */
function validBlank(){
	var t = $("#dataForm").serializeArray();
	var temp;
	$.each(t, function() {
		temp = temp+this.value;
	});
	return temp;
}
// 保存新增的单位信息 获得要保存的数据
function savePerson() {
	var formData = {};
	var t = $("#dataForm").serializeArray();
	$.each(t, function() {
		formData[this.name] = this.value;
	});
	
	var dept = $('#cc').textbox('getValue');
	formData['postcode'] = getMaxAsId('post','postcode'); // 获得新增单位编号
	formData['organcode'] = dept; // 新增人员所属的部门
	
	var dataStr = JSON.stringify(formData);
	formData = "{'operation':'insert','tableName':'post','column':" + dataStr + "}";
	var param = "data=" + formData;
	return param;
}

//保存修改的单位信息 获得要保存的数据
function getModifyPersonData() {
	var row = $('#dg').datagrid('getSelected');
	var oData = new Object();
	oData.tableName = 'post';
	oData.operation = 'update';
	oData.column =  serializeObject($("#dataForm"));
	oData.where = new Object();
	oData.where['postcode'] = row.postcode;
	var param = "data=" + JSON.stringify(oData);
	return param;
}

/***
 * 验证不为空
 */
function valideIsSpace(id) {
	var inputs = $("#" + id + " input");
	var f = true;
	$("#" + id + " input").each(function(index) {
		if ($(this).attr("isSpace") == 0 && ($(this).val() == null || $(this).val() == '') && ($(this).textbox('getValue')==null || $(this).textbox('getValue')=="")) {
			var obj = $(this).closest("td").find("span").eq(0);
			alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
			f= false;
			return false;
		}
		
	});
	
	return f;
}



//获得最大id
function getMaxAsId(tableName,key) {
	var id;
	$.ajax({
		type : 'POST',
		url : 'auto/getMaxAsId.form',
		data : 'tableName='+tableName+'&column='+key,
		dataType : 'json',
		async : false,
		success : function(msg) {
			id = msg;
		},
		error : function() {
			alert('error')
		}
	});
	return id;
}

function queryMenuList() {
	$.ajax({
		type : 'POST',
		url : 'auto/querySingleForList.form',
		async:false ,
		data : 'tableName=tableset&where=[{modulecode:' + 6 + '}]',
		success : function(msg) {
		
			window.MenuListData = msg;
			getTablename();
		}
	});
}





//获得表名
function getTablename(title){
	var result="";
	var menu = eval(window.MenuListData);
	
	 for (var i = 0; i <	menu.length; i++) {
		
		 if(menu[i].Definition==title){
			 result=menu[i].tablename;
		 }		
     }	
	 return result;
}



function loadnGridPage(title,index,postcode){
	var row = $('#dg').datagrid('getSelected');
	if (row!=null){
		postcode = row.postcode;
	}
	//加在工作区的gridData
	var data;
	//计算tabs的高度
	var wHeight = window.document.body.clientHeight;
	var height = wHeight-361-40;
	
	try{
		unUseAllButton();
	}catch(e){
		
	}

	tabIndex=index;
	
	if (postcode==null){
		data = 'tableName=post&definition='+title+'&where=[{1:' + 2 + '}]&height='+height+'&index='+index;
	}else{
		data = 'tableName=post&definition='+title+'&where=[{postcode:' + postcode + '}]&height='+height+'&index='+index;
	}
	$.ajax({
		type : 'POST',
		url : 'auto/queryTabsGridHtml.form',
		data : data,
		dataType : 'text',
		async:false ,
		success : function(msg) {
		      
			 $('#pn'+index).html(msg);
		    $.parser.parse($('#pn'+index));
			
		},
		error:function(msg){
			alert(msg);
		}
	});
}

/***
 * 点击新增，输入数据后，点击保存，将数据保存起来
 */
$("#addBtn").off().click(function(){
	if ($("#addBtn").linkbutton("options").disabled){
		return;
	}
	var dept = $('#cc').textbox('getValue');
	if (dept==null || dept==""){
		$.messager.alert('提示信息','请先选择部门！');
		return;
	}
	

	$("#addBtn").linkbutton('disable');  
	$("#editBtn").linkbutton('disable');  
	$("#removeBtn").linkbutton('disable');  
	$("#saveBtn").linkbutton('enable');  
	$("#cancelBtn").linkbutton('enable');  
	$.ajax({
		type : 'POST',
		url : 'auto/madeSingleInsert.form',
		data : 'tableName=post',
		dataType : 'text',
		async:false ,
		success : function(msg) {
			
			 $('#dataForm').html(msg);
			 $.parser.parse('#dataForm');
			 
			 $('#organcode').val(dept);
			 $('#postcode').val(getMaxAsId('post','postcode'));
			 $('#organcode').attr('disabled','disabled');
			 $('#postcode').attr('disabled','disabled');
			 tabType=1;
		},
		error:function(msg){
			alert(msg);
		}
	});
	opt = 'insertPost';
});
/***
 * 点击修改，解除所有的不可输入限制，修改数据后，点击保存，将数据保存起来
 */
$("#editBtn").click(function(){
	if ($("#editBtn").linkbutton("options").disabled){
		return;
	}
	var row = $('#dg').datagrid('getSelected');
	if (row==null){
		$.messager.alert('提示信息','请先选择职务名称！');
		return;
	}
	
	$("#addBtn").linkbutton('disable');  
	$("#editBtn").linkbutton('disable');  
	$("#removeBtn").linkbutton('disable');  
	$("#saveBtn").linkbutton('enable');  
	$("#cancelBtn").linkbutton('enable');  
	
	formDisabled();
	$('#organcode').attr('disabled','disabled');
	$('#postcode').attr('disabled','disabled');
	opt = 'updatePost';
	tabType=1
});
/***
 * 点击删除，将数据直接删除掉
 */
$("#removeBtn").click(function(){
	if ($("#removeBtn").linkbutton("options").disabled){
		return;
	}
	var row = $('#dg').datagrid('getSelected');
	if (row==null){
		$.messager.alert('提示信息','请先选择左边职位！');
		return;
	}
	$.messager.confirm('信息提示','你是否要删除该职位信息',function(r){
	    if (r){
	    	
	    		var row = $('#dg').datagrid('getSelected');
				var oData = new Object();
				oData.tableName = 'post';
				oData.operation = 'update';
				oData.where = new Object();
				oData.where.postcode = row.postcode;
				oData.column = new Object();
				oData.column.isDelete = 1;
				var param = "data=" + JSON.stringify(oData);
				var url = "auto/base.form";
				ajaxUtils.post(url, param, function(json) {// 回调函数
					if (json == 'success') {
						$('#dataForm').html('');
		    			$('#dg').datagrid('reload');
					}
				})
	    }
	});
});
/***
 * 点击保存，将新增页面或者修改页面的数据保存起来
 */
$("#saveBtn").click(function(){
	save();
	
});
/***
 * 取消掉当前操作，返回查询且不可修改的情况
 */
$("#cancelBtn").click(function(){
	if ($("#cancelBtn").linkbutton("options").disabled){
		return;
	}
	$("#addBtn").linkbutton('enable');  
	$("#editBtn").linkbutton('enable');  
	$("#removeBtn").linkbutton('enable');  
	$("#saveBtn").linkbutton('disable');  
	$("#cancelBtn").linkbutton('disable');  
	if (opt == 'updatePost'){
		var row = $('#dg').datagrid('getSelected');
		loadCenterInfo(row.postcode);
		tabType=0;
	
	}else if (opt == 'insertPost'){
		var dept = $('#cc').textbox('getValue');
		$.ajax({
			type : 'POST',
			url : 'auto/madeSingleInsert.form',
			data : 'tableName=post',
			dataType : 'text',
			async:false ,
			success : function(msg) {
				 $('#dataForm').html(msg);
				 $.parser.parse('#dataForm');
				 
				 $('#organcode').val(dept);
				 $('#postcode').val(getMaxAsId('post','postcode'));
				 $('#organcode').attr('disabled','disabled');
				 $('#postcode').attr('disabled','disabled');
				 tabType=0;
			},
			error:function(msg){
				alert(msg);
			}
		});
	}
	opt = '';
	formAbled();
});


/***
 * 表单可编辑
 */
function formDisabled(){
	
	$('#dataForm table input').removeAttr('disabled');
	$('#dataForm table select').removeAttr('disabled');
	$('.easyui-textbox').textbox({
		disabled : false
	});
	$("#dataForm .easyui-combobox").combobox({ disabled: false });  //对form里面的下拉框可用
	
	$("#dataForm .easyui-datebox").datebox({ disabled: false }); //时间控间可用
	
	$("#dataForm .easyui-numberbox").numberbox({ disabled: false }); //数字控间可用

	$.parser.parse('#dataForm');
}
/***
 * 表单不可编辑
 */
function formAbled(){

	$('#dataForm table input').attr('disabled','disabled');
	$('#dataForm table select').attr('disabled','disabled');
	$('.easyui-textbox').textbox({
		disabled : true
	});
	$("#dataForm .easyui-combobox").combobox({ disabled: true });  //对form里面的下拉框可用
	
	$("#dataForm .easyui-datebox").datebox({ disabled: true }); //时间控间不可用
	
	$("#dataForm .easyui-numberbox").numberbox({ disabled: true }); //数字控间不可用
	$.parser.parse('#dataForm');
}
/***
 * 打开通用弹出框
 */
function openDialog(title,url){
	$('#openDialog').dialog({
		title:title,
		width:400,
		height:600,
		modal: true,
		"href":url,
		onClose:function(){
			if ($("#allId")!=null){
				$("#allId").removeAttr("id");
			}
	    }
	});
	
}

/***
 * 根据点击的节点的id判断调用哪个树形结构
 */
function searchData(e) {
	
	var obj = $(this).closest("td").find("input").eq(0);
	 var code = obj.attr("id")
	 
	 if (code=="F101"){//籍贯
		 dialogType = "F101";
		 openDialog("籍贯","./pages/base/personnel/provinceTree.jsp");
	 }else if (code=="F174"){//出生地
		 dialogType = "F174";
		 openDialog("出生地","./pages/base/personnel/provinceTree.jsp");
	 }else if (code=="F176"){//健康情况
		 dialogType = "F176";
		 openDialog("健康情况","./pages/base/personnel/IllnessTree.jsp");
	 }else if (code=="F354"){//人员身份
		 dialogType = "F354";
		 openDialog("人员身份","./pages/base/personnel/IdentityTree.jsp");
	 }else if (code=="F205"){//现从事专业
		 dialogType = "F205";
		 openDialog("从事专业","./pages/base/personnel/SpecialtyTree.jsp");
	 }else if (code=="F207"){//现工作性质
		 dialogType = "F207";
		 openDialog("工作性质","./pages/base/personnel/PropertyTree.jsp");
	 }else if (code=="F218"){//增员类别
		 dialogType = "F218";
		 openDialog("增员类别","./pages/base/personnel/AddMemberTree.jsp");
	 }else if (code=="F220"){//减员类别
		 dialogType = "F220";
		 openDialog("减员类别","./pages/base/personnel/CutMemberTree.jsp");
	 }else{
		 var field = $(e.data.target).closest("table").closest("td").attr("field");
		 $(e.data.target).attr("id","allId")
		  var title=$('#gridTable'+tabIndex).datagrid('getColumnOption',field).title
		 openDialog(title,"pages/base/personnel/allTree.jsp?id="+field+"&type=F");
		
	 }
}


//grid加载完毕后选择第一行
$('#dg').datagrid({onLoadSuccess : function(data){
    $('#dg').datagrid('selectRow',0);
    var row = $('#dg').datagrid('getSelected');
    if(row!=null){
    	loadCenterInfo(row.postcode);
    }
}});
/***
 * 加载中间工作区的上面表单和下面列表部分
 */
function loadCenterInfo(postcode){
	$.ajax({
		type : 'POST',
		url : 'auto/madeSingleUpdate.form',
		data : 'tableName=post&where=[{\'postcode\':'+postcode+'}]',
		dataType : 'text',
		async:false ,
		success : function(msg) {
			
			 $('#dataForm').html(msg);
			 $.parser.parse('#dataForm');
			 
			 $('#organTabs').tabs('select',0);
			
			var currTab =$('#organTabs').tabs('getSelected');
			loadnGridPage( currTab.panel('options').title,0,postcode);
		},
		error:function(msg){
			alert(msg)
		}
	});
}





/***
 * 工作区下方的列表的新增、修改、保存、删除
 */
var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#gridTable'+tabIndex).datagrid('validateRow', editIndex)){
		var ed = $('#gridTable'+tabIndex).datagrid('getEditor', {index:editIndex,field:'F259'});
		
		var productname = $(ed.target).combobox('getText');
		
		$('#gridTable'+tabIndex).datagrid('getRows')[editIndex]['productname'] = productname;
		$('#gridTable'+tabIndex).datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onDbClickRow(index){

	if (insertType==1){
		return;
	}
	$('#gridTable'+tabIndex).datagrid('rejectChanges');//回滚到未修改保存之前的状态
	$('#gridTable'+tabIndex).datagrid('selectRow', index)
	.datagrid('beginEdit', index);
	editIndex = index;
	opt = 'updateDgData';
	$("#addTb").linkbutton('disable');  
	$("#saveTb").linkbutton('enable');  
	$("#delTb").linkbutton('disable');  
	$("#cancelTb").linkbutton('enable');  
	useAllButton();
	insertType=1;
}
/***
 * 新增
 */
function append(){
		var row = $('#dg').datagrid('getSelected');
		if (row==null){
			$.messager.alert('提示信息','请先选择左边职位！');
			return;
		}
		$('#gridTable'+tabIndex).datagrid('appendRow',{status:'P'});

		editIndex = $('#gridTable'+tabIndex).datagrid('getRows').length-1;
		$('#gridTable'+tabIndex).datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
		opt = 'addDgData';
		$("#addTb").linkbutton('disable');  
		$("#saveTb").linkbutton('enable');  
		$("#delTb").linkbutton('disable');  
		$("#cancelTb").linkbutton('enable');  
		
		useAllButton();
		
		insertType=1;
}


/***
 * 取消操作
 */
function cancel(){
	var row = $('#dg').datagrid('getSelected');
	if (row==null){
		$.messager.alert('提示信息','请先选择左边职位！');
		return;
	}
	$('#gridTable'+tabIndex).datagrid('rejectChanges');//回滚到未修改保存之前的状态
	$("#addTb").linkbutton('enable');  
	$("#saveTb").linkbutton('disable');  
	$("#delTb").linkbutton('enable');  
	$("#cancelTb").linkbutton('disable');  
	unUseAllButton();
	insertType=0;
}
/***
 * 保存
 */
function accept(){
		var temp="";
		var row = $('#dg').datagrid('getSelected');
		if (row==null){
			$.messager.alert('提示信息','请先选择左边职位！');
			return;
		}
		$("#gridTable"+tabIndex).datagrid('endEdit', editIndex);  //结束行编辑模式
		var rows = $("#gridTable"+tabIndex).datagrid('getChanges');  //获得修改后的数据
		var oData = new Object()
		var tableName = getTablename($('.tabs-selected').text());
	
		oData.tableName=tableName;
		var opts = $("#gridTable"+tabIndex).datagrid('getColumnFields'); //这是获取到所有的FIELD

		if(opt == 'addDgData') {	//新增
			oData.operation='insert';
			
			oData.column=new Object();
			oData.column['postCode']= $('#dg').datagrid('getSelected').postcode;
			oData.column[tableName+'ID']=getMaxAsId(tableName,tableName+'ID');
			for(i=0;i<opts.length;i++) {
				var col = $("#gridTable"+tabIndex).datagrid( "getColumnOption" , opts[i] );	
				
				if	(!col.hidden){
					if (rows[0][col.field]==null || rows[0][col.field]==""){
						
					}else{
						oData.column[col.field]=rows[0][col.field];
						temp=temp+rows[0][col.field];
					}
				}
				
			}
		} else if(opt == 'updateDgData') {	//修改
			
			oData.operation = 'update';
			oData.column=new Object();
			oData.where =new Object();
			for(i=0;i<opts.length;i++) {
				var col = $("#gridTable"+tabIndex).datagrid( "getColumnOption" , opts[i] );			
				
				if	(col.hidden){
				
					oData.where[col.field]=rows[0][col.field];
				}else{
					if (rows[0][col.field]==null || rows[0][col.field]==""){
						
					}else{
						oData.column[col.field]=rows[0][col.field];
						temp=temp+rows[0][col.field];
					}
				}
			}
		}
		if (temp=="undefined" || temp==null ||  temp==""){
			$.messager.alert('提示信息','至少填写一项信息');
			$('#gridTable'+tabIndex).datagrid('selectRow', editIndex)
			.datagrid('beginEdit', editIndex);
			return;
		}
		var url =  "auto/base.form";
		var param = "data=" +JSON.stringify( oData);
	
		ajaxUtils.post(url, param, function(json) {
			// 回调函数
			if (json == 'success') {	
				
				editIndex = undefined;
				$('#gridTable'+tabIndex).datagrid('acceptChanges');//更改页面上的数据
				$("#addTb").linkbutton('enable');  
				$("#saveTb").linkbutton('disable');  
				$("#delTb").linkbutton('enable');  
				$("#cancelTb").linkbutton('disable'); 
				
				unUseAllButton();
				 var currTab =$('#organTabs').tabs('getSelected');
				
				 var index = $('#organTabs').tabs("getTabIndex",currTab);
				 loadnGridPage(currTab.panel('options').title,index,row.postcode);
				
			}
		})
		insertType=0;

}
/***
 * 删除
 */
function removeit(){
	
	var row = $('#dg').datagrid('getSelected');
	if (row==null){
		$.messager.alert('提示信息','请先选择左边职位！');
		return;
	}
	var row = $('#gridTable'+tabIndex).datagrid('getSelected');
	if(row==null || row=='') {
		$.messager.alert('信息提示','请选择需要删除的数据');
		return false;
	} else {
		var node = $('#dg').tree('getSelected');
		
		$.messager.confirm('信息提示', '你是否要删除数据', function(r) {
			if (r) {
				var oData = new Object();
				oData.tableName = getTablename($('.tabs-selected').text());
				oData.operation = 'delete';
				var opts = $("#gridTable"+tabIndex).datagrid('getColumnFields'); //这是获取到所有的FIELD
				oData.where = new Object();
				for(i=0;i<opts.length;i++) {
					var col = $("#gridTable"+tabIndex).datagrid( "getColumnOption" , opts[i] );					
					if	(col.hidden){
						oData.where[col.field]=row[col.field];
					}
				}
				var param = "data=" + JSON.stringify(oData);
				var url = "auto/base.form";
				ajaxUtils.post(url, param, function(json) {// 回调函数
					if (json == 'success') {
						editIndex = undefined;
						$('#gridTable'+tabIndex).datagrid('deleteRow',$('#gridTable'+tabIndex).datagrid('getRowIndex',row));//更改页面上的数据
						$("#addTb").linkbutton('enable');  
						$("#saveTb").linkbutton('disable');  
						$("#delTb").linkbutton('enable');  
						$("#cancelTb").linkbutton('disable');  
						
					}
				})
			}
		});
	}
	
	
	
	
	if (editIndex == undefined){return};
	$('#gridTable'+tabIndex).datagrid('cancelEdit', editIndex)
			.datagrid('deleteRow', editIndex);
	editIndex = undefined;
}



function useAllButton(){
	$("#personBtn").linkbutton('disable');  
	$("#addBtn").linkbutton('disable');  
	$("#editBtn").linkbutton('disable');  
	$("#removeBtn").linkbutton('disable');  
	$("#saveBtn").linkbutton('disable');  
	$("#cancelBtn").linkbutton('disable'); 
	$("#autoBtn").linkbutton('disable');  
	$("#pChangeBtn").linkbutton('disable');  
	var post = $("#cc").combotree("getValue");
	$("#cc").combotree({ disabled: true }); //时间控间不可用
	$("#cc").combotree("setValue",post);
	tabType=1
	var tab = $('#organTabs').tabs('getSelected');
	var selectIndex = $('#organTabs').tabs('getTabIndex',tab);
	$(".tabs li").each(function(index, obj) {  
        //获取所有可关闭的选项卡  
        $("#organTabs").tabs('disableTab', index);  
	});  
	$('#organTabs').tabs('enableTab', selectIndex);
}
function unUseAllButton(){
	$("#personBtn").linkbutton('enable');  
	$("#addBtn").linkbutton('enable');  
	$("#editBtn").linkbutton('enable');  
	$("#removeBtn").linkbutton('enable');  
	$("#saveBtn").linkbutton('disable');  
	$("#cancelBtn").linkbutton('disable'); 
	$("#autoBtn").linkbutton('enable');  
	$("#pChangeBtn").linkbutton('enable');  
	var post = $("#cc").combotree("getValue");
	$("#cc").combotree({ disabled: false }); //时间控间可用
	$("#cc").combotree("setValue",post);
	tabType=0
	$(".tabs li").each(function(index, obj) {  
        //获取所有可关闭的选项卡  
        $("#organTabs").tabs('enableTab', index);  
  });  
}
function  leftCanClick(){
	if (tabType==1){
		return false;
	}else{
		return true;
	}
}
