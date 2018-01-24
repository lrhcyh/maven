package com.abcft.system.rolemanage;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

/**
 * 角色实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "backend_role", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5779863572055947450L;

	private Long id;
	private String role_name;
	private String role_info;
	private Date create_time;

	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "role_name", unique = false, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Column(name = "role_info", unique = false, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getRole_info() {
		return role_info;
	}

	public void setRole_info(String role_info) {
		this.role_info = role_info;
	}

	
}
