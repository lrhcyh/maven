package com.abcft.business.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

/**
 * Demo实体
 * @author inning
 * @DateTime 2015-7-20 上午11:31:02
 *
 */
@Entity
@Table(name = "DEMO", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Demo extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID = 8408698653779429801L;
	private Long id;
	private String demostring;
	private Integer demointeger;
	private String demodate;


	
	/** default constructor */
	public Demo() {
	}

	/** minimal constructor */
	public Demo(Long id, String demostring, Integer demointeger,
			String demodate) {
		this.id = id;
		this.demostring = demostring;
		this.demointeger = demointeger;
		this.demodate = demodate;
	}
	
	// Property accessors
	@Id
	@SequenceGenerator(name="DEMO") //代表此字段为自增长，可以返回刚插入的最大值
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "DEMO_STRING", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getDemostring() {
		return demostring;
	}

	public void setDemostring(String demostring) {
		this.demostring = demostring;
	}
	
	@Column(name = "DEMO_INTEGER", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Integer getDemointeger() {
		return demointeger;
	}

	public void setDemointeger(Integer demointeger) {
		this.demointeger = demointeger;
	}
	
	@Column(name = "DEMO_DATE", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getDemodate() {
		return demodate;
	}

	public void setDemodate(String demodate) {
		this.demodate = demodate;
	}
	
	
}
