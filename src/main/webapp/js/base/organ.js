//保存单位/部门/班组信息
var opt;
function save() {

	if (valideIsSpace('dataForm') == false) {
		return false;
	}
	var param;
	var url = "auto/base.form";
	if (opt == 'insertOrgan') {
		param = saveOrgan();
	} else if (opt == 'updateOrgan') {
		param = getModifyOrganData();
	} else if(opt=='insertChildOrgan') {
		param = getChildOrganInfo();
	}
	ajaxUtils.post(url, param, function(json) {// 回调函数
		if (json == 'success') {
			$('#dataForm').html('');
			$('#tg').tree('reload');
			undo();
			reject();
		}
	})
}

function getChildOrganInfo() {
	var formData = {};
	var t = $("#dataForm").serializeArray();
	$.each(t, function() {
		formData[this.name] = this.value;
	});
	var row = $('#tg').tree('getSelected');
	formData['organcode'] = getMaxAsId('organ', 'organcode'); // 获得新增单位编号
	formData['parent'] = $('#tg').tree('getSelected').id; // 新增单位的父级节点为0
	formData['bmbj'] = 0;
	var dataStr = JSON.stringify(formData);
	formData = "{'operation':'insert','tableName':'organ','column':" + dataStr + "}";
	var param = "data=" + formData;
	return param;
}
// 保存新增的单位信息 获得要保存的数据
function saveOrgan() {
	var formData = {};
	var t = $("#dataForm").serializeArray();
	$.each(t, function() {
		if (this.value==null || this.value==""){
			//不处理这个情况
		}else{
			formData[this.name] = this.value;
		}
	});
	var row = $('#tg').tree('getSelected');
	formData['organcode'] = getMaxAsId('organ', 'organcode'); // 获得新增单位编号
	formData['parent'] = 0; // 新增单位的父级节点为0
	formData['bmbj'] = 0;
	var dataStr = JSON.stringify(formData);
	formData = "{'operation':'insert','tableName':'organ','column':" + dataStr + "}";
	var param = "data=" + formData;
	return param;
}

// 保存修改的单位信息 获得要保存的数据
function getModifyOrganData() {
	var row = $('#tg').tree('getSelected');
	var oData = new Object();
	oData.tableName = 'organ';
	oData.operation = 'update';
	oData.column = serializeObject($("#dataForm"));
	oData.column.bmbj = 0;
	oData.where = new Object();
	oData.where['organCode'] = row.id;
	var param = "data=" + JSON.stringify(oData);
	return param;
}

