<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
    var basepath = '<%=basePath%>';

	
</script>
</head>
<body>

	<script type="text/javascript">
    
    var toolbar = [ {
		text : '添加友情链接',
		iconCls : 'icon-add',
		handler : function() {
			showWindow(basepath + '/advert/gotoPage.form?page=addFriendlyLink', 600, 500, '添加友情链接');
		}
	}, '-', {
		text : '修改友情链接',
		iconCls : 'icon-edit',
		handler : function() {
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录修改', 'info');
				return false;
			}
			showWindow(basepath + '/advert/gotoPage.form?page=editFriendlyLink', 600, 500, '修改友情链接');
		}
	}, '-', {
		text : '删除友情链接',
		iconCls : 'icon-remove',
		handler : function() {
			var node = $('#dg').treegrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录进行操作', 'info');
				return false;
			}
			$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "/advert/removeFriendlyLink.form?id=" + node.id, null, function(json) {
						var json = eval('(' + json + ')');
						if (json.result == 'success') {
							$('#dg').datagrid('load');
							$.messager.alert('消息', '操作成功', 'info');
						} else {
							$.messager.alert('错误', '操作失败', 'error');
						}
					});
				}
			});
		}
	} ];

	
</script>

	<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
		<div data-options="region:'north',title:'操作',overflow:'show',collapsible:false" style="height: 85px"></div>
		<div style="width: 100%; height: auto;" data-options="region:'center',border:false">




			<table id="dg" class="easyui-datagrid" style="width: inherit; height: 100%" data-options="singleSelect:true,
							  url:'advert/findFriendlyLinkList.form',
							  toolbar: toolbar,
							  method:'POST', 
							  striped:true,
							  pagination:true
							  ">
				<thead>
					<tr>

						<th data-options="field:'id',width:80,align:'center'">编号</th>
						<th data-options="field:'name',width:100,align:'center'">链接名称</th>
						<th data-options="field:'url',width:150,align:'center'">链接地址</th>
						<th data-options="field:'createTime',width:200,align:'center'">创建时间</th>
						<th data-options="field:'fullName',width:200,align:'center'">创建人</th>




					</tr>
				</thead>
			</table>

		</div>


	</div>



</body>

<script type="text/javascript">
	
</script>
</html>
