package com.abcft.common.base;
/**
 * 基础实体 
 * @author inning
 * @DateTime 2015-7-20 上午11:34:42
 *
 */
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
	private Integer lastRow;
	
	public Integer getLastRow() {
		return curPage*pageRows;
	}

	public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}

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
		return (curPage - 1) * pageRows+1;
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
