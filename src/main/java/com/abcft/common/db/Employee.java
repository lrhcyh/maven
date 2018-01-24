package com.abcft.common.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

/**
 * Employee实体
 *
 */

@Entity
@Table(name = "backend_user", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Employee extends BaseEntity implements java.io.Serializable {

	private String id;

	private String username;

	private String password;

	private String email;
	// 随机数
	private String salt;

	private String fullname;

	private String IDNumber;

	private String phoneNumber;



	private Integer roleId;

	private String roleName; // 角色名称



	private String functionName;
	
	private String status;

	
	@Column(name = "status", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "function_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	

	@Column(name = "role_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleId", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	

	@Id
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "userName", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "passWord", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salt", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "fullName", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "idnumber", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	@Column(name = "phonenumber", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
