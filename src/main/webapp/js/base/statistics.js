var myShow=new Array();
var selectType=0;
var tabType2=0;
var excelType=0;
var myChart
//打开2维统计表
function open(){
	$('#twoWindow').window('open');
}
$("#twoStatis").off().click(function(){
	$("#tabs").tabs("select",1);
	$('#twoWindow').window('open');
})
//关闭二维统计表
function out(){
	$('#twoWindow').window('close');
}


//将统计信息存入数据库
function save(){
	$.messager.prompt('请输入统计名称', '信息提', function(r){
		
		if (r!="undefined"){
			
			if (r==""){
				$.messager.alert('消息提示','请输入信息名称!');
			}else{
				
			}
		}
	});
}

$("#oneStatis").off().on('click',function(){

	$("#tabs").tabs("select",0);
	$("#statisBox").window("open");
	
})

$("#excel").off().click(function(){
	if ($("#excel").linkbutton("options").disabled){
		return;
	}
	
	if (excelType==2){
		var xycode = $('#twoCartogram').datagrid('getSelected').xycode;
		window.location.href = ('statistics/reportExcel.form?type=2&xycode='+xycode);
	}else if (excelType==1){
		window.location.href = ('statistics/reportExcel.form?type=1&vertical=' + $("#vertical").combobox("getValue") + '&horizontal=' + $("#horizontal").combobox("getValue"));
		
	}
	
	
})



/***
 * 常用统计
 */
$("#commonStatis").off().click(function(){
	
	
	$("#commonWindow").window("open");
	$('#oneCartogram').datagrid('reload'); 
	
})

	


function cmOut(){
	$("#commonWindow").window("close")
}








/**
 * 保存常用二维查询
 */
function save(){
	var vertical=$('#vertical').combobox('getValue');
	var horizontal=$('#horizontal').combobox('getValue');
	if(vertical==null||vertical==''){
		$.messager.alert('提示','横向统计条件','info');
		return;
	}if(horizontal==null||horizontal==''){
		$.messager.alert('提示','纵向统计条件','info');
		return;
	}
	if(vertical==horizontal){
		$.messager.alert('提示','横向条件不能与纵向条件相等','info');
		return;
	}
    $("#commonTwoWindow").window('open');
	
}

$(function(){
	$('#wpy-aa').on('click','tr',function(){
		var index=$(this).index();
		if(index>0){
			$(this).find('td').css({background:'#f5f5f5'});
			$(this).siblings().find('td').css({background:"#fff"});
			$(this).addClass('active').siblings().removeClass('active');
		}
	})
	
	
	

$("#complexQuery").off().click(function(){

	var where =" [ ";
	
	$("#texts p").each(function(index){
		
			var obj = "{";
			obj = obj + "title:'"+$(this).attr("title")+"',";
			myShow[index]=$(this).attr("title")
			obj = obj + "sqlTemp:'"+$(this).attr("val")+"'";
			obj=obj+"},";
			where = where + obj;
		
	})
	
	where = where.substring(0,where.length-1) +" ] ";
	$("#where").val(where);
	
	showPieByWhere(where);
	//initShow()
	 $("#excel").linkbutton('disable');  
	 excelType=0;
	
	$('#statisBox').window('close');
})
	
})


function saveOne(){
	
	 var tt=document.getElementById("texts").getElementsByTagName("p");
	   var t1="";
	  for(var i=0; i<tt.length; i++){
		  var t2=$(tt[i]).attr('title'); 
		var t1=t1+t2+","+$(tt[i]).attr('val')+"|";
	 }
	var name=$('#oneName').val();
	 var text=t1;
	 if(text==null||text==''){
		 $.messager.alert('提示','保存成功','info');
	 }
	 $.ajax({
			type : 'POST',
			url : 'statistics/saveOne.form',
			data:{'name':name,'text':text,'modulecode':'2'},
			dataType : 'text',
			async:false ,
			success : function(msg) {
				if(msg=='success'){
					$.messager.alert('提示','保存成功','info');
					 $("#commonOneWindow").window('close');
					 $('#oneCartogram').datagrid("reload");
					 $('#texts').html("");
					 $('#twoName').val("");
				}
				//$('#twoWindow').window('close');
				//$("#two").html(msg);
			},
			error:function(msg){
				alert(msg);
			}
		});
}

