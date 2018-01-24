
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
	<form id="activityForm" action="${basePath}/enterprise/audit.form" method="post" style="padding: 20px; line-height: 35px;">

		<table width="100%" class="table">
			<tr>
				<th colspan="2" style="margin-top: 10px;">经办人信息</th>
			</tr>

			<input type="hidden" id="id" name="id" value="${enterprise.id }" />
			<input type="hidden" id="userId" name="userId" value="${enterprise.userId }" />
			<tr>
				<td width="20%" class="label">经办人手机号</td>
				<td><input type="text" id="linkmanPhone" name="linkmanPhone" class="easyui-validatebox" value="${enterprise.linkmanPhone}" data-options="required:true" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">经办人</td>
				<td><input type="text" id="linkman" name="linkman" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">证件类型</td>
				<td><input type="text" id="" name="" class="easyui-textbox" value="中国大陆居民身份证" readonly="readonly" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">经办人身份证号</td>
				<td><input type="text" id="legalIdNumber" name="legalIdNumber" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">经办人办公电话</td>
				<td><input type="text" id="companyTel" name="companyTel" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">经办人E-mail</td>
				<td><input type="text" id="email" name="email" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th colspan="2" style="border-top: 1px solid #ccc; margin-top: 10px;">公司信息</th>
			</tr>
			<tr>
				<td class="label">证件类型</td>
				<td><select id="licenseType" class="easyui-combobox" name="licenseType" style="width: 170px;" data-options="onChange:function(newValue,oldValue) {
																												if(newValue == '三证三号') {
																														$('#code').validatebox({'required':true});
																														$('#taxCertificate').validatebox({'required':true});
																												} else {
																													$('#code').validatebox({'required':false});
																													$('#taxCertificate').validatebox({'required':false});
																												}
																											},panelHeight:'auto'">
						<option value="一证一码">一证一码</option>
						<option value="三证三号">三证三号</option>
				</select></td>
			</tr>
			<tr>
				<td class="label">单位名称</td>
				<td><input type="text" id="companyName" name="companyName" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">单位详细地址</td>
				<td><input type="text" id="address" name="address" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">营业执照号</td>
				<td><input type="text" id="businessLicense" name="businessLicense" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">组织机构代码</td>
				<td><input type="text" id="code" name="code" class="easyui-validatebox" data-options="prompt:'格式为12345678-9'" /></td>
			</tr>
			<tr>
				<td class="label">税务登记号</td>
				<td><input type="text" id="taxCertificate" name="taxCertificate" class="easyui-validatebox" data-options="" /></td>
			</tr>
			<tr>
				<th colspan="2" style="border-top: 1px solid #ccc; margin-top: 10px;">法人信息</th>
			</tr>
			<tr>
				<td class="label">法定代表人</td>
				<td><input type="text" id="representative" name="representative" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="label">证件类型</td>
				<td><input type="text" id="" name="" class="easyui-textbox" value="中国大陆居民身份证" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">法定代表人身份证号</td>
				<td><input type="text" id="IDNumber" name="IDNumber" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th colspan="2" style="border-top: 1px solid #ccc; margin-top: 10px;">提现账户信息</th>
			</tr>
			<tr>
				<td class="label">账户类型</td>
				<td><input type="text" id="" name="" class="easyui-textbox" value="银行对公账户" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label">开户银行类型</td>
				<td><input id="accountBankType" class="easyui-combobox" name="accountBankType" data-options="prompt:'请选择',valueField:'bankCode',textField:'bankName',url:'enterprise/findBankList.form',editable:false,panelHeight: 200,required:true"> <input id="bankName1" name="bankName1" type="hidden"></td>
			</tr>
			<tr>
				<td class="label">开户银行区域</td>
				<td><input id="provinceCode" class="easyui-combobox" name="provinceCode" data-options="prompt:'请选择',valueField:'province',textField:'province',url:'enterprise/findProvinceList.form',editable:false,panelHeight: 200,required:true,
																				 onChange:function(newValue,oldValue) {
																				 	$('#cityCode').combobox({url:'enterprise/findCityList.form?provinceCode='+newValue})
																				 }" style="width: 80px;">省 <input id="cityCode" class="easyui-combobox" name="cityCode" data-options="prompt:'请选择',valueField:'code',textField:'city',url:'',editable:false,panelHeight: 200,required:true" style="width: 80px;">市</td>
			</tr>
			<tr>
				<td class="label">开户银行名称</td>
				<td><input id="accountBankName" class="easyui-combobox" name="accountBankName" data-options="prompt:'请选择',valueField:'brabank_name',textField:'brabank_name',editable:false,panelHeight: 200,required:true,
																				onShowPanel:function() {
																							var url = 'http://iweb.hanxinbank.com/lianlianApp/queryBankNameList.do';
																							var bankCode = $('#accountBankType').combobox('getValue');
																							var cityCode = $('#cityCode').combobox('getValue');
																						    $.post(url,{bank_code:bankCode,brabank_name:'行',city_code:cityCode}, function(json) {
																						    	console.info(json.data.data);
																						    	$('#accountBankName').combobox('loadData',json.data.data);
																						    },'json');
																							
																				}"></td>
			</tr>
			<tr>
				<td class="label">开户银行账号</td>
				<td><input type="text" id="accountNo" name="accountNo" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>


			<tr>
				<td style="padding-left: 20%;" colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">审核</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="backs()">返回</a></td>
			</tr>
		</table>

	</form>

	<script type="text/javascript">
	var basepath = '${basePath}';
	
	$(function() {
	    $('#activityForm').form('load', {
	    	linkmanPhone:'${enterprise.userName}',
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
	    	accountBankType:'${enterprise.accountBankType}',
	    	provinceCode:'${enterprise.provinceCode}',
	    	cityCode:'${enterprise.cityCode}',
	    	
	    	bankName1:'${enterprise.bankName1}',
	    	accountBankName:'${enterprise.accountBankName}',
	    	accountNo:'${enterprise.accountNo}',
	    	bankArea:'${bankArea}'
	    	
	    	
	    	
	    	
	    });
	})
	
	
	function save() {
		var progress = $.messager.progress({title:'审核',msg:'',interval:1000}); 
		$('#bankName1').val($('#accountBankType').combobox('getText'));
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
				backs();
			}
		});
	}
	
	function backs(){
		$('#centerArea').panel({
			href : basepath + '/pages/enterprise/list.jsp',
			onLoad : function() {
			}
		});
	}
	
	
</script>
</body>

