<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<script type="text/javascript" src="js/inning/selfdemo.js"></script>
	<div>
		<table class="easyui-datagrid" title="用户列表" style="width: inherit; height: auto;" data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:10,pageList:[1,10,20],url:'http://127.0.0.1:8080/background/demo/queryList.form',method:'post',toolbar:'#tb',footer:'#ft'">
			<thead>
				<tr>
					<th data-options="field:'userId',width:250,align:'center'">用户Id</th>
					<th data-options="field:'fullName',width:100,align:'center'">姓名</th>
					<th data-options="field:'canUseAmnout',width:80,align:'center'">可用金额</th>
					<th data-options="field:'freezeAmount',width:80,align:'center'">冻结金额</th>
					<th data-options="field:'idNumber',width:240,align:'center'">身份证号</th>

				</tr>
			</thead>
		</table>
		<div id="tb" style="padding: 2px 5px;">
			Date From: <input class="easyui-datebox" style="width: 110px"> 到: <input class="easyui-datebox" style="width: 110px"> 语言: <select class="easyui-combobox" panelHeight="auto" style="width: 100px">
				<option value="java">Java</option>
				<option value="c">C</option>
				<option value="basic">Basic</option>
				<option value="perl">Perl</option>
				<option value="python">Python</option>
			</select> <a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
		<div id="ft" style="padding: 2px 5px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a> <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a> <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
	</div>

</body>
