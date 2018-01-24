
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form id="activityForm" action="${basePath}/advert/addPlace.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>所属前端编号:</td>
			<td><input type="text" id="moduleid" name="moduleid" class="easyui-textbox" disabled="disabled" />
		</tr>
		<tr>
			<td>广告位名：</td>
			<td><input type="text" id="placeName" name="placeName" class="easyui-validatebox" data-options="required:true" />
		</tr>
		<tr>
			<td>广告位编码：</td>
			<td><input type="text" id="placeCode" name="placeCode" class="easyui-validatebox" data-options="required:true" />
		</tr>
		<tr>
			<td>广告位高度：</td>
			<td><input type="text" id="placeHeight" name="placeHeight" class="easyui-numberbox" data-options="required:true" />
		</tr>
		<tr>
			<td>广告位宽度：</td>
			<td><input type="text" id="placeWidth" name="placeWidth" class="easyui-numberbox" data-options="required:true" />
		</tr>


		<tr>
			<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
		</tr>
	</table>

</form>

<script type="text/javascript">
		var basepath = '${basePath}';
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

