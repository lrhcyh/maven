<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<script type="text/javascript" src="js/inning/gift.js"></script>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west',split:true,border:false" style="width: 200px; padding: 10px">
			<!-- left -->
			<div id="activity" class="easyui-datalist" title="正在开展的活动" style="width: 100%; float: left;" lines="true" data-options="
            url: 'gift/activityList.form',
            method: 'get',
            onClickRow : function(rowIndex,rowData) {
            	var id = rowData.id;
            	$('#giftCoupon').css('display','block');
            	$('#detailDg').datagrid('resize');
            	$('#detailDg').datagrid({url:'gift/giftCouponList.form?activityId='+id});
            	$('#useDetailDg').datagrid('resize');
            	$('#useDetailDg').datagrid({url:'gift/couponUseDetailList.form?activityId='+id});
            	$('#phoneNumber').textbox('reset');
            }
            "></div>
			<!-- left -->
		</div>
		<div data-options="region:'center',border:false" style="padding: 10px;">
			<!-- right -->
			<div id="giftCoupon" style="display: none">
				<table id="detailDg" class="easyui-datagrid" style="width: 95%; height: auto" data-options="singleSelect:true,
																											url:'',
																											striped:true,
																											onClickRow : function(index,row) {
																											$('#phoneNumber').textbox('setValue','');
																												var id = row.id;
																								            	$('#useDetailDg').datagrid('reload',{ruleId:id});
																								            }
																											">
					<thead>
						<tr>
							<th data-options="field:'id',width:200,align:'center',hidden:true">ruleId</th>
							<th data-options="field:'couponTitle',width:200,align:'center'">券标题</th>
							<th data-options="field:'couponType',width:120,align:'center',formatter: function(value,row,index){
																									if (row.couponType=='1'){
																										return '现金券';
																									} else if (row.couponType=='2'){
																										return '加息券';
																									} else if (row.couponType=='3'){
																										return '提现券';
																									}}">券类型</th>
							<th data-options="field:'couponAmount',width:100,align:'center'">发放数量（张）</th>
							<th data-options="field:'leaveCupon',width:100,align:'center'">剩余数量（张）</th>
							<th data-options="field:'couponScene',width:150,align:'center',formatter: function(value,row,index){
																									if (value=='1'){
																										return '注册领券';
																									} else if(value=='2') {
																										return '实名认证领券';
																									} else if(value=='3') {
																										return '邀请好友领券';
																									} else if(value=='4') {
																										return '累计投资领券';
																									} else if(value=='5') {
																										return '特定专项活动页领券';
																									} else if(value=='9') {
																										return '不限';
																									}}">领券场景</th>
							<th data-options="field:'personLimits',width:100,align:'center'">个人限领（张）</th>
							<th data-options="field:'useChannel',width:100,align:'center',formatter: function(value,row,index){
																										if(value == 1) {
																											return 'pc';
																										} else if(value == 2) {
																											return 'app';
																										} else if(value == 9) {
																											return '全部';
																										}
																									}">使用渠道</th>
							<th
								data-options="field:'projectList',width:200,align:'center', formatter: function(value,row,index){
																										if(row.couponType == 1 || row.couponType ==2) {
																											return value;
																										} else {
																											return '不限';
																										}
																									}">可投项目</th>
							<th data-options="field:'weekendLimit',width:100,align:'center' ,formatter :function(value,row,index) {
																								if(value == 0) {
																									return '不限';
																								} else if(value == 1) {
																									return '仅周末可使用';
																								}
																							}">仅限周末使用</th>
							<th data-options="field:'validDays',width:80,align:'center'">有效期（天）</th>
							<th
								data-options="field:'condition',width:180,align:'center',formatter:function(value,row,index) {
																										var conditionStr = '';
																										var arr = [];
																										if(row.couponType==1) {
																											arr = value.split(',');
																											conditionStr = '满'+arr[0] +'，减' +arr[1];
																											if(arr[2]==0) {
																												conditionStr += '，不限年化';
																											} else if(arr[2]==1) {
																												conditionStr += '，限年化';
																											}
																										} else if(row.couponType==2) {
																											arr = value.split(',');
																											conditionStr = '满'+arr[0] +'，加息' +arr[1]+'%';
																											if(arr[2]==0) {
																												conditionStr += '，不限年化';
																											} else if(arr[2]==1) {
																												conditionStr += '，限年化';
																											}
																										}
																										return conditionStr;
																								}">使用条件</th>
						</tr>
					</thead>
				</table>
				<div style="margin: 20px 0; width: 95%; border: 1px solid #95b9e7; text-align: left">
					<table style="width: 100%; height: auto; margin: 10px 0;">
						<tr>
							<td width="260px" align="center">手机号码：<input id="phoneNumber" class="easyui-textbox" style="width: 150px; height: 32px"></td>
							<td width="100px"><a class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px; height: 32px" onclick="searchCouponUseDetail()">查询</a></td>
							<td width="100px" align="left"><a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width: 80px; height: 32px" onclick="sendCoupon()">发券</a></td>
							<td></td>
						</tr>
					</table>
				</div>
				<table id="useDetailDg" class="easyui-datagrid" style="width: 95%; height: auto;" data-options="singleSelect:true,url:'' ,pagination:true,
                 pageList: [5,10,20,50,80],
                 striped:true,
				pageSize:20">
					<thead>
						<tr>
							<th data-options="field:'acticityId',width:200,align:'center',hidden:true">acticityId</th>
							<th data-options="field:'id',width:120,align:'center'">券编号</th>
							<th data-options="field:'couponTitle',width:150,align:'center'">优惠券标题</th>
							<th data-options="field:'couponType',width:100,align:'center',formatter: function(value,row,index){
																									if (row.couponType=='1'){
																										return '现金券';
																									} else if (row.couponType=='2'){
																										return '加息券';
																									} else if (row.couponType=='3'){
																										return '提现券';
																									}}">券类型</th>
							<th data-options="field:'fullName',width:100,align:'center'">领取人</th>
							<th data-options="field:'phoneNumber',width:120,align:'center'">手机号码</th>
							<th data-options="field:'receiveDate',width:200,align:'center'">领取时间</th>
							<th data-options="field:'version',width:80,align:'center',formatter: function(value,row,index){
																									if (row.version=='0'){
																										return '未使用';
																									} else {
																										return '已使用';
																									}}">状态</th>
							<th data-options="field:'hahaha',width:100,align:'center',formatter: function(value,row,index){
																									return '<a href=javascript:removeCoupon(\''+row.id+'\',\''+row.version+'\',\''+row.activityId+'\') style=\'color: #2472b5\'>删除</a>';}">操作
							</th>
						</tr>
					</thead>
				</table>
				<!-- right -->
			</div>
		</div>
	</div>

	<script type="text/javascript">
	function removeCoupon(id,version,activityId) {
		if(version != 0) {
			$.messager.alert('提示','优惠券已使用，无法删除');
			return false;
		} 
		
		$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
			if (r) {
				ajaxUtils.post(basepath + "/gift/removeCoupon.form?couponCode=" + id, null, function(json) {// 回调函数
					var json = eval('(' + json + ')');
					if (json.result == 'success') {
		            	$('#detailDg').datagrid('load',{activityId:activityId});
		            	$('#useDetailDg').datagrid('load',{activityId:activityId});
		            	$('#phoneNumber').textbox('setValue','');
						
						$.messager.alert('消息', '操作成功', 'info');
					} else {
						$.messager.alert('错误', '操作失败', 'error');
					}
				});
			}
		});
		
	}
</script>


</body>





