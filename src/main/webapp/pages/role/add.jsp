<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<body>
	<script type="text/javascript" src="js/inning/role.js"></script>


	<form id="activityForm" method="post"  style="margin:10px 30px;line-height:35px;" action="${basePath}/role/addRole.form">
	<table > 
	   <tr>
	      <td>角色名称：</td>
	      <td><input type="text" id="role_name"  name=role_name class="easyui-validatebox"  style="width:128px" required="true"/>
	   </tr>
	   <tr>
	      <td>角色描述：</td>
		  <td><input type="text" id="role_info" class="easyui-validatebox" style="width:128px"   name="role_info"/></td>
	   </tr>
	    <tr>
	        <td colspan="2">
	         <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
		     <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a>
	        </td>
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
						break;
					case '1':
						console.log(1);
						$.messager.alert('错误', '角色名称重复', 'error');
						break;
					default :
						$.messager.alert('错误', '操作失败', 'error');
						break;
				}
				$('#role_dg').datagrid('reload');
			}
		});
	}
</script>
</body>
