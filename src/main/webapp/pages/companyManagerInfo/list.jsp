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
		var stockCode = $('#stockCode').textbox('getValue');
		var managerName = $('#managerName').textbox('getValue');
		
		$("#dg").datagrid('load', {
			stockCode : stockCode,
			managerName : managerName
		});
	}
	
	function searchListRepeat() {
		$("#dg").datagrid('load', {
			keyword: 'distinct'
		});
	}

    var toolbar = [ {
		text : '添加股东',
		iconCls : 'icon-add',
		handler : function() {
			showWindow(basepath + '/users/gotoPage.form?page=add', 600, 500, '添加用户等级');
		}
	}, '-', {
		text : '修改股东',
		iconCls : 'icon-edit',
		handler : function() {
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录修改', 'info');
				return false;
			}
			showWindow(basepath + '/users/gotoPage.form?page=edit', 600, 500, '修改用户等级');
		}
	}, '-', {
		text : '删除删除',
		iconCls : 'icon-cut',
		handler : function() {
			var node = $('#dg').treegrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录进行操作', 'info');
				return false;
			}
			$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "/company/remove.form?id=" + node.id, null, function(json) {
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
		text : '保存公司高管信息',
		iconCls : 'icon-save',
		handler : function() {
			// 将修改的数据存放到网页缓存中
			$('#dg').datagrid('endEdit', editIndex);
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录保存', 'info');
				return false;
			}
			$.ajax({
				type : "post",
				dataType : "json",
				url : basepath + "companyManager/saveCompanyManager.form",
				data : {
					manager_info_id : node.manager_info_id,
					stock_code : node.stock_code,
					manager_name : node.manager_name,
					post_name : node.post_name,
					post_type : node.post_type,
					begin_date : node.begin_date,
					end_date : node.end_date,
					sex : node.sex,
					country : node.country,
					education : node.education,
					birth_year : node.birth_year,
					work_experience : node.work_experience,
					created_at : node.created_at,
				},
				success : function(data) {
					editIndex = undefined;
					$('#dg').datagrid('load');
				}
			})
		}
	}, '-', {
		text : '导入Excel',
		iconCls : 'icon-large-chart',
		handler : function() {
			showWindow(basepath + 'companyManager/gotoPage.form?page=import', 350, 200, '批量导入公司高管信息');
		}
	}, '-', {
		text : '审核数据',
		iconCls : 'icon-ok',
		handler : function() {
			$.messager.confirm("提示", "您确定要提交数据吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "companyManager/importCompanyManager.form", null, function(data) {
						var json = eval('(' + data + ')');
						if (json.result == 'success') {
							$('#dg').datagrid('load');
							$.messager.alert('消息', '操作成功', 'info');
						} else {
							$('#dg').datagrid('load');
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
							<td align="right" width="80px" margin="10px">股票代码</td>
							<td align="right" width="60px" margin="10px"><input id="stockCode" name="stockCode" class="easyui-textbox" data-options=""></td>
							<td align="right" width="80px" margin="10px">股东姓名</td>
							<td align="right" width="60px" margin="10px"><input id="managerName" name="managerName" class="easyui-textbox" data-options=""></td>
							
							<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
							<td width="100px" height="50px"><a id="btn" onclick="searchListRepeat()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">重复数据</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
			<table id="dg" class="easyui-datagrid"  style="width: inherit; height: 100%" data-options="singleSelect:true,
							  url:'companyManager/findList.form',
							  toolbar: toolbar,
							  method:'POST', 
							  pageList: [200,500,1000,2000,5000],
							  pagination:true,checkbox:true,onClickCell: onClickCell
							  ">
				<thead>
					<tr>
                        <th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'stock_code',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}">股票编号</th>
						<th data-options="field:'manager_name',width:130,align:'center',editor:{type:'validatebox',options:{required:true}}">高管姓名</th>
						<th data-options="field:'post_name',width:180,align:'center',editor:{type:'validatebox',options:{required:true}}">职位名称</th>
						<th data-options="field:'post_type',width:180,align:'center',editor:{type:'validatebox',options:{required:false}}">职级类别</th>
						<th data-options="field:'begin_date',width:180,align:'center',editor:{type:'datebox',options:{required:false}}">开始时间</th>
						<th data-options="field:'end_date',width:180,align:'center',editor:{type:'datebox',options:{required:false}}">结束时间</th>
						<th data-options="field:'sex',width:180,align:'center',editor:{type:'validatebox',options:{required:false}}">性别</th>
						<th data-options="field:'country',width:180,align:'center',editor:{type:'validatebox',options:{required:false}}">国家</th>
						<th data-options="field:'education',width:180,align:'center',editor:{type:'validatebox',options:{required:false}}">学历</th>
						<th data-options="field:'birth_year',width:180,align:'center',editor:{type:'datebox',options:{required:false}}">出生日期</th>
						<th data-options="field:'work_experience',width:180,align:'center',editor:{type:'validatebox',options:{required:false}}">工作简介</th>
						<th data-options="field:'created_at',width:180,align:'center',editor:{type:'datebox',options:{required:false}}">创建时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
<script type="text/javascript">
var editIndex = undefined;
function endEditing() {
	if (editIndex == undefined){return true}
	if ($('#dg').datagrid('validateRow', editIndex)){
		$('#dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow(index) {
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

$.extend($.fn.datagrid.methods, {
	editCell: function(jq, param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

function onClickCell(index, field){
	if (endEditing()){
		$('#dg').datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
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
