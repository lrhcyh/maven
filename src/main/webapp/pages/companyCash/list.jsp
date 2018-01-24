<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">
	<div data-options="region:'north',title:'现金流量信息',overflow:'show',collapsible:false" style="height: 85px">
	
	<form id="searchForm" method="post">
		<table align="center">
			<tr align="center">
					<td align="right" width="80px" margin="10px">股票代码</td>
					<td align="right" width="60px" margin="10px">
						<input id="stockCode" name="stockCode" class="easyui-textbox" data-options="">
					</td>
					<td align="right" width="140px" margin="10px">截止日期开始时间</td>
					<td align="right" width="60px" margin="10px">
						<input id="end_date_start" name="end_date_start" class="easyui-datebox" data-options="">
					</td>
					<td align="right" width="140px" margin="10px">截止日期结束时间</td>
					<td align="right" width="60px" margin="10px">
						<input id="end_date_end" name="end_date_end" class="easyui-datebox" data-options="">
					</td>
					<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
					<td width="120px" height="50px"><a id="btn" onclick="searchRepeat()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">重复数据</a></td>
			</tr>
		</table>
	</form>
	</div>
	<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
		<table id="dg" class="easyui-datagrid" style="width: inherit; height: 100%" data-options=" iconCls: 'icon-edit', singleSelect: true,
				url: 'companyCash/findList.form',
				toolbar: toolbar,
				method: 'POST',
				pageList: [200,500,1000,2000,5000],
				pagination:true,checkbox:true,
				onClickCell: onClickCell
			">
		
			<thead>
				<tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <input type="hidden" id="cash_sheet_id" name="cash_sheet_id">
					<th data-options="field:'stock_code',width:80,align:'center',editor:{type:'text'}">股票代码</th>
					<th data-options="field:'publish_date',width:100,align:'center',editor:{type:'datebox'}">公告日期</th>
					<th data-options="field:'end_date',width:100,align:'center',editor:{type:'datebox'}">截止日期</th>
					<th data-options="field:'account_date',width:100,align:'center',editor:{type:'datebox'}">报告年度</th>
					<th data-options="field:'report_period',width:50,align:'center',editor:{type:'numberbox'}">报告期</th>
					<th data-options="field:'sale_cash',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">销售商品、提供劳务收到的现金（元）</th>
					<th data-options="field:'tax_return',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">收到的税费返还</th>
					<th data-options="field:'rec_other_cash',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">收到其他与经营活动有关的现金</th>
					<th data-options="field:'bussiness_cash_total',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">经营活动现金流入小计</th>
					<th data-options="field:'buy_for_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">购买商品、接受劳务支付的现金</th>
					<th data-options="field:'pay_emp_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付给职工以及为职工支付的现金</th>
					<th data-options="field:'pay_tax',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付的各项税费</th>
					<th data-options="field:'pay_other_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付其他与经营活动有关的现金</th>
					<th data-options="field:'bussiness_cash_output',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">经营活动现金流出小计</th>
					<th data-options="field:'bussiness_cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">经营活动产生的现金流量净额</th>
					<th data-options="field:'rec_invest_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">收回投资收到的现金</th>
					<th data-options="field:'invest_rec_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">取得投资收益收到的现金</th>
					<th data-options="field:'dispose_asset_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">处置固定资产、无形资产和其他长期资产收回的现金净额</th>
					<th data-options="field:'dispose_other_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">处置子公司及其他营业单位收到的现金净额</th>
					<th data-options="field:'rec_otherinvest_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">收到其他与投资活动有关的现金</th>
					<th data-options="field:'invest_cash_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">投资活动现金流入小计</th>
					<th data-options="field:'buy_asset_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">购建固定资产、无形资产和其他长期资产支付的现金</th>
					<th data-options="field:'invest_pay_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">投资支付的现金</th>
					<th data-options="field:'loan_net_addvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">质押贷款净增加额</th>
					<th data-options="field:'rec_othercompany_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">取得子公司及其他营业单位支付的现金净额</th>
					<th data-options="field:'pay_otherinvest_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付其他与投资活动有关的现金</th>
					<th data-options="field:'invest_cash_output',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">投资活动现金流出小计</th>
					<th data-options="field:'invest_cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">投资活动产生的现金流量净额</th>
					<th data-options="field:'rec_invest_reccash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">吸收投资收到的现金</th>
					<th data-options="field:'rec_borrow_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">取得借款收到的现金</th>
					<th data-options="field:'publish_rec_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">发行债券收到的现金</th>
					<th data-options="field:'rec_other_relatecash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">收到其他与筹资活动有关的现金</th>
					<th data-options="field:'borrow_cash_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">筹资活动现金流入小计</th>
					<th data-options="field:'pay_debet_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">偿还债务支付的现金</th>
					<th data-options="field:'interest_pay_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">分配股利、利润或偿付利息支付的现金</th>
					<th data-options="field:'pay_other_relatecash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付其他与筹资活动有关的现金</th>
					<th data-options="field:'borrow_cash_outtotal',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">筹资活动现金流出小计</th>
					<th data-options="field:'borrow_cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">筹资活动产生的现金流量净额</th>
					<th data-options="field:'rate_to_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">汇率变动对现金的影响</th>
					<th data-options="field:'other_to_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他原因对现金的影响</th>
					<th data-options="field:'cash_to_netadd',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">现金及现金等价物净增加额</th>
					<th data-options="field:'origin_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">期初现金及现金等价物余额</th>
					<th data-options="field:'last_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">期末现金及现金等价物余额</th>
					<th data-options="field:'addition',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">附注：</th>
					<th data-options="field:'netvalue_to_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">将净利润调节为经营活动现金流量：</th>
					<th data-options="field:'netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">净利润</th>
					<th data-options="field:'plus_asset_loss',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">加：资产减值准备</th>
					<th data-options="field:'asset_discount',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">固定资产折旧、油气资产折耗、生产性生物资产折旧</th>
					<th data-options="field:'intangible_asset_discount',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">无形资产摊销</th>
					<th data-options="field:'long_cost_discount',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">长期待摊费用摊销</th>
					<th data-options="field:'asset_loss',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">处置固定资产、无形资产和其他长期资产的损失</th>
					<th data-options="field:'fix_asset_loss',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">固定资产报废损失</th>
					<th data-options="field:'value_change_loss',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">公允价值变动损失</th>
					<th data-options="field:'fin_cost',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">财务费用</th>
					<th data-options="field:'invest_loss',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">投资损失</th>
					<th data-options="field:'tax_reduce',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">递延所得税资产减少</th>
					<th data-options="field:'debt_reduce',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">递延所得税负债增加</th>
					<th data-options="field:'stock_reduce',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">存货的减少</th>
					<th data-options="field:'rec_project_reduce',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">经营性应收项目的减少</th>
					<th data-options="field:'pay_project_add',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">经营性应付项目的增加</th>
					<th data-options="field:'other',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他</th>
					<th data-options="field:'business_cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">经营活动产生的现金流量净额2</th>
					<th data-options="field:'non_cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">不涉及现金收支的重大投资和筹资活动：</th>
					<th data-options="field:'debt_to_capital',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">债务转为资本</th>
					<th data-options="field:'debt_one_year',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">一年内到期的可转换公司债券</th>
					<th data-options="field:'cash_to_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">融资租入固定资产</th>
					<th data-options="field:'cash_change',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">现金及现金等价物净变动情况：</th>
					<th data-options="field:'last_cash_value',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">现金的期末余额</th>
					<th data-options="field:'reduce_origin_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">减：现金的期初余额</th>
					<th data-options="field:'plus_last_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">加：现金等价物的期末余额</th>
					<th data-options="field:'reduce_origin_value',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">减：现金等价物的期初余额</th>
					<th data-options="field:'plus_other_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">加：其他原因对现金的影响2</th>
					<th data-options="field:'cash_to_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">现金及现金等价物净增加额2</th>
					<th data-options="field:'custom_to_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">客户存款和同业存放款项净增加额</th>
					<th data-options="field:'borrow_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">向中央银行借款净增加额</th>
					<th data-options="field:'borrow_other_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">向其他金融机构拆入资金净增加额</th>
					<th data-options="field:'rec_insurance_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">收到原保险合同保费取得的现金</th>
					<th data-options="field:'rec_insurance_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">收到再保险业务现金净额</th>
					<th data-options="field:'invest_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">保户储金及投资款净增加额</th>
					<th data-options="field:'dispose_the_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">处置以公允价值计量且其变动计入当期损益的金融资产净增加额</th>
					<th data-options="field:'rec_interest_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">收取利息、手续费及佣金的现金</th>
					<th data-options="field:'cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">拆入资金净增加额</th>
					<th data-options="field:'return_cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">回购业务资金净增加额</th>
					<th data-options="field:'custom_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">客户贷款及垫款净增加额</th>
					<th data-options="field:'bank_cash_netvalue',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">存放中央银行和同业款项净增加额</th>
					<th data-options="field:'pay_insurance_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付原保险合同赔付款项的现金</th>
					<th data-options="field:'pay_interest_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付利息、手续费及佣金的现金</th>
					<th data-options="field:'pay_profit_cash',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">支付保单红利的现金</th>
					<th data-options="field:'cash_for_holder',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其中：子公司吸收少数股东投资收到的现金</th>
					<th data-options="field:'profit_for_holder',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其中：子公司支付给少数股东的股利、利润</th>
					<th data-options="field:'house_disount',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">投资性房地产的折旧及摊销</th>
					<th data-options="field:'push_flag',width:100,align:'center',editor:{type:'numberbox'}">PUSH FLAG</th>
				</tr>
			</thead>
		</table>
	</div>
	
