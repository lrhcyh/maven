///保存单位/部门/班组信息
var opt;
var dialogType;
var tabIndex=0;
var tabType;
var insertType=0;
var selectType=0;
var editor ;
function save() {
	
	/*var f = valideIsSpace('dataForm');
	debugger*/
	if ($("#saveBtn").linkbutton("options").disabled){
		return;
	}
	if (valideIsSpace('dataForm') == false) {
		return false;
	}
	
	
		var param;
		var url = "auto/base.form";
		jnum = $("#empCard").textbox('getValue')
		var row = $('#dg').datagrid('getSelected');
		if (jnum==null || jnum==""){
			$.messager.alert('提示信息','工号不能为空！');
			return;
		}
		var temp = validBlank();
		if (temp=="undefined" || temp==null ||  temp==""){
			$.messager.alert('提示信息','至少填写一项信息');
			return;
		}
		$.ajax({
			type : 'POST',
			url : 'personnel/validJNum.form',
			data : 'code='+jnum+"&empCode="+(row==null?"":row.EmpCode),
			dataType : 'text',
			async:false ,
			success : function(msg) {
				if (msg=="0"){
					var contextHtml ="";
					if (editor!=null && editor!="undefined"){
						contextHtml = editor.html();
					}
			
					if (opt == 'insertRyxx') {
						param = savePerson();
						if (editor!=null && editor!="undefined"){
							editor.text("");
						}
					} else if(opt == 'updateRyxx') {
						param =  getModifyPersonData();
						if (editor!=null && editor!="undefined"){
							editor.text("");
						}
					}
				
					ajaxUtils.post(url, param, function(json) {// 回调函数
						if (json == 'success') {
							//$('#dataForm').html('');
							updateContextHtml(contextHtml);
							
							var rowIndex=$('#dg').datagrid('getRowIndex',$('#dg').datagrid('getSelected'));
							
							$("#dg").datagrid({
							    onLoadSuccess: function(data){
							    	$('#dg').datagrid('selectRow',rowIndex);
							    }
							});
							$('#dg').datagrid('reload');
							
							$("#personBtn").linkbutton('enable');  
							$("#addBtn").linkbutton('enable');  
							$("#editBtn").linkbutton('enable');  
							$("#removeBtn").linkbutton('enable');  
							$("#saveBtn").linkbutton('disable');  
							$("#cancelBtn").linkbutton('disable'); 
							$("#autoBtn").linkbutton('enable');  
							$("#pChangeBtn").linkbutton('enable');  
							
							formAbled();
							tabType=0;
							
						} 
					})
				}else{
					$.messager.alert('提示信息','工号重复，请重新输入！');
					$("#jnum").val("");
					return;
				}
				
			},
			error:function(msg){
				alert(msg)
			}
		});
	
	
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
		if (this.value==null || this.value==""){
			//不处理这个情况
		}else{
			formData[this.name] = this.value;
		}
	
	});
	var dept = $('#cc').textbox('getValue');
	
	var orgcode = $("#orgId").val();//公司编码
	var deptId = $("#deptId").val();//部门编码
	var groupId = $("#groupId").val();//班组编码
	
	var orgName = $("#orgName").val();//公司名称
	var deptName = $("#deptName").val();//部门名称
	var groupName = $("#groupName").val();//班组名称
	formData['empcode'] = getMaxAsId('ryxx','empcode'); // 获得新增单位编号
	formData['organcode'] = dept; 
	formData['companyCode'] = orgcode; 
	formData['deptCode'] = deptId; 
	formData['classCode'] = groupId; 
	formData['empState'] = "0"; //0未锁定 1锁定
	formData['pwd'] = "e10adc3949ba59abbe56e057f20f883e"; 
	formData['deptDescript'] = orgName+"/"+deptName+"/"+groupName; 
	
	var dataStr = JSON.stringify(formData);
	formData = "{'operation':'insert','tableName':'ryxx','column':" + dataStr + "}";
	var param = "data=" + formData;
	return param;
}

