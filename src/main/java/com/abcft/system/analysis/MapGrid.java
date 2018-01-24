package com.abcft.system.analysis;

import java.io.Serializable;

import javax.persistence.Column;


public class MapGrid implements Serializable{
	
	private Integer id;
	private String name;	//网页名称
	private Integer pv;		//总访问量
	private Integer uv;		//访问用户数
	private Integer ipNum;	//访问ip数
	private Integer source;	//访问来源
	private String accessTime;//平均访问时长
	private String url ;
	private String registNum;//注册数量
	private String registVisitNum;//注册页访问数量
	@Column(name = "registVisitNum")
	public String getRegistVisitNum() {
		return registVisitNum;
	}
	public void setRegistVisitNum(String registVisitNum) {
		this.registVisitNum = registVisitNum;
	}
	private String registLv;//注册率
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "province")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "pv")
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	@Column(name = "uv")
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}
	@Column(name = "ipNum")
	public Integer getIpNum() {
		return ipNum;
	}
	public void setIpNum(Integer ipNum) {
		this.ipNum = ipNum;
	}
	@Column(name = "source")
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	@Column(name = "accessTime")
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "registNum")
	public String getRegistNum() {
		return registNum;
	}
	public void setRegistNum(String registNum) {
		this.registNum = registNum;
	}
	@Column(name = "registLv")
	public String getRegistLv() {
		return registLv;
	}
	public void setRegistLv(String registLv) {
		this.registLv = registLv;
	}
	
	
	
	
	
	

}
