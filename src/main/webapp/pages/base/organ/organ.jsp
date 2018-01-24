<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<style>
form table {
	background: #ccc;
	line-height: 50px;
	width: 100%;
}

form table td, table th {
	background: #fff;
	padding: 0 10px;
	align: center;
}
</style>
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div region="west" style="width: 400px;" data-options="collapsible:false">
			<table id="tg" class="easyui-treegrid" style="width: inherient; height: auto" data-options="url:'auto/findTableTree.form',idField:'id',treeField:'name',
																										  onContextMenu: onContextMenu,
																										onClickRow:function(row) {
																											$.ajax({
																											   type: 'POST',
																											   url: 'auto/madeSingleInsert.form',
																											   data: 'tableName='+row.tableName,
																											   dataType: 'html',
																											   success: function(msg){
																											   		$('#dataForm').html(msg);
																											   }
																											});
																										}">
				<thead>
					<tr>
						<th data-options="field:'name',width:180"></th>
						<th data-options="field:'tableName',width:100,hidden:false"></th>
					</tr>
				</thead>
			</table>
			<div id="mm" class="easyui-menu" style="width: 120px;">
				<div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
				<div onclick="editIt()" data-options="iconCls:'icon-edit'">修改</div>
				<div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>
			</div>
			<script type="text/javascript">
				function formatProgress(value) {
					if (value) {
						var s = '<div style="width:100%;border:1px solid #ccc">' + '<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
						'</div>';
						return s;
					} else {
						return '';
					}
				}
				function onContextMenu(e, row) {
					if (row) {
						e.preventDefault();
						$(this).treegrid('select', row.id);
						$('#mm').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
					}
				}
				var idIndex = 100;
				function append() {
					var row = $('#tg').treegrid('getSelected');
					$.ajax({
						type : 'POST',
						url : 'auto/madeSingleInsert.form',
						data : 'tableName=' + row.tableName,
						dataType : 'html',
						success : function(msg) {
							$('#dataForm').html(msg);
							$('tr').last().after('<tr><td><input type=\"button\" value=\"添加\" onclick=\"add();\" class=\"easyui-linkbutton\" ></td></tr>');
							
						}
					});
				}
				function removeIt() {
					var node = $('#tg').treegrid('getSelected');
					if (node) {
						$('#tg').treegrid('remove', node.id);
					}
				}
			
				function add() {
					var formData = {}; 
					var t = $("#dataForm").serializeArray(); 
					$.each(t, function() { formData[this.name] = this.value; }); 
					var dataStr = JSON.stringify(formData);
					var row = $('#tg').treegrid('getSelected');
					formData = "{'operation':'insert','tableName':'"+row.tableName+"','column':"+dataStr+"}";
					var url='<%=basePath%>' + "auto/base.form";
					var param = "data=" + formData;
					ajaxUtils.post(url, param, function(json) {//回调函数
						if(json=='success') {
							$('#dataForm').html('');
							alert('添加成功');
						}else {
							alert('添加失败');
						}
						
					})
				}
			</script>
		</div>
		<div id="content" region="center" style="padding: 5px;">
			<form id="dataForm"></form>
		</div>
	</div>



</body>
</html>