// 获得最大id
function getMaxAsId(tName, col) {
	var id;
	$.ajax({
		type : 'POST',
		url : 'auto/getMaxAsId.form',
		data : 'tableName=' + tName + '&column=' + col,
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

// 添加单位
function addCompany() {
	var id = 10;
	var node = $('#tg').tree('getSelected');
	if (node != null) {
		var level = $('#tg').tree('getLevel', node.target);
		if (level != 1) {
			$.messager.alert('信息提示', '不能在部门或班组新增下级单位');
			return false;
		}
	}
	menuBtnDo('left');

	$.ajax({
		type : 'POST',
		url : 'auto/madeSingleInsert.form',
		data : 'tableName=organ&where=[{organCode:' + id + '}]',
		dataType : 'html',
		async : true,
		success : function(msg) {
			$('#dataForm').html(msg);
			$.parser.parse('#dataForm');
			setClass('dataForm');
		},
		error : function() {
			alert('error')
		}
	});
	opt = 'insertOrgan';
	flag = false;
	dgBtnDo('all');
	var tab = $('#organTabs').tabs('getSelected');
	var index = $('#organTabs').tabs('getTabIndex', tab);
	disableTab('organTabs', index);

}

//添加子单位
function addChildCompany() {
	var id = 10;
	var node = $('#tg').tree('getSelected');
	if(node.attributes.bmbj!=0) {
		$.messager.alert('信息提示', '只能在单位下添加子单位');
		return false;
	}
	menuBtnDo('left');

	$.ajax({
		type : 'POST',
		url : 'auto/madeSingleInsert.form',
		data : 'tableName=organ&where=[{organCode:' + id + '}]',
		dataType : 'html',
		async : true,
		success : function(msg) {
			$('#dataForm').html(msg);
			$.parser.parse('#dataForm');
			setClass('dataForm');
		},
		error : function() {
			alert('error')
		}
	});
	opt = 'insertChildOrgan';
	flag = false;
	dgBtnDo('all');
	var tab = $('#organTabs').tabs('getSelected');
	var index = $('#organTabs').tabs('getTabIndex', tab);
	disableTab('organTabs', index);

}

// 添加部门 弹出添加部门的弹窗
function addDept() {
	var node = $('#tg').tree('getSelected');
	if ( node.attributes.bmbj == 2) {
		$.messager.alert('信息提示', '不能在班组下添加班组');
		return false;
	}
	opt = 'add';
	$('#deptDlg').dialog('open');
	$('.easyui-textbox').textbox({
		disabled : false
	});
}

// 添加班组 弹出添加班组的弹窗
function addGroup() {
	var node = $('#tg').tree('getSelected');
	if (node.attributes.bmbj == 2) {
		$.messager.alert('信息提示', '不能在班组下添加班组');
		return false;
	}
	opt = 'add';
	$('#groupDlg').dialog('open');
	$('.easyui-textbox').textbox({
		disabled : false
	});
}

// 保存部门信息
function saveDept() {
	var dValue = $('#deptName').textbox('getValue');
	if(dValue == null || dValue == "") {
		alert('部门名称不能为空');
		return false;
	}
	var row = $('#tg').tree('getSelected');
	var parent = row.id;
	/*if(validSameName(dValue,parent)) {
		alert('该部门已存在');
		return false;
	}*/
	
	var oData = new Object();
	oData.tableName = 'organ';
	oData.column = new Object();
	oData.column.organname = $('#deptName').textbox('getValue');
	oData.column.bmbj = 1 ;
	if (opt == 'add') {
		oData.operation = 'insert';
		oData.column.organcode = getMaxAsId('organ', 'organcode');
		oData.column.parent = row.id;
	} else if (opt == 'updateDept') {
		oData.operation = 'update';
		oData.where = new Object();
		oData.where.organcode = row.id;
	}

	var param = "data=" + JSON.stringify(oData);
	var url = "auto/base.form";
	ajaxUtils.post(url, param, function(json) {// 回调函数
		if (json == 'success') {
			$('#dataForm').html('');
			$('#tg').tree('reload');
			closeDeptDlg();
		}
	})
}

// 保存班组信息
function saveGroup() {
	var gValue = $('#groupName').textbox('getValue');
	if(gValue == null || gValue == "") {
		alert('班组名称不能为空');
		return false;
	}
	var row = $('#tg').tree('getSelected');
	var parent = row.id;
	/*if(validSameName(gValue,parent)) {
		alert('该班组已存在');
		return false;
	}*/
	var oData = new Object();
	oData.tableName = 'organ';
	oData.column = new Object();
	oData.column.organname = $('#groupName').textbox('getValue');
	oData.column.bmbj = 2 ;  //标识  2班组
	if (opt == 'add') {
		oData.operation = 'insert';
		oData.column.organcode = getMaxAsId('organ', 'organcode');
		oData.column.parent = row.id;
	} else if (opt == 'updateGroup') {
		oData.operation = 'update';
		oData.where = new Object();
		oData.where.organcode = row.id;
	}

	var param = "data=" + JSON.stringify(oData);
	var url = "auto/base.form";
	ajaxUtils.post(url, param, function(json) {// 回调函数
		if (json == 'success') {
			$('#dataForm').html('');
			$('#tg').tree('reload');
			closeGroupDlg();
		}
	})
}

// 删除单位/部门/班组
function removeOrgan() {
	var node = $('#tg').tree('getSelected');
	if(!validDelete(node)) {
		$.messager.alert('信息提示','请确定要删除的单位（部门）下没有相关的部门及人员信息');
		
		return false;
	} else {
		$.messager.confirm('信息提示', '你是否要删除该单位（部门）', function(r) {
			if (r) {
				var row = $('#tg').tree('getSelected');
				var oData = new Object();
				oData.tableName = 'organ';
				oData.operation = 'update';
				oData.where = new Object();
				oData.where.organcode = row.id;
				oData.column = new Object();
				oData.column.isDelete = 1;
				var param = "data=" + JSON.stringify(oData);
				var url = "auto/base.form";
				ajaxUtils.post(url, param, function(json) {// 回调函数
					if (json == 'success') {
						//$('#dataForm').html('');
						//$('#tg').tree('reload');
						
						var node = $('#tg').tree('getSelected');
						if (node){
							$('#tt').tree('remove', node.target);
						}
					}
				})
			}
		});
	}
}

//

// 菜单栏按钮操作控制
function menuBtnDo(ret) {
	if (ret == 'left') {
		// 禁用菜单栏左边的按钮
		$('#saveBtn').linkbutton({
			disabled : false
		});
		$('#cancelBtn').linkbutton({
			disabled : false
		});
		$('#addBtn').linkbutton({
			disabled : true
		});
		$('#editBtn').linkbutton({
			disabled : true
		});
		$('#removeBtn').linkbutton({
			disabled : true
		});
	} else if (ret == 'right') {
		// 菜单栏右边的按钮禁用
		$('#saveBtn').linkbutton({
			disabled : true
		});
		$('#cancelBtn').linkbutton({
			disabled : true
		});
		$('#addBtn').linkbutton({
			disabled : false
		});
		$('#editBtn').linkbutton({
			disabled : false
		});
		$('#removeBtn').linkbutton({
			disabled : false
		});
	} else if (ret == 'all') {
		// 禁用所有
		$('#saveBtn').linkbutton({
			disabled : true
		});
		$('#cancelBtn').linkbutton({
			disabled : true
		});
		$('#addBtn').linkbutton({
			disabled : true
		});
		$('#editBtn').linkbutton({
			disabled : true
		});
		$('#removeBtn').linkbutton({
			disabled : true
		});
	}
}

// 取消操作部门信息的新增和编辑操作
function undo() {
	menuBtnDo('right');
	flag = true;
	dgBtnDo('right');
	enableTab('organTabs');
	var node = $('#tg').tree('getSelected');
	displayOrganInfo(node);
}

var searchText;
function searchData(e) {
	// 传入fieldrelation
	var textboxname = $(e.data.target).attr('textboxname');
	$('#dlg').attr('textboxnameid', textboxname);
	searchText = e.data.target;
	var codeid = $(e.data.target).attr('fieldRelation');
	$('#tgrid').treegrid({
		onBeforeExpand : function(node) {
			$("#tgrid").treegrid("options").url = 'auto/queryTreeData.form?parent=' + node.CodeID;
			return true;
		},
		onClickRow : function(row) {
			$('#txtselectvalue').val(row.CodeName);
		}
	});
	$('#tgrid').treegrid({
		url : 'auto/queryTreeData.form?basecode=' + codeid + '&parent=' + codeid
	});
	$('#tgrid').treegrid({
		animate : false
	});
	$('#tgrid').treegrid('collapseAll');
	$('#txtselectvalue').val('');
	$('#dlg').dialog('open');
}


function searchData2(e,fieldrelation) {
	var textboxname = $(e.data.target).attr('textboxname');
	$('#dlg').attr('textboxnameid', textboxname);
	searchText = e.data.target;
	var codeid = fieldrelation;
	$('#tgrid').treegrid({
		onBeforeExpand : function(node) {
			$("#tgrid").treegrid("options").url = 'auto/queryTreeData.form?parent=' + node.CodeID;
			return true;
		},
		onClickRow : function(row) {
			$('#txtselectvalue').val(row.CodeName);
		}
	});
	$('#tgrid').treegrid({
		url : 'auto/queryTreeData.form?basecode=' + codeid + '&parent=' + codeid
	});
	$('#tgrid').treegrid({
		animate : false
	});
	$('#tgrid').treegrid('collapseAll');
	$('#txtselectvalue').val('');
	$('#dlg').dialog('open');
}


// 0取消 1确认
function closeSearchData(type) {
	$('#dlg').dialog('close');
	if (type == 1 ) {
		$(searchText).textbox('setValue', $('#txtselectvalue').val());
	}
}

// 加载小窗口隶属关系的树形结构
$('#tgrid').treegrid({
	onClickRow : function(row) {
		$('#txtselectvalue').val(row.codeName);
		$('#txtselectvalue').attr('ma', row.ma);
	}
});

function selectTab(title, index) {
	var tableName = getTablename(title);
	var array = [];
	var columns = [];
	$.ajax({
		url : 'auto/findTablename.form',
		type : 'POST',
		async : false,
		data : 'tablename=' + tableName + '&organCode=' + $('#tg').tree('getSelected').id,
		success : function(data) {

			$(data.ds).each(function() {
				array.push({
					field : '',
					title : '',
					width : ''
				});
			});
			columns.push(array);
			$(data.ds).each(function(index, el) {
				columns[0][index]['field'] = el['FieldName'].toLowerCase() == 'organcode' ? 'OrganCode' : el['FieldName'];
				columns[0][index]['title'] = el['Definition'];
				columns[0][index]['width'] = '130';
				columns[0][index]['align'] = 'center';
				columns[0][index]['FieldRelation'] = el['FieldRelation'];
				if (el['FieldType'] == '日期型') {
					columns[0][index]['editor'] = {
						type : 'datebox',
						options : {
							fit : true,
							width : 500,
							editable:false
						}
					};
				} else if (el['FieldType'] == '整型') {
					columns[0][index]['editor'] = 'numberbox';
				} else {
					if (el['grade'] == 1) {
						columns[0][index]['editor'] = {
							type : 'combobox',
							options : {
								valueField : 'codeName',
								textField : 'codeName',
								method : 'get',
								editable:false,
								url : 'auto/findSelectData.form?parent='+el['FieldRelation']
							}
						};
					} else if(el['grade'] == 2) {
						columns[0][index]['editor'] = {
								type : 'textbox',
								options : {
									label: 'Icons',
									value:'sss',
									labelPosition: 'top',    
									iconWidth: 22, 
									editable:false,
									icons: [{ iconCls:'icon-add', handler: function(e){
										searchData2(e,el['FieldRelation']); 
										}
									}]
								}
							};
					}else {
						columns[0][index]['editor'] = {
								type:'validatebox',
								options : {
								}
								
						};
					}
				}
				columns[0][index]['hidden'] = el['FieldState'] == 0 ? true : false;
			});
			$('#dg' + index).datagrid({
				fit : true,
				checkOnSelect : true,
				selectOnCheck : true,
				singleSelect : true,
				columns : columns,
				data : data.ds1,
				dataType : 'json',
				border : false,
				toolbar : '#tb',
				onBeforeSelect : function(index, row) {
					return flag;
				},
				onDblClickRow : function(index, row) {
					if(flag) {
						//判断是否是单位
						var node = $('#tg').tree('getSelected');
						if(node.attributes.bmbj!=0) {
							$.messager.alert('信息提示', '只能在单位下进行操作');
							return false;
						}
						var tab = $('#organTabs').tabs('getSelected');
						var tabIndex = $('#organTabs').tabs('getTabIndex', tab);
						if (editIndex == undefined) {
							$('#dg' + tabIndex).datagrid('beginEdit', index);
							editIndex = index;
							dgBtnDo('left');
							opt = 'updateDgData';
							flag = false;
							menuBtnDo('all');
							disableTab('organTabs', index);
						}
					}
					
				}
			});
		},
		dataType : 'json'
	});
}

function queryMenuList() {
	$.ajax({
		type : 'POST',
		url : 'auto/querySingleForList.form',
		async : false,
		data : 'tableName=tableset&where=[{modulecode:' + 1 + '}]',
		success : function(msg) {
			window.MenuListData = msg;
			//getTablename();
		}
	});
}

// 获得表名
function getTablename(title) {
	var result = "";
	var s = $.parseJSON( window.MenuListData);  
	for (var i = 0; i < s.length; i++) {
		if (s[i].Definition == title) {
			result = s[i].tablename;
		}
	}
	return result;
}

// 关闭添加部门的窗口
function closeDeptDlg() {
	$('#deptName').textbox('setValue', '');
	$('.easyui-textbox').textbox({
		disabled : true
	});
	$('#deptDlg').dialog('close');
}

// 关闭添加班组的窗口
function closeGroupDlg() {
	$('#groupName').textbox('setValue', '');
	$('#groupDlg').dialog('close');
	$('.easyui-textbox').textbox({
		disabled : true
	});
}

// 编辑单位、部门、班组等信息
function editOrgan() {
	var node = $('#tg').tree('getSelected');
	var bmbj = node.attributes.bmbj;// 0单位1部门2班组
	$('#dataForm table input').removeAttr('disabled');
	$('#dataForm table select').removeAttr('disabled');
	$('.easyui-textbox').textbox({
		disabled : false
	});
	$("#dataForm .easyui-combobox").combobox({ disabled: false });  //对form里面的下拉框可用
	
	$("#dataForm .easyui-datebox").datebox({ disabled: false }); //时间控间可用

	$.parser.parse('#dataForm');
	if (bmbj == 0) { // 单位
		menuBtnDo('left');
		opt = 'updateOrgan'; // 保存要修改的单位
		var tab = $('#organTabs').tabs('getSelected');
		var index = $('#organTabs').tabs('getTabIndex', tab);
		disableTab('organTabs', index);
		flag = false;
		dgBtnDo('all');
	} else if (bmbj == 1) { // 部门
		$('#deptName').textbox('setValue', node.text);
		$('#deptDlg').dialog('open');
		opt = 'updateDept';
	} else if (bmbj == 2) { // 班组
		$('#groupName').textbox('setValue', node.text);
		$('#groupDlg').dialog('open');
		opt = 'updateGroup';
	}

}

function valideIsSpace(id) {
	var inputs = $("#" + id + " input");
	var f = true;
	$("#" + id + " input").each(function(index) {
	
		if ($(this).attr("isSpace") == 0 && ($(this).val() == null || $(this).val() == '') && ($(this).textbox('getValue')==null || $(this).textbox('getValue')=="")) {
			var obj = $(this).closest("td").find("span").eq(0);
			alert($(obj).text().substring(0,$(obj).text().length-1) + '不能为空');
			f= false;

		}
	});
	
	return f;
}

// datagrid按钮操作控制
function dgBtnDo(ret) {
	if (ret == 'left') {
		// 禁用菜单栏左边的按钮
		$('#appendDgBtn').linkbutton({
			disabled : true
		});
		$('#removeDgBtn').linkbutton({
			disabled : true
		});
		$('#acceptDgBtn').linkbutton({
			disabled : false
		});
		$('#rejectDgBtn').linkbutton({
			disabled : false
		});
	} else if (ret == 'right') {
		// 菜单栏右边的按钮禁用
		$('#appendDgBtn').linkbutton({
			disabled : false
		});
		$('#removeDgBtn').linkbutton({
			disabled : false
		});
		$('#acceptDgBtn').linkbutton({
			disabled : true
		});
		$('#rejectDgBtn').linkbutton({
			disabled : true
		});
	} else if (ret == 'all') {
		$('#appendDgBtn').linkbutton({
			disabled : true
		});
		$('#removeDgBtn').linkbutton({
			disabled : true
		});
		$('#acceptDgBtn').linkbutton({
			disabled : true
		});
		$('#rejectDgBtn').linkbutton({
			disabled : true
		});
	}
}

// 加载tabs中的子表数据
function loadTabs() {
	$.ajax({
		type : 'POST',
		url : 'auto/querySingleForList.form',
		data : 'tableName=tableSet&where=[{moduleCode:1,tableState:1}]',
		dataType : 'json',
		async : false,
		success : function(msg) {
			$('#organTabs').tabs({
				border : false,
				tabPosition : 'bottom',
				onSelect : function(title, index) {
					selectTab(title, index);
				},
				onBeforeSelect : function(row) {
					return flag;
				}
			});
			queryMenuList();
			$.each(msg, function(i, n) {
				if (!$('#organTabs').tabs('exists', n.Definition)) {
					if (i == 0) {
						$('#organTabs').tabs('add', {
							title : n.Definition,
							content : '<table id=\'dg' + i + '\' ></table>'
						})
					} else {
						$('#organTabs').tabs('add', {
							title : n.Definition,
							content : '<table id=\'dg' + i + '\' ></table>',
							selected : false
						})
					}
				}

			});
		}
	});
}

// 取消编辑datagrid
function reject() {
	var tab = $('#organTabs').tabs('getSelected');
	var index = $('#organTabs').tabs('getTabIndex', tab);
	$('#dg' + index).datagrid('rejectChanges');
	editIndex = undefined;
	dgBtnDo('right');
	flag = true;
	menuBtnDo('right');
	enableTab('organTabs');
}

// 禁用除选中外的tab
function disableTab(id, index) {
	var length = $('#' + id).tabs('tabs').length;
	for (var i = 0; i < length; i++) {
		if (i != index) {
			$('#' + id).tabs('disableTab', i);
		}
	}
}

// 取消禁用tab
function enableTab(id) {
	var length = $('#' + id).tabs('tabs').length;
	for (var i = 0; i < length; i++) {
		$('#' + id).tabs('enableTab', i);
	}
}

function setClass(id) {
	var inputs = $("#" + id + " input");
	$("#" + id + " input").each(function(index) {

		if ($(this).attr("isSpace") == 0) {
			$(this).siblings("span").css({
				color : "red"
			});

		}
	});
}

// 点击单位节点加载单位信息
function displayOrganInfo(row) {
	$.ajax({
		type : 'POST',
		url : 'auto/madeSingleUpdate.form',
		data : 'tableName=organ&where=[{organCode:' + row.id + '}]',
		dataType : 'html',
		success : function(msg) {
			$('#dataForm').html(msg);
			$.parser.parse('#dataForm');
			setClass('dataForm');
			// 刷新当前tab
			var currTab = $('#organTabs').tabs('getSelected'); // 获得当前tab
			var index = $('#organTabs').tabs('getTabIndex', currTab);
			selectTab(currTab.panel('options').title, index);
		}
	});
}

//点击单位节点加载部门、班组信息
function displayDeptInfo() {
	$.ajax({
		type : 'POST',
		url : 'auto/madeSingleInsert.form',
		data : 'tableName=organ',
		dataType : 'html',
		success : function(msg) {
			$('#dataForm').html(msg);
			$('#dataForm input').each(function(index){
				$('#dataForm table input').attr('disabled','disabled');
				$('#dataForm table select').attr('disabled','disabled');
				$('.easyui-textbox').textbox({
					disabled : true
				});
				$("#dataForm .easyui-combobox").combobox({ disabled: true });  //对form里面的下拉框可用
				
				$("#dataForm .easyui-datebox").datebox({ disabled: true }); //时间控间可用
			})
			$.parser.parse('#dataForm');
			setClass('dataForm');
			
			$('#dataForm select').each(function(index){
				$(this).attr('disabled','disabled');
			})
		}
	});
}

// 更改组织单位树的图标
function changeOrganTreeIcons() {
	var nodes = $('#tg').tree('getChildren');
	for (var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		var icons = $(node.target).find('span.tree-icon');
		/*var level = $('#tg').tree('getLevel', nodes[i].target);// 1单位2部门3班组
		if (level == 1) {
			$(icons[0]).addClass('icon-organ');
		} else if (level == 2) {
			$(icons[0]).addClass('icon-dept');
		} else {
			$(icons[0]).addClass('icon-group');
		}*/
		var bmbj = node.attributes.bmbj;// 0单位1部门2班组
		if (bmbj == 0) {
			$(icons[0]).addClass('icon-organ');
		} else if (bmbj == 1) {
			$(icons[0]).addClass('icon-dept');
		} else {
			$(icons[0]).addClass('icon-group');
		}
	}
}


//定义全局变量，当前编辑的行
var editIndex = undefined;

//在datagrid中新增数据
function appendDg() {
	//判断是否是单位
	var node = $('#tg').tree('getSelected');
	if(node.attributes.bmbj!=0) {
		$.messager.alert('信息提示', '只能在单位下新增相应的子库');
		return false;
	}
	//获得当前tab页的index
	var tab = $('#organTabs').tabs('getSelected');
	var index = $('#organTabs').tabs('getTabIndex',tab);
	var tName = getTablename($('.tabs-selected').text());
	var tNameID = getTablename($('.tabs-selected').text())+'ID';
	var tid = getMaxAsId(tName,tNameID);
    if (editIndex == undefined) {
    	
    	var obj={};
    	obj.OrganCode=$('#tg').tree('getSelected').id;
    	obj[tNameID] ='13';
       $("#dg"+index).datagrid('appendRow', obj
        	
        );
        
        editIndex = $('#dg'+index).datagrid('getRows').length - 1;
		$('#dg'+index).datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		dgBtnDo('left');//禁用新增和删除按钮
		opt = 'addDgData';
		flag=false;
		menuBtnDo('all');
		disableTab('organTabs',index);
    }
}

//删除tab中的子表数据
function removeit() {
	var tab = $('#organTabs').tabs('getSelected');
	var index = $('#organTabs').tabs('getTabIndex',tab);
	var row = $('#dg'+index).datagrid('getSelected');
	if(row==null || row=='') {
		$.messager.alert('信息提示','请选择需要删除的数据');
		return false;
	} else {
		var node = $('#tg').tree('getSelected');
		if(node.attributes.bmbj!=0) {
			$.messager.alert('信息提示', '只能在单位下进行操作');
			return false;
		}
		$.messager.confirm('信息提示', '你是否要删除数据', function(r) {
			if (r) {
				var oData = new Object();
				oData.tableName = getTablename($('.tabs-selected').text());
				oData.operation = 'delete';
				var opts = $("#dg"+index).datagrid('getColumnFields'); //这是获取到所有的FIELD
				oData.where = new Object();
				for(i=0;i<opts.length;i++) {
					var col = $("#dg"+index).datagrid( "getColumnOption" , opts[i] );					
					if	(col.hidden){
						oData.where[col.field]=row[col.field];
					}
				}
				var param = "data=" + JSON.stringify(oData);
				var url = "auto/base.form";
				ajaxUtils.post(url, param, function(json) {// 回调函数
					if (json == 'success') {
						dgBtnDo('right');
						flag = true;
						menuBtnDo('right');
						enableTab('organTabs');
						editIndex = undefined;
						$('#dg'+index).datagrid('deleteRow',$('#dg'+index).datagrid('getRowIndex',row));//更改页面上的数据
					}
				})
			}
		});
	}
	
}
//保存新增或修改的部门子表信息
function accept() {
	var tab = $('#organTabs').tabs('getSelected');
	var index = $('#organTabs').tabs('getTabIndex',tab);
	$("#dg"+index).datagrid('endEdit', editIndex);  //结束行编辑模式
	var rows = $("#dg"+index).datagrid('getChanges');  //获得修改后的数据
	var oData = new Object(); 
	var tableName = getTablename($('.tabs-selected').text());
	oData.tableName=tableName;
	var opts = $("#dg"+index).datagrid('getColumnFields'); //这是获取到所有的FIELD
	var emptyStr='';
	for(i=0;i<opts.length;i++) {
		var col = $("#dg"+index).datagrid( "getColumnOption" , opts[i] );					
		if	(!col.hidden){
			emptyStr += rows[0][col.field];
		}
	}
	if(emptyStr==null || emptyStr=='' || emptyStr.length==0) {
		$.messager.alert('信息提示', '请至少填入一项数据');
		$("#dg"+index).datagrid('beginEdit', editIndex);
		return false;
	}
	if(opt == 'addDgData') {	//新增
		oData.operation='insert';
		oData.column=new Object();
		for(i=0;i<opts.length;i++) {
			var col = $("#dg"+index).datagrid( "getColumnOption" , opts[i] );					
			if	(!col.hidden){
				oData.column[col.field]=rows[0][col.field];
			}
			oData.column['organcode']=$('#tg').tree('getSelected').id;
			oData.column[tableName+'ID']=getMaxAsId(tableName,tableName+'ID');
		}
	} else if(opt == 'updateDgData') {	//修改
		oData.operation = 'update';
		oData.column=new Object();
		oData.where =new Object();
		for(i=0;i<opts.length;i++) {
			var col = $("#dg"+index).datagrid( "getColumnOption" , opts[i] );					
			if	(col.hidden){
				oData.where[col.field]=rows[0][col.field];
			}else{
			oData.column[col.field]=rows[0][col.field];
			}
		}
	}
	var url =  "auto/base.form";
	var param = "data=" +JSON.stringify( oData);
	ajaxUtils.post(url, param, function(json) {
		// 回调函数
		if (json == 'success') {	
			dgBtnDo('right');
			flag = true;
			menuBtnDo('right');
			enableTab('organTabs');
			editIndex = undefined;
			$('#dg'+index).datagrid('acceptChanges');//更改页面上的数据
			
		}
	})
	
}

//同一级下不能重名
function validSameName(name,parent) {
	var f = true;
	$.ajax({
		type : 'POST',
		url : 'auto/validSameName.form?organName='+name+'&parent='+parent,
		dataType : 'json',
		async : false,
		success : function(msg) {
			if(msg>0) {
				f=false;
			}
		},
		error : function() {
			alert('error')
		}
	});
	return f ;
}

//判断要删除的节点是否有子节点
function validDelete(node) {
	var f = true;
	$.ajax({
		type : 'POST',
		url : 'auto/validDelete.form?organCode='+node.id,
		dataType : 'json',
		async : false,
		success : function(msg) {
			if(msg!=0) {
				f=false;
			}
		},
		error : function() {
			alert('error')
		}
	});
	return f ;
}
