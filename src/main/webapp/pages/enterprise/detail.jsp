
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<body>
	<style type="text/css">
.table td {
	padding: 0 10px;
}

.table .label {
	text-align: right;
}

.table th {
	padding-left: 20px;
}
</style>
	<form id="activityForm" action="" method="post" style="padding: 20px; line-height: 35px;">

		<table width="100%" class="table">
			<tr>
				<th colspan="2" style="margin-top: 10px;">经办人信息</th>
			</tr>
			<tr>
				<td width="20%" class="label">经办人手机号</td>
				<td><input type="text" id="linkmanPhone" name="linkmanPhone" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">经办人</td>
				<td><input type="text" id="linkman" name="linkman" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">证件类型</td>
				<td><input type="text" id="" name="" class="easyui-textbox" value="中国大陆居民身份证" readonly="readonly" data-options="width:300" /></td>
			</tr>
			<tr>
				<td class="label">经办人身份证号</td>
				<td><input type="text" id="legalIdNumber" name="legalIdNumber" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">经办人办公电话</td>
				<td><input type="text" id="companyTel" name="companyTel" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">经办人E-mail</td>
				<td><input type="text" id="email" name="email" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<th colspan="2" style="border-top: 1px solid #ccc; margin-top: 10px;">公司信息</th>
			</tr>
			<tr>
				<td class="label">证件类型</td>
				<td><input type="text" id="licenseType" name="licenseType" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">单位名称</td>
				<td><input type="text" id="companyName" name="companyName" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">单位详细地址</td>
				<td><input type="text" id="address" name="address" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">营业执照号</td>
				<td><input type="text" id="businessLicense" name="businessLicense" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">组织机构代码</td>
				<td><input type="text" id="code" name="code" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">税务登记号</td>
				<td><input type="text" id="taxCertificate" name="taxCertificate" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<th colspan="2" style="border-top: 1px solid #ccc; margin-top: 10px;">法人信息</th>
			</tr>
			<tr>
				<td class="label">法定代表人</td>
				<td><input type="text" id="representative" name="representative" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">证件类型</td>
				<td><input type="text" id="" name="" class="easyui-textbox" value="中国大陆居民身份证" readonly="readonly" data-options="width:300" /></td>
			</tr>
			<tr>
				<td class="label">法定代表人身份证号</td>
				<td><input type="text" id="IDNumber" name="IDNumber" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>
			<tr>
				<th colspan="2" style="border-top: 1px solid #ccc; margin-top: 10px;">提现账户信息</th>
			</tr>
			<tr>
				<td class="label">账户类型</td>
				<td><input type="text" id="" name="" class="easyui-textbox" value="银行对公账户" readonly="readonly" data-options="width:300" /></td>
			</tr>
			<tr>
				<td class="label">开户银行类型</td>
				<td><input id="bankName1" name="bankName1" class="easyui-textbox" readonly="readonly" data-options="width:300"></td>
			</tr>
			<tr>
				<td class="label">开户银行区域</td>
				<td><input id="bankArea" name="bankArea" class="easyui-textbox" readonly="readonly" data-options="width:300"></td>
			</tr>
			<tr>
				<td class="label">开户银行名称</td>
				<td><input id="accountBankName" name="accountBankName" class="easyui-textbox" readonly="readonly" data-options="width:300"></td>
			</tr>
			<tr>
				<td class="label">开户银行账号</td>
				<td><input type="text" id="accountNo" name="accountNo" class="easyui-textbox" data-options="width:300" readonly="readonly" /></td>
			</tr>


			<tr>
				<td style="padding-left: 20%;" colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="backs()">返回</a></td>
			</tr>
		</table>

	</form>

	<script type="text/javascript">
	var basepath = '${basePath}';
	
	
	$(function() {
	    $('#activityForm').form('load', {
	    	linkmanPhone:'${enterprise.linkmanPhone}',
	    	linkman:'${enterprise.linkman}',
	    	IDNumber:'${enterprise.IDNumber}',
	    	companyTel:'${enterprise.companyTel}',
	    	email:'${enterprise.email}',
	    	licenseType:'${enterprise.licenseType}',
	    	companyName:'${enterprise.companyName}',
	    	address:'${enterprise.address}',
	    	businessLicense:'${enterprise.businessLicense}',
	    	code:'${enterprise.code}',
	    	taxCertificate:'${enterprise.taxCertificate}',
	    	representative:'${enterprise.representative}',
	    	legalIdNumber:'${enterprise.legalIdNumber}',
	    	bankName1:'${enterprise.bankName1}',
	    	accountBankName:'${enterprise.accountBankName}',
	    	accountNo:'${enterprise.accountNo}',
	    	bankArea:'${bankArea}'
	    	
	    	
	    	
	    	
	    });
	})
	
	
	function backs(){
		$('#centerArea').panel({
			href : basepath + '/pages/enterprise/list.jsp',
			onLoad : function() {
			}
		});
	}
	
	
</script>
</body>