function saveTwo(){
	
	var vertical=$('#vertical').combobox('getValue');
	var horizontal=$('#horizontal').combobox('getValue');
	var name=$('#twoName').val();
	if(name==null||name==''){
		$.messager.alert('提示','名称不能为空','info');
		return;
	}
	$.ajax({
		type : 'POST',
		url : 'statistics/saveQuery.form?code1='+vertical+'&code2='+horizontal,
		data:{'name':name,'modulecode':'2'},
		dataType : 'text',
		async:false ,
		success : function(msg) {
			if(msg=='success'){
				$.messager.alert('提示','保存成功','info');
				 $("#commonTwoWindow").window('close');
				 $('#twoCartogram').datagrid("reload");
				 $('#texts').html("");
				 $('#twoName').val("");
			}
			
			//$('#twoWindow').window('close');
			//$("#two").html(msg);
		},
		error:function(msg){
			alert(msg);
		}
	});
	
	
}



function statics(){
	var vertical = $("#vertical").combobox("getValue");
	var horizontal = $("#horizontal").combobox("getValue");
	$.ajax({
		type : 'POST',
		url : 'statistics/towStatics.form',
		async:false ,
		data : 'vertical='+vertical+'&horizontal='+horizontal,
		success : function(msg) {
			$("#two").css("display","block");
			$("#one").css("display","none");
			$('#twoWindow').window('close');
			$("#two").html(msg);
			$.parser.parse($('#two'));
			$("#excel").linkbutton('enable');  
			excelType=1;
		}
	});
}



//测试用例
function initShow(){

	var myChart = echarts.init(document.getElementById('pie'));

	  // 指定图表的配置项和数据
	  var option = {
	      title: {
	          text: 'ECharts 入门示例'
	      },
	      tooltip: {},
	      legend: {
	          data:['销量']
	      },
	      xAxis: {
	          data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
	      },
	      yAxis: {},
	      series: [{
	          name: '销量',
	          type: 'bar',
	          data: [5, 20, 36, 10, 10, 20]
	      }]
	  };

	  // 使用刚指定的配置项和数据显示图表。
	  myChart.setOption(option);

}




/***
 * 展示饼状图
 */
function showPieByWhere(where){
	selectType=2;
	if (where==null || where =="undefined"){
		where = $("#where").val();
	}
	$("#one").css("display","block");
	$("#pie").css("display","block");
	$("#bar").css("display","none");
	$("#line").css("display","none");
	
	var pie = echarts.init(document.getElementById('pie'));


	$("#pieButton").addClass("activeButton");
	$("#barButton").removeClass("activeButton");
	$("#lineButton").removeClass("activeButton");
	
	var wHeight = window.document.body.clientHeight;
	var wWidth = window.document.body.clientWidth;
	var height=wHeight-170+"px";
	var width=wWidth-390+"px";

	$("#pie").css("height",height);
	$("#pie").css("width",width);
	// 显示标题，图例和空的坐标轴

	$.ajax({
		type : 'POST',
		url : 'statistics/oneStatics.form',
		async:false ,
		data : "type=1&where="+where,
		success : function(data) {
		
			//alert(JSON.stringify(data))
			//console.info(data)
			
	
			pie.setOption(
					{
					    title : {
					        text: '统计分析',
					       /* subtext: '纯属虚构',*/
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient : 'vertical',
					        x : 'left',
					        data:myShow
					    },
					    calculable : true,
					    series : [
					        {
					            name:'统计分析',
					            type:'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:eval(data)
					        }
					    ]
					}
					);   
			
		}
	});

}

/***
 * 展示直方型
 */
