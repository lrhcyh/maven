
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form id="activityForm" action="${basePath}users/editUser.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>昵称：</td>
			<input type="hidden" id="id" name="id" />
			<td><input type="text" id="fullName" name="fullName" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>邮箱：</td>
			<td><input type="email" id="email" name="email" class="easyui-validatebox" data-options="required:true,validType:'email'" /></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input id="status" name="status" class="easyui-combobox" data-options="
				'valueField': 'label',
				textField: 'value',
				data: [{
					label: '0',
					value: '正常'
				},{
					label: '1',
					value: '锁定'
				}]" /></td>
		</tr>

		<tr>
			<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
		</tr>
	</table>

</form>

<script type="text/javascript">
	var basepath = '${basePath}';
	
	$(function() {
		var row = $("#dg").datagrid('getSelected');
		
		$("#activityForm").form('load', {
			id: row.id,
			status: row.status,
			fullName: row.fullName,
			email: row.email
		})
	})

	$(function() {
		var row = $("#dg").datagrid('getSelected');
		
		$("#activityForm").form('load', {
			fullName : row.fullName,
			email : row.email,
			status : row.status,
			id: row.id
		})
	})

	function save() {
		var win = $.messager.progress({
			title : '正在提交',
			msg : '正在提交,请稍等...'
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
				if (json.result == '-1') {
					$.messager.alert('错误', '操作失败', 'error');
				} else if (json.result == '0') {
					$.messager.alert('消息', '操作成功', 'info');
					$('#dg').datagrid('reload');
				}
			}
		});
	}
</script>
<div></div>

