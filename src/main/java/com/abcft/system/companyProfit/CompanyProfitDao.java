package com.abcft.system.companyProfit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.base.RsglBaseDao;
import com.abcft.common.util.DateUtils;
import com.abcft.common.util.NumberUtil;
import com.abcft.common.util.StringUtils;

@Repository
@Transactional
public class CompanyProfitDao extends RsglBaseDao {

	private static final Logger logger = LogManager
			.getLogger(CompanyProfitDao.class);

	@SuppressWarnings("unchecked")
	public List<CompanyProfitInfo> findList(long start, long end,
			String stockCode, String keyword) {
		StringBuffer sql = null;
		List<Object> params = new ArrayList<Object>();
		if (keyword != null && "distinct".equals(keyword)) {
			sql = new StringBuffer(" select profit_sheet_id,stock_code,date_format(publish_date, '%Y-%m-%d') as publish_date,date_format(end_date, '%Y-%m-%d') as end_date,"
					+ "date_format(account_date, '%Y-%m-%d') as account_date,report_period,overall_income,main_income,overall_cost,main_cost,tax,"
					+ "sale_cost,manage_cost,other_cost,fin_cost,asset_loss,plus_change_income,invest_income,"
					+ "relate_invest_income,gain_loss_income,other_subject,overall_profit,plus_subsidy_income,"
					+ "addition_income,reduce_addition_cost,asset_dispose_loss,plus_profit_subject,profit_total,"
					+ "reduce_tax,plus_netprofit_subject,netprofit,parent_netprofit,minority_loss,perstock_income,"
					+ "basic_perstock_income,reduce_perstock_income,other_all_income,all_income_total,"
					+ "income_for_parent,income_for_holder,interest_income,earn_insurance,commission_income,"
					+ "interest_cost,commission_cost,canel_insurance_money,pay_netprofit,insurance_netprofit,"
					+ "insurance_cost,reduce_insurance_cost,interest_dispose,push_flag "
					+ " from company_profit_sheet profit where "
					+ " (profit.stock_code,profit.publish_date) in ("
					+ " select stock_code,publish_date "
					+ " from company_profit_sheet group by stock_code,publish_date having count(*) > 1) ");
		} else {
			sql = new StringBuffer(
					" select profit_sheet_id,stock_code,date_format(publish_date, '%Y-%m-%d') as publish_date,date_format(end_date, '%Y-%m-%d') as end_date,"
					+ "date_format(account_date, '%Y-%m-%d') as account_date,report_period,overall_income,main_income,overall_cost,main_cost,tax,"
					+ "sale_cost,manage_cost,other_cost,fin_cost,asset_loss,plus_change_income,invest_income,"
					+ "relate_invest_income,gain_loss_income,other_subject,overall_profit,plus_subsidy_income,"
					+ "addition_income,reduce_addition_cost,asset_dispose_loss,plus_profit_subject,profit_total,"
					+ "reduce_tax,plus_netprofit_subject,netprofit,parent_netprofit,minority_loss,perstock_income,"
					+ "basic_perstock_income,reduce_perstock_income,other_all_income,all_income_total,"
					+ "income_for_parent,income_for_holder,interest_income,earn_insurance,commission_income,"
					+ "interest_cost,commission_cost,canel_insurance_money,pay_netprofit,insurance_netprofit,"
					+ "insurance_cost,reduce_insurance_cost,interest_dispose,push_flag"
					+ " from  company_profit_sheet  where 1=1");
	
			if (!StringUtils.isEmpty(stockCode)) {
				sql.append("  AND stock_code like '%" + stockCode + "%' ");
			}
	
			sql.append(" limit ?,? ");
			params.add(start);
			params.add(end);
		}
		
		logger.info(sql);
		logger.info(params);
		return getJdbcTemplate().query(sql.toString(),
				new EntityMapper(CompanyProfitInfo.class), params.toArray());
	}

	@SuppressWarnings("deprecation")
	public Long getTotalRole(String stockCode, String keyword) {
		StringBuffer sql = null;
		if (keyword != null && "distinct".equals(keyword)) {
			sql = new StringBuffer(" select count(*) from (select * from company_profit_sheet profit where "
					+ " (profit.stock_code,profit.publish_date) in (select stock_code,publish_date "
					+ " from company_profit_sheet group by stock_code,publish_date having count(*) > 1)) T");
		} else {
			sql = new StringBuffer("select count(*) from company_profit_sheet  where 1=1");
			
			if (!StringUtils.isEmpty(stockCode)) {
				sql.append("  AND stock_code like '%" + stockCode + "%' ");
			}
		}

		return getJdbcTemplate().queryForLong(sql.toString());
	}

