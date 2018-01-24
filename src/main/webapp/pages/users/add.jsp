
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form id="activityForm" action="${basePath}/users/addUser.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" id="userName" name="userName" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>昵称：</td>
			<td><input type="text" id="fullName" name="fullName" value="" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" id="passWord" name="passWord" value="" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>重复密码：</td>
			<td><input type="password" id="passWord2" name="passWord2" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#passWord\']'" /></td>
		</tr>
		<tr>
			<td>邮箱：</td>
			<td><input type="email" id="email" name="email" class="easyui-validatebox" data-options="required:true,validType:'email'" /></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input id="status" name="status" class="easyui-combobox" data-options="
				valueField: 'label',
				textField: 'value',
				data: [{
					label: '0',
					value: '正常',
					selected: true
				},{
					label: '1',
					value: '锁定'
				}]" /></td>
		</tr>
		<tr>
			<td>所属角色:</td>
			<td><input id="roleId" class="easyui-combobox" name="roleId" data-options="valueField:'id',textField:'role_name',url:'users/findRoles.form',required:true,value:'--请选择--',validType:'validCombobox',editable:false" ></td>
		</tr>

		<tr>
			<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
		</tr>
	</table>

</form>

<script type="text/javascript">
	var basepath = '${basePath}';
	
	function save() {
		var win = $.messager.progress({
			title : '正在提交',
			msg : '正在添加,请稍等...'
		});

		$('#activityForm').form('submit', {
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close'); // hide progress bar while the form is invalid
				}
				return isValid; // return false will stop the form submission
			},
			success : function(data) {
				$.messager.progress('close');
				closeWin();
				
				var json = eval('(' + data + ')');
				switch (json.result) {
					case '-1':
						$.messager.alert('错误', '操作失败', 'error');
						break;
					case '0':
						$.messager.alert('消息', '操作成功', 'info');
						$('#dg').datagrid('reload');
						break;
					case '1':
						$.messager.alert('错误', '用户名重复', 'error');
						break;
					default :
						$.messager.alert('错误', '操作失败', 'error');
						break;
				}
			}
		});
	}
</script>
<div></div>

