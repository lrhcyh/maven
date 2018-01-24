package com.abcft.system.rolemanage;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.StringUtils;
import com.abcft.system.privilege.Functions;
import com.abcft.system.privilege.PrivilegeDao;
import com.google.gson.GsonBuilder;

/**
 * 角色控制器
 * 
 * @author 黄茜
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	private static Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PrivilegeDao privilegeDao;

	/**
	 * 进入添加或者修改页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoPage")
	public ModelAndView goToPage(HttpServletRequest request) {
		ModelAndView mv = null;
		String goToPage = request.getParameter("page");
		
		// 修改
		if (goToPage.equals("edit")) {
			mv = new ModelAndView("role/edit");
		} else if (goToPage.equals("add")) {
			mv = new ModelAndView("role/add");
		} else if (goToPage.equals("addRight")) {
			mv = new ModelAndView("role/addRight");
		} else if (goToPage.equals("addAuth")) {
			mv = new ModelAndView("role/auth");
		}
		return mv;
	}

	/**
	 * 显示角色列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findRoles")
	public void findRoles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 分页
		Long currentPage = Long.valueOf(request.getParameter("page"));
		Long pageSize = Long.valueOf(request.getParameter("rows"));
		long start= (currentPage-1)*pageSize;
		long end= pageSize;
		List<Role> rolelist = roleDao.getRoles(start, end);
		DataGrid<Role> dg = new DataGrid<Role>();
		dg.setTotal(roleDao.getTotalRole());
		dg.setRows(rolelist);
		ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(dg));
	}

	/**
	 * 添加角色
	 * 
	 * @param request
	 * @param response
	 * @param data
	 * 
	 * @return result[0-添加角色成功，1-角色名重复，-1-添加角色失败]
	 * @throws Exception 
	 */
	@RequestMapping("/addRole")
	public void addRole(HttpServletRequest request, HttpServletResponse response, Role role) throws Exception {
		String result = "-1";
		try {
			List<Role> list = roleDao.getRoleByRoleName(role.getRole_name());
			if (null != list && list.size() > 0) {
				result = "1";
			} else {
				roleDao.insertRole(role);
				result = "0";
			}
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}

	// 显示所有权限
	@RequestMapping("/getTree")
	public void getTree(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		// 根据角色判断是否勾选节点
		List<Long> functionIds = new ArrayList<Long>();
		String roleStr = request.getParameter("id");
		if (!StringUtils.isEmpty(roleStr)) {
			functionIds = roleDao.getChekedFunctions(Integer.valueOf(roleStr));
		}

		// 根节点 id为3
		JSONArray rootJsonArr = new JSONArray();
		JSONObject treeJsonObject = new JSONObject();
		treeJsonObject.accumulate("id", "3");
		treeJsonObject.accumulate("text", "权限列表");
		/*
		 * if (functionIds.contains(51)) { treeJsonObject.accumulate("checked",
		 * true); }
		 */
		JSONArray deptJsonArr = getDeptTree(3, functionIds);
		treeJsonObject.put("children", deptJsonArr);
		rootJsonArr.put(treeJsonObject);
		String json = "[" + treeJsonObject.toString() + "]";
		try {
			logger.info(json);
			ControllerUtil.writeJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JSONArray getDeptTree(int parentId, List<Long> functionIds) throws JSONException {
		List<Functions> persons = privilegeDao.getFunctionsParent(parentId);
		JSONArray deptJsonArr = new JSONArray();
		for (Functions function : persons) {
			JSONObject deptJsonData = new JSONObject();
			deptJsonData.accumulate("id", function.getId());
			deptJsonData.accumulate("text", function.getFunctionName());
			deptJsonData.accumulate("url", function.getUrl());
			deptJsonData.accumulate("pid", parentId);
			if (functionIds.contains(function.getId()) && function.getIsLeaf() == 1) {
				deptJsonData.accumulate("checked", true);
			}
			List<Functions> subDeptList = privilegeDao.getFunctionsParent(Integer.valueOf(function.getId().toString()));
			if (subDeptList != null && subDeptList.size() > 0) {
				deptJsonData.accumulate("state", "open");
				JSONArray subTreeJsonArr = new JSONArray();
				subTreeJsonArr = getDeptTree(Integer.valueOf(function.getId().toString()), functionIds);
				deptJsonData.put("children", subTreeJsonArr);
				deptJsonArr.put(deptJsonData);
			} else {
				deptJsonArr.put(deptJsonData);
				continue;
			}
		}

		return deptJsonArr;
	}

	// 添加角色权限
	@RequestMapping("/saveRoleRight")
	public void saveRoleRight(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("idData");
		String rid = request.getParameter("roleId");
		String result = "";
		try {
			if (!StringUtils.isEmpty(data) && !StringUtils.isEmpty(rid)) {
				Long roleId = Long.valueOf(request.getParameter("roleId"));
				// List<Long> ids = new ArrayList<Long>();
				String[] ids = data.split(",");

				// 先删除原有的role_right
				roleDao.deleteRoleRights(roleId);
				// 添加权限
				for (int i = 0; i < ids.length; i++) {
					roleDao.addRight(roleId, Long.valueOf(ids[i]));
				}
				result = "success";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = "fail";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		ControllerUtil.writeJson(response, result);
	}
	
	@RequestMapping("/remove")
	@Transactional
	public void removeRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("roleId");
		String result = "fail";
		try {
			Integer rid = null;
			if (!StringUtils.isEmpty(id)) {
				rid = Integer.parseInt(id);
			}
			//将该角色下的用户的roleId设为null
			roleDao.updateRoleOfUser(rid);
			//删除该角色的权限
			roleDao.removeRoleRight(rid);
			//删除角色
			roleDao.removeRole(rid);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	/**
	 * 添加权限
	 * 
	 * @param request
	 * @param response
	 * @param data
	 * @throws Exception 
	 */
	@RequestMapping("/addRight")
	public void addRight(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "fail";
		try {
			String roleId = request.getParameter("roleId");
			String _func = request.getParameter("functionIds");
			String[] functionIds = _func.split(",");
			
			// 先删除角色原有的所有权限
			roleDao.deleteRoleRights(Long.valueOf(roleId));
			
			for (String fid : functionIds) {
				if ("0".equals(fid)) {
					continue;
				}
				// 添加角色权限
				roleDao.addRight(Long.valueOf(roleId), Long.valueOf(fid));
			}
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
}