	public int remove(String profit_sheet_id) {
		String sql = "delete  from company_profit_sheet  where profit_sheet_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(profit_sheet_id);
		
		return getJdbcTemplate().update(sql, params.toArray());
	}
	
	public void updateCompanyProfit(CompanyProfitInfo companyProfitInfo) {
		String sql = "update company_profit_sheet set  stock_code=?,publish_date=?,end_date=?,"
				+ "account_date=?,report_period=?,overall_income=?,main_income=?,overall_cost=?,main_cost=?,tax=?,"
				+ "sale_cost=?,manage_cost=?,other_cost=?,fin_cost=?,asset_loss=?,plus_change_income=?,invest_income=?,"
				+ "relate_invest_income=?,gain_loss_income=?,other_subject=?,overall_profit=?,plus_subsidy_income=?,"
				+ "addition_income=?,reduce_addition_cost=?,asset_dispose_loss=?,plus_profit_subject=?,profit_total=?,"
				+ "reduce_tax=?,plus_netprofit_subject=?,netprofit=?,parent_netprofit=?,minority_loss=?,perstock_income=?,"
				+ "basic_perstock_income=?,reduce_perstock_income=?,other_all_income=?,all_income_total=?,"
				+ "income_for_parent=?,income_for_holder=?,interest_income=?,earn_insurance=?,commission_income=?,"
				+ "interest_cost=?,commission_cost=?,canel_insurance_money=?,pay_netprofit=?,insurance_netprofit=?,"
				+ "insurance_cost=?,reduce_insurance_cost=?,interest_dispose=?,push_flag=? "
				+ " where profit_sheet_id=? ";
		
		List<Object> list = parseCompanyProfitInfo(companyProfitInfo);
		list.add(companyProfitInfo.getProfit_sheet_id());
		
		getJdbcTemplate().update(sql, list.toArray());
	}
	
	private List<Object> parseCompanyProfitInfo(CompanyProfitInfo companyProfitInfo) {
		List<Object> list = new ArrayList<Object>();
		list.add(StringUtils.parseValue(companyProfitInfo.getStock_code()));
		list.add(DateUtils.parseDate(companyProfitInfo.getPublish_date()));
		list.add(DateUtils.parseDate(companyProfitInfo.getEnd_date()));
		list.add(DateUtils.parseDate(companyProfitInfo.getAccount_date()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getReport_period()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getOverall_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getMain_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getOverall_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getMain_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getTax()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getSale_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getManage_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getOther_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getFin_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getAsset_loss()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getPlus_change_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getInvest_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getRelate_invest_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getGain_loss_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getOther_subject()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getOverall_profit()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getPlus_subsidy_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getAddition_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getReduce_addition_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getAsset_dispose_loss()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getPlus_profit_subject()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getProfit_total()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getReduce_tax()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getPlus_netprofit_subject()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getNetprofit()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getParent_netprofit()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getMinority_loss()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getPerstock_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getBasic_perstock_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getReduce_perstock_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getOther_all_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getAll_income_total()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getIncome_for_parent()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getIncome_for_holder()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getInterest_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getEarn_insurance()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getCommission_income()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getInterest_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getCommission_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getCanel_insurance_money()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getPay_netprofit()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getInsurance_netprofit()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getInsurance_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getReduce_insurance_cost()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getInterest_dispose()));
		list.add(NumberUtil.parseValue(companyProfitInfo.getPush_flag()));
		
		return list;
	}

