
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="activityForm" action="${basePath}nlpSynonym/editNlpSynonym.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>指标扩展</td>
			<input type="hidden" id="id" name="id" >
			<td><input type="text" id="name" name="name" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		<tr>
			<td>创建时间</td>
			<td><input type="text" id="createTime" name="createTime" class="easyui-datebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>指标类型</td>
			<td><input type="text" id="type" name="type" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>同义词</td>
			<td><input type="text" id="name_text" name="name_text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>股票代码</td>
			<td><input type="text" id="stock_name" name="stock_name" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>股票名称</td>
			<td><input type="text" id="stock_code" name="stock_code" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>阿博茨扩展名</td>
			<td><input type="text" id="stock_abc_code" name="stock_abc_code" class="easyui-validatebox" data-options="required:false"></td>
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
			createTime : row.createTime,
			type : row.type,
			name_text : row.name_text,
			stock_name : row.stock_name,
			stock_code : row.stock_code,
			stock_abc_code : row.stock_abc_code
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