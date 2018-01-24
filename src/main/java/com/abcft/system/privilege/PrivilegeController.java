package com.abcft.system.privilege;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.core.UserSession;
import com.abcft.common.pagemodel.TreeGrid;
import com.abcft.common.pagemodel.TreeGridNode;
import com.abcft.common.util.ControllerUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 权限控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/privilege")
public class PrivilegeController {
	private static Logger logger = Logger.getLogger(PrivilegeController.class);

	@Autowired
	private PrivilegeDao privilegeDao;

	// 在左侧显示树型菜单
	@RequestMapping("/findModuleById")
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
		JSONArray deptJsonArr = getDeptTree(roleId,Integer.valueOf(id));
		treeJsonObject.put("children", deptJsonArr);
		//rootJsonArr.put(treeJsonObject);
		String json = "[" + treeJsonObject.toString() + "]";
		try {
			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private JSONArray getDeptTree(Integer roletId, Integer parentId) throws JSONException {
		List<Functions> persons  = new ArrayList<Functions>();
		if(roletId != 0) {
			 persons = privilegeDao.getFunctionsParent(roletId,parentId);
		} else {
			persons = privilegeDao.getFunctionsParent(parentId);
		}
		JSONArray deptJsonArr = new JSONArray();
		for (Functions function : persons) {
			JSONObject deptJsonData = new JSONObject();
			deptJsonData.accumulate("id", function.getId());
			deptJsonData.accumulate("text", function.getFunctionName());
			deptJsonData.accumulate("url", function.getUrl());
			
			List subDeptList = privilegeDao.getFunctionsParent(null,Integer.valueOf(function.getId().toString()));
			if (subDeptList != null && subDeptList.size() > 0) {
				deptJsonData.accumulate("state", "open");
				JSONArray subTreeJsonArr = new JSONArray();
				subTreeJsonArr = getDeptTree(roletId,Integer.valueOf(function.getId().toString()));
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
	 * 进入添加或者修改页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/goToPage")
	public ModelAndView goToPage(HttpServletRequest request) {
		ModelAndView mv = null;
		String goToPage = request.getParameter("goToPage");
		String id = request.getParameter("id");
		// 修改
		if (goToPage.equals("update")) {
			mv = new ModelAndView("privilege/edit");
		} else if (goToPage.equals("insert")) {
			mv = new ModelAndView("privilege/add");
		}
		return mv;
	}

	/**
	 * 在treegrid中显示菜单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/treegrid")
	public void getTreeGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Functions> flist = privilegeDao.getFunctions();
		TreeGrid tg = new TreeGrid();
		tg.setTotal(flist.size());
		List<TreeGridNode> rows = new ArrayList<TreeGridNode>();
        
		for (Functions f : flist) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String createTime = sdf.format(f.getCreate_time());
			if(f.getParentid()==0){
				f.setParentid(null);
			}
			TreeGridNode node = new TreeGridNode(f.getId(), f.getFunctionName(), f.getUrl(), f.getParentid(), createTime);
			node.setSortnum(f.getSortNum());
			node.setIsLeaf(f.getIsLeaf());
			node.setIsShow(f.getIsShow());
			if (f.getIsShow() == 1) {
				node.setShowText("是");
			} else {
				node.setShowText("否");
			}
			rows.add(node);
		}
		tg.setRows(rows);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		logger.info(gson.toJson(tg));
		ControllerUtil.writeJson(response,gson.toJson(tg));
		
	}

	// 添加菜单子节点
	@RequestMapping("/addMenuNode")
	public void addMenuNode(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "0") String data) {
		Gson oGson = new GsonBuilder().create();
		Functions function = (Functions) oGson.fromJson(data, Functions.class);
		try {
			Long id = privilegeDao.insertFunction(function);
			function.setId(id);
			ControllerUtil.writeJson(response, new Gson().toJson(function));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改菜单节点
	@RequestMapping("/editMenuNode")
	public void editFunction(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "0") String data) throws Exception {
		Gson oGson = new GsonBuilder().create();
		Functions function = (Functions) oGson.fromJson(data, Functions.class);
		try {
			privilegeDao.updateFunction(function);
			ControllerUtil.writeJson(response, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 拖拽菜单节点
	@RequestMapping("/dragNode")
	public void dragNode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long sourceId = (Long.valueOf(request.getParameter("sourceId")));
		Long targetId = (Long.valueOf(request.getParameter("targetId")));
		Long sourceNum = (Long.valueOf(request.getParameter("sourceNum")));
		Long targetNum = (Long.valueOf(request.getParameter("targetNum")));
		String result = "";
		try {
			privilegeDao.dragNode(sourceId, targetId, sourceNum, targetNum);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ControllerUtil.writeJson(response, result);
	}

	// 删除修改菜单节点
	@RequestMapping("/deleteMenuNode")
	public void deleteFunction(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "0") String data) throws Exception {
		Long id = Long.valueOf(data);
		String result = "";
		try {
			privilegeDao.deleteFunction(id);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ControllerUtil.toJson(response, result);
		}
	}

	// 隐藏菜单节点
	@RequestMapping("/hideNode")
	public void hideNode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = request.getParameter("id");
		String str2 = request.getParameter("isShow");
		if (str != null && !("").equals(str) && str2 != null && !("").equals(str2)) {
			Long id = Long.valueOf(str);
			Integer isShow = Integer.valueOf(str2);
			try {
				privilegeDao.updateIsShow(id, isShow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ControllerUtil.writeJson(response, str2);

	}
	


	// 判断节点能否隐藏
	@RequestMapping("/nodeCanHide")
	public void nodeCanHide(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long id = Long.valueOf(request.getParameter("id"));
		String result = "";
		if (id != null && !id.equals("")) {
			try {
				int flag = privilegeDao.nodeCanHide(id);
				if (flag == 0) {
					result = "yes";
				} else {
					result="no";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ControllerUtil.writeJson(response, result);
	}

}
