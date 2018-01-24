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

</head>
<body>

<script type="text/javascript">
    var basepath = '<%=basePath%>';
    

    
    function searchList() {
		var userName = $('#userName').textbox('getValue');
		var fullName = $('#fullName').textbox('getValue');
		var roleId = $('#roleId').combobox('getValue');
		$("#dg").datagrid('load', {
			userName : userName,
			fullName : fullName,
			roleId : roleId
		});
	}

    var toolbar = [ {
		text : '添加用户',
		iconCls : 'icon-add',
		handler : function() {
			showWindow(basepath + '/users/gotoPage.form?page=add', 300, 400, '添加用户');
		}
	}, '-', {
		text : '修改用户',
		iconCls : 'icon-edit',
		handler : function() {
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录修改', 'info');
				return false;
			}
			showWindow(basepath + '/users/gotoPage.form?page=edit', 300, 250, '修改用户');
		}
	}, '-', {
		text : '删除用户',
		iconCls : 'icon-cut',
		handler : function() {
			var node = $('#dg').treegrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录进行操作', 'info');
				return false;
			}
			$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "/users/removeUser.form?id=" + node.id, null, function(json) {
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
    
    function editUserRole(roleId) {
    	showWindow(basepath + '/users/gotoPage.form?page=editUserRole&roleId='+roleId, 300, 150, '修改用户角色');
    }
</script>

	<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
		<div data-options="region:'north',title:'操作',overflow:'show',collapsible:false" style="height: 85px">
		<form id="searchForm" method="post">
			<table align="center">
				<tr align="center">
						<td align="right" width="80px" margin="10px">用户名:</td>
						<td align="right" width="60px" margin="10px"><input id="userName" name="userName" class="easyui-textbox" data-options=""></td>
						<td align="right" width="80px" margin="10px">昵称:</td>
						<td align="right" width="60px" margin="10px"><input id="fullName" name="fullName" class="easyui-textbox" data-options=""></td>
						<td align="right" width="80px" margin="10px">所属角色</td>
						<td><input id="roleId" class="easyui-combobox" name="roleId" data-options="valueField:'id',textField:'role_name',url:'users/findRoles.form',width:100,value:'全部',editable:false"></td>
						<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
				</tr>
			</table>
			</form>
		</div>
		
		<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
			<table id="dg" class="easyui-datagrid"  style="width: inherit; height: 100%" data-options="singleSelect:true,
							  url:'users/findUserList.form',
							  toolbar: toolbar,
							  method:'POST', 
							  pagination:true
							  ">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',width:'auto',align:'center',hidden:true">编号</th>
						<th data-options="field:'roleId',width:'auto',align:'center',hidden:true">角色编号</th>
						<th data-options="field:'userName',width:150,align:'center'">用户名</th>
						<th data-options="field:'fullName',width:150,align:'center'">昵称</th>
						<th data-options="field:'email',width:150,align:'center'">邮箱</th>
						<th data-options="field:'status',width:180,align:'center',formatter: function(value,row,index){
							if (row.status == '0') {
								return '正常';
							} else if (row.status == '1') {
								return '锁定';
							}
						}">状态</th>
						<th data-options="field:'role_name',width:180,align:'center'">所属角色</th>
						<!-- <th data-options="field:'menus',width:'auto',align:'center'">可访问资源</th> -->
						<th data-options="field:'hahaha',width:80,align:'center',formatter: function(value,row,index){
																										return '<a href=javascript:editUserRole('+row.roleId+') style=\'color: #2472b5\'>修改权限</a>' ; 
																										}">操作
						</th>
						



					</tr>
				</thead>
			</table>

		</div>


	</div>



</body>

<script type="text/javascript">
	
</script>
</html>
