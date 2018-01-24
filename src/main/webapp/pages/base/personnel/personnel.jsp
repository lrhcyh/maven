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
<script type="text/javascript" src="js/base/personal.js"></script>
<link rel="stylesheet" href="themes/default/default.css" />
<link rel="stylesheet" href="plugins/code/prettify.css" />
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script charset="utf-8" src="editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="plugins/code/prettify.js"></script>
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
				<a id="personBtn"  class="easyui-menubutton" data-options="menu:'#searchMenu'">人员库</a> 
				<a id="addBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a> 
				<a id="editBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
				<a id="removeBtn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a id="saveBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save',disabled:true" >保存</a>
				<a id="cancelBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo',disabled:true" >取消</a>
				<a id="autoBtn"  class="easyui-menubutton" data-options="menu:'#autoMenu'">自动处理</a> 
				<a id="picBtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">照片显示</a> 
				<a id="pChangeBtn"  class="easyui-menubutton" data-options="menu:'#pChangeMenu'">人员变动</a> 
			</div>
			<div id="searchMenu" style="width: 150px;">
				<div id="quickSearch">快速定位</div>
				<div id="nameOrder">姓名排序</div>
				<div class="menu-sep"></div>
				<div id="normal" >正式员工</div>
				<div id="employ" >聘用员工</div>
				<div id="fire" >离职员工</div>
				<div id="in" >内退员工</div>
				<div id="inFire" >离退休员工</div>
				<div id="elseReduce" >其它减员员工</div>
			</div>
			<div id="autoMenu" style="width: 150px;">
				<div id="ageCal">年龄计算</div>
				<div id="workCal">工龄计算</div>
				<div class="menu-sep"></div>
				<div id="birthTips" >今日生日提示</div>
				<div id="inFireTips" >今日离退休提示</div>
			</div>
			<div id="pChangeMenu" style="width: 150px;">
				<div id="deptChange">部门调整</div>
				<div id="inFireDo">离退休处理</div>
				<div id="reduceDo" >减员处理</div>
			</div>
		</div> 
	    <input id="empType"   type="hidden"/>
	     <input id="empOrder"  type="hidden" value="no"/>
		<div region="west" style="width: 200px; height: 100%; " data-options="collapsible:false">
		
			<input type="hidden" id="orgId">
			<input type="hidden" id="deptId">
			<input type="hidden" id="groupId">
			
			<input type="hidden" id="orgName">
			<input type="hidden" id="deptName">
			<input type="hidden" id="groupName">
		
			<input type="hidden" id="departId">
			<div class="easyui-layout" style="width: 100%; height: 100%">
			<div  data-options="region:'north',collapsible:false,border:false" >
		    <input class="easyui-combotree" id="cc" data-options="url:'personnel/findOrganTree.form',required:true,onSelect:
		    			function(row) {
		                        $('#departId').val(row.id);
		                        loadOrgInfo(row.id);
		                        var url='personnel/queryDepartmentList.form?departmentId='+$('#departId').val();
		                        $('#dg').datagrid({
			                    	url : url
		                        });
								
						} ,value:'请选择用户所在部门'" style="width:100%;height: 30px;">
				</div>
				<div region="center" style="overflow-x:hidden">
	    		<table id="dg" class="easyui-datagrid" title="员工列表" style="width:100%;height:auto;" data-options="singleSelect:true,
	                 url:'personnel/queryDepartmentList.form?departmentId='+$('#departId').val(),
	                 method:'get',
	                 remoteSort:false,
	                 multiSort:true,
	                 rownumbers:true,
	                 onSelect : function(index,row) {
	                  	var code= row.EmpCode;
	                  	loadCenterInfo(code)
	                  	  
						//formAbled();
		            
		             },
		             onBeforeSelect : function(node) {
		             		var flag = leftCanClick();
							return flag;
					}
	            ">
					<thead>
						<tr>
							<th data-options="field:'EmpName',width:80,align:'center'">员工姓名</th>
							<th data-options="field:'sex',width:80,align:'center'">性别</th>
						</tr>
					</thead>
				</table>
				</div>
				</div>
		</div>
		<div id="MenuListData"></div>
		<div id="content" region="center" style="left:230px;">
			<div class="easyui-layout" style="width: 100%; height: 100%">
				<div data-options="region:'north',title:'员工基本信息',collapsible:false,border:false" style="height: 43%;">
					<form id="dataForm"></form>
				</div>
				
				<div data-options="region:'center'">
					 <div id="tb" style="height:30px;" >
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="addTb" onclick="append()">新增</a>
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  id="delTb" onclick="removeit()">删除</a>
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,disabled:true"  id="saveTb" onclick="accept()">保存</a>
						<a  class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true,disabled:true"  id="cancelTb" onclick="cancel()">取消</a>
					</div>
					<div id="organTabs" style="width:100%;">
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
				data : 'tableName=tableSet&where=[{moduleCode:2,tableState:1}]',
				dataType : 'json',
				async:false ,
				success : function(msg) {
					$(".use").remove();
					
					$('#organTabs').tabs({
				 		border:false,
				 		tabPosition:'bottom', 
				 		onSelect:function(title,index){
				 			loadnGridPage(title,index,null);
				 	    	
	                   }
	               });
					/* var wHeight = window.document.body.clientHeight;
					var height = (wHeight-140)*57/100-62; */
					queryMenuList();
					
					 $.each(msg, function(i, n) {
						if(i==0) {
							$('#organTabs').tabs('add', {
								title : n.Definition,
								//content:'<div id=\'pn'+i+'\' ><table  class="easyui-datagrid"  id = "gridTable'+i+'" style="width: inherit;height:'+height+'px  "></table></div>'
								content:'<div id=\'pn'+i+'\' ></div>'
							})
						}else {
							$('#organTabs').tabs('add', {
								title : n.Definition,
								//content:'<div  id=\'pn'+i+'\' ><table  class="easyui-datagrid"  id = "gridTable'+i+'" style="width: inherit;height:'+height+'px  "></table></div>',
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
				data : 'tableName=ryxx',
				dataType : 'text',
				async:false ,
				success : function(msg) {
					
					 $('#dataForm').html(msg);
					 //$.parser.parse('#dataForm');
					 tabType=0;
					
				}
			});
		   
			

		   
		})
		
   </script>
   <div id="openDialog"></div>
   <div  class="easyui-window" id="contextHtml" title="背景调查" data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,modal:true">
		  <textarea name="content1" id="content1"  style="width:700px;height:400px;visibility:hidden;"></textarea>
		  <div style="display:none;" id="contextBar">
			  <a class="easyui-linkbutton" style="cursor:pointer;margin-left: 214px;" data-options="iconCls:'icon-ok'"  onclick="saveContext()" style="width:80px">保存</a>
			  <a class="easyui-linkbutton" style="cursor:pointer;margin-left: 119px;" data-options="iconCls:'icon-cancel'"  onclick="cancelContext()" style="width:80px">取消</a>
		  </div>
  </div> 

	
</body>
</html>