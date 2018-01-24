package com.abcft.system.companyCash;

import java.io.IOException;
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
import com.abcft.system.companyBalance.CompanyBalanceInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/companyCash")
public class CompanyCashController {
	
	@Autowired
	private CompanyCashDao companyCashDao;
	
	/**
	 * 跳转页面
	 */
	@RequestMapping("/gotoPage")
	public ModelAndView goToPage(HttpServletRequest req){
		try {
			String page = req.getParameter("page");
			if ("add".equals(page)) {
				return new ModelAndView("companyCash/add");
			} else if ("update".equals(page)) {
				return new ModelAndView("companyCash/update");
			} else if ("import".equals(page)){
				return new ModelAndView("companyCash/import");
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
			
			// 每次查询20条数据进行展示
			List<CompanyCashInfo> list = companyCashDao.findCompanyCashList(start, end, stockCode, end_date_start, end_date_end, keyWord);
			
			DataGrid<CompanyCashInfo> dg = new DataGrid<CompanyCashInfo>();
			// 统计数量
			dg.setTotal(companyCashDao.getTotalRole(stockCode, end_date_start, end_date_end, keyWord));
			dg.setRows(list);
			
			ControllerUtil.writeJson(rep, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加现金流量信息
	 */
	@RequestMapping("/addCompanyCash")
	public void addCompanyCashInfo(@ModelAttribute() CompanyCashInfo companyCashInfo, HttpServletRequest req, HttpServletResponse rep){
		try{
			// 根据股票代码和公告日期判断是否存在重复数据
			List<CompanyCashInfo> isRepeat = companyCashDao.findIsRepeat(companyCashInfo.getStock_code(), companyCashInfo.getPublish_date());
			if(isRepeat != null && isRepeat.size() > 0){
				ControllerUtil.writeJson(rep, new Gson().toJson("存在相同或相似的数据"));
			}else{
				companyCashDao.insertCompanyCashInfo(companyCashInfo);
				ControllerUtil.writeJson(rep, new Gson().toJson(companyCashInfo));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新现金流量信息
	 */
	@RequestMapping("/updateCompanyCash")
	public void updateCompanyCashInfo(@ModelAttribute() CompanyCashInfo companyCashInfo, HttpServletRequest req, HttpServletResponse rep){
		try{
			companyCashDao.updateCompanyCash(companyCashInfo);
			ControllerUtil.writeJson(rep, new Gson().toJson(companyCashInfo));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据cash_sheet_id删除一条数据
	 */
	@RequestMapping("/remove")
	public void removeCompanyCashInfo(HttpServletRequest req , HttpServletResponse rep) throws Exception{
		String result = "fail";
		try{
			String cash_sheet_id = req.getParameter("cash_sheet_id");
			companyCashDao.removeCompanyCashInfo(cash_sheet_id);
			result = "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		ControllerUtil.toJson(rep, result);
	}
	
	/**
	 * 保存现金流量信息
	 */
	@RequestMapping("/saveCompanyCash")
	public void saveCompanyCashInfo(HttpServletRequest req, HttpServletResponse rep, CompanyCashInfo companyCashInfo){
		try{
			companyCashDao.updateCompanyCash(companyCashInfo);
			ControllerUtil.writeJson(rep, new Gson().toJson(companyCashInfo));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用excel导入现金流量信息
	 */
	@RequestMapping("/importCash")
	public void importCompanyCashInfo(HttpServletRequest req, HttpServletResponse rep, @RequestParam MultipartFile file){
		// 判断文件是否为空
		if(!file.isEmpty()){
			try {
				String fileName = file.getOriginalFilename();
				String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toLowerCase();
				ReadExcelUtils excelUtil = new ReadExcelUtils(fileType, file.getInputStream());
				
				Map<Integer, Map<Integer, Object>> content = excelUtil.readExcelContentForMap();
				
				// 批量导入现金流量信息
				List<CompanyCashInfo> batchImport = new ArrayList<>();
				
				for(int i=1; i<=content.size(); i++){
					Map<Integer, Object> map = content.get(i);
					// 过滤掉数据为空的行
					if(StringUtils.isEmpty(String.valueOf(map.get(1)))) continue;
					
					CompanyCashInfo companyCashInfo = new CompanyCashInfo();
					companyCashInfo.setStock_code(String.valueOf(map.get(1)));
					companyCashInfo.setPublish_date(String.valueOf(map.get(2)));
					companyCashInfo.setEnd_date(String.valueOf(map.get(3)));
					companyCashInfo.setAccount_date(String.valueOf(map.get(4)));
					companyCashInfo.setReport_period(Integer.parseInt(String.valueOf(map.get(5))));
					companyCashInfo.setSale_cash(Double.parseDouble(String.valueOf(map.get(6))));
					companyCashInfo.setTax_return(Double.parseDouble(String.valueOf(map.get(7))));
					companyCashInfo.setRec_other_cash(Double.parseDouble(String.valueOf(map.get(8))));
					companyCashInfo.setBussiness_cash_total(Double.parseDouble(String.valueOf(map.get(9))));
					companyCashInfo.setBuy_for_cash(Double.parseDouble(String.valueOf(map.get(10))));
					companyCashInfo.setPay_emp_cash(Double.parseDouble(String.valueOf(map.get(11))));
					companyCashInfo.setPay_tax(Double.parseDouble(String.valueOf(map.get(12))));
					companyCashInfo.setPay_other_cash(Double.parseDouble(String.valueOf(map.get(13))));
					companyCashInfo.setBussiness_cash_output(Double.parseDouble(String.valueOf(map.get(14))));
					companyCashInfo.setBussiness_cash_netvalue(Double.parseDouble(String.valueOf(map.get(15))));
					companyCashInfo.setRec_invest_cash(Double.parseDouble(String.valueOf(map.get(16))));
					companyCashInfo.setInvest_rec_cash(Double.parseDouble(String.valueOf(map.get(17))));
					companyCashInfo.setDispose_asset_netvalue(Double.parseDouble(String.valueOf(map.get(18))));
					companyCashInfo.setDispose_other_netvalue(Double.parseDouble(String.valueOf(map.get(19))));
					companyCashInfo.setRec_otherinvest_cash(Double.parseDouble(String.valueOf(map.get(20))));
					companyCashInfo.setInvest_cash_total(Double.parseDouble(String.valueOf(map.get(21))));
					companyCashInfo.setBuy_asset_cash(Double.parseDouble(String.valueOf(map.get(22))));
					companyCashInfo.setInvest_pay_cash(Double.parseDouble(String.valueOf(map.get(23))));
					companyCashInfo.setLoan_net_addvalue(Double.parseDouble(String.valueOf(map.get(24))));
					companyCashInfo.setRec_othercompany_cash(Double.parseDouble(String.valueOf(map.get(25))));
					companyCashInfo.setPay_otherinvest_cash(Double.valueOf(String.valueOf(map.get(26))));
					companyCashInfo.setInvest_cash_output(Double.parseDouble(String.valueOf(map.get(27))));
					companyCashInfo.setInvest_cash_netvalue(Double.parseDouble(String.valueOf(map.get(28))));
					companyCashInfo.setRec_invest_reccash(Double.parseDouble(String.valueOf(map.get(29))));
					companyCashInfo.setRec_borrow_cash(Double.parseDouble(String.valueOf(map.get(30))));
					companyCashInfo.setPublish_rec_cash(Double.parseDouble(String.valueOf(map.get(31))));
					companyCashInfo.setRec_other_relatecash(Double.parseDouble(String.valueOf(map.get(32))));
					companyCashInfo.setBorrow_cash_total(Double.parseDouble(String.valueOf(map.get(33))));
					companyCashInfo.setPay_debet_cash(Double.parseDouble(String.valueOf(map.get(34))));
					companyCashInfo.setInterest_pay_cash(Double.parseDouble(String.valueOf(map.get(35))));
					companyCashInfo.setPay_other_relatecash(Double.parseDouble(String.valueOf(map.get(36))));
					companyCashInfo.setBorrow_cash_outtotal(Double.parseDouble(String.valueOf(map.get(37))));
					companyCashInfo.setBorrow_cash_netvalue(Double.parseDouble(String.valueOf(map.get(38))));
					companyCashInfo.setRate_to_cash(Double.parseDouble(String.valueOf(map.get(39))));
					companyCashInfo.setOther_to_cash(Double.parseDouble(String.valueOf(map.get(40))));
					companyCashInfo.setCash_to_netadd(Double.parseDouble(String.valueOf(map.get(41))));
					companyCashInfo.setOrigin_cash(Double.parseDouble(String.valueOf(map.get(42))));
					companyCashInfo.setLast_cash(Double.parseDouble(String.valueOf(map.get(43))));
					companyCashInfo.setAddition(Double.parseDouble(String.valueOf(map.get(44))));
					companyCashInfo.setNetvalue_to_cash(Double.parseDouble(String.valueOf(map.get(45))));
					companyCashInfo.setNetvalue(Double.parseDouble(String.valueOf(map.get(46))));
					companyCashInfo.setPlus_asset_loss(Double.parseDouble(String.valueOf(map.get(47))));
					companyCashInfo.setAsset_discount(Double.parseDouble(String.valueOf(map.get(48))));
					companyCashInfo.setIntangible_asset_discount(Double.parseDouble(String.valueOf(map.get(49))));
					companyCashInfo.setLong_cost_discount(Double.parseDouble(String.valueOf(map.get(50))));
					companyCashInfo.setAsset_loss(Double.parseDouble(String.valueOf(map.get(51))));
					companyCashInfo.setFix_asset_loss(Double.parseDouble(String.valueOf(map.get(52))));
					companyCashInfo.setValue_change_loss(Double.parseDouble(String.valueOf(map.get(53))));
					companyCashInfo.setFin_cost(Double.parseDouble(String.valueOf(map.get(54))));
					companyCashInfo.setInvest_loss(Double.parseDouble(String.valueOf(map.get(55))));
					companyCashInfo.setTax_reduce(Double.parseDouble(String.valueOf(map.get(56))));
					companyCashInfo.setDebt_reduce(Double.parseDouble(String.valueOf(map.get(57))));
					companyCashInfo.setStock_reduce(Double.parseDouble(String.valueOf(map.get(58))));
					companyCashInfo.setRec_project_reduce(Double.parseDouble(String.valueOf(59)));
					companyCashInfo.setPay_project_add(Double.parseDouble(String.valueOf(map.get(60))));
					companyCashInfo.setOther(Double.parseDouble(String.valueOf(map.get(61))));;
					companyCashInfo.setBusiness_cash_netvalue(Double.parseDouble(String.valueOf(map.get(62))));
					companyCashInfo.setNon_cash_netvalue(Double.parseDouble(String.valueOf(map.get(63))));
					companyCashInfo.setDebt_to_capital(Double.parseDouble(String.valueOf(map.get(64))));
					companyCashInfo.setDebt_one_year(Double.parseDouble(String.valueOf(map.get(65))));
					companyCashInfo.setCash_to_asset(Double.parseDouble(String.valueOf(map.get(66))));
					companyCashInfo.setCash_change(Double.parseDouble(String.valueOf(map.get(67))));
					companyCashInfo.setLast_cash_value(Double.parseDouble(String.valueOf(map.get(68))));
					companyCashInfo.setReduce_origin_cash(Double.parseDouble(String.valueOf(map.get(69))));
					companyCashInfo.setPlus_last_cash(Double.parseDouble(String.valueOf(map.get(70))));
					companyCashInfo.setReduce_origin_value(Double.parseDouble(String.valueOf(map.get(71))));
					companyCashInfo.setPlus_other_cash(Double.parseDouble(String.valueOf(map.get(72))));
					companyCashInfo.setCash_to_netvalue(Double.parseDouble(String.valueOf(map.get(73))));
					companyCashInfo.setCustom_to_netvalue(Double.parseDouble(String.valueOf(map.get(74))));
					companyCashInfo.setBorrow_netvalue(Double.parseDouble(String.valueOf(map.get(75))));
					companyCashInfo.setBorrow_other_netvalue(Double.parseDouble(String.valueOf(map.get(76))));
					companyCashInfo.setRec_insurance_cash(Double.parseDouble(String.valueOf(map.get(77))));
					companyCashInfo.setRec_insurance_netvalue(Double.parseDouble(String.valueOf(map.get(78))));
					companyCashInfo.setInvest_netvalue(Double.parseDouble(String.valueOf(map.get(79))));
					companyCashInfo.setDispose_the_cash(Double.parseDouble(String.valueOf(map.get(80))));
					companyCashInfo.setRec_interest_cash(Double.parseDouble(String.valueOf(map.get(81))));
					companyCashInfo.setCash_netvalue(Double.parseDouble(String.valueOf(map.get(82))));
					companyCashInfo.setReturn_cash_netvalue(Double.parseDouble(String.valueOf(map.get(83))));
					companyCashInfo.setCustom_netvalue(Double.parseDouble(String.valueOf(map.get(84))));
					companyCashInfo.setBank_cash_netvalue(Double.parseDouble(String.valueOf(map.get(85))));
					companyCashInfo.setPay_insurance_cash(Double.parseDouble(String.valueOf(map.get(86))));
					companyCashInfo.setPay_interest_cash(Double.parseDouble(String.valueOf(map.get(87))));
					companyCashInfo.setPay_profit_cash(Double.parseDouble(String.valueOf(map.get(88))));
					companyCashInfo.setCash_for_holder(Double.parseDouble(String.valueOf(map.get(89))));
					companyCashInfo.setProfit_for_holder(Double.parseDouble(String.valueOf(map.get(90))));
					companyCashInfo.setHouse_disount(Double.parseDouble(String.valueOf(map.get(91))));
					companyCashInfo.setPush_flag(Integer.parseInt(String.valueOf(map.get(92))));
					
					batchImport.add(companyCashInfo);
				}
				
				UUID uuid = UUID.randomUUID();
            	req.getSession().setAttribute(uuid.toString(), batchImport);
            	
            	Cookie cookie = new Cookie("UUID", uuid.toString());
            	cookie.setPath("/");
            	cookie.setMaxAge(1800);
            	rep.addCookie(cookie);
				
				DataGrid<CompanyCashInfo> dg = new DataGrid<CompanyCashInfo>();
				dg.setTotal((long) batchImport.size());
				dg.setRows(batchImport);
				
				ControllerUtil.writeJson(rep, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 审批提交现金流量信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/commitCash")
	public void commitCompanyCashInfo(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		String result = "fail";
		try{
			Cookie cookie = CookieUtils.getCookie(req, rep, "UUID");
			String UUID = cookie.getValue();
			
			final List<CompanyCashInfo> list = (List<CompanyCashInfo>) req.getSession().getAttribute(UUID);
			
			if(list != null && list.size() > 0){
				// 批量修改
				List<CompanyCashInfo> cashInfoUpdate = new ArrayList<>();
				// 批量插入
				List<CompanyCashInfo> cashInfoInsert = new ArrayList<>();
				for(CompanyCashInfo companyCashInfo : list){
					// 根据股票代码和公告日期判断是否存在重复数据
					List<CompanyCashInfo> isRepeat = companyCashDao.findIsRepeat(companyCashInfo.getStock_code(), companyCashInfo.getPublish_date());
					if(isRepeat != null && isRepeat.size() > 0){
						companyCashInfo.setCash_sheet_id(isRepeat.get(0).getCash_sheet_id());
						cashInfoUpdate.add(companyCashInfo);
					}else{
						cashInfoInsert.add(companyCashInfo);
					}
				}
				// 执行批量添加
				companyCashDao.balanceInsert(cashInfoInsert);
				// 执行批量修改
				companyCashDao.balanceUpdate(cashInfoUpdate);
				
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
