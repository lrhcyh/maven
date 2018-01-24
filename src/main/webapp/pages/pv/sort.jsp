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
	<div class="easyui-tabs" style="width: 100%; height: 100%" data-options="onSelect: function(title) {
																											$('.dt').removeClass('active');
																														if(title=='PC') {
																															$('#today').addClass('active');
																															$('#beginDateTime').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															$('#endDateTime').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															loadData();
																														} else if(title == 'IOS') {
																															$('#today2').addClass('active');
																															$('#beginDateTime2').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															$('#endDateTime2').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															loadData2();
																														} else if(title == '安卓') {
																															$('#today3').addClass('active');
																															$('#beginDateTime3').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															$('#endDateTime3').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															loadData3();
																														} else if(title == 'H5') {
																															$('#today4').addClass('active');
																															$('#beginDateTime4').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															$('#endDateTime4').datebox('setValue', new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate());
																															loadData4();
																														}
																													}">
		<div title="PC" style="padding: 10px">
			<div class="pd10" style="height: 100%">
				<div class="hxc-position"></div>
				<div class="hxc-wrapper">
					<div class="hxc-filter" style="height: 10%">
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
					<div class="ct" style="height: 90%">
						<table id="dg" class="easyui-datagrid" style="width: 100%; height: inherit;" data-options="singleSelect:true,collapsible:true,url:'analysis/pageSort.form',method:'get',rownumbers:true,striped:true,border:false">
							<thead>
								<tr>
									<th data-options="field:'pageName',align:'center'" style="width: 20%">页面名称</th>
									<th data-options="field:'url_dest',align:'center'" style="width: 33%">页面地址</th>
									<th data-options="field:'pv',align:'center'" style="width: 15%">页面总访问量PV</th>
									<th data-options="field:'uv',align:'center'" style="width: 15%">独立访问量UV</th>
									<th data-options="field:'accessTime',align:'center'" style="width: 15%">平均访问时间（秒）</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div title="IOS" style="padding: 10px">
			<div class="pd10" style="height: 100%">
				<div class="hxc-position"></div>
				<div class="hxc-wrapper">
					<div class="hxc-filter" style="height: 10%">
						统计周期
						<dl class="time-filter">
							<dd id="today2" class="active dt">今天</dd>
							<dd id="yesterday2" class="dt">昨天</dd>
							<dd id="week2" class="dt">本周</dd>
							<dd id="month2" class="dt">本月</dd>
							<dd id="seven2" class="dt">近7天</dd>
							<dd id="thirty2" class="dt">近30天</dd>
							<dd>
								<input id="beginDateTime2" name="beginDateTime" class="easyui-datebox" data-options="editable:false"></input>至<input id="endDateTime2" name="endDateTime" class="easyui-datebox" data-options="editable:false"></input>
							</dd>
						</dl>
						<span class="btn" onclick="searchPv2()">查询</span>
					</div>
					<div class="ct" style="height: 90%">
						<table id="dg2" class="easyui-datagrid" style="width: 100%; height: inherit;" data-options="singleSelect:true,
																													collapsible:true,url:'analysis/channelPageSort.form?channel=2',
																													method:'get',
																													rownumbers:true,
																													striped:true,
																													border:false">
							<thead>
								<tr>
									<th data-options="field:'url_dest',align:'center'" style="width: 33%">页面地址</th>
									<th data-options="field:'pv',align:'center'" style="width: 15%">页面总访问量PV</th>
									<th data-options="field:'uv',align:'center'" style="width: 15%">独立访问量UV</th>
									<th data-options="field:'accessTime',align:'center'" style="width: 15%">平均访问时间（秒）</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div title="安卓" style="padding: 10px">
			<div class="pd10" style="height: 100%">
				<div class="hxc-position"></div>
				<div class="hxc-wrapper">
					<div class="hxc-filter" style="height: 10%">
						统计周期
						<dl class="time-filter">
							<dd id="today3" class="active dt">今天</dd>
							<dd id="yesterday3" class="dt">昨天</dd>
							<dd id="week3" class="dt">本周</dd>
							<dd id="month3" class="dt">本月</dd>
							<dd id="seven3" class="dt">近7天</dd>
							<dd id="thirty3" class="dt">近30天</dd>
							<dd>
								<input id="beginDateTime3" name="beginDateTime" class="easyui-datebox" data-options="editable:false"></input>至<input id="endDateTime3" name="endDateTime" class="easyui-datebox" data-options="editable:false"></input>
							</dd>
						</dl>
						<span class="btn" onclick="searchPv3()">查询</span>
					</div>
					<div class="ct" style="height: 90%">
						<table id="dg3" class="easyui-datagrid" style="width: 100%; height: inherit;" data-options="singleSelect:true,collapsible:true,url:'analysis/channelPageSort.form?channel=3',method:'get',rownumbers:true,striped:true,border:false">
							<thead>
								<tr>
									<th data-options="field:'url_dest',align:'center'" style="width: 33%">页面地址</th>
									<th data-options="field:'pv',align:'center'" style="width: 15%">页面总访问量PV</th>
									<th data-options="field:'uv',align:'center'" style="width: 15%">独立访问量UV</th>
									<th data-options="field:'accessTime',align:'center'" style="width: 15%">平均访问时间（秒）</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div title="H5" style="padding: 10px">
			<div class="pd10" style="height: 100%">
				<div class="hxc-position"></div>
				<div class="hxc-wrapper">
					<div class="hxc-filter" style="height: 10%">
						统计周期
						<dl class="time-filter">
							<dd id="today4" class="active dt">今天</dd>
							<dd id="yesterday4" class="dt">昨天</dd>
							<dd id="week4" class="dt">本周</dd>
							<dd id="month4" class="dt">本月</dd>
							<dd id="seven4" class="dt">近7天</dd>
							<dd id="thirty4" class="dt">近30天</dd>
							<dd>
								<input id="beginDateTime4" name="beginDateTime" class="easyui-datebox" data-options="editable:false"></input>至<input id="endDateTime4" name="endDateTime" class="easyui-datebox" data-options="editable:false"></input>
							</dd>
						</dl>
						<span class="btn" onclick="searchPv4()">查询</span>
					</div>
					<div class="ct" style="height: 90%">
						<table id="dg4" class="easyui-datagrid" style="width: 100%; height: inherit;" data-options="singleSelect:true,collapsible:true,url:'analysis/channelPageSort.form?channel=4',method:'get',rownumbers:true,striped:true,border:false">
							<thead>
								<tr>
									<th data-options="field:'url_dest',align:'center'" style="width: 33%">页面地址</th>
									<th data-options="field:'pv',align:'center'" style="width: 15%">页面总访问量PV</th>
									<th data-options="field:'uv',align:'center'" style="width: 15%">独立访问量UV</th>
									<th data-options="field:'accessTime',align:'center'" style="width: 15%">平均访问时间（秒）</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		

	</div>


	<script type="text/javascript">
		function searchPv() {
			$(".dt").removeClass("active");
			loadData();
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

			loadData();
		});
		$("#yesterday").click(function() {
			$(".dt").removeClass("active");
			$("#yesterday").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-1));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate() - 1));

			loadData();

		});
		$("#week").click(function() {
			$(".dt").removeClass("active");
			$("#week").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-new Date().getDay() + 1));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData();
		});
		$("#month").click(function() {
			$(".dt").removeClass("active");
			$("#month").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-new Date().getDate() + 1));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData();

		});
		$("#seven").click(function() {
			$(".dt").removeClass("active");
			$("#seven").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-7));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			loadData();

		});
		$("#thirty").click(function() {
			$(".dt").removeClass("active");
			$("#thirty").addClass("active");
			$("#beginDateTime").datebox("setValue", GetDateStr(-30));
			$("#endDateTime").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData();
		});

		function loadData() {
			var begin = $('#beginDateTime').datebox('getValue');
			var end = $('#endDateTime').datebox('getValue');
			$('#dg').datagrid('load', {
				starttime : begin,
				endtime : end
			});
		}
	</script>
	<!-- ios -->
	<script type="text/javascript">
		function searchPv2() {
			$(".dt").removeClass("active");
			loadData2();
		}
		$("#beginDateTime2").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		$("#endDateTime2").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		function GetDateStr(AddDayCount) {
			var dd = new Date();
			dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
			var y = dd.getFullYear();
			var m = dd.getMonth() + 1;//获取当前月份的日期
			var d = dd.getDate();
			return y + "-" + m + "-" + d;
		}
		$("#today2").click(function() {
			$(".dt").removeClass("active");
			$("#today2").addClass("active");
			$("#beginDateTime2").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			$("#endDateTime2").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData2();
		});
		$("#yesterday2").click(function() {
			$(".dt").removeClass("active");
			$("#yesterday2").addClass("active");
			$("#beginDateTime2").datebox("setValue", GetDateStr(-1));
			$("#endDateTime2").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate() - 1));

			loadData2();

		});
		$("#week2").click(function() {
			$(".dt").removeClass("active");
			$("#week2").addClass("active");
			$("#beginDateTime2").datebox("setValue", GetDateStr(-new Date().getDay() + 1));
			$("#endDateTime2").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData2();
		});
		$("#month2").click(function() {
			$(".dt").removeClass("active");
			$("#month2").addClass("active");
			$("#beginDateTime2").datebox("setValue", GetDateStr(-new Date().getDate() + 1));
			$("#endDateTime2").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData2();

		});
		$("#seven2").click(function() {
			$(".dt").removeClass("active");
			$("#seven2").addClass("active");
			$("#beginDateTime2").datebox("setValue", GetDateStr(-7));
			$("#endDateTime2").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			loadData2();

		});
		$("#thirty2").click(function() {
			$(".dt").removeClass("active");
			$("#thirty2").addClass("active");
			$("#beginDateTime2").datebox("setValue", GetDateStr(-30));
			$("#endDateTime2").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData2();
		});

		function loadData2() {
			var begin = $('#beginDateTime2').datebox('getValue');
			var end = $('#endDateTime2').datebox('getValue');
			$('#dg2').datagrid('load', {
				starttime : begin,
				endtime : end
			});
		}
	</script>
		<!-- ios -->
	<script type="text/javascript">
		function searchPv3() {
			$(".dt").removeClass("active");
			loadData3();
		}
		$("#beginDateTime3").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		$("#endDateTime3").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		
		$("#today3").click(function() {
			$(".dt").removeClass("active");
			$("#today3").addClass("active");
			$("#beginDateTime3").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			$("#endDateTime3").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData3();
		});
		$("#yesterday3").click(function() {
			$(".dt").removeClass("active");
			$("#yesterday3").addClass("active");
			$("#beginDateTime3").datebox("setValue", GetDateStr(-1));
			$("#endDateTime3").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate() - 1));

			loadData3();

		});
		$("#week3").click(function() {
			$(".dt").removeClass("active");
			$("#week3").addClass("active");
			$("#beginDateTime3").datebox("setValue", GetDateStr(-new Date().getDay() + 1));
			$("#endDateTime3").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData3();
		});
		$("#month3").click(function() {
			$(".dt").removeClass("active");
			$("#month3").addClass("active");
			$("#beginDateTime3").datebox("setValue", GetDateStr(-new Date().getDate() + 1));
			$("#endDateTime3").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData3();

		});
		$("#seven3").click(function() {
			$(".dt").removeClass("active");
			$("#seven3").addClass("active");
			$("#beginDateTime3").datebox("setValue", GetDateStr(-7));
			$("#endDateTime3").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			loadData3();

		});
		$("#thirty3").click(function() {
			$(".dt").removeClass("active");
			$("#thirty3").addClass("active");
			$("#beginDateTime3").datebox("setValue", GetDateStr(-30));
			$("#endDateTime3").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData3();
		});

		function loadData3() {
			var begin = $('#beginDateTime3').datebox('getValue');
			var end = $('#endDateTime3').datebox('getValue');
			$('#dg3').datagrid('load', {
				starttime : begin,
				endtime : end
			});
		}
	</script>
		<!-- ios -->
	<script type="text/javascript">
		function searchPv4() {
			$(".dt").removeClass("active");
			loadData4();
		}
		$("#beginDateTime4").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		$("#endDateTime4").val(new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
		
		$("#today4").click(function() {
			$(".dt").removeClass("active");
			$("#today4").addClass("active");
			$("#beginDateTime4").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			$("#endDateTime4").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData4();
		});
		$("#yesterday4").click(function() {
			$(".dt").removeClass("active");
			$("#yesterday4").addClass("active");
			$("#beginDateTime4").datebox("setValue", GetDateStr(-1));
			$("#endDateTime4").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + (new Date().getDate() - 1));

			loadData4();

		});
		$("#week4").click(function() {
			$(".dt").removeClass("active");
			$("#week4").addClass("active");
			$("#beginDateTime4").datebox("setValue", GetDateStr(-new Date().getDay() + 1));
			$("#endDateTime4").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData4();
		});
		$("#month4").click(function() {
			$(".dt").removeClass("active");
			$("#month4").addClass("active");
			$("#beginDateTime4").datebox("setValue", GetDateStr(-new Date().getDate() + 1));
			$("#endDateTime4").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData4();

		});
		$("#seven4").click(function() {
			$(".dt").removeClass("active");
			$("#seven4").addClass("active");
			$("#beginDateTime4").datebox("setValue", GetDateStr(-7));
			$("#endDateTime4").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
			loadData4();

		});
		$("#thirty4").click(function() {
			$(".dt").removeClass("active");
			$("#thirty4").addClass("active");
			$("#beginDateTime4").datebox("setValue", GetDateStr(-30));
			$("#endDateTime4").datebox("setValue", new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());

			loadData4();
		});

		function loadData4() {
			var begin = $('#beginDateTime4').datebox('getValue');
			var end = $('#endDateTime4').datebox('getValue');
			$('#dg4').datagrid('load', {
				starttime : begin,
				endtime : end
			});
		}
	</script>
</body>
</html>
