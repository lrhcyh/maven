package com.abcft.system.companyProfit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.core.BaseController;
import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.CookieUtils;
import com.abcft.common.util.NumberUtil;
import com.abcft.common.util.StringUtils;
import com.abcft.system.company.ReadExcelUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/companyProfit")
public class CompanyProfitController extends BaseController {

	@Autowired
	private CompanyProfitDao companyProfitDao;

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
				return new ModelAndView("companyProfit/add");
			} else if ("edit".equals(page)) {
				return new ModelAndView("companyProfit/edit");
			} else if ("import".equals(page)) {
				return new ModelAndView("companyProfit/import");
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
	 * 显示公司利润列表
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
			String stockCode = request.getParameter("stockCode");
			String keyword = request.getParameter("keyword");

			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 10 : Long.valueOf(rows);

			long start = (currentPage - 1) * pageSize;
			long end = pageSize * currentPage;

			List<CompanyProfitInfo> list = companyProfitDao.findList(start, end, stockCode, keyword);
			DataGrid<CompanyProfitInfo> dg = new DataGrid<CompanyProfitInfo>();
			dg.setTotal(companyProfitDao.getTotalRole(stockCode, keyword));
			dg.setRows(list);

			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除公司利润信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/remove")
	public void removeCompanyProfit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String profit_sheet_id = request.getParameter("profit_sheet_id");
		String result = "fail";
		try {
			
			companyProfitDao.remove(profit_sheet_id);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	@RequestMapping("/editCompanyProfit")
	public void editCompanyProfit(@ModelAttribute() CompanyProfitInfo companyProfitInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.companyProfitDao.updateCompanyProfit(companyProfitInfo);
			ControllerUtil.writeJson(response, new Gson().toJson(companyProfitInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addCompanyProfit")
	public void addCompanyProfit(@ModelAttribute() CompanyProfitInfo companyProfitInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.companyProfitDao.insertCompanyProfit(companyProfitInfo);
			ControllerUtil.writeJson(response, new Gson().toJson(companyProfitInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量导入公司利润信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/importExport")
	public void importExport(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	String fileName = file.getOriginalFilename();
            	String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            	
            	ReadExcelUtils excelUtils = new ReadExcelUtils(fileType, file.getInputStream());
            	
            	Map<Integer, Map<Integer, Object>> content = excelUtils.readExcelContentForMap();
            	List<CompanyProfitInfo> companyProfitInfos = new ArrayList<>();
            	
            	for (int i = 1; i <= content.size(); i++) {
            		Map<Integer, Object> maps = content.get(i);
    				CompanyProfitInfo profit = new CompanyProfitInfo();
    				profit.setStock_code(String.valueOf(StringUtils.parseValue(maps.get(1))));
    				profit.setPublish_date(String.valueOf(StringUtils.parseValue(maps.get(2))));
    				profit.setEnd_date(String.valueOf(StringUtils.parseValue(maps.get(3))));
    				profit.setAccount_date(String.valueOf(StringUtils.parseValue(maps.get(4))));
    				profit.setReport_period(Long.valueOf((String) StringUtils.parseValue(maps.get(5))));
    				profit.setOverall_income(NumberUtil.parseValue(maps.get(6)));
    				profit.setMain_income(NumberUtil.parseValue(maps.get(7)));
    				profit.setOverall_cost(NumberUtil.parseValue(maps.get(8)));
    				profit.setMain_cost(NumberUtil.parseValue(maps.get(9)));
    				profit.setTax(NumberUtil.parseValue(maps.get(10)));
    				profit.setSale_cost(NumberUtil.parseValue(maps.get(11)));
    				profit.setManage_cost(NumberUtil.parseValue(maps.get(12)));
    				profit.setOther_cost(NumberUtil.parseValue(maps.get(13)));
    				profit.setFin_cost(NumberUtil.parseValue(maps.get(14)));
    				profit.setAsset_loss(NumberUtil.parseValue(maps.get(15)));
    				profit.setPlus_change_income(NumberUtil.parseValue(maps.get(16)));
    				profit.setInvest_income(NumberUtil.parseValue(maps.get(17)));
    				profit.setRelate_invest_income(NumberUtil.parseValue(maps.get(18)));
    				profit.setGain_loss_income(NumberUtil.parseValue(maps.get(19)));
    				profit.setOther_subject(NumberUtil.parseValue(maps.get(20)));
    				profit.setOverall_profit(NumberUtil.parseValue(maps.get(21)));
    				profit.setPlus_subsidy_income(NumberUtil.parseValue(maps.get(22)));
    				profit.setAddition_income(NumberUtil.parseValue(maps.get(23)));
    				profit.setReduce_addition_cost(NumberUtil.parseValue(maps.get(24)));
    				profit.setAsset_dispose_loss(NumberUtil.parseValue(maps.get(25)));
    				profit.setPlus_profit_subject(NumberUtil.parseValue(maps.get(26)));
    				profit.setProfit_total(NumberUtil.parseValue(maps.get(27)));
    				profit.setReduce_tax(NumberUtil.parseValue(maps.get(28)));
    				profit.setPlus_netprofit_subject(NumberUtil.parseValue(maps.get(29)));
    				profit.setNetprofit(NumberUtil.parseValue(maps.get(30)));
    				profit.setParent_netprofit(NumberUtil.parseValue(maps.get(31)));
    				profit.setMinority_loss(NumberUtil.parseValue(maps.get(32)));
    				profit.setPerstock_income(NumberUtil.parseValue(maps.get(33)));
    				profit.setBasic_perstock_income(NumberUtil.parseValue(maps.get(34)));
    				profit.setReduce_perstock_income(NumberUtil.parseValue(maps.get(35)));
    				profit.setOther_all_income(NumberUtil.parseValue(maps.get(36)));
    				profit.setAll_income_total(NumberUtil.parseValue(maps.get(37)));
    				profit.setIncome_for_parent(NumberUtil.parseValue(maps.get(38)));
    				profit.setIncome_for_holder(NumberUtil.parseValue(maps.get(39)));
    				profit.setInterest_income(NumberUtil.parseValue(maps.get(40)));
    				profit.setEarn_insurance(NumberUtil.parseValue(maps.get(41)));
    				profit.setCommission_income(NumberUtil.parseValue(maps.get(42)));
    				profit.setInterest_cost(NumberUtil.parseValue(maps.get(43)));
    				profit.setCommission_cost(NumberUtil.parseValue(maps.get(44)));
    				profit.setCanel_insurance_money(NumberUtil.parseValue(maps.get(45)));
    				profit.setPay_netprofit(NumberUtil.parseValue(maps.get(46)));
    				profit.setInsurance_netprofit(NumberUtil.parseValue(maps.get(47)));
    				profit.setInsurance_cost(NumberUtil.parseValue(maps.get(48)));
    				profit.setReduce_insurance_cost(NumberUtil.parseValue(maps.get(49)));
    				profit.setInterest_dispose(NumberUtil.parseValue(maps.get(50)));
    				profit.setPush_flag(Integer.valueOf((String) StringUtils.parseValue(maps.get(51))));
    				
    				List<CompanyProfitInfo> result = this.companyProfitDao.findList(0, 100, profit.getStock_code(), null);
    				if (result != null && result.size() > 0) {
    					System.out.println(result.get(0).getProfit_sheet_id());
    					profit.setProfit_sheet_id(result.get(0).getProfit_sheet_id());
    				}
    				companyProfitInfos.add(profit);
    			}
            	
            	UUID uuid = UUID.randomUUID();
            	request.getSession().setAttribute(uuid.toString(), companyProfitInfos);
            	
            	Cookie cookie = new Cookie("UUID", uuid.toString());
            	cookie.setPath("/");
            	cookie.setMaxAge(3600);
            	response.addCookie(cookie);
            	
            	DataGrid<CompanyProfitInfo> dg = new DataGrid<CompanyProfitInfo>();
    			dg.setTotal((long) companyProfitInfos.size());
    			dg.setRows(companyProfitInfos);

    			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/importCompanyProfit")
	public void importCompanyProfit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "fail";
		try {
			Cookie cookie = CookieUtils.getCookie(request, response, "UUID");
			if (cookie == null) {
				throw new Exception("Please import excel first.");
			}
			String UUID = cookie.getValue();
			final List<CompanyProfitInfo> companyProfits = (List<CompanyProfitInfo>) request.getSession().getAttribute(UUID);
        	List<CompanyProfitInfo> companyProfitUpdate = new ArrayList<>();
        	List<CompanyProfitInfo> companyProfitInsert = new ArrayList<>();
			
			if (companyProfits != null && companyProfits.size() > 0) {
				for (CompanyProfitInfo m : companyProfits) {
					System.out.println(m);
					if (m.getProfit_sheet_id() == null) {
						companyProfitInsert.add(m);
					} else {
						companyProfitUpdate.add(m);
					}
				}
				
				companyProfitDao.batchInsertCompanyProfit(companyProfitInsert);
				companyProfitDao.batchUpdateCompanyProfit(companyProfitUpdate);
				
				result = "success";
			}
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		// 清除缓存中的数据
		request.getSession().removeAttribute("UUID");
		
		ControllerUtil.toJson(response, result);
	}
	
	@RequestMapping("/saveCompanyProfit")
	public void saveCompanyProfit(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute() CompanyProfitInfo companyProfitInfo) throws Exception {
		String result = "fail";
		try {
			companyProfitDao.updateCompanyProfit(companyProfitInfo);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		
		ControllerUtil.toJson(response, result);
	}
}
