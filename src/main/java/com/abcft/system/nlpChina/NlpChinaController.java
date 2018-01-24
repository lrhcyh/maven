package com.abcft.system.nlpChina;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.core.BaseController;
import com.abcft.common.core.UserSession;
import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.pagemodel.TreeGrid;
import com.abcft.common.pagemodel.TreeGridNode;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.StringUtils;
import com.abcft.system.privilege.Functions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/nlpCountry")
public class NlpChinaController extends BaseController {
	
	@Autowired
	private NlpChinaDao nlpChinaDao;
	
	private static Logger logger = Logger.getLogger(NlpChinaController.class);
	
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
			if ("add".equals(page)) {
				return new ModelAndView("nlpCountry/add");
			} else if ("edit".equals(page)) {
				return new ModelAndView("nlpCountry/edit");
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
	 * 显示同义词列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findList")
	public void findList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");

			// 查询参数
			String name = request.getParameter("name");
			

			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 10 : Long.valueOf(rows);

			long start = (currentPage - 1) * pageSize;
			long end = pageSize;

			List<NlpCountryInfo> list = nlpChinaDao.findList(start, end, name);
			DataGrid<NlpCountryInfo> dg = new DataGrid<NlpCountryInfo>();
			dg.setTotal(nlpChinaDao.getTotalRole(name));
			dg.setRows(list);

			ControllerUtil.writeJson(response,
					new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除同义词
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/remove")
	public void removeNlpSynonym(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String result = "fail";
		try {
			nlpChinaDao.remove(id);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	/**
	 * 编辑同义词信息
	 * @param nlpSynonymInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/editNlpCountry")
	public void editNlpCountry(@ModelAttribute() NlpCountryInfo nlpSynonymInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.nlpChinaDao.updateNlpSynonym(nlpSynonymInfo);
			ControllerUtil.writeJson(response, new Gson().toJson(nlpSynonymInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addNlpCountry")
	public void addNlpCountry(@ModelAttribute() NlpCountryInfo nlpSynonymInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.nlpChinaDao.insertNlpSynonym(nlpSynonymInfo);
			ControllerUtil.writeJson(response, new Gson().toJson(nlpSynonymInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
