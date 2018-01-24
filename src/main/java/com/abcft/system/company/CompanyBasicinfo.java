package com.abcft.system.company;

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
@Table(name = "company_basicinfo", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class CompanyBasicinfo extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 5865132672163539715L;

	private String id;

	private String stock_code;

	private String stock_category;

	// 随机数
	private String stock_name;

	private String stock_alias;

	private String company_name;

	private String company_ename;

	private String industry_name_csrc;

	private String industry_code_csrc;

	private String industry_code;

	private String industry_name;

	private String found_date;
	
	private Long register_capital;
	
	private String legal_man;
	
	private String stock_type;
	
	private String ipo_date;
	
	private String ipo_main_saler;
	
	private String concept;
	
	private String province;
	
	private String city;
	
	private String company_character;
	
	private String directors;
	
	private Long emp_sum;
	
	private String register_number;
	
	private String register_address;
	
	private String office_address;
	
	private String major_product_type;
	
	private String major_product_name;
	
	private String phone;
	
	private String zipcode;
	
	private String fax;
	
	private String website;
	
	private String email;
	
	private String link_main;
	
	private String company_brief;
	
	private String business;
	
	private String wind_code;
	
	private String closing_price;
	
	private String price_date;
	
	private String board_chair_men;
	
	private String register_addressCopy;
	
	private String count;
	
	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "stock_code", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getStock_code() {
		return stock_code;
	}

	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}

	@Column(name = "stock_category", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getStock_category() {
		return stock_category;
	}

	public void setStock_category(String stock_category) {
		this.stock_category = stock_category;
	}
	
	@Column(name = "stock_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getStock_name() {
		return stock_name;
	}

	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

	@Column(name = "stock_alias", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getStock_alias() {
		return stock_alias;
	}

	public void setStock_alias(String stock_alias) {
		this.stock_alias = stock_alias;
	}
	
	@Column(name = "company_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	@Column(name = "company_ename", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getCompany_ename() {
		return company_ename;
	}

	public void setCompany_ename(String company_ename) {
		this.company_ename = company_ename;
	}

	@Column(name = "industry_name_csrc", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getIndustry_name_csrc() {
		return industry_name_csrc;
	}

	public void setIndustry_name_csrc(String industry_name_csrc) {
		this.industry_name_csrc = industry_name_csrc;
	}
	
	@Column(name = "industry_code_csrc", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getIndustry_code_csrc() {
		return industry_code_csrc;
	}

	public void setIndustry_code_csrc(String industry_code_csrc) {
		this.industry_code_csrc = industry_code_csrc;
	}
	
	@Column(name = "industry_code", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getIndustry_code() {
		return industry_code;
	}

	public void setIndustry_code(String industry_code) {
		this.industry_code = industry_code;
	}

	@Column(name = "industry_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getIndustry_name() {
		return industry_name;
	}

	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}
	
	@Column(name = "found_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getFound_date() {
		return found_date;
	}

	public void setFound_date(String found_date) {
		this.found_date = found_date;
	} // 角色名称

	@Column(name = "register_capital", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getRegister_capital() {
		return register_capital;
	}

	public void setRegister_capital(Long register_capital) {
		this.register_capital = register_capital;
	}

	@Column(name = "legal_man", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getLegal_man() {
		return legal_man;
	}

	public void setLegal_man(String legal_man) {
		this.legal_man = legal_man;
	}

	@Column(name = "stock_type", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getStock_type() {
		return stock_type;
	}

	public void setStock_type(String stock_type) {
		this.stock_type = stock_type;
	}

	@Column(name = "ipo_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 0, scale = 0)
	public String getIpo_date() {
		return ipo_date;
	}

	public void setIpo_date(String ipo_date) {
		this.ipo_date = ipo_date;
	}

	@Column(name = "ipo_main_saler", unique = true, nullable = false, insertable = true, updatable = true, precision = 50, scale = 0)
	public String getIpo_main_saler() {
		return ipo_main_saler;
	}

	public void setIpo_main_saler(String ipo_main_saler) {
		this.ipo_main_saler = ipo_main_saler;
	}

	@Column(name = "concept", unique = true, nullable = false, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	@Column(name = "province", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", unique = true, nullable = false, insertable = true, updatable = true, precision = 30, scale = 0)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "company_character", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getCompany_character() {
		return company_character;
	}

	public void setCompany_character(String company_character) {
		this.company_character = company_character;
	}

	@Column(name = "directors", unique = true, nullable = false, insertable = true, updatable = true, precision = 300, scale = 0)
	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	@Column(name = "emp_sum", unique = true, nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	public Long getEmp_sum() {
		return emp_sum;
	}

	public void setEmp_sum(Long emp_sum) {
		this.emp_sum = emp_sum;
	}

	@Column(name = "register_number", unique = true, nullable = false, insertable = true, updatable = true, precision = 50, scale = 0)
	public String getRegister_number() {
		return register_number;
	}

	public void setRegister_number(String register_number) {
		this.register_number = register_number;
	}

	@Column(name = "register_address", unique = true, nullable = false, insertable = true, updatable = true, precision = 200, scale = 0)
	public String getRegister_address() {
		return register_address;
	}

	public void setRegister_address(String register_address) {
		this.register_address = register_address;
	}

	@Column(name = "office_address", unique = true, nullable = false, insertable = true, updatable = true, precision = 200, scale = 0)
	public String getOffice_address() {
		return office_address;
	}

	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}

	@Column(name = "major_product_type", unique = true, nullable = false, insertable = true, updatable = true, precision = 0, scale = 0)
	public String getMajor_product_type() {
		return major_product_type;
	}

	public void setMajor_product_type(String major_product_type) {
		this.major_product_type = major_product_type;
	}

	@Column(name = "major_product_name", unique = true, nullable = false, insertable = true, updatable = true, precision = 0, scale = 0)
	public String getMajor_product_name() {
		return major_product_name;
	}

	public void setMajor_product_name(String major_product_name) {
		this.major_product_name = major_product_name;
	}

	@Column(name = "phone", unique = true, nullable = false, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "zipcode", unique = true, nullable = false, insertable = true, updatable = true, precision = 50, scale = 0)
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "fax", unique = true, nullable = false, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "website", unique = true, nullable = false, insertable = true, updatable = true, precision = 60, scale = 0)
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "email", unique = true, nullable = false, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "link_main", unique = true, nullable = true, insertable = true, updatable = true, precision = 50, scale = 0)
	public String getLink_main() {
		return link_main;
	}

	public void setLink_main(String link_main) {
		this.link_main = link_main;
	}

	@Column(name = "company_brief", unique = true, nullable = false, insertable = true, updatable = true, precision = 0, scale = 0)
	public String getCompany_brief() {
		return company_brief;
	}

	public void setCompany_brief(String company_brief) {
		this.company_brief = company_brief;
	}

	@Column(name = "business", unique = true, nullable = false, insertable = true, updatable = true, precision = 0, scale = 0)
	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	@Column(name = "wind_code", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getWind_code() {
		return wind_code;
	}

	public void setWind_code(String wind_code) {
		this.wind_code = wind_code;
	}

	@Column(name = "closing_price", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public String getClosing_price() {
		return closing_price;
	}

	public void setClosing_price(String closing_price) {
		this.closing_price = closing_price;
	}

	@Column(name = "price_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getPrice_date() {
		return price_date;
	}

	public void setPrice_date(String price_date) {
		this.price_date = price_date;
	}

	@Column(name = "board_chair_men", unique = true, nullable = false, insertable = true, updatable = true, precision = 50, scale = 0)
	public String getBoard_chair_men() {
		return board_chair_men;
	}

	public void setBoard_chair_men(String board_chair_men) {
		this.board_chair_men = board_chair_men;
	}

	@Column(name = "register_addressCopy", unique = true, nullable = false, insertable = true, updatable = true, precision = 200, scale = 0)
	public String getRegister_addressCopy() {
		return register_addressCopy;
	}

	public void setRegister_addressCopy(String register_addressCopy) {
		this.register_addressCopy = register_addressCopy;
	}

	@Column(name = "count", unique = true, nullable = true, insertable = true, updatable = true, precision = 200, scale = 0)
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
}
