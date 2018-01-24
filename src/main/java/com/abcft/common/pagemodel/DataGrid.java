package com.abcft.common.pagemodel;

import java.util.List;

import com.abcft.common.base.BaseEntity;

public class DataGrid<T> extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9213526857727926131L;
	private Long total;
	private List<T> rows;

	private List<T> footer;

	public List<T> getFooter() {
		return footer;
	}

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