	public void insertCompanyProfit(CompanyProfitInfo companyProfitInfo) {
		String sql = "insert into company_profit_sheet (stock_code,publish_date,end_date,"
				+ "account_date,report_period,overall_income,main_income,overall_cost,main_cost,tax,"
				+ "sale_cost,manage_cost,other_cost,fin_cost,asset_loss,plus_change_income,invest_income,"
				+ "relate_invest_income,gain_loss_income,other_subject,overall_profit,plus_subsidy_income,"
				+ "addition_income,reduce_addition_cost,asset_dispose_loss,plus_profit_subject,profit_total,"
				+ "reduce_tax,plus_netprofit_subject,netprofit,parent_netprofit,minority_loss,perstock_income,"
				+ "basic_perstock_income,reduce_perstock_income,other_all_income,all_income_total,"
				+ "income_for_parent,income_for_holder,interest_income,earn_insurance,commission_income,"
				+ "interest_cost,commission_cost,canel_insurance_money,pay_netprofit,insurance_netprofit,"
				+ "insurance_cost,reduce_insurance_cost,interest_dispose,push_flag) "
				+ " values (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?)";
		
		List<Object> list = parseCompanyProfitInfo(companyProfitInfo);
		
		getJdbcTemplate().update(sql.toString(), list.toArray());
	}
	
	public void batchInsertCompanyProfit(final List<CompanyProfitInfo> companyProfits) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getJdbcTemplate().getDataSource().getConnection(); 
			conn.setAutoCommit(false);
			
			String sql = "insert into company_profit_sheet (stock_code,publish_date,end_date,"
					+ "account_date,report_period,overall_income,main_income,overall_cost,main_cost,tax,"
					+ "sale_cost,manage_cost,other_cost,fin_cost,asset_loss,plus_change_income,invest_income,"
					+ "relate_invest_income,gain_loss_income,other_subject,overall_profit,plus_subsidy_income,"
					+ "addition_income,reduce_addition_cost,asset_dispose_loss,plus_profit_subject,profit_total,"
					+ "reduce_tax,plus_netprofit_subject,netprofit,parent_netprofit,minority_loss,perstock_income,"
					+ "basic_perstock_income,reduce_perstock_income,other_all_income,all_income_total,"
					+ "income_for_parent,income_for_holder,interest_income,earn_insurance,commission_income,"
					+ "interest_cost,commission_cost,canel_insurance_money,pay_netprofit,insurance_netprofit,"
					+ "insurance_cost,reduce_insurance_cost,interest_dispose,push_flag) "
					+ " values (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?)";
			
			ps = conn.prepareStatement(sql);
			for (CompanyProfitInfo profit : companyProfits) {
				ps.setObject(1, StringUtils.parseValue(profit.getStock_code()));
				ps.setObject(2, DateUtils.parseDate(profit.getPublish_date()));
				ps.setObject(3, DateUtils.parseDate(profit.getEnd_date()));
				ps.setObject(4, DateUtils.parseDate(profit.getAccount_date()));
				ps.setObject(5, NumberUtil.parseValue(profit.getReport_period()));
				ps.setObject(6, NumberUtil.parseValue(profit.getOverall_income()));
				ps.setObject(7, NumberUtil.parseValue(profit.getMain_income()));
				ps.setObject(8, NumberUtil.parseValue(profit.getOverall_cost()));
				ps.setObject(9, NumberUtil.parseValue(profit.getMain_cost()));
				ps.setObject(10, NumberUtil.parseValue(profit.getTax()));
				ps.setObject(11, NumberUtil.parseValue(profit.getSale_cost()));
				ps.setObject(12, NumberUtil.parseValue(profit.getManage_cost()));
				ps.setObject(13, NumberUtil.parseValue(profit.getOther_cost()));
				ps.setObject(14, NumberUtil.parseValue(profit.getFin_cost()));
				ps.setObject(15, NumberUtil.parseValue(profit.getAsset_loss()));
				ps.setObject(16, NumberUtil.parseValue(profit.getPlus_change_income()));
				ps.setObject(17, NumberUtil.parseValue(profit.getInvest_income()));
				ps.setObject(18, NumberUtil.parseValue(profit.getRelate_invest_income()));
				ps.setObject(19, NumberUtil.parseValue(profit.getGain_loss_income()));
				ps.setObject(20, NumberUtil.parseValue(profit.getOther_subject()));
				ps.setObject(21, NumberUtil.parseValue(profit.getOverall_profit()));
				ps.setObject(22, NumberUtil.parseValue(profit.getPlus_subsidy_income()));
				ps.setObject(23, NumberUtil.parseValue(profit.getAddition_income()));
				ps.setObject(24, NumberUtil.parseValue(profit.getReduce_addition_cost()));
				ps.setObject(25, NumberUtil.parseValue(profit.getAsset_dispose_loss()));
				ps.setObject(26, NumberUtil.parseValue(profit.getPlus_profit_subject()));
				ps.setObject(27, NumberUtil.parseValue(profit.getProfit_total()));
				ps.setObject(28, NumberUtil.parseValue(profit.getReduce_tax()));
				ps.setObject(29, NumberUtil.parseValue(profit.getPlus_netprofit_subject()));
				ps.setObject(30, NumberUtil.parseValue(profit.getNetprofit()));
				ps.setObject(31, NumberUtil.parseValue(profit.getParent_netprofit()));
				ps.setObject(32, NumberUtil.parseValue(profit.getMinority_loss()));
				ps.setObject(33, NumberUtil.parseValue(profit.getPerstock_income()));
				ps.setObject(34, NumberUtil.parseValue(profit.getBasic_perstock_income()));
				ps.setObject(35, NumberUtil.parseValue(profit.getReduce_perstock_income()));
				ps.setObject(36, NumberUtil.parseValue(profit.getOther_all_income()));
				ps.setObject(37, NumberUtil.parseValue(profit.getAll_income_total()));
				ps.setObject(38, NumberUtil.parseValue(profit.getIncome_for_parent()));
				ps.setObject(39, NumberUtil.parseValue(profit.getIncome_for_holder()));
				ps.setObject(40, NumberUtil.parseValue(profit.getInterest_income()));
				ps.setObject(41, NumberUtil.parseValue(profit.getEarn_insurance()));
				ps.setObject(42, NumberUtil.parseValue(profit.getCommission_income()));
				ps.setObject(43, NumberUtil.parseValue(profit.getInterest_cost()));
				ps.setObject(44, NumberUtil.parseValue(profit.getCommission_cost()));
				ps.setObject(45, NumberUtil.parseValue(profit.getCanel_insurance_money()));
				ps.setObject(46, NumberUtil.parseValue(profit.getPay_netprofit()));
				ps.setObject(47, NumberUtil.parseValue(profit.getInsurance_netprofit()));
				ps.setObject(48, NumberUtil.parseValue(profit.getInsurance_cost()));
				ps.setObject(49, NumberUtil.parseValue(profit.getReduce_insurance_cost()));
				ps.setObject(50, NumberUtil.parseValue(profit.getInterest_dispose()));
				ps.setObject(51, NumberUtil.parseValue(profit.getPush_flag()));
				
				ps.addBatch();
			}
			
