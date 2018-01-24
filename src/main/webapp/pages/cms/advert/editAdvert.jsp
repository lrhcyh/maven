
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form id="activityForm" action="${basePath}/advert/editAdvert.form" method="post" enctype="multipart/form-data" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>广告图片:</td>
			<td><input class="easyui-filebox" name="img1" data-options="prompt:'选择图片'" style="width: 100%"></td>
		</tr>
		<tr>
			<td>广告编号:</td>
			<td><input id="ad_id" class="easyui-textbox" name="ad_id" data-options="" readonly="readonly"> <input id="status" name="status" type="hidden"></td>
		</tr>
		<tr>
			<td>广告位id:</td>
			<td><input id="cc" class="easyui-combobox" name="place_id" data-options="valueField:'placeId',textField:'placeName',url:'advert/findPlaces.form',required:true,validType:'validCombobox',editable:false"></td>
		</tr>
		<tr>
			<td>广告标题：</td>
			<td><input type="text" id="ad_title" name="ad_title" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>原图片预览:</td>
			<td><img id='oldPic' style="width: 300px; height: 100px;"></td>
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
			<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
		</tr>
	</table>

</form>

<script type="text/javascript">
	var basepath = '${basePath}';
	

	$(function() {
		var row = $('#dg').datagrid('getSelected');
		
		$('#activityForm').form('load', {
			ad_id : row.adId,
			place_id : row.placeId,
			ad_title : row.adTitle,
			url : row.url,
			status:row.status,
			start_time : row.startTime,
			end_time : row.endTime
		});
		$('#oldPic').attr('src','http://pic.hanxinbank.com/'+row.thumb);
		

	})
	function save() {

		$('#activityForm').form('submit', {
			success : function(data) {

				closeWin();
				$('#dg').datagrid('reload');
			}
		});
	}
</script>
<div></div>

