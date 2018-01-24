
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="activityForm" action="${basePath}companyProfit/editCompanyProfit.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>股票编号</td>
			<input type="hidden" id="profit_sheet_id" name="profit_sheet_id">
			<td><input type="text" id="stock_code" name="stock_code" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		<tr>
			<td>公告日期</td>
			<td><input type="text" id="publish_date" name="publish_date" class="easyui-datebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>截止日期</td>
			<td><input type="text" id="end_date" name="end_date" class="easyui-datebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>报告年度</td>
			<td><input type="text" id="account_date" name="account_date" class="easyui-datebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>报告期</td>
			<td><input type="text" id="report_period" name="report_period" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业总收入</td>
			<td><input type="text" id="overall_income" name="overall_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业收入</td>
			<td><input type="text" id="main_income" name="main_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业总成本</td>
			<td><input type="text" id="overall_cost" name="overall_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业成本</td>
			<td><input type="text" id="main_cost" name="main_cost" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业税金及附加</td>
			<td><input type="text" id="tax" name="tax" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>销售费用</td>
			<td><input type="text" id="sale_cost" name="sale_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>管理费用</td>
			<td><input type="text" id="manage_cost" name="manage_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>勘探费用</td>
			<td><input type="text" id="other_cost" name="other_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>财务费用</td>
			<td><input type="text" id="fin_cost" name="fin_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>资产减值损失</td>
			<td><input type="text" id="asset_loss" name="asset_loss" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公允价值变动净收益</td>
			<td><input type="text" id="plus_change_income" name="plus_change_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资收益</td>
			<td><input type="text" id="invest_income" name="invest_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>对联营企业和合营企业的投资收益</td>
			<td><input type="text" id="relate_invest_income" name="relate_invest_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>汇兑收益</td>
			<td><input type="text" id="gain_loss_income" name="gain_loss_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>影响营业利润的其他科目</td>
			<td><input type="text" id="other_subject" name="other_subject" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业利润</td>
			<td><input type="text" id="overall_profit" name="overall_profit" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>补贴收入</td>
			<td><input type="text" id="plus_subsidy_income" name="plus_subsidy_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业外收入</td>
			<td><input type="text" id="addition_income" name="addition_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>营业外支出</td>
			<td><input type="text" id="reduce_addition_cost" name="reduce_addition_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>非流动资产处置损失</td>
			<td><input type="text" id="asset_dispose_loss" name="asset_dispose_loss" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>影响利润总额的其他科目</td>
			<td><input type="text" id="plus_profit_subject" name="plus_profit_subject" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>利润总额</td>
			<td><input type="text" id="profit_total" name="profit_total" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>所得税</td>
			<td><input type="text" id="reduce_tax" name="reduce_tax" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>影响净利润的其他科目</td>
			<td><input type="text" id="plus_netprofit_subject" name="plus_netprofit_subject" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>净利润</td>
			<td><input type="text" id="netprofit" name="netprofit" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>归属于母公司所有者的净利润</td>
			<td><input type="text" id="parent_netprofit" name="parent_netprofit" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>少数股东损益</td>
			<td><input type="text" id="minority_loss" name="minority_loss" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>每股收益</td>
			<td><input type="text" id="perstock_income" name="perstock_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>基本每股收益</td>
			<td><input type="text" id="basic_perstock_income" name="basic_perstock_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>稀释每股收益</td>
			<td><input type="text" id="reduce_perstock_income" name="reduce_perstock_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他综合收益</td>
			<td><input type="text" id="other_all_income" name="other_all_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>综合收益总额</td>
			<td><input type="text" id="puall_income_totalblish_date" name="all_income_total" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>归属于母公司</td>
			<td><input type="text" id="income_for_parent" name="income_for_parent" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>归属于少数股东</td>
			<td><input type="text" id="income_for_holder" name="income_for_holder" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>利息收入</td>
			<td><input type="text" id="interest_income" name="interest_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>已赚保费</td>
			<td><input type="text" id="earn_insurance" name="earn_insurance" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>手续费及佣金收入</td>
			<td><input type="text" id="commission_income" name="commission_income" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>利息支出</td>
			<td><input type="text" id="interest_cost" name="interest_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>手续费及佣金支出</td>
			<td><input type="text" id="commission_cost" name="commission_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>退保金</td>
			<td><input type="text" id="canel_insurance_money" name="canel_insurance_money" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>赔付支出净额</td>
			<td><input type="text" id="pay_netprofit" name="pay_netprofit" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>提取保险合同准备金净额</td>
			<td><input type="text" id="insurance_netprofit" name="insurance_netprofit" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>保单红利支出</td>
			<td><input type="text" id="insurance_cost" name="insurance_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>分保费用</td>
			<td><input type="text" id="reduce_insurance_cost" name="reduce_insurance_cost" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>非流动资产处置利得</td>
			<td><input type="text" id="interest_dispose" name="interest_dispose" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>PUSH FLAG</td>
			<td><input type="text" id="push_flag" name="push_flag" class="easyui-validatebox" data-options="required:true"></td>
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
			profit_sheet_id : row.profit_sheet_id,
			stock_code : row.stock_code,
			publish_date : row.publish_date,
			end_date : row.end_date,
			account_date : row.account_date,
			report_period : row.report_period,
			overall_income : row.overall_income,
			main_income : row.main_income,
			overall_cost : row.overall_cost,
			main_cost : row.main_cost,
			tax : row.tax,
			sale_cost : row.sale_cost,
			manage_cost : row.manage_cost,
			other_cost : row.other_cost,
			fin_cost : row.fin_cost,
			asset_loss : row.asset_loss,
			plus_change_income : row.plus_change_income,
			invest_income : row.invest_income,
			relate_invest_income : row.relate_invest_income,
			gain_loss_income : row.gain_loss_income,
			other_subject : row.other_subject,
			overall_profit : row.overall_profit,
			plus_subsidy_income : row.plus_subsidy_income,
			addition_income : row.addition_income,
			reduce_addition_cost : row.reduce_addition_cost,
			asset_dispose_loss : row.asset_dispose_loss,
			plus_profit_subject : row.plus_profit_subject,
			profit_total : row.profit_total,
			reduce_tax : row.reduce_tax,
			plus_netprofit_subject : row.plus_netprofit_subject,
			netprofit : row.netprofit,
			parent_netprofit : row.parent_netprofit,
			minority_loss : row.minority_loss,
			perstock_income : row.perstock_income,
			basic_perstock_income : row.basic_perstock_income,
			reduce_perstock_income : row.reduce_perstock_income,
			other_all_income : row.other_all_income,
			all_income_total : row.all_income_total,
			income_for_parent : row.income_for_parent,
			income_for_holder : row.income_for_holder,
			interest_income : row.interest_income,
			earn_insurance : row.earn_insurance,
			commission_income : row.commission_income,
			interest_cost : row.interest_cost,
			commission_cost : row.commission_cost,
			canel_insurance_money : row.canel_insurance_money,
			pay_netprofit : row.pay_netprofit,
			insurance_netprofit : row.insurance_netprofit,
			insurance_cost : row.insurance_cost,
			reduce_insurance_cost : row.reduce_insurance_cost,
			interest_dispose : row.interest_dispose,
			push_flag : row.push_flag,
		});
	});
	
	function save() {
		var win = $.messager.progress({
			title : '正在提交',
			msg : '正在添加,请稍等...'
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
				$('#dg').datagrid('reload');
			}
		});
	}
</script>
<div></div>