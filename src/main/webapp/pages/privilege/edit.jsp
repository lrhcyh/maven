<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>


<body>
	<script type="text/javascript" src="js/inning/privilege.js"></script>
	<script type="text/javascript">
	$(function() {
		var node = $("#privilege_tg").treegrid('getSelected');
		$("#fid").val(node.id);
		$("#functionName").val(node.name);
		$("#url").val(node.URL);
	})
</script>

	<form id="demoForm" method="post" style="margin: 10px 30px; line-height: 35px;">
		<table>

			<tr>
				<td>节点名称：</td>
				<input type="hidden" id="fid" name="id" />
				<td><input type="text" id="functionName" name="functionName" class="easyui-validatebox" style="width: 128px" required="true" /></td>
			</tr>
			<tr>
				<td>url：</td>
				<td><input type="text" id="url" class="easyui-validatebox" style="width: 128px" name="url" /></td>
			</tr>
			<tr>
				<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePrivilegeDemo('update')">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
			</tr>
		</table>
	</form>
</body>
