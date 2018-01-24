
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="activityForm" action="${basePath}companyBalance/updateCompanyBalance.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>股票编号</td>
			<input type="hidden" id="balance_sheet_id" name="balance_sheet_id">
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
			<td>货币资金</td>
			<td><input type="text" id="cash" name="cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>以公允价值计量且其变动计入当期损益的金融资产</td>
			<td><input type="text" id="trading_fin_asset" name="trading_fin_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应收票据</td>
			<td><input type="text" id="rec_note" name="rec_note" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应收账款</td>
			<td><input type="text" id="rec_account" name="rec_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>预付款项</td>
			<td><input type="text" id="prepay" name="prepay" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他应收款</td>
			<td><input type="text" id="other_rec_account" name="other_rec_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应收关联公司款</td>
			<td><input type="text" id="rec_relate_account" name="rec_relate_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应收利息</td>
			<td><input type="text" id="rec_interest" name="rec_interest" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应收股利</td>
			<td><input type="text" id="rec_dividend" name="rec_dividend" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>存货</td>
			<td><input type="text" id="stock" name="stock" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>消耗性生物资产</td>
			<td><input type="text" id="consume_asset" name="consume_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>一年内到期的非流动资产</td>
			<td><input type="text" id="non_current_asset" name="non_current_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他流动资产</td>
			<td><input type="text" id="other_current_asset" name="other_current_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>流动资产合计</td>
			<td><input type="text" id="total_current_asset" name="total_current_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>可供出售金融资产</td>
			<td><input type="text" id="available_sale_asset" name="available_sale_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>持有至到期投资</td>
			<td><input type="text" id="held_investment" name="held_investment" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>长期应收款</td>
			<td><input type="text" id="long_rec_account" name="long_rec_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>长期股权投资</td>
			<td><input type="text" id="long_equity_investment" name="long_equity_investment" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资性房地产</td>
			<td><input type="text" id="invest_house" name="invest_house" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>固定资产</td>
			<td><input type="text" id="fix_asset" name="fix_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>在建工程</td>
			<td><input type="text" id="building" name="building" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>工程物资</td>
			<td><input type="text" id="project_material" name="project_material" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>固定资产清理</td>
			<td><input type="text" id="fix_asset_dispose" name="fix_asset_dispose" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>生物性生物资产</td>
			<td><input type="text" id="product_asset" name="product_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>油气资产</td>
			<td><input type="text" id="oil_asset" name="oil_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>无形资产</td>
			<td><input type="text" id="intangible_asset" name="intangible_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>开发支出</td>
			<td><input type="text" id="develop_cost" name="develop_cost" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>商誉</td>
			<td><input type="text" id="goodwill" name="goodwill" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>长期待摊费用</td>
			<td><input type="text" id="long_defer_cost" name="long_defer_cost" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>递延所得税资产</td>
			<td><input type="text" id="tax_asset" name="tax_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他非流动资产</td>
			<td><input type="text" id="other_noncurrent_asset" name="other_noncurrent_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>非流动资产合计</td>
			<td><input type="text" id="noncurrent_asset_total" name="noncurrent_asset_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>资产总计</td>
			<td><input type="text" id="asset_total" name="asset_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>短期借款</td>
			<td><input type="text" id="short_borrow" name="short_borrow" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>以公允价值计量且其变动计入当期损益的金融负债</td>
			<td><input type="text" id="trading_fin_borrow" name="trading_fin_borrow" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应付票据</td>
			<td><input type="text" id="pay_note" name="pay_note" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应付账款</td>
			<td><input type="text" id="pay_account" name="pay_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>预收款项</td>
			<td><input type="text" id="prepay_account" name="prepay_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应付职工薪酬</td>
			<td><input type="text" id="pay_salary" name="pay_salary" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应交税费</td>
			<td><input type="text" id="pay_tax" name="pay_tax" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应付利息</td>
			<td><input type="text" id="pay_interest" name="pay_interest" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应付股利</td>
			<td><input type="text" id="pay_dividend" name="pay_dividend" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他应付款</td>
			<td><input type="text" id="other_pay_account" name="other_pay_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应付关联公司款</td>
			<td><input type="text" id="pay_relate_account" name="pay_relate_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>一年内到期的非流动负债</td>
			<td><input type="text" id="non_current_borrow" name="non_current_borrow" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他流动负债</td>
			<td><input type="text" id="other_current_borrow" name="other_current_borrow" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>流动负债合计</td>
			<td><input type="text" id="current_borrow_total" name="current_borrow_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>长期借款</td>
			<td><input type="text" id="long_borrow" name="long_borrow" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>应付债券</td>
			<td><input type="text" id="pay_bonds" name="pay_bonds" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>长期应付款</td>
			<td><input type="text" id="long_pay_account" name="long_pay_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>专项应付款</td>
			<td><input type="text" id="term_pay_account" name="term_pay_account" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>预计负债</td>
			<td><input type="text" id="pre_bonds" name="pre_bonds" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>递延所得税负债</td>
			<td><input type="text" id="tax_bonds" name="tax_bonds" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他非流动负债</td>
			<td><input type="text" id="other_noncurrent_bonds" name="other_noncurrent_bonds" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>非流动负债合计</td>
			<td><input type="text" id="noncurrent_bonds_total" name="noncurrent_bonds_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>负债合计</td>
			<td><input type="text" id="bonds_total" name="bonds_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>实收资本（或股本）</td>
			<td><input type="text" id="rec_capital" name="rec_capital" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>资本公积</td>
			<td><input type="text" id="capital_reserve" name="capital_reserve" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>盈余公积</td>
			<td><input type="text" id="earn_reserve" name="earn_reserve" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>减：库存股</td>
			<td><input type="text" id="reduce_share" name="reduce_share" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>未分配利润</td>
			<td><input type="text" id="nopay_profit" name="nopay_profit" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>少数股东权益</td>
			<td><input type="text" id="monority_holder_equity" name="monority_holder_equity" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>外币报表折算价差</td>
			<td><input type="text" id="exchange_difference" name="exchange_difference" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>非正常经营项目收益调整</td>
			<td><input type="text" id="profit_adjust" name="profit_adjust" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>所有者权益（或股东权益）合计</td>
			<td><input type="text" id="equity_total" name="equity_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>负债和所有者（或股东权益）合计</td>
			<td><input type="text" id="all_total" name="all_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>归属于母公司所有者权益</td>
			<td><input type="text" id="parent_equity" name="parent_equity" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>累计折旧和摊销</td>
			<td><input type="text" id="total_depre_amor" name="total_depre_amor" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>PUSH FLAG</td>
			<td><input type="text" id="push_flag" name="push_flag" class="easyui-numberbox" data-options="required:true"></td>
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
			balance_sheet_id : row.balance_sheet_id,
			stock_code : row.stock_code,
			publish_date : row.publish_date,
			end_date : row.end_date,
			account_date : row.account_date,
			report_period : row.report_period,
			cash : row.cash,
			trading_fin_asset : row.trading_fin_asset,
			rec_note : row.rec_note,
			rec_account : row.rec_account,
			prepay : row.prepay,
			other_rec_account : row.other_rec_account,
			rec_relate_account : row.rec_relate_account,
			rec_interest : row.rec_interest,
			rec_dividend : row.rec_dividend,
			stock : row.stock,
			consume_asset : row.consume_asset,
			non_current_asset : row.non_current_asset,
			other_current_asset : row.other_current_asset,
			total_current_asset : row.total_current_asset,
			available_sale_asset : row.available_sale_asset,
			held_investment : row.held_investment,
			long_rec_account : row.long_rec_account,
			long_equity_investment : row.long_equity_investment,
			invest_house : row.invest_house,
			fix_asset : row.fix_asset,
			building : row.building,
			project_material : row.project_material,
			fix_asset_dispose : row.fix_asset_dispose,
			product_asset : row.product_asset,
			oil_asset : row.oil_asset,
			intangible_asset : row.intangible_asset,
			develop_cost : row.develop_cost,
			goodwill : row.goodwill,
			long_defer_cost : row.long_defer_cost,
			tax_asset : row.tax_asset,
			other_noncurrent_asset : row.other_noncurrent_asset,
			noncurrent_asset_total : row.noncurrent_asset_total,
			asset_total : row.asset_total,
			short_borrow : row.short_borrow,
			trading_fin_borrow : row.trading_fin_borrow,
			pay_note : row.pay_note,
			pay_account : row.pay_account,
			prepay_account : row.prepay_account,
			pay_salary : row.pay_salary,
			pay_tax : row.pay_tax,
			pay_interest : row.pay_interest,
			pay_dividend : row.pay_dividend,
			other_pay_account : row.other_pay_account,
			pay_relate_account : row.pay_relate_account,
			non_current_borrow : row.non_current_borrow,
			other_current_borrow : row.other_current_borrow,
			current_borrow_total : row.current_borrow_total,
			long_borrow : row.long_borrow,
			pay_bonds : row.pay_bonds,
			long_pay_account : row.long_pay_account,
			term_pay_account : row.term_pay_account,
			pre_bonds : row.pre_bonds,
			tax_bonds : row.tax_bonds,
			other_noncurrent_bonds : row.other_noncurrent_bonds,
			noncurrent_bonds_total : row.noncurrent_bonds_total,
			bonds_total : row.bonds_total,
			rec_capital : row.rec_capital,
			capital_reserve : row.capital_reserve,
			earn_reserve : row.earn_reserve,
			reduce_share : row.reduce_share,
			nopay_profit : row.nopay_profit,
			monority_holder_equity : row.monority_holder_equity,
			exchange_difference : row.exchange_difference,
			profit_adjust : row.profit_adjust,
			equity_total : row.equity_total,
			all_total : row.all_total,
			parent_equity : row.parent_equity,
			total_depre_amor : row.total_depre_amor,
			push_flag : row.push_flag
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