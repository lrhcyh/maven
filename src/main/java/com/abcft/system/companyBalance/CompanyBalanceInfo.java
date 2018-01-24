package com.abcft.system.companyBalance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

@Entity
@Table(name = "company_balance_sheet", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "balance_sheet_id" }) })
public class CompanyBalanceInfo extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -3785918722004858101L;
	
	private Long balance_sheet_id;
	private String stock_code;
	private String publish_date;
	private String end_date;
	private String account_date;
	private int report_period;
	private Double cash;
	private Double trading_fin_asset;
	private Double rec_note;
	private Double rec_account;
	private Double prepay;
	private Double other_rec_account;
	private Double rec_relate_account;
	private Double rec_interest;
	private Double rec_dividend;
	private Double stock;
	private Double consume_asset;
	private Double non_current_asset;
	private Double other_current_asset;
	private Double total_current_asset;
	private Double available_sale_asset;
	private Double held_investment;
	private Double long_rec_account;
	private Double long_equity_investment;
	private Double invest_house;
	private Double fix_asset;
	private Double building;
	private Double project_material;
	private Double fix_asset_dispose;
	private Double product_asset;
	private Double oil_asset;
	private Double intangible_asset;
	private Double develop_cost;
	private Double goodwill;
	private Double long_defer_cost;
	private Double tax_asset;
	private Double other_noncurrent_asset;
	private Double noncurrent_asset_total;
	private Double asset_total;
	private Double short_borrow;
	private Double trading_fin_borrow;
	private Double pay_note;
	private Double pay_account;
	private Double prepay_account;
	private Double pay_salary;
	private Double pay_tax;
	private Double pay_interest;
	private Double pay_dividend;
	private Double other_pay_account;
	private Double pay_relate_account;
	private Double non_current_borrow;
	private Double other_current_borrow;
	private Double current_borrow_total;
	private Double long_borrow;
	private Double pay_bonds;
	private Double long_pay_account;
	private Double term_pay_account;
	private Double pre_bonds;
	private Double tax_bonds;
	private Double other_noncurrent_bonds;
	private Double noncurrent_bonds_total;
	private Double bonds_total;
	private Double rec_capital;
	private Double capital_reserve;
	private Double earn_reserve;
	private Double reduce_share;
	private Double nopay_profit;
	private Double monority_holder_equity;
	private Double exchange_difference;
	private Double profit_adjust;
	private Double equity_total;
	private Double all_total;
	private Double parent_equity;
	private Double total_depre_amor;
	private int push_flag;
	
	@Id
	@Column(name = "balance_sheet_id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getBalance_sheet_id() {
		return balance_sheet_id;
	}
	public void setBalance_sheet_id(Long balance_sheet_id) {
		this.balance_sheet_id = balance_sheet_id;
	}
	
	@Column(name = "stock_code", unique = true, nullable = false, insertable = true, updatable = true, precision = 100, scale = 0)
	public String getStock_code() {
		return stock_code;
	}
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}
	
	@Column(name = "publish_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	
	@Column(name = "end_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	@Column(name = "account_date", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public String getAccount_date() {
		return account_date;
	}
	
	public void setAccount_date(String account_date) {
		this.account_date = account_date;
	}
	
	@Column(name = "report_period", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public int getReport_period() {
		return report_period;
	}
	public void setReport_period(int report_period) {
		this.report_period = report_period;
	}
	
	@Column(name = "cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	
	@Column(name = "trading_fin_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTrading_fin_asset() {
		return trading_fin_asset;
	}
	public void setTrading_fin_asset(Double trading_fin_asset) {
		this.trading_fin_asset = trading_fin_asset;
	}
	
	@Column(name = "rec_note", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_note() {
		return rec_note;
	}
	public void setRec_note(Double rec_note) {
		this.rec_note = rec_note;
	}
	
	@Column(name = "rec_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_account() {
		return rec_account;
	}
	public void setRec_account(Double rec_account) {
		this.rec_account = rec_account;
	}
	
	@Column(name = "prepay", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPrepay() {
		return prepay;
	}
	public void setPrepay(Double prepay) {
		this.prepay = prepay;
	}
	
	@Column(name = "other_rec_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther_rec_account() {
		return other_rec_account;
	}
	public void setOther_rec_account(Double other_rec_account) {
		this.other_rec_account = other_rec_account;
	}
	
	@Column(name = "rec_relate_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_relate_account() {
		return rec_relate_account;
	}
	public void setRec_relate_account(Double rec_relate_account) {
		this.rec_relate_account = rec_relate_account;
	}
	
	@Column(name = "rec_interest", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_interest() {
		return rec_interest;
	}
	public void setRec_interest(Double rec_interest) {
		this.rec_interest = rec_interest;
	}
	
	@Column(name = "rec_dividend", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_dividend() {
		return rec_dividend;
	}
	public void setRec_dividend(Double rec_dividend) {
		this.rec_dividend = rec_dividend;
	}
	
	@Column(name = "stock", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	
	@Column(name = "consume_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getConsume_asset() {
		return consume_asset;
	}
	public void setConsume_asset(Double consume_asset) {
		this.consume_asset = consume_asset;
	}
	
	@Column(name = "non_current_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNon_current_asset() {
		return non_current_asset;
	}
	public void setNon_current_asset(Double non_current_asset) {
		this.non_current_asset = non_current_asset;
	}
	
	@Column(name = "other_current_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther_current_asset() {
		return other_current_asset;
	}
	public void setOther_current_asset(Double other_current_asset) {
		this.other_current_asset = other_current_asset;
	}
	
	@Column(name = "total_current_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTotal_current_asset() {
		return total_current_asset;
	}
	public void setTotal_current_asset(Double total_current_asset) {
		this.total_current_asset = total_current_asset;
	}
	
	@Column(name = "available_sale_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getAvailable_sale_asset() {
		return available_sale_asset;
	}
	public void setAvailable_sale_asset(Double available_sale_asset) {
		this.available_sale_asset = available_sale_asset;
	}
	
	@Column(name = "held_investment", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getHeld_investment() {
		return held_investment;
	}
	public void setHeld_investment(Double held_investment) {
		this.held_investment = held_investment;
	}
	
	@Column(name = "long_rec_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLong_rec_account() {
		return long_rec_account;
	}
	public void setLong_rec_account(Double long_rec_account) {
		this.long_rec_account = long_rec_account;
	}
	
	@Column(name = "long_equity_investment", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLong_equity_investment() {
		return long_equity_investment;
	}
	public void setLong_equity_investment(Double long_equity_investment) {
		this.long_equity_investment = long_equity_investment;
	}
	
	@Column(name = "invest_house", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_house() {
		return invest_house;
	}
	public void setInvest_house(Double invest_house) {
		this.invest_house = invest_house;
	}
	
	@Column(name = "fix_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getFix_asset() {
		return fix_asset;
	}
	public void setFix_asset(Double fix_asset) {
		this.fix_asset = fix_asset;
	}
	
	@Column(name = "building", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBuilding() {
		return building;
	}
	public void setBuilding(Double building) {
		this.building = building;
	}
	
	@Column(name = "project_material", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getProject_material() {
		return project_material;
	}
	public void setProject_material(Double project_material) {
		this.project_material = project_material;
	}
	
	@Column(name = "fix_asset_dispose", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getFix_asset_dispose() {
		return fix_asset_dispose;
	}
	public void setFix_asset_dispose(Double fix_asset_dispose) {
		this.fix_asset_dispose = fix_asset_dispose;
	}
	
	@Column(name = "product_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getProduct_asset() {
		return product_asset;
	}
	public void setProduct_asset(Double product_asset) {
		this.product_asset = product_asset;
	}
	
	@Column(name = "oil_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOil_asset() {
		return oil_asset;
	}
	public void setOil_asset(Double oil_asset) {
		this.oil_asset = oil_asset;
	}
	
	@Column(name = "intangible_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getIntangible_asset() {
		return intangible_asset;
	}
	public void setIntangible_asset(Double intangible_asset) {
		this.intangible_asset = intangible_asset;
	}
	
	@Column(name = "develop_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getDevelop_cost() {
		return develop_cost;
	}
	public void setDevelop_cost(Double develop_cost) {
		this.develop_cost = develop_cost;
	}
	
	@Column(name = "goodwill", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getGoodwill() {
		return goodwill;
	}
	public void setGoodwill(Double goodwill) {
		this.goodwill = goodwill;
	}
	
	@Column(name = "long_defer_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLong_defer_cost() {
		return long_defer_cost;
	}
	public void setLong_defer_cost(Double long_defer_cost) {
		this.long_defer_cost = long_defer_cost;
	}
	
	@Column(name = "tax_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTax_asset() {
		return tax_asset;
	}
	public void setTax_asset(Double tax_asset) {
		this.tax_asset = tax_asset;
	}
	
	@Column(name = "other_noncurrent_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther_noncurrent_asset() {
		return other_noncurrent_asset;
	}
	public void setOther_noncurrent_asset(Double other_noncurrent_asset) {
		this.other_noncurrent_asset = other_noncurrent_asset;
	}
	
	@Column(name = "noncurrent_asset_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNoncurrent_asset_total() {
		return noncurrent_asset_total;
	}
	public void setNoncurrent_asset_total(Double noncurrent_asset_total) {
		this.noncurrent_asset_total = noncurrent_asset_total;
	}
	
	@Column(name = "asset_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getAsset_total() {
		return asset_total;
	}
	public void setAsset_total(Double asset_total) {
		this.asset_total = asset_total;
	}
	
	@Column(name = "short_borrow", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getShort_borrow() {
		return short_borrow;
	}
	public void setShort_borrow(Double short_borrow) {
		this.short_borrow = short_borrow;
	}
	
	@Column(name = "trading_fin_borrow", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTrading_fin_borrow() {
		return trading_fin_borrow;
	}
	public void setTrading_fin_borrow(Double trading_fin_borrow) {
		this.trading_fin_borrow = trading_fin_borrow;
	}
	
	@Column(name = "pay_note", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_note() {
		return pay_note;
	}
	public void setPay_note(Double pay_note) {
		this.pay_note = pay_note;
	}
	
	@Column(name = "pay_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_account() {
		return pay_account;
	}
	public void setPay_account(Double pay_account) {
		this.pay_account = pay_account;
	}
	
	@Column(name = "prepay_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPrepay_account() {
		return prepay_account;
	}
	public void setPrepay_account(Double prepay_account) {
		this.prepay_account = prepay_account;
	}
	
	@Column(name = "pay_salary", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_salary() {
		return pay_salary;
	}
	public void setPay_salary(Double pay_salary) {
		this.pay_salary = pay_salary;
	}
	
	@Column(name = "pay_tax", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_tax() {
		return pay_tax;
	}
	public void setPay_tax(Double pay_tax) {
		this.pay_tax = pay_tax;
	}
	
	@Column(name = "pay_interest", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_interest() {
		return pay_interest;
	}
	public void setPay_interest(Double pay_interest) {
		this.pay_interest = pay_interest;
	}
	
	@Column(name = "pay_dividend", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_dividend() {
		return pay_dividend;
	}
	public void setPay_dividend(Double pay_dividend) {
		this.pay_dividend = pay_dividend;
	}
	
	@Column(name = "other_pay_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther_pay_account() {
		return other_pay_account;
	}
	public void setOther_pay_account(Double other_pay_account) {
		this.other_pay_account = other_pay_account;
	}
	
	@Column(name = "pay_relate_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_relate_account() {
		return pay_relate_account;
	}
	public void setPay_relate_account(Double pay_relate_account) {
		this.pay_relate_account = pay_relate_account;
	}
	
	@Column(name = "non_current_borrow", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNon_current_borrow() {
		return non_current_borrow;
	}
	public void setNon_current_borrow(Double non_current_borrow) {
		this.non_current_borrow = non_current_borrow;
	}
	
	@Column(name = "other_current_borrow", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther_current_borrow() {
		return other_current_borrow;
	}
	public void setOther_current_borrow(Double other_current_borrow) {
		this.other_current_borrow = other_current_borrow;
	}
	
	@Column(name = "current_borrow_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCurrent_borrow_total() {
		return current_borrow_total;
	}
	public void setCurrent_borrow_total(Double current_borrow_total) {
		this.current_borrow_total = current_borrow_total;
	}
	
	@Column(name = "long_borrow", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLong_borrow() {
		return long_borrow;
	}
	public void setLong_borrow(Double long_borrow) {
		this.long_borrow = long_borrow;
	}
	
	@Column(name = "pay_bonds", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_bonds() {
		return pay_bonds;
	}
	public void setPay_bonds(Double pay_bonds) {
		this.pay_bonds = pay_bonds;
	}
	
	@Column(name = "long_pay_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLong_pay_account() {
		return long_pay_account;
	}
	public void setLong_pay_account(Double long_pay_account) {
		this.long_pay_account = long_pay_account;
	}
	
	@Column(name = "term_pay_account", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTerm_pay_account() {
		return term_pay_account;
	}
	public void setTerm_pay_account(Double term_pay_account) {
		this.term_pay_account = term_pay_account;
	}
	
	@Column(name = "pre_bonds", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPre_bonds() {
		return pre_bonds;
	}
	public void setPre_bonds(Double pre_bonds) {
		this.pre_bonds = pre_bonds;
	}
	
	@Column(name = "tax_bonds", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTax_bonds() {
		return tax_bonds;
	}
	public void setTax_bonds(Double tax_bonds) {
		this.tax_bonds = tax_bonds;
	}
	
	@Column(name = "other_noncurrent_bonds", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther_noncurrent_bonds() {
		return other_noncurrent_bonds;
	}
	public void setOther_noncurrent_bonds(Double other_noncurrent_bonds) {
		this.other_noncurrent_bonds = other_noncurrent_bonds;
	}
	
	@Column(name = "noncurrent_bonds_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNoncurrent_bonds_total() {
		return noncurrent_bonds_total;
	}
	public void setNoncurrent_bonds_total(Double noncurrent_bonds_total) {
		this.noncurrent_bonds_total = noncurrent_bonds_total;
	}
	
	@Column(name = "bonds_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBonds_total() {
		return bonds_total;
	}
	public void setBonds_total(Double bonds_total) {
		this.bonds_total = bonds_total;
	}
	
	@Column(name = "rec_capital", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_capital() {
		return rec_capital;
	}
	public void setRec_capital(Double rec_capital) {
		this.rec_capital = rec_capital;
	}
	
	@Column(name = "capital_reserve", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCapital_reserve() {
		return capital_reserve;
	}
	public void setCapital_reserve(Double capital_reserve) {
		this.capital_reserve = capital_reserve;
	}
	
	@Column(name = "earn_reserve", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getEarn_reserve() {
		return earn_reserve;
	}
	public void setEarn_reserve(Double earn_reserve) {
		this.earn_reserve = earn_reserve;
	}
	
	@Column(name = "reduce_share", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getReduce_share() {
		return reduce_share;
	}
	public void setReduce_share(Double reduce_share) {
		this.reduce_share = reduce_share;
	}
	
	@Column(name = "nopay_profit", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNopay_profit() {
		return nopay_profit;
	}
	public void setNopay_profit(Double nopay_profit) {
		this.nopay_profit = nopay_profit;
	}
	
	@Column(name = "monority_holder_equity", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getMonority_holder_equity() {
		return monority_holder_equity;
	}
	public void setMonority_holder_equity(Double monority_holder_equity) {
		this.monority_holder_equity = monority_holder_equity;
	}
	
	@Column(name = "exchange_difference", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getExchange_difference() {
		return exchange_difference;
	}
	public void setExchange_difference(Double exchange_difference) {
		this.exchange_difference = exchange_difference;
	}
	
	@Column(name = "profit_adjust", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getProfit_adjust() {
		return profit_adjust;
	}
	public void setProfit_adjust(Double profit_adjust) {
		this.profit_adjust = profit_adjust;
	}
	
	@Column(name = "equity_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getEquity_total() {
		return equity_total;
	}
	public void setEquity_total(Double equity_total) {
		this.equity_total = equity_total;
	}
	
	@Column(name = "all_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getAll_total() {
		return all_total;
	}
	public void setAll_total(Double all_total) {
		this.all_total = all_total;
	}
	
	@Column(name = "parent_equity", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getParent_equity() {
		return parent_equity;
	}
	public void setParent_equity(Double parent_equity) {
		this.parent_equity = parent_equity;
	}
	
	@Column(name = "total_depre_amor", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTotal_depre_amor() {
		return total_depre_amor;
	}
	public void setTotal_depre_amor(Double total_depre_amor) {
		this.total_depre_amor = total_depre_amor;
	}
	
	@Column(name = "push_flag", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public int getPush_flag() {
		return push_flag;
	}
	public void setPush_flag(int push_flag) {
		this.push_flag = push_flag;
	}
	
}