function showBarByWhere(){
	var where = $("#where").val();
	selectType=2;
	var wHeight = window.document.body.clientHeight;
	var wWidth = window.document.body.clientWidth;
	var height=wHeight-170+"px";
	var width=wWidth-390+"px";
	$("#bar").css("height",height);
	$("#bar").css("width",width);
	$("#one").css("display","block");
	$("#pie").css("display","none");
	$("#bar").css("display","block");
	$("#line").css("display","none");
	var bar = echarts.init(document.getElementById('bar'));
	$("#pieButton").removeClass("activeButton");
	$("#barButton").addClass("activeButton");
	$("#lineButton").removeClass("activeButton");
	
	
	$.ajax({
		type : 'POST',
		url : 'statistics/oneStatics.form',
		async:false ,
		data : "type=2&where="+where,
		success : function(data) {
			//alert(JSON.stringify(data))
			//console.info(data)

				bar.setOption({
				    title: {
				        x: 'center',
				        text: '统计分析'
				       /* subtext: 'Rainbow bar example',
				        link: 'http://echarts.baidu.com/doc/example.html'*/
				    },
				    tooltip: {
				        trigger: 'item'
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            //dataView: {show: false, readOnly: false},
				            //restore: {show: false},
				            saveAsImage: {show: true}
				        }
				    },
				    calculable: true,
				    grid: {
				        borderWidth: 0,
				        y: 80,
				        y2: 60
				    },
				    xAxis: [
				        {
				            type: 'category',
				            show: false,
				           // data: ['Line', 'Bar', 'Scatter', 'K', 'Pie', 'Radar', 'Chord', 'Force', 'Map', 'Gauge', 'Funnel']
				            data:myShow
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            show: true
				        }
				    ],
				    series: [
				        {
				            name: 'ECharts例子个数统计',
				            type: 'bar',
				            itemStyle: {
				                normal: {
				                    color: function(params) {
				                        // build a color map as your need.
				                        var colorList = [
				                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
				                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
				                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
				                        ];
				                        return colorList[params.dataIndex]
				                    },
				                    label: {
				                        show: true,
				                        position: 'top',
				                        formatter: '{b}\n{c}'
				                    }
				                }
				            },
				            data: eval(data)
				        }
				    ]
				});
			
		}
	});
	
		                    
}
/***
 * 展示线型
 */
