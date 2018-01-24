<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<script type="text/javascript" src="js/inning/selfdemo.js"></script>
	<form id="demoForm" method="post" style="margin: 10px 30px; line-height: 35px;">
		<table>
			<tr>
				<td>用户姓名：</td>
				<td><input type="hidden" value="${userinfo.userId}" id="userId" name="userId" /> <input type="text" value="${userinfo.fullName}" id="fullName" readonly="readonly" name="fullName" class="easyui-validatebox" style="width: 158px" /></td>
			</tr>
			<tr>
				<td>可用金额：</td>
				<td><input type="text" value="${userinfo.canUseAmnout}" readonly="readonly" id="canUseAmnout" class="easyui-validatebox" style="width: 128px" required="true" name="canUseAmnout" /></td>
			</tr>
			<tr>
				<td>冻结金额：</td>
				<td><input type="text" id="price" class="easyui-numberbox" style="width: 128px" data-options="required:true,missingMessage:'请输入冻结金额'" name="price" /></td>
			</tr>
			<tr>
				<td>原因：</td>
				<td><input type="text" id="cause" class="easyui-validatebox" style="width: 128px" data-options="required:true,missingMessage:'请输入冻结原因'" name="cause" /></td>
			</tr>
			<tr>
				<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSelfDemo('update')">冻结</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
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
	<script>
		$(function(){
			$('input.easyui-validatebox').validatebox({
				tipOptions: {	// the options to create tooltip
					showEvent: 'mouseenter',
					hideEvent: 'mouseleave',
					showDelay: 0,
					hideDelay: 0,
					zIndex: '',
					onShow: function(){
						if (!$(this).hasClass('validatebox-invalid')){
							if ($(this).tooltip('options').prompt){
								$(this).tooltip('update', $(this).tooltip('options').prompt);
							} else {
								$(this).tooltip('tip').hide();
							}
						} else {
							$(this).tooltip('tip').css({
								color: '#000',
								borderColor: '#CC9933',
								backgroundColor: '#FFFFCC'
							});
						}
					},
					onHide: function(){
						if (!$(this).tooltip('options').prompt){
							$(this).tooltip('destroy');
						}
					}
				}
			}).tooltip({
				position: 'right',
				content: function(){
					var opts = $(this).validatebox('options');
					return opts.prompt;
				},
				onShow: function(){
					$(this).tooltip('tip').css({
						color: '#000',
						borderColor: '#CC9933',
						backgroundColor: '#FFFFCC'
					});
				}
			});
		});
	</script>
</body>