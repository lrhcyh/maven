
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="activityForm" action="${basePath}companyCash/addCompanyCash.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>股票编号</td>
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
			<td>销售商品、提供劳务收到的现金（元）</td>
			<td><input type="text" id="sale_cash" name="sale_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收到的税费返还</td>
			<td><input type="text" id="tax_return" name="tax_return" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收到其他与经营活动有关的现金</td>
			<td><input type="text" id="rec_other_cash" name="rec_other_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>经营活动现金流入小计</td>
			<td><input type="text" id="bussiness_cash_total" name="bussiness_cash_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>购买商品、接受劳务支付的现金</td>
			<td><input type="text" id="buy_for_cash" name="buy_for_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付给职工以及为职工支付的现金</td>
			<td><input type="text" id="pay_emp_cash" name="pay_emp_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付的各项税费</td>
			<td><input type="text" id="pay_tax" name="pay_tax" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付其他与经营活动有关的现金</td>
			<td><input type="text" id="pay_other_cash" name="pay_other_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>经营活动现金流出小计</td>
			<td><input type="text" id="bussiness_cash_output" name="bussiness_cash_output" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>经营活动产生的现金流量净额</td>
			<td><input type="text" id="bussiness_cash_netvalue" name="bussiness_cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收回投资收到的现金</td>
			<td><input type="text" id="rec_invest_cash" name="rec_invest_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>取得投资收益收到的现金</td>
			<td><input type="text" id="invest_rec_cash" name="invest_rec_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>处置固定资产、无形资产和其他长期资产收回的现金净额</td>
			<td><input type="text" id="dispose_asset_netvalue" name="dispose_asset_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>处置子公司及其他营业单位收到的现金净额</td>
			<td><input type="text" id="dispose_other_netvalue" name="dispose_other_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收到其他与投资活动有关的现金</td>
			<td><input type="text" id="rec_otherinvest_cash" name="rec_otherinvest_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资活动现金流入小计</td>
			<td><input type="text" id="invest_cash_total" name="invest_cash_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>购建固定资产、无形资产和其他长期资产支付的现金</td>
			<td><input type="text" id="buy_asset_cash" name="buy_asset_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资支付的现金</td>
			<td><input type="text" id="invest_pay_cash" name="invest_pay_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>质押贷款净增加额</td>
			<td><input type="text" id="loan_net_addvalue" name="loan_net_addvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>取得子公司及其他营业单位支付的现金净额</td>
			<td><input type="text" id="rec_othercompany_cash" name="rec_othercompany_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付其他与投资活动有关的现金</td>
			<td><input type="text" id="pay_otherinvest_cash" name="pay_otherinvest_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资活动现金流出小计</td>
			<td><input type="text" id="invest_cash_output" name="invest_cash_output" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资活动产生的现金流量净额</td>
			<td><input type="text" id="invest_cash_netvalue" name="invest_cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>吸收投资收到的现金</td>
			<td><input type="text" id="rec_invest_reccash" name="rec_invest_reccash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>取得借款收到的现金</td>
			<td><input type="text" id="rec_borrow_cash" name="rec_borrow_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>发行债券收到的现金</td>
			<td><input type="text" id="publish_rec_cash" name="publish_rec_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收到其他与筹资活动有关的现金</td>
			<td><input type="text" id="rec_other_relatecash" name="rec_other_relatecash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>筹资活动现金流入小计</td>
			<td><input type="text" id="borrow_cash_total" name="borrow_cash_total" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>偿还债务支付的现金</td>
			<td><input type="text" id="pay_debet_cash" name="pay_debet_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>分配股利、利润或偿付利息支付的现金</td>
			<td><input type="text" id="interest_pay_cash" name="interest_pay_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付其他与筹资活动有关的现金</td>
			<td><input type="text" id="pay_other_relatecash" name="pay_other_relatecash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>筹资活动现金流出小计</td>
			<td><input type="text" id="borrow_cash_outtotal" name="borrow_cash_outtotal" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>筹资活动产生的现金流量净额</td>
			<td><input type="text" id="borrow_cash_netvalue" name="borrow_cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>汇率变动对现金的影响</td>
			<td><input type="text" id="rate_to_cash" name="rate_to_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他原因对现金的影响</td>
			<td><input type="text" id="other_to_cash" name="other_to_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>现金及现金等价物净增加额</td>
			<td><input type="text" id="cash_to_netadd" name="cash_to_netadd" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>期初现金及现金等价物余额</td>
			<td><input type="text" id="origin_cash" name="origin_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>期末现金及现金等价物余额</td>
			<td><input type="text" id="last_cash" name="last_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>附注：</td>
			<td><input type="text" id="addition" name="addition" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>将净利润调节为经营活动现金流量：</td>
			<td><input type="text" id="netvalue_to_cash" name="netvalue_to_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>净利润</td>
			<td><input type="text" id="netvalue" name="netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>加：资产减值准备</td>
			<td><input type="text" id="plus_asset_loss" name="plus_asset_loss" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>固定资产折旧、油气资产折耗、生产性生物资产折旧</td>
			<td><input type="text" id="asset_discount" name="asset_discount" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>无形资产摊销</td>
			<td><input type="text" id="intangible_asset_discount" name="intangible_asset_discount" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>长期待摊费用摊销</td>
			<td><input type="text" id="long_cost_discount" name="long_cost_discount" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>处置固定资产、无形资产和其他长期资产的损失</td>
			<td><input type="text" id="asset_loss" name="asset_loss" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>固定资产报废损失</td>
			<td><input type="text" id="fix_asset_loss" name="fix_asset_loss" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公允价值变动损失</td>
			<td><input type="text" id="value_change_loss" name="value_change_loss" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>财务费用</td>
			<td><input type="text" id="fin_cost" name="fin_cost" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资损失</td>
			<td><input type="text" id="invest_loss" name="invest_loss" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>递延所得税资产减少</td>
			<td><input type="text" id="tax_reduce" name="tax_reduce" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>递延所得税负债增加</td>
			<td><input type="text" id="debt_reduce" name="debt_reduce" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>存货的减少</td>
			<td><input type="text" id="stock_reduce" name="stock_reduce" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>经营性应收项目的减少</td>
			<td><input type="text" id="rec_project_reduce" name="rec_project_reduce" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>经营性应付项目的增加</td>
			<td><input type="text" id="pay_project_add" name="pay_project_add" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其他</td>
			<td><input type="text" id="other" name="other" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>经营活动产生的现金流量净额</td>
			<td><input type="text" id="business_cash_netvalue" name="business_cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>不涉及现金收支的重大投资和筹资活动：</td>
			<td><input type="text" id="non_cash_netvalue" name="non_cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>债务转为资本</td>
			<td><input type="text" id="debt_to_capital" name="debt_to_capital" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>一年内到期的可转换公司债券</td>
			<td><input type="text" id="debt_one_year" name="debt_one_year" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>融资租入固定资产</td>
			<td><input type="text" id="cash_to_asset" name="cash_to_asset" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>3、现金及现金等价物净变动情况：</td>
			<td><input type="text" id="cash_change" name="cash_change" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>现金的期末余额</td>
			<td><input type="text" id="last_cash_value" name="last_cash_value" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>减：现金的期初余额</td>
			<td><input type="text" id="reduce_origin_cash" name="reduce_origin_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>加：现金等价物的期末余额</td>
			<td><input type="text" id="plus_last_cash" name="plus_last_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>减：现金等价物的期初余额</td>
			<td><input type="text" id="reduce_origin_value" name="reduce_origin_value" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>加：其他原因对现金的影响</td>
			<td><input type="text" id="plus_other_cash" name="plus_other_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>现金及现金等价物净增加额</td>
			<td><input type="text" id="cash_to_netvalue" name="cash_to_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>客户存款和同业存放款项净增加额</td>
			<td><input type="text" id="custom_to_netvalue" name="custom_to_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>向中央银行借款净增加额</td>
			<td><input type="text" id="borrow_netvalue" name="borrow_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>向其他金融机构拆入资金净增加额</td>
			<td><input type="text" id="borrow_other_netvalue" name="borrow_other_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收到原保险合同保费取得的现金</td>
			<td><input type="text" id="rec_insurance_cash" name="rec_insurance_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收到再保险业务现金净额</td>
			<td><input type="text" id="rec_insurance_netvalue" name="rec_insurance_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>保户储金及投资款净增加额</td>
			<td><input type="text" id="invest_netvalue" name="invest_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>处置以公允价值计量且其变动计入当期损益的金融资产净增加额</td>
			<td><input type="text" id="dispose_the_cash" name="dispose_the_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收取利息、手续费及佣金的现金</td>
			<td><input type="text" id="rec_interest_cash" name="rec_interest_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>拆入资金净增加额</td>
			<td><input type="text" id="cash_netvalue" name="cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>回购业务资金净增加额</td>
			<td><input type="text" id="return_cash_netvalue" name="return_cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>客户贷款及垫款净增加额</td>
			<td><input type="text" id="custom_netvalue" name="custom_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>存放中央银行和同业款项净增加额</td>
			<td><input type="text" id="bank_cash_netvalue" name="bank_cash_netvalue" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付原保险合同赔付款项的现金</td>
			<td><input type="text" id="pay_insurance_cash" name="pay_insurance_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付利息、手续费及佣金的现金</td>
			<td><input type="text" id="pay_interest_cash" name="pay_interest_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>支付保单红利的现金</td>
			<td><input type="text" id="pay_profit_cash" name="pay_profit_cash" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其中：子公司吸收少数股东投资收到的现金</td>
			<td><input type="text" id="cash_for_holder" name="cash_for_holder" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>其中：子公司支付给少数股东的股利、利润</td>
			<td><input type="text" id="profit_for_holder" name="profit_for_holder" class="easyui-numberbox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>投资性房地产的折旧及摊销</td>
			<td><input type="text" id="house_disount" name="house_disount" class="easyui-numberbox" data-options="required:false"></td>
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