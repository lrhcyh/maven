<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>

	<div class="easyui-panel" style="padding: 5px;">
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm1'">机构管理</a> <a href="#" class="easyui-menubutton" data-options="menu:'#mm2'">人员管理</a><a href="#" class="easyui-menubutton" data-options="menu:'#mm3'">职务管理</a>
	</div>
	<div id="mm1" style="width: 180px;">
		<div>单位（部门）信息维护</div>
		<div>单位（部门）信息查询</div>
		<div>单位（部门）人数统计</div>
		<div class="menu-sep"></div>
		<div>机构报表设计</div>
	</div>
	<div id="mm2" style="width: 180px;">
		<div><a href="javascript:void(0)"  onclick="personnelInfo();">员工信息维护</a></div>
		<div><a href="javascript:void(0)"  onclick="retire();">员工信息查询</a></div>
		<div>员工信息统计分析</div>
		<div>员工离退休预测</div>
		<div class="menu-sep"></div>
		<div>员工花名册设计</div>
		<div>员工登记表设计</div>
	</div>
	<div id="mm3" style="width: 180px;">
		<div><a href="javascript:void(0)"  onclick="postInfo();">职务信息维护</a></div>
	</div>
	<div  class="functio_bj2" style="padding-top: 1px;height: 110px; overflow:hidden;" >
		<ul>
			<li class="functio_hover7" ><a href="javascript:void(0)" onclick="organInfo()"></a>
			<div style="">部门信息</div></li>
			<li class="functio_hover6" ><a href="javascript:void(0)" onclick="personnelInfo()"></a>
			<div style="display:inline-block" >员工信息</div>
			</li>
			<li class="functio_hover6" ><a href="javascript:void(0)" onclick="postInfo();"></a>
			<div style="display:inline-block" >职务信息</div>
			</li>
		</ul>
	</div>
	<div id="win"></div>
	
	<script type="text/javascript">
	    //部门信息条状
		function organInfo() {
			//showWindow(basepath + '/auto/gotoPage.form?page=organInfo', 600, 500, '单位（部门）信息维护');
			$('#win').window({
			    width:1000,
			    height:600,
			    href : 'pages/base/organ/organInfo.jsp',
			    cache : false,
			    title:'单位（部门）信息维护',
			    fit:true,
			    modal:true,
			    onClose:function(){
			    	$('#win').window('destroy');
			    }
			});
		}
	    
	     //员工信息查询
	     function personnelInfo(){
	    	 $('#win').window({
				    width:1000,
				    height:600,
				    href : 'pages/base/personnel/personnel.jsp',
				    cache : false,
				    title:'员工信息维护',
				    fit:true,
				    modal:true,
				    onClose:function(){
				    	$('#win').window('destroy');
				    }
				});
	     }
	     //职务信息查询
	     function postInfo(){
	    	 $('#win').window({
				    width:1000,
				    height:600,
				    href : 'pages/base/post/postInfo.jsp',
				    cache : false,
				    title:'职务信息维护',
				    fit:true,
				    modal:true,
				    onClose:function(){
				    	$('#win').window('destroy');
				    }
				});
	     }
	     function provinceInfo(){
	    	 $('#dialog').dialog({title:"哈哈哈",iconCls:"icon-edit",buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function () {
	                	var temp = $('#province').tree('getSelected');	
	                	if($('#province').tree('isLeaf',temp.target)){
	           			var text=temp.text;
	           			$.ajax({
	           				type : 'POST',
	           				url : 'personnel/updateDepartMent.form',
	           				data : 'organCode=ryxx&column=empcode',
	           				dataType : 'json',
	           				async : false,
	           				success : function(msg) {
	           					
	           				},
	           				error : function() {
	           					alert('error')
	           				}
	           			});
	                	}
	                }
	            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#dialog').dialog('close');
	                }
	            }],width:300,height:500,"href":"pages/base/personnel/UpdateDepartmentTree.jsp?id=5"});
	    	 /* $('#win').window({
				    width:500,
				    height:300,
				    href : 'pages/base/personnel/UpdateDepartmentTree.jsp?id=5',
				    cache : false,
				    title:'员工信息维护',
				    fit:true,
				    modal:true
				}); */
	     }
	     
	     
	     
	     function retire(){
	    	 $('#dialog').dialog({title:"哈哈哈",iconCls:"icon-edit",buttons: [{
	                text: '确定',
	                iconCls: 'icon-ok',
	                handler: function () {
	                	var type=$('#state').combobox('getValue');	
	                	var time=$('#empDate').val();
	           			var text=temp.text;
	           			$.ajax({
	           				type : 'POST',
	           				url : 'personnel/updateUserType.form?type='+type+'&time='+time+'&id='+$('#empId').val(),
	           				dataType : 'json',
	           				async : false,
	           				success : function(msg) {
	           					
	           				},
	           				error : function() {
	           					alert('error')
	           				}
	           			});
	                }
	            }, {
	                text: '取消',
	                iconCls: 'icon-cancel',
	                handler: function () {
	                    $('#dialog').dialog('close');
	                }
	            }],width:400,height:300,"href":"pages/base/personnel/userPicture.jsp?empCode=221"});
	    	 /* $('#win').window({
				    width:500,
				    height:300,
				    href : 'pages/base/personnel/UpdateDepartmentTree.jsp?id=5',
				    cache : false,
				    title:'员工信息维护',
				    fit:true,
				    modal:true
				}); */
	     }
		
	</script>
<div id="dialog"></div>

</body>