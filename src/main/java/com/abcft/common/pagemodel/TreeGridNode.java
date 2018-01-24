package com.abcft.common.pagemodel;

import com.abcft.common.base.BaseEntity;

public class TreeGridNode extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8683293850441352206L;
	
	private Long id;
	private String name;
	private String URL;
	private Long _parentId;
	private String create_time;
	private String update_time;
	private String create_user;
	private Long sortnum;
	private Integer isLeaf;
	private Integer isShow;
	private String showText;
	
	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Long getSortnum() {
		return sortnum;
	}

	public void setSortnum(Long sortnum) {
		this.sortnum = sortnum;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public TreeGridNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TreeGridNode(Long id, String name, String uRL, Long _parentId, String create_time) {
		super();
		this.id = id;
		this.name = name;
		URL = uRL;
		this._parentId = _parentId;
		this.create_time = create_time;

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

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

}
