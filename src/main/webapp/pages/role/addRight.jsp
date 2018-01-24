<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<body>

<script type="text/javascript" src="js/inning/role.js"></script>

<script type="text/javascript">
	var basepath = '${basePath}';
	
	$(function() {
		var row = $("#role_dg").datagrid('getSelected');
		
		if (row == null) {
			closeWin();
			$.messager.alert('消息', '请选择一条记录修改', 'info');
		} else {
			$("#activityForm").form('load', {
				id: row.id
			});
		}
	})
</script>

	<form id="activityForm" method="post"  style="margin:10px 30px;line-height:35px;" action="${basePath}role/addRight.form">
		<input type="hidden" id="id" name="id" />
		<ul id="tttt" class="easyui-tree"
			url="${basePath}role/getTree.form"
			data-options="field:'roleInfo',onBeforeLoad:function(node, param){var row = $('#role_dg').datagrid('getSelected');param.id = row.id;}"
			checkbox="true">
		</ul>
		
		<div colspan="2">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a>
		</div>
	</form>
	
<script type="text/javascript">
	var basepath = '${basePath}';
	
	function save() {
		var win = $.messager.progress({
			title : '正在提交',
			msg : '正在提交,请稍等...'
		});
		
		var row = $("#role_dg").datagrid('getSelected');

		var nodes =$('#tttt').tree('getChecked', ['checked','indeterminate']);
		var functionIds = "";
		
		for (var i in nodes) {
			functionIds = functionIds + nodes[i].id + ","
		}
		ajaxUtils.post(basepath + "role/addRight.form?functionIds=" + functionIds + "&roleId=" + row.id, null, function(json) {
			$.messager.progress('close');
			closeWin();
			
			var json = eval('(' + json + ')');
			if (json.result == 'success') {
				$.messager.alert('消息', '操作成功', 'info');
				$('#dg').datagrid('load');
			} else {
				$.messager.alert('错误', '操作失败', 'error');
			}
		});
		
	}
</script>

</body>
