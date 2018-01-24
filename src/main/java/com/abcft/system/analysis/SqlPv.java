package com.abcft.system.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 存入PV原始数据实体
 * 
 * @author bocom
 *
 */
@Entity
@Table(name = "SqlPv", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserId" }) })
public class SqlPv {
	
	private Integer id;

	private String visit_datetime;// PV记录产生时间` datetime NOT NULL default
									// '0000-00-00 00:00:00',

	private Integer visit_hour;// PV记录产生小时` smallint(5) unsigned NOT NULL
								// default '0',

	private String domain_dest;// 目标访问域名` varchar(100) NOT NULL default '',

	private String domain_ref;// 目标访问父级别域名` varchar(100) NOT NULL default '',

	private String dirname;// 二级目录名称` varchar(100) NOT NULL default '',

	// private Integer item_type;//访问类型` tinyint(3) unsigned NOT NULL default
	// '0',

	// private Integer item_id;//对象ID` int(10) unsigned NOT NULL default '0',

	private String ip_long;// 源IP` int(10) unsigned NOT NULL default '0',

	private String userid;// 用户ID` int(10) unsigned NOT NULL default '0',

	private String cookies;// cookies` tinytext NOT NULL,

	private String script_dest;// 目标访问链接(去除域名)` varchar(254) NOT NULL default
								// '',

	private String script_ref;// 目标访问父级别链接(去除域名)` varchar(254) NOT NULL default
								// '',

	private String url_dest;// 目标访问链接` varchar(254) NOT NULL default '',

	private String url_ref;// 目标访问父级别链接` varchar(254) NOT NULL default '',

	private String sessionid;
	private String day;

	private String userbrowser;

	private String useros;

	private String agent;

	private String province;

	private String city;

	private String visitorid;
	
	private Integer source;//访问来源  0：直接访问1：站内链接2：站外链接
	
	private String pageName;	//页面名称
	
	
	@Column(name = "source")
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@Column(name = "pageName")
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "visitorid")
	public String getVisitorid() {
		return visitorid;
	}

	public void setVisitorid(String visitorid) {
		this.visitorid = visitorid;
	}

	@Column(name = "userbrowser")
	public String getUserbrowser() {
		return userbrowser;
	}

	public void setUserbrowser(String userbrowser) {
		this.userbrowser = userbrowser;
	}

	@Column(name = "useros")
	public String getUseros() {
		return useros;
	}

	public void setUseros(String useros) {
		this.useros = useros;
	}

	@Column(name = "agent")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Column(name = "day")
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Column(name = "visit_datetime")
	public String getVisit_datetime() {
		return visit_datetime;
	}

	public void setVisit_datetime(String visit_datetime) {
		this.visit_datetime = visit_datetime;
	}

	@Column(name = "visit_hour")
	public Integer getVisit_hour() {
		return visit_hour;
	}

	public void setVisit_hour(Integer visit_hour) {
		this.visit_hour = visit_hour;
	}

	@Column(name = "domain_dest")
	public String getDomain_dest() {
		return domain_dest;
	}

	public void setDomain_dest(String domain_dest) {
		this.domain_dest = domain_dest;
	}

	@Column(name = "domain_ref")
	public String getDomain_ref() {
		return domain_ref;
	}

	public void setDomain_ref(String domain_ref) {
		this.domain_ref = domain_ref;
	}

	@Column(name = "dirname")
	public String getDirname() {
		return dirname;
	}

	public void setDirname(String dirname) {
		this.dirname = dirname;
	}

	@Column(name = "sessionid")
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	@Column(name = "ip_long")
	public String getIp_long() {
		return ip_long;
	}

	public void setIp_long(String ip_long) {
		this.ip_long = ip_long;
	}

	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "cookies")
	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	@Column(name = "script_dest")
	public String getScript_dest() {
		return script_dest;
	}

	public void setScript_dest(String script_dest) {
		this.script_dest = script_dest;
	}

	@Column(name = "script_ref")
	public String getScript_ref() {
		return script_ref;
	}

	public void setScript_ref(String script_ref) {
		this.script_ref = script_ref;
	}

	@Column(name = "url_dest")
	public String getUrl_dest() {
		return url_dest;
	}

	public void setUrl_dest(String url_dest) {
		this.url_dest = url_dest;
	}

	@Column(name = "url_ref")
	public String getUrl_ref() {
		return url_ref;
	}

	public void setUrl_ref(String url_ref) {
		this.url_ref = url_ref;
	}

	@Override
	public String toString() {
		return "SqlPv [visit_datetime=" + visit_datetime + ", visit_hour=" + visit_hour + ", domain_dest=" + domain_dest + ", domain_ref=" + domain_ref + ", dirname=" + dirname + ", ip_long=" + ip_long + ", userid=" + userid + ", cookies=" + cookies + ", script_dest=" + script_dest + ", script_ref=" + script_ref + ", url_dest=" + url_dest + ", url_ref=" + url_ref + ", sessionid=" + sessionid + ", day=" + day + ", userbrowser=" + userbrowser + ", useros=" + useros + ", agent=" + agent + "]";
	}

}
