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
    
    function upload() {
		var win = $.messager.progress({
			title : '正在上传',
			msg : '正在上传,请稍等...'
		});
		
		$('#activityForm').form('submit', {
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close');
				}
				return isValid;
			},
			success : function(data) {
				$.messager.progress('close');
				closeWin();
			}
		});
	}
	
    function editRoleRight(roleId) {
    	showWindow(basepath + '/users/gotoPage.form?page=editRoleRight&roleId='+roleId, 600, 500, '修改角色权限');
    }
</script>

	<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
		<form id="activityForm" action="${basePath}demo/importPDF.form" method="post" enctype="multipart/form-data" 
			style="margin: 10px 30px; line-height: 35px;">
		  	<input type="file" name="file" iconCls="icon-large-chart">  
			    
		    <br/><br/>
		    <div colspan="2">
			    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="upload();">上传</a> 
			    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin();">取消</a>
		    </div>
		</form>
	</div>
</body>

</html>
