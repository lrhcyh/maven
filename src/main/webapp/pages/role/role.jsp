<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
<script type="text/javascript" src="js/inning/role.js"></script>
	<div id="cc" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'操作',overflow:'hidden',collapsible:false" style="height: 100px">
			<table align="center">
				<tr align="center">
					<td width="100px" height="60px"><a id="btn" onclick="saveRole()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加角色</a></td>
					<!-- <td width="100px"><a id="btn" onclick="editRole()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改角色</a></td> -->
					<td width="100px"><a id="btn" onclick="giveAuthToUser()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加权限</a></td>
					<td width="100px"><a id="btn" onclick="removeRole()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除角色</a></td>
				</tr>
			</table>
		</div>
	<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
		<table id="role_dg" class="easyui-datagrid" style="width: inherit; height: 100%;" data-options="singleSelect:true,
                 url:'role/findRoles.form',
                 method:'get',
                 remoteSort:false,
                 rownumbers:true, 
                 pagination:true
            ">
				<thead>
					<tr>
						<th data-options="field:'id',width:80,align:'center'">编号</th>
						<th data-options="field:'role_name',width:100,align:'center'">角色名称</th>
						<th data-options="field:'create_time',width:240,align:'center'">创建时间</th>
					</tr>
				</thead>
			</table>
		
		</div>
	</div>

<script type="text/javascript">
	function saveRole() {
		showWindow(basepath + 'role/gotoPage.form?page=add', 300, 200, '添加角色');
	}

	function giveAuthToUser() {
		showWindow(basepath + 'role/gotoPage.form?page=addRight', 270, 450, '添加权限');
	}
	
	function removeRole() {
		var node = $('#role_dg').treegrid('getSelected');
		
		ajaxUtils.post(basepath + "/role/remove.form?roleId=" + node.id, null, function(json) {
			var json = eval('(' + json + ')');
			if (json.result == 'success') {
				$('#role_dg').datagrid('load');
				$.messager.alert('消息', '操作成功', 'info');
			} else {
				$.messager.alert('错误', '操作失败', 'error');
			}
		});
	}
</script>

</body>
