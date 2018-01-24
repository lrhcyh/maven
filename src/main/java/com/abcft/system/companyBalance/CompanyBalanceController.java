package com.abcft.system.companyBalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.CookieUtils;
import com.abcft.system.company.ReadExcelUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/companyBalance")
public class CompanyBalanceController {
	
	@Autowired
	private CompanyBalanceDao companyBalanceDao;
	
	/**
	 * 跳转页面
	 */
	@RequestMapping("/gotoPage")
	public ModelAndView goToPage(HttpServletRequest req){
		try {
			String page = req.getParameter("page");
			if ("add".equals(page)) {
				return new ModelAndView("companyBalance/add");
			} else if ("update".equals(page)) {
				return new ModelAndView("companyBalance/update");
			} else if ("import".equals(page)){
				return new ModelAndView("companyBalance/import");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("findList")
	public void findList(HttpServletRequest req , HttpServletResponse rep){
		try{
			// 页数
			String page = req.getParameter("page");
			// 每页显示的条数
			String rows = req.getParameter("rows");
			// 查询参数
			String stockCode = req.getParameter("stockCode");
			String end_date_start = req.getParameter("end_date_start");
			String end_date_end = req.getParameter("end_date_end");
			String keyWord = req.getParameter("keyWord");
			
			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Integer.parseInt(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 10 : Integer.parseInt(rows);
			
			long start = (currentPage -1 ) * pageSize;
			long end = pageSize;
			
			List<CompanyBalanceInfo> list = companyBalanceDao.findList(start, end, stockCode, end_date_start, end_date_end, keyWord);
			
			DataGrid<CompanyBalanceInfo> dg = new DataGrid<CompanyBalanceInfo>();
			dg.setTotal(companyBalanceDao.getTotalRole(stockCode, end_date_start, end_date_end, keyWord));
			dg.setRows(list);
			
			ControllerUtil.writeJson(rep, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加负债资产信息
	 */
	@RequestMapping("/addCompanyBalance")
	public void addCompanyBalance(HttpServletRequest req, HttpServletResponse rep, @ModelAttribute() CompanyBalanceInfo companyBalanceInfo){
		try{
			// 如果能根据股票编号和公告日期在数据库中可以查到数据，则已存在数据，不添加！
			List<CompanyBalanceInfo> result = companyBalanceDao.findIsRepeat(companyBalanceInfo.getStock_code(), companyBalanceInfo.getPublish_date());
			if(result != null && result.size() > 0) {
				ControllerUtil.writeJson(rep, new Gson().toJson("存在相同或相似的数据！"));
			}else{
				companyBalanceDao.insertCompanyBalanceInfo(companyBalanceInfo);
				ControllerUtil.writeJson(rep, new Gson().toJson(companyBalanceInfo));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改负债资产信息
	 */
	@RequestMapping("/updateCompanyBalance")
	public void updateCompanyBalance(@ModelAttribute() CompanyBalanceInfo companyBalanceInfo, HttpServletRequest req, HttpServletResponse rep){
		try{
			companyBalanceDao.updateCompanyBalanceInfo(companyBalanceInfo);
			ControllerUtil.writeJson(rep, new Gson().toJson(companyBalanceInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一条记录
	 */
	@RequestMapping("/remove")
	public void removeCompanyBalance(HttpServletRequest req , HttpServletResponse rep) throws Exception{
		String result = "fail";
		try{
			String balance_sheet_id = req.getParameter("balance_sheet_id");
			companyBalanceDao.removeCompanyBalanceInfo(balance_sheet_id);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(rep, result);
	}
	
	/**
	 * 保存修改负债资产信息
	 */
	@RequestMapping("/saveCompanyBalance")
	public void saveCompanyBalance(CompanyBalanceInfo companyBalanceInfo, HttpServletRequest req, HttpServletResponse rep){
		try{
			companyBalanceDao.updateCompanyBalanceInfo(companyBalanceInfo);
			ControllerUtil.writeJson(rep, new Gson().toJson(companyBalanceInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导入资产负债信息的excel
	 */
	@RequestMapping("/importBalance")
	public void importCompanyBalance(HttpServletRequest req, HttpServletResponse rep, @RequestParam("file") MultipartFile file){
		// 判断文件是否为空
		if(!file.isEmpty()){
			try{
				String fileName = file.getOriginalFilename();
				String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toLowerCase();
				ReadExcelUtils excelUtil = new ReadExcelUtils(fileType, file.getInputStream());
				
				Map<Integer, Map<Integer, Object>> content = excelUtil.readExcelContentForMap();

				// 批量导入
				List<CompanyBalanceInfo> batchImportBalance = new ArrayList<>();
				for(int i=1; i<content.size(); i++){
					Map<Integer, Object> map = content.get(i);
					// 过滤掉为空的数据行
					if(StringUtils.isEmpty(String.valueOf(map.get(1)))) continue;
					CompanyBalanceInfo companyBalanceInfo = new CompanyBalanceInfo();
					
					companyBalanceInfo.setStock_code(String.valueOf(map.get(1)));
					companyBalanceInfo.setPublish_date(String.valueOf(map.get(2)));
					companyBalanceInfo.setEnd_date(String.valueOf(map.get(3)));
					companyBalanceInfo.setAccount_date(String.valueOf(map.get(4)));
					companyBalanceInfo.setReport_period(Integer.parseInt(String.valueOf(map.get(5))));
					companyBalanceInfo.setCash(Double.parseDouble(String.valueOf(map.get(6))));
					companyBalanceInfo.setTrading_fin_asset(Double.parseDouble(String.valueOf(map.get(7)))); 
					companyBalanceInfo.setRec_note(Double.parseDouble(String.valueOf(map.get(8))));
					companyBalanceInfo.setRec_account(Double.parseDouble(String.valueOf(map.get(9))));
					companyBalanceInfo.setPrepay(Double.parseDouble(String.valueOf(map.get(10))));
					companyBalanceInfo.setOther_rec_account(Double.parseDouble(String.valueOf(map.get(11))));
					companyBalanceInfo.setRec_relate_account(Double.parseDouble(String.valueOf(map.get(12))));
					companyBalanceInfo.setRec_interest(Double.parseDouble(String.valueOf(map.get(13))));
					companyBalanceInfo.setRec_dividend(Double.parseDouble(String.valueOf(map.get(14))));
					companyBalanceInfo.setStock(Double.parseDouble(String.valueOf(map.get(15))));
					companyBalanceInfo.setConsume_asset(Double.parseDouble(String.valueOf(map.get(16))));
					companyBalanceInfo.setNon_current_asset(Double.parseDouble(String.valueOf(map.get(17))));
					companyBalanceInfo.setOther_current_asset(Double.parseDouble(String.valueOf(map.get(18))));
					companyBalanceInfo.setTotal_current_asset(Double.parseDouble(String.valueOf(map.get(19))));
					companyBalanceInfo.setAvailable_sale_asset(Double.parseDouble(String.valueOf(map.get(20))));
					companyBalanceInfo.setHeld_investment(Double.parseDouble(String.valueOf(map.get(21))));
					companyBalanceInfo.setLong_rec_account(Double.parseDouble(String.valueOf(map.get(22))));
					companyBalanceInfo.setLong_equity_investment(Double.parseDouble(String.valueOf(map.get(23))));
					companyBalanceInfo.setInvest_house(Double.parseDouble(String.valueOf(map.get(24))));
					companyBalanceInfo.setFix_asset(Double.parseDouble(String.valueOf(map.get(25))));
					companyBalanceInfo.setBuilding(Double.parseDouble(String.valueOf(map.get(26))));
					companyBalanceInfo.setProject_material(Double.parseDouble(String.valueOf(map.get(27))));
					companyBalanceInfo.setFix_asset_dispose(Double.parseDouble(String.valueOf(map.get(28))));
					companyBalanceInfo.setProduct_asset(Double.parseDouble(String.valueOf(map.get(29))));
					companyBalanceInfo.setOil_asset(Double.parseDouble(String.valueOf(map.get(30))));
					companyBalanceInfo.setIntangible_asset(Double.parseDouble(String.valueOf(map.get(31))));
					companyBalanceInfo.setDevelop_cost(Double.parseDouble(String.valueOf(map.get(32))));
					companyBalanceInfo.setGoodwill(Double.parseDouble(String.valueOf(map.get(33))));
					companyBalanceInfo.setLong_defer_cost(Double.parseDouble(String.valueOf(map.get(34))));
					companyBalanceInfo.setTax_asset(Double.parseDouble(String.valueOf(map.get(35))));
					companyBalanceInfo.setOther_noncurrent_asset(Double.parseDouble(String.valueOf(map.get(36))));
					companyBalanceInfo.setNoncurrent_asset_total(Double.parseDouble(String.valueOf(map.get(37))));
					companyBalanceInfo.setAsset_total(Double.parseDouble(String.valueOf(map.get(38))));
					companyBalanceInfo.setShort_borrow(Double.parseDouble(String.valueOf(map.get(39))));
					companyBalanceInfo.setTrading_fin_borrow(Double.parseDouble(String.valueOf(map.get(40))));
					companyBalanceInfo.setPay_note(Double.parseDouble(String.valueOf(map.get(41))));
					companyBalanceInfo.setPay_account(Double.parseDouble(String.valueOf(map.get(42))));
					companyBalanceInfo.setPrepay_account(Double.parseDouble(String.valueOf(map.get(43))));
					companyBalanceInfo.setPay_salary(Double.parseDouble(String.valueOf(map.get(44))));
					companyBalanceInfo.setPay_tax(Double.parseDouble(String.valueOf(map.get(45))));
					companyBalanceInfo.setPay_interest(Double.parseDouble(String.valueOf(map.get(46))));
					companyBalanceInfo.setPay_dividend(Double.parseDouble(String.valueOf(map.get(47))));
					companyBalanceInfo.setOther_pay_account(Double.parseDouble(String.valueOf(map.get(48))));
					companyBalanceInfo.setPay_relate_account(Double.parseDouble(String.valueOf(map.get(49))));
					companyBalanceInfo.setNon_current_borrow(Double.parseDouble(String.valueOf(map.get(50))));
					companyBalanceInfo.setOther_current_borrow(Double.parseDouble(String.valueOf(map.get(51))));
					companyBalanceInfo.setCurrent_borrow_total(Double.parseDouble(String.valueOf(map.get(52))));
					companyBalanceInfo.setLong_borrow(Double.parseDouble(String.valueOf(map.get(53))));
					companyBalanceInfo.setPay_bonds(Double.parseDouble(String.valueOf(map.get(54))));
					companyBalanceInfo.setLong_pay_account(Double.parseDouble(String.valueOf(map.get(55))));
					companyBalanceInfo.setTerm_pay_account(Double.parseDouble(String.valueOf(map.get(56))));
					companyBalanceInfo.setPre_bonds(Double.parseDouble(String.valueOf(map.get(57))));
					companyBalanceInfo.setTax_bonds(Double.parseDouble(String.valueOf(map.get(58))));
					companyBalanceInfo.setOther_noncurrent_bonds(Double.parseDouble(String.valueOf(map.get(59))));
					companyBalanceInfo.setNoncurrent_bonds_total(Double.parseDouble(String.valueOf(map.get(60))));
					companyBalanceInfo.setBonds_total(Double.parseDouble(String.valueOf(map.get(61))));
					companyBalanceInfo.setRec_capital(Double.parseDouble(String.valueOf(map.get(62))));
					companyBalanceInfo.setCapital_reserve(Double.parseDouble(String.valueOf(map.get(63))));
					companyBalanceInfo.setEarn_reserve(Double.parseDouble(String.valueOf(map.get(64))));
					companyBalanceInfo.setReduce_share(Double.parseDouble(String.valueOf(map.get(65))));
					companyBalanceInfo.setNopay_profit(Double.parseDouble(String.valueOf(map.get(66))));
					companyBalanceInfo.setMonority_holder_equity(Double.parseDouble(String.valueOf(map.get(67))));
					companyBalanceInfo.setExchange_difference(Double.parseDouble(String.valueOf(map.get(68))));
					companyBalanceInfo.setProfit_adjust(Double.parseDouble(String.valueOf(map.get(69))));
					companyBalanceInfo.setEquity_total(Double.parseDouble(String.valueOf(map.get(70))));
					companyBalanceInfo.setAll_total(Double.parseDouble(String.valueOf(map.get(71))));
					companyBalanceInfo.setParent_equity(Double.parseDouble(String.valueOf(map.get(72))));
					companyBalanceInfo.setTotal_depre_amor(Double.parseDouble(String.valueOf(map.get(73))));
					companyBalanceInfo.setPush_flag(Integer.parseInt(String.valueOf(map.get(74))));
					
					batchImportBalance.add(companyBalanceInfo);
					
				}
				
				UUID uuid = UUID.randomUUID();
            	req.getSession().setAttribute(uuid.toString(), batchImportBalance);
            	
            	Cookie cookie = new Cookie("UUID", uuid.toString());
            	cookie.setPath("/");
            	cookie.setMaxAge(1800);
            	rep.addCookie(cookie);
				
				DataGrid<CompanyBalanceInfo> dg = new DataGrid<CompanyBalanceInfo>();
				dg.setTotal((long) batchImportBalance.size());
				dg.setRows(batchImportBalance);
				
				ControllerUtil.writeJson(rep, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/commitBalance")
	public void commitCompanyBalance(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		String result = "fail";
		try {
			Cookie cookie = CookieUtils.getCookie(req, rep, "UUID");
			String UUID = cookie.getValue();
			
			final List<CompanyBalanceInfo> list = (List<CompanyBalanceInfo>) req.getSession().getAttribute(UUID);
			
			if(list != null && list.size() > 0){
				// 批量修改
				List<CompanyBalanceInfo> balaceUpdate = new ArrayList<>();
				// 批量插入
				List<CompanyBalanceInfo> balaceInsert = new ArrayList<>();
				
				for(CompanyBalanceInfo companyBI : list){
					// 如果能根据股票编号和公告日期在数据库中可以查到数据，则已存在数据，不添加！
					List<CompanyBalanceInfo> results = companyBalanceDao.findIsRepeat(companyBI.getStock_code(), companyBI.getPublish_date());
					if(results != null && results.size() > 0){
						companyBI.setBalance_sheet_id(results.get(0).getBalance_sheet_id());
						balaceUpdate.add(companyBI);
					}else{
						balaceInsert.add(companyBI);
					}
				}
				// 执行批量添加
				companyBalanceDao.balanceInsert(balaceInsert);
				// 执行批量修改
				companyBalanceDao.balanceUpdate(balaceUpdate);
				
				// 清除缓存中的数据
				req.getSession().removeAttribute("UUID");
				result = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ControllerUtil.toJson(rep, result);
	}
	
}
