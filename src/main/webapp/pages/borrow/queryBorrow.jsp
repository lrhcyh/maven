<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'操作',overflow:'show',collapsible:false" style="height: 85px">
			<form id="searchForm" method="post">
				<table align="center">
					<tr align="center">
						<td>开始时间：<input id="beginDate" name="beginDate" class="easyui-datebox"></input></td>
						<td>结束时间：<input id="endDate" name="endDate" class="easyui-datebox"></input></td>

						<td width="80px" height="50px"><a id="btn" onclick="searchKefu()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>


					</tr>
				</table>
			</form>


		</div>
		<div style="width: 100%; height: auto;" data-options="region:'center',fit:true,border:false">
			<table id="dg" class="easyui-datagrid" title="模板列表" style="width: inherit; height: 100%;" data-options="rownumbers:true,
								showFooter: true,
							  singleSelect:true,
							  url:'borrow/queryListByInvestEndDate.form',
							  striped:true,
							  method:'POST' ">
				<thead>
					<tr>

						<th data-options="field:'phoneNumber',width:240,align:'center'">手机</th>
						<th data-options="field:'fullName',width:100,align:'center'">用户</th>
						<th data-options="field:'firstamout',width:100,align:'center'">投资金额</th>
						<th data-options="field:'platformOutAmout',width:240,align:'center'">本利和</th>
						<th data-options="field:'investEndDate',width:240,align:'center'">兑付日期</th>

					</tr>
				</thead>
			</table>


		</div>
</body>
</html>