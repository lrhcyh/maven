<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员信息统计分析</title>
</head>
<body>
<!-- 引入 echarts.js -->
	<script src="js/echarts.js"></script>
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
	height: 132px;
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
.wpy-right-tool>span{
	display: block;
	width: 100%;
	margin-bottom:10px;
}
.wpy-right-tool span:hover{
	background: #fff;
}
.tj-table{
	background:#ccc;
}
.tj-table td,.tj-table th{
	padding:0 10px;
	background:#fff;
}

.tj-table2{
	background:none;
}
.tj-table2 td,.tj-table2 th{
	background:none;
	padding:0;
}
.activeButton{
	background: #e2e2e2;
    color: #000000;
    border: 1px solid #ccc;
    filter: none;
}
  </style>
<script type="text/javascript" src="js/base/postStatistics.js"></script>

 <div class="easyui-layout" style="width: 100%; height: 100%;">
  		<div region="north" style="height: 30px" data-options="border:false">
  			<div class="easyui-panel" data-options="fit:true">
				<a id="oneStatis"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">一维统计图</a> 
				<a id="twoStatis"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">二维统计图</a>
				<!-- <a id="commonStatis" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-paper'">常用统计</a> -->
				<a id="excel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-link',disabled:true">Excel</a>
			</div>
  		</div>
  		<div id="content" region="center" >
  			<div class="easyui-layout" style="width: 100%; height: 100%;">
  				<div region="west" style="width: 200px" data-options="border:false">
  					<div class="easyui-tabs " style="width:200px;height:100%; " id="tabs">
					  		<div title="一维统计图" >
						  		<table class="easyui-datagrid" id="oneCartogram"  
									data-options="singleSelect:true,collapsible:true,url:'statistics/findCondition.form?moduleCode=6',method:'get',onSelect:
					    			function(index,row) {
					                        var accountcode = row.accountcode
											showHistoryPie(accountcode)
									},onLoadSuccess:
					    			function(data) {
					                        $('#oneCartogram').datagrid('selectRow', 0);
					                        selectType=1;
									}">
								  <thead>
									<tr>
										<th data-options="field:'accountname',align:'center',width:195">统计名称</th>
									</tr>
								   </thead>
						        </table>
					  		</div>
					  		<div title="二维统计图" >
					  			<table class="easyui-datagrid" id="twoCartogram" 
							data-options="singleSelect:true,collapsible:true,onSelect:
					    			function(index,row) {
					                        var xycode = row.xycode
											showPic2(xycode)
									},onLoadSuccess:
					    			function(data) {
					                        $('#twoCartogram').datagrid('selectRow', 0);
									}">
						  <thead>
							<tr>
								<th data-options="field:'xyname',align:'center',width:195">统计名称</th>
							</tr>
						   </thead>
					       </table>
					  		</div>
					  </div>
  				</div>
  				<div id="content2" region="center" >
					<div style="display:none;width:100%;height:100%" id="one" >
						<div>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showPie()" id="pieButton" style="width:80px;">饼型</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showBar()"  id="barButton" style="width:80px;">直方型</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showLine()"  id="lineButton" style="width:80px;">线型</a>
						</div>
						<div id="pie" style="display:none;width: 800px; float: center; height: 400px;"></div>
						<div id="bar" style="display:none;width: 800px; float: center; height: 400px"></div>
						<div id="line" style="display:none;width: 800px; float: center; height: 400px"></div>
					</div>
					<div style="display:none;" id="two">
						
					</div>
				</div>
			</div>
		</div>
  </div>
  <div id="twoWindow" class="easyui-window" title="二维统计表"  data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,width:500,height:250,modal:true">
  		<div style="padding:10px 20px 20px 20px">
		    <table cellpadding="5" style="width:400px;">
		    		<tr>
		    			<td width="20%" style="padding:10px 0">横向统计条件:</td>
		    			<td style="padding:10px 5"><input class="easyui-combobox"  id="vertical" style="width:96%" data-options="editable:false,url:'statistics/findCondition.form?moduleCode=6',method:'get',valueField:'accountcode',textField:'accountname'"></input></td>
		    		</tr>
		    		<tr>
		    			<td style="padding:10px 0">纵向统计条件:</td>
		    			<td style="padding:10px 5"><input class="easyui-combobox"  id="horizontal" style="width:96%" data-options="editable:false,url:'statistics/findCondition.form?moduleCode=6',method:'get',valueField:'accountcode',textField:'accountname'"></input></td>
		    		</tr>
		    		
		    </table>
		    <div style="text-align:center;padding:10px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" style="width:80px;">保存</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="statics()" style="margin-left:10px;width:80px;">统计</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="out()" style="margin-left:10px;width:80px;">退出</a>
		    </div>
	    </div>
  </div>
