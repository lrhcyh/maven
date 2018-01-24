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
<script type="text/javascript">
    var basepath = '<%=basePath%>';

	
</script>
</head>
<body>

	<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
		<div data-options="region:'north',title:'操作',overflow:'show',collapsible:false" style="height: 85px"></div>
		<div style="width: 100%; height: auto;" data-options="region:'center',border:false">




			<table id="dg" class="easyui-datagrid" style="width: inherit; height: 100%" data-options="singleSelect:true,
							  url:'advert/findAdvertPlaceList.form',
							  toolbar: [{
							  		text :'添加广告位',
									iconCls: 'icon-add',
									handler: function(){
										showWindow(basepath + '/advert/gotoPage.form?page=addAdPlace', 600, 500, '新增广告位');
									}
								}],
							  method:'POST', 
							  striped:true,
							  pagination:true
							  ">
				<thead>
					<tr>

						<th data-options="field:'placeId',width:80,align:'center'">广告位编号</th>
						<th data-options="field:'placeName',width:100,align:'center'">广告位名</th>
						<th data-options="field:'placeCode',width:150,align:'center'">广告位编码</th>
						<th data-options="field:'placeHeight',width:200,align:'center'">广告位高度</th>
						<th data-options="field:'placeWidth',width:200,align:'center'">广告位宽度</th>
						<th data-options="field:'status',width:200,align:'center'">状态</th>
						<th data-options="field:'moduleId',width:200,align:'center'">所属前端模块编号</th>




					</tr>
				</thead>
			</table>

		</div>


	</div>



</body>

<script type="text/javascript">
	
</script>
</html>
