package com.abcft.system.analysis;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.GsonBuilder;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.StringUtils;

@Controller
@RequestMapping("analysis")
public class AnalysisController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private AnalysisDao analysisDao;

	@RequestMapping("/findPvList")
	public void findPvList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * List<SqlPv> list = analysisDao.findPvList(); for (SqlPv sqlPv : list)
		 * { System.out.println(sqlPv.toString()); }
		 */
	}

	/**
	 * 访问来源的饼状图数据 折线图数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getAccessData")
	public void getAccessData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, List> data = new HashMap<String, List>();

		try {
			// 统计周期
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			// 默认显示今天的统计数据
			if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
				Calendar cal = Calendar.getInstance();
				starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
				endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			}

			// 折线图的x轴数据
			List<String> xAxis = new ArrayList<String>();
			// 三种访问来源的数据 访问来源 0：直接访问1：站内链接2：站外链接
			LineData data0 = new LineData();
			data0.setName("直接访问");
			data0.setType("line");
			LineData data1 = new LineData();
			data1.setName("站内链接");
			data1.setType("line");
			LineData data2 = new LineData();
			data2.setName("站外链接");
			data2.setType("line");

			// 封装折线图的series数据
			List<LineData> lineData = new ArrayList<LineData>();

			List<Object> list0 = new ArrayList<Object>();
			List<Object> list1 = new ArrayList<Object>();
			List<Object> list2 = new ArrayList<Object>();

			if (starttime.equals(endtime)) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starttime));
				// 查询一天的数据 x轴为24小时
				for (int i = 0; i < 24; i++) {
					xAxis.add(i + ":00");
					// 每小时的访问量 每一条折线的数据
					list0.add(analysisDao.getHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i, 0));
					list1.add(analysisDao.getHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i, 1));
					list2.add(analysisDao.getHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i, 2));
				}
			} else {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starttime));
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(endtime));
				// 查询一段时间的数据 x为天数
				while (cal1.compareTo(cal2) <= 0) {
					xAxis.add(new SimpleDateFormat("MM/dd").format(cal1.getTime()));
					// 每天的访问量 每一条折线的数据
					list0.add(analysisDao.getHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1, 0));
					list1.add(analysisDao.getHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1, 1));
					list2.add(analysisDao.getHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1, 2));
					cal1.add(Calendar.DAY_OF_YEAR, 1);
				}
			}

			data0.setData(list0);
			data1.setData(list1);
			data2.setData(list2);

			lineData.add(data0);
			lineData.add(data1);
			lineData.add(data2);

			data = new HashMap<String, List>();
			// 饼状图数据
			// 饼状图的统计周期范围
			String begin = starttime + " 00:00:00";
			String end = endtime += " 23:59:59";
			List<AccessSource> list = analysisDao.getAccessData(begin, end);
			data.put("pieData", list);
			data.put("xAxis", xAxis);
			data.put("lineData", lineData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerUtil.writeJson(response, new GsonBuilder().create().toJson(data));
	}

	/**
	 * 访问来源的页面统计数据表格
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findAccessGrid")
	public void findAccessGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String json = "";
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("utf-8");

			// 统计周期
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			// 默认显示今天的统计数据
			if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
				Calendar cal = Calendar.getInstance();
				starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
				endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			}

			String begin = starttime + " 00:00:00";
			String end = endtime += " 23:59:59";

			// 根据访问来源分类统计
			List<AccessGrid> list = analysisDao.findAccessGrid(begin, end);
			JSONArray rootJsonArr = new JSONArray();
			for (AccessGrid grid : list) {
				JSONObject root = new JSONObject();
				root.accumulate("id", 10000 + grid.getId());
				root.accumulate("name", grid.getName());
				root.accumulate("pv", grid.getPv());
				root.accumulate("uv", grid.getUv());
				root.accumulate("ipNum", grid.getIpNum());
				root.accumulate("source", grid.getSource());
				root.accumulate("accessTime", grid.getAccessTime());
				root.accumulate("iconCls", null);
				// 设置二跳率
				root.accumulate("skip", analysisDao.getSkipBySource(grid.getSource(), null, begin, end));
				JSONArray deptJsonArr = getDeptTree(grid.getSource(), begin, end);
				if (!StringUtils.isEmpty(deptJsonArr)) {
					root.accumulate("state", "open");
					root.put("children", deptJsonArr);
				}
				rootJsonArr.put(root);
			}
			json = rootJsonArr.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerUtil.writeJson(response, json);

	}

	/**
	 * 访问来源的区域数据，展示成中国地图
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getChinaData")
	public void getChinaData(HttpServletRequest request, HttpServletResponse response) {
		// 统计周期
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		// 默认显示今天的统计数据
		Calendar cal = Calendar.getInstance();

		try {
			// 查询各个省份的数据,为地图做数据
			List<MapGrid> list = analysisDao.findChinaGrid(starttime, endtime);
			JSONArray rootJsonArr = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject root = new JSONObject();
				MapGrid grid = list.get(i);

				root.accumulate("name", grid.getName().replaceAll("省", ""));
				// root.accumulate("selected", false);
				root.accumulate("value", grid.getIpNum());

				rootJsonArr.put(root);
			}

			String json = rootJsonArr.toString();

			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ControllerUtil.writeJson(response, new
		// GsonBuilder().create().toJson(rootJsonArr));
	}

	/**
	 * 访问来源的区域数据，展示成环状图
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getChinaPieData")
	public void getChinaPieData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 统计周期
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		// 默认显示今天的统计数据
		Calendar cal = Calendar.getInstance();

		int topTen = 0;
		try {
			// 查询各个省份的数据,为地图做数据
			List<MapGrid> list = analysisDao.findChinaGridForChinaPie(starttime, endtime);
			JSONArray rootJsonArr = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject root = new JSONObject();
				MapGrid grid = list.get(i);

				root.accumulate("name", grid.getName());
				root.accumulate("value", grid.getIpNum());
				topTen = topTen + grid.getIpNum();
				rootJsonArr.put(root);
			}
			int total = analysisDao.findChinaGridForChinaPieTotal(starttime, endtime);
			JSONObject root2 = new JSONObject();
			root2.accumulate("name", "其他");
			root2.accumulate("value", total - topTen);
			rootJsonArr.put(root2);
			String json = rootJsonArr.toString();

			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ControllerUtil.writeJson(response, new
		// GsonBuilder().create().toJson(rootJsonArr));
	}

	/**
	 * 将区域的访问数据展示成表单
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/findChinaGrid")
	public void findChinaGrid(HttpServletRequest request, HttpServletResponse response)  {
		try {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		JSONObject treeJsonObject = new JSONObject();
		// 根据访问来源分类统计
		List<MapGrid> list = analysisDao.findChinaGrid(starttime, endtime);
		JSONArray rootJsonArr = new JSONArray();
		int num = 0;
		for (MapGrid grid : list) {
			JSONObject root = new JSONObject();
			root.accumulate("id", grid.getId());
			root.accumulate("name", grid.getName());
			root.accumulate("pv", grid.getPv());
			root.accumulate("uv", grid.getUv());

			// 2跳率
			
	        if(Integer.valueOf(grid.getUv())==0){
	        	root.accumulate("skip","0%");
	        }else{
	        	root.accumulate("skip", new BigDecimal(analysisDao.findChinaGridSkip(starttime, endtime, grid.getName())).multiply(new BigDecimal(100)).divide(new BigDecimal(grid.getUv()), 2, BigDecimal.ROUND_HALF_UP)+"%");
				
	        }
			if (grid.getRegistVisitNum() == null || grid.getRegistVisitNum() == "0" || grid.getRegistVisitNum().equals("") || grid.getRegistVisitNum().equals("0")) {
				root.accumulate("accessRegist", "0人&nbsp;&nbsp;&nbsp;  0%");
			} else {
				root.accumulate("accessRegist", grid.getRegistNum() + "人 &nbsp;&nbsp;&nbsp; " + new BigDecimal(grid.getRegistNum()).multiply(new BigDecimal(100)).divide(new BigDecimal(grid.getUv()), 2, BigDecimal.ROUND_HALF_UP) + "%");
			}

			root.accumulate("ipNum", grid.getIpNum());
			root.accumulate("source", grid.getSource());
			root.accumulate("accessTime", grid.getAccessTime());

			rootJsonArr.put(root);
		}
		String json = rootJsonArr.toString();
		
			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONArray getDeptTree(Integer parentId, String begin, String end) throws JSONException {
		List<AccessGrid> list = analysisDao.findAccessGridBySource(parentId, begin, end);
		JSONArray deptJsonArr = new JSONArray();
		for (AccessGrid grid : list) {
			JSONObject treeJsonObject = new JSONObject();
			/* treeJsonObject.accumulate("name", grid.getName()); */
			treeJsonObject.accumulate("id", grid.getId());
			treeJsonObject.accumulate("name", grid.getUrl());
			treeJsonObject.accumulate("pv", grid.getPv());
			treeJsonObject.accumulate("uv", grid.getUv());
			treeJsonObject.accumulate("ipNum", grid.getIpNum());
			treeJsonObject.accumulate("source", grid.getSource());
			treeJsonObject.accumulate("accessTime", grid.getAccessTime());
			treeJsonObject.accumulate("iconCls", "");
			// 设置二跳率
			treeJsonObject.accumulate("skip", analysisDao.getSkipBySource(grid.getSource(), grid.getUrl(), begin, end));

			treeJsonObject.accumulate("accessRegist", grid.getAccessTime());

			deptJsonArr.put(treeJsonObject);
		}

		return deptJsonArr;
	}

	/**
	 * 页面排行列表（pc）
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/pageSort")
	public void findPageSort(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String json = "";
		try {
			// 统计周期
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			// 默认显示今天的统计数据
			if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
				Calendar cal = Calendar.getInstance();
				starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
				endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			}

			String begin = starttime + " 00:00:00";
			String end = endtime + " 23:59:59";

			List<PageGrid> list = analysisDao.findPageGrid(begin, end);;
			json = new GsonBuilder().create().toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerUtil.writeJson(response, json);
	}
	
	/**
	 * ios、安卓、h5的页面排行统计
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/channelPageSort")
	public void findChannelPageSort(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String json = "";
		try {
			// 统计周期
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			// 默认显示今天的统计数据
			if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
				Calendar cal = Calendar.getInstance();
				starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
				endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			}

			String begin = starttime + " 00:00:00";
			String end = endtime + " 23:59:59";
			String channel = request.getParameter("channel");

			List<PageGrid> list = analysisDao.findChannelPageGrid(channel,begin, end);
			json = new GsonBuilder().create().toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerUtil.writeJson(response, json);
	}


	/**
	 * 获得统计数据 uv ip数 pv 二跳率 跳失率 平均访问时长
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAnalysisData")
	public void getAnalysisData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 统计周期
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		// 默认显示今天的统计数据
		if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
			Calendar cal = Calendar.getInstance();
			starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		starttime += " 00:00:00";
		endtime += " 23:59:59";
		int pv = analysisDao.getPvData(starttime, endtime);
		int uv = analysisDao.getUvData(starttime, endtime);
		int ipNum = analysisDao.getIpNum(starttime, endtime);
		String skip = analysisDao.getSkipData(starttime, endtime);
		String lost = analysisDao.getLostData(starttime, endtime);
		String accessTime = analysisDao.getAccessTime(starttime, endtime);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pv", pv*10);
		map.put("uv", uv*10);
		map.put("ipNum", ipNum*10);
		map.put("skip", skip);
		map.put("lost", lost);
		map.put("accessTime", accessTime);

		ControllerUtil.writeJson(response, new GsonBuilder().create().toJson(map));

	}

	/**
	 * 获得不同统计项的折线图数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getLineData")
	public void getLineData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, List> data = new HashMap<String, List>();

		try {
			// 统计周期
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			// 默认显示今天的统计数据
			if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
				Calendar cal = Calendar.getInstance();
				starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
				endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			}
			// 折线图的x轴数据
			List<String> xAxis = new ArrayList<String>();
			// 新增注册会员 注册页面点击量
			LineData data0 = new LineData();
			data0.setType("line");

			// 封装折线图的series数据
			List<LineData> lineData = new ArrayList<LineData>();
			List<Object> list0 = new ArrayList<Object>();

			// 统计数据类型
			String type = "pv";
			if (!StringUtils.isEmpty(request.getParameter("type"))) {
				type = request.getParameter("type");
			}

			if (starttime.equals(endtime)) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starttime));
				// 查询一天的数据 x轴为24小时
				for (int i = 0; i < 24; i++) {
					xAxis.add(i + ":00");
					// 每小时的访问量 每一条折线的数据
					if ("pv".equals(type)) {
						// 每小时的访问量 每一条折线的数据
						data0.setName("pv");
						list0.add(10*(analysisDao.gePvtHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i)));
					} else if ("uv".equals(type)) {
						// 每小时的访客量 每一条折线的数据
						data0.setName("uv");
						list0.add(10*(analysisDao.geUvtHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i)));
					} else if ("ipNum".equals(type)) {
						// 每小时的访问ip 每一条折线的数据
						data0.setName("ipNum");
						list0.add(10*(analysisDao.geIpNumtHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i)));
					} else if ("lost".equals(type)) {
						// 每小时的跳失率 每一条折线的数据
						data0.setName("lost");
						list0.add(analysisDao.geLostHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i));
					} else if ("accessTime".equals(type)) {
						// 每小时平均访问时间
						data0.setName("accessTime");
						list0.add(analysisDao.geAccessTimeHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i));
					}
				}
			} else {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starttime));
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(endtime));
				// 查询一段时间的数据 x为天数
				while (cal1.compareTo(cal2) <= 0) {
					xAxis.add(new SimpleDateFormat("MM/dd").format(cal1.getTime()));
					// 每天的访问量 每一条折线的数据
					if ("pv".equals(type)) {
						// 每小时的访问量 每一条折线的数据
						data0.setName("pv");
						list0.add(10*(analysisDao.gePvtHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1)));
					} else if ("uv".equals(type)) {
						// 每小时的访客量 每一条折线的数据
						data0.setName("uv");
						list0.add(10*(analysisDao.geUvtHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1)));
					} else if ("ipNum".equals(type)) {
						// 每小时的访问ip 每一条折线的数据
						data0.setName("ipNum");
						list0.add(10*(analysisDao.geIpNumtHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1)));
					} else if ("lost".equals(type)) {
						// 每小时的跳失率 每一条折线的数据
						data0.setName("lost");
						list0.add(analysisDao.geLostHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1));
					} else if ("accessTime".equals(type)) {
						// 每小时平均访问时间
						data0.setName("accessTime");
						list0.add(analysisDao.geAccessTimeHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1));
					}

					cal1.add(Calendar.DAY_OF_YEAR, 1);
				}
			}

			data0.setData(list0);

			lineData.add(data0);

			data = new HashMap<String, List>();
			data.put("xAxis", xAxis);
			data.put("lineData", lineData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerUtil.writeJson(response, new GsonBuilder().create().toJson(data));
	}

	/**
	 * 统计不同渠道的ip数
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getChannelPieData")
	public void getChannelPieData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 统计周期
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		// 默认显示今天的统计数据
		if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
			Calendar cal = Calendar.getInstance();
			starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		starttime += " 00:00:00";
		endtime += " 23:59:59";

		// 查询各个省份的数据,为地图做数据
		List<AccessSource> list = analysisDao.findChannelData(starttime, endtime);
		for (AccessSource source : list) {
			if ("1".equals(source.getName())) {
				source.setName("pc");
			} else if ("2".equals(source.getName())) {
				source.setName("IOS");
			} else if ("3".equals(source.getName())) {
				source.setName("安卓");
			} else if ("4".equals(source.getName())) {
				source.setName("H5");
			}
		}
		String json = new GsonBuilder().create().toJson(list);
		try {
			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ControllerUtil.writeJson(response, new
		// GsonBuilder().create().toJson(rootJsonArr));
	}

	/**
	 * 统计用户注册的相关数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getRegistersData")
	public void getRegisterData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 统计周期
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		// 默认显示今天的统计数据
		if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
			Calendar cal = Calendar.getInstance();
			starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		starttime += " 00:00:00";
		endtime += " 23:59:59";

		// 新增注册会员
		int newUser = analysisDao.getNewUserData(starttime, endtime);
		// 注册页面点击量 	暂时不统计
		//int clickRate = analysisDao.getClickRateData(starttime, endtime);
		// 注册成功率  暂时不统计
		//String successRate = analysisDao.getSuccessRate(starttime, endtime);
		// 未登录的访客人数
		int noLoginUser = analysisDao.getNoLoginUser(starttime, endtime);
		// 访客注册转化率 注册的访客/userid为空的访客
		String registerRate = "";
		try {
			if (noLoginUser == 0) {
				registerRate = "0.00%";
			} else {
				registerRate = String.format("%.2f", (float) newUser / noLoginUser * 100) + "%";
			}
			if ("NaN".equals(registerRate)) {
				registerRate = "0.00%";
			}
		} catch (Exception e) {
			registerRate = "0.00%";
			e.printStackTrace();
		}
		String registerTime = analysisDao.getRegisterTime(starttime, endtime);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newUser", newUser);
		//map.put("clickRate", clickRate);
		//map.put("successRate", successRate);
		map.put("visitorNum", noLoginUser);
		map.put("registerRate", registerRate);
		map.put("registerTime", registerTime);

		ControllerUtil.writeJson(response, new GsonBuilder().create().toJson(map));

	}

	/**
	 * 新增注册会员 注册页面点击量 折线图
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getRegisterLineData")
	public void getRegisterLineData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, List> data = new HashMap<String, List>();

		try {
			// 统计周期
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			// 默认显示今天的统计数据
			if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
				Calendar cal = Calendar.getInstance();
				starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
				endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			}
			// 折线图的x轴数据
			List<String> xAxis = new ArrayList<String>();
			// 新增注册会员 注册页面点击量
			LineData data0 = new LineData();
			data0.setName("新增注册会员（人）");
			data0.setType("line");
			LineData data1 = new LineData();
			//data1.setName("注册页面点击量（次）");
			data1.setName("访客数量（人）");
			data1.setType("line");

			// 封装折线图的series数据
			List<LineData> lineData = new ArrayList<LineData>();
			List<Object> list0 = new ArrayList<Object>();
			List<Object> list1 = new ArrayList<Object>();

			if (starttime.equals(endtime)) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starttime));
				// 查询一天的数据 x轴为24小时
				for (int i = 0; i < 24; i++) {
					xAxis.add(i + ":00");
					// 每小时的访问量 每一条折线的数据
					list0.add(analysisDao.getNewUserHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i));
					//list1.add(analysisDao.getClickRateHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i));
					list1.add(analysisDao.getNoLoginUserHourData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), i));
				}
			} else {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starttime));
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(endtime));
				// 查询一段时间的数据 x为天数
				while (cal1.compareTo(cal2) <= 0) {
					xAxis.add(new SimpleDateFormat("MM/dd").format(cal1.getTime()));
					// 每天的访问量 每一条折线的数据
					list0.add(analysisDao.getNewUserHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1));
					//list1.add(analysisDao.getClickRateHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1));
					list1.add(analysisDao.getNoLoginUserHourData(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + 1, cal1.get(Calendar.DAY_OF_MONTH), -1));
					cal1.add(Calendar.DAY_OF_YEAR, 1);
				}
			}

			data0.setData(list0);
			data1.setData(list1);

			lineData.add(data0);
			lineData.add(data1);

			data = new HashMap<String, List>();
			data.put("xAxis", xAxis);
			data.put("lineData", lineData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerUtil.writeJson(response, new GsonBuilder().create().toJson(data));
	}

	/**
	 * 统计访客浏览量占比 饼状图 柱状图
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getPagePvData")
	public void getPagePvData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 统计周期
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		// 默认显示今天的统计数据
		if (StringUtils.isEmpty(starttime) && StringUtils.isEmpty(endtime)) {
			Calendar cal = Calendar.getInstance();
			starttime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			endtime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		starttime += " 00:00:00";
		endtime += " 23:59:59";

		// 柱状图x轴数据 series数据
		List<String> xAxis = new ArrayList<String>();
		List<Integer> series = new ArrayList<Integer>();

		// 访问量排名前五的页面 饼状图数据
		List<AccessSource> list = analysisDao.findPagePvData(starttime, endtime);
		// 总pv量
		int pv = analysisDao.findAllPvData(starttime, endtime);
		// 饼状图name
		List<String> name = new ArrayList<String>();
		for (AccessSource s : list) {
			pv -= Integer.parseInt(s.getValue());
			name.add(s.getName());
			xAxis.add(s.getName());
			series.add(Integer.parseInt(s.getValue()));
		}
		AccessSource other = new AccessSource();
		other.setName("其他");
		other.setValue(String.valueOf(pv));
		list.add(other);
		name.add("其他");
		xAxis.add("其他");
		series.add(pv);

		Map<String, List> map = new HashMap<String, List>();
		map.put("pagePvPie", list);
		map.put("pvPieName", name);
		map.put("xAxis", xAxis);
		map.put("series", series);
		String json = new GsonBuilder().create().toJson(map);
		try {
			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
