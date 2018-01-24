
var selectFlag = true;



// 添加角色权限
function saveRoleRight() {
	var url = basepath + "/role/saveRoleRight.form";
	// 选中节点的id 子节点和父节点
	var nodes = $('#role_tt').tree('getChecked', 'indeterminate');
	var nodes1 = $('#role_tt').tree('getChecked');
	// alert(nodes);
	// alert(nodes1);
	var s = '';
	for (var i = 0; i < nodes.length; i++) {
		if (s != '')
			s += ',';
		s += nodes[i].id;
	}
	for (var i = 0; i < nodes1.length; i++) {
		if (s != '')
			s += ',';
		s += nodes1[i].id;
	}

	var rid = $('#role_dg').datagrid('getSelected').id;

	var param = 'idData=' + s + '&roleId=' + rid;
	ajaxUtils.post(url, param, function(json) {// 回调函数
		if (json == 'success') {
			$.messager.alert('消息', '操作成功', 'info');
			closeWin();
		}

	});

}

function loadRoleFormData(row) {
	$('#roleForm').form('load', {
		rolename : row.text,
		intro : row.intro,
		deptright : row.deptright,
		rolecode : row.rolecode
	})
	$('#modules').tree({
		url : 'role/getModules.form?modulecodeset=' + row.modulecodeset,
		multiple : true,
		disabled : true
	});
	$('#roleForm input').attr('disabled', 'disabled');
	$('#roleForm textarea').attr('disabled', 'disabled');
	$('#mc').show();
	$('#saveRoleBtn').linkbutton({
		disabled : true
	});
	$('#undoRoleBtn').linkbutton({
		disabled : true
	});
}

var saveFlag;
function addRoleInfo() {
	$('#roleForm').form('load', {
		rolename : '',
		intro : '',
		deptright : '',
		rolecode : '',
	})
	$('#modules').tree({
		url : 'role/getTree.form',
		multiple : true,
		disabled : true
	});
	$('#roleForm input').attr('disabled', false);
	$('#roleForm textarea').attr('disabled', false);
	$('#mc').hide();
	$('#addRoleBtn').linkbutton({
		disabled : true
	});
	$('#editRoleBtn').linkbutton({
		disabled : true
	});
	$('#removeRoleBtn').linkbutton({
		disabled : true
	});
	$('#saveRoleBtn').linkbutton({
		disabled : false
	});
	$('#undoRoleBtn').linkbutton({
		disabled : false
	});
	$('input[name="deptright"]').eq(0).attr('checked','checked');
	saveFlag='insert';
	selectFlag = false;
}

function editRoleInfo() {
	$('#roleForm input').attr('disabled', false);
	$('#roleForm textarea').attr('disabled', false);
	$('#mc').hide();
	$('#addRoleBtn').linkbutton({
		disabled : true
	});
	$('#editRoleBtn').linkbutton({
		disabled : true
	});
	$('#removeRoleBtn').linkbutton({
		disabled : true
	});
	$('#saveRoleBtn').linkbutton({
		disabled : false
	});
	$('#undoRoleBtn').linkbutton({
		disabled : false
	});
	saveFlag='update';
	selectFlag = false;
}

function undoRoleInfo() {
	var row = $('#roleDgList').datalist('getSelected');
	loadRoleFormData(row);
	$('#addRoleBtn').linkbutton({
		disabled : false
	});
	$('#editRoleBtn').linkbutton({
		disabled : false
	});
	$('#removeRoleBtn').linkbutton({
		disabled : false
	});
	$('#saveRoleBtn').linkbutton({
		disabled : true
	});
	$('#undoRoleBtn').linkbutton({
		disabled : true
	});
	selectFlag = true;
}


function saveRoleInfo() {
	var win = $.messager.progress({
		title : '正在提交',
		msg : '正在提交信息,请稍等...'
	});

	$('#roleForm').form('submit', {
		onSubmit : function(param) {
			var nodes = $('#modules').tree('getChecked',['checked','indeterminate']);
			if(nodes==null||nodes==""||nodes.length==0) {
				$.messager.alert('消息', '请选择可操作模块', 'info');
				$.messager.progress('close');
				return false
			}
			var s = '';
            for(var i=0; i<nodes.length; i++){
                if (s != '') s += ',';
                s += nodes[i].id;
            }
			param.operation = saveFlag;
			param.modulecodeset = s;
			if(saveFlag == 'update') {
				param.rolecode = $('#roleDgList').datalist('getSelected').rolecode;
			}
			var isValid = $(this).form('validate');
			if (!isValid) {
				$.messager.progress('close'); // hide progress bar while the
				// form is invalid
			}
			return isValid; // return false will stop the form submission
		},
		success : function(data) {
			$.messager.progress('close');
			$('#roleDgList').datagrid('reload');
			$('#addRoleBtn').linkbutton({
				disabled : false
			});
			$('#editRoleBtn').linkbutton({
				disabled : false
			});
			$('#removeRoleBtn').linkbutton({
				disabled : false
			});
			$('#saveRoleBtn').linkbutton({
				disabled : true
			});
			$('#undoRoleBtn').linkbutton({
				disabled : true
			});
			selectFlag = true;
		}
	});
}


function removeRoleInfo() {
	var node = $('#roleDgList').datagrid('getSelected');
	if (node == null) {
		$.messager.alert('消息', '请选择一条记录进行操作', 'info');
		return;
	}
	$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
		if (r) {
			var win = $.messager.progress({
				title : '正在删除',
				msg : '正在删除,请稍等...'
			});
			ajaxUtils.post(basepath + '/role/remove.form?rolecode=' + node.rolecode, null, function(json) {
				$.messager.progress('close');
				var index = $('#roleDgList').datagrid('getRowIndex',node);
				$('#roleDgList').datagrid('deleteRow',index);
				$('#roleDgList').datagrid('selectRow',index-1);
				
			});
		}
	});
}
