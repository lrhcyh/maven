<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="jeasyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/ajax.utils.js"></script>
<script type="text/javascript" src="js/form.utils.js"></script>
   <link href="css/public.css" rel="stylesheet" type="text/css">


<title>用户登录</title>
</head>
<body>
<script type="text/javascript">
function submitForm(){
	var msg="";
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	
	
	
	if(username==""){
		msg="用户名不能为空！";
		document.getElementById("msg").innerHTML=msg;
		return ;
	}else if(password==""){
		msg="密码不能为空！";
		document.getElementById("msg").innerHTML=msg;
		return ;
	}
	var formObj=document.getElementById("admin");//获取表单对象
	//获取form的值并转成json格式
	var formJson=getFormToJson("admin");//moduleset是表单form的id值
	var url='<%=basePath%>'+"login/loginIn.form";
	var dataStr='{'+formJson+'}';
	var param = 'data='+ dataStr;
	ajaxUtils.post(url, param,function(json) {//回调函数
		var json=eval('('+json+')');
		if (json.result==1) {
			msg="用户不存在！";
			document.getElementById("msg").innerHTML=msg;
			return false;
		}else if(json.result==2){
			msg="输入密码错误！";
			document.getElementById("msg").innerHTML=msg;
			return false;
		}else if(json.result==3){
            window.location='<%=basePath%>'+"index.jsp";
		}
	});	
}

function reset(){
	document.getElementById("username").value="";
	document.getElementById("password").value="";
}
function resetForm(){
	document.getElementById("admin").reset();
}
//验证用户名密码是否为空值
function check(){
	var msg="";
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	if(username==null||username==""){
		msg="<font color='red'>*用户名不能为空！</font>";
	}else if(password==null||password==""){
		msg="<font color='red'>*密码不能为空！</font>";
	}
	document.getElementById("msg").innerHTML=msg;
}
</script>

<form name="admin" id="admin">

 <div class="login-wrapper">
        <div class="logo"></div>
        <div class="form-group"><span class="group-title">用户名：</span><input type="text" id="username" name="username" placeholder="请输入用户名"></div>
        <div class="form-group"><span class="group-title">密　码：</span><input type="password" id="password" name="password" placeholder="请输入密码"></div>
        <div class="error" id="msg"></div>
        <div class="btn-wrapper">
            <input type="button" value="登录" onclick="submitForm();" class="submit">
            <input type="reset" onclick="reset();" value="重置" class="reset">
        </div>
    </div>
 </form>

</body>
</html>
