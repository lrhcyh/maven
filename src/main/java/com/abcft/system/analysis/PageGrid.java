package com.abcft.system.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 页面统计信息
 * 
 * @author 黄茜
 *
 */
@Entity
@Table(name = "PageGrid", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserId" }) })
public class PageGrid {

	private String pageName;	//页面名称
	private String url_dest;	//页面url
	private Integer uv;		//访客量
	private Integer pv;		//访问量
	private String accessTime;	//页面平均访问时间

	@Column(name = "pv")
	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	@Column(name = "accessTime")
	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	@Column(name = "pageName")
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Column(name = "url_dest")
	public String getUrl_dest() {
		return url_dest;
	}

	public void setUrl_dest(String url_dest) {
		this.url_dest = url_dest;
	}

	@Column(name = "uv")
	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

}
