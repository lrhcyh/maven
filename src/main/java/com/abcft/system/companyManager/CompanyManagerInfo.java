package com.abcft.system.companyManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

/**
 * Employee实体
 *
 */

@Entity
@Table(name = "company_manager_info", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class CompanyManagerInfo extends BaseEntity implements java.io.Serializable {

	private Integer manager_info_id;

	private String stock_code;

	private String idcard;

	// 随机数
	private String manager_name;

	private String post_name;

	private String post_type;

	private String begin_date;

	private String end_date;

	private String sex;

	private String country;

	private String education;

	private String birth_year;
	
	private String work_experience;

	private String created_at;

	@Id
	@Column(name = "manager_info_id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Integer getManager_info_id() {
		return manager_info_id;
	}

	public void setManager_info_id(Integer manager_info_id) {
		this.manager_info_id = manager_info_id;
	}

	@Column(name = "stock_code", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getStock_code() {
		return stock_code;
	}

	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}

	@Column(name = "idcard", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "manager_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	@Column(name = "post_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	@Column(name = "post_type", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}

	@Column(name = "begin_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	@Column(name = "end_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	
	@Column(name = "sex", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	@Column(name = "country", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "education", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "birth_year", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getBirth_year() {
		return birth_year;
	}

	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}

	
	@Column(name = "work_experience", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getWork_experience() {
		return work_experience;
	}

	public void setWork_experience(String work_experience) {
		this.work_experience = work_experience;
	}

	@Column(name = "created_at", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	
	
	
	
	

	
	
	
	

	
	

}
