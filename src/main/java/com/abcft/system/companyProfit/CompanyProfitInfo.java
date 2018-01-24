package com.abcft.system.companyProfit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

@Entity
@Table(name = "company_profit_sheet", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "profit_sheet_id" }) })
public class CompanyProfitInfo extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = -3785918722004858101L;

	private Long profit_sheet_id;
	
	private String stock_code;
	
	private String publish_date;
	
	private String end_date;
	
	private String account_date;
	
	private Long report_period;
	
	private String overall_income;
	
	private String main_income;
	
	private String overall_cost;
	
	private String main_cost;
	
	private String tax;
	
	private String sale_cost;
	
	private String manage_cost;
	
	private String other_cost;
	
	private String fin_cost;
	
	private String asset_loss;
	
	private String plus_change_income;
	
	private String invest_income;
	
	private String relate_invest_income;
	
	private String gain_loss_income;
	
	private String other_subject;
	
	private String overall_profit;
	
	private String plus_subsidy_income;
	
	private String addition_income;
	
	private String reduce_addition_cost;
	
	private String asset_dispose_loss;
	
	private String plus_profit_subject;
	
	private String profit_total;
	
	private String reduce_tax;
	
	private String plus_netprofit_subject;
	
	private String netprofit;
	
	private String parent_netprofit;
	
	private String minority_loss;
	
	private String perstock_income;
	
	private String basic_perstock_income;
	
	private String reduce_perstock_income;
	
	private String other_all_income;
	
	private String all_income_total;
	
	private String income_for_parent;
	
	private String income_for_holder;
	
	private String interest_income;
	
	private String earn_insurance;
	
	private String commission_income;
	
	private String interest_cost;
	
	private String commission_cost;
	
	private String canel_insurance_money;
	
	private String pay_netprofit;
	
	private String insurance_netprofit;
	
	private String insurance_cost;
	
	private String reduce_insurance_cost;
	
	private String interest_dispose;
	
	private int push_flag;
	
	private String main_costCopy;

	@Id
	@Column(name = "profit_sheet_id", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public Long getProfit_sheet_id() {
		return profit_sheet_id;
	}

	public void setProfit_sheet_id(Long profit_sheet_id) {
		this.profit_sheet_id = profit_sheet_id;
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
	public Long getReport_period() {
		return report_period;
	}

	public void setReport_period(Long report_period) {
		this.report_period = report_period;
	}

	@Column(name = "overall_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getOverall_income() {
		return overall_income;
	}

	public void setOverall_income(String overall_income) {
		this.overall_income = overall_income;
	}

	@Column(name = "main_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getMain_income() {
		return main_income;
	}

	public void setMain_income(String main_income) {
		this.main_income = main_income;
	}

	@Column(name = "overall_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getOverall_cost() {
		return overall_cost;
	}

	public void setOverall_cost(String overall_cost) {
		this.overall_cost = overall_cost;
	}

	@Column(name = "main_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getMain_cost() {
		return main_cost;
	}

	public void setMain_cost(String main_cost) {
		this.main_cost = main_cost;
	}

	@Column(name = "tax", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	@Column(name = "sale_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getSale_cost() {
		return sale_cost;
	}

	public void setSale_cost(String sale_cost) {
		this.sale_cost = sale_cost;
	}

	@Column(name = "manage_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getManage_cost() {
		return manage_cost;
	}

	public void setManage_cost(String manage_cost) {
		this.manage_cost = manage_cost;
	}

	@Column(name = "other_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getOther_cost() {
		return other_cost;
	}

	public void setOther_cost(String other_cost) {
		this.other_cost = other_cost;
	}

	@Column(name = "fin_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getFin_cost() {
		return fin_cost;
	}

	public void setFin_cost(String fin_cost) {
		this.fin_cost = fin_cost;
	}

	@Column(name = "asset_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getAsset_loss() {
		return asset_loss;
	}

	public void setAsset_loss(String asset_loss) {
		this.asset_loss = asset_loss;
	}

	@Column(name = "plus_change_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getPlus_change_income() {
		return plus_change_income;
	}

	public void setPlus_change_income(String plus_change_income) {
		this.plus_change_income = plus_change_income;
	}

	@Column(name = "invest_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getInvest_income() {
		return invest_income;
	}

	public void setInvest_income(String invest_income) {
		this.invest_income = invest_income;
	}

	@Column(name = "relate_invest_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getRelate_invest_income() {
		return relate_invest_income;
	}

	public void setRelate_invest_income(String relate_invest_income) {
		this.relate_invest_income = relate_invest_income;
	}

	@Column(name = "gain_loss_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getGain_loss_income() {
		return gain_loss_income;
	}

	public void setGain_loss_income(String gain_loss_income) {
		this.gain_loss_income = gain_loss_income;
	}

	@Column(name = "other_subject", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getOther_subject() {
		return other_subject;
	}

	public void setOther_subject(String other_subject) {
		this.other_subject = other_subject;
	}

	@Column(name = "overall_profit", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getOverall_profit() {
		return overall_profit;
	}

	public void setOverall_profit(String overall_profit) {
		this.overall_profit = overall_profit;
	}

	@Column(name = "plus_subsidy_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getPlus_subsidy_income() {
		return plus_subsidy_income;
	}

	public void setPlus_subsidy_income(String plus_subsidy_income) {
		this.plus_subsidy_income = plus_subsidy_income;
	}

	@Column(name = "addition_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getAddition_income() {
		return addition_income;
	}

	public void setAddition_income(String addition_income) {
		this.addition_income = addition_income;
	}

	@Column(name = "reduce_addition_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getReduce_addition_cost() {
		return reduce_addition_cost;
	}

	public void setReduce_addition_cost(String reduce_addition_cost) {
		this.reduce_addition_cost = reduce_addition_cost;
	}

	@Column(name = "asset_dispose_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getAsset_dispose_loss() {
		return asset_dispose_loss;
	}

	public void setAsset_dispose_loss(String asset_dispose_loss) {
		this.asset_dispose_loss = asset_dispose_loss;
	}

	@Column(name = "plus_profit_subject", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getPlus_profit_subject() {
		return plus_profit_subject;
	}

	public void setPlus_profit_subject(String plus_profit_subject) {
		this.plus_profit_subject = plus_profit_subject;
	}

	@Column(name = "profit_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getProfit_total() {
		return profit_total;
	}

	public void setProfit_total(String profit_total) {
		this.profit_total = profit_total;
	}

	@Column(name = "reduce_tax", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getReduce_tax() {
		return reduce_tax;
	}

	public void setReduce_tax(String reduce_tax) {
		this.reduce_tax = reduce_tax;
	}

	@Column(name = "plus_netprofit_subject", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getPlus_netprofit_subject() {
		return plus_netprofit_subject;
	}

	public void setPlus_netprofit_subject(String plus_netprofit_subject) {
		this.plus_netprofit_subject = plus_netprofit_subject;
	}

	@Column(name = "netprofit", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getNetprofit() {
		return netprofit;
	}

	public void setNetprofit(String netprofit) {
		this.netprofit = netprofit;
	}

	@Column(name = "parent_netprofit", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getParent_netprofit() {
		return parent_netprofit;
	}

	public void setParent_netprofit(String parent_netprofit) {
		this.parent_netprofit = parent_netprofit;
	}

	@Column(name = "minority_loss", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getMinority_loss() {
		return minority_loss;
	}

	public void setMinority_loss(String minority_loss) {
		this.minority_loss = minority_loss;
	}

	@Column(name = "perstock_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getPerstock_income() {
		return perstock_income;
	}

	public void setPerstock_income(String perstock_income) {
		this.perstock_income = perstock_income;
	}

	@Column(name = "basic_perstock_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getBasic_perstock_income() {
		return basic_perstock_income;
	}

	public void setBasic_perstock_income(String basic_perstock_income) {
		this.basic_perstock_income = basic_perstock_income;
	}

	@Column(name = "reduce_perstock_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getReduce_perstock_income() {
		return reduce_perstock_income;
	}

	public void setReduce_perstock_income(String reduce_perstock_income) {
		this.reduce_perstock_income = reduce_perstock_income;
	}

	@Column(name = "other_all_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getOther_all_income() {
		return other_all_income;
	}

	public void setOther_all_income(String other_all_income) {
		this.other_all_income = other_all_income;
	}

	@Column(name = "all_income_total", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getAll_income_total() {
		return all_income_total;
	}

	public void setAll_income_total(String all_income_total) {
		this.all_income_total = all_income_total;
	}

	@Column(name = "income_for_parent", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getIncome_for_parent() {
		return income_for_parent;
	}

	public void setIncome_for_parent(String income_for_parent) {
		this.income_for_parent = income_for_parent;
	}

	@Column(name = "income_for_holder", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getIncome_for_holder() {
		return income_for_holder;
	}

	public void setIncome_for_holder(String income_for_holder) {
		this.income_for_holder = income_for_holder;
	}

	@Column(name = "interest_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getInterest_income() {
		return interest_income;
	}

	public void setInterest_income(String interest_income) {
		this.interest_income = interest_income;
	}

	@Column(name = "earn_insurance", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getEarn_insurance() {
		return earn_insurance;
	}

	public void setEarn_insurance(String earn_insurance) {
		this.earn_insurance = earn_insurance;
	}

	@Column(name = "commission_income", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getCommission_income() {
		return commission_income;
	}

	public void setCommission_income(String commission_income) {
		this.commission_income = commission_income;
	}

	@Column(name = "interest_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getInterest_cost() {
		return interest_cost;
	}

	public void setInterest_cost(String interest_cost) {
		this.interest_cost = interest_cost;
	}
	
	@Column(name = "commission_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getCommission_cost() {
		return commission_cost;
	}

	public void setCommission_cost(String commission_cost) {
		this.commission_cost = commission_cost;
	}

	@Column(name = "canel_insurance_money", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getCanel_insurance_money() {
		return canel_insurance_money;
	}

	public void setCanel_insurance_money(String canel_insurance_money) {
		this.canel_insurance_money = canel_insurance_money;
	}

	@Column(name = "pay_netprofit", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getPay_netprofit() {
		return pay_netprofit;
	}

	public void setPay_netprofit(String pay_netprofit) {
		this.pay_netprofit = pay_netprofit;
	}

	@Column(name = "insurance_netprofit", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getInsurance_netprofit() {
		return insurance_netprofit;
	}

	public void setInsurance_netprofit(String insurance_netprofit) {
		this.insurance_netprofit = insurance_netprofit;
	}

	@Column(name = "insurance_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getInsurance_cost() {
		return insurance_cost;
	}

	public void setInsurance_cost(String insurance_cost) {
		this.insurance_cost = insurance_cost;
	}

	@Column(name = "reduce_insurance_cost", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getReduce_insurance_cost() {
		return reduce_insurance_cost;
	}

	public void setReduce_insurance_cost(String reduce_insurance_cost) {
		this.reduce_insurance_cost = reduce_insurance_cost;
	}

	@Column(name = "interest_dispose", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getInterest_dispose() {
		return interest_dispose;
	}

	public void setInterest_dispose(String interest_dispose) {
		this.interest_dispose = interest_dispose;
	}

	@Column(name = "push_flag", unique = true, nullable = false, insertable = true, updatable = true, precision = 20, scale = 0)
	public int getPush_flag() {
		return push_flag;
	}

	public void setPush_flag(int push_flag) {
		this.push_flag = push_flag;
	}

	@Column(name = "main_costCopy", unique = true, nullable = true, insertable = true, updatable = true, precision = 18, scale = 2)
	public String getMain_costCopy() {
		return main_costCopy;
	}

	public void setMain_costCopy(String main_costCopy) {
		this.main_costCopy = main_costCopy;
	}
}
