
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form id="activityForm" action="${basePath}/advert/add.form" method="post" enctype="multipart/form-data" style="margin: 10px 30px; line-height: 35px;">

	<table cellpadding="0" cellspacing="0" class="formtable">
		<tr>
			<td>广告位id:</td>
			<td>
				<!-- <input type="text" id="place_id" name="place_id" class="easyui-textbox" disabled="disabled"   /> --> <input id="cc" class="easyui-combobox" name="place_id" data-options="valueField:'placeId',textField:'placeName',url:'advert/findPlaces.form',required:true,value:'--请选择--',validType:'validCombobox',editable:false">
			</td>
		</tr>
		<tr>
			<td>广告标题：</td>
			<td><input type="text" id="ad_title" name="ad_title" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>广告图片:</td>
			<td><input class="easyui-filebox" name="img1" data-options="prompt:'选择图片',required:true" style="width: 100%"></td>
		</tr>
		<tr>
			<td>广告链接url：</td>
			<td><input type="text" id="url" name="url" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>广告投放开始时间：</td>
			<td><input type="text" id="start_time" name="start_time" class="easyui-datetimebox" data-options="required:true,editable:false" /></td>
		</tr>
		<tr>
			<td>广告投放结束时间：</td>
			<td><input type="text" id="end_time" name="end_time" class="easyui-datetimebox" data-options="required:true,validType:'compareDate[\'#start_time\']',editable:false" /></td>
		</tr>


		<tr>
			<td colspan="2"><a href="#" class="easyui-linkbutton blue-btn" style="width: 60px; margin-right: 15px;" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" style="width: 60px;" onclick="closeWin()">取消</a></td>
		</tr>
	</table>

</form>

<script type="text/javascript">
		var basepath = '${basePath}';
		function save() {
			var win = $.messager.progress({
				title : '正在提交',
				msg : '正在添加,请稍等...'
			});

			$('#activityForm').form('submit', {
				
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (!isValid) {
						$.messager.progress('close'); // hide progress bar while the form is invalid
					}
					return isValid; // return false will stop the form submission
				},
				success : function(data) {

					$.messager.progress('close');
					closeWin();
					$('#dg').datagrid('reload');
				}
			});
				
				
		}
	</script>
<div></div>

