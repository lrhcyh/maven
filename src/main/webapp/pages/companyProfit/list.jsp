<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

</head>
<body>
  
<script type="text/javascript">
	var basepath = '<%=basePath%>';
	
	function searchList() {
		var stockCode = $('#stockCode').textbox('getValue');
		
		$("#dg").datagrid('load', {
			stockCode : stockCode,
		});
	}
	
	function searchListRepeat() {
		$("#dg").datagrid('load', {
			keyword: 'distinct'
		});
	}
    
	var toolbar = [ {
		text : '添加利润',
		iconCls : 'icon-add',
		handler : function() {
			showWindow(basepath + '/companyProfit/gotoPage.form?page=add', 600, 500, '添加利润');
		}
	}, '-', {
		text : '修改利润',
		iconCls : 'icon-edit',
		handler : function() {
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录修改', 'info');
				return false;
			}
			showWindow(basepath + '/companyProfit/gotoPage.form?page=edit', 600, 500, '修改利润');
		}
	}, '-', {
		text : '删除利润',
		iconCls : 'icon-cut',
		handler : function() {
			var node = $('#dg').treegrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录进行操作', 'info');
				return false;
			}
			$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "/companyProfit/remove.form?profit_sheet_id=" + node.id, null, function(json) {
						var json = eval('(' + json + ')');
						if (json.result == 'success') {
							$('#dg').datagrid('load');
							$.messager.alert('消息', '操作成功', 'info');
						} else {
							$.messager.alert('错误', '操作失败', 'error');
						}
					});
				}
			});
		}
	}, '-', {
		text : '保存公司利润信息',
		iconCls : 'icon-save',
		handler : function() {
			// 将修改的数据存放到网页缓存中
			$('#dg').datagrid('endEdit', editIndex);
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录保存', 'info');
				return false;
			}
			$.ajax({
				type : "post",
				dataType : "json",
				url : basepath + "companyProfit/saveCompanyProfit.form",
				data : {
					profit_sheet_id : node.profit_sheet_id,
					stock_code : node.stock_code,
					publish_date : node.publish_date,
					end_date : node.end_date,
					account_date : node.account_date,
					report_period : node.report_period,
					overall_income : node.overall_income,
					main_income : node.main_income,
					overall_cost : node.overall_cost,
					main_cost : node.main_cost,
					tax : node.tax,
					sale_cost : node.sale_cost,
					manage_cost : node.manage_cost,
					other_cost : node.other_cost,
					fin_cost : node.fin_cost,
					asset_loss : node.asset_loss,
					plus_change_income : node.plus_change_income,
					invest_income : node.invest_income,
					relate_invest_income : node.relate_invest_income,
					gain_loss_income : node.gain_loss_income,
					other_subject : node.other_subject,
					overall_profit : node.overall_profit,
					plus_subsidy_income : node.plus_subsidy_income,
					addition_income : node.addition_income,
					reduce_addition_cost : node.reduce_addition_cost,
					asset_dispose_loss : node.asset_dispose_loss,
					plus_profit_subject : node.plus_profit_subject,
					profit_total : node.profit_total,
					reduce_tax : node.reduce_tax,
					plus_netprofit_subject : node.plus_netprofit_subject,
					netprofit : node.netprofit,
					parent_netprofit : node.parent_netprofit,
					minority_loss : node.minority_loss,
					perstock_income : node.perstock_income,
					basic_perstock_income : node.basic_perstock_income,
					reduce_perstock_income : node.reduce_perstock_income,
					other_all_income : node.other_all_income,
					all_income_total : node.all_income_total,
					income_for_parent : node.income_for_parent,
					income_for_holder : node.income_for_holder,
					interest_income : node.interest_income,
					earn_insurance : node.earn_insurance,
					commission_income : node.commission_income,
					interest_cost : node.interest_cost,
					commission_cost : node.commission_cost,
					canel_insurance_money : node.canel_insurance_money,
					pay_netprofit : node.pay_netprofit,
					insurance_netprofit : node.insurance_netprofit,
					insurance_cost : node.insurance_cost,
					reduce_insurance_cost : node.reduce_insurance_cost,
					interest_dispose : node.interest_dispose,
					push_flag : node.push_flag
				},
				success : function(data) {
					editIndex = undefined;
					$('#dg').datagrid('load');
				}
			})
		}
	}, '-', {
		text : '导入Excel',
		iconCls : 'icon-large-chart',
		handler : function() {
			showWindow(basepath + 'companyProfit/gotoPage.form?page=import', 350, 200, '批量导入公司利润信息');
		}
	}, '-', {
		text : '审核数据',
		iconCls : 'icon-ok',
		handler : function() {
			$.messager.confirm("提示", "您确定要提交数据吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "companyProfit/importCompanyProfit.form", null, function(data) {
						var json = eval('(' + data + ')');
						if (json.result == 'success') {
							$('#dg').datagrid('load');
							$.messager.alert('消息', '操作成功', 'info');
						} else {
							$('#dg').datagrid('load');
							$.messager.alert('错误', '操作失败', 'error');
						}
					});
				}
			});
		}
	}];
	
	function editRoleRight(roleId) {
    	showWindow(basepath + '/users/gotoPage.form?page=editRoleRight&roleId='+roleId, 600, 500, '修改角色权限');
    }
