package com.abcft.system.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.abcft.common.util.DateUtils;
import com.abcft.common.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 用户管理控制器
 * 
 * @author 黄茜
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyDao companyDao;
	
	

	/**
	 * 显示公司列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findList")
	public void findEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			//查询参数
			String stockCode = request.getParameter("stockCode");
			String stockName = request.getParameter("stockName");
			String keyword = request.getParameter("keyword");
			
			
			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 10 : Long.valueOf(rows);
			
			long start= (currentPage-1)*pageSize;
			long end= pageSize;
			
			List<CompanyBasicinfo> list = companyDao.findCompanyList(start, end, stockCode, stockName, keyword);
			DataGrid<CompanyBasicinfo> dg = new DataGrid<CompanyBasicinfo>();
			dg.setTotal(companyDao.getTotalRole(stockCode, stockName, keyword));
			dg.setRows(list);
			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
				return new ModelAndView("company/add");
			} else if ("edit".equals(page)) {
				return new ModelAndView("company/edit");
			} else if ("import".equals(page)) {
				return new ModelAndView("company/import");
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
	 * 删除公司
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/remove")
	public void removeCompany(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String result = "fail";
		try {
			
			companyDao.remove(id);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	/**
	 * 添加公司
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addCompany")
	public void addCompany(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute() CompanyBasicinfo companyInfo) throws Exception {
		try {
			Long id = companyDao.insertCompanyBasicinfo(companyInfo);
			companyInfo.setId(String.valueOf(id));
			ControllerUtil.writeJson(response, new Gson().toJson(companyInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改公司
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/editCompany")
	public void editCompany(HttpServletRequest request, HttpServletResponse response, @ModelAttribute() CompanyBasicinfo companyInfo) throws Exception {
		try {
			companyDao.updateCompanyBasicinfo(companyInfo);
			ControllerUtil.writeJson(response, new Gson().toJson(companyInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量导入公司信息
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
            	List<CompanyBasicinfo> companyBasicinfos = new ArrayList<>();
            	
            	for (int i = 1; i <= content.size(); i++) {
            		Map<Integer, Object> companyMaps = content.get(i);
    				CompanyBasicinfo companyInfo = new CompanyBasicinfo();
    				companyInfo.setStock_code(String.valueOf(companyMaps.get(1)));
    				companyInfo.setStock_category("");
    				companyInfo.setStock_name(String.valueOf(companyMaps.get(2)));
    				companyInfo.setStock_alias(String.valueOf(companyMaps.get(3)));
    				companyInfo.setCompany_name(String.valueOf(companyMaps.get(4)));
    				companyInfo.setCompany_ename(String.valueOf(companyMaps.get(5)));
    				companyInfo.setIndustry_name_csrc(String.valueOf(companyMaps.get(6)));
    				companyInfo.setIndustry_code_csrc(String.valueOf(companyMaps.get(7)));
    				companyInfo.setIndustry_code(String.valueOf(companyMaps.get(8)));
    				companyInfo.setIndustry_name(String.valueOf(companyMaps.get(9)));
    				companyInfo.setFound_date(String.valueOf(companyMaps.get(10)));
    				companyInfo.setRegister_capital(Long.valueOf(String.valueOf(companyMaps.get(11))));
    				companyInfo.setLegal_man(String.valueOf(companyMaps.get(12)));
    				companyInfo.setStock_type(String.valueOf(companyMaps.get(13)));
    				Object obj = companyMaps.get(14);
    				if (obj instanceof String) {
    					companyInfo.setIpo_date(DateUtils.getDateForString(String.valueOf(obj)));
    				} else if (obj instanceof Date) {
    					companyInfo.setIpo_date(new SimpleDateFormat("yyyy-MM-dd").format(obj));
    				}
    				companyInfo.setIpo_main_saler(String.valueOf(companyMaps.get(15)));
    				companyInfo.setConcept(String.valueOf(companyMaps.get(16)));
    				companyInfo.setProvince(String.valueOf(companyMaps.get(17)));
    				companyInfo.setCity(String.valueOf(companyMaps.get(18)));
    				companyInfo.setCompany_character(String.valueOf(companyMaps.get(19)));
    				companyInfo.setDirectors(String.valueOf(companyMaps.get(20)));
    				companyInfo.setEmp_sum(Long.valueOf(String.valueOf(companyMaps.get(21))));
    				companyInfo.setRegister_number(String.valueOf(companyMaps.get(22)));
    				companyInfo.setRegister_address(String.valueOf(companyMaps.get(23)));
    				companyInfo.setOffice_address(String.valueOf(companyMaps.get(24)));
    				companyInfo.setMajor_product_type(String.valueOf(companyMaps.get(25)));
    				companyInfo.setMajor_product_name(String.valueOf(companyMaps.get(26)));
    				companyInfo.setPhone(String.valueOf(companyMaps.get(27)));
    				companyInfo.setZipcode(String.valueOf(companyMaps.get(28)));
    				companyInfo.setFax(String.valueOf(companyMaps.get(29)));
    				companyInfo.setWebsite(String.valueOf(companyMaps.get(30)));
    				companyInfo.setEmail(String.valueOf(companyMaps.get(31)));
    				companyInfo.setLink_main(String.valueOf(companyMaps.get(32)));
    				companyInfo.setCompany_brief(String.valueOf(companyMaps.get(33)));
    				companyInfo.setBusiness(String.valueOf(companyMaps.get(34)));
    				companyInfo.setWind_code(String.valueOf(companyMaps.get(35)));
    				companyInfo.setClosing_price(String.valueOf(companyMaps.get(36)));
    				companyInfo.setPrice_date(String.valueOf(companyMaps.get(37)));
    				companyInfo.setBoard_chair_men(String.valueOf(companyMaps.get(38)));
    				companyInfo.setRegister_addressCopy("");
    				
    				List<CompanyBasicinfo> result = this.companyDao.findCompanyList(0, 100, companyInfo.getStock_code(),
    						companyInfo.getStock_name(), null);
    				if (result != null && result.size() > 0) {
    					companyInfo.setId(result.get(0).getId());
    				}
    				companyBasicinfos.add(companyInfo);
    			}
            	
            	UUID uuid = UUID.randomUUID();
            	request.getSession().setAttribute(uuid.toString(), companyBasicinfos);
            	
            	Cookie cookie = new Cookie("UUID", uuid.toString());
            	cookie.setPath("/");
            	cookie.setMaxAge(3600);
            	response.addCookie(cookie);
            	
            	DataGrid<CompanyBasicinfo> dg = new DataGrid<CompanyBasicinfo>();
    			dg.setTotal((long) companyBasicinfos.size());
    			dg.setRows(companyBasicinfos);

    			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/importCompanyBasic")
	public void importCompanyBasic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "fail";
		try {
			Cookie cookie = CookieUtils.getCookie(request, response, "UUID");
			if (cookie == null) {
				throw new Exception("Please import excel first.");
			}
			String UUID = cookie.getValue();
			final List<CompanyBasicinfo> companyBasicinfos = (List<CompanyBasicinfo>) request.getSession().getAttribute(UUID);
        	List<CompanyBasicinfo> companyBasicinfoUpdate = new ArrayList<>();
        	List<CompanyBasicinfo> companyBasicinfoInsert = new ArrayList<>();
			
			if (companyBasicinfos != null && companyBasicinfos.size() > 0) {
				for (CompanyBasicinfo m : companyBasicinfos) {
					System.out.println(m.getCompany_name());
					if (m.getId() != null) {
						companyBasicinfoUpdate.add(m);
					} else {
						companyBasicinfoInsert.add(m);
					}
				}
				
				companyDao.batchInsertCompanyBasicinfo(companyBasicinfoInsert);
				companyDao.batchUpdateCompanyBasicinfo(companyBasicinfoUpdate);
				
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
	
	@RequestMapping("/saveCompanyBasic")
	public void saveCompanyBasic(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute() CompanyBasicinfo companyBasicinfo) throws Exception {
		String result = "fail";
		try {
			companyDao.updateCompanyBasicinfo(companyBasicinfo);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		
		ControllerUtil.toJson(response, result);
	}
}
