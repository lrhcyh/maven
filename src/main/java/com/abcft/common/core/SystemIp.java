package com.abcft.common.core;


import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * 获取客户端用户IP
 * @author inning
 * @DateTime 2015-7-20 上午11:33:29
 *
 */
public class SystemIp {
	
	private static InetAddress inetAddress = null;
	//初始化
	static {
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	//获取系统IP地址
	public static String getSysIp(){
		
		return inetAddress.getHostAddress();
	}
	//获取系统IP对应的主机名
	public static String getSysLoc(){
		return inetAddress.getHostName();
	}
	
	//获取客户端真实IP地址
	public static String getRemortIP(HttpServletRequest request) {
		  if (request.getHeader("x-forwarded-for") == null) {
		   return request.getRemoteAddr();
		  }
		  return request.getHeader("x-forwarded-for");
	}

	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		
		System.out.println("服务端IP"+SystemIp.getSysIp()+"  "+SystemIp.getSysLoc());
		
	}
	
}
