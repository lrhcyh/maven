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
	<script type="text/javascript" src="js/base/organ.js"></script>
	<style>
form table {
	background: #ccc;
	width: 100%;
}

form table td, table th {
	background: #fff;
	align: center;
	height: 30px;
	padding: 0px 10px;
}

table span {
	width: 120px;
}

.textbox-addon {
	width: 22px;
}
</style>

	<script type="text/javascript">
	//控制左边是否可点
	var flag = true;
	
</script>

	<div class="easyui-layout" style="width: 100%; height: 100%;overflow:hidden;">
		<div region="north" style="height: 30px" data-options="border:false">
			<div class="easyui-panel" data-options="fit:true">
				<a id="addBtn" href="#" class="easyui-menubutton" data-options="menu:'#menu1',iconCls:'icon-add'">新增</a> 
				<a id="editBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editOrgan()">编辑</a> 
				<a id="removeBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removeOrgan()">删除</a> 
				<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save',disabled:true" onclick="save()">保存</a> 
				<a id="cancelBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo',disabled:true" onclick="undo()">取消</a>
			</div>
			<div id="menu1" style="width: 150px;">
				<div onclick="addCompany()">新增单位</div>
				<div onclick="addChildCompany()">新增子单位</div>
				<div onclick="addDept()">新增部门</div>
				<div onclick="addGroup()">新增班组</div>
			</div>
		</div>

		<div region="west" style="width: 200px; height: 100%" data-options="collapsible:false,title:'单位名称'">
			<ul id="tg" title='单位名称'  class="easyui-tree" data-options="url:'auto/findOrganTree.form',
												  border:false,
												  onLoadSuccess:function(node, data) {
													var id = data[0].id;
													var node = $('#tg').tree('find', id);
		   										  	$('#tg').tree('select',node.target);
		   											loadTabs();
													changeOrganTreeIcons();
												 },
												  onBeforeSelect : function(node) {
													return flag;
												},
												  onSelect:function(node) {
													  if(node.attributes.bmbj==0) {
													  	displayOrganInfo(node);
													  }else{
													  	displayDeptInfo();
													  }
												}
												  "></ul>
			
		</div>
		<div id="MenuListData"></div>
		<div id="tb" style="height: auto">
			<a id="appendDgBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendDg()">新增</a> 
			<a id="removeDgBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a> 
			<a id="acceptDgBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,disabled:true" onclick="accept()">保存</a> 
			<a id="rejectDgBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true,disabled:true" onclick="reject()">取消</a>
		</div>
		<div id="content" region="center" >
			<div class="easyui-layout" style="width: 100%; height: 100%">
				<div data-options="region:'north',title:'单位基本信息',collapsible:false,border:false" style="height: 40%;">
					<form id="dataForm"></form>
				</div>
				<div data-options="region:'center'">
					<div id="organTabs" style="width: 100%; height: 100%"></div>
				</div>
			</div>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" closed="true" title="选择指标项集-多级指标" data-options="iconCls:'icon-save'" style="width: 600px; height: 600px; padding: 10px">
		<p>
			<span>数据项名称：</span> <input id='txtselectvalue' type='text' disabled='disabled' value='' style='width: 200px;' />
		</p>
		<br />
		<table id='tgrid' title="" class="easyui-treegrid" style="width: 98%; height: 80%" data-options="               
                method: 'get',
                rownumbers: true,
                idField: 'CodeID',
                treeField: 'CodeName'
            ">
			<thead>
				<tr>
					<th data-options="field:'CodeID',hidden:true" width="220">CodeID</th>
					<th data-options="field:'parent',hidden:true" width="150">parent</th>
					<th data-options="field:'CodeName'" width="300" align="left">数据项内容</th>
					<th data-options="field:'ma'" width="150">数据项编码</th>
				</tr>

			</thead>
		</table>
		</br> <input type='button' value='确认' onclick='closeSearchData(1)' style="margin-right: 20px;" /> <input type='button' onclick='closeSearchData(0)' value='取消' />
	</div>

	<!-- 新增部门 -->
	<div id="deptDlg" class="easyui-dialog" title="信息提示" data-options="iconCls:'icon-save', closed: true,
																     cache: false,
																     modal: true" style="width: 400px; height: 200px; padding: 10px">
		<form id="deptForm" style="margin: 10px 30px; line-height: 35px;">
			<table style="border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; border: none;">
				<tr>
					<td>请输入部门名:</td>
					<td><input type="text" id="deptName" name="deptName" class="easyui-textbox" /><span style='color:red;padding-left:10px;'>★</span>
				</tr>
				<tr>
					<td align="center" colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveDept();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDeptDlg()">取消</a></td>
				</tr>
			</table>

		</form>

	</div>

	<!-- 新增班组 -->
	<div id="groupDlg" class="easyui-dialog" title="信息提示" data-options="iconCls:'icon-save', closed: true,
																     cache: false,
																     modal: true" style="width: 400px; height: 200px; padding: 10px">
		<form id="groupForm" style="margin: 10px 30px; line-height: 35px;">
			<table style="border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; border: none;">
				<tr>
					<td>请输入班组名:</td>
					<td><input type="text" id="groupName" name="groupName" class="easyui-textbox" /><span style='color:red;padding-left:10px;'>★</span>
				</tr>
				<tr>
					<td align="center" colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveGroup();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeGroupDlg()">取消</a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>