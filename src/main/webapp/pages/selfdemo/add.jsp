<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<body>
	<script type="text/javascript" src="js/inning/selfdemo.js"></script>

	<form id="demoForm" method="post" style="margin: 10px 30px; line-height: 35px;">
		<table>
			<tr>
				<td>demo字符：</td>
				<td><input type="text" id="demostring" name="demostring" class="easyui-validatebox" style="width: 128px" required="true" /></td>
			</tr>
			<tr>
				<td>demo整数：</td>
				<td><input type="text" id="demointeger" class="easyui-validatebox" style="width: 128px" required="true" name="demointeger" /></td>
			</tr>
			<tr>
				<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSelfDemo('insert')">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
			</tr>
		</table>
	</form>
</body>