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
<title>离退休处理</title>
</head>
<body>
<div style="height:50px;line-height:50px;padding:0 10px;">
<p>姓&nbsp;&nbsp;&nbsp; &nbsp;    名：<input id="empName" type="text"  readonly="readonly" style="width:160px;height: 30px"></p>
</div>
<input type="hidden" id="empId" value="<%=request.getParameter("id") %>"/> 
<div style="height:50px;line-height:50px;padding:0 10px;">
<p>减员类别：<select class="easyui-combobox" name="state" id="state" style="width:160px;height: 30px">
		<option value="退休">退休</option>
		<option value="离休">离休</option>
		</select>
		</p>
</div>
<div style="height:50px;line-height:50px;padding:0 10px;">
<p>减员时间：<input id="empDate" class="easyui-datebox" style="width:160px;height: 30px" type="text">
		</p>
</div>
	<script type="text/javascript">
	 $(function(){
			
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
	

	</script>
</body>
</html>