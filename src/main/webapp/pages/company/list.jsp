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
		var stockName = $('#stockName').textbox('getValue');
		
		$("#dg").datagrid('reload', {
			stockCode : stockCode,
			stockName : stockName
		});
	}
	
	function searchListRepeat() {
		$("#dg").datagrid('load', {
			keyword: 'distinct'
		});
	}

    var toolbar = [ {
		text : '添加公司',
		iconCls : 'icon-add',
		handler : function() {
			showWindow(basepath + '/company/gotoPage.form?page=add', 600, 500, '添加公司');
		}
	}, '-', {
		text : '修改公司',
		iconCls : 'icon-edit',
		handler : function() {
			var node = $('#dg').datagrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录修改', 'info');
				return false;
			}
			showWindow(basepath + '/company/gotoPage.form?page=edit', 600, 500, '修改公司');
		}
	}, '-', {
		text : '删除公司',
		iconCls : 'icon-cut',
		handler : function() {
			var node = $('#dg').treegrid('getSelected');
			if (node == null) {
				$.messager.alert('消息', '请选择一条记录进行操作', 'info');
				return false;
			}
			$.messager.confirm("提示", "您确定要删除选中的吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "/company/remove.form?id=" + node.id, null, function(json) {
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
		text : '保存公司信息',
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
				url : basepath + "company/saveCompanyBasic.form",
				data : {
					id : node.id,
					stock_code : node.stock_code,
					stock_category : node.stock_category,
					stock_name : node.stock_name,
					company_name : node.company_name,
					company_ename: node.company_ename,
					industry_name_csrc : node.industry_name_csrc,
					industry_code : node.industry_code,
					industry_name : node.industry_name,
					found_date : node.found_date,
					register_capital : node.register_capital,
					legal_man : node.legal_man,
					stock_type : node.stock_type,
					ipo_date : node.ipo_date,
					ipo_main_saler : node.ipo_main_saler,
					concept : node.concept,
					province : node.province,
					city : node.city,
					company_character : node.company_character,
					directors : node.directors,
					emp_sum : node.emp_sum,
					register_number : node.register_number,
					register_address : node.register_address,
					office_address : node.office_address,
					majo_rproduct_type : node.majo_rproduct_type,
					major_product_name : node.major_product_name,
					phone : node.phone,
					zipcode : node.zipcode,
					fax : node.fax,
					website : node.website,
					email : node.email,
					link_man : node.link_man,
					company_brief : node.company_brief,
					business : node.business,
					wind_code : node.wind_code,
					closing_price : node.closing_price,
					price_date : node.price_date,
					board_chair_men : node.board_chair_men,
					register_addressCopy : node.register_addressCopy
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
			showWindow(basepath + 'company/gotoPage.form?page=import', 350, 200, '批量导入公司基本信息');
		}
	}, '-', {
		text : '审核数据',
		iconCls : 'icon-ok',
		handler : function() {
			$.messager.confirm("提示", "您确定要提交数据吗？", function(r) {
				if (r) {
					ajaxUtils.post(basepath + "company/importCompanyBasic.form", null, function(data) {
						var json = eval('(' + data + ')');
						if (json.result == 'success') {
							$('#dg').datagrid('reload');
							$.messager.alert('消息', '操作成功', 'info');
						} else {
							$('#dg').datagrid('reload');
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
						<td align="right" width="80px" margin="10px">股票代码:</td>
						<td align="right" width="60px" margin="10px"><input id="stockCode" name="stockCode" class="easyui-textbox" data-options=""></td>
						<td align="right" width="80px" margin="10px">股票名称:</td>
						<td align="right" width="60px" margin="10px"><input id="stockName" name="stockName" class="easyui-textbox" data-options=""></td>
						<td width="80px" height="50px"><a id="btn" onclick="searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
						<td width="100px" height="50px"><a id="btn" onclick="searchListRepeat()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">重复数据</a></td>
				</tr>
			</table>
			</form>
		</div>
		<div style="width: 100%; height: auto;" data-options="region:'center',border:false">
			<table id="dg" class="easyui-datagrid"  style="width: inherit; height: 100%" data-options="iconCls:'icon-edit',singleSelect:true,
							  url:'company/findList.form',
							  toolbar: toolbar,
							  method:'POST', 
							  pageList: [200,500,1000,2000,5000],
							  pagination:true,checkbox:true,onClickCell: onClickCell
							  ">
				<thead>
					<tr>
                        <th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'stock_code',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}">股票编号</th>
						<th data-options="field:'stock_category',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}">股票类型</th>
						<th data-options="field:'stock_name',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}">股票简称</th>
						<th data-options="field:'company_name',width:220,align:'center',editor:{type:'validatebox',options:{required:true}}">公司中文名称</th>
						<th data-options="field:'company_ename',width:400,align:'center',editor:{type:'validatebox',options:{required:true}}">公司英文名称</th>
						<th data-options="field:'industry_name_csrc',width:380,align:'center',editor:{type:'validatebox',options:{required:false}}">证监会行业名称</th>
						<th data-options="field:'industry_code',width:120,align:'center',editor:{type:'validatebox',options:{required:false}}">行业代码</th>
						<th data-options="field:'industry_name',width:150,align:'center',editor:{type:'validatebox',options:{required:false}}">行业名称</th>
						<th data-options="field:'found_date',width:100,align:'center',editor:{type:'datebox',options:{required:false}}">公司成立日期</th>
						<th data-options="field:'register_capital',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">注册资本</th>
						<th data-options="field:'legal_man',width:70,align:'center',editor:{type:'validatebox',options:{required:false}}">法定代表人</th>
						<th data-options="field:'stock_type',width:70,align:'center',editor:{type:'validatebox',options:{required:false}}">证券类型</th>
						<th data-options="field:'ipo_date',width:100,align:'center',editor:{type:'datebox',options:{required:false}}">首发上市日期</th>
						<th data-options="field:'ipo_main_saler',width:180,align:'center',editor:{type:'validatebox',options:{required:false}}">首发主承销商</th>
						<th data-options="field:'concept',width:370,align:'center',editor:{type:'validatebox',options:{required:false}}">所属概念块</th>
						<th data-options="field:'province',width:70,align:'center',editor:{type:'validatebox',options:{required:false}}">省份</th>
						<th data-options="field:'city',width:70,align:'center',editor:{type:'validatebox',options:{required:false}}">城市</th>
						<th data-options="field:'company_character',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">公司属性</th>
						<th data-options="field:'directors',width:180,align:'center',editor:{type:'validatebox',options:{required:false}}">公司独立董事(现任)</th>
						<th data-options="field:'emp_sum',width:70,align:'center',editor:{type:'validatebox',options:{required:false}}">员工总数</th>
						<th data-options="field:'register_number',width:135,align:'center',editor:{type:'validatebox',options:{required:false}}">工商登记号</th>
						<th data-options="field:'register_address',width:376,align:'center',editor:{type:'validatebox',options:{required:false}}">注册地址</th>
						<th data-options="field:'office_address',width:335,align:'center',editor:{type:'validatebox',options:{required:false}}">办公地址</th>
						<th data-options="field:'majo_rproduct_type',width:100,align:'center',editor:{type:'validatebox',options:{required:false}}">主营产品类型</th>
						<th data-options="field:'major_product_name',width:700,align:'center',editor:{type:'validatebox',options:{required:false}}">主营产品名称</th>
						<th data-options="field:'phone',width:340,align:'center',editor:{type:'validatebox',options:{required:false}}">公司电话</th>
						<th data-options="field:'zipcode',width:50,align:'center',editor:{type:'validatebox',options:{required:false}}">邮政编码</th>
						<th data-options="field:'fax',width:235,align:'center',editor:{type:'validatebox',options:{required:false}}">公司传真</th>
						<th data-options="field:'website',width:230,align:'center',editor:{type:'validatebox',options:{required:false}}">公司网址</th>
						<th data-options="field:'email',width:380,align:'center',editor:{type:'validatebox',options:{required:false}}">公司电子邮件地址</th>
						<th data-options="field:'link_man',width:50,align:'center',editor:{type:'validatebox',options:{required:false}}">信息披露人</th>
						<th data-options="field:'company_brief',width:700,align:'center',editor:{type:'validatebox',options:{required:false}}">公司简介</th>
						<th data-options="field:'business',width:300,align:'center',editor:{type:'validatebox',options:{required:false}}">经营范围</th>
						<th data-options="field:'wind_code',width:80,align:'center',editor:{type:'validatebox',options:{required:false}}">万德代码</th>
						<th data-options="field:'closing_price',width:70,align:'center',editor:{type:'validatebox',options:{required:false}}">收盘价</th>
						<th data-options="field:'price_date',width:100,align:'center',editor:{type:'datebox',options:{required:false}}">收盘价日期</th>
						<th data-options="field:'board_chair_men',width:50,align:'center',editor:{type:'validatebox',options:{required:false}}">董事长</th>
						<th data-options="field:'register_addressCopy',width:200,align:'center',editor:{type:'validatebox',options:{required:false}}">注册地址</th>
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
