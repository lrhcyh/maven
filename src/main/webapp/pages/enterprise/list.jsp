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

		$(function() {

		});

		function displayEnterprise(id) {
			$('#centerArea').panel({
				href : basepath + '/enterprise/gotoPage.form?page=detail&id=' + id,
				onLoad : function() {
				}
			});
		}

		function searchList() {
			var userName = $('#userName').textbox('getValue');
			var beginDateTime = $('#beginDateTime').datebox('getValue');
			var endDateTime = $('#endDateTime').datebox('getValue');
			var companyName = $('#companyName').textbox('getValue');
			var status = $('#status').combobox('getValue');
			$("#dg").datagrid('load', {
				userName : userName,
				beginDateTime : beginDateTime,
				endDateTime : endDateTime,
				companyName : companyName,
				status : status
			});
		}
	</script>

	<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
		<div data-options="region:'north',title:'操作',overflow:'show',collapsible:false" style="height: 100px">
			<form id="searchForm" method="post">
				<table align="center">
					<tr align="center">
						<td align="right" width="80px" margin="10px">用户名</td>
						<td align="right" width="60px" margin="10px"><input id="userName" name="userName" class="easyui-textbox" data-options="prompt:'关键字查询'"></td>
						<td align="right" width="90px">注册时间</td>
						<td><input id="beginDateTime" name="beginDateTime" class="easyui-datebox" data-options="editable:false"></input></td>
						<td align='center' width="20px">至</td>
						<td><input id="endDateTime" name="endDateTime" class="easyui-datebox" data-options="editable:false"></input></td>
						<td align="right" width="80px" margin="10px">单位名称</td>
						<td align="right" width="60px" margin="10px"><input id="companyName" name="companyName" class="easyui-textbox" data-options="prompt:'关键字查询'"></td>
						<td align="right" width="80px" margin="10px">审核状态：</td>
						<td><select id="status" class="easyui-combobox" name="status" data-options="editable:false" style="width: 100px;">
								<option value="">全部</option>
								<option value="0">待审核</option>
								<option value="1">审核通过</option>
						</select></td>


						<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
						<td></td>

					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%; height: auto;" data-options="region:'center',border:false">



			<table id="dg" class="easyui-datagrid" style="width: inherit; height: 100%;" data-options="singleSelect:true,
                 url:'enterprise/findEnterpriseList.form',
                 method:'get',
                 remoteSort:false,
                 rownumbers:true, 
                 pagination:true,
                 striped:true,
                 pageSize:50,
				 toolbar: [{
						iconCls: 'icon-cut',
						text : '审核',
						handler: function(){
							var row = $('#dg').datagrid('getSelected');
								if(row == null) {
									$.messager.alert('提示','请选择一条记录进行操作');
									return false;
								}
								if(row.status!=0) {
									$.messager.alert('提示','只有待审核状态的记录可以审核');
									return false;
								}
								
								$('#centerArea').panel({
									href : basepath + '/enterprise/gotoPage.form?page=audit&id=' + row.id,
									onLoad : function() {
									}
								});
							}
					}]
            ">
				<thead>
					<tr>
						<th data-options="field:'id',width:80,align:'center',hidden:true">investId</th>
						<th data-options="field:'userId',width:80,align:'center',hidden:true">investId</th>


						<th data-options="field:'userName',width:'auto',align:'center'">用户名</th>
						<th data-options="field:'registerDate',width:'auto',align:'center'">注册时间</th>
						<th data-options="field:'companyName',width:'auto',align:'center'">单位名称</th>
						<th data-options="field:'linkman',width:'auto',align:'center'">经办人</th>
						<th data-options="field:'companyTel',width:'auto',align:'center'">经办人办公电话</th>
						<th data-options="field:'email',width:'auto',align:'center'">经办人邮箱</th>
						<th data-options="field:'representative',width:'auto',align:'center'">法定代表人</th>
						<th data-options="field:'businessLicense',width:'auto',align:'center'">营业执照号</th>
						<th data-options="field:'address',width:'auto',align:'center'">单位地址</th>
						<th data-options="field:'auditTime',width:'auto',align:'center'">审核时间</th>
						<th data-options="field:'status',width:'auto',align:'center',formatter:function(value,row,index) {
																							if(value == 0) {
																								return '待审核';
																							} else if(value == 1) {
																								return '审核通过';
																							}  
																						}">状态</th>
						<th data-options="field:'hahaha',width:80,align:'center',formatter: function(value,row,index){
																										return '<a href=javascript:displayEnterprise(\''+row.id+'\') style=\'color: #2472b5\'>查看</a>' ; }">操作
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
