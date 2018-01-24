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
	    
		var name = $('#name').textbox('getValue');
		var name_text = $('#name_text').textbox('getValue');
		
		$("#dg").datagrid('load', {
			name : name,
			name_text : name_text
		});
	}
    
	var toolbar = [ {
		text : '添加同义词',
		iconCls : 'icon-add',
		handler : function() {
			showWindow(basepath + '/nlpSynonym/gotoPage.form?page=add', 600, 500, '添加同义词');
		}
	}, '-', {
		text : '修改同义词',
		iconCls : 'icon-edit',
		handler : function() {
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录修改', 'info');
				return false;
			}
			showWindow(basepath + '/nlpSynonym/gotoPage.form?page=edit', 600, 500, '修改同义词');
		}
	}, '-', {
		text : '删除同义词',
		iconCls : 'icon-cut',
		handler : function() {
			var node = $('#dg').treegrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录进行操作', 'info');
				return false;
			}
			$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "/nlpSynonym/remove.form?id=" + node.id, null, function(json) {
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
	}, '-', {
		text : '导入Excel',
		iconCls : 'icon-large-chart',
		handler : function() {
			showWindow(basepath + 'nlpSynonym/gotoPage.form?page=import', 350, 200, '批量导入同义词');
		}
	}, '-', {
		text : '审核数据',
		iconCls : 'icon-ok',
		handler : function() {
			$.messager.confirm("提示", "您确定要提交数据吗？", function(r) {
				if (r) {
					var win = $.messager.progress({
						title : '正在提交',
						msg : '正在添加,请稍等...'
					});
					ajaxUtils.post(basepath + "nlpSynonym/importSynonym.form", null, function(data) {
						$.messager.progress('close');
						closeWin();
						var json = eval('(' + data + ')');
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
							<td align="right" width="80px" margin="10px">指标扩展:</td>
							<td align="right" width="60px" margin="10px"><input id="name" name="name" class="easyui-textbox" data-options=""></td>
							<td align="right" width="80px" margin="10px">同义词:</td>
							<td align="right" width="60px" margin="10px"><input id="name_text" name="name_text" class="easyui-textbox" data-options=""></td>
							<td align="right" width="80px" margin="10px">指标类型:</td>
							<td align="right" width="60px" margin="10px"><input id="type" name="type" class="easyui-combobox" data-options="
								'valueField': 'label',
								textField: 'value',
								data: [{
									label: '1',
									value: '指标扩展'
								}, {
									label: '2',
									value: '指标及指标同义词'
								}, {
									label: '3',
									value: '行业及行业同义词'
								}, {
									label: '4',
									value: '公司名及公司名同义词'
								}, {
									label: '5',
									value: '公式扩展'
								}, {
									label: '6',
									value: '主题的扩展'
								}, {
									label: '7',
									value: '财务指标词'
								}]"></td>
							
							<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
					</tr>
				</table>
			</form>
			</div>
			<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
			 <table id="dg" class="easyui-datagrid"  style="width: inherit; height: 100%;" data-options="singleSelect:true,
							  url:'nlpSynonym/findList.form?type=3',
							  toolbar: toolbar,
							  method:'POST', 
							  pageList: [200,500,1000,2000,5000],
							  pagination:true,checkbox:true,onClickRow: true,singleSelect: true
							  ">
				<thead>
					<tr>
	                    <th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'name',width:150,align:'center'">指标扩展</th>
						<!-- <th data-options="field:'createTime',width:150,align:'center'">创建时间</th> -->
						<th data-options="field:'type',width:200,align:'center',formatter: function(value,row,index){
							if (row.type == '1') {
								return '指标扩展';
							} else if (row.type == '2') {
								return '指标及指标同义词';
							} else if (row.type == '3') {
								return '行业及行业同义词';
							} else if (row.type == '4') {
								return '公司名及公司名同义词';
							} else if (row.type == '5') {
								return '公式扩展';
							} else if (row.type == '6') {
								return '主题的扩展';
							} else if (row.type == '7') {
								return '财务指标词';
							}
						}">指标类型</th>
						<th data-options="field:'name_text',width:750,align:'center'">同义词</th>
						<th data-options="field:'stock_name',width:150,align:'center'">股票代码</th>
						<th data-options="field:'stock_code',width:150,align:'center'">股票名称</th>
						<th data-options="field:'stock_abc_code',width:150,align:'center'">阿博茨扩展名</th>
						<th data-options="field:'parentName',width:150,align:'center',formatter: function(value,row,index){
							return '同义词';
						}">父类</th>
					</tr>
				</thead>
			</table>
			</div>
		</div>
		
	


<script type="text/javascript">
var i = 0;

$('#nlpSynonym').tabs({
	onSelect: function(title, index) {
		switch (index) {
			case 0:
				break;
			case 1:
				break;
			default:
				break;
		}
	}
});

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
