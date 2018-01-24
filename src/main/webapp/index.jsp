<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.abcft.common.core.UserSession"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html class="panel-fit">
<head>
<META http-equiv="Content-Type" CONTENT="text/html;charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="jeasyui/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="jeasyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="jeasyui/demo/demo.css" />
<link rel="stylesheet" type="text/css" href="css/inning_backend.css" />
<link href="css/three.css" rel="stylesheet" type="text/css" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link id="dream" href="css/blue/blue.css" rel="stylesheet" type="text/css" />
<link href="css/analysis.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jeasyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jeasyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jeasyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jeasyui/treegrid-dnd.js"></script>
<script type="text/javascript" src="jeasyui/datagrid-dnd.js"></script>
<script type="text/javascript" src="js/backend.js"></script>
<script type="text/javascript" src="js/ajax.utils.js"></script>
<script type="text/javascript" src="js/form.utils.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<!-- <script type="text/javascript" src="js/inning/demo.js"></script>
<script type="text/javascript" src="js/inning/privilege.js"></script>
<script type="text/javascript" src="js/inning/workflow.js"></script>
<script type="text/javascript" src="js/inning/keyword.js"></script> -->

<script type="text/javascript" src="js/publicSystem.js"></script>
<script type="text/javascript" src="js/utl.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<style>
.panel-header {
	padding: 3px 5px !important;
}
</style>

<!-- cms start -->
<script type="text/javascript" src="js/cms/cms_model.js"></script>

<!-- cms end -->

<script type="text/javascript" src="js/inning/kefu.js"></script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<script type="text/javascript">
var basepath = '<%=basePath%>';


    
	$(document).ready( function() {
		$.ajaxSetup({  
	        contentType: "application/x-www-form-urlencoded;charset=utf-8",  
	        cache: false,  
	        complete: function(XHR, TS){ 
	            var resText = XHR.responseText;  
	            var sessionstatus = XHR.getResponseHeader("sessionstatus");  
	            var loginPath = XHR.getResponseHeader("loginPath");  
	            if (911 == XHR.status && "timeout" == sessionstatus) {  
	                $.messager.confirm('session过期', '您的会话已经过期，请重新登陆后继续操作！', function(confirm){  
	                    if (confirm) {  
	                        window.location.replace(loginPath);  
	                    }  
	                });  
	                return;  
	            }  
	        }  
	    });  
		
		<%if (request.getSession() == null) {%>
			window.location.href= basepath + "login.jsp";
			return;
		<%}
			UserSession user = (UserSession) request.getSession().getAttribute("user");
			//String sessionid = request.getSession().getId();
			if (user == null) {%>
			window.location.href= basepath + "login.jsp";
			return;
		<%}%>
		var myDate = new Date();
		
		var str = "";  
		var week = new Date().getDay();  
		if (week == 0) {  
		        str = "日";  
		} else if (week == 1) {  
		        str = "一";  
		} else if (week == 2) {  
		        str = "二";  
		} else if (week == 3) {  
		        str = "三";  
		} else if (week == 4) {  
		        str = "四";  
		} else if (week == 5) {  
		        str = "五";  
		} else if (week == 6) {  
		        str = "六";  
		}  
		
		document.getElementById("nowTime").innerHTML = myDate.getFullYear()+'/'+(myDate.getMonth()+1)+"/"+myDate.getDate()+" 星期" +str + " " + myDate.getHours() + ":"+myDate.getMinutes();
		
		var skin = document.getElementById("skin");
		 //皮肤列表
		skin.onmouseover = function(){
			var dream_1 = document.getElementById("dream_1");
			dream_1.className="dream_displayblock";
			dream_1.style.display = "";
		}
		var dream_1 = document.getElementById("dream_1");
		
		dream_1.onmouseover = function(){

			dream_1.style.display = "";
			this.onmouseout = function(){
			 	dream_1.style.display = "none";
			}

		} 
	});
		
		$('.panel-title ').css('font-size','50px');
</script>
<style>
html {
	/*overflow-x: hidden;*/
	
}

#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	color: #666;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 90px;
}

.tablepb tr td {
	border: 1px solid #000;
}

