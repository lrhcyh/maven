package com.abcft.common.core;                                            

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 所有Controller父类
 * @author inning
 * @DateTime 2015-7-20 上午10:29:43
 *
 */

public class BaseController {

	private static final long serialVersionUID = 1L;
	private String message = null;
	private String error_message = null;
	protected HttpServletResponse response = null;
	protected HttpServletRequest request = null;

	public String getMessage() {
		return message;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	/** 
	 * @Description: 异步返回数据
	 * @param j      
	 * @author LiXiaoDong
	 */
	public void writeJqueryJson(String j) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html");
			//response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.write(j);
		} catch (Exception e) {
			
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}

		}
	}
	
	/**
	 * 获取登陆用户
	 * 
	 * @author LiXiaoDong
	 * @param 页面request
	 * */
	public UserSession getLoginedUserName() {
		UserSession user = (UserSession) this.request.getSession().getAttribute(
				"user");
		return user;
	}
}
