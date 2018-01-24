package com.abcft.system.pvSearch;

import javax.persistence.Column;

public class PvSearchResult {

	private String serchName;
	private int serchCount;
	@Column(name = "serchName", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getSerchName() {
		return serchName;
	}
	public void setSerchName(String serchName) {
		this.serchName = serchName;
	}
	@Column(name = "serchCount", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public int getSerchCount() {
		return serchCount;
	}
	public void setSerchCount(int serchCount) {
		this.serchCount = serchCount;
	}
	
}
