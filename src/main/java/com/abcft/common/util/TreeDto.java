package com.abcft.common.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author inning
 * @DateTime 2015-7-20 上午11:38:35
 *
 */
public class TreeDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private String state;
	private Boolean checked;
	private List<TreeDto> children;
	private String parentId;
	
	private Map<String, String> attributes ;
	
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public List<TreeDto> getChildren() {
		return children;
	}
	public void setChildren(List<TreeDto> children) {
		this.children = children;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
