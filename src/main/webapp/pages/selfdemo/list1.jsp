<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.io.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mine_con_1">
	<table cellpadding="0" cellspacing="0" class="title_table" toolbar="#addDemoBar">

		<tr class="tr_bj">
			<th class="td_6" style="width: 10%"></th>
			<th class="td_8	" style="width: 10%">身份证号</th>
			<th class="td_8" style="width: 15%">姓名</th>
			<th class="td_8" style="width: 15%">可用金额</th>
			<th class="td_8" style="width: 15%">冻结金额</th>
			<th class="td_8" style="width: 15%">操作</th>
		</tr>


		<tbody>

			<c:forEach items="${list}" var="userInfo" varStatus="vs">
				<c:if test="${vs.index % 2 == 0}">
					<tr>
				</c:if>
				<c:if test="${vs.index % 2 == 1}">
					<tr class="tr_2bj">
				</c:if>

				<td class="td_6" style="width: 10%"><input type="checkbox" name="demoCheckboxName" value="${userInfo.userId}" onclick="selectOnlyCheckbox(this)" /></td>
				<td class="td_8" style="width: 15%; text-align: center;">${userInfo.idNumber}</td>
				<td class="td_8" style="width: 25%; text-align: center;">${userInfo.fullName}</td>
				<td class="td_8" style="width: 25%; text-align: center;">${userInfo.canUseAmnout}</td>
				<td class="td_8" style="width: 15%; text-align: center;">${userInfo.freezeAmount}</td>
				<td><a href="#" id="updateDemoButton" onclick="updateSelfUser('${userInfo.userId}')" class="easyui-linkbutton" iconCls="icon-edit">冻结</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>
<div id="resultDemoButtom" class="page">
	<script>
			$(function(){
				$('#resultDemoButtom').pagination({total:${userInfo.totalRows},pageSize:${userInfo.pageRows},pageList:[10,15,20,50,100],pageNumber:${userInfo.curPage},onSelectPage:function(pageNumber, pageSize){
						addTab(basepath + 'demo/selfQueryList.form?curPage='+pageNumber+"&pageRows="+pageSize,null);
					}
				});
			});
		</script>
</div>