</div>
	
	<script type="text/javascript">
		var basepath = '<%=basePath%>';
		
		var editIndex = undefined;
		
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'stock_code'});
				//获取修改行的所有数据
                var rows = $('#dg').datagrid('getRows');
                //获取修后该行所有field的值，在这里将修改后数据写入到数据库中
                var rowData = rows[editIndex];
                //结束编辑:如果在结束编辑之前，rowData还是编辑之前的数据，只有结束编辑之后，rowData才是编辑完成的数据
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		
// 		function onClickRow(index){
// 			if (editIndex != index){
// 				if (endEditing()){
// 					$('#dg').datagrid('selectRow', index).datagrid('beginEdit', index);
// 					editIndex = index;
// 				} else {
// 					$('#dg').datagrid('selectRow', editIndex);
// 				}
// 			}
// 		}
		
		function onClickCell(index, field){
			if (endEditing()) {
					$('#dg').datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});
					editIndex = index;
			}
		}
		
		$.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
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
		
		function searchList() {
			var stockCode = $('#stockCode').textbox('getValue');
			var end_date_start = $('#end_date_start').textbox('getValue');
			var end_date_end = $('#end_date_end').textbox('getValue');
			$("#dg").datagrid('load', {
				stockCode : stockCode,
				end_date_start : end_date_start,
				end_date_end : end_date_end
			});
		}
		 
		function searchRepeat(){
			$("#dg").datagrid('load', {
				keyWord : "keyWord"
			});
		}
		
		var toolbar = [ {
			text : '添加现金流量信息',
			iconCls : 'icon-add',
			handler : function() {
				showWindow(basepath + 'companyCash/gotoPage.form?page=add', 600, 500, '添加现金流量信息');
			}
		}, '-', {
			text : '修改现金流量信息',
			iconCls : 'icon-edit',
			handler : function() {
				var node = $('#dg').datagrid('getSelected');
				if (node == null) {
					$.messager.alert('消息', '请选择一条记录修改', 'info');
					return false;
				}
				showWindow(basepath + 'companyCash/gotoPage.form?page=update', 600, 500, '修改现金流量信息');
			}
		}, '-', {
			text : '删除现金流量信息',
			iconCls : 'icon-cut',
			handler : function() {
				var node = $('#dg').treegrid('getSelected');
				if (node == null) {
					$.messager.alert('消息', '请选择一条记录进行操作', 'info');
					return false;
				}
				$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
					if (r) {
						ajaxUtils.post(basepath + "companyCash/remove.form?cash_sheet_id=" + node.cash_sheet_id, null, function(json) {
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
			text : '保存现金流量信息',
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
					url : basepath + "companyCash/saveCompanyCash.form",
					data : {"cash_sheet_id" : node.cash_sheet_id,
						"stock_code" : node.stock_code,
						"publish_date"  : node.publish_date,
						"end_date" : node.end_date,
						"account_date" : node.account_date,
						"report_period" : node.report_period,
						"sale_cash" : node.sale_cash,
						"tax_return" : node.tax_return,
						"rec_other_cash" : node.rec_other_cash,
						"bussiness_cash_total" : node.bussiness_cash_total,
						"buy_for_cash" : node.buy_for_cash,
						"pay_emp_cash" : node.pay_emp_cash,
						"pay_tax" : node.pay_tax,
						"pay_other_cash" : node.pay_other_cash,
						"bussiness_cash_output" : node.bussiness_cash_output,
						"bussiness_cash_netvalue" : node.bussiness_cash_netvalue,
						"rec_invest_cash" : node.rec_invest_cash,
						"invest_rec_cash" : node.invest_rec_cash,
						"dispose_asset_netvalue" : node.dispose_asset_netvalue,
						"dispose_other_netvalue" : node.dispose_other_netvalue,
						"rec_otherinvest_cash" : node.rec_otherinvest_cash,
						"invest_cash_total" : node.invest_cash_total,
						"buy_asset_cash" : node.buy_asset_cash,
						"invest_pay_cash" : node.invest_pay_cash,
						"loan_net_addvalue" : node.loan_net_addvalue,
						"rec_othercompany_cash" : node.rec_othercompany_cash,
						"pay_otherinvest_cash" : node.pay_otherinvest_cash,
						"invest_cash_output" : node.invest_cash_output,
						"invest_cash_netvalue" : node.invest_cash_netvalue,
						"rec_invest_reccash" : node.rec_invest_reccash,
						"rec_borrow_cash" : node.rec_borrow_cash,
						"publish_rec_cash" : node.publish_rec_cash,
						"rec_other_relatecash" : node.rec_other_relatecash,
						"borrow_cash_total" : node.borrow_cash_total,
						"pay_debet_cash" : node.pay_debet_cash,
						"interest_pay_cash" : node.interest_pay_cash,
						"pay_other_relatecash" : node.pay_other_relatecash,
						"borrow_cash_outtotal" : node.borrow_cash_outtotal,
						"borrow_cash_netvalue" : node.borrow_cash_netvalue,
						"rate_to_cash" : node.rate_to_cash,
						"other_to_cash" : node.other_to_cash,
						"cash_to_netadd" : node.cash_to_netadd,
						"origin_cash" : node.origin_cash,
						"last_cash" : node.last_cash,
						"addition" : node.addition,
						"netvalue_to_cash" : node.netvalue_to_cash,
						"netvalue" : node.netvalue,
						"plus_asset_loss" : node.plus_asset_loss,
						"asset_discount": node.asset_discount,
						"intangible_asset_discount" : node.intangible_asset_discount,
						"long_cost_discount" : node.long_cost_discount,
						"asset_loss" : node.asset_loss,
						"fix_asset_loss" : node.fix_asset_loss,
						"value_change_loss" : node.value_change_loss,
						"fin_cost" : node.fin_cost,
						"invest_loss" : node.invest_loss,
						"tax_reduce" : node.tax_reduce,
						"debt_reduce" : node.debt_reduce,
						"stock_reduce" : node.stock_reduce,
						"rec_project_reduce" : node.rec_project_reduce,
						"pay_project_add" : node.pay_project_add,
						"other" : node.other,
						"business_cash_netvalue" : node.business_cash_netvalue,
						"non_cash_netvalue" : node.non_cash_netvalue,
						"debt_to_capital" : node.debt_to_capital,
						"debt_one_year" : node.debt_one_year,
						"cash_to_asset" : node.cash_to_asset,
						"cash_change" : node.cash_change,
						"last_cash_value" : node.last_cash_value,
						"reduce_origin_cash" : node.reduce_origin_cash,
						"plus_last_cash" : node.plus_last_cash,
						"reduce_origin_value" : node.reduce_origin_value,
						"plus_other_cash" : node.plus_other_cash,
						"cash_to_netvalue" : node.cash_to_netvalue,
						"custom_to_netvalue" : node.custom_to_netvalue,
						"borrow_netvalue" : node.borrow_netvalue,
						"borrow_other_netvalue" : node.borrow_other_netvalue,
						"rec_insurance_cash" : node.rec_insurance_cash,
						"rec_insurance_netvalue" : node.rec_insurance_netvalue,
						"invest_netvalue" : node.invest_netvalue,
						"dispose_the_cash" : node.dispose_the_cash,
						"rec_interest_cash" : node.rec_interest_cash,
						"cash_netvalue" : node.cash_netvalue,
						"return_cash_netvalue" : node.return_cash_netvalue,
						"custom_netvalue" : node.custom_netvalue,
						"bank_cash_netvalue" : node.bank_cash_netvalue,
						"pay_insurance_cash" : node.pay_insurance_cash,
						"pay_interest_cash" : node.pay_interest_cash,
						"pay_profit_cash" : node.pay_profit_cash,
						"cash_for_holder" : node.cash_for_holder,
						"profit_for_holder" : node.profit_for_holder,
						"house_disount" : node.house_disount,
						"push_flag" : node.push_flag},
						success : function(result){
							editIndex = undefined;
							$('#dg').datagrid('reload');
						}
				});
				
			}
		}, '-', {
			text : '导入现金流量信息',
			iconCls : 'icon-large-chart',
			handler : function() {
				showWindow(basepath + 'companyCash/gotoPage.form?page=import', 350, 200, '导入现金流量信息');
			}
		}, '-', {
			text : '审核数据',
			iconCls : 'icon-ok',
			handler : function() {
				$.messager.confirm("提示", "您确定要提交数据吗？", function(r) {
					if (r) {
						ajaxUtils.post(basepath + "companyCash/commitCash.form", null, function(data) {
							var json = eval('(' + data + ')');
							if (json.result == 'success') {
								$('#dg').datagrid('reload');
								$.messager.alert('消息', '操作成功', 'info');
							} else {
								$.messager.alert('错误', '操作失败', 'error');
							}
						});
					}
				});
			}
		}];
		
		
	</script>
	
</body>
</html>