function showLineByWhere(){
	var where = $("#where").val();
	selectType=2;
	var wHeight = window.document.body.clientHeight;
	var wWidth = window.document.body.clientWidth;
	var height=wHeight-170+"px";
	var width=wWidth-390+"px";
	$("#line").css("height",height);
	$("#line").css("width",width);
	$("#one").css("display","block");
	$("#pie").css("display","none");
	$("#bar").css("display","none");
	$("#line").css("display","block");
	var line = echarts.init(document.getElementById('line'));
	$("#pieButton").removeClass("activeButton");
	$("#barButton").removeClass("activeButton");
	$("#lineButton").addClass("activeButton");
	
	
	
	$.ajax({
		type : 'POST',
		url : 'statistics/oneStatics.form',
		async:false ,
		data : "type=2&where="+where,
		success : function(data) {
			//alert(JSON.stringify(data))
			//console.info(data)
			
			line.setOption({
				    title : {
				        text: '统计分析',
				        subtext: '线性'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['统计图']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            //mark : {show: true},
				            //dataView : {show: true, readOnly: false},
				            //magicType : {show: true, type: ['line', 'bar']},
				            //restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            //data : ['周一','周二']
				            data:myShow
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series : [
				        {
				            name:'最高气温',
				            type:'line',
				            data:eval(data)
				        }
				    ]
			});
		}
	});
	
	
}
























/***
 * 退出查询条件
 */   
   
$("#queryOut").off().click(function(){

	$('#statisBox').window('close');
})
var count=0;
$('.wpy-add').on('click',function(){
	

	var quota=$('#quota').combo('getValue');//备用指标集
	var context=$('#context').combo('getValue');//内容
	var contextLabel = $("#context").combo('getText');//显示
	var logic=$('#logic').combo('getValue');//逻辑关系
	var relation=$('#relation').combo('getValue');//比较关系
	var texttpyes = $("#comparison").attr("texttpyes");
	var comparison="";

	//1 combobox 2textbox(弹出框) 3datetimebox 4textbox(输入框)
	if (texttpyes=="1"){
		comparison=$('#comparison').combobox("getText");//比较值
		comparisonLabel=$('#comparison').combobox("getText");//比较值
	}else if (texttpyes=="2"){
		comparison=$('#comparison').textbox("getValue");//比较值
		comparisonLabel=$('#comparison').textbox("getValue");//比较值
	}else if (texttpyes=="3"){
		comparison=$('#comparison').datebox("getValue");//比较值
		comparisonLabel=$('#comparison').datebox("getValue");//比较值
	}else if (texttpyes=="4"){
		comparison=$('#comparison').textbox("getValue");//比较值
		comparisonLabel=$('#comparison').textbox("getValue");//比较值
	}
		
	//var comparison=$('#comparison').val();//比较值
	
	if(quota==""||context==""||logic==""||relation==""||comparison==""){
		$.messager.alert('提示信息','请输入完整的查询条件');
		return;
	}
	count=count+1;
	//var text="<tr context='"+context+"' logic='"+logic+"' relation='"+relation+"' comparison='"+comparison+"'><td>"+count+"</td><td>"+contextLabel+"  "+relation+"  "+comparisonLabel+"</td></tr>";
	
	/*$('#texts1').datagrid('appendRow',{
		texts: contextLabel+"  "+relation+"  "+comparisonLabel
		
	});*/
	var text="<tr context='"+context+"'  relation='"+relation+"' comparison='"+comparison+"' table='"+quota+"'><td>"+count+"</td><td>"+contextLabel+"  "+relation+"  "+comparisonLabel+"</td></tr>";
	
	$('#wpy-aa').append(text);
	refreshTdNumber();
});
$('#wpy-ct').off().on('click','p',function(){
	$(this).addClass('active').siblings().removeClass('active');
});
$('.wpy-del').off().on('click',function(){
	$('#wpy-aa').find('.active').remove();
	
	 /*var targetTable = $("wpy-aa");
	 console.info(targetTable);
	 for (var i = 0; i<targetTable.rows.length; i++) {
         if (i != 0)
         targetTable.rows[i].cells[0].innerHTML = i;
     }*/
	 refreshTdNumber();
	/*var row = $('#texts1').datagrid('getSelected');
	if (row==null){
		$.messager.alert('提示信息','请选中一行');
		return;
	}
	
      var index = $('#texts1').datagrid('getRowIndex',row);
      $('#texts1').datagrid('deleteRow',index);*/
});

function refreshTdNumber(){
	var tr=$('#wpy-aa').find('tr');
	$.each(tr,function(i,item){
		if(i>0){
			$(item).find('td').eq(0).html(i);
		}
	});
}
function openDialog(title,url){
	$('#openDialog').dialog({
		title:title,
		width:400,
		height:600,
		"href":url,
		onClose:function(){
			if ($("#allId")!=null){
				$("#allId").removeAttr("id");
			}
	    }
	});
	
}

function openMessageWindow(){
	var text="";
	var tr=$('#wpy-aa').find('tr');
	if(tr==null||tr==''||tr=='undefined'||tr.length==1){
		return;
	}
	 
	 
	 var twoName=$("#importText").val();
	
	 
	 if(twoName==null||twoName==''){
		/*$.each(tr,function(i,item){
				if(i>0){
					
					if(i==tr.length-1){
					$("#twoText").val($(item).find('td').eq(1).html());	
					$("#twoText").attr("aaa",i);
					}
					console.info($(item).find('td').eq(1).html(i))
					text=$(item).find('td').eq(1).html(item);
				}
			});*/
		 /*var rows = $("#texts1").datagrid("getRows");
		 var row = rows[rows.length-1];
		 $("#twoText").attr("aaa",rows.length);
		
		 $("#twoText").val(row.texts);	*/
		 
	 }else{
		 $("#messageWindow").window('open');
		 var tables="";
		 var tablesWhere="";
		 var baseTable="";
		 var str=twoName;
		 var strHide=twoName.replace(new RegExp('\\*',"gm"), ' and ');
		 strHide=strHide.replace(new RegExp('\\+',"gm"), ' or ');
		 str=str.replace(new RegExp('\\*',"gm"), ' 且 ');
		 str=str.replace(new RegExp('\\+',"gm"), ' 或  ');
		 
		 var reg = /[1-9][0-9]*/g;
		 var numList = str.match(reg);  
		 for (var j = 0; j < numList.length; j++) {
			 str=str.replace(new RegExp(numList[j],"gm"), "@@"+numList[j]);
			 strHide = strHide.replace(new RegExp(numList[j],"gm"), "@@"+numList[j]);
		 }
		
         for (var i = 0; i < numList.length; i++) {
        	var ddd= $('#wpy-aa').find('tr').eq(numList[i]).find('td').eq(1).html();
        	 
        	/* var rows = $("#texts1").datagrid("getRows");
        	 var row = rows[numList[i]-1];*/
   
        	 str=str.replace(new RegExp("@@"+numList[i],"gm"), ddd);
        	/*var ddd= $('#wpy-aa').find('tr').eq(numList[i]).find('td').eq(1).html();
       
        	 str=str.replace(numList[i], ddd);*/
        	 $("#twoText").val(str);	
			 $("#twoText").attr("aaa",twoName);
			 
			 var trLine = $('#wpy-aa').find('tr').eq(numList[i]);
			 var context = trLine.attr("context");
			 var relation = trLine.attr("relation");
			 var table  = trLine.attr("table");
			
			 if (tables.indexOf(table)>=0){
				 
			 }else{
				 tables =tables+ table+",";
				 if (baseTable==""){
					 baseTable = table+".empcode"
				 }else {
					 tablesWhere = tablesWhere +" "+ baseTable + "=" +table+".empcode and";
				 }
			 }
			
			 var comparison = trLine.attr("comparison");
			 var temp = table+"."+context+relation+"@"+comparison +"@";
			
			 strHide = strHide.replace(new RegExp("@@"+numList[i],"gm"), temp);
		}
        
         if (tablesWhere.length>2){
        	 tablesWhere = " and " + tablesWhere.substring(0,tablesWhere.length-3);
         }
         strHide = tables.substring(0,tables.length-1)+" where "+strHide + tablesWhere;
         $("#twoText").attr("val",strHide);
         
	 }
	
	
}



function saveTwoName(){
	/*var count1=$("#twoText").val().match(/(/g).length;
	var count2=$("#twoText").val().match(/(/g).length;
	if(count1>count2){
		$.messager.alert('提示信息',')右括号缺少');
		return;
	}if(count1<count2){
		$.messager.alert('提示信息','(左括号缺少');
		return;
	}*/
	
	
	/*var content="<p>"+$("#twoText").attr('aaa')+"</p>";*/
	var content="<p class='zwc' val='"+$("#twoText").attr('val')+"' title='"+$("#twoText").val()+"'>"+$("#twoText").attr('aaa')+"</p>";
	$("#twoText").val('');
	$('#texts').append(content);
	$("#importText").val('');
	deleteLog();
	 $("#messageWindow").window('close');
	
	
}

/*$('#texts').on('mousedown','p',function(){
	var index=$(this).index();
	$(this).siblings().removeClass('active').css({background:'none'});
	$(this).addClass('active');
	console.log($(this).attr('class'));
	$(this).css({background:'#f5f5f5'});
})*/
$('#texts').off().on('click','p',function(){
	$(this).addClass('active');
	$(this).css({background:'#f5f5f5'});
	$(this).siblings().removeClass('active').css({background:'none'});
})



function deleteLog(){
	$('.zwc').smartMenu([[{text:'删除',func:function(){
		$('.zwc.active').remove();
		
		}}]])
	
}


function choicebox(newValue){
	
	$.ajax({
		type : 'POST',
		url : 'personnel/findSelectBox.form?value='+newValue,
		dataType : 'json',
		async:false ,
		success : function(msg) {
			
			
			$('#text1').html('');
			$('#text1').append('<input type="text" id="comparison" name="">');
			  $("#comparison").attr("texttpyes",msg.grade);
			  
			if(msg.grade=='1'){
				
				$('#comparison').addClass('easyui-combobox');
				 //$.parser.parse('#comparison');
				// console.info(msg.list1);
				 $('#comparison').combobox({
					    data:msg.list1,
					    valueField:'id',
					    editable:false,
					    textField:'codeName',
					    width:180
					});
			}else if(msg.grade=='2'){
				$('#comparison').addClass('easyui-textbox');
				 //$.parser.parse('#comparison');
				// console.info(msg.list1);
				 $('#comparison').textbox({
					   label: 'Icons',
					   labelPosition: 'top',
					   iconWidth: 22,
					   icons:[{ iconCls:'icon-add', handler: function(e){ onDialog(msg.map.FieldRelation); } }] ,
					   width:180
					});
				 $("#comparison").off().click(function(){
					 onDialog(msg.map.FieldRelation);
				 });
				
			}else if(msg.grade=='3'){
				$('#comparison').addClass('easyui-datebox');
				$('#comparison').datetimebox({
				    value: '',
				    required: false,
				    showSeconds: false,
				    editable:false,
				    width:180
				});
			}else{/*
				$('#comparison').addClass('easyui-validatebox');
				$('#comparison').validatebox({
				    required: false,
				});*/



				$('#comparison').textbox({

				    required: false,
				    width:180
				});
				//$('#comparison').textbox('setValue', '');
			}
			
		},
		error:function(msg){
			alert(2);
		}
	});

}






/***
 * 展示历史饼状图
 */
function showHistoryPie(accountcode){
	if (accountcode==null || accountcode =="undefined"){
		accountcode = $('#oneCartogram').datagrid('getSelected').accountcode;
	}
	selectType=1;
	var wHeight = window.document.body.clientHeight;
	var wWidth = window.document.body.clientWidth;
	var height=wHeight-170+"px";
	var width=wWidth-390+"px";
	$("#pie").css("height",height);
	$("#pie").css("width",width);
	$("#one").css("display","block");
	$("#two").css("display","none");
	$("#pie").css("display","block");
	$("#bar").css("display","none");
	$("#line").css("display","none");
	var pie = echarts.init(document.getElementById('pie'));
	
	$("#pieButton").addClass("activeButton");
	$("#barButton").removeClass("activeButton");
	$("#lineButton").removeClass("activeButton");
	
	// 显示标题，图例和空的坐标轴
	$.ajax({
		type : 'POST',
		url : 'statistics/showHistoryOne.form',
		async:false ,
		data : "type=1&accountcode="+accountcode,
		success : function(data) {
			//alert(JSON.stringify(data))
			//console.info(data)

			pie.setOption(
					{
					    title : {
					        text: '统计分析',
					       /* subtext: '纯属虚构',*/
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient : 'vertical',
					        x : 'left',
					        data:myShow
					    },
					    calculable : true,
					    series : [
					        {
					            name:'统计分析',
					            type:'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:eval(data)
					        }
					    ]
					}
					);   
			
		}
	});

}

/***
 * 展示历史直方型
 */
function showHistoryBar(){
	var accountcode = $('#oneCartogram').datagrid('getSelected').accountcode;
	var wHeight = window.document.body.clientHeight;
	var wWidth = window.document.body.clientWidth;
	var height=wHeight-170+"px";
	var width=wWidth-390+"px";
	$("#bar").css("height",height);
	$("#bar").css("width",width);
	$("#one").css("display","block");
	$("#two").css("display","none");
	$("#pie").css("display","none");
	$("#bar").css("display","block");
	$("#line").css("display","none");
	var bar = echarts.init(document.getElementById('bar'));
	$("#pieButton").removeClass("activeButton");
	$("#barButton").addClass("activeButton");
	$("#lineButton").removeClass("activeButton");
	
	selectType=1;
	$.ajax({
		type : 'POST',
		url : 'statistics/showHistoryOne.form',
		async:false ,
		data : "type=2&accountcode="+accountcode,
		success : function(data) {
			var array = data.split("@");
			var dataShow = array[0];
			var title = array[1];
			
			//alert(JSON.stringify(dataShow))
			//console.info(dataShow)

				bar.setOption({
				    title: {
				        x: 'center',
				        text: '统计分析'
				       /* subtext: 'Rainbow bar example',
				        link: 'http://echarts.baidu.com/doc/example.html'*/
				    },
				    tooltip: {
				        trigger: 'item'
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            //dataView: {show: false, readOnly: false},
				            //restore: {show: false},
				            saveAsImage: {show: true}
				        }
				    },
				    calculable: true,
				    grid: {
				        borderWidth: 0,
				        y: 80,
				        y2: 60
				    },
				    xAxis: [
				        {
				            type: 'category',
				            show: false,
				           // data: ['Line', 'Bar', 'Scatter', 'K', 'Pie', 'Radar', 'Chord', 'Force', 'Map', 'Gauge', 'Funnel']
				            data:eval(title)
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            show: true
				        }
				    ],
				    series: [
				        {
				            name: 'ECharts例子个数统计',
				            type: 'bar',
				            itemStyle: {
				                normal: {
				                    color: function(params) {
				                        // build a color map as your need.
				                        var colorList = [
				                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
				                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
				                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
				                        ];
				                        return colorList[params.dataIndex]
				                    },
				                    label: {
				                        show: true,
				                        position: 'top',
				                        formatter: '{b}\n{c}'
				                    }
				                }
				            },
				            data: eval(dataShow)
				        }
				    ]
				});
			
		}
	});
	
		                    
}
/***
 * 展示历史线型
 */
