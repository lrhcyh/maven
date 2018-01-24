<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
		var startTime = $('#startTime').textbox('getValue');
		var endTime = $('#endTime').textbox('getValue');
		var serchName = $('#serchName').textbox('getValue');
		var userName = $('#userName').textbox('getValue');
		var searchResult = $('#searchResult').textbox('getValue');
		var type = $('#type').textbox('getValue');
		
		$("#dg").datagrid('load', {
			startTime : startTime,
			endTime : endTime,
			serchName: serchName,
			userName: userName,
			searchResult: searchResult,
			type: type
		});
	}
	
	var toolbar = [ {
		text : '导出Excel',
		iconCls : 'icon-large-chart',
		handler : function() {
			var startTime = $('#startTime').textbox('getValue');
			var endTime = $('#endTime').textbox('getValue');
			var serchName = $('#serchName').textbox('getValue');
			var userName = $('#userName').textbox('getValue');
			var searchResult = $('#searchResult').textbox('getValue');
			var type = $('#type').textbox('getValue');
			
			window.location.href=basepath+'/pvSearch/detailExportExcel.form?startTime=' + startTime + '&endTime=' 
				+ endTime + '&type=' + type + '&serchName=' + serchName + '&userName=' + userName + '&searchResult=' 
				+ searchResult;
		}
	}];
	
	function editRoleRight(roleId) {
    	showWindow(basepath + '/users/gotoPage.form?page=editRoleRight&roleId='+roleId, 600, 500, '修改角色权限');
    }
</script>

<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
	<div data-options="region:'north',title:'操作',overflow:'show',collapsible:false" style="height: 85px">
	<form id="searchForm" method="post">
		<table align="center">
			<tr align="center">
					<td align="right" width="80px" margin="10px">开始时间:</td>
					<td align="right" width="60px" margin="10px"><input id="startTime" name="startTime" class="easyui-datetimebox" data-options=""></td>
					<td align="right" width="80px" margin="10px">结束时间:</td>
					<td align="right" width="60px" margin="10px"><input id="endTime" name="endTime" class="easyui-datetimebox" data-options=""></td>
					<td align="right" width="80px" margin="10px">搜索名称:</td>
					<td align="right" width="60px" margin="10px"><input id="serchName" name="serchName" class="easyui-textbox" data-options=""></td>
					<td align="right" width="80px" margin="10px">用户名:</td>
					<td align="right" width="60px" margin="10px"><input id="userName" name="userName" class="easyui-textbox" data-options=""></td>
					<td align="right" width="80px" margin="10px">搜索结果:</td>
					<td align="right" width="60px" margin="10px"><input id="searchResult" name="searchResult" class="easyui-combobox" data-options="
							'valueField': 'label',
							textField: 'value',
							data: [{
								label: 'success',
								value: '搜索成功'
							}, {
								label: 'nodata',
								value: '无数据'
							}, {
								label: 'fail',
								value: '搜索失败'
							}, {
								label: 'missdata',
								value: '缺省数据'
							}]" /></td>
					<td align="right" width="80px" margin="10px">搜索类型:</td>
					<td align="right" width="60px" margin="10px"><input id="type" name="type" class="easyui-combobox" data-options="
							'valueField': 'label',
							textField: 'value',
							data: [{
								label: 'news',
								value: '资讯'
							}, {
								label: 'chart',
								value: '数据图表'
							}, {
								label: 'report',
								value: '研报'
							}, {
								label: 'notice',
								value: '公告'
							}, {
								label: 'indicator',
								value: '指标'
							}]" /></td>
					
					<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
		<table id="dg" class="easyui-datagrid"  style="width: inherit; height: 100%" data-options="singleSelect:true,
						  url:'pvSearch/findDetailList.form',
						  method:'POST', 
						  toolbar: toolbar,
						  pageList: [50,100,150,200],
						  pagination:true,checkbox:true,onClickRow: true,singleSelect: true
						">
			<thead>
				<tr>
                    <th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'serchName',width:300,align:'center'">搜索内容</th>
					<th data-options="field:'create_date',width:140,align:'center'">创建时间</th>
					<th data-options="field:'ip_long',width:200,align:'center'">IP 地址</th>
					<th data-options="field:'type',width:100,align:'center',formatter: function(value,row,index){
							if (row.type == 'news') {
								return '咨讯';
							} else if (row.type == 'chart') {
								return '数据图表';
							} else if (row.type == 'report') {
								return '研报';
							} else if (row.type == 'notice') {
								return '公告';
							} else if (row.type == 'indicator') {
								return '指标';
							}
						}">搜索类型</th>
					<th data-options="field:'searchResult',width:100,align:'center',formatter: function(value,row,index){
							if (row.searchResult == 'success') {
								return '搜索成功';
							} else if (row.searchResult == 'nodata') {
								return '无数据';
							} else if (row.searchResult == 'fail') {
								return '搜索失败';
							} else if (row.searchResult == 'missdata') {
								return '缺省数据';
							}
						}">搜索结果</th>
					<th data-options="field:'userName',width:100,align:'center'">用户名称</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">
var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#dg').datagrid('validateRow', editIndex)){
		var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
		var productname = $(ed.target).combobox('getText');
		$('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
		$('#dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow(index){
	if (editIndex != index){
		if (endEditing()){
			$('#dg').datagrid('selectRow', index)
					.datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#dg').datagrid('selectRow', editIndex);
		}
	}
}
function append(){
	if (endEditing()){
		$('#dg').datagrid('appendRow',{status:'P'});
		editIndex = $('#dg').datagrid('getRows').length-1;
		$('#dg').datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
	}
}
function removeit(){
	if (editIndex == undefined){return}
	$('#dg').datagrid('cancelEdit', editIndex)
			.datagrid('deleteRow', editIndex);
	editIndex = undefined;
}
function accept(){
	if (endEditing()){
		$('#dg').datagrid('acceptChanges');
	}
}
function reject(){
	$('#dg').datagrid('rejectChanges');
	editIndex = undefined;
}
function getChanges(){
	var rows = $('#dg').datagrid('getChanges');
	alert(rows.length+' rows are changed!');
}
</script>
  
</body>
</html>
