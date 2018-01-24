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

	<div class="pd10">
		<div class="hxc-wrapper">
			<div class="hxc-filter">
				统计周期
				<dl class="time-filter">
					<dd id="today" class="active dt">今天</dd>
					<dd id="yesterday" class="dt">昨天</dd>
					<dd id="week" class="dt">本周</dd>
					<dd id="month" class="dt">本月</dd>
					<dd id="seven" class="dt">近7天</dd>
					<dd id="thirty" class="dt">近30天</dd>
					<dd>
						<input id="beginDateTime" name="beginDateTime" class="easyui-datebox" data-options="editable:false"></input>至<input id="endDateTime" name="endDateTime" class="easyui-datebox" data-options="editable:false"></input>
					</dd>
				</dl>
				<span class="btn" onclick="searchPv()">查询</span>
			</div>
			<div class="ct">
				<div class="hd">流量数据分析</div>
				<div class="bd">
					<div id="pie" style="width: 40%; float: left; height: 400px"></div>
					<div id="line" style="width: 60%; float: left; height: 400px"></div>
				</div>
			</div>
			<div class="ct">
				<div class="hd">访问来源分析</div>
				<div class="bd">
					<table id="tg" class="easyui-treegrid" style="width: inherit; height: auto;" data-options="
			                url: 'analysis/findAccessGrid.form',
			                method: 'get',
			                idField:'id',
			                treeField:'name',
			                lines:true,
			                striped:true
			            ">
						<thead>
							<tr>
								<th data-options="field:'name'" width="20% ">来源类型</th>
								<th data-options="field:'pv',align:'center'" width="20%">浏览量（PV）</th>
								<th data-options="field:'uv',align:'center'" width="15%">访问量（UV）</th>
								<th data-options="field:'ipNum',align:'center'" width="15%">IP数</th>
								<th data-options="field:'skip',align:'center'" width="15%">二跳率</th>
								<th data-options="field:'accessTime',align:'center'" width="15%">平均访问时长（秒）</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function searchPv() {
			$(".dt").removeClass("active");
			loadnPage();
		}

		$("#beginDateTime").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		$("#endDateTime").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		function GetDateStr(AddDayCount) {
			var dd = new Date();
			dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
			var y = dd.getFullYear();
			var m = dd.getMonth() + 1;//获取当前月份的日期
			var d = dd.getDate();
			return y + "-" + m + "-" + d;
		}
		$("#today").click(function() {
			$(".dt").removeClass("active");
			$("#today").addClass("active");
			$("#beginDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadnPage();
		});
		$("#yesterday").click(function() {
			$(".dt").removeClass("active");
			$("#yesterday").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-1));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate() - 1));

			loadnPage();

		});
		$("#week").click(function() {
			$(".dt").removeClass("active");
			$("#week").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-new Date().getDay() + 1));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadnPage();

		});
		$("#month").click(function() {
			$(".dt").removeClass("active");
			$("#month").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-new Date().getDate() + 1));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadnPage();

		});
		$("#seven").click(function() {
			$(".dt").removeClass("active");
			$("#seven").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-7));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadnPage();

		});
		$("#thirty").click(function() {
			$(".dt").removeClass("active");
			$("#thirty").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-30));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadnPage();
		});
	</script>

	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('pie'));

		

		// 显示标题，图例和空的坐标轴
		myChart.setOption({
			title : {
				text : '访问来源类别占比',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [ '直接进入', '站内链接', '站外链接' ]
			},
			series : [ {
				name : '访问来源',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		});

		//折线图
		var myChart2 = echarts.init(document.getElementById('line'));

		// 显示标题，图例和空的坐标轴
		myChart2.setOption({
			title : {
				text : ''
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '直接访问', '站内链接', '站外链接' ]
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				data : []
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : []
		});

		showLoading();
		getChartData('', '');

		// 异步加载数据
		function getChartData(starttime, endtime) {
			$.get('analysis/getAccessData.form?starttime=' + starttime + '&endtime=' + endtime, null, null, 'json').done(function(data) {
				console.info(data);
				// 填入数据
				myChart.setOption({
					series : [ {
						// 根据名字对应到相应的系列
						data : data.pieData
					} ]
				});
				myChart2.setOption({
					xAxis : [ {
						data : data.xAxis
					} ],
					series : data.lineData
				});
				hideLoading();
			});
		}
	
		function showLoading() {
			myChart.showLoading({
			})
			myChart2.showLoading({
			})
		}
		
		function hideLoading() {
			myChart.hideLoading({
			})
			myChart2.hideLoading({
			})
		}
		$("#search").click(function() {
			loadnPage();
		})
		function loadnPage() {
			showLoading();
			getChartData($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
			$('#tg').treegrid('reload', {
				"starttime" : $("#beginDateTime").datebox("getValue"),
				"endtime" : $("#endDateTime").datebox("getValue")
			});
		}
	</script>
</body>