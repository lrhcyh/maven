package com.abcft.system.nlpSynonym;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dic_synonyms", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class NlpSynonymInfo {

	private Long id;
	
	private String name;
	
	private Long parentId;
	
	private String createTime;
	
	private int type;
	
	private String name_text;
	
	private String stock_name;
	
	private String stock_code;
	
	private String stock_abc_code;

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

	@Column(name = "parentId", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	@Column(name = "name_text", unique = true, nullable = true, insertable = true, updatable = true, precision = 200, scale = 0)
	public String getName_text() {
		return name_text;
	}

	public void setName_text(String name_text) {
		this.name_text = name_text;
	}

	@Column(name = "stock_name", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getStock_name() {
		return stock_name;
	}

	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	
	@Column(name = "stock_code", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getStock_code() {
		return stock_code;
	}

	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}

	@Column(name = "stock_abc_code", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getStock_abc_code() {
		return stock_abc_code;
	}

	public void setStock_abc_code(String stock_abc_code) {
		this.stock_abc_code = stock_abc_code;
	}
	
	
}
