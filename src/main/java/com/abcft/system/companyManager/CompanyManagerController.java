package com.abcft.system.companyManager;

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
import com.abcft.common.util.StringUtils;
import com.abcft.system.company.ReadExcelUtils;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/companyManager")
public class CompanyManagerController extends BaseController {

	@Autowired
	private companyManagerDao companyDao;
	
	

	/**
	 * 显示公司列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findList")
	public void findList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			//查询参数
			String stockCode = request.getParameter("stockCode");
			String managerName = request.getParameter("managerName");
			String keyword = request.getParameter("keyword");
			
			
			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 10 : Long.valueOf(rows);
			
			long start= (currentPage-1)*pageSize;
			long end= pageSize;
			
			List<CompanyManagerInfo> list = companyDao.findList(start, end,stockCode,managerName, keyword);
			DataGrid<CompanyManagerInfo> dg = new DataGrid<CompanyManagerInfo>();
			dg.setTotal(companyDao.getTotalRole(stockCode,managerName, keyword));
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
				return new ModelAndView("users/add");
			} else if ("edit".equals(page)) {
				return new ModelAndView("users/edit");
			} else if ("import".equals(page)) {
				return new ModelAndView("companyManagerInfo/import");
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
	public void removeUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * 批量导入公司高管信息
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
            	List<CompanyManagerInfo> companyManagerInfos = new ArrayList<>();
            	
            	for (int i = 1; i <= content.size(); i++) {
            		Map<Integer, Object> companyMaps = content.get(i);
            		CompanyManagerInfo companyInfo = new CompanyManagerInfo();
    				companyInfo.setStock_code(String.valueOf(companyMaps.get(1)));
    				companyInfo.setIdcard(String.valueOf(companyMaps.get(2)));
    				companyInfo.setManager_name(String.valueOf(companyMaps.get(3)));
    				companyInfo.setPost_name(String.valueOf(companyMaps.get(4)));
    				companyInfo.setPost_type(String.valueOf(companyMaps.get(5)));
    				companyInfo.setBegin_date(String.valueOf(companyMaps.get(6)));
    				companyInfo.setEnd_date(String.valueOf(companyMaps.get(7)));
    				companyInfo.setSex(String.valueOf(companyMaps.get(8)));
    				companyInfo.setCountry(String.valueOf(companyMaps.get(9)));
    				companyInfo.setEducation(String.valueOf(companyMaps.get(10)));
    				companyInfo.setBirth_year(String.valueOf(companyMaps.get(11)));
    				companyInfo.setWork_experience(String.valueOf(companyMaps.get(12)));
    				companyInfo.setCreated_at(String.valueOf(companyMaps.get(13)));
    				
    				List<CompanyManagerInfo> result = this.companyDao.findList(0, 100, companyInfo.getStock_code(),
    						companyInfo.getManager_name(), null);
    				if (result != null && result.size() > 0) {
    					companyInfo.setManager_info_id(result.get(0).getManager_info_id());
    				}
    				companyManagerInfos.add(companyInfo);
    			}
            	
            	UUID uuid = UUID.randomUUID();
            	request.getSession().setAttribute(uuid.toString(), companyManagerInfos);
            	
            	Cookie cookie = new Cookie("UUID", uuid.toString());
            	cookie.setPath("/");
            	cookie.setMaxAge(3600);
            	response.addCookie(cookie);
            	
            	DataGrid<CompanyManagerInfo> dg = new DataGrid<CompanyManagerInfo>();
    			dg.setTotal((long) companyManagerInfos.size());
    			dg.setRows(companyManagerInfos);

    			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/importCompanyManager")
	public void importCompanyManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "fail";
		try {
			Cookie cookie = CookieUtils.getCookie(request, response, "UUID");
			if (cookie == null) {
				throw new Exception("Please import excel first.");
			}
			String UUID = cookie.getValue();
			final List<CompanyManagerInfo> companyManagerInfos = (List<CompanyManagerInfo>) request.getSession().getAttribute(UUID);
			List<CompanyManagerInfo> insertcompanyManagerInfos = new ArrayList<>();
			List<CompanyManagerInfo> updatecompanyManagerInfos = new ArrayList<>();
			
			if (companyManagerInfos != null && companyManagerInfos.size() > 0) {
				for (CompanyManagerInfo m : companyManagerInfos) {
					System.out.println(m.getManager_name());
					if (m.getManager_info_id() != null) {
						insertcompanyManagerInfos.add(m);
					} else {
						updatecompanyManagerInfos.add(m);
					}
					
				}
				
				companyDao.batchInsertCompanyManager(insertcompanyManagerInfos);
				companyDao.batchUpdateCompanyManager(updatecompanyManagerInfos);
				
				result = "success";
				// 清除缓存中的数据
				request.getSession().removeAttribute("UUID");
			}
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		
		ControllerUtil.toJson(response, result);
	}
	
	@RequestMapping("/saveCompanyManager")
	public void saveCompanyManager(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute() CompanyManagerInfo companyManagerInfo) throws Exception {
		String result = "fail";
		try {
			companyDao.updateCompanyManager(companyManagerInfo);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		
		ControllerUtil.toJson(response, result);
	}
	
}
