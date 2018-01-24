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
	<!-- 引入 echarts.js -->
	<script src="js/echarts.js"></script>
	<script src="js/china.js"></script>
	<script type="text/javascript">
		
		$("#beginDateTime").val();
		$("#endDateTime").val();
		
		function GetDateStr(AddDayCount) {
	        var dd = new Date();
	        dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	        var y = dd.getFullYear();
	        var m = dd.getMonth()+1;//获取当前月份的日期
	        var d = dd.getDate();
	        return y+"-"+m+"-"+d;
	    }
		$("#all").click(function(){
			$(".dt").removeClass("active");
			$("#all").addClass("active");
			$("#beginDateTime").datebox("setValue","");
			$("#endDateTime").datebox("setValue","");
			
			loadnPage();
		});
		$("#today").click(function(){
			$(".dt").removeClass("active");
			$("#today").addClass("active");
			$("#beginDateTime").datebox("setValue",new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());
			$("#endDateTime").datebox("setValue",new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());
			
			loadnPage();
		});
		$("#yestoday").click(function(){
			$(".dt").removeClass("active");
			$("#yestoday").addClass("active");
			$("#beginDateTime").datebox("setValue",GetDateStr(-1));
			$("#endDateTime").datebox("setValue",GetDateStr(-1));
			
			loadnPage();
			
		});
		$("#week").click(function(){
			$(".dt").removeClass("active");
			$("#week").addClass("active");
			$("#beginDateTime").datebox("setValue",GetDateStr(-new Date().getDay()+1));
			$("#endDateTime").datebox("setValue",new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());
			
			loadnPage();
		});
		$("#month").click(function(){
			$(".dt").removeClass("active");
			$("#month").addClass("active");
			$("#beginDateTime").datebox("setValue",GetDateStr(-new Date().getDate()+1));
			$("#endDateTime").datebox("setValue",new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());
			
			loadnPage();
		});
		$("#seven").click(function(){
			$(".dt").removeClass("active");
			$("#seven").addClass("active");
			$("#beginDateTime").datebox("setValue",GetDateStr(-7));
			$("#endDateTime").datebox("setValue",new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());
			
			loadnPage();
		});
		$("#thirty").click(function(){
			$(".dt").removeClass("active");
			$("#thirty").addClass("active");
			$("#beginDateTime").datebox("setValue",GetDateStr(-30));
			$("#endDateTime").datebox("setValue",new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());
			
			loadnPage();
		});
	</script>
	<div class="pd10">
		<div class="hxc-wrapper">
			<div class="hxc-filter">
				统计周期
				<dl class="time-filter">
					<dd class="active dt"  id="all">全部</dd>
					<dd class="dt"  id="today">今天</dd>
					<dd class="dt" id="yestoday">昨天</dd>
					<dd  class="dt" id="week">本周</dd>
					<dd class="dt" id="month">本月</dd>
					<dd class="dt" id="seven">近7天</dd>
					<dd class="dt" id="thirty">近30天</dd>
					<dd>
						<input id="beginDateTime" name="beginDateTime" class="easyui-datebox date1" data-options="editable:false" ></input>至<input id="endDateTime" name="endDateTime" class="easyui-datebox date2" data-options="editable:false"></input>
					</dd>
				</dl>
				<span class="btn" id="search">查询</span>
			</div>
			<div class="ct">
				<div class="hd">流量数据分析</div>
				<div class="bd">
					<div id="china" style="width: 50%; float: left; height: 400px"></div>
					<div id="pie" style="width: 50%; float: left; height: 400px"></div>
				</div>
			</div>
			<div class="ct">
				<div class="hd">访问来源分析</div>
				<div class="bd">
					<table id="tg" class="easyui-datagrid" style="width: inherit; height: auto;" data-options="
			                url: 'analysis/findChinaGrid.form',
			                method: 'get',
			                lines:true,
			                striped:true
			            ">
						<thead>
							<tr>
								<th data-options="field:'name'" width="20% ">地域</th>
								<th data-options="field:'pv',align:'center'" width="10%">浏览量（PV）</th>
								<th data-options="field:'uv',align:'center'" width="10%">访问量（UV）</th>
								<th data-options="field:'ipNum',align:'center'" width="15%">IP数</th>
								<th data-options="field:'skip',align:'center'" width="15%">二跳率</th>
								<th data-options="field:'accessTime',align:'center'" width="15%">平均访问时长（秒）</th>
								<th data-options="field:'accessRegist',align:'center'" width="15%">注册转化（量、率）</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>


	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('china'));

		// 显示标题，图例和空的坐标轴  地图
		myChart.setOption({
			title : {
				text: 'IP数区域分布',
		        x: 'left'
			},
			tooltip : {
				trigger: 'item'
			},
			legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['区域访问']
		    },
		    dataRange: {
		        min: 0,
		        max: 2500,
		        x: 'left',
		        y: 'bottom',
		        text:['高','低'],           // 文本，默认为数值文本
		        calculable : true
		    },
		    toolbox: {
		        show: true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            //dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    }/* ,
		    
		    series : [
		              {
		                  name: 'iphone3',
		                  type: 'map',
		                  mapType: 'china',
		                  selectedMode:'single',  
		                  roam: true,
		                  showLegendSymbol:true,  
		                  itemStyle:{
		                	  normal:{
								   label:{show:true},
								   borderWidth:1,//省份的边框宽度
								   borderColor:'#f60',//省份的边框颜色
								   color:'green',//地图背景颜色 
								   areaStyle:{color:'green'}//设置地图颜色
							   },
		                      emphasis:{label:{show:true}}
		                  },
		                  data:[
		                      {name: '湖北',value: Math.round(Math.random()*1000)},
		                      {name: '广东',value: Math.round(Math.random()*1000)},
		                      {name: '河北',value: Math.round(Math.random()*1000)}
		                  ]
		              }
		          ]
		     */
		});

		//折线图
		var myChart2 = echarts.init(document.getElementById('pie'));

		// 显示标题，图例和空的坐标轴
		myChart2.setOption({
			tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left'
		    },
		    series: [
		        {
		            name:'访问来源',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '30%'],

		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            }
		            
		        }
		    ]
		});

		getChartData('','');

		// 异步加载数据
		function getChartData(starttime,endtime) {
			
			 $.get('analysis/getChinaData.form?starttime=' + starttime+'&endtime='+endtime, null, null, 'json').done(function(data) {
				//alert(JSON.stringify(data))
				// 填入数据
 				 myChart.setOption({
					 series : [ {
						 name: '地域',
		                  type: 'map',
		                  mapType: 'china',
		                  selectedMode:'single',  
		                  roam: true,
		                  showLegendSymbol:true,  
						// 根据名字对应到相应的系列
						 data : data,
						
						 itemStyle:{
				               normal:{
								   label:{show:true},
								   borderWidth:1,//省份的边框宽度
								   borderColor:'#f60',//省份的边框颜色
								   color:'green'//地图背景颜色
									//areaStyle:{color:'#f60'}//设置地图颜色
							   },
			               emphasis:{label:{show:true}}
			           }
					} ] 
				
 				}); 
				
			}); 
			
			 $.get('analysis/getChinaPieData.form?starttime=' + starttime+'&endtime='+endtime, null, null, 'json').done(function(data) {
				//alert(JSON.stringify(data))
				
				   myChart2.setOption({
					  
					    series: [
					        {
					            name:'访问来源',
					            type:'pie',
					            selectedMode: 'single',
					            radius: [0, '30%'],

					            label: {
					                normal: {
					                    position: 'inner'
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            }
					            
					        } ,
							 {
								type:'pie',
					            radius: ['20%', '65%'],
								data :   data
							} 
					    ]
				});   
			}); 
		}
		
		$("#search").click(function(){
			loadnPage();
		})
		function loadnPage(){
			getChartData($("#beginDateTime").datebox("getValue"),$("#endDateTime").datebox("getValue"));
			$('#tg').datagrid('load',{
				"starttime": $("#beginDateTime").datebox("getValue"),
				"endtime": $("#endDateTime").datebox("getValue")
			});
		}
	</script>
</body>