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
<title>省市区树</title>
</head>
<body>
<div class="easyui-panel">
		<table  id="province" class="easyui-treegrid" style="border:0;width: 100%;"  data-options="
		    
				method: 'get',
				idField: 'id',
				treeField: 'text',
				remoteSort:false,
				 multiSort:true,
				lines: true,
				onContextMenu: function(e,node){
					e.preventDefault();
					$(this).treegrid('select',node.target);
					$('#mm').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
				},
				onDblClickRow:function(node){
				    findChildren(node);
				},
				onBeforeExpand:function(node){
                  $.ajax({
            		url:'personnel/findTree.form?type=1&id='+node.id,
            		type:'post',
               		async: false,
               		dataType:'text',
	                success:function(data){
	                	var jsonData = $.parseJSON(data);
	                	
	                	var t = $('#province');
	                	var test = t.treegrid('getData',node.target); 
	                	if(test.children){
	                		if(test.children.length>0){
	                			return ;
	                		}
	                	}
	                	
	                	t.treegrid('append', {
		                parent: node.id,
		                data: jsonData
	                    });
	                	
						
						
	            		
	                  }
                 });
					
				}">
		    <thead>
			<tr>
				<th data-options="field:'text',width:'50%',align:'left',sortable:'true'"   >城市名称</th>
				<th data-options="field:'ma',width:'50%',align:'center',sortable:'true'"   >城市编码</th>
			</tr>
		 </thead>
		</table >
	</div>
	<script type="text/javascript">
	 $(function(){
			
			var url = "personnel/findTree.form?type=1&id=1";
			$.ajax({
				url:url,
				type:"post",
				//data:data,
				dataType:"json",
				success:function(jsonData){
					//var t = "["+jsonData.data.toString()+"]";
					var t = jsonData;
					$("#province").treegrid("loadData",t);
				}
			})
			
		})	 
	
		
		function findChildren(temp){
		 
			 if (dialogType=="F101"){
				 $("#F101").textbox("setValue",temp.text);
			 }else if (dialogType=="F174"){
				 $("#F174").textbox("setValue",temp.text);
			 }
			 $('#openDialog').dialog("close");
	 }
	
	</script>
</body>
</html>