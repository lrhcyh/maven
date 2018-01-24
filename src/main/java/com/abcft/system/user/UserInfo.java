package com.abcft.system.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "backend_user", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class UserInfo {

	private Long id;
	
	private String userName;
	
	private String passWord;
	
	private String fullName;
	
	private String email;
	
	private int status;
	
	private int roleId;
	
	private String role_name;

	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "userName", unique = true, nullable = true, insertable = true, updatable = true, precision = 150, scale = 0)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "passWord", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "fullName", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "email", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "status", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "roleId", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "role_name", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
}
