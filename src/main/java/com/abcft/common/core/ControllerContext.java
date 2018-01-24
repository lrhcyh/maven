package com.abcft.common.core;

import javax.servlet.http.HttpSession;
/**
 * Controller的上下文，存放session等等
 * @author inning
 * @DateTime 2015-7-20 上午10:30:20
 *
 */
public class ControllerContext {
	
	public static final ThreadLocal<HttpSession> threadLocal = new ThreadLocal<HttpSession>();
	
	public static HttpSession getSession() {
		return threadLocal.get();
	}
	
	public static void setSession(HttpSession session) {
		threadLocal.set(session);
	}

	
}
