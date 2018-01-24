package com.abcft.system.analysis;

import java.io.Serializable;


public class MapData implements Serializable{
	
	private String name;	//网页名称
	private Integer value;	//访问ip数
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	private String registLv;//注册率
	private Boolean selected;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRegistLv() {
		return registLv;
	}
	public void setRegistLv(String registLv) {
		this.registLv = registLv;
	}
	
	
	
	
	

}
