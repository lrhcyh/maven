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
	<div data-options="region:'north',title:'公司资产负债信息',overflow:'show',collapsible:false" style="height: 85px">
	
	<form id="searchForm" method="post">
		<table align="center">
			<tr align="center">
					<td align="right" width="80px" margin="10px">股票代码</td>
					<td align="right" width="60px" margin="10px">
						<input id="stockCode" name="stockCode" class="easyui-textbox" data-options="">
					</td>
					<td align="right" width="140px" margin="10px">截止日期开始时间</td>
					<td align="right" width="60px" margin="10px">
						<input id="end_date_start" name="end_date" class="easyui-datebox" data-options="">
					</td>
					<td align="right" width="140px" margin="10px">截止日期结束时间</td>
					<td align="right" width="60px" margin="10px">
						<input id="end_date_end" name="end_date" class="easyui-datebox" data-options="">
					</td>
					<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
					<td width="120px" height="50px"><a id="btn" onclick="searchRepeat()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">重复数据</a></td>
			</tr>
		</table>
	</form>
	</div>
	<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
		<table id="dg" class="easyui-datagrid" style="width: inherit; height: 100%" data-options=" iconCls: 'icon-edit', singleSelect: true,
				url: 'companyBalance/findList.form',
				toolbar: toolbar,
				method: 'POST',
				pageList: [200,500,1000,2000,5000],
				pagination:true,checkbox:true,
				onClickCell: onClickCell
			">
		
			<thead>
				<tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <input type="hidden" id="balance_sheet_id" name="balance_sheet_id">
					<th data-options="field:'stock_code',width:80,align:'center',editor:{type:'text'}">股票代码</th>
					<th data-options="field:'publish_date',width:100,align:'center',editor:{type:'datebox'}">公告日期</th>
					<th data-options="field:'end_date',width:100,align:'center',editor:{type:'datebox'}">截止日期</th>
					<th data-options="field:'account_date',width:100,align:'center',editor:{type:'datebox'}">报告年度</th>
					<th data-options="field:'report_period',width:50,align:'center',editor:{type:'numberbox'}">报告期</th>
					<th data-options="field:'cash',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">货币资金</th>
					<th data-options="field:'trading_fin_asset',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">以公允价值计量且其变动计入当期损益的金融资产</th>
					<th data-options="field:'rec_note',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">应收票据</th>
					<th data-options="field:'rec_account',width:120,align:'center',editor:{type:'numberbox',options:{precision:2}}">应用转账</th>
					<th data-options="field:'prepay',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">预付款项</th>
					<th data-options="field:'other_rec_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他应收款</th>
					<th data-options="field:'rec_relate_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应收关联公司款</th>
					<th data-options="field:'rec_interest',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应收利息</th>
					<th data-options="field:'rec_dividend',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应收股利</th>
					<th data-options="field:'stock',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">存货</th>
					<th data-options="field:'consume_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">消耗性生产物资</th>
					<th data-options="field:'non_current_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">一年内到期的给流动资产</th>
					<th data-options="field:'other_current_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他流动资产</th>
					<th data-options="field:'total_current_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">流动资产合计</th>
					<th data-options="field:'available_sale_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">可供出售金融资产</th>
					<th data-options="field:'held_investment',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">持有至到期投资</th>
					<th data-options="field:'long_rec_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">长期应收款</th>
					<th data-options="field:'long_equity_investment',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">长期股权投资</th>
					<th data-options="field:'invest_house',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">投资性房地产</th>
					<th data-options="field:'fix_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">固定资产</th>
					<th data-options="field:'building',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">在建工程</th>
					<th data-options="field:'project_material',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">工程物资</th>
					<th data-options="field:'fix_asset_dispose',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">固定资产清理</th>
					<th data-options="field:'product_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">生产性生物资产</th>
					<th data-options="field:'oil_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">油气资产</th>
					<th data-options="field:'intangible_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">无形资产</th>
					<th data-options="field:'develop_cost',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">开发支出</th>
					<th data-options="field:'goodwill',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">商誉</th>
					<th data-options="field:'long_defer_cost',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">长期待摊费用</th>
					<th data-options="field:'tax_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">递延所得税资产</th>
					<th data-options="field:'other_noncurrent_asset',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他非流动资产</th>
					<th data-options="field:'noncurrent_asset_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">非流动资产合计</th>
					<th data-options="field:'asset_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">资产总计</th>
					<th data-options="field:'short_borrow',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">短期借款</th>
					<th data-options="field:'trading_fin_borrow',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">以公允价值计量且其变动计入当期损益的金融负债</th>
					<th data-options="field:'pay_note',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应付票据</th>
					<th data-options="field:'pay_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应付账款</th>
					<th data-options="field:'prepay_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">预收款项</th>
					<th data-options="field:'pay_salary',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应付职工薪酬</th>
					<th data-options="field:'pay_tax',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应交税费</th>
					<th data-options="field:'pay_interest',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应付利息</th>
					<th data-options="field:'pay_dividend',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应付股利</th>
					<th data-options="field:'other_pay_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他应付款</th>
					<th data-options="field:'pay_relate_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应付关联公司款</th>
					<th data-options="field:'non_current_borrow',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">一年内到期的非流动负债</th>
					<th data-options="field:'other_current_borrow',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他流动负债</th>
					<th data-options="field:'current_borrow_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">流动负债合计</th>
					<th data-options="field:'long_borrow',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">长期借款</th>
					<th data-options="field:'pay_bonds',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">应付债券</th>
					<th data-options="field:'long_pay_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">长期应付款</th>
					<th data-options="field:'term_pay_account',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">专项应付款</th>
					<th data-options="field:'pre_bonds',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">预计负债</th>
					<th data-options="field:'tax_bonds',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">递延所得税负债</th>
					<th data-options="field:'other_noncurrent_bonds',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">其他非流动负债</th>
					<th data-options="field:'noncurrent_bonds_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">非流动负债合计</th>
					<th data-options="field:'bonds_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">负债合计</th>
					<th data-options="field:'rec_capital',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">实收资本（或股本）</th>
					<th data-options="field:'capital_reserve',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">资本公积</th>
					<th data-options="field:'earn_reserve',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">盈余公积</th>
					<th data-options="field:'reduce_share',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">减：库存股</th>
					<th data-options="field:'nopay_profit',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">未分配利润</th>
					<th data-options="field:'monority_holder_equity',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">少数股东权益</th>
					<th data-options="field:'exchange_difference',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">外币报表折算价差</th>
					<th data-options="field:'profit_adjust',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">非正常经营项目收益调整</th>
					<th data-options="field:'equity_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">所有者权益（或股东权益）合计</th>
					<th data-options="field:'all_total',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">负债和所有者（或股东权益）合计</th>
					<th data-options="field:'parent_equity',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">归属于母公司所有者权益</th>
					<th data-options="field:'total_depre_amor',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">累计折旧和摊销</th>
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
// 					$('#dg').datagrid('selectRow', index)
// 							.datagrid('beginEdit', index);
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
			text : '添加资产信息',
			iconCls : 'icon-add',
			handler : function() {
				showWindow(basepath + 'companyBalance/gotoPage.form?page=add', 600, 500, '添加资产信息');
			}
		}, '-', {
			text : '修改资产信息',
			iconCls : 'icon-edit',
			handler : function() {
				var node = $('#dg').datagrid('getSelected');
				if (node == null) {
					$.messager.alert('消息', '请选择一条记录修改', 'info');
					return false;
				}
				showWindow(basepath + 'companyBalance/gotoPage.form?page=update', 600, 500, '修改资产信息');
			}
		}, '-', {
			text : '删除资产信息',
			iconCls : 'icon-cut',
			handler : function() {
				var node = $('#dg').treegrid('getSelected');
				if (node == null) {
					$.messager.alert('消息', '请选择一条记录进行操作', 'info');
					return false;
				}
				$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
					if (r) {
						ajaxUtils.post(basepath + "companyBalance/remove.form?balance_sheet_id=" + node.balance_sheet_id, null, function(json) {
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
			text : '保存资产信息',
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
					url : basepath + "companyBalance/saveCompanyBalance.form",
					data : {"balance_sheet_id" : node.balance_sheet_id,
						"stock_code" : node.stock_code,
						"publish_date"  : node.publish_date,
						"end_date" : node.end_date,
						"account_date" : node.account_date,
						"report_period" : node.report_period,
						"cash" : node.cash,
						"trading_fin_asset" : node.trading_fin_asset,
						"rec_note" : node.rec_note,
						"rec_account" : node.rec_account,
						"prepay" : node.prepay,
						"other_rec_account" : node.other_rec_account,
						"rec_relate_account" : node.rec_relate_account,
						"rec_interest" : node.rec_interest,
						"rec_dividend" : node.rec_dividend,
						"stock" : node.stock,
						"consume_asset" : node.consume_asset,
						"non_current_asset" : node.non_current_asset,
						"other_current_asset" : node.other_current_asset,
						"total_current_asset" : node.total_current_asset,
						"available_sale_asset" : node.available_sale_asset,
						"held_investment" : node.held_investment,
						"long_rec_account" : node.long_rec_account,
						"long_equity_investment" : node.long_equity_investment,
						"invest_house" : node.invest_house,
						"fix_asset" : node.fix_asset,
						"building" : node.building,
						"project_material" : node.project_material,
						"fix_asset_dispose" : node.fix_asset_dispose,
						"product_asset" : node.product_asset,
						"oil_asset" : node.oil_asset,
						"intangible_asset" : node.intangible_asset,
						"develop_cost" : node.develop_cost,
						"goodwill" : node.goodwill,
						"long_defer_cost" : node.long_defer_cost,
						"tax_asset" : node.tax_asset,
						"other_noncurrent_asset" : node.other_noncurrent_asset,
						"noncurrent_asset_total" : node.noncurrent_asset_total,
						"asset_total" : node.asset_total,
						"short_borrow" : node.short_borrow,
						"trading_fin_borrow" : node.trading_fin_borrow,
						"pay_note" : node.pay_note,
						"pay_account" : node.pay_account,
						"prepay_account" : node.prepay_account,
						"pay_salary" : node.pay_salary,
						"pay_tax" : node.pay_tax,
						"pay_interest" : node.pay_interest,
						"pay_dividend" : node.pay_dividend,
						"other_pay_account" : node.other_pay_account,
						"pay_relate_account" : node.pay_relate_account,
						"non_current_borrow" : node.non_current_borrow,
						"other_current_borrow" : node.other_current_borrow,
						"current_borrow_total" : node.current_borrow_total,
						"long_borrow" : node.long_borrow,
						"pay_bonds" : node.pay_bonds,
						"long_pay_account" : node.long_pay_account,
						"term_pay_account" : node.term_pay_account,
						"pre_bonds" : node.pre_bonds,
						"tax_bonds" : node.tax_bonds,
						"other_noncurrent_bonds" : node.other_noncurrent_bonds,
						"noncurrent_bonds_total" : node.noncurrent_bonds_total,
						"bonds_total" : node.bonds_total,
						"rec_capital" : node.rec_capital,
						"capital_reserve" : node.capital_reserve,
						"earn_reserve" : node.earn_reserve,
						"reduce_share" : node.reduce_share,
						"nopay_profit" : node.nopay_profit,
						"monority_holder_equity" : node.monority_holder_equity,
						"exchange_difference" : node.exchange_difference,
						"profit_adjust" : node.profit_adjust,
						"equity_total" : node.equity_total,
						"all_total" : node.all_total,
						"parent_equity" : node.parent_equity,
						"total_depre_amor" : node.total_depre_amor,
						"push_flag" : node.push_flag},
						success : function(result){
							editIndex = undefined;
							$('#dg').datagrid('reload');
						}
				});
				
			}
		}, '-', {
			text : '导入资产负债信息',
			iconCls : 'icon-large-chart',
			handler : function() {
				showWindow(basepath + 'companyBalance/gotoPage.form?page=import', 350, 200, '导入资产负债信息');
			}
		}, '-', {
			text : '审核数据',
			iconCls : 'icon-ok',
			handler : function() {
				$.messager.confirm("提示", "您确定要提交数据吗？", function(r) {
					if (r) {
						ajaxUtils.post(basepath + "companyBalance/commitBalance.form", null, function(data) {
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