			ps.executeBatch();
			conn.commit();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void batchUpdateCompanyProfit(final List<CompanyProfitInfo> companyBasicinfos) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getJdbcTemplate().getDataSource().getConnection(); 
			conn.setAutoCommit(false);
			
			String sql = "update company_profit_sheet set  stock_code=?,publish_date=?,end_date=?,"
					+ "account_date=?,report_period=?,overall_income=?,main_income=?,overall_cost=?,main_cost=?,tax=?,"
					+ "sale_cost=?,manage_cost=?,other_cost=?,fin_cost=?,asset_loss=?,plus_change_income=?,invest_income=?,"
					+ "relate_invest_income=?,gain_loss_income=?,other_subject=?,overall_profit=?,plus_subsidy_income=?,"
					+ "addition_income=?,reduce_addition_cost=?,asset_dispose_loss=?,plus_profit_subject=?,profit_total=?,"
					+ "reduce_tax=?,plus_netprofit_subject=?,netprofit=?,parent_netprofit=?,minority_loss=?,perstock_income=?,"
					+ "basic_perstock_income=?,reduce_perstock_income=?,other_all_income=?,all_income_total=?,"
					+ "income_for_parent=?,income_for_holder=?,interest_income=?,earn_insurance=?,commission_income=?,"
					+ "interest_cost=?,commission_cost=?,canel_insurance_money=?,pay_netprofit=?,insurance_netprofit=?,"
					+ "insurance_cost=?,reduce_insurance_cost=?,interest_dispose=?,push_flag=? "
					+ " where profit_sheet_id=? ";
			
