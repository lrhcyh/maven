
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="activityForm" action="${basePath}companyManagerInfo/importExport.form" method="post" enctype="multipart/form-data" style="margin: 10px 30px; line-height: 35px;">
  	 <input type="file" name="file" iconCls="icon-large-chart">  
	    
    <br/><br/>
    <div colspan="2">
	    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="upload();">上传</a> 
	    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a>
    </div>
</form>

<script type="text/javascript">
	var basepath = '${basePath}';
	
	function upload() {
		var win = $.messager.progress({
			title : '正在上传',
			msg : '正在上传,请稍等...'
		});
		
		$('#activityForm').form('submit', {
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close');
				}
				return isValid;
			},
			success : function(data) {
				$.messager.progress('close');
				closeWin();
				
				var json = eval('(' + data + ')');
				// 第一次手动加载
				$("#dg").datagrid("loadData", json.rows.slice(0, 1000));
				
				var pager = $("#dg").datagrid("getPager");  
	            pager.pagination({  
	                total: json.total,  
	                onSelectPage:function (pageNo, pageSize) {  
	                    var start = (pageNo - 1) * pageSize;  
	                    var end = start + pageSize;  
	                    $("#dg").datagrid("loadData", json.rows.slice(start, end));  
	                    pager.pagination('refresh', {  
	                        total: json.total,  
	                        pageNumber: pageNo  
	                    });  
	                }  
	            });
			}
		});
	}
</script>
<div></div>