<!--   <div id="commonWindow" class="easyui-window" title="常用统计"  data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,width:470,height:400">
	  
	  <div style="text-align:center;padding:10px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="del()" style="width:80px;">删除</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cmStatics()" style="margin-left:20px;width:80px;">统计</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cmOut()" style="margin-left:20px;width:80px;">退出</a>
	  </div>
  </div> -->
  <div  class="easyui-window" id="commonTwoWindow" title="请输入统计名称" data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,width:300,heigth:120,modal:true">
   <p>&nbsp;</p>
   <p>名&nbsp;&nbsp;&nbsp; &nbsp;  称  ：<input id="twoName" type="text"   style="width:120px;height: 30px"></p>
   <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveTwo();" style="width:80px">保存</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#commonTwoWindow').window('close')" style="width:80px">取消</a>
			</div>
  </div>
   <div  class="easyui-window" id="commonOneWindow" title="请输入统计名称" data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,width:300,heigth:120,modal:true">
   <p>&nbsp;</p>
   <p>名&nbsp;&nbsp;&nbsp; &nbsp;  称  ：<input id="oneName" type="text"   style="width:120px;height: 30px"></p>
   <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveOne();" style="width:80px">保存</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#commonOneWindow').window('close')" style="width:80px">取消</a>
			</div>
  </div>
  
    <div id="statisPostBox" class="easyui-window" title="查询"  data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,modal:true" >
	  <div class="wpy-box" >
	  	<div class="form-item">
				<label>备用指标集&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-combobox" id="quota" style="width:300px;" data-options="editable:false,panelHeight:300,url:'personnel/findModuleCode.form?moduleCode=6',method:'get',valueField:'tablename',textField:'Definition',panelHeight:'auto'"></label>
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
		  	<div class="bd" id="wpy-ct" style="overflow-y:auto;">
		  		<table style="width:100%;line-height:30px;" id="wpy-aa" class="tj-table" cellspacing="1">
		  			<tr><th width="30%">序号</th><th>条件</th></tr>
		  			
		  		</table>
		  	</div>
	  	</div>
	  	<div class="wpy-right-tool">
	  		<p class="title">操作</p>
	  		<span class="easyui-linkbutton wpy-add" id="complexAdd">添加</span>
	  		<span class="easyui-linkbutton wpy-del" id="complexDel"">删除</span>
	  		<span id="complexQuery" class="easyui-linkbutton">统计</span>
	  		<span id="queryOut" class="easyui-linkbutton">退出</span>
	  		<span id="saveOneCondition" class="easyui-linkbutton" onclick="$('#commonOneWindow').window('open')">保存</span>
	  	</div>
	  	<div class="wpy-bottom">
	  		<table style="width:100%;line-height:30px;" class="tj-table tj-table2" cellspacing="0">
	  			<tr><td width="70%"><input type="text" id="importText" style="width:350px;"></td><td rowspan="2" style="vertical-align:top;background:#f5f5f5;">
					<div style="margin-top:60px;margin-left:15px;"><span class="easyui-linkbutton" onclick="openMessageWindow();" style="width:100%">统计图形条件确定</span></div>
					<table style="margin-top:10px;width:100%;">
						<tr><td width="50%" style="text-align:right;padding:0;"><span class="easyui-linkbutton sj-btn" style="width:50px;" onclick="$('#importText').val($('#importText').val()+'*')">*(且)</span></td><td width="50%" style="text-align:left;padding:0;"><span class="easyui-linkbutton sj-btn" onclick="$('#importText').val($('#importText').val()+'+')"  style="width:50px;">+(或)</span></td></tr>
						<tr><td style="text-align:right;padding:0;"><span class="easyui-linkbutton sj-btn"  style="width:50px;" onclick="$('#importText').val($('#importText').val()+'(')">(</span></td><td style="text-align:left;padding:0;"><span class="easyui-linkbutton sj-btn" onclick="$('#importText').val($('#importText').val()+')')"  style="width:50px;">)</span></td></tr>
					</table>
				</td></tr>
	  			<tr><td>
	  				<div id="texts" style="width:100%;height:200px;margin-top:10px; border:1px solid #ccc; background:#fff;">
	  				
	  				</div>
	  			</td></tr>
	  		</table>
	  	</div>
	  </div>
  </div>
  <div id="openDialog"></div>
    <div  class="easyui-window" id="messageWindow" title="请输入名称" data-options="collapsible:false,minimizable:false,maximizable:false,closed:true,width:300,heigth:120,modal:true">
   <p>&nbsp;</p>
   <p>名&nbsp;&nbsp;&nbsp; &nbsp;  称  ：<input id="twoText" type="text"   style="width:220px;height: 30px"></p>
   <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveTwoName();" style="width:80px">保存</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#messageWindow').window('close')" style="width:80px">取消</a>
			</div>
  </div>
  <input type="hidden" id="where"/>

</body>
</html>