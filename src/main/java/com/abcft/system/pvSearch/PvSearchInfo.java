package com.abcft.system.pvSearch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "pv_serch", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class PvSearchInfo {

	private Long id;
	
	private String serchName;
	
	private String create_date;
	
	private String ip_long;
	
	private String visitorid;
	
	private String type;
	
	private String userId;
	
	private String searchResult;
	
	private String summary;
	
	private String userName;

	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "create_date", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	@Column(name = "ip_long", unique = true, nullable = true, insertable = true, updatable = true, precision = 50, scale = 0)
	public String getIp_long() {
		return ip_long;
	}

	public void setIp_long(String ip_long) {
		this.ip_long = ip_long;
	}

	@Column(name = "visitorid", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getVisitorid() {
		return visitorid;
	}

	public void setVisitorid(String visitorid) {
		this.visitorid = visitorid;
	}

	@Column(name = "type", unique = true, nullable = true, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "userId", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "searchResult", unique = true, nullable = true, insertable = true, updatable = true, precision = 30, scale = 0)
	public String getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}

	@Column(name = "summary", unique = true, nullable = true, insertable = true, updatable = true, precision = 1000, scale = 0)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "serchName", unique = true, nullable = true, insertable = true, updatable = true, precision = 200, scale = 0)
	public String getSerchName() {
		return serchName;
	}

	public void setSerchName(String serchName) {
		this.serchName = serchName;
	}

	@Column(name = "userName", unique = true, nullable = true, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
