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
    <title>员工信息查询</title>
   
  </head>
  <body>
  <style>
  	body{
	background: #fff;
}
.wpy-box{
	width: 500px;
	margin: 0 auto;
	border: 1px solid #ccc;
	overflow: hidden;
	padding: 10px;
	background: #f5f5f5;
}
.form-item{
	margin-bottom:10px;
	position: relative;
	overflow: hidden;
}
.form-item table{
	width: 100%;
	text-align:left;
	line-height: 30px;
}
.form-item table input{
	width: 90%
}
.wpy-left-content{
	width: 350px;
	float: left;
}
.wpy-left-content .bd{
	height: 200px;
	background: #fff;
	border: 1px solid #ccc;
	margin-top: 10px;
}
.wpy-left-content p{
	line-height: 30px;
	cursor: pointer;
}
.wpy-left-content .active{
	background: #f5f5f5;
}
.wpy-right-tool{
	margin-left: 360px;
}
.wpy-right-tool .title{
	margin-bottom: 20px;
}
.wpy-right-tool span{
	display: block;
	width: 100%;
	border: 1px solid #ccc;
	margin:5px 0;
	line-height: 28px;
	text-align: center;
	background: #fafafa;
	cursor: pointer;
}
.wpy-right-tool span:hover{
	background: #fff;
}
  </style>
  <script type="text/javascript" src="js/base/complexQuery.js"></script>
  <div class="easyui-layout" style="width: 100%; height: 100%;">
  		<div region="north" style="height: 30px" data-options="border:false">
  			<div class="easyui-panel" data-options="fit:true">
				<a id="query"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">查询</a> 
				<a id="queryAll"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">查询所有</a>
				<a id="batchDo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">批量处理</a>
			</div>
  		</div>
  		<div id="content" region="center" >
				<table  class="easyui-datagrid"  id = "tgContent" style="width: inherit;height:100%  "></table>
		</div>
  </div>
  <div id="complexBox" class="easyui-window" title="查询"  data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,modal:true" >
	  <div class="wpy-box" >
	  	<div class="form-item">
				<label>备用指标集&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-combobox" id="quota" style="width:300px;" data-options="editable:false,panelHeight:300,url:'personnel/findModuleCode.form?moduleCode=2',method:'get',valueField:'tablename',textField:'Definition',panelHeight:'auto'"></label>
	  	</div>
	  	<div class="form-item" style="border-top:1px solid #ccc; border-bottom:1px solid #ccc; padding:10px 0; margin:20px 0;">
				<p>预选条件</p>
				<table>
					<tr>
						<th width="15%">逻辑关系</th>
						<th width="27%">内容</th>
						<th width="18%">比较关系</th>
						<th width="40%">比较值</th>
					</tr>
					<tr>
						<td>
							<select class="easyui-combobox" id="logic" style="width:50px;">
								<option value="and">&nbsp;且&nbsp;</option>
								<option value="or">&nbsp;或&nbsp;</option>
							</select>
						</td>
						<td>
							<input class="easyui-combobox" id="context" style="width:120px;" data-options="editable:false,panelHeight:300,url:'',method:'get',valueField:'FieldName',textField:'Definition',onChange:function(newValue, oldValue){
 choicebox(newValue);
},onShowPanel : function(){

        var s=$(this).combobox('getData')
       if(s.length==0){  
       $(this).combobox('options').url= 'personnel/findmoduleField.form?tableName='+$('#quota').combo('getValue');
       $(this).combobox('reload');
    
  }}">
						</td>
						<td>
							<select class="easyui-combobox" id="relation"style="width:55px;" >
								<option value="=">&nbsp;=&nbsp;</option>
								<option value="<>">&nbsp;<>&nbsp;</option>
								<option value=">">&nbsp;>&nbsp;</option>
								<option value=">=">&nbsp; >= &nbsp;</option>
								<option value="<">&nbsp;< &nbsp;</option>
								<option value="<=">&nbsp; <= &nbsp;</option>
							</select>
						</td>
						<td id="text1">
							<input type="text" id="comparison" name="">
						</td>
					</tr>
				</table>
	  	</div>
	  	<div class="wpy-left-content">
		  	<p class="title">已形成条件</p>
		  	<div class="bd" id="wpy-ct">
		  		
		  	</div>
	  	</div>
	  	<div class="wpy-right-tool">
	  		<p class="title">操作</p>
	  		<span class="wpy-add" id="complexAdd">添加</span>
	  		<span class="wpy-del" id="complexDel"">删除</span>
	  		<span id="complexQuery">查询</span>
	  		<span id="queryOut">退出</span>
	  	</div>
	  </div>
  </div>
  <div id="openDialog"></div>
  <div id="batchUpdate" class="easyui-window" title="批量修改："  data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,width:500,height:300,modal:true">
  		<div style="padding:10px 20px 20px 20px">
		    <table cellpadding="5" style="width:447px;">
		    		<tr>
		    			<td width="20%" style="padding:10px 0">指标集:</td>
		    			<td style="padding:10px 5"><input class="easyui-combobox"  id="seqCollect" style="width:96%" data-options="editable:false,url:'personnel/findModuleCode.form?moduleCode=2',method:'get',valueField:'tablename',textField:'Definition'"></input></td>
		    		</tr>
		    		<tr>
		    			<td style="padding:10px 0">指标项:</td>
		    			<td style="padding:10px 0"><input class="easyui-combobox"  id="seq"  style="width:96%" data-options="value:'',editable:false,method:'get',valueField:'FieldName',textField:'Definition',onShowPanel : function(){
       							   $(this).combobox('setValue','');
							       $(this).combobox('options').url= 'personnel/findmoduleField.form?tableName='+$('#seqCollect').combo('getValue');
							       $(this).combobox('reload');
							    
							  }"></input>
						</td>
		    		</tr>
		    		<tr>
		    			<td style="padding:10px 0">替换成:</td>
		    			<td style="padding:10px 0"><input class="easyui-textbox" type="text"  id="newSeq"  style="width:96%"></input><input type="hidden" id="newSeqValue"/></td>
		    		</tr>
		    		<tr>
		    			<td style="padding:10px 0">参考项目:</td>
		    			<td style="padding:10px 0"><input class="easyui-combobox" id="seqRefer"  style="width:96%" data-options="editable:false,method:'get',valueField:'FieldName',textField:'Definition',onShowPanel : function(){
        					   $(this).combobox('setValue','');
						       $(this).combobox('options').url= 'personnel/findmoduleField.form?tableName='+$('#seqCollect').combo('getValue');
						       $(this).combobox('reload');
						    
						  },onSelect:function(){
						  		$('#newSeq').textbox('setValue',$(this).combobox('getText'))
						  		$('#newSeqValue').val($(this).combobox('getValue'));
						  }"></input>
						</td>
		    		</tr>
		    		
		    </table>
		    <div style="text-align:center;padding:10px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px;">确定</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="margin-left:10px;width:80px;">取消</a>
		    </div>
	    </div>
  </div>
</body>


</html>