</script>

<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
	<div data-options="region:'north',title:'操作',overflow:'show',collapsible:false" style="height: 85px">
	<form id="searchForm" method="post">
		<table align="center">
			<tr align="center">
					<td align="right" width="80px" margin="10px">股票代码</td>
					<td align="right" width="60px" margin="10px"><input id="stockCode" name="stockCode" class="easyui-textbox" data-options=""></td>
					
					<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
					<td width="100px" height="50px"><a id="btn" onclick="searchListRepeat()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">重复数据</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
		<table id="dg" class="easyui-datagrid"  style="width: inherit; height: 100%" data-options="singleSelect:true,
						  url:'companyProfit/findList.form',
						  toolbar: toolbar,
						  method:'POST', 
						  pageList: [200,500,1000,2000,5000],
						  pagination:true,checkbox:true,onClickCell: onClickCell
						  ">
			<thead>
				<tr>
                    <th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'stock_code',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}">股票代码</th>
					<th data-options="field:'publish_date',width:100,align:'center',editor:{type:'datebox',options:{required:true}}">公告日期</th>
					<th data-options="field:'end_date',width:100,align:'center',editor:{type:'datebox',options:{required:false}}">截止日期</th>
					<th data-options="field:'account_date',width:100,align:'center',editor:{type:'datebox',options:{required:false}}">报告年度</th>
					<th data-options="field:'report_period',width:50,align:'center',editor:{type:'validatebox',options:{required:false}}">报告期</th>
					<th data-options="field:'overall_income',width:120,align:'center',editor:{type:'validatebox',options:{required:false}}">营业总收入</th>
					<th data-options="field:'main_income',width:120,align:'center',editor:{type:'validatebox',options:{required:false}}">营业收入</th>
					<th data-options="field:'overall_cost',width:120,align:'center',editor:{type:'validatebox',options:{required:false}}">营业总成本</th>
					<th data-options="field:'main_cost',width:120,align:'center',editor:{type:'validatebox',options:{required:false}}">营业成本</th>
					<th data-options="field:'tax',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">营业税金及附加</th>
					<th data-options="field:'sale_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">销售费用</th>
					<th data-options="field:'manage_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">管理费用</th>
					<th data-options="field:'other_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">勘探费用</th>
					<th data-options="field:'fin_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">财务费用</th>
					<th data-options="field:'asset_loss',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">资产减值损失</th>
					<th data-options="field:'plus_change_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">公允价值变动净收益</th>
					<th data-options="field:'invest_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">投资收益</th>
					<th data-options="field:'relate_invest_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">对联营企业和合营企业的投资收益</th>
					<th data-options="field:'gain_loss_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">汇兑收益</th>
					<th data-options="field:'other_subject',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">影响营业利润的其他科目</th>
					<th data-options="field:'overall_profit',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">营业利润</th>
					<th data-options="field:'plus_subsidy_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">补贴收入</th>
					<th data-options="field:'addition_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">营业外收入</th>
					<th data-options="field:'reduce_addition_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">营业外支出</th>
					<th data-options="field:'asset_dispose_loss',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">非流动资产处置损失</th>
					<th data-options="field:'plus_profit_subject',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">影响利润总额的其他科目</th>
					<th data-options="field:'profit_total',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">利润总额</th>
					<th data-options="field:'reduce_tax',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">所得税</th>
					<th data-options="field:'plus_netprofit_subject',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">影响净利润的其他科目</th>
					<th data-options="field:'netprofit',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">净利润</th>
					<th data-options="field:'parent_netprofit',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">归属于母公司所有者的净利润</th>
					<th data-options="field:'minority_loss',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">少数股东损益</th>
					<th data-options="field:'perstock_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">每股收益</th>
					<th data-options="field:'basic_perstock_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">基本每股收益</th>
					<th data-options="field:'reduce_perstock_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">稀释每股收益</th>
					<th data-options="field:'other_all_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">其他综合收益</th>
					<th data-options="field:'all_income_total',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">综合收益总额</th>
					<th data-options="field:'income_for_parent',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">归属于母公司</th>
					<th data-options="field:'income_for_holder',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">归属于少数股东</th>
					<th data-options="field:'interest_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">利息收入</th>
					<th data-options="field:'earn_insurance',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">已赚保费</th>
					<th data-options="field:'commission_income',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">手续费及佣金收入</th>
					<th data-options="field:'interest_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">利息支出</th>
					<th data-options="field:'commission_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">手续费及佣金支出</th>
					<th data-options="field:'canel_insurance_money',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">退保金</th>
					<th data-options="field:'pay_netprofit',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">赔付支出净额</th>
					<th data-options="field:'insurance_netprofit',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">提取保险合同准备金净额</th>
					<th data-options="field:'insurance_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">保单红利支出</th>
					<th data-options="field:'reduce_insurance_cost',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">分保费用</th>
					<th data-options="field:'interest_dispose',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">非流动资产处置利得</th>
					<th data-options="field:'push_flag',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">PUSH FLAG</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">
