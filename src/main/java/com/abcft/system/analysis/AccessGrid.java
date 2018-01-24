package com.abcft.system.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 访问来源统计表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "AccessGrid", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserId" }) })
public class AccessGrid {

	private Integer id;
	private String name; // 网页名称
	private Integer pv; // 总访问量
	private Integer uv; // 访问用户数
	private Integer ipNum; // 访问ip数
	private Integer source; // 访问来源
	private String accessTime;// 平均访问时长
	private String url;

	private Integer registerNum; // 注册人数
	private String conversion; // 注册转化率

	@Column(name = "registerNum")
	public Integer getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(Integer registerNum) {
		this.registerNum = registerNum;
	}

	@Column(name = "conversion")
	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "url_dest")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String skip; // 二跳率

	@Column(name = "name")
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

	@Column(name = "skip")
	public String getSkip() {
		return skip;
	}

	public void setSkip(String skip) {
		this.skip = skip;
	}

}
