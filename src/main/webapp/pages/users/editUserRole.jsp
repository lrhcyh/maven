
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form id="activityForm" action="${basePath}/users/editUserRole.form" method="post" style="margin: 10px 30px; line-height: 35px;">
	<table>
		<tr>
			<td>所属角色:</td>
			<input type="hidden" id="id" name="id" />
			<td><input id="roleId" class="easyui-combobox" name="roleId" data-options="valueField:'id',textField:'role_name',url:'users/findRoles.form',required:true,validType:'validCombobox',editable:false"></td>
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
			roleId: row.roleId
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
				$('#dg').datagrid('reload');
			}
		});
	}
</script>
<div></div>

