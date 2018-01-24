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



	<div class="pd10">
		<div class="hxc-wrapper">
			<div class="hxc-filter">
				统计周期
				<dl class="time-filter">
					<dd id="today" class="active dt" value="today">今天</dd>
					<dd id="yesterday" class="dt">昨天</dd>
					<dd id="week" class="dt">本周</dd>
					<dd id="month" class="dt">本月</dd>
					<dd id="seven" class="dt">近7天</dd>
					<dd id="thirty" class="dt">近30天</dd>
					<dd>
						<input id="beginDateTime" name="beginDateTime" class="easyui-datebox" data-options="editable:false"></input>至<input id="endDateTime" name="endDateTime" class="easyui-datebox" data-options="editable:false"></input>
					</dd>
				</dl>
				<span class="btn" id="search">查询</span>
			</div>
			<div class="ct">
				<div class="hd">流量数据分析</div>
				<div class="bd">
					<div class="flow">
						<div class="flow-nav">
							<div>
								<p class="title">访问量（UV）</p>
								<p id="uv"></p>
							</div>
						</div>
						<div class="flow-nav">
							<div>
								<p class="title">IP数</p>
								<p id="ipNum"></p>
							</div>
						</div>
						<div class="flow-nav">
							<div>
								<p class="title">浏览量（PV）</p>
								<p id="pv"></p>
							</div>
						</div>
						<div class="flow-nav">
							<div>
								<p class="title">二跳率</p>
								<p id="skip"></p>
							</div>
						</div>
						<div class="flow-nav">
							<div>
								<p class="title">跳失率</p>
								<p id="lost"></p>
							</div>
						</div>
						<div class="flow-nav">
							<div>
								<p class="title">平均访问时长（秒）</p>
								<p id="accessTime"></p>
							</div>
						</div>
					</div>
					<div class="f-type">
						查看方式：<input type="radio" name="type" checked="checked" value="pv"> 浏览量（PV） <input type="radio" name="type" value="uv"> 访客数（UV） <input type="radio" name="type" value="ipNum"> IP数 <input type="radio" name="type" value="lost"> 跳失率 <input type="radio" name="type" value="accessTime"> 平均访问时长
					</div>
					<div id="line1" style="width: 100%; height: 400px"></div>
				</div>
				<div class="ct">
					<div class="hd">访问来源分析</div>
					<div class="bd">
						<div id="pie1" style="width: 40%; float: left; height: 400px"></div>
						<div id="line2" style="width: 60%; float: left; height: 400px"></div>
					</div>
				</div>
				<div class="ct">
					<div class="hd">地域分布分析</div>
					<div class="bd">
						<div id="china" style="width: 50%; float: left; height: 400px"></div>
						<div id="pie" style="width: 50%; float: left; height: 400px"></div>
					</div>
				</div>
				<div class="ct">
					<div class="hd">系统环境分析</div>
					<div class="bd">
						<div id="pie2" style="width: 50%; float: left; height: 400px"></div>
					</div>
				</div>
				<div class="ct">
					<div class="hd">用户注册行为分析</div>
					<div class="bd">
						<div style="width: 30%; float: left; height: 400px">
							<dl style="margin: 50px 0 0 155px" class="left-label">
								<dd>
									<label>新增注册会员（人）：</label><span id="newUser">2323</span>
								</dd>
								<!-- <dd>
									<label>注册页面点击量（次）：</label><span id="clickRate"></span>
								</dd>
								<dd>
									<label>注册成功率：</label><span id="successRate"></span>
								</dd> -->
								<!-- <dd>
									<label>访客注册转化率：</label><span id="registerRate"></span>
								</dd> -->
								<dd>
									<label>访客数量（人）：</label><span id="visitorNum"></span>
								</dd>
								<dd>
									<label>注册转化率：</label><span id="registerRate"></span>
								</dd>
								<dd>
									<label>注册平均用时（秒）：</label><span id="registerTime"></span>
								</dd>
							</dl>
						</div>
						<div id="line3" style="width: 70%; float: left; height: 400px"></div>
					</div>
				</div>
				<div class="ct">
					<div class="hd">访问行为分析</div>
					<div class="bd">
						<div id="pie3" style="width: 40%; float: left; height: 400px"></div>
						<div id="bar" style="width: 60%; float: left; height: 400px"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		/* 设置统计数据 */
		function setAnalysisData(starttime, endtime) {
			$.post("analysis/getAnalysisData.form", {
				"starttime" : starttime,
				"endtime" : endtime
			}, function(data) {
				$('#pv').html(data.pv);
				$('#uv').html(data.uv);
				$('#ipNum').html(data.ipNum);
				$('#skip').html(data.skip);
				$('#lost').html(data.lost);
				$('#accessTime').html(data.accessTime);
			}, "json");
		}

		/* 设置注册统计数据 */
		function setRegistersData(starttime, endtime) {
			$.post("analysis/getRegistersData.form", {
				"starttime" : starttime,
				"endtime" : endtime
			}, function(data) {
				$('#newUser').html(data.newUser);
				/* $('#clickRate').html(data.clickRate);
				$('#successRate').html(data.successRate); */
				$('#visitorNum').html(data.visitorNum);
				$('#registerRate').html(data.registerRate);
				$('#registerTime').html(data.registerTime);
			}, "json");
		}

		setAnalysisData('', '');
		setRegistersData('', '');

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

		//不同统计项
		$('input:radio[name="type"]').change(function() {
			var period = $('.active').attr('id');
			var type = $(this).val();
			line1.setOption({
				series : [{
					name:type
				}],
			});
			line1.showLoading({});
			getChartData($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"), type);
		})

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
		//折线图
		var line1 = echarts.init(document.getElementById('line1'));

		// 显示标题，图例和空的坐标轴
		line1.setOption({
			title : {
				text : ''
			},
			tooltip : {
				trigger : 'axis',
				formatter: '{b0}: {c0}'
				
			},
			legend : {
				data : []
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
		
		getChartData('', '', 'pv');
		getChartData2('', '');

		// 异步加载数据  第一个折线图
		function getChartData(starttime, endtime, type) {
			$.get('analysis/getLineData.form?starttime=' + starttime + '&endtime=' + endtime + '&type=' + type).done(function(data) {
				console.info(data.lineData[0].name);
				var name = data.lineData[0].name;
				// 填入数据
				line1.setOption({
					series : [{
						name:name,
			            type:'line',
			            data:data.lineData[0].data
					}],
					xAxis : [ {
						type :'category',
						data : data.xAxis
					} ]
				});
				line1.hideLoading({})
			});
		}


		//第一个饼状图
		var pie1 = echarts.init(document.getElementById('pie1'));

		// 显示标题，图例和空的坐标轴
		pie1.setOption({
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
		var line2 = echarts.init(document.getElementById('line2'));

		// 显示标题，图例和空的坐标轴
		line2.setOption({
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

		
		function getChartData2(starttime, endtime) {
			$.get('analysis/getAccessData.form?starttime=' + starttime + '&endtime=' + endtime).done(function(data) {
				// 填入数据
				pie1.setOption({
					series : [ {
						// 根据名字对应到相应的系列
						data : data.pieData
					} ]
				});
				line2.setOption({
					xAxis : [ {
						data : data.xAxis
					} ],
					series : data.lineData
				});
				pie1.hideLoading({});
				line2.hideLoading({});
			});
		}
	</script>

	<!-- 为ECharts准备一个具备大小（宽高）的Dom  地图 -->
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('china'));

		// 显示标题，图例和空的坐标轴  地图
		myChart.setOption({
			title : {
				text : 'IP数区域分布',
				x : 'left'
			},
			tooltip : {
				trigger : 'item'
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : [ '区域访问' ]
			},
			dataRange : {
				min : 0,
				max : 2500,
				x : 'left',
				y : 'bottom',
				text : [ '高', '低' ], // 文本，默认为数值文本
				calculable : true
			},
			color : [ '#d48265', '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074', '#546570', '#c4ccd3' ],
			toolbox : {
				show : true,
				orient : 'vertical',
				x : 'right',
				y : 'center',
				feature : {
					mark : {
						show : true
					},
					/* dataView : {
						show : true,
						readOnly : false
					}, */
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			roamController : {
				show : true,
				x : 'right',
				mapTypeControl : {
					'china' : true
				}
			}
		/* ,
				    
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
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left'
			},
			series : [ {
				name : '访问来源',
				type : 'pie',
				selectedMode : 'single',
				radius : [ 0, '30%' ],

				label : {
					normal : {
						position : 'inner'
					}
				},
				labelLine : {
					normal : {
						show : false
					}
				}

			} ]
		});

		getChartData3('', '');
		//loadnPage();

		// 异步加载数据
		function getChartData3(starttime, endtime) {

			$.get('analysis/getChinaData.form?starttime=' + starttime + '&endtime=' + endtime).done(function(data) {
				//alert(JSON.stringify(data))
				// 填入数据
				myChart.setOption({
					series : [ {
						name : '地域',
						type : 'map',
						mapType : 'china',
						selectedMode : 'single',
						roam : true,
						showLegendSymbol : true,
						// 根据名字对应到相应的系列
						data : data,

						itemStyle : {
							normal : {
								label : {
									show : true
								},
								borderWidth : 1,//省份的边框宽度
								borderColor : '#f60',//省份的边框颜色
								color : 'green'//地图背景颜色
							//areaStyle:{color:'#f60'}//设置地图颜色
							},
							emphasis : {
								label : {
									show : true
								}
							}
						}
					} ]

				});
				myChart.hideLoading();

			});

			$.get('analysis/getChinaPieData.form?starttime=' + starttime + '&endtime=' + endtime).done(function(data) {
				//alert(JSON.stringify(data))

				myChart2.setOption({

					series : [ {
						name : '访问来源',
						type : 'pie',
						selectedMode : 'single',
						radius : [ 0, '30%' ],

						label : {
							normal : {
								position : 'inner'
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						}

					}, {
						type : 'pie',
						radius : [ '20%', '65%' ],
						data : data
					} ]
				});
				myChart2.hideLoading({});
			});
		}

		$("#search").click(function() {
			$(".dt").removeClass("active");
			loadnPage();
		})
		function loadnPage() {
			showLoading();
			setAnalysisData($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
			var type = $('input:radio:checked').val();
			getChartData($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"), type);

			getChartData2($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
			getChartData3($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
			getChartData4($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
			setRegistersData($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
			getChartData5($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
			getChartData6($("#beginDateTime").datebox("getValue"), $("#endDateTime").datebox("getValue"));
		}
	</script>

	<!-- 为ECharts准备一个具备大小（宽高）的Dom 系统环境分析 -->
	<script type="text/javascript">
		//第二个饼状图
		var pie2 = echarts.init(document.getElementById('pie2'));

		// 显示标题，图例和空的坐标轴
		pie2.setOption({
			title : {
				text : '渠道ip占比',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'right',
				top : 'middle',
				data : [ 'pc', 'IOS', '安卓', 'H5' ]
			},
			series : [ {
				name : 'ip数',
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

		getChartData4('', '');

		//异步加载饼状图数据
		function getChartData4(starttime, endtime) {
			$.get('analysis/getChannelPieData.form?starttime=' + starttime + '&endtime=' + endtime).done(function(data) {
				// 填入数据
				pie2.setOption({
					series : [ {
						// 根据名字对应到相应的系列
						data : data
					} ]
				});
				pie2.hideLoading({})
			});
		}
	</script>

	<script type="text/javascript">
		//第三个折线图
		var line3 = echarts.init(document.getElementById('line3'));

		// 显示标题，图例和空的坐标轴
		line3.setOption({
			title : {
				text : ''
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '新增注册会员（人）', '访客数量（人）' ]
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

		getChartData5('', '');

		// 异步加载数据  第一个折线图
		function getChartData5(starttime, endtime) {
			$.get('analysis/getRegisterLineData.form?starttime=' + starttime + '&endtime=' + endtime).done(function(data) {
				// 填入数据
				line3.setOption({
					xAxis : [ {
						data : data.xAxis
					} ],
					series : data.lineData
				});
				line3.hideLoading({});
			});
			
		}
	</script>

	<script type="text/javascript">
		//第三个饼状图
		var pie3 = echarts.init(document.getElementById('pie3'));

		// 显示标题，图例和空的坐标轴
		pie3.setOption({
			title : {
				text : '页面浏览量占比',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : []
			},
			series : [ {
				name : '页面访问量',
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

		//柱状图
		var bar = echarts.init(document.getElementById('bar'));

		// 显示标题，图例和空的坐标轴
		bar.setOption({
			color : [ '#3398DB' ],
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
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
				data : [],
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '页面访问量',
				type : 'bar',
				barWidth : '60%',
				data : []
			} ]
		});

		showLoading();
		getChartData6('', '');
		
		// 异步加载数据  
		function getChartData6(starttime, endtime) {
			$.get('analysis/getPagePvData.form?starttime=' + starttime + '&endtime=' + endtime).done(function(data) {
				// 填入数据
				pie3.setOption({
					legend : {
						data : data.pvPieName
					},
					series : [ {
						data : data.pagePvPie
					} ],
				});

				bar.setOption({
					xAxis : [ {
						data : data.xAxis
					} ],
					series : [ {
						data : data.series
					} ],
				});
				pie3.hideLoading({});
				bar.hideLoading({});
			});
		}
		
		function showLoading() {
			line1.showLoading({
			})
			pie1.showLoading({
			})
			line2.showLoading({
			})
			myChart.showLoading({
			})
			myChart2.showLoading({
			})
			/* pie.showLoading({
			})
 */			pie2.showLoading({
			})
			line3.showLoading({
			})
			pie3.showLoading({
			})
			bar.showLoading({
			})
		}
		
	
	</script>
</body>