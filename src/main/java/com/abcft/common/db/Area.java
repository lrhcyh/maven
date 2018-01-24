package com.abcft.common.db;

import javax.persistence.Column;

/**
 * 地区实体类
 * @author 黄茜
 *
 */
public class Area {

	private String province;	//省
	private String city;		//市
	private String code;		//编码
	private Integer id;
	
	
	@Column(name = "id", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "province", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "code", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
