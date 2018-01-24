<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<body>
	<script type="text/javascript" src="js/inning/gift.js"></script>


	<form id="sendForm" method="post" style="margin: 10px 30px; height: 130px"">
		<table cellpadding="20" height="130px">
			<tr height="40px">
				<td height="25px" width="70px">电话号码：</td>
				<td height="25px"><input type="text" id="phone" class="easyui-validatebox textbox" style="width: 128px" name="demointeger" onblur="checkPhoneNumber();" /></td>
				<td><span id="msg"></span></td>
			</tr>
			<tr align="center">
				<td colspan="2"><a href="javascript:checkCanSend();" class="easyui-linkbutton" iconCls="icon-ok">发放</a></td>
			</tr>
		</table>
	</form>


	<style scoped="scoped">
.textbox {
	height: 20px;
	margin: 0;
	padding: 0 2px;
	box-sizing: content-box;
}
</style>

</body>