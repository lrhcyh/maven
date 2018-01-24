var tableCode;
var queryWhere;
/***
 * 初始化表格，不带数据的
 */
   $(function() {
	   tableCode=2
	   //initData("[{context:1,logic:'and',relation:'=',comparison:'2'}]");
	   initColumn()
   })

function initColumn(){
	   var data;
	   data = 'moduleCode='+tableCode;
		$.ajax({
			type : 'POST',
			url : 'auto/complexQueryColumn.form',
			data : data,
			dataType : 'text',
			async:false ,
			success : function(msg) {
				$('#tgContent').datagrid({
					lines:true,
					striped:true,
					singleSelect: true,
					method:'post',
					rownumbers:true,
					data:[],
					pagination:true,
					pageSize:20,
				    columns:[eval(msg)]
			   });
				
			},
			error:function(msg){
				alert(msg);
			}
		});
}   
   

function initData(where){
	   var data;
	   data = 'moduleCode='+tableCode+'&where='+where;
	   queryWhere = data;
	   $('#tgContent').datagrid({
       		url : 'auto/complexQuery.form?'+data,
       });
	
}

   
   
   
   
/***
 * 查询所有
 */   
   
$("#queryAll").click(function(){
	
	initData("[{context:'1',logic:'and',relation:'=',comparison:'1'}]");
	
})

/***
 * 查询所有
 */   
   
$("#query").click(function(){

	$('#complexBox').window('open');
})
/***
 * 开始查询
 */   
   
$("#complexQuery").click(function(){

	var where =" [ ";
	
	$("#wpy-ct p").each(function(index){
	
		var obj = "{";
		obj = obj + "context:'"+$(this).attr("context")+"',";
		obj = obj + "logic:'"+$(this).attr("logic")+"',";
		obj = obj + "relation:'"+$(this).attr("relation")+"',";
		obj = obj + "comparison:'"+encodeURI(encodeURI($(this).attr("comparison")))+"'";
		obj=obj+"},";
		where = where + obj;
	})
	
	where = where.substring(0,where.length-1) +" ] ";

	initData(where);

	$('#complexBox').window('close');
})
/***
 * 退出查询条件
 */   
   
$("#queryOut").click(function(){

	$('#complexBox').window('close');
})
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
	var text="<p context='"+context+"' logic='"+logic+"' relation='"+relation+"' comparison='"+comparison+"'>"+contextLabel+"  "+relation+"  "+comparisonLabel+"</p>";
	$('#wpy-ct').append(text);
});
$('#wpy-ct').on('click','p',function(){
	$(this).addClass('active').siblings().removeClass('active');
});
$('.wpy-del').on('click',function(){
	$('#wpy-ct').find('.active').remove();
});

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

$("#batchDo").click(function(){
	var rows = $('#tgContent').datagrid('getRows')
	if (rows.length==0){
		$.messager.alert('提示信息','无可修改的数据');
		return;
	}
	$('#batchUpdate').window('open');
})


function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
 }
		
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();            
}

function submitForm(){
	var seq = $("#seq").combobox("getValue");
	var newSeq = $("#newSeqValue").val();
	var seqCollect = $("#seqCollect").textbox("getValue");
	
	if (seq==null || seq=="undefined" || seq=="" || newSeq==null || newSeq=="undefined" || newSeq==""){
		$.messager.alert('提示信息','请输入完整的修改条件');
		return;
	}
	//setPageRows(seq,newSeq,seqCollect)
	var data;
	   data = 'moduleCode='+tableCode;
		$.ajax({
			type : 'POST',
			url : 'auto/batchExe.form',
			data : "seq="+seq+"&newSeq="+newSeq+"&seqCollect="+seqCollect,
			dataType : 'text',
			async:false ,
			success : function(msg) {
				$('#tgContent').datagrid({
		    		url : 'auto/complexQuery.form?'+queryWhere,
				});
				
			},
			error:function(msg){
				alert(msg);
			}
		});
	
	$('#batchUpdate').window('close');
}

function clearForm(){
	$('#batchUpdate').window('close');
}

function setPageRows(column,param,seqCollect) {  
	
	var data =  $('#tgContent').datagrid('getData')
  
	var  b=$.extend({},data)
	var paramField;
	var columns =  $('#tgContent').datagrid('options').columns;
	
	for (var j =0;j<columns[0].length;j++){
		if (columns[0][j].title==param){
			paramField = columns[0][j].field;
		}
	}
	
	if (paramField==null || paramField=="undefined"){
		for (var i=0;i<b.rows.length;i++){
			b.rows[i][column] = param;
		}
	}else{
		for (var i=0;i<b.rows.length;i++){
			b.rows[i][column] = b.rows[i][paramField];
		}
	}
	
	$('#tgContent').datagrid('loadData',b);
	
	
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


function onDialog(a){
	openDialog('数据查询',"pages/base/query/allTree.jsp?id="+a+"&type="+a);
}

