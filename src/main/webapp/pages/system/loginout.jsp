<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String zt = (String) request.getAttribute("zt");
%>
<html>
<head>
<base href="<%=basePath%>" />

<script language="JavaScript"> 
alert(123);
$(function() {
	alert('asa')
})
if (window != top) 
top.location.href = location.href; 
</script>

</head>
<body bgcolor="#ffffff">
	<p>&nbsp;</p>
	<p></p>
	<p></p>
	<p></p>
	<script language=JavaScript>

		
	</script>
</body>
</html>
