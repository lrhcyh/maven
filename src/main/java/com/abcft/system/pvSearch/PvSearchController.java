package com.abcft.system.pvSearch;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.DateUtils;
import com.abcft.common.util.StringUtils;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/pvSearch")
public class PvSearchController {
	
	@Autowired
	private PvSearchDao pvSearchDao;
	
	/**
	 * 要跳转的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoPage")
	public ModelAndView gotoPage(HttpServletRequest request) {
		try {
			String page = request.getParameter("page");
			if ("export".equals(page)) {
				return new ModelAndView("pv/export_search");
			} else if("addUserRole".equals(page)) {
				ModelAndView mv = new ModelAndView("users/auth");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示搜索管理列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findList")
	public void findList(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");

			// 查询参数
			String startTime = request.getParameter("startTime");
			if (null != startTime && startTime.length() > 1) {
				startTime = startTime + " 00:00:00";
			}
			String endTime = request.getParameter("endTime");
			if (null != endTime && endTime.length() > 1) {
				endTime = endTime + " 23:59:59";
			}
			
			String type = request.getParameter("type");

			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 100 : Long.valueOf(rows);

			long start = (currentPage - 1) * pageSize;
			long end = pageSize;

			List<PvSearchResult> list = pvSearchDao.findList(start, end, startTime, endTime, type);
			DataGrid<PvSearchResult> dg = new DataGrid<PvSearchResult>();
			dg.setTotal(pvSearchDao.getTotalRole(startTime, endTime, type));
			dg.setRows(list);

			ControllerUtil.writeJson(response,
					new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示搜索详情列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findDetailList")
	public void findDetailList(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");

			// 查询参数
			String startTime = request.getParameter("startTime");
			if (null != startTime && startTime.length() > 1) {
				startTime = startTime + " 00:00:00";
			}
			String endTime = request.getParameter("endTime");
			if (null != endTime && endTime.length() > 1) {
				endTime = endTime + " 23:59:59";
			}
			String serchName = request.getParameter("serchName");
			String userName = request.getParameter("userName");
			String searchResult = request.getParameter("searchResult");
			String type = request.getParameter("type");

			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 50 : Long.valueOf(rows);

			long start = (currentPage - 1) * pageSize;
			long end = pageSize;

			List<PvSearchInfo> list = pvSearchDao.findDetailList(start, end, startTime, endTime, serchName, 
					searchResult, userName, type);
			DataGrid<PvSearchInfo> dg = new DataGrid<PvSearchInfo>();
			dg.setTotal(pvSearchDao.getDetailTotalRole(startTime, endTime, serchName, searchResult, userName, type));
			dg.setRows(list);

			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出报表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportExcel")
	public void exportReport(HttpServletRequest request,
			HttpServletResponse response) {
		
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=report"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
						.toString() + ".xls");

		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String startTime = request.getParameter("startTime");
		if (null != startTime && startTime.length() > 1) {
			startTime = startTime + " 00:00:00";
		}
		String endTime = request.getParameter("endTime");
		if (null != endTime && endTime.length() > 1) {
			endTime = endTime + " 23:59:59";
		}
		
		String type = request.getParameter("type");
		List<PvSearchResult> pvSearchResults = pvSearchDao.findListAll(startTime, endTime, type);
		
		ExportExcel<PvSearchResult> ex = new ExportExcel<PvSearchResult>();
		String[] headers = { "搜索内容", "搜索次数" };
		List<PvSearchResult> dataset = new ArrayList<PvSearchResult>();

		try {
			for (PvSearchResult k : pvSearchResults) {
				PvSearchResult e = new PvSearchResult();
				e.setSerchName(k.getSerchName());
				e.setSerchCount(k.getSerchCount());

				dataset.add(e);
			}
			ex.exportExcel("用户搜索统计分析", headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 导出搜索详情页报表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/detailExportExcel")
	public void detailExportExcel(HttpServletRequest request, HttpServletResponse response) {
		
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=report"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
						.toString() + ".xls");

		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String serchName = request.getParameter("serchName");
		String userName = request.getParameter("userName");
		String searchResult = request.getParameter("searchResult");
		String type = request.getParameter("type");
		
		List<PvSearchInfo> list = pvSearchDao.findDetailListAll(startTime, endTime, serchName, 
				searchResult, userName, type);
		
		ExportExcel<PvSearchInfo> ex = new ExportExcel<PvSearchInfo>();
		String[] headers = { "搜索内容", "创建时间", "搜索类型", "搜索结果", "用户名称" };
		List<PvSearchInfo> dataset = new ArrayList<PvSearchInfo>();
		try {
			for (PvSearchInfo k : list) {
				PvSearchInfo e = new PvSearchInfo();
				e.setSerchName(StringUtils.parseString(k.getSerchName()));
				e.setCreate_date(DateUtils.parseDate(k.getCreate_date()));
				e.setType(StringUtils.parseString(k.getType()));
				e.setSearchResult(StringUtils.parseString(k.getSearchResult()));
				e.setUserName(StringUtils.parseString(k.getUserName()));

				dataset.add(e);
			}
			ex.exportExcel("搜索详情统计分析", headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
