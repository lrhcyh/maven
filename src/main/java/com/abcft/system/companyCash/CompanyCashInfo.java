package com.abcft.system.companyCash;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

@Entity
@Table(name = "company_cash_sheet", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "cash_sheet_id" }) })
public class CompanyCashInfo extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long cash_sheet_id;
	private String stock_code;
	private String publish_date;
	private String end_date;
	private String account_date;
	private int report_period;
	private Double sale_cash;
	private Double tax_return;
	private Double rec_other_cash;
	private Double bussiness_cash_total;
	private Double buy_for_cash;
	private Double pay_emp_cash;
	private Double pay_tax;
	private Double pay_other_cash;
	private Double bussiness_cash_output;
	private Double bussiness_cash_netvalue;
	private Double rec_invest_cash;
	private Double invest_rec_cash;
	private Double dispose_asset_netvalue;
	private Double dispose_other_netvalue;
	private Double rec_otherinvest_cash;
	private Double invest_cash_total;
	private Double buy_asset_cash;
	private Double invest_pay_cash;
	private Double loan_net_addvalue;
	private Double rec_othercompany_cash;
	private Double pay_otherinvest_cash;
	private Double invest_cash_output;
	private Double invest_cash_netvalue;
	private Double rec_invest_reccash;
	private Double rec_borrow_cash;
	private Double publish_rec_cash;
	private Double rec_other_relatecash;
	private Double borrow_cash_total;
	private Double pay_debet_cash;
	private Double interest_pay_cash;
	private Double pay_other_relatecash;
	private Double borrow_cash_outtotal;
	private Double borrow_cash_netvalue;
	private Double rate_to_cash;
	private Double other_to_cash;
	private Double cash_to_netadd;
	private Double origin_cash;
	private Double last_cash;
	private Double addition;
	private Double netvalue_to_cash;
	private Double netvalue;
	private Double plus_asset_loss;
	private Double asset_discount;
	private Double intangible_asset_discount;
	private Double long_cost_discount;
	private Double asset_loss;
	private Double fix_asset_loss;
	private Double value_change_loss;
	private Double fin_cost;
	private Double invest_loss;
	private Double tax_reduce;
	private Double debt_reduce;
	private Double stock_reduce;
	private Double rec_project_reduce;
	private Double pay_project_add;
	private Double other;
	private Double business_cash_netvalue;
	private Double non_cash_netvalue;
	private Double debt_to_capital;
	private Double debt_one_year;
	private Double cash_to_asset;
	private Double cash_change;
	private Double last_cash_value;
	private Double reduce_origin_cash;
	private Double plus_last_cash;
	private Double reduce_origin_value;
	private Double plus_other_cash;
	private Double cash_to_netvalue;
	private Double custom_to_netvalue;
	private Double borrow_netvalue;
	private Double borrow_other_netvalue;
	private Double rec_insurance_cash;
	private Double rec_insurance_netvalue;
	private Double invest_netvalue;
	private Double dispose_the_cash;
	private Double rec_interest_cash;
	private Double cash_netvalue;
	private Double return_cash_netvalue;
	private Double custom_netvalue;
	private Double bank_cash_netvalue;
	private Double pay_insurance_cash;
	private Double pay_interest_cash;
	private Double pay_profit_cash;
	private Double  cash_for_holder;
	private Double profit_for_holder;
	private Double house_disount;
	private int push_flag;
	
	@Id
	@Column(name = "cash_sheet_id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getCash_sheet_id() {
		return cash_sheet_id;
	}
	public void setCash_sheet_id(Long cash_sheet_id) {
		this.cash_sheet_id = cash_sheet_id;
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
	
	@Column(name = "sale_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getSale_cash() {
		return sale_cash;
	}
	public void setSale_cash(Double sale_cash) {
		this.sale_cash = sale_cash;
	}
	
	@Column(name = "tax_return", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTax_return() {
		return tax_return;
	}
	public void setTax_return(Double tax_return) {
		this.tax_return = tax_return;
	}
	
	@Column(name = "rec_other_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_other_cash() {
		return rec_other_cash;
	}
	public void setRec_other_cash(Double rec_other_cash) {
		this.rec_other_cash = rec_other_cash;
	}
	
	@Column(name = "bussiness_cash_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBussiness_cash_total() {
		return bussiness_cash_total;
	}
	public void setBussiness_cash_total(Double bussiness_cash_total) {
		this.bussiness_cash_total = bussiness_cash_total;
	}
	
	@Column(name = "buy_for_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBuy_for_cash() {
		return buy_for_cash;
	}
	public void setBuy_for_cash(Double buy_for_cash) {
		this.buy_for_cash = buy_for_cash;
	}
	
	@Column(name = "pay_emp_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_emp_cash() {
		return pay_emp_cash;
	}
	public void setPay_emp_cash(Double pay_emp_cash) {
		this.pay_emp_cash = pay_emp_cash;
	}
	
	@Column(name = "pay_tax", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_tax() {
		return pay_tax;
	}
	public void setPay_tax(Double pay_tax) {
		this.pay_tax = pay_tax;
	}
	
	@Column(name = "pay_other_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_other_cash() {
		return pay_other_cash;
	}
	public void setPay_other_cash(Double pay_other_cash) {
		this.pay_other_cash = pay_other_cash;
	}
	
	@Column(name = "bussiness_cash_output", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBussiness_cash_output() {
		return bussiness_cash_output;
	}
	public void setBussiness_cash_output(Double bussiness_cash_output) {
		this.bussiness_cash_output = bussiness_cash_output;
	}
	
	@Column(name = "bussiness_cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBussiness_cash_netvalue() {
		return bussiness_cash_netvalue;
	}
	public void setBussiness_cash_netvalue(Double bussiness_cash_netvalue) {
		this.bussiness_cash_netvalue = bussiness_cash_netvalue;
	}
	
	@Column(name = "rec_invest_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_invest_cash() {
		return rec_invest_cash;
	}
	public void setRec_invest_cash(Double rec_invest_cash) {
		this.rec_invest_cash = rec_invest_cash;
	}
	
	@Column(name = "invest_rec_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_rec_cash() {
		return invest_rec_cash;
	}
	public void setInvest_rec_cash(Double invest_rec_cash) {
		this.invest_rec_cash = invest_rec_cash;
	}
	
	@Column(name = "dispose_asset_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getDispose_asset_netvalue() {
		return dispose_asset_netvalue;
	}
	public void setDispose_asset_netvalue(Double dispose_asset_netvalue) {
		this.dispose_asset_netvalue = dispose_asset_netvalue;
	}
	
	@Column(name = "dispose_other_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getDispose_other_netvalue() {
		return dispose_other_netvalue;
	}
	public void setDispose_other_netvalue(Double dispose_other_netvalue) {
		this.dispose_other_netvalue = dispose_other_netvalue;
	}
	
	@Column(name = "rec_otherinvest_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_otherinvest_cash() {
		return rec_otherinvest_cash;
	}
	public void setRec_otherinvest_cash(Double rec_otherinvest_cash) {
		this.rec_otherinvest_cash = rec_otherinvest_cash;
	}
	
	@Column(name = "invest_cash_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_cash_total() {
		return invest_cash_total;
	}
	public void setInvest_cash_total(Double invest_cash_total) {
		this.invest_cash_total = invest_cash_total;
	}
	
	@Column(name = "buy_asset_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBuy_asset_cash() {
		return buy_asset_cash;
	}
	public void setBuy_asset_cash(Double buy_asset_cash) {
		this.buy_asset_cash = buy_asset_cash;
	}
	
	@Column(name = "invest_pay_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_pay_cash() {
		return invest_pay_cash;
	}
	public void setInvest_pay_cash(Double invest_pay_cash) {
		this.invest_pay_cash = invest_pay_cash;
	}
	
	@Column(name = "loan_net_addvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLoan_net_addvalue() {
		return loan_net_addvalue;
	}
	public void setLoan_net_addvalue(Double loan_net_addvalue) {
		this.loan_net_addvalue = loan_net_addvalue;
	}
	
	@Column(name = "rec_othercompany_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_othercompany_cash() {
		return rec_othercompany_cash;
	}
	public void setRec_othercompany_cash(Double rec_othercompany_cash) {
		this.rec_othercompany_cash = rec_othercompany_cash;
	}
	
	@Column(name = "pay_otherinvest_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_otherinvest_cash() {
		return pay_otherinvest_cash;
	}
	public void setPay_otherinvest_cash(Double pay_otherinvest_cash) {
		this.pay_otherinvest_cash = pay_otherinvest_cash;
	}
	
	@Column(name = "invest_cash_output", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_cash_output() {
		return invest_cash_output;
	}
	public void setInvest_cash_output(Double invest_cash_output) {
		this.invest_cash_output = invest_cash_output;
	}
	
	@Column(name = "invest_cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_cash_netvalue() {
		return invest_cash_netvalue;
	}
	public void setInvest_cash_netvalue(Double invest_cash_netvalue) {
		this.invest_cash_netvalue = invest_cash_netvalue;
	}
	
	@Column(name = "rec_invest_reccash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_invest_reccash() {
		return rec_invest_reccash;
	}
	public void setRec_invest_reccash(Double rec_invest_reccash) {
		this.rec_invest_reccash = rec_invest_reccash;
	}
	
	@Column(name = "rec_borrow_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_borrow_cash() {
		return rec_borrow_cash;
	}
	public void setRec_borrow_cash(Double rec_borrow_cash) {
		this.rec_borrow_cash = rec_borrow_cash;
	}
	
	@Column(name = "publish_rec_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPublish_rec_cash() {
		return publish_rec_cash;
	}
	public void setPublish_rec_cash(Double publish_rec_cash) {
		this.publish_rec_cash = publish_rec_cash;
	}
	
	@Column(name = "rec_other_relatecash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_other_relatecash() {
		return rec_other_relatecash;
	}
	public void setRec_other_relatecash(Double rec_other_relatecash) {
		this.rec_other_relatecash = rec_other_relatecash;
	}
	
	@Column(name = "borrow_cash_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBorrow_cash_total() {
		return borrow_cash_total;
	}
	public void setBorrow_cash_total(Double borrow_cash_total) {
		this.borrow_cash_total = borrow_cash_total;
	}
	
	@Column(name = "pay_debet_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_debet_cash() {
		return pay_debet_cash;
	}
	public void setPay_debet_cash(Double pay_debet_cash) {
		this.pay_debet_cash = pay_debet_cash;
	}
	
	@Column(name = "interest_pay_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInterest_pay_cash() {
		return interest_pay_cash;
	}
	public void setInterest_pay_cash(Double interest_pay_cash) {
		this.interest_pay_cash = interest_pay_cash;
	}
	
	@Column(name = "pay_other_relatecash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_other_relatecash() {
		return pay_other_relatecash;
	}
	public void setPay_other_relatecash(Double pay_other_relatecash) {
		this.pay_other_relatecash = pay_other_relatecash;
	}
	
	@Column(name = "borrow_cash_outtotal", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBorrow_cash_outtotal() {
		return borrow_cash_outtotal;
	}
	public void setBorrow_cash_outtotal(Double borrow_cash_outtotal) {
		this.borrow_cash_outtotal = borrow_cash_outtotal;
	}
	
	@Column(name = "borrow_cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBorrow_cash_netvalue() {
		return borrow_cash_netvalue;
	}
	public void setBorrow_cash_netvalue(Double borrow_cash_netvalue) {
		this.borrow_cash_netvalue = borrow_cash_netvalue;
	}
	
	@Column(name = "rate_to_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRate_to_cash() {
		return rate_to_cash;
	}
	public void setRate_to_cash(Double rate_to_cash) {
		this.rate_to_cash = rate_to_cash;
	}
	
	@Column(name = "other_to_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther_to_cash() {
		return other_to_cash;
	}
	public void setOther_to_cash(Double other_to_cash) {
		this.other_to_cash = other_to_cash;
	}
	
	@Column(name = "cash_to_netadd", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCash_to_netadd() {
		return cash_to_netadd;
	}
	public void setCash_to_netadd(Double cash_to_netadd) {
		this.cash_to_netadd = cash_to_netadd;
	}
	
	@Column(name = "origin_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOrigin_cash() {
		return origin_cash;
	}
	public void setOrigin_cash(Double origin_cash) {
		this.origin_cash = origin_cash;
	}
	
	@Column(name = "last_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLast_cash() {
		return last_cash;
	}
	public void setLast_cash(Double last_cash) {
		this.last_cash = last_cash;
	}
	
	@Column(name = "addition", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getAddition() {
		return addition;
	}
	public void setAddition(Double addition) {
		this.addition = addition;
	}
	
	@Column(name = "netvalue_to_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNetvalue_to_cash() {
		return netvalue_to_cash;
	}
	public void setNetvalue_to_cash(Double netvalue_to_cash) {
		this.netvalue_to_cash = netvalue_to_cash;
	}
	
	@Column(name = "netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNetvalue() {
		return netvalue;
	}
	public void setNetvalue(Double netvalue) {
		this.netvalue = netvalue;
	}
	
	@Column(name = "plus_asset_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPlus_asset_loss() {
		return plus_asset_loss;
	}
	public void setPlus_asset_loss(Double plus_asset_loss) {
		this.plus_asset_loss = plus_asset_loss;
	}
	
	@Column(name = "asset_discount", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getAsset_discount() {
		return asset_discount;
	}
	public void setAsset_discount(Double asset_discount) {
		this.asset_discount = asset_discount;
	}
	
	@Column(name = "intangible_asset_discount", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getIntangible_asset_discount() {
		return intangible_asset_discount;
	}
	public void setIntangible_asset_discount(Double intangible_asset_discount) {
		this.intangible_asset_discount = intangible_asset_discount;
	}
	
	@Column(name = "long_cost_discount", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLong_cost_discount() {
		return long_cost_discount;
	}
	public void setLong_cost_discount(Double long_cost_discount) {
		this.long_cost_discount = long_cost_discount;
	}
	
	@Column(name = "asset_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getAsset_loss() {
		return asset_loss;
	}
	public void setAsset_loss(Double asset_loss) {
		this.asset_loss = asset_loss;
	}
	
	@Column(name = "fix_asset_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getFix_asset_loss() {
		return fix_asset_loss;
	}
	public void setFix_asset_loss(Double fix_asset_loss) {
		this.fix_asset_loss = fix_asset_loss;
	}
	
	@Column(name = "value_change_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getValue_change_loss() {
		return value_change_loss;
	}
	public void setValue_change_loss(Double value_change_loss) {
		this.value_change_loss = value_change_loss;
	}
	
	@Column(name = "fin_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getFin_cost() {
		return fin_cost;
	}
	public void setFin_cost(Double fin_cost) {
		this.fin_cost = fin_cost;
	}
	
	@Column(name = "invest_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_loss() {
		return invest_loss;
	}
	public void setInvest_loss(Double invest_loss) {
		this.invest_loss = invest_loss;
	}
	
	@Column(name = "tax_reduce", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getTax_reduce() {
		return tax_reduce;
	}
	public void setTax_reduce(Double tax_reduce) {
		this.tax_reduce = tax_reduce;
	}
	
	@Column(name = "debt_reduce", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getDebt_reduce() {
		return debt_reduce;
	}
	public void setDebt_reduce(Double debt_reduce) {
		this.debt_reduce = debt_reduce;
	}
	
	@Column(name = "stock_reduce", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getStock_reduce() {
		return stock_reduce;
	}
	public void setStock_reduce(Double stock_reduce) {
		this.stock_reduce = stock_reduce;
	}
	
	@Column(name = "rec_project_reduce", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_project_reduce() {
		return rec_project_reduce;
	}
	public void setRec_project_reduce(Double rec_project_reduce) {
		this.rec_project_reduce = rec_project_reduce;
	}
	
	@Column(name = "pay_project_add", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_project_add() {
		return pay_project_add;
	}
	public void setPay_project_add(Double pay_project_add) {
		this.pay_project_add = pay_project_add;
	}
	
	@Column(name = "other", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
		this.other = other;
	}
	
	@Column(name = "business_cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBusiness_cash_netvalue() {
		return business_cash_netvalue;
	}
	public void setBusiness_cash_netvalue(Double business_cash_netvalue) {
		this.business_cash_netvalue = business_cash_netvalue;
	}
	
	@Column(name = "non_cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getNon_cash_netvalue() {
		return non_cash_netvalue;
	}
	public void setNon_cash_netvalue(Double non_cash_netvalue) {
		this.non_cash_netvalue = non_cash_netvalue;
	}
	
	@Column(name = "debt_to_capital", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getDebt_to_capital() {
		return debt_to_capital;
	}
	public void setDebt_to_capital(Double debt_to_capital) {
		this.debt_to_capital = debt_to_capital;
	}
	
	@Column(name = "debt_one_year", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getDebt_one_year() {
		return debt_one_year;
	}
	public void setDebt_one_year(Double debt_one_year) {
		this.debt_one_year = debt_one_year;
	}
	
	@Column(name = "cash_to_asset", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCash_to_asset() {
		return cash_to_asset;
	}
	public void setCash_to_asset(Double cash_to_asset) {
		this.cash_to_asset = cash_to_asset;
	}
	
	@Column(name = "cash_change", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCash_change() {
		return cash_change;
	}
	public void setCash_change(Double cash_change) {
		this.cash_change = cash_change;
	}
	
	@Column(name = "last_cash_value", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getLast_cash_value() {
		return last_cash_value;
	}
	public void setLast_cash_value(Double last_cash_value) {
		this.last_cash_value = last_cash_value;
	}
	
	@Column(name = "reduce_origin_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getReduce_origin_cash() {
		return reduce_origin_cash;
	}
	public void setReduce_origin_cash(Double reduce_origin_cash) {
		this.reduce_origin_cash = reduce_origin_cash;
	}
	
	@Column(name = "plus_last_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPlus_last_cash() {
		return plus_last_cash;
	}
	public void setPlus_last_cash(Double plus_last_cash) {
		this.plus_last_cash = plus_last_cash;
	}
	
	@Column(name = "reduce_origin_value", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getReduce_origin_value() {
		return reduce_origin_value;
	}
	public void setReduce_origin_value(Double reduce_origin_value) {
		this.reduce_origin_value = reduce_origin_value;
	}
	
	@Column(name = "plus_other_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPlus_other_cash() {
		return plus_other_cash;
	}
	public void setPlus_other_cash(Double plus_other_cash) {
		this.plus_other_cash = plus_other_cash;
	}
	
	@Column(name = "cash_to_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCash_to_netvalue() {
		return cash_to_netvalue;
	}
	public void setCash_to_netvalue(Double cash_to_netvalue) {
		this.cash_to_netvalue = cash_to_netvalue;
	}
	
	@Column(name = "custom_to_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCustom_to_netvalue() {
		return custom_to_netvalue;
	}
	public void setCustom_to_netvalue(Double custom_to_netvalue) {
		this.custom_to_netvalue = custom_to_netvalue;
	}
	
	@Column(name = "borrow_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBorrow_netvalue() {
		return borrow_netvalue;
	}
	public void setBorrow_netvalue(Double borrow_netvalue) {
		this.borrow_netvalue = borrow_netvalue;
	}
	
	@Column(name = "borrow_other_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBorrow_other_netvalue() {
		return borrow_other_netvalue;
	}
	public void setBorrow_other_netvalue(Double borrow_other_netvalue) {
		this.borrow_other_netvalue = borrow_other_netvalue;
	}
	
	@Column(name = "rec_insurance_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_insurance_cash() {
		return rec_insurance_cash;
	}
	public void setRec_insurance_cash(Double rec_insurance_cash) {
		this.rec_insurance_cash = rec_insurance_cash;
	}
	
	@Column(name = "rec_insurance_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_insurance_netvalue() {
		return rec_insurance_netvalue;
	}
	public void setRec_insurance_netvalue(Double rec_insurance_netvalue) {
		this.rec_insurance_netvalue = rec_insurance_netvalue;
	}
	
	@Column(name = "invest_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getInvest_netvalue() {
		return invest_netvalue;
	}
	public void setInvest_netvalue(Double invest_netvalue) {
		this.invest_netvalue = invest_netvalue;
	}
	
	@Column(name = "dispose_the_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getDispose_the_cash() {
		return dispose_the_cash;
	}
	public void setDispose_the_cash(Double dispose_the_cash) {
		this.dispose_the_cash = dispose_the_cash;
	}
	
	@Column(name = "rec_interest_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getRec_interest_cash() {
		return rec_interest_cash;
	}
	public void setRec_interest_cash(Double rec_interest_cash) {
		this.rec_interest_cash = rec_interest_cash;
	}
	
	@Column(name = "cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCash_netvalue() {
		return cash_netvalue;
	}
	public void setCash_netvalue(Double cash_netvalue) {
		this.cash_netvalue = cash_netvalue;
	}
	
	@Column(name = "return_cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getReturn_cash_netvalue() {
		return return_cash_netvalue;
	}
	public void setReturn_cash_netvalue(Double return_cash_netvalue) {
		this.return_cash_netvalue = return_cash_netvalue;
	}
	
	@Column(name = "custom_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCustom_netvalue() {
		return custom_netvalue;
	}
	public void setCustom_netvalue(Double custom_netvalue) {
		this.custom_netvalue = custom_netvalue;
	}
	
	@Column(name = "bank_cash_netvalue", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getBank_cash_netvalue() {
		return bank_cash_netvalue;
	}
	public void setBank_cash_netvalue(Double bank_cash_netvalue) {
		this.bank_cash_netvalue = bank_cash_netvalue;
	}
	
	@Column(name = "pay_insurance_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_insurance_cash() {
		return pay_insurance_cash;
	}
	public void setPay_insurance_cash(Double pay_insurance_cash) {
		this.pay_insurance_cash = pay_insurance_cash;
	}
	
	@Column(name = "pay_interest_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_interest_cash() {
		return pay_interest_cash;
	}
	public void setPay_interest_cash(Double pay_interest_cash) {
		this.pay_interest_cash = pay_interest_cash;
	}
	
	@Column(name = "pay_profit_cash", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getPay_profit_cash() {
		return pay_profit_cash;
	}
	public void setPay_profit_cash(Double pay_profit_cash) {
		this.pay_profit_cash = pay_profit_cash;
	}
	
	@Column(name = "cash_for_holder", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getCash_for_holder() {
		return cash_for_holder;
	}
	public void setCash_for_holder(Double cash_for_holder) {
		this.cash_for_holder = cash_for_holder;
	}
	
	@Column(name = "profit_for_holder", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getProfit_for_holder() {
		return profit_for_holder;
	}
	public void setProfit_for_holder(Double profit_for_holder) {
		this.profit_for_holder = profit_for_holder;
	}
	
	@Column(name = "house_disount", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public Double getHouse_disount() {
		return house_disount;
	}
	public void setHouse_disount(Double house_disount) {
		this.house_disount = house_disount;
	}
	
	@Column(name = "push_flag", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 2)
	public int getPush_flag() {
		return push_flag;
	}
	public void setPush_flag(int push_flag) {
		this.push_flag = push_flag;
	}
	
}
