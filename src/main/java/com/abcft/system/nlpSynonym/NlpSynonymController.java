package com.abcft.system.nlpSynonym;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.core.BaseController;
import com.abcft.common.core.UserSession;
import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.CookieUtils;
import com.abcft.common.util.StringUtils;
import com.abcft.system.company.ReadExcelUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/nlpSynonym")
public class NlpSynonymController extends BaseController {
	
	@Autowired
	private NlpSynonymDao nlpSynonymDao;
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(NlpSynonymController.class);
	
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
				return new ModelAndView("nlpSynonym/add");
			} else if ("edit".equals(page)) {
				return new ModelAndView("nlpSynonym/edit");
			} else if ("import".equals(page)) {
				return new ModelAndView("nlpSynonym/import");
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
			String name_text = request.getParameter("name_text");
			String type = request.getParameter("type");

			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 10 : Long.valueOf(rows);

			long start = (currentPage - 1) * pageSize;
			long end = pageSize;

			List<NlpSynonymInfo> list = nlpSynonymDao.findList(start, end, name, name_text, type);
			DataGrid<NlpSynonymInfo> dg = new DataGrid<NlpSynonymInfo>();
			dg.setTotal(nlpSynonymDao.getTotalRole(name, name_text, type));
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
			nlpSynonymDao.remove(id);
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
	@RequestMapping("/editNlpSynonym")
	public void editNlpSynonym(@ModelAttribute() NlpSynonymInfo nlpSynonymInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.nlpSynonymDao.updateNlpSynonym(nlpSynonymInfo);
			ControllerUtil.writeJson(response, new Gson().toJson(nlpSynonymInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addNlpSynonym")
	public void addNlpSynonym(@ModelAttribute() NlpSynonymInfo nlpSynonymInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.nlpSynonymDao.insertNlpSynonym(nlpSynonymInfo);
			ControllerUtil.writeJson(response, new Gson().toJson(nlpSynonymInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// 在左侧显示树型菜单
	@SuppressWarnings("unused")
	@RequestMapping("/treegrid")
	public void findModuleById(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		String id = request.getParameter("id");
		UserSession u = (UserSession) request.getSession().getAttribute("user");
		Integer roleId = 0;
		if(u != null) {
			roleId = u.getRoleid();
		}
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		//JSONArray rootJsonArr = new JSONArray();
		JSONObject treeJsonObject = new JSONObject();
		treeJsonObject.accumulate("id", "0");
		treeJsonObject.accumulate("text", "菜单列表");
		JSONArray deptJsonArr = getDeptTree((long) 0);
		treeJsonObject.put("children", deptJsonArr);
		//rootJsonArr.put(treeJsonObject);
		String json = "[" + treeJsonObject.toString() + "]";
		try {
			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private JSONArray getDeptTree( Long parentId) throws JSONException {
		List<NlpSynonymInfo> persons  = new ArrayList<NlpSynonymInfo>();
		
			persons = nlpSynonymDao.getSynonymsParent(parentId);
		
		JSONArray deptJsonArr = new JSONArray();
		for (NlpSynonymInfo function : persons) {
			JSONObject deptJsonData = new JSONObject();
			deptJsonData.accumulate("id", function.getId());
			deptJsonData.accumulate("text", function.getName());
			deptJsonData.accumulate("name_text", function.getName_text());
			
			List<NlpSynonymInfo> subDeptList = nlpSynonymDao.getSynonymsParent(function.getId());
			if (subDeptList != null && subDeptList.size() > 0) {
				deptJsonData.accumulate("state", "open");
				JSONArray subTreeJsonArr = new JSONArray();
				subTreeJsonArr = getDeptTree(function.getId());
				deptJsonData.put("children", subTreeJsonArr);
				deptJsonArr.put(deptJsonData);
			} else {
				deptJsonArr.put(deptJsonData);
				continue;
			}
		}

		return deptJsonArr;
	}
	
	/**
	 * 导入Excel，导入的数据不写入数据库，解析后直接返回给客户端
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping("/importExport")
	public void importExport(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {  
            try {  
            	String fileName = file.getOriginalFilename();
            	String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            	
            	ReadExcelUtils excelUtils = new ReadExcelUtils(fileType, file.getInputStream());
            	
            	Map<Integer, Map<Integer, Object>> content = excelUtils.readExcelContentForMap();
            	List<NlpSynonymInfo> nlpSynonymInfos = new ArrayList<>();
            	Integer type = null;
            	for (int i = 2; i <= content.size(); i++) {
            		Map<Integer, Object> synonymMaps = content.get(i);
    				NlpSynonymInfo synonymInfo = new NlpSynonymInfo();
    				if (type == null) {
    					String _type = String.valueOf(synonymMaps.get(1));
    					if (_type != null) {
							type = Integer.valueOf(_type);
							synonymInfo.setType(type);
    					}
    				} else {
    					synonymInfo.setType(type);
    				}
    				String name = String.valueOf(synonymMaps.get(2));
    				if (name == null || name.length() < 1) {
    					continue;
    				}
    				synonymInfo.setName(name);
    				synonymInfo.setParentId(0L);
    				String name_text = String.valueOf(synonymMaps.get(3));
    				if (name_text != null && name_text.length() > 0) {
    					synonymInfo.setName_text(name_text);
    				}
    				synonymInfo.setStock_abc_code(String.valueOf(synonymMaps.get(4)));
    				
    				nlpSynonymInfos.add(synonymInfo);
    			}
            	
            	UUID uuid = UUID.randomUUID();
            	request.getSession().setAttribute(uuid.toString(), nlpSynonymInfos);
            	
            	Cookie cookie = new Cookie("UUID", uuid.toString());
            	cookie.setPath("/");
            	cookie.setMaxAge(3600);
            	response.addCookie(cookie);
            	
            	DataGrid<NlpSynonymInfo> dg = new DataGrid<NlpSynonymInfo>();
    			dg.setTotal((long) nlpSynonymInfos.size());
    			dg.setRows(nlpSynonymInfos);

    			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/importSynonym")
	public void importSynonym(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "fail";
		try {
			Cookie cookie = CookieUtils.getCookie(request, response, "UUID");
			if (cookie == null) {
				throw new Exception("Please import excel first.");
			}
			String UUID = cookie.getValue();
			final List<NlpSynonymInfo> nlpSynonymInfos = (List<NlpSynonymInfo>) request.getSession().getAttribute(UUID);
			
			
			if (nlpSynonymInfos != null && nlpSynonymInfos.size() > 0) {
				List<NlpSynonymInfo> synonymInfoInsert = new ArrayList<>();
				
				for (NlpSynonymInfo synonymInfo : nlpSynonymInfos) {
					System.out.println(synonymInfo.getName());
					if (synonymInfo.getParentId() != null && synonymInfo.getParentId() == 0) {
						Long id = nlpSynonymDao.selectNlpSynonymByNameAndParentIdAndType(synonymInfo.getName(), 0L, 
								synonymInfo.getType());
						String nametexts = synonymInfo.getName_text();
						if (nametexts == null || nametexts.length() < 1) {
							continue;
						}
						String[] name_texts = nametexts.split(",");
						
						if (id != null && id > 0) {
							nlpSynonymDao.updateNlpSynonym(synonymInfo);
							// 删除主体下的子节点
							nlpSynonymDao.deleteNlpSynonymByParentId(id, synonymInfo.getType());
							
		    				for (String text : name_texts) {
		    					if (text == null || text.length() < 1) {
		    						continue;
		    					}
		    					NlpSynonymInfo synonym = new NlpSynonymInfo();
		    					synonym.setType(synonymInfo.getType());
		    					synonym.setName(text);
		    					synonym.setParentId(id);
		        				synonym.setName_text(synonymInfo.getName_text());
		        				synonym.setStock_abc_code(synonymInfo.getStock_abc_code());
		        				
		        				synonymInfoInsert.add(synonym);
		    				}
						} else {
							// 插入主节点数据，获取到主节点 Id，作为子节点的parentId
							id = nlpSynonymDao.insertNlpSynonym(synonymInfo);
							for (String text : name_texts) {
		    					if (text == null || text.length() < 1) {
		    						continue;
		    					}
		    					NlpSynonymInfo synonym = new NlpSynonymInfo();
		    					synonym.setType(synonymInfo.getType());
		    					synonym.setName(text);
		    					synonym.setParentId(id);
		        				synonym.setName_text(synonymInfo.getName_text());
		        				synonym.setStock_abc_code(synonymInfo.getStock_abc_code());
		        				synonymInfoInsert.add(synonym);
		    				}
						}
						if (synonymInfoInsert.size() > 1000) {
        					nlpSynonymDao.batchInsertNlpSynonym(synonymInfoInsert);
        					synonymInfoInsert.clear();
        				}
					}
				}
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
	
	
	
}
