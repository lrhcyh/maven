
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.io.*,java.util.*"%>


<form id="activityForm" action="${basePath}/couponactivity/add.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>是否支持多劵</td>
			<td><select id="IF_MULTICOUPON" name="IF_MULTICOUPON">
					<option value="0">单类劵</option>
					<option value="1">多类劵</option>
			</select></td>
		</tr>



		<tr>
			<td>活动名称：</td>
			<td><input type="text" id="activityName" name="activityName" class="easyui-validatebox" style="width: 128px" required="true" />
		</tr>
		<tr>
			<td>活动描述：</td>
			<td><input type="text" id="activityDes" name="activityDes" class="easyui-validatebox" style="width: 128px" required="true" />
		</tr>


		<tr>
			<td>pc连接地址：</td>
			<td><input type="text" id="pcPage" name="pcPage" class="easyui-validatebox" style="width: 128px" required="true" /></td>
		</tr>
		<tr>
			<td>app连接地址：</td>

			<td><input type="text" id="appPage" name="appPage" class="easyui-validatebox" style="width: 128px" required="true" />
		</tr>

		<!-- <tr>
			<td>责任人：</td>
			
				<td><input type="text" id="createId"  name="createId" class="easyui-validatebox" style="width: 128px" required="true" />
			
		</tr> -->

		<tr>
			<td>开始时间：<input id="beginDate" name="beginDate" class="easyui-datebox"></input></td>
		</tr>
		<tr>
			<td>结束时间：<input id="endDate" name="endDate" class="easyui-datebox"></input></td>
		</tr>


		<tr>
			<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin()">取消</a></td>
		</tr>
	</table>
	<script type="text/javascript">

	var basepath = '${basePath}';
    function save(){
  
    
    $('#activityForm').form('submit', {
        success: function(data){
        	
    	closeWin();
    	$('#dg').datagrid('reload');
        }
    });
    }

    

</script>
</form>
<div></div>

