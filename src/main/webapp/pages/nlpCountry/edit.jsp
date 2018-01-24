
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="activityForm" action="${basePath}nlpCountry/editNlpCountry.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>国家名字</td>
			<input type="hidden" id="id" name="id" class="easyui-numberbox">
			<td><input type="text" id="name" name="name" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		
		<tr>
			<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
		</tr>
	</table>

</form>

<script type="text/javascript">
	var basepath = '${basePath}';
	
	$(function() {
		var row = $('#dg').datagrid('getSelected');
		
		$('#activityForm').form('load', {
			id : row.id,
			name : row.name,
			
		});
	});
	
	function save() {
		var win = $.messager.progress({
			title : '正在提交',
			msg : '正在添加,请稍等...'
		});
		
		$('#activityForm').form('submit', {
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close');
				}
				return isValid; 
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