var editIndex = undefined;
function endEditing() {
	if (editIndex == undefined){return true}
	if ($('#dg').datagrid('validateRow', editIndex)){
		$('#dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow(index) {
	if (editIndex != index){
		if (endEditing()){
			$('#dg').datagrid('selectRow', index)
					.datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#dg').datagrid('selectRow', editIndex);
		}
	}
}

$.extend($.fn.datagrid.methods, {
	editCell: function(jq, param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

function onClickCell(index, field){
	if (endEditing()){
		$('#dg').datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
	}
}
function append(){
	if (endEditing()){
		$('#dg').datagrid('appendRow',{status:'P'});
		editIndex = $('#dg').datagrid('getRows').length-1;
		$('#dg').datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
	}
}
function removeit(){
	if (editIndex == undefined){return}
	$('#dg').datagrid('cancelEdit', editIndex)
			.datagrid('deleteRow', editIndex);
	editIndex = undefined;
}
function accept(){
	if (endEditing()){
		$('#dg').datagrid('acceptChanges');
	}
}
function reject(){
	$('#dg').datagrid('rejectChanges');
	editIndex = undefined;
}
function getChanges(){
	var rows = $('#dg').datagrid('getChanges');
	alert(rows.length+' rows are changed!');
}
</script>
  
</body>
</html>