function showHistoryLine(){
	var accountcode = $('#oneCartogram').datagrid('getSelected').accountcode;
	var wHeight = window.document.body.clientHeight;
	var wWidth = window.document.body.clientWidth;
	var height=wHeight-170+"px";
	var width=wWidth-390+"px";
	$("#line").css("height",height);
	$("#line").css("width",width);
	$("#one").css("display","block");
	$("#two").css("display","none");
	$("#pie").css("display","none");
	$("#bar").css("display","none");
	$("#line").css("display","block");
	var line = echarts.init(document.getElementById('line'));
	$("#pieButton").removeClass("activeButton");
	$("#barButton").removeClass("activeButton");
	$("#lineButton").addClass("activeButton");
	selectType=1;
	
	
	$.ajax({
		type : 'POST',
		url : 'statistics/showHistoryOne.form',
		async:false ,
		data : "type=3&accountcode="+accountcode,
		success : function(data) {
			var array = data.split("@");
			var dataShow = array[0];
			var title = array[1];
			//alert(JSON.stringify(data))
			//console.info(data)
			
	
			
			line.setOption({
				    title : {
				        text: '统计分析',
				        subtext: '线性'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['统计图']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            //mark : {show: true},
				            //dataView : {show: true, readOnly: false},
				            //magicType : {show: true, type: ['line', 'bar']},
				            //restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            //data : ['周一','周二']
				            data:eval(title)
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series : [
				        {
				            name:'最高气温',
				            type:'line',
				            data:eval(dataShow)
				        }
				    ]
			});
		}
	});
	
	
}



function showPie(){
	if (selectType==1){
		showHistoryPie();
	}else if (selectType==2){
		showPieByWhere()
	}
}

function showBar(){
	if (selectType==1){
		showHistoryBar();
	}else if (selectType==2){
		showBarByWhere()
	}
}
function showLine(){
	if (selectType==1){
		showHistoryLine();
	}else if (selectType==2){
		showLineByWhere()
	}
}


function showPic2(){
	var xycode = $('#twoCartogram').datagrid('getSelected').xycode;
	$.ajax({
		type : 'POST',
		url : 'statistics/showHistoryTwo.form',
		async:false ,
		data : 'xycode='+xycode,
		success : function(msg) {
			$("#two").css("display","block");
			$("#one").css("display","none");
			$("#two").html(msg);
			$.parser.parse($('#two'));
			
		}
	});
}

$('#tabs').tabs({
	  onSelect: function(title,index){
		  	
		   if (index==1 && tabType2==0){
			   $("#excel").linkbutton('enable');  
			   $('#twoCartogram').datagrid({
               	url : 'statistics/findTwoCondition.form?moduleCode=2'
               });
			   tabType2=1;
			   excelType=2;
		   }else{
			   $("#excel").linkbutton('disable');  
			   excelType=0;
		   }
	  }
	});
	

function removeIt(status){
	
	
	var node;
	var id;
	if(status==1){
		 node = $('#oneCartogram').datagrid('getSelected');
		 id=node.accountcode;
	}else{
		node = $('#twoCartogram').datagrid('getSelected');
		id=node.xycode;
	}
	
	$.messager.confirm('提示', '你确定删除该项吗?', function(r){
		if (r){
	   if (node){
		$.ajax({
			type : 'POST',
			url : 'statistics/deleteAccount.form',
			async:false ,
			data : {'status':status,'id':id},
			success : function(msg) {
				if(msg=='success'){
					$.messager.alert('提示','删除成功','info');
					if(status==1){
						
						var rowIndex = $('#oneCartogram').datagrid('getRowIndex', node);
				         $('#oneCartogram').datagrid('deleteRow', rowIndex);  
					}else{
						var rowIndex = $('#twoCartogram').datagrid('getRowIndex', node);
				         $('#twoCartogram').datagrid('deleteRow', rowIndex);  
					}
				}else{
					$.messager.alert('提示','删除失败','info');
				}
				
			}
		});
		
		
		
		
	}
		}
	});	
}


