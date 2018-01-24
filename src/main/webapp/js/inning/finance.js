// 导出报表
function exportReport(userType) {
	window.open('finance/exportReport.form?userType='+userType);
	//$("#form").submit();
}

//格式化电话号码
function formatPhoneNumber(value) {
	var str = '';
	for(var i=0;i<value.length;i++) {
		if(i<3 || i>6) {
			str += value[i];
		} else {
			str += '*';
		}
	}
	return str;
}


//查询还款余额
function searchRepayment(id) {
	$('#'+id).datagrid('load', {beginDate:$('#beginDate').datebox('getValue'),endDate:$('#endDate').datebox('getValue')});   //点击搜索
}



        function downloaddata() {//整个表格拷贝到EXCEL中
            var uri = 'data:applicationnd.ms-excel;base64,',
               template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body><ml>',
                 base64 = function (s) { return window.btoa(unescape(encodeURIComponent(s))) },
                 format = function (s, c) {
                     return s.replace(/{(\w+)}/g,
                     function (m, p) { return c[p]; })
                 };

            table = $('#repayment_dg');// document.getElementById('#tabledata');
            alert($('#repayment_dg').html());
            var ctx = { worksheet: 'data' || 'Worksheet', table: $('#repayment_dg').html() };
            window.location.href = uri + base64(format(template, ctx));

        }
        
        
      //EasyUI datagrid 动态导出Excel  
        function ExporterExcel(dg) {  
                    //获取Datagride的列  
                    var rows = dg.datagrid('getRows');  
                    var columns = dg.datagrid("options").columns[0];  
                    var oXL = new ActiveXObject("Excel.Application"); //创建AX对象excel   
                    var oWB = oXL.Workbooks.Add(); //获取workbook对象   
                    var oSheet = oWB.ActiveSheet; //激活当前sheet  
                    //设置工作薄名称  
                    oSheet.name = "导出Excel报表";  
                    //设置表头  
                    for (var i = 0; i < columns.length; i++) {  
                        oSheet.Cells(1, i+1).value = columns[i].title;  
                    }  
                    //设置内容部分  
                    for (var i = 0; i < rows.length; i++) {  
                        //动态获取每一行每一列的数据值  
                        for (var j = 0; j < columns.length; j++) {                 
                            oSheet.Cells(i + 2, j+1).value = rows[i][columns[j].field];  
                        }     
                    }                
                    oXL.Visible = true; //设置excel可见属性  
        }  
        
//从后台导出数据
        function BackendExport() {
        	
        }
        
      
//交易记录条件查询
function searchFuioufundlog() {
	
	var phoneNumber= $('#phoneNumber').textbox('getValue');
	var dealType = $('#dealType').combobox('getValue');
	var beginDate = $('#beginDate').datebox('getValue');
	var endDate = $('#endDate').datebox('getValue');
	
	$('#fuioufundlog_dg').datagrid({url:'finance/getFuioufundlogList.form?phoneNumber='+phoneNumber+'&dealType='+dealType+'&beginDate='+beginDate+'&endDate'+endDate});
	
} 
        
  
        
//用户账户信息查询
function searchInfoByPhone() {
	$('#userInfoRecord_dg').datagrid('load', {phoneNumber:$('#phoneNumber').textbox('getValue')});   //点击搜索
	$('#profit_dg1').datagrid('load', {phoneNumber:$('#phoneNumber').textbox('getValue')});
	$('#profit_dg2').datagrid('load', {phoneNumber:$('#phoneNumber').textbox('getValue')});
	$('#redemption_dg').datagrid('load', {phoneNumber:$('#phoneNumber').textbox('getValue')});

}
        
        
        
        
        
        
        
        
        
        
        
        