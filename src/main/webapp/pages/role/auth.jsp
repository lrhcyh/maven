<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>

<body>
	<script type="text/javascript" src="js/inning/role.js"></script>


	<script type="text/javascript">
		var rid = $('#role_dg').datagrid('getSelected').id;
	</script>


	<div class="easyui-panel" style="padding: 5px">
		<ul id="role_tt" class="easyui-tree" data-options="url:'role/getTree.form?roleId='+rid,method:'get',animate:true,checkbox:true,fit:true,border:false"></ul>

		<div align="center">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRoleRight()">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a>
		</div>
	</div>
</body>