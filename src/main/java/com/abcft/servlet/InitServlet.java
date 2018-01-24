package com.abcft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * web项目 初始化servlt
 * @author inning
 * @DateTime 2015-7-20 上午11:38:52
 *
 */
public class InitServlet extends HttpServlet{
	public static final String WEB_SITE_ROOT_PATH = InitServlet.class.getResource("/").getPath().replaceAll("%20", " ").substring(0, InitServlet.class.getResource("/").getPath().replaceAll("%20", " ").indexOf("WEB-INF"));

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int RUN_SLEEP_TIME = 5000;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
	}
	
}
