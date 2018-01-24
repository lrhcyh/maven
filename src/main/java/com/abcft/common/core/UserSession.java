package com.abcft.common.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * MD5算法  
 * @author inning
 * @DateTime 2015-7-20 上午11:33:43
 *
 */
public class UserSession {
	

	public static final String NAME = "userSession";

	private String userId;

	private String userName;
	
	private Integer roleid;

	private Long resId;

	private String trueName;
	
	private Long areaid;
	
	private Long orgId;
	
	private String ip;
	
	private String userType;
	
	private List<Integer> forbiddenResIdList = new ArrayList<Integer>();
	
	private String department; 
	
	private String phoneNumber;
	
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setSessionUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setSessionUserName(String userName) {
		this.userName = userName;
	}

	public Long getResId() {
		return resId;
	}

	public void setSessionResId(Long resId) {
		this.resId = resId;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setSessionTrueName(String trueName) {
		this.trueName = trueName;
	}


	public Long getAreaid() {
		return areaid;
	}

	public void setSessionAreaid(Long areaid) {
		this.areaid = areaid;
	}

	public List<Integer> getForbiddenResIdList() {
		return forbiddenResIdList;
	}

	public void setForbiddenResIdList(List<Integer> forbiddenResIdList) {
		this.forbiddenResIdList = forbiddenResIdList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setSessionOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserType() {
		return userType;
	}

	public void setSessionUserType(String userType) {
		this.userType = userType;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
