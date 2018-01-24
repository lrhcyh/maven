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
<title>部门调整树</title>
</head>
<body>
<div style="height:50px;line-height:50px;padding:0 10px;">
<p>姓名：<input id="empName" type="text"></p>
</div>
<input type="hidden" id="empId" value="<%=request.getParameter("id") %>"/> 
<div class="easyui-panel" style="padding:5px">
		<ul id="province" class="easyui-tree" data-options="
		
				method: 'get',
				animate: true,
				lines:true,
				onContextMenu: function(e,node){
					e.preventDefault();
					$(this).tree('select',node.target);
					$('#mm').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
				},
				onClick:function(node){
				    findChildren(node);
				},
				onBeforeExpand:function(node){
                  
					
				}
		
		"></ul>
	</div>
	<script type="text/javascript">
	 $(function(){
			
			var url = "personnel/findOrganTree.form";
			$.ajax({
				url:url,
				type:"post",
				//data:data,
				dataType:"json",
				success:function(jsonData){
					//var t = "["+jsonData.data.toString()+"]";
					var t = jsonData;
					$("#province").tree("loadData",t);
				}
			})
			
			$.ajax({
				url:"personnel/findUserById.form?empCode="+$("#empId").val(),
				type:"post",
				//data:data,
				dataType:"json",
				success:function(jsonData){
					//var t = "["+jsonData.data.toString()+"]";
					var t = jsonData;
					$("#empName").val(t.EmpName);
				}
			})
			
			
		})	 
	
		
		function findChildren(temp){
		 
		 
	 }
	
	</script>
</body>
</html>