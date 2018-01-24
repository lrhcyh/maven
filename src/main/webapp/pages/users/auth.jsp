<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>

<body>


	<script type="text/javascript">
		var rid = ${roleId};
		
		function saveUserRoleRight() {
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

			var rid = ${roleId};

			var param = 'idData=' + s + '&roleId=' + rid;
			ajaxUtils.post(url, param, function(json) {// 回调函数
				if (json == 'success') {
					$.messager.alert('消息', '操作成功', 'info');
					closeWin();
				}
			});
		}
	</script>


	<div class="easyui-panel" style="padding: 5px">
		<ul id="role_tt" class="easyui-tree" data-options="url:'role/getTree.form?roleId='+rid,method:'get',animate:true,checkbox:true,fit:true,border:false"></ul>

		<div align="center">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUserRoleRight()">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a>
		</div>
	</div>
</body>