			ps = conn.prepareStatement(sql);
			for (CompanyProfitInfo profit : companyBasicinfos) {
				ps.setObject(1, StringUtils.parseValue(profit.getStock_code()));
				ps.setObject(2, DateUtils.parseDate(profit.getPublish_date()));
				ps.setObject(3, DateUtils.parseDate(profit.getEnd_date()));
				ps.setObject(4, DateUtils.parseDate(profit.getAccount_date()));
				ps.setObject(5, NumberUtil.parseValue(profit.getReport_period()));
				ps.setObject(6, NumberUtil.parseValue(profit.getOverall_income()));
				ps.setObject(7, NumberUtil.parseValue(profit.getMain_income()));
				ps.setObject(8, NumberUtil.parseValue(profit.getOverall_cost()));
				ps.setObject(9, NumberUtil.parseValue(profit.getMain_cost()));
				ps.setObject(10, NumberUtil.parseValue(profit.getTax()));
				ps.setObject(11, NumberUtil.parseValue(profit.getSale_cost()));
				ps.setObject(12, NumberUtil.parseValue(profit.getManage_cost()));
				ps.setObject(13, NumberUtil.parseValue(profit.getOther_cost()));
				ps.setObject(14, NumberUtil.parseValue(profit.getFin_cost()));
				ps.setObject(15, NumberUtil.parseValue(profit.getAsset_loss()));
				ps.setObject(16, NumberUtil.parseValue(profit.getPlus_change_income()));
				ps.setObject(17, NumberUtil.parseValue(profit.getInvest_income()));
				ps.setObject(18, NumberUtil.parseValue(profit.getRelate_invest_income()));
				ps.setObject(19, NumberUtil.parseValue(profit.getGain_loss_income()));
				ps.setObject(20, NumberUtil.parseValue(profit.getOther_subject()));
				ps.setObject(21, NumberUtil.parseValue(profit.getOverall_profit()));
				ps.setObject(22, NumberUtil.parseValue(profit.getPlus_subsidy_income()));
				ps.setObject(23, NumberUtil.parseValue(profit.getAddition_income()));
				ps.setObject(24, NumberUtil.parseValue(profit.getReduce_addition_cost()));
				ps.setObject(25, NumberUtil.parseValue(profit.getAsset_dispose_loss()));
				ps.setObject(26, NumberUtil.parseValue(profit.getPlus_profit_subject()));
				ps.setObject(27, NumberUtil.parseValue(profit.getProfit_total()));
				ps.setObject(28, NumberUtil.parseValue(profit.getReduce_tax()));
				ps.setObject(29, NumberUtil.parseValue(profit.getPlus_netprofit_subject()));
				ps.setObject(30, NumberUtil.parseValue(profit.getNetprofit()));
				ps.setObject(31, NumberUtil.parseValue(profit.getParent_netprofit()));
				ps.setObject(32, NumberUtil.parseValue(profit.getMinority_loss()));
				ps.setObject(33, NumberUtil.parseValue(profit.getPerstock_income()));
				ps.setObject(34, NumberUtil.parseValue(profit.getBasic_perstock_income()));
				ps.setObject(35, NumberUtil.parseValue(profit.getReduce_perstock_income()));
				ps.setObject(36, NumberUtil.parseValue(profit.getOther_all_income()));
				ps.setObject(37, NumberUtil.parseValue(profit.getAll_income_total()));
				ps.setObject(38, NumberUtil.parseValue(profit.getIncome_for_parent()));
				ps.setObject(39, NumberUtil.parseValue(profit.getIncome_for_holder()));
				ps.setObject(40, NumberUtil.parseValue(profit.getInterest_income()));
				ps.setObject(41, NumberUtil.parseValue(profit.getEarn_insurance()));
				ps.setObject(42, NumberUtil.parseValue(profit.getCommission_income()));
				ps.setObject(43, NumberUtil.parseValue(profit.getInterest_cost()));
				ps.setObject(44, NumberUtil.parseValue(profit.getCommission_cost()));
				ps.setObject(45, NumberUtil.parseValue(profit.getCanel_insurance_money()));
				ps.setObject(46, NumberUtil.parseValue(profit.getPay_netprofit()));
				ps.setObject(47, NumberUtil.parseValue(profit.getInsurance_netprofit()));
				ps.setObject(48, NumberUtil.parseValue(profit.getInsurance_cost()));
				ps.setObject(49, NumberUtil.parseValue(profit.getReduce_insurance_cost()));
				ps.setObject(50, NumberUtil.parseValue(profit.getInterest_dispose()));
				ps.setObject(51, NumberUtil.parseValue(profit.getPush_flag()));
				ps.setObject(52, StringUtils.parseValue(profit.getProfit_sheet_id()));
				
				ps.addBatch();
			}
			
			ps.executeBatch();
			conn.commit();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

}
