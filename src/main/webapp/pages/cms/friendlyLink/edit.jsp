
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form id="activityForm" action="${basePath}/advert/editFriendlyLink.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>链接名称：</td>
			<td><input type="text" id="name" name="name" class="easyui-textbox" data-options="required:true" /> <input type="hidden" id="id" name="id"></td>
		</tr>
		<tr>
			<td>链接地址：</td>
			<td><input type="text" id="url" name="url" class="easyui-textbox" data-options="required:true" /></td>
		</tr>


		<tr>
			<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
		</tr>
	</table>

</form>

<script type="text/javascript">
	var basepath = '${basePath}';
	
	$(function() {
		var node = $('#dg').datagrid('getSelected');
		$('#activityForm').form('load',{
			id : node.id,
			name : node.name,
			url : node.url
		})
	})
	
	function save() {
		var win = $.messager.progress({
			title : '正在提交',
			msg : '正在添加友情链接,请稍等...'
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

