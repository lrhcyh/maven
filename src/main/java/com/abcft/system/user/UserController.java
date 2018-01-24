package com.abcft.system.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.core.BaseController;
import com.abcft.common.core.MD5;
import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.StringUtils;
import com.abcft.system.rolemanage.Role;
import com.abcft.system.rolemanage.RoleDao;
import com.google.gson.GsonBuilder;

/**
 * 用户管理控制器
 * 
 * @author 黄茜
 *
 */
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PlatformTransactionManager transactionManager; 

	/**
	 * 显示用户列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/findUserList")
	public void findUserList(HttpServletRequest request, HttpServletResponse response) {
		try {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			//查询参数
			String userName = request.getParameter("userName");
			String fullName = request.getParameter("fullName");
			String roleId = request.getParameter("roleId");
			
			long currentPage = (StringUtils.isEmpty(page)) ? 1 : Long.valueOf(page);
			long pageSize = (StringUtils.isEmpty(rows)) ? 10 : Long.valueOf(rows);
			
			long start= (currentPage - 1) * pageSize;
			long end= pageSize;
			
			List<UserInfo> listUserInfos = userDao.findEmployees(start, end, userName, fullName, roleId);
			DataGrid<UserInfo> dg = new DataGrid<UserInfo>();
			dg.setTotal(userDao.getTotalRole(userName, fullName, roleId));
			dg.setRows(listUserInfos);
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
			} else if ("editUserRole".equals(page)) {
				// 修改用户角色
				return new ModelAndView("users/editUserRole");
			} else if("addUserRole".equals(page)) {
				ModelAndView mv = new ModelAndView("users/auth");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/findRoles")
	public void findRoles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "fail";
		try {
			List<Role> list = userDao.findRoles();
			result = new GsonBuilder().create().toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ControllerUtil.writeJson(response, result);
	}
	
	/**
	 * 添加用户
	 * @param userInfo
	 * @param request
	 * @param response
	 * 
	 * @return result[0-用户创建成功，1-用户名已存在，-1-创建用户失败]
	 * @throws Exception 
	 */
	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "-1";
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(request.getParameter("userName"));
			userInfo.setFullName(request.getParameter("fullName"));
			userInfo.setPassWord(request.getParameter("passWord"));
			userInfo.setEmail(request.getParameter("email"));
			userInfo.setStatus(Integer.valueOf(request.getParameter("status")));
			userInfo.setRoleId(Integer.valueOf(request.getParameter("roleId")));
			
			userInfo.setPassWord(MD5.getMD5(userInfo.getPassWord()));
			// 判断用户名是否重复
			Long uid = userDao.getUserInfoByUserName(userInfo.getUserName());
			if (null != uid && uid > 0) {
				// 用户名已存在
				result = "1";
			} else {
				Long id = userDao.insertUser(userInfo);
				userInfo.setId(id);
				result = "0";
			}
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	@RequestMapping("/editUser")
	public void editUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "-1";
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(Long.valueOf(request.getParameter("id")));
			userInfo.setFullName(request.getParameter("fullName"));
			userInfo.setEmail(request.getParameter("email"));
			userInfo.setStatus(Integer.valueOf(request.getParameter("status")));
			
			userDao.updateUser(userInfo);
			result = "0";
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	@RequestMapping("/findUserRole")
	public void findUserRole(HttpServletRequest request, HttpServletResponse respons) {
		try {
			String roleId = request.getParameter("roleId");
			List<Role> roleList = roleDao.findRoleById(roleId);
			Map<String, String> map = new HashMap<>();
			map.put("role_name", roleList.get(0).getRole_name());
			
			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/removeUser")
	public void removeUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String result = "fail";
		try {
			userDao.removeUser(id);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	@RequestMapping("/saveUserRole")
	public void saveUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rolecode = request.getParameter("rolecode");
		String empCode = request.getParameter("empCode");
		String result = "fail";
		try {
			
			userDao.updateUserRole(rolecode,empCode);
			result = "success";
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		ControllerUtil.toJson(response, result);
	}
	
	/**
	 * 修改用户权限
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/editUserRole")
	public void editUserRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String roleId = request.getParameter("roleId");
			
			userDao.updateUserRole(id, roleId);
			UserInfo userInfo = userDao.queryUserById(id);
			
			ControllerUtil.writeJson(response, new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(userInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
