package com.abcft.common.db;

public class BaseEntity implements java.io.Serializable {
	
	/**
	 * 1.0
	 */
	private static final long serialVersionUID = 1L;
	private Integer curPage = 1;// 当前页面
	private Integer pageRows = 10;// 每页的行数
	private Integer pageAmount = 0;// 总页数
	private Integer totalRows;//总行数
	@SuppressWarnings("unused")
	private Integer firstRow=1;
	
	private String orderBy;//排序字段
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/*	private Integer shopId;//店铺Id
	private Long merchantId;//商家编号
*/	
	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}
	
	public Integer getFirstRow() {
		if (curPage != null && pageRows != null)
			return (curPage - 1) * pageRows;
		else
			return null;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageRows() {
		return pageRows;
	}

	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}

	public Integer getPageAmount() {
		return pageAmount;
	}

	public void setPageAmount(Integer pageAmount) {
		this.pageAmount = pageAmount;
	}
	

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
}