.centerOverFlow {
	overflow: hidden;
}
</style>
</head>
<body class="easyui-layout layout">
	<!-- <div class="easyui-layout"> -->
	<div class="top" data-options="region:'north',border:false" style="height: 110px; overflow: hidden;">
		<div class="functio_bj" style="padding-top: 1px">
			<ul>
				<!--  <li class="functio_hover8"><a href="javascript:void(0)" onclick="exitOperater();"></a>
						<div style="">注销</div></li>-->


				<c:forEach items="${moduleList}" var="module" varStatus="status">

					<c:choose>

						<c:when test="${fn:contains(modulesAvail,module.id)}">
							<li class="functio_hover${ status.index + 1}"><a href="javascript:void(0)" onclick="findModule(${module.id}, '${module.functionName}')"></a>
								<div>${module.functionName}</div></li>
						</c:when>

						<c:otherwise>
							<li class="functio_hover_disable${ status.index + 1}">
								<div>${module.functionName}</div>
							</li>
						</c:otherwise>

					</c:choose>

				</c:forEach>


			</ul>

		</div>

		<div class="InfoBar" id="infoHeight" style="height: 40px; width: 100%">
			<div class="InfoBar_1">
				<span class="sun">&nbsp;</span> <span style="height: 18px; line-height: 18px;"><span> <%
 	if (user != null) {
 %><%=user.getUserName()%> <%
 	}
 %>欢迎使用abc后台管理系统! | 本次登录时间：
				</span><span id="nowTime"></span> </span>
			</div>
			<div class="InfoBar_2">
				<div class="InfoBar_input1">
					<span><a href="javascript:void(0)" onclick="exitOperater();"><font color="#FFFFFF">注销</font></a></span>
				</div>
				<div class="InfoBar_input2">
					<span><a href="javascript:void(0)" onclick="changePassword();"><font color="#FFFFFF">修改密码</font></a></span>
				</div>
				<div class="InfoBar_input3">
					<span>换肤</span><span><a href="#"><img id="skin" src="lanimg/int2a.png" border="0" /></a></span>
				</div>
			</div>
		</div>
		<div id="dream_1" style="display: none; width: 80px; height: 50px;">
			<div style="width: 67px; height: 48px; border: 1px solid #a0a0a0; padding-left: 7px; background: #ffffff">
				<!-- <div id="redDream" style="width:50px;height:20px;line-height:20px;"><a onclick="red()" href="#" style=""><img src="images/redSkin.jpg" /><span style="padding: 0 0 0 7px">红色</span></a></div> -->
				<div id="blueDream" style="width: 50px; height: 20px; line-height: 20px;">
					<a onclick="blue()" href="#" style=""><img src="images/blueSkin.jpg" /><span style="padding: 0 0 0 7px">蓝色</span></a>
				</div>
			</div>
		</div>
	</div>

	<div region="west" style="width: 180px; overflow: auto;" class="contenter_left">
		<div class="contenter_title" id="contentLeftHeight" style="width: 175px;">
			<div class="shortcutMenuparn">
				<h2>功能类目</h2>
			</div>
			<ul id="tt"></ul>

		</div>
	</div>
	<!-- <div region="south" border="false" style="height: 0px; background: #A9FACD; padding: 10px;">
			
		</div> -->
	<div region="center" id="centerArea" class="easyui-panel" data-options="border:false">
		<div style="position: releative; background: url(./images/2.png) 0 0 repeat-x #bbe2f6; height: 100%; width: 100%">
			<img alt=""   src="./images/1.png"  style="position: absolute; left: 50%; top: 0; margin-left: -511px;display:none" id="timeImg" />
		</div>
	</div>
	<span id="windowParent"><div id="myWindow"></div>
		<div id="mySubWindow"></div> </span>
</body>
<script type="text/javascript">



//更换皮肤
function red(){
	var dream = document.getElementById("dream");
	//alert(dreamRight);
	dream.href="css/red/red.css";
	dream_1.style.display="none";
	
}
function blue(){
	var dream = document.getElementById("dream");
	//alert(dream);
	dream.href="css/blue/blue.css";
	dream_1.style.display="none";
}
function gotoDemo(){
	$('#centerArea').panel({
	    href:basepath+"demo/selfQueryList.form",
	    onLoad:function(){
	    }
	});
}

function gotoPrivilege() {
	$('#centerArea').panel({
	    href:basepath+"pages/privilege/privilege.jsp",
	    onLoad:function(){
	    }
	});
}





function findModule(id, name){
	    
		$('#tt').tree({
	    url:basepath+"privilege/findModuleById.form?id="+id,
	    onSelect:function(node){
	    	if(node.url) {
	    		var obj = $("#openDialog");
	    		if ((obj!=null || obj!="undefined") && obj.hasClass("panel-body")){
	    			obj.dialog("close");
	    		}
	    		//$('#centerArea').hide();
	    		$('#centerArea').html("");
	    		 
	    		$('#centerArea').panel({
		    	    href:basepath+node.url,
		    	    border: false,
		    	    onLoad:function(){
		    	    }
		    	});	
	    	}
		},
		/* formatter:function(node){
			var num = "";
			$.ajax({
		        url : "oa/getToDoWorkNum.form",
		        dataType : "json",
		        type : "POST",
		        async : false,
		        success: function(msg){
		        	num = msg;
		        }
		    });
			return node.text == '我的工作'? node.text + '<span style="color:red">&nbsp;('+num+')&nbsp;</span>' : node.text;
		}, */
		onLoadSuccess: function(row, data){
			if(name == '协同办公') {
				 var id = data[0].children[1].children[0].id;
			        var node = $('#tt').tree('find', id);
			        $('#tt').tree('select', node.target);
			}
			if(name == '人事管理') {
				$('#centerArea').css({overflow:'hidden'});
			} else {
				$('#centerArea').css({overflow:'auto'});
			}
		}
	});
	
}
window.setTimeout(function(){
	$('#timeImg').show();
},200);

</script>

</html>
