
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form id="activityForm" action="${basePath}company/editCompany.form" method="post" style="margin: 10px 30px; line-height: 35px;">

	<table>
		<tr>
			<td>股票编号</td>
			<input type="hidden" id="id" name="id">
			<td><input type="text" id="stock_code" name="stock_code" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		
		<tr>
			<td>股票类型</td>
			<td><input id="stock_category" name="stock_category" class="easyui-combobox" data-options="
				'valueField': 'label',
				textField: 'value',
				data: [{
					label: 'A股',
					value: 'A股'
				},{
					label: 'B股',
					value: 'B股'
				},{
					label: 'H股',
					value: 'H股'
				},{
					label: 'N股',
					value: 'N股'
				},{
					label: 'S股',
					value: 'S股'
				}]" />
			</td>
		</tr>
		
		<tr>
			<td>股票简称</td>
			<td><input id="stock_name" name="stock_name" type="text" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		
		<tr>
			<td>公司中文名称</td>
			<td><input id="company_name" name="company_name" type="text" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		
		<tr>
			<td>公司英文名称</td>
			<td><input id="company_ename" name="company_ename" type="text" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		
		<tr>
			<td>证监会行业名称</td>
			<td><input id="industry_name_csrc" name="industry_name_csrc" type="text" class="easyui-validatebox" data-options="required:true"></td>
		</tr>
		
		<tr>
			<td>行业代码</td>
			<td><input id="industry_code" name="industry_code" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		
		<tr>
			<td>行业名称</td>
			<td><input id="industry_name" name="industry_name" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		
		<tr>
			<td>公司成立日期</td>
			<td><input id="found_date" name="found_date" type="text" class="easyui-datebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>注册资本</td>
			<td><input id="register_capital" name="register_capital" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>法定代表人</td>
			<td><input id="legal_man" name="legal_man" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>证券类型</td>
			<td><input id="stock_type" name="stock_type" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>首发上市日期</td>
			<td><input id="ipo_date" name="ipo_date" type="text" class="easyui-datebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>首发主承销商</td>
			<td><input id="ipo_main_saler" name="ipo_main_saler" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>所属概念块</td>
			<td><input id="concept" name="concept" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>省份</td>
			<td><input id="province" name="province" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>城市</td>
			<td><input id="city" name="city" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公司属性</td>
			<td><input id="company_character" name="company_character" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公司独立董事(现任)</td>
			<td><input id="directors" name="directors" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>员工总数</td>
			<td><input id="emp_sum" name="emp_sum" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>工商登记号</td>
			<td><input id="register_number" name="register_number" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>注册地址</td>
			<td><input id="register_address" name="register_address" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>办公地址</td>
			<td><input id="office_address" name="office_address" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>主营产品类型</td>
			<td><input id="majo_rproduct_type" name="majo_rproduct_type" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>主营产品名称</td>
			<td><input id="major_product_name" name="major_product_name" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公司电话</td>
			<td><input id="phone" name="phone" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>邮政编码</td>
			<td><input id="zipcode" name="zipcode" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公司传真</td>
			<td><input id="fax" name="fax" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公司网址</td>
			<td><input id="website" name="website" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公司电子邮件地址</td>
			<td><input id="email" name="email" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>信息披露人</td>
			<td><input id="link_man" name="link_man" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>公司简介</td>
			<td><input id="company_brief" name="company_brief" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>经营范围</td>
			<td><input id="business" name="business" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>万德代码</td>
			<td><input id="wind_code" name="wind_code" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收盘价</td>
			<td><input id="closing_price" name="closing_price" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>收盘价日期</td>
			<td><input id="price_date" name="price_date" type="text" class="easyui-datebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>董事长</td>
			<td><input id="board_chair_men" name="board_chair_men" type="text" class="easyui-validatebox" data-options="required:false"></td>
		</tr>
		<tr>
			<td>注册地址</td>
			<td><input id="register_addressCopy" name="register_addressCopy" type="text" class="easyui-validatebox" data-options="required:false"></td>
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
			id : row.id,
			stock_code : row.stock_code,
			stock_category : row.stock_category,
			stock_name : row.stock_name,
			company_name : row.company_name,
			company_ename: row.company_ename,
			industry_name_csrc : row.industry_name_csrc,
			industry_code : row.industry_code,
			industry_name : row.industry_name,
			found_date : row.found_date,
			register_capital : row.register_capital,
			legal_man : row.legal_man,
			stock_type : row.stock_type,
			ipo_date : row.ipo_date,
			ipo_main_saler : row.ipo_main_saler,
			concept : row.concept,
			province : row.province,
			city : row.city,
			company_character : row.company_character,
			directors : row.directors,
			emp_sum : row.emp_sum,
			register_number : row.register_number,
			register_address : row.register_address,
			office_address : row.office_address,
			majo_rproduct_type : row.majo_rproduct_type,
			major_product_name : row.major_product_name,
			phone : row.phone,
			zipcode : row.zipcode,
			fax : row.fax,
			website : row.website,
			email : row.email,
			link_man : row.link_man,
			company_brief : row.company_brief,
			business : row.business,
			wind_code : row.wind_code,
			closing_price : row.closing_price,
			price_date : row.price_date,
			board_chair_men : row.board_chair_men,
			register_addressCopy : row.register_addressCopy
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
					$.messager.progress('close'); // hide progress bar while the form is invalid
				}
				return isValid; // return false will stop the form submission
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