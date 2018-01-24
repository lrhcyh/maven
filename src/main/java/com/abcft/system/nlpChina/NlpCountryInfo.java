package com.abcft.system.nlpChina;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dic_basic", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class NlpCountryInfo {

	private Long id;
	
	private String name;
	
	
	
	private String createTime;
	
	private int type;
	


	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false, insertable = true, updatable = true, precision = 200, scale = 0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name = "createTime", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "type", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	


	
	
}
