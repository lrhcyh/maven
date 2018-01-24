package com.abcft.common.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 银行类型实体类
 * 
 * @author 黄茜
 *
 */
@Entity
@Table(name = "BankLimit", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class BankLimit {

	private String bankCode;
	private String bankName;

	@Column(name = "bank_code", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "bank_name", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
