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
	<form name="admin" id="admin">
		<div style="margin: 30px 30px 30px 30px">
			<input type="button" value="添加" onclick="add();" class="easyui-linkbutton" data-options="width:150" >
		</div>
		<div style="margin: 30px 30px 30px 30px">
			<input type="button" value="修改" onclick="modify();" class="easyui-linkbutton" data-options="width:150" >
		</div>
		<div style="margin: 30px 30px 30px 30px">
			<input type="button" value="删除" onclick="remove();" class="easyui-linkbutton" data-options="width:150" >
		</div>
	</form>


	<script type="text/javascript">
		function add() {
			var formObj=document.getElementById("admin");//获取表单对象
			//获取form的值并转成json格式
			var formJson=getFormToJson("admin");//moduleset是表单form的id值
			var url='<%=basePath%>' + "auto/base.form";
			var dataStr = "{\"operation\":\"insert\",\"tableName\":\"moduleinfo\",\"column\":{\"modulename\":\"test1\",\"xxh\":\"7\",\"modulecode\":7}}";
			var param = "data=" + dataStr;
			ajaxUtils.post(url, param, function(json) {//回调函数
				alert('save success');
			})
		}
		
		function modify() {
			var url='<%=basePath%>' + "auto/base.form";
			var dataStr = "{\"operation\":\"update\",\"tableName\":\"moduleinfo\",\"column\":{\"modulename\":\"test2\",\"xxh\":\"8\"},\"where\":{\"modulecode\":7}}";
			var param = "data=" + dataStr;
			ajaxUtils.post(url, param, function(json) {//回调函数
				alert(' modify success');
			})
		}
		
		function remove() {
			var url='<%=basePath%>' + "auto/base.form";
			var dataStr = "{\"operation\":\"delete\",\"tableName\":\"moduleinfo\",\"where\":{\"modulecode\":7}}";
			var param = "data=" + dataStr;
			ajaxUtils.post(url, param, function(json) {//回调函数
				alert('remove success');
			})
		}
		
		
	</script>
</body>
</html>