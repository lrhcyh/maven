<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="managerApprove" method="post">
	<input type="hidden" id="operatorId" value="${user}" />
	<div>
		<table cellpadding="0" cellspacing="0" class="formtable">
			<tr>
				<td class="td_1" style="width: 30%">用户ID</td>
				<td class="td_2" style="width: 70%">${user}</td>
			</tr>
			<tr>
				<td class="td_1" style="width: 30%">原始密码：</td>
				<td class="td_2" style="width: 70%"><input type="password" id="operatorPassword" class="easyui-validatebox" required="true" validType="length[6,12]" class="easyui-validatebox" required="true" name="backOperator.operatorPassword" /></td>
			</tr>
			<tr>
				<td class="td_1" style="width: 30%">新密码：</td>
				<td class="td_2" style="width: 70%"><input type="password" id="newOperatorPassword" class="easyui-validatebox" required="true" validType="length[6,12]" class="easyui-validatebox" required="true" name="backOperator.operatorPasswordConf" /></td>
			</tr>
			<tr>
				<td class="td_1" style="width: 30%">密码确认：</td>
				<td class="td_2" style="width: 70%"><input id="newOperatorPasswordConf" type="password" required="true" class="easyui-validatebox" validType="equalise[$('#newOperatorPassword').val()]" name="newOperatorPasswordConf" /></td>
			</tr>
			<tr class="form-btnwrapper">
				<td class="td_1" style="width: 30%"></td>
				<td class="td_2" style="width: 70%"><a href="#" class="easyui-linkbutton blue-btn" onclick="modifyPassword()" style="width: 60px; margin-right: 15px;">保存</a> <a href="#" class="easyui-linkbutton" onclick="closeWin()" style="width: 60px;">取消</a></td>
			</tr>

		</table>
	</div>
</form>

