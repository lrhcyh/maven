package com.abcft.system.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

/**
 * 系统用户实体
 * @author inning
 * @DateTime 2015-7-20 上午11:40:09
 *
 */
@Entity
@Table(name = "SYS_ADMIN", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "OPERATOR_ID" }) })
public class BackOperator extends BaseEntity {

	private static final long serialVersionUID = 8408698653779429801L;
	private Long operatorid;
	private String operatorname;
	private String operatorpassword;
	private String operatorrealname;
	private String rolelist;
	
	/** default constructor */
	public BackOperator() {
	}

	/** minimal constructor */
	public BackOperator(Long operatorid, String operatorname, String operatorpassword,
			String operatorrealname,String rolelist) {
		this.operatorid = operatorid;
		this.operatorname = operatorname;
		this.operatorpassword = operatorpassword;
		this.operatorrealname = operatorrealname;
		this.rolelist = rolelist;
	}
	
	// Property accessors
	@Id
	@Column(name = "OPERATOR_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}
	
	@Column(name = "OPERATOR_NAME", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	
	@Column(name = "OPERATOR_PASSWORD", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getOperatorpassword() {
		return operatorpassword;
	}

	public void setOperatorpassword(String operatorpassword) {
		this.operatorpassword = operatorpassword;
	}
	
	@Column(name = "OPERATOR_REALNAME", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getOperatorrealname() {
		return operatorrealname;
	}

	public void setOperatorrealname(String operatorrealname) {
		this.operatorrealname = operatorrealname;
	}
	
	@Column(name = "ROLELIST", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getRolelist() {
		return rolelist;
	}

	public void setRolelist(String rolelist) {
		this.rolelist = rolelist;
	}
	
	
}
