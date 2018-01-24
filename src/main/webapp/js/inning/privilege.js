function onContextMenu(e, row) {
	if (row) {
		e.preventDefault();
		$(this).treegrid('select', row.id);
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	}
}

function removeIt() {
	var node = $('#tg').treegrid('getSelected');
	if (node) {
		$('#tg').treegrid('remove', node.id);
	}
}

function saveAuthority() {
	var node = $('#privilege_tg').treegrid('getSelected');
	if (node == null) {
		$.messager.alert('消息', '请选择一条记录进行操作', 'info');
		return false;
	}
	showWindow(basepath + '/privilege/goToPage.form?goToPage=insert', 500, 400,
			'添加节点');
}

function editAuthority() {
	var node = $('#privilege_tg').treegrid('getSelected');
	if (node == null) {
		$.messager.alert('消息', '请选择一条记录进行操作', 'info');
		return false;
	}
	showWindow(basepath + '/privilege/goToPage.form?goToPage=update', 500, 400,
			'修改节点');

}

// 删除节点
function removeAuthority() {
	var node = $('#privilege_tg').treegrid('getSelected');
	if (node == null) {
		$.messager.alert('消息', '请选择一条记录进行操作', 'info');
		return;
	}
	if (node.isLeaf == 0) {
		$.messager.alert('消息', '该节点不能删除！', 'info');
		return;
	}
	$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
		if (r) {
			ajaxUtils.post(basepath + "/privilege/deleteMenuNode.form?data="
					+ node.id, null, function(json) {// 回调函数
				var json = eval('(' + json + ')');
				if (json.result == 'success') {
					$('#privilege_tg').treegrid('deleteRow', node.id);
					$.messager.alert('消息', '操作成功', 'info');
				} else {
					$.messager.alert('错误', '操作失败', 'error');
				}
			});
		}
	});

}

function savePrivilegeDemo(flag) {
	var win = $.messager.progress({
		title : '正在提交',
		msg : '正在添加信息,请稍等...'
	});
	
	var node = $('#privilege_tg').treegrid('getSelected');
	$('#parentid').val(node.id);
	
	if ($("#demoForm").form("validate")) {
		var formJson = getFormToJson("demoForm");// moduleset是表单form的id值
		var url;
		var dataStr = '{' + formJson + '}';
		var param = 'data=' + dataStr;
		if (flag == 'insert') {
			
			url = basepath + "/privilege/addMenuNode.form";
			ajaxUtils.post(url, param, function(json) {// 回调函数
				var json = eval('(' + json + ')');
				$('#privilege_tg').treegrid('reload');
				$.messager.progress('close');
				$.messager.alert('消息', '操作成功', 'info');
				closeWin();
			});
		} else if (flag == 'update') {
			url = basepath + "/privilege/editMenuNode.form";
			ajaxUtils.post(url, param, function(json) {// 回调函数
				var json = eval('(' + json + ')');
				$('#privilege_tg').treegrid('update', {
					id : node.id,
					row : {
						name : json.functionName,
						URL : json.url
					}
				})
				$.messager.progress('close');
				$.messager.alert('消息', '操作成功', 'info');
				closeWin();
			});
		}
	}
}

function displayAuthority() {
	var node = $('#privilege_tg').treegrid('getSelected');
	if (node == null) {
		$.messager.alert('消息', '请选择一条记录进行操作', 'info');
		return;
	}
	if (node.isShow == 1) {
		$.messager.alert('消息', '该节点已经是显示状态，操作无效', 'info');
		return;
	} else {
		$.ajax({
			url : 'privilege/hideNode.form?id=' + node.id + '&isShow=1',
			dataType : 'text',
			success : function(j) {
				$('#privilege_tg').treegrid('update', {
					id : node.id,
					row : {
						showText : "是",
							isShow:1
					}
				})
				$.messager.alert('消息', '操作成功！', 'info');
			}
		});
	}

}

function hideAuthority() {
	var node = $('#privilege_tg').treegrid('getSelected');
	if (node == null) {
		$.messager.alert('消息', '请选择一条记录进行操作', 'info');
		return;
	}
	if (node.isShow == 0) {
		$.messager.alert('消息', '该节点已经是隐藏状态，操作无效', 'info');
		return;
	}
	// 如果父节点下的子节点均为隐藏状态 yes可以隐藏 no不能隐藏
	var flag = '';
	if (node.isLeaf == 0) {
		flag = $.ajax({
			url : 'privilege/nodeCanHide.form?id=' + node.id,
			dataType : 'text',
			success : function(j) {
				if (j == 'no') {
					$.messager.alert('消息', '该节点不能隐藏！', 'info');
					return 'no';
				} else {
					return 'yes';
				}
			}
		})
	}

	if (flag != 'no') {
		$.ajax({
			url : 'privilege/hideNode.form?id=' + node.id + '&isShow=0',
			dataType : 'text',
			success : function(j) {
				$('#privilege_tg').treegrid('update', {
					id : node.id,
					row : {
						showText : "否",
						isShow:0
					}
				})
				$.messager.alert('消息', '操作成功！', 'info');
			}
		})
	}
}

