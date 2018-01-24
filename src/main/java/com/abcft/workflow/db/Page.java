package com.abcft.workflow.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 描述:分页属性类
 * @author inning
 * @DateTime 2015-7-20 上午11:40:53
 */
public class Page<T> implements Serializable{
	
	private static final long serialVersionUID = -7991002736828223928L;
	private int pageSize = 10;/**每页显示的记录数**/
	private int pageSum = 0;					/**页面总记录数**/
	private int currentPage = 1;				/**当前页面数**/
	private int maxPage = 1;					/**最大页面数**/
	private int firstPage = 1;					/**首页页面数**/
	private int lastPagee = 1;					/**最后一页页面数**/
	private int nextPagee = -1;					/**下一页页面数**/
	private int previousPage = 1;				/**上一页页面数**/
	private String pageParam = "";			/**分页条件**/ 
	private List<T> list = new ArrayList<T>();
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageSum() {
		return pageSum;
	}
	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPagee() {
		return lastPagee;
	}
	public void setLastPagee(int lastPagee) {
		this.lastPagee = lastPagee;
	}
	public int getNextPagee() {
		return nextPagee;
	}
	public void setNextPagee(int nextPagee) {
		this.nextPagee = nextPagee;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public String getPageParam() {
		return pageParam;
	}
	public void setPageParam(String pageParam) {
		this.pageParam = pageParam;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
