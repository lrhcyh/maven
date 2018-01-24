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
    function searchAdvert() {
		var place = $('#ad_cc').combobox('getValue');
		$("#dg").datagrid('load',{place:place});
	}
    
    
    function onShelf(id) {
    	$.messager.confirm('提示','确定要上架这条广告吗?',function(r){
    	    if (r){
    	    	ajaxUtils.post(basepath + '/advert/updateStatus.form?adId=' + id+'&status=1', null, function(json) {
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
    
    function underShelf(id) {
    	var row = $('#dg').datagrid('getSelected');
    	//卡片广告位不能少于四张图片
    	if(row.placeId == 3) {
    		 $.ajax({
    			url:'advert/findAdvertCount.form?placeId='+row.placeId,
    			dataType:'text',
    			success:function(j) {
    				if(j<=4) {
    					$.messager.alert('消息', '无法下架，不能少于四个正常状态的广告', 'info');
    				} else {
    					$.messager.confirm('提示','确定要下架这条广告吗?',function(r){
    		        	    if (r){
    		        	    	ajaxUtils.post(basepath + '/advert/updateStatus.form?adId=' + id+'&status=2', null, function(json) {
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
    			}
    		});
    	} else {
    		$.messager.confirm('提示','确定要下架这条广告吗?',function(r){
        	    if (r){
        	    	ajaxUtils.post(basepath + '/advert/updateStatus.form?adId=' + id+'&status=2', null, function(json) {
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
    	
    	
    }
	
</script>

	<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
		<div data-options="region:'north',title:'操作',collapsible:false" style="height: 100px">
			<form id="searchForm" method="post">
				<table align="center">
					<tr align="center">

						<td width="80px"></td>
						<td width="80px" align="right">广告位：</td>
						<td><input id="ad_cc" class="easyui-combobox" name="place_id" data-options="valueField:'placeId',textField:'placeName',url:'advert/findPlaces.form',value:'--请选择--',editable:false"></td>
						<td width="80px" height="50px"><a id="btn" onclick="searchAdvert()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
					</tr>
				</table>
			</form>


		</div>
		<div style="width: 100%; height: auto;" data-options="region:'center',border:false">




			<table id="dg" class="easyui-datagrid" style="width: inherit; height: 100%"
				data-options="singleSelect:true,
							  url:'advert/findList.form',
							  toolbar: [{
							  		text :'添加广告',
									iconCls: 'icon-add',
									handler: function(){
										showWindow(basepath + '/advert/gotoPage.form?page=addAdvert', 600, 500, '新增广告');
									}
								},'-',{
							  		text :'修改广告',
									iconCls: 'icon-edit',
									handler: function(){
										var row = $('#dg').datagrid('getSelected');
										if (row == null) {
											$.messager.alert('消息', '请选择要修改的广告', 'info');
											return false;
										}
										showWindow(basepath + '/advert/gotoPage.form?page=editAdvert', 600, 500, '修改广告');
									}
								},'-',{
							  		text :'删除广告',
									iconCls: 'icon-remove',
									handler: function(){
										var row = $('#dg').datagrid('getSelected');
										if (row == null) {
											$.messager.alert('消息', '请选择要删除的广告', 'info');
											return false;
										}
										$.messager.confirm('确认','确定要删除这条记录吗?',function(r){
										    if (r){
										    	ajaxUtils.post(basepath + '/advert/updateStatus.form?adId=' + row.adId+'&status=3', null, function(json) {
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
								}],
							  method:'POST', 
							  pagination:true,
							  rownumbers:true,
							  striped:true,
							  onLoadSuccess:function(){
								$(this).datagrid('enableDnd');
							 },
							 onBeforeDrop:function(targetRow,sourceRow,point) {
							 	
							 	$.messager.confirm('确认','确定要改变这条记录的顺序吗?',function(r){
										    if (r){
										    	var sourceId = sourceRow.adId;
												var targetId = targetRow.adId;
												var sourceNum = sourceRow.sortNum;
												var targetNum = targetRow.sortNum;
												var placeId = '';
												if(sourceRow.placeId == targetRow.placeId) {
													placeId = sourceRow.placeId;
												}
												$.ajax({
													url:'advert/dragNode.form?sourceId='+sourceId+'&targetId='+targetId+'&sourceNum='+sourceNum+'&targetNum='+targetNum+'&point='+point+'&placeId='+placeId,
													dataType:'text',
													success:function(j) {
														$('#dg').datagrid('reload');
													}
												})
											 }
										});
								}
							 	
								
							  
							  ">
				<thead>
					<tr>

						<th data-options="field:'adId',width:80,align:'center',hidden:true">编号</th>
						<th data-options="field:'sortNum',width:80,align:'center',hidden:true">排序</th>
						<th data-options="field:'placeId',width:100,align:'center'">广告位编号</th>
						<th data-options="field:'adTitle',width:150,align:'center'">广告标题</th>
						<th data-options="field:'thumb',width:200,align:'center'">图片</th>
						<th data-options="field:'url',width:200,align:'center'">链接</th>
						<th data-options="field:'startTime',width:200,align:'center'">广告投放开始时间</th>
						<th data-options="field:'endTime',width:200,align:'center'">广告投放结束时间</th>
						<th data-options="field:'status',width:180,align:'center',formatter: function(value,row,index){
																									if (row.status =='1'){
																										return '正常';
																									} else if(row.status=='2') {
																										return '已下架';
																									}}">广告状态</th>
						<th data-options="field:'thumbImg',width:100,align:'center',formatter:function (value,row,index){
																						return '<img height=&quot;50px&quot; width=&quot;100px&quot; src=&quot;http://pic.hanxinbank.com/'+row.thumb+'&quot; ></img>'; }">图片</th>
						<th data-options="field:'hahaha',width:180,align:'center',formatter: function(value,row,index){
																									return '<a href=javascript:onShelf('+row.adId+') style=\'color: #2472b5\'>上架</a>&nbsp;&nbsp;<a href=javascript:underShelf('+row.adId+') style=\'color: #2472b5\'>下架</a>';}">操作
						</th>





					</tr>
				</thead>
			</table>

		</div>


	</div>



</body>


</html>