//保存修改的单位信息 获得要保存的数据
function getModifyPersonData() {
	var row = $('#dg').datagrid('getSelected');
	var oData = new Object();
	oData.tableName = 'ryxx';
	oData.operation = 'update';
	oData.column =  serializeObject($("#dataForm"));
	oData.where = new Object();
	oData.where['empcode'] = row.EmpCode;
	var param = "data=" + JSON.stringify(oData);
	return param;
}

/***
 * 验证不为空
 */
function valideIsSpace(id) {
	
	var inputs = $("#" + id + " input");
	var f = true;
	var num=0;
	$("#" + id + " input").each(function(index) {
	
		if ($(this).attr("isSpace") == 0) {
			
			if ($(this).hasClass("easyui-combobox")){
				if ($(this).combobox('getValue') == null || $(this).combobox('getValue') == ''){
					var obj = $(this).closest("td").find("span").eq(0);
					alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
					f= false;
					num=num+1;
					return false;
				}
			}else if ($(this).hasClass("easyui-textbox")){
				if ($(this).textbox('getValue') == null || $(this).textbox('getValue') == ''){
					var obj = $(this).closest("td").find("span").eq(0);
					alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
					f= false;
					num=num+1;
					return false;
				}
			}else if ($(this).hasClass("easyui-datebox")){
				if ($(this).datebox('getValue') == null || $(this).datebox('getValue') == ''){
					var obj = $(this).closest("td").find("span").eq(0);
					alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
					f= false;
					num=num+1;
					return false;
				}
			}else{
				if ($(this).val() == null || $(this).val() == ''){
					var obj = $(this).closest("td").find("span").eq(0);
					alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
					f= false;
					num=num+1;
					return false;
				}
			}
			
		}
		
	});
	if (num==0){
		$("#" + id + " select ").each(function(index) {
			
			if ($(this).attr("isSpace") == 0) {
				
				if ($(this).hasClass("easyui-combobox")){
					if ($(this).combobox('getValue') == null || $(this).combobox('getValue') == ''){
						var obj = $(this).closest("td").find("span").eq(0);
						alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
						f= false;
						return false;
					}
				}else if ($(this).hasClass("easyui-textbox")){
					if ($(this).textbox('getValue') == null || $(this).textbox('getValue') == ''){
						var obj = $(this).closest("td").find("span").eq(0);
						alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
						f= false;
						return false;
					}
				}else if ($(this).hasClass("easyui-datebox")){
					if ($(this).datebox('getValue') == null || $(this).datebox('getValue') == ''){
						var obj = $(this).closest("td").find("span").eq(0);
						alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
						f= false;
						return false;
					}
				}else{
					if ($(this).val() == null || $(this).val() == ''){
						var obj = $(this).closest("td").find("span").eq(0);
						alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
						f= false;
						return false;
					}
				}
				
			}
			
		});
	}
	
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
		data : 'tableName=tableset&where=[{modulecode:' + 2 + '}]',
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



function loadnGridPage(title,index,empcode){
	var row = $('#dg').datagrid('getSelected');
	if (row!=null){
		empcode = row.EmpCode;
	}
	
	//加在工作区的gridData
	var data;
	var wHeight = window.document.body.clientHeight;
	var height = (wHeight-140)*57/100-62;
	try{
		unUseAllButton();
	}catch(e){
		
	}

	tabIndex=index;
	if (empcode==null){
		data = 'tableName=ryxx&definition='+title+'&where=[{1:' + 2 + '}]&height='+height+'&index='+index;
	}else{
		data = 'tableName=ryxx&definition='+title+'&where=[{empCode:' + empcode + '}]&height='+height+'&index='+index;
	}
	//var columnData =  'tableName=ryxx&definition='+title;
	$.ajax({
		type : 'POST',
		url : 'auto/queryTabsGridHtml.form',
		data : data,
		dataType : 'text',
		async:false ,
		success : function(msg) {
		    //console.info(msg)
		
			$('#pn'+index).html(msg);
		    $.parser.parse($('#pn'+index));
	
		   /* $('#gridTable'+index).datagrid({
				lines:true,
				striped:true,
				singleSelect: true,
				method:'post',
				rownumbers:true,
				data:[],
			    columns:[eval(msg)],
			    onDblClickRow:function(){
			    	onDbClickRow
			    }
		   });*/
			
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
	
	$("#personBtn").linkbutton('disable');  
	$("#addBtn").linkbutton('disable');  
	$("#editBtn").linkbutton('disable');  
	$("#removeBtn").linkbutton('disable');  
	$("#saveBtn").linkbutton('enable');  
	$("#cancelBtn").linkbutton('enable');  
	$("#autoBtn").linkbutton('disable');  
	$("#pChangeBtn").linkbutton('disable');  
	var dept = $("#deptId").val();

	$.ajax({
		type : 'POST',
		url : 'auto/madeSingleInsert.form',
		data : 'tableName=ryxx&dept='+dept,
		dataType : 'text',
		async:false ,
		success : function(msg) {
			 $('#dataForm').html(msg);
			 $.parser.parse('#dataForm');
			 $(".easyui-datebox").datebox('setValue', '');
			 $(".easyui-datetimebox").datetimebox('setValue', '');
			 $('#organcode').val(dept);
			 $('#empCode').val(getMaxAsId('ryxx','empcode'));
			 $('#organcode').attr('disabled','disabled');
			 $('#empCode').attr('disabled','disabled');
			 tabType=1;
			
			 var tab = $('#organTabs').tabs('getSelected');
			 var selectIndex = $('#organTabs').tabs('getTabIndex',tab);
			 $(".tabs li").each(function(index, obj) {  
			        //获取所有可关闭的选项卡  
			        $("#organTabs").tabs('disableTab', index);  
			  });  
			  $('#organTabs').tabs('enableTab', selectIndex);
		},
		error:function(msg){
			alert(msg);
		}
	});
	opt = 'insertRyxx';
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
		$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
		return;
	}
	
	$("#personBtn").linkbutton('disable');  
	$("#addBtn").linkbutton('disable');  
	$("#editBtn").linkbutton('disable');  
	$("#removeBtn").linkbutton('disable');  
	$("#saveBtn").linkbutton('enable');  
	$("#cancelBtn").linkbutton('enable');  
	$("#autoBtn").linkbutton('disable');  
	$("#pChangeBtn").linkbutton('disable');  
	
	formDisabled();
	 $('#organcode').attr('disabled','disabled');
	 $('#empCode').attr('disabled','disabled');
	opt = 'updateRyxx';
	tabType=1
	var tab = $('#organTabs').tabs('getSelected');
	var selectIndex = $('#organTabs').tabs('getTabIndex',tab);
	$(".tabs li").each(function(index, obj) {  
        //获取所有可关闭的选项卡  
        $("#organTabs").tabs('disableTab', index);  
  });  
	$('#organTabs').tabs('enableTab', selectIndex);
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
		$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
		return;
	}
	$.messager.confirm('信息提示','你是否要删除该员工信息',function(r){
	    if (r){
	    	
	    	var row = $('#dg').datagrid('getSelected');
				var oData = new Object();
				oData.tableName = 'ryxx';
				oData.operation = 'update';
				oData.where = new Object();
				oData.where.empcode = row.EmpCode;
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
 * 员工照片管理
 */
$("#picBtn").click(function(){
	
	var row = $('#dg').datagrid('getSelected');
	if (row==null){
		$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
		return;
	}
	
	$('#openDialog').dialog({
		title:"照片",
		width:140,
		height:165,
		left:$(window).width()-160,
		top:170,
		modal: true,
		href:"./pages/base/personnel/userPicture.jsp?empCode="+row.EmpCode,
		onClose:function(){
			$('#openDialog').addClass("use");
			//$('#openDialog').dialog("close");
	    }
	});
	
	
});
/***
 * 取消掉当前操作，返回查询且不可修改的情况
 */
$("#cancelBtn").click(function(){
	if ($("#cancelBtn").linkbutton("options").disabled){
		return;
	}
	$("#personBtn").linkbutton('enable');  
	$("#addBtn").linkbutton('enable');  
	$("#editBtn").linkbutton('enable');  
	$("#removeBtn").linkbutton('enable');  
	$("#saveBtn").linkbutton('disable');  
	$("#cancelBtn").linkbutton('disable');  
	$("#autoBtn").linkbutton('enable');  
	$("#pChangeBtn").linkbutton('enable');  
	
	if (opt == 'updateRyxx'){
		var row = $('#dg').datagrid('getSelected');
		loadCenterInfo(row.EmpCode);
		tabType=0;
	
	}else if (opt == 'insertRyxx'){
		var dept = $("#deptId").val();
		$.ajax({
			type : 'POST',
			url : 'auto/madeSingleInsert.form',
			data : 'tableName=ryxx&dept='+dept,
			dataType : 'text',
			async:false ,
			success : function(msg) {
				
				 $('#dataForm').html(msg);
				 //$.parser.parse('#dataForm');
				 
				 $('#organcode').val(dept);
				 $('#empCode').val(getMaxAsId('ryxx','empcode'));
				 $('#organcode').attr('disabled','disabled');
				 $('#empCode').attr('disabled','disabled');
				 tabType=0;
			},
			error:function(msg){
				alert(msg);
			}
		});
	}
	opt = '';
	formAbled();

	 $(".tabs li").each(function(index, obj) {  
	        //获取所有可关闭的选项卡  
	        $("#organTabs").tabs('enableTab', index);  
	  });  
});

/***
 * 人员库查询
 */
$('#searchMenu').menu({
	iconCls:'icon-add',
    onClick:function(item){
    	var text=item.text;
    	$('#personBtn').menubutton({text:text});
    	var itemId = $(item).attr("id");
    	var idArray=['quickSearch','nameOrder','normal','employ','fire','in','inFire','elseReduce']	;
    	
    	$.each(idArray,function(i,item){
    		//var a=$('#searchMenu').menu('getItem', $('#'+item)[0]);
    		
    		
    		$('#searchMenu').menu('setIcon', {
    			target: $('#'+item)[0],
    			iconCls: ''
    		});
    	});
		$('#searchMenu').menu('setIcon', {
			target: item.target,
			iconCls: 'icon-ok'
		});
    	if (itemId=="quickSearch"){//快速定位
    		$.messager.prompt('输入信息框:','请输入员工',function(r){ 
	    		if(r){ 
	    			quickSearchInfo(r);
	    		} else{
	    			//$.messager.alert('提示信息','请输入员工！');
	    		}
    		}) 
    	}else if (itemId=="nameOrder" ){//姓名排序
    		var val = $("#empOrder").val();
    		if (val=="no"){
    			$("#empOrder").val("yes");
    		}else if (val=="yes"){
    			$("#empOrder").val("no");
    		}
    		reload();
    		
    	}else if (itemId=="normal"){//正式员工
    		$("#empType").val("正式员工");
    		reload();
    	}else if (itemId=="employ"){//聘用员工
    		$("#empType").val("聘用员工");
    		reload();
    	}else if (itemId=="fire"){//离职员工
    		$("#empType").val("离职员工");
    		reload();
    	}else if (itemId=="in"){//内退员工
    		$("#empType").val("内退员工");
    		reload();
    	}else if (itemId=="inFire"){//离退休员工
    		$("#empType").val("离退休员工");
    		reload();
    	}else if (itemId=="elseReduce"){//其他减员员工
    		$("#empType").val("其他减员员工");
    		reload();
    	}
    }

});
/***
 *   再次加载左边的员工列表
 */
function reload(){
	$('#dg').datagrid({
    	url : 'personnel/queryDepartmentListForMenu.form?departmentId='+$('#departId').val()+'&empType='+encodeURI(encodeURI($('#empType').val()))+'&empOrder='+$('#empOrder').val()
    });
	//$('#dg').datagrid('reload');
}

/***
 * 自动处理功能
 */
$('#autoMenu').menu({
	
    onClick:function(item){
    	var itemId = $(item).attr("id");
    	if (itemId=="ageCal"){//计算年龄
    		var row = $('#dg').datagrid('getSelected');
    		if (row==null){
    			$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
    			return;
    		}
    		autoCal("personnel/calculateBirthday.form",row.EmpCode,"age");
    	}else if (itemId=="workCal"){//计算工龄
    		var row = $('#dg').datagrid('getSelected');
    		if (row==null){
    			$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
    			return;
    		}
    		autoCal("personnel/calculateWorkAge.form",row.EmpCode,"workage");
    	}else if (itemId=="birthTips"){
    		$.messager.alert('提示信息','建设中！');
    		return;
    	}else if (itemId=="inFireTips"){
    		$.messager.alert('提示信息','建设中！');
			return;
    	}
    }
});
/***
 * 人员变动处理
 */
$('#pChangeMenu').menu({
	
    onClick:function(item){
    	var itemId = $(item).attr("id");
    	var row = $('#dg').datagrid('getSelected');
		if (row==null){
			$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
			return;
		}
    	if (itemId=="deptChange"){//部门调整
    		openDeptChangeDialog(row.EmpCode);
    	}else if (itemId=="inFireDo"){//离退休处理
    		retire(row.EmpCode);
    	}else if (itemId=="reduceDo"){//减员处理
    		reduce(row.EmpCode);
    	}
    }
});

function autoCal(url,empCode,id){
	$.ajax({
		type : 'POST',
		url : url,
		data : 'empCode='+empCode,
		dataType : 'text',
		async:false ,
		success : function(msg) {
			
			$("#"+id).textbox("setValue",msg)
		
		},
		error:function(msg){
			alert("网络异常");
		}
	});
}


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
	
	$("#dataForm .easyui-datebox").datebox({ disabled: true }); //时间控间可用
	
	$("#dataForm .easyui-numberbox").numberbox({ disabled: true }); //数字控间不可用
	/*$.parser.parse('#dataForm');*/
}

/***
 * 打开通用弹出框   openDialog("职位名称","./pages/base/personnel/postTree.jsp");
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
 * 减员处理弹出框
 */

function reduce(empCode){
	 $('#openDialog').dialog({title:"减员处理",modal: true,iconCls:"icon-edit",buttons: [{
           text: '确定',
           iconCls: 'icon-ok',
           handler: function () {
           	var type=$('#state').combobox('getValue');	
           	var time=$('#empDate').textbox('getValue');
        	var cause = $('#cause').combobox('getValue');
      			//var text=temp.text;
      			$.ajax({
      				type : 'POST',
      				url : 'personnel/reduceMan.form?type='+encodeURI(encodeURI(type))+'&time='+time+'&id='+$('#empId').val()+'&cause='+encodeURI(encodeURI(cause)),
      				dataType : 'json',
      				async : false,
      				success : function(msg) {
      					$('#dataForm').html('');
						$('#dg').datagrid('reload');
						$('#openDialog').dialog('close');
						
      				},
      				error : function() {
      					alert('error');
      				}
      			});
           }
       }, {
           text: '取消',
           iconCls: 'icon-cancel',
           handler: function () {
               $('#openDialog').dialog('close');
           }
       }],width:400,height:300,"href":"pages/base/personnel/Reduce.jsp?id="+empCode});

}


/***
 * 离退休处理弹出框
 */

function retire(empCode){
	 $('#openDialog').dialog({title:"离退休处理",modal: true,iconCls:"icon-edit",buttons: [{
           text: '确定',
           iconCls: 'icon-ok',
           handler: function () {
           	var type=$('#state').combobox('getValue');	
           	var time=$('#empDate').textbox('getValue');
        	
      			//var text=temp.text;
      			$.ajax({
      				type : 'POST',
      				url : 'personnel/updateUserType.form?type='+encodeURI(encodeURI(type))+'&time='+time+'&id='+$('#empId').val(),
      				dataType : 'json',
      				async : false,
      				success : function(msg) {
      					$('#dataForm').html('');
						$('#dg').datagrid('reload');
						$('#openDialog').dialog('close');
						
      				},
      				error : function() {
      					alert('error');
      				}
      			});
           }
       }, {
           text: '取消',
           iconCls: 'icon-cancel',
           handler: function () {
               $('#openDialog').dialog('close');
           }
       }],width:400,height:300,"href":"pages/base/personnel/Retire.jsp?id="+empCode});

}

/***
 * 打开部门调整所需要的弹框
 */
function openDeptChangeDialog(empCode){
	
	
	$('#openDialog').dialog({title:"部门调整",modal: true,iconCls:"icon-edit",buttons: [{
	    text: '确定',
	    iconCls: 'icon-ok',
	    handler: function () {
	    	var temp = $('#province').tree('getSelected');	
//	    	if($('#province').tree('isLeaf',temp.target)){
				var text=temp.id;
			
				$.ajax({
					type : 'POST',
					url : 'personnel/updateDepartMent.form',
					data : 'organCode='+text+'&empCode='+empCode,
					dataType : 'json',
					async : false,
					success : function(msg) {
						$('#dataForm').html('');
						$('#dg').datagrid('reload');
						$('#openDialog').dialog('close');
						
					},
					error : function() {
						alert('error');
					}
				});
//	    	}
	    }
	}, {
	    text: '取消',
	    iconCls: 'icon-cancel',
	    handler: function () {
	        $('#openDialog').dialog('close');
	    }
	}],width:300,height:500,"href":"pages/base/personnel/UpdateDepartmentTree.jsp?id="+empCode});
	
}

/***
 * 根据点击的节点的id判断调用哪个树形结构
 */


function searchData(e) {

	 var code = $(e.data.target).attr('id');
	 
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
	 }
	 else if (code=="postname"){//职位名称
		 dialogType = "postname";
		 openDialog("职位名称","./pages/base/personnel/postTree.jsp");
	 }else{
		 var field = $(e.data.target).closest("table").closest("td").attr("field");
		 $(e.data.target).attr("id","allId")
		 var title=$('#gridTable'+tabIndex).datagrid('getColumnOption',field).title
		 openDialog(title,"pages/base/personnel/allTree.jsp?id="+field+"&type=F");
		
	 }
}



/***
 * 快速查询
 * @param empName
 */
function quickSearchInfo(empName){
	//模糊查询
	 $('#dg').datagrid({
     	url : 'personnel/findEmpCodeByName.form?empName='+encodeURI(encodeURI(empName))
     });
	
}
//grid加载完毕后选择第一行
$('#dg').datagrid({onLoadSuccess : function(data){
    $('#dg').datagrid('selectRow',0);
  
    /*var row = $('#dg').datagrid('getSelected');
    if(row!=null){
    	
    	loadCenterInfo(row.EmpCode);
    }*/
}});
/***
 * 加载中间工作区的上面表单和下面列表部分
 */
function loadCenterInfo(empCode){
	var dept = $("#deptId").val();
	
	if (selectType==0){
		$.ajax({
			type : 'POST',
			url : 'auto/madeSingleUpdate.form',
			data : 'tableName=ryxx&where=[{\'EmpCode\':'+empCode+'}]&dept='+dept,
			dataType : 'text',
			async:false ,
			success : function(msg) {
				
				 $('#dataForm').html(msg);
				
				 //$.parser.parse('#dataForm');
			
				 $('#organTabs').tabs('select',0);
				
				var currTab =$('#organTabs').tabs('getSelected');
				loadnGridPage( currTab.panel('options').title,0,empCode);
			},
			error:function(msg){
				alert(msg)
			}
		});
		selectType=1;
	}else if (selectType==1){
		$.ajax({
			type : 'POST',
			url : 'auto/getUpdateData.form',
			data : 'tableName=ryxx&where=[{\'EmpCode\':'+empCode+'}]&dept='+dept,
			dataType : 'text',
			async:false ,
			success : function(msg) {
				//console.info(msg)
				var obj;
				if (typeof(JSON) == 'undefined'){    
					obj = eval("("+msg+")");    
				}else{    
					obj = JSON.parse(msg);    
				}  
				$('#dataForm').form('load',obj);
				 $('#organTabs').tabs('select',0);
				
				var currTab =$('#organTabs').tabs('getSelected');
				loadnGridPage( currTab.panel('options').title,0,empCode);
			},
			error:function(msg){
				alert(msg)
			}
		});
	}
}




/***
 * 查询出员工所属的班组、部门、公司
 */
function loadOrgInfo(dept){
	
	$.ajax({
		type : 'POST',
		url : 'personnel/queryWholeOrg.form',
		data : 'code='+dept,
		dataType : 'text',
		async:false ,
		success : function(msg) {
			var obj ;
			if (typeof(JSON) == 'undefined'){    
				obj = eval("("+msg+")");    
			}else{    
				obj = JSON.parse(msg);    
			}   
			//var obj = JSON.parse(msg); //由JSON字符串转换为JSON对象
			$('#orgId').val(obj.orgCode);
			$('#deptId').val(obj.deptCode);
			$('#groupId').val(obj.groupCode);
			
			$('#orgName').val(obj.orgDescrip);
			$('#deptName').val(obj.deptDescrip);
			$('#groupName').val(obj.groupDescrip);
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
			$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
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
		$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
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
		var row = $('#dg').datagrid('getSelected');
		var temp="";
		if (row==null){
			$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
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
			oData.column['empCode']= $('#dg').datagrid('getSelected').EmpCode;
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
				
				/*editIndex = undefined;
				$('#gridTable'+tabIndex).datagrid('acceptChanges');//更改页面上的数据*/
				$("#addTb").linkbutton('enable');  
				$("#saveTb").linkbutton('disable');  
				$("#delTb").linkbutton('enable');  
				$("#cancelTb").linkbutton('disable'); 
				
				 unUseAllButton();
				 var currTab =$('#organTabs').tabs('getSelected');
				
				 var index = $('#organTabs').tabs("getTabIndex",currTab);
				 loadnGridPage(currTab.panel('options').title,index,row.EmpCode);
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
		$.messager.alert('提示信息','请先从左侧“员工列表”选择相关人员');
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
	
	
	
	
	/*if (editIndex == undefined){return};
	$('#gridTable'+tabIndex).datagrid('cancelEdit', editIndex)
			.datagrid('deleteRow', editIndex);
	editIndex = undefined;*/
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
	var dept = $("#cc").combotree("getValue");
	$("#cc").combotree({ disabled: true }); //时间控间不可用
	$("#cc").combotree("setValue",dept);
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
	var dept = $("#cc").combotree("getValue");
	$("#cc").combotree({ disabled: false }); //时间控间可用
	$("#cc").combotree("setValue",dept);
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

function showContext(){
	var text="";
	try{
		text = $("#context").textbox("getValue");
	}catch(e){
		text = $("#context").val();
	}
	if (!$("#saveBtn").linkbutton("options").disabled){
		$("#contextBar").css("display","block");
	}else{
		$("#contextBar").css("display","none");
	}
	$("#contextHtml").window('open');
	window.setTimeout(function(){
		if (editor==null || editor=="undefined"){
			editor = KindEditor.create('textarea[name="content1"]');
		}
		
		if (opt == 'insertRyxx') {
		}else if(opt == 'updateRyxx') {
			showContextFromDb(text);
		}else{
			var row = $('#dg').datagrid('getSelected');
			var empCode = row.EmpCode;
			
			if (empCode==null || empCode=="undefined"){
				editor.text("");
			}else{
				showContextFromDb(text);
			}
			
		}
		
		
	},200)
}


function saveContext(){
	$("#context").textbox("setValue",editor.text());
	$("#contextHtml").window('close');
}
function cancelContext(){
	$("#contextHtml").window('close');
}

function showContextFromDb(text){
	var contextHtml = editor.html()
	var row = $('#dg').datagrid('getSelected');
	var url =  "personnel/showContextHtml.form";
	var param = "empCode=" +row.EmpCode;

	$.ajax({
		type : 'POST',
		url : url,
		async:false ,
		data : param,
		success : function(msg) {
			KindEditor.sync("content1");
		
			if (msg==null || msg==""){
				editor.html(text);
			}else{
				editor.html(msg);
			}
		}
	});
}
function updateContextHtml(contextHtml){
	
	var text="";
	try{
		text = $("#context").textbox("getValue");
	}catch(e){
		text = $("#context").val();
	}
	var row = $('#dg').datagrid('getSelected');
	
	if (text==""){
		contextHtml = "";
	}
	var url =  "personnel/updateContexHtml.form";
	var param = "empCode=" +row.EmpCode+"&contextHtml="+contextHtml;


	$.ajax({
		type : 'POST',
		url : url,
		async:false ,
		data : param,
		success : function(msg) {
			
		}
	});

}







