package com.abcft.system.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 访问来源饼状图数据
 * @author Administrator
 *
 */
@Entity
@Table(name = "AccessSource", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserId" }) })
public class AccessSource {

	private String name;
	private String value;

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
