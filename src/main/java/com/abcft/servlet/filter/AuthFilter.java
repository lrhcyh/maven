package com.abcft.servlet.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abcft.common.util.StringUtils;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		// 获得用户请求的URI
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		
		if(uri.contains("/login.jsp") || uri.contains("/index.jsp")|| uri.contains("/loginout.jsp") ||uri.equals("/hanxinManager/")) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		
		
		// 根据权限判断是否可以访问页面
		//UserSession u = (UserSession) session.getAttribute(UserSession.NAME);
		/*if (u == null) {
			response.sendRedirect(contextPath + "/login.jsp");
			return;
		}*/
		
		
		List<String> roleUrls = (List<String>) session.getAttribute("roleUrls");
		if(!StringUtils.isEmpty(roleUrls)) {
			for (String ru : roleUrls) {
				if(!StringUtils.isEmpty(ru) && uri.contains(ru)) {
					filterChain.doFilter(servletRequest, servletResponse);
				}
			}
		}
		

		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
