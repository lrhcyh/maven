package com.abcft.system.nlpSynonym;

import com.abcft.common.pagemodel.TreeGridNode;

public class NlpSynonymTreeGridNode extends TreeGridNode {
	
	private Long id;
	private String name;
	private Long parentId;
	private String createTime;
	private int type;
	private String name_text;
	private String stock_name;
	private String stock_code;
	private String stock_abc_code;
	
	public NlpSynonymTreeGridNode(Long id, String name, Long parentId,
			String createTime, int type, String name_text, String stock_name,
			String stock_code, String stock_abc_code) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.createTime = createTime;
		this.type = type;
		this.name_text = name_text;
		this.stock_name = stock_name;
		this.stock_code = stock_code;
		this.stock_abc_code = stock_abc_code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName_text() {
		return name_text;
	}

	public void setName_text(String name_text) {
		this.name_text = name_text;
	}

	public String getStock_name() {
		return stock_name;
	}

	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

	public String getStock_code() {
		return stock_code;
	}

	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}

	public String getStock_abc_code() {
		return stock_abc_code;
	}

	public void setStock_abc_code(String stock_abc_code) {
		this.stock_abc_code = stock_abc_code;
	}
}
