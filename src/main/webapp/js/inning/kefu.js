// 导出报表
function exportKeFuReport() {
	var beginDate = $('#beginDate').datebox('getValue');
	var endDate = $('#endDate').datebox('getValue');
	window.location.href = ('keFuPhoneAnalysis/exportKeFuReport.form?beginDate=' + beginDate + '&endDate=' + endDate);

	// $("#form").submit();
}

// 格式化电话号码
function formatPhoneNumber(value) {
	var str = '';
	for (var i = 0; i < value.length; i++) {
		if (i < 3 || i > 6) {
			str += value[i];
		} else {
			str += '*';
		}
	}
	return str;
}

// 条件查询
function searchKefu() {
	$('#dg').datagrid('load', {
		beginDate : $('#beginDate').datebox('getValue'),
		endDate : $('#endDate').datebox('getValue')
	}); // 点击搜索
}

function searchBirthday() {
	$('#dg').datagrid('load', {
		days : $('#days').combobox('getValue'),
		userLevel : $('#userLevel').combobox('getValue')
	}); // 点击搜索
}