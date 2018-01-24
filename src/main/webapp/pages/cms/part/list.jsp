<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>角色列表</title>
<script type="text/javascript">
    var basepath = '${basePath}';
</script>
<base href="${basePath }">
</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'操作',overflow:'hidden'" style="height: 100px">
			<table align="center">
				<thead align="center">
					<td width="100px" height="60px"><a id="btn" onclick="openEditCMSModel()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增组件</a></td>
					<td width="100px"><a id="btn" onclick="editRole()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑组件</a></td>
					<td width="100px"><a id="btn" onclick="removeRole()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除组件</a></td>
					<td width="100px"><a id="btn" onclick="giveAuthToUser()" class="easyui-linkbutton" data-options="iconCls:'icon-print'">组件预览</a></td>
				</thead>
			</table>
		</div>
		<div data-options="region:'center',fit:true,border:false">
			<table id="dg" class="easyui-datagrid" title="组件列表" style="width: inherit; height: auto;" data-options="rownumbers:true,
							  singleSelect:true,
							  url:'cms/model/dataList.form',
							  method:'POST', 
							  pagination:true,
							  pageList: [5,10,20,50,100],
							  pageSize:5">
				<thead>
					<tr>
						<th data-options="field:'id',width:80,align:'center'">编号</th>
						<th data-options="field:'name',width:240">模板名称</th>
						<th data-options="field:'type',width:100,align:'center'">模板类型</th>
						<th data-options="field:'is_part',width:240,align:'center'">是否部件</th>
						<th data-options="field:'deleted',width:240,align:'center'">是否删除</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>