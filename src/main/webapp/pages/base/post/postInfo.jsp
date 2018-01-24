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
<script type="text/javascript" src="js/base/post.js"></script>
<style>
form table {
	background: #ccc;
	width: 100%;
}

form table td, table th {
	background: #fff;
	align: center;
	height: 30px;
	padding: 0px 10px;
}

table span {
	width: 120px;
}

.textbox-addon {
	width: 22px;
}
</style>


	<div class="easyui-layout" style="width: 100%; height: 100%;">
		 <div region="north" style="height: 30px" data-options="border:false">
			<div class="easyui-panel" data-options="fit:true">
				<a id="addBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a> 
				<a id="editBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
				<a id="removeBtn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a id="saveBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save',disabled:true" >保存</a>
				<a id="cancelBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo',disabled:true" >取消</a>
			</div> 
		</div>
	
		<div region="west" style="width: 220px; height: 100%; overflow: hidden; " data-options="collapsible:false">
		<input type="hidden" id="departId">
		<div class="easyui-layout" style="width: 100%; height: 100%">
			<div  data-options="region:'north',collapsible:false,border:false" >
	    <input class="easyui-combotree" id="cc" data-options="url:'personnel/findOrganTreeEndDept.form',required:true,onSelect:
	    			function(row) {
	                        $('#departId').val(row.id);
	                        var url='personnel/queryPostList.form?departmentId='+$('#departId').val();
	                        $('#dg').datagrid({
		                    	url : url
	                        });
							
					}" style="width:100%;height: 28px;">
			</div>
			<div region="center" style="overflow-x:hidden">
    		<table id="dg" class="easyui-datagrid" title="职位列表" style="width:100%;height:auto;" data-options="singleSelect:true,
                 url:'personnel/queryPostList.form?departmentId='+$('#departId').val(),
                 method:'get',
                 remoteSort:false,
                 multiSort:true,
                 rownumbers:true,
                 onSelect : function(index,row) {
                  	var code= row.postcode;
                  	loadCenterInfo(code)
					
					formAbled();
	            
	             },
		             onBeforeSelect : function(node) {
		             		var flag = leftCanClick();
							return flag;
					}
            ">
				<thead>
					<tr>
						<th data-options="field:'postname',width:180,align:'center'">职位名称</th>
					</tr>
				</thead>
			</table>
			</div>
				</div>
		</div>
		<div id="MenuListData"></div>
		<div id="content" region="center" style="left:230px;">
			<div class="easyui-layout" style="width: 100%; height: 100%">
				<div data-options="region:'north',title:'职位基本信息',collapsible:false,border:false" style="height: 195px;">
					<form id="dataForm"></form>
				</div>
				
				<div data-options="region:'center'">
					<div id="tb" style="height:30px;" >
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="addTb" onclick="append()">新增</a>
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  id="delTb" onclick="removeit()">删除</a>
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,disabled:true"  id="saveTb" onclick="accept()">保存</a>
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true,disabled:true"  id="cancelTb" onclick="cancel()">取消</a>
					</div>
					<div id="organTabs" style="width: 100%; ">
					</div>
				</div>
			</div>
		</div>
	</div>	
   <script>
	   $(function() {
		   //初始化中心区域下方的tabs
			$.ajax({
				type : 'POST',
				url : 'auto/querySingleForList.form',
				data : 'tableName=tableSet&where=[{moduleCode:6,tableState:1}]',
				dataType : 'json',
				async:false ,
				success : function(msg) {
					$('#organTabs').tabs({
				 		border:false,
				 		tabPosition:'bottom', 
				 		onSelect:function(title,index){
				 			loadnGridPage(title,index,null);
				 	    	
	                   }
	               });
					queryMenuList();
					 $.each(msg, function(i, n) {
						if(i==0) {
							$('#organTabs').tabs('add', {
								title : n.Definition,
								content:'<div id=\'pn'+i+'\' ></div>'
							})
						}else {
							$('#organTabs').tabs('add', {
								title : n.Definition,
								content:'<div  id=\'pn'+i+'\' ></div>',
								selected:false
							})
						}
					}); 
					 
					 
					
				}
			});
		   
		   
		   //初始化中心区域中心的table，产生一个对应的table，所有数据不允许输入
			$.ajax({
				type : 'POST',
				url : 'auto/madeSingleInit.form',
				data : 'tableName=post',
				dataType : 'text',
				async:false ,
				success : function(msg) {
					 $('#dataForm').html(msg);
					 $.parser.parse('#dataForm');
					 
					 tabType=0;
				}
			});
		   
		})
   </script>
   <div id="openDialog"></div>
</body>
</html>