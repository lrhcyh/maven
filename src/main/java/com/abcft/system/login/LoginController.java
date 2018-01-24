package com.abcft.system.login;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.core.MD5;
import com.abcft.common.core.SystemIp;
import com.abcft.common.core.UserSession;
import com.abcft.common.db.Employee;
import com.abcft.common.util.ControllerUtil;
import com.abcft.system.privilege.Functions;
import com.abcft.system.privilege.PrivilegeDao;
//import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 系统用户控制器
 * 
 * @author 武平阳
 * @DateTime 2017-5-17 上午11:40:26
 *
 */
@Controller
@SessionAttributes({ "userSession" })
@RequestMapping("/login")
public class LoginController {
	private Logger logger = LogManager.getLogger(LoginController.class);
	// private BackOperator backOperator;

	@Autowired
	private BackOperaterDao backOperaterDao;

	@Autowired
	private PrivilegeDao privilegeDao;
	
	
	@Autowired
	private LonginDao loginDao;

	/**
	 * 跳转到登录页面
	 */
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("login");

	}
	
	

	/**
	 * 系统登录用
	 * 
	 */
	@RequestMapping("loginIn")
	public void doLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "0") String data) throws Exception {

		// logger.info("进入到loginIn***********");
		// System.out.println("进入到loginIn***********");
		try {
			Gson oGson = new GsonBuilder().create();
			Employee employee = (Employee) oGson.fromJson(data, Employee.class);
			Employee a = this.loginDao.findOperatorLogins(employee.getUsername());
			String result = "";
			if (a == null) {
				// 用户不存在
				result = "1";
			} else if (!a.getPassword().equals(MD5.getMD5(employee.getPassword()))) {
				// 密码错误
				result = "2";
			} else {
				// 登录成功
				result = "3";

				UserSession u = new UserSession();
				u.setSessionUserId(a.getId());
				u.setSessionUserName(a.getUsername());
				u.setSessionTrueName(a.getFullname());
				u.setIp(SystemIp.getRemortIP(request));
				u.setRoleid(a.getRoleId());
				u.setPhoneNumber(a.getPhoneNumber());
				request.getSession().setAttribute("user", u);

				// 将用户的菜单放入到session中  所有的一级目录节点
				List<Functions> flist = privilegeDao.getFunctionsParent(3);
				
				//获得角色的可操作模块集合
				List<String> functionIds = privilegeDao.getModulesByRoleId(u.getRoleid());
				String modules = org.apache.commons.lang.StringUtils.join(functionIds.toArray(),","); 
				

				// 将用户权限写入到session
				List<String> roleUrls = privilegeDao.getRoleUrls(u.getRoleid());

				request.getSession().setAttribute("roleUrls", roleUrls);
				request.getSession().setAttribute("moduleList", flist);
				request.getSession().setAttribute("modulesAvail", modules);
			}

			ControllerUtil.toJson(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5.getMD5("123456"));
		System.out.println(UUID.randomUUID());
	}

	/**
	 * 用户注销
	 * 
	 */
	@RequestMapping("/loginout")
	public void loginout(HttpServletRequest request, HttpServletResponse response) {
		// 移除session
		/*
		 * request.getSession().removeAttribute(UserSession.NAME);
		 * request.getSession().removeAttribute("admin");
		 */
		// System.out.println("**************");
		// System.out.println(((UserSession)request.getSession().getAttribute(UserSession.NAME)).getUserName());
		// System.out.println("**************");
		request.getSession().invalidate();
		// System.out.println(((UserSession)request.getSession().getAttribute(UserSession.NAME)).getUserName());
		// String result = "success";
		// oGson=null;
		// ControllerUtil.toJson(response, result);
		// ModelAndView mv = new ModelAndView("defaultViewResolver");
		// return mv;
	}

	/**
	 * 跳转到修改密码
	 * 
	 */
	@RequestMapping("/changePassword")
	public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("system/password");
		UserSession user = (UserSession) request.getSession().getAttribute("user");
		mv.addObject("user", user.getUserName());
		return mv;
	}

	/**
	 * 确认密码
	 * 
	 */
	
	
	/*@RequestMapping("/confirmPassword")
	public void confirmPassword(HttpServletRequest request, HttpServletResponse response, @RequestParam String id, @RequestParam String psw, @RequestParam String newpsw) throws Exception {
		String result = "fail";

		Employee a = this.backOperaterDao.findOperatorLogin(id);
		if (a != null) { // 确认旧密码是否存在
			if (a.getPassword().equals(MD5.getMD5(psw))) {
				BackOperator backOperator = new BackOperator();
				backOperator.setOperatorid(a.getOperatorid());
				a.setPassword(MD5.getMD5(newpsw));
				int count = this.backOperaterDao.updateEmployee(a);
				if (count > 0) {
					result = "success";
				} else {
					result = "修改失败！";
				}
			} else {
				result = "原始密码错误！";
			}
		} else {
			result = "用户不存在！";
		}
		ControllerUtil.toJson(response, result);

	}*/
}