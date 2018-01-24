package com.abcft.common.core;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abcft.common.util.ControllerUtil;

/**
 * 
 * Controller拦截
 * 
 * @author inning
 * @DateTime 2015-7-20 上午10:30:47
 *
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {

	private List<String> allowUrls;

	public List<String> getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}

	private static final Logger logger = LogManager.getLogger(ControllerInterceptor.class);

	/**
	 * 在Controller方法前进行拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// log.log(Level.INFO, "在Controller方法前进行拦截");
		UserSession u = (UserSession) request.getSession().getAttribute("user");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		// 将工程地址存入session
		request.getSession().setAttribute("basePath", basePath);
		String uri = request.getRequestURI();

		for (String url : allowUrls) {
			if (uri.contains(url))
				return true;
		}

		if (u == null) {
			// 不拦截用户登录和注销
			if (uri.contains("login.form") || uri.contains("loginout.form")) {
				ControllerContext.setSession(request.getSession());
				return true;
			} 
			// session 过期处理,跳转到登录页面
			try {
				/*response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				response.sendRedirect(basePath + "/pages/system/loginout.jsp");*/
				
				//此处考虑ajax操作session过期的操作，如果ajax请求过程中session过期，则指定过期状态码为：911.  
	            String requestType = request.getHeader("X-Requested-With");  
	            if (!StringUtils.isEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")) {  
	            	response.setStatus(911);  
	            	response.setHeader("sessionstatus", "timeout");  
	            	response.addHeader("loginPath", basePath+"login.jsp"); 
	            	ControllerUtil.writeJson(response, "timeout");
	            }

			} catch (Exception e) {
				// 只打印，不抛出异常，否则可能导出现死循环
				logger.error(e);
			}
			return false;
		} else {
			/**
			 * List<String> a=adminDao.findUserModuleset(u.getUserId()); //
			 * 拦截非法的URL地址 StringBuffer url = request.getRequestURL(); String
			 * queryStr = request.getQueryString(); url.append(queryStr == null
			 * ? "" : "?" + queryStr);
			 * 
			 * int index = -1; for (int i = 0; i < a.size(); i++) { String[]
			 * resStr=a.get(i).split(";"); for(int j=0;j<resStr.length;j++){ if
			 * (a.get(j).equals("javascript:void(0)")||url.toString().indexOf(a.
			 * get(j)) > 0 ) { index = i; break; } } } if(index==-1){
			 * PrintWriter pw = null; try { pw = response.getWriter(); pw.write(
			 * "<script>top.location.href = '" + basePath +
			 * "public/forbidden.jsp';</script>"); response.setStatus(300); }
			 * catch (Exception e) { // 只打印，不抛出异常，否则可能导出现死循环 log.error(e); }
			 * finally { if (pw != null) { pw.close(); } } }
			 **/
			return true;
		}

	}

	/**
	 * 在Controller方法处理中进行拦截 生成视图之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// log.log(Level.INFO, "在Controller方法处理中进行拦截);
	}

	/**
	 * 在Controller方法后进行拦截
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// log.log( "在Controller方法后进行拦截");
	}
}
