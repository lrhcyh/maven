package com.abcft.system.companyCash;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.base.RsglBaseDao;

@Repository
@Transactional
public class CompanyCashDao extends RsglBaseDao {
	
	private static Logger logger = LogManager.getLogger(CompanyCashDao.class);
	
	
	/**
	 * 根据相应的条件查询数据
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyCashInfo> findCompanyCashList(long start, long end, String stockCode, String end_date_start, String end_date_end, String keyWord) {
		StringBuffer sql = new StringBuffer("select cash_sheet_id,stock_code,date_format(publish_date, '%Y-%m-%d') as publish_date,"
			+ "date_format(end_date, '%Y-%m-%d') as end_date,date_format(account_date, '%Y-%m-%d') as account_date,report_period,"
			+ "sale_cash,tax_return,rec_other_cash,bussiness_cash_total,buy_for_cash,pay_emp_cash,pay_tax,pay_other_cash,bussiness_cash_output,"
			+ "bussiness_cash_netvalue,rec_invest_cash,invest_rec_cash,dispose_asset_netvalue,dispose_other_netvalue,rec_otherinvest_cash,"
			+ "invest_cash_total,buy_asset_cash,invest_pay_cash,loan_net_addvalue,rec_othercompany_cash,pay_otherinvest_cash,invest_cash_output,"
			+ "invest_cash_netvalue,rec_invest_reccash,rec_borrow_cash,publish_rec_cash,rec_other_relatecash,borrow_cash_total,pay_debet_cash,"
			+ "interest_pay_cash,pay_other_relatecash,borrow_cash_outtotal,borrow_cash_netvalue,rate_to_cash,other_to_cash,cash_to_netadd,"
			+ "origin_cash,last_cash,addition,netvalue_to_cash,netvalue,plus_asset_loss,asset_discount,intangible_asset_discount,"
			+ "long_cost_discount,asset_loss,fix_asset_loss,value_change_loss,fin_cost,invest_loss,tax_reduce,debt_reduce,stock_reduce,"
			+ "rec_project_reduce,pay_project_add,other,business_cash_netvalue,non_cash_netvalue,debt_to_capital,debt_one_year,cash_to_asset,"
			+ "cash_change,last_cash_value,reduce_origin_cash,plus_last_cash,reduce_origin_value,plus_other_cash,cash_to_netvalue,"
			+ "custom_to_netvalue,borrow_netvalue,borrow_other_netvalue,rec_insurance_cash,rec_insurance_netvalue,invest_netvalue,"
			+ "dispose_the_cash,rec_interest_cash,cash_netvalue,return_cash_netvalue,custom_netvalue,bank_cash_netvalue,pay_insurance_cash,"
			+ "pay_interest_cash,pay_profit_cash,cash_for_holder,profit_for_holder,house_disount,push_flag from company_cash_sheet where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(keyWord) && "keyWord".equals(keyWord)){
			sql.append(" group by stock_code,publish_date  having count(stock_code)>1 and count(publish_date)>1");
		}else{
			if(!StringUtils.isEmpty(stockCode)){
				sql.append(" and stock_code like '%" + stockCode + "%' ");
			}
			if(!StringUtils.isEmpty(end_date_start)){
				sql.append(" and publish_date>=?");
				params.add(end_date_start);
			}
			if(!StringUtils.isEmpty(end_date_end)){
				sql.append(" and publish_date<=?");
				params.add(end_date_end);
			}
		}
		
		sql.append(" limit ?,?");
		params.add(start);
		params.add(end);
		logger.info(sql);
		logger.info(params);
		
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(CompanyCashInfo.class), params.toArray());
	}

	
	/**
	 * 根据相应条件，统计数量
	 */
	public Long getTotalRole(String stockCode, String end_date_start, String end_date_end, String keyWord) {
		StringBuffer sql = new StringBuffer("select count(*) from company_cash_sheet where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(keyWord) && "keyWord".equals(keyWord)){
			sql = new StringBuffer("select count(*) from (select count(*) from company_cash_sheet"
					+ " group by stock_code,publish_date  having count(stock_code)>1 and count(publish_date)>1) T");
		}else{
			if(!StringUtils.isEmpty(stockCode)){
				sql.append(" and stock_code like '%" + stockCode + "%' ");
			}
			if(!StringUtils.isEmpty(end_date_start)){
				sql.append(" and publish_date>=?");
				params.add(end_date_start);
			}
			if(!StringUtils.isEmpty(end_date_end)){
				sql.append(" and publish_date<=?");
				params.add(end_date_end);
			}
		}
		
		return getJdbcTemplate().queryForLong(sql.toString(), params.toArray());
	}

	
	/**
	 * 根据股票代码和公告日期查询是否已存在该数据
	 */
	public List<CompanyCashInfo> findIsRepeat(String stock_code, String publish_date) {
		String sql = "select cash_sheet_id from company_cash_sheet where stock_code=? and publish_date=?";
		List<Object> params = new ArrayList<Object>();
		params.add(stock_code);
		params.add(publish_date);
		
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(CompanyCashInfo.class), params.toArray());
	}

	/**
	 * 添加现金流量信息
	 */
	public void insertCompanyCashInfo(CompanyCashInfo companyCashInfo) {
		String sql = "insert into company_cash_sheet (stock_code,publish_date,end_date,account_date,report_period,sale_cash,tax_return,"
				+ "rec_other_cash,bussiness_cash_total,buy_for_cash,pay_emp_cash,pay_tax,pay_other_cash,bussiness_cash_output,"
				+ "bussiness_cash_netvalue,rec_invest_cash,invest_rec_cash,dispose_asset_netvalue,dispose_other_netvalue,rec_otherinvest_cash,"
				+ "invest_cash_total,buy_asset_cash,invest_pay_cash,loan_net_addvalue,rec_othercompany_cash,pay_otherinvest_cash,"
				+ "invest_cash_output,invest_cash_netvalue,rec_invest_reccash,rec_borrow_cash,publish_rec_cash,rec_other_relatecash,"
				+ "borrow_cash_total,pay_debet_cash,interest_pay_cash,pay_other_relatecash,borrow_cash_outtotal,borrow_cash_netvalue,"
				+ "rate_to_cash,other_to_cash,cash_to_netadd,origin_cash,last_cash,addition,netvalue_to_cash,netvalue,plus_asset_loss,"
				+ "asset_discount,intangible_asset_discount,long_cost_discount,asset_loss,fix_asset_loss,value_change_loss,fin_cost,invest_loss,"
				+ "tax_reduce,debt_reduce,stock_reduce,rec_project_reduce,pay_project_add,other,business_cash_netvalue,non_cash_netvalue,"
				+ "debt_to_capital,debt_one_year,cash_to_asset,cash_change,last_cash_value,reduce_origin_cash,plus_last_cash,reduce_origin_value,"
				+ "plus_other_cash,cash_to_netvalue,custom_to_netvalue,borrow_netvalue,borrow_other_netvalue,rec_insurance_cash,"
				+ "rec_insurance_netvalue,invest_netvalue,dispose_the_cash,rec_interest_cash,cash_netvalue,return_cash_netvalue,custom_netvalue,"
				+ "bank_cash_netvalue,pay_insurance_cash,pay_interest_cash,pay_profit_cash,cash_for_holder,profit_for_holder,house_disount,"
				+ "push_flag) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		List<Object> params = getParamsCashInfo(companyCashInfo);
		
		getJdbcTemplate().update(sql, params.toArray());
		
	}
	
	private List<Object> getParamsCashInfo(CompanyCashInfo companyCashInfo){
		List<Object> list = new ArrayList<Object>();
		
		list.add(companyCashInfo.getStock_code());
		list.add(companyCashInfo.getPublish_date());
		list.add(companyCashInfo.getEnd_date());
		list.add(companyCashInfo.getAccount_date());
		list.add(companyCashInfo.getReport_period());
		list.add(companyCashInfo.getSale_cash());
		list.add(companyCashInfo.getTax_return());
		list.add(companyCashInfo.getRec_other_cash());
		list.add(companyCashInfo.getBussiness_cash_total());
		list.add(companyCashInfo.getBuy_for_cash());
		list.add(companyCashInfo.getPay_emp_cash());
		list.add(companyCashInfo.getPay_tax());
		list.add(companyCashInfo.getPay_other_cash());
		list.add(companyCashInfo.getBussiness_cash_output());
		list.add(companyCashInfo.getBussiness_cash_netvalue());
		list.add(companyCashInfo.getRec_invest_cash());
		list.add(companyCashInfo.getInvest_rec_cash());
		list.add(companyCashInfo.getDispose_asset_netvalue());
		list.add(companyCashInfo.getDispose_other_netvalue());
		list.add(companyCashInfo.getRec_otherinvest_cash());
		list.add(companyCashInfo.getInvest_cash_total());
		list.add(companyCashInfo.getBuy_asset_cash());
		list.add(companyCashInfo.getInvest_pay_cash());
		list.add(companyCashInfo.getLoan_net_addvalue());
		list.add(companyCashInfo.getRec_othercompany_cash());
		list.add(companyCashInfo.getPay_otherinvest_cash());
		list.add(companyCashInfo.getInvest_cash_output());
		list.add(companyCashInfo.getInvest_cash_netvalue());
		list.add(companyCashInfo.getRec_invest_reccash());
		list.add(companyCashInfo.getRec_borrow_cash());
		list.add(companyCashInfo.getPublish_rec_cash());
		list.add(companyCashInfo.getRec_other_relatecash());
		list.add(companyCashInfo.getBorrow_cash_total());
		list.add(companyCashInfo.getPay_debet_cash());
		list.add(companyCashInfo.getInterest_pay_cash());
		list.add(companyCashInfo.getPay_other_relatecash());
		list.add(companyCashInfo.getBorrow_cash_outtotal());
		list.add(companyCashInfo.getBorrow_cash_netvalue());
		list.add(companyCashInfo.getRate_to_cash());
		list.add(companyCashInfo.getOther_to_cash());
		list.add(companyCashInfo.getCash_to_netadd());
		list.add(companyCashInfo.getOrigin_cash());
		list.add(companyCashInfo.getLast_cash());
		list.add(companyCashInfo.getAddition());
		list.add(companyCashInfo.getNetvalue_to_cash());
		list.add(companyCashInfo.getNetvalue());
		list.add(companyCashInfo.getPlus_asset_loss());
		list.add(companyCashInfo.getAsset_discount());
		list.add(companyCashInfo.getIntangible_asset_discount());
		list.add(companyCashInfo.getLong_cost_discount());
		list.add(companyCashInfo.getAsset_loss());
		list.add(companyCashInfo.getFix_asset_loss());
		list.add(companyCashInfo.getValue_change_loss());
		list.add(companyCashInfo.getFin_cost());
		list.add(companyCashInfo.getInvest_loss());
		list.add(companyCashInfo.getTax_reduce());
		list.add(companyCashInfo.getDebt_reduce());
		list.add(companyCashInfo.getStock_reduce());
		list.add(companyCashInfo.getRec_project_reduce());
		list.add(companyCashInfo.getPay_project_add());
		list.add(companyCashInfo.getOther());
		list.add(companyCashInfo.getBusiness_cash_netvalue());
		list.add(companyCashInfo.getNon_cash_netvalue());
		list.add(companyCashInfo.getDebt_to_capital());
		list.add(companyCashInfo.getDebt_one_year());
		list.add(companyCashInfo.getCash_to_asset());
		list.add(companyCashInfo.getCash_change());
		list.add(companyCashInfo.getLast_cash_value());
		list.add(companyCashInfo.getReduce_origin_cash());
		list.add(companyCashInfo.getPlus_last_cash());
		list.add(companyCashInfo.getReduce_origin_value());
		list.add(companyCashInfo.getPlus_other_cash());
		list.add(companyCashInfo.getCash_to_netvalue());
		list.add(companyCashInfo.getCustom_to_netvalue());
		list.add(companyCashInfo.getBorrow_netvalue());
		list.add(companyCashInfo.getBorrow_other_netvalue());
		list.add(companyCashInfo.getRec_insurance_cash());
		list.add(companyCashInfo.getRec_insurance_netvalue());
		list.add(companyCashInfo.getInvest_netvalue());
		list.add(companyCashInfo.getDispose_the_cash());
		list.add(companyCashInfo.getRec_interest_cash());
		list.add(companyCashInfo.getCash_netvalue());
		list.add(companyCashInfo.getReturn_cash_netvalue());
		list.add(companyCashInfo.getCustom_netvalue());
		list.add(companyCashInfo.getBank_cash_netvalue());
		list.add(companyCashInfo.getPay_insurance_cash());
		list.add(companyCashInfo.getPay_interest_cash());
		list.add(companyCashInfo.getPay_profit_cash());
		list.add(companyCashInfo.getCash_for_holder());
		list.add(companyCashInfo.getProfit_for_holder());
		list.add(companyCashInfo.getHouse_disount());
		list.add(companyCashInfo.getPush_flag());
		
		return list;
	}

	/**
	 * 更新现金流量信息
	 */
	public void updateCompanyCash(CompanyCashInfo companyCashInfo) {
		String sql = "update company_cash_sheet set stock_code=?,publish_date=?,end_date=?,account_date=?,report_period=?,sale_cash=?,"
			+ "tax_return=?,rec_other_cash=?,bussiness_cash_total=?,buy_for_cash=?,pay_emp_cash=?,pay_tax=?,pay_other_cash=?,"
			+ "bussiness_cash_output=?,bussiness_cash_netvalue=?,rec_invest_cash=?,invest_rec_cash=?,dispose_asset_netvalue=?,"
			+ "dispose_other_netvalue=?,rec_otherinvest_cash=?,invest_cash_total=?,buy_asset_cash=?,invest_pay_cash=?,loan_net_addvalue=?,"
			+ "rec_othercompany_cash=?,pay_otherinvest_cash=?,invest_cash_output=?,invest_cash_netvalue=?,rec_invest_reccash=?,rec_borrow_cash=?,"
			+ "publish_rec_cash=?,rec_other_relatecash=?,borrow_cash_total=?,pay_debet_cash=?,interest_pay_cash=?,pay_other_relatecash=?,"
			+ "borrow_cash_outtotal=?,borrow_cash_netvalue=?,rate_to_cash=?,other_to_cash=?,cash_to_netadd=?,origin_cash=?,last_cash=?,"
			+ "addition=?,netvalue_to_cash=?,netvalue=?,plus_asset_loss=?,asset_discount=?,intangible_asset_discount=?,long_cost_discount=?,"
			+ "asset_loss=?,fix_asset_loss=?,value_change_loss=?,fin_cost=?,invest_loss=?,tax_reduce=?,debt_reduce=?,stock_reduce=?,"
			+ "rec_project_reduce=?,pay_project_add=?,other=?,business_cash_netvalue=?,non_cash_netvalue=?,debt_to_capital=?,debt_one_year=?,"
			+ "cash_to_asset=?,cash_change=?,last_cash_value=?,reduce_origin_cash=?,plus_last_cash=?,reduce_origin_value=?,plus_other_cash=?,"
			+ "cash_to_netvalue=?,custom_to_netvalue=?,borrow_netvalue=?,borrow_other_netvalue=?,rec_insurance_cash=?,rec_insurance_netvalue=?,"
			+ "invest_netvalue=?,dispose_the_cash=?,rec_interest_cash=?,cash_netvalue=?,return_cash_netvalue=?,custom_netvalue=?,"
			+ "bank_cash_netvalue=?,pay_insurance_cash=?,pay_interest_cash=?,pay_profit_cash=?,cash_for_holder=?,profit_for_holder=?,"
			+ "house_disount=?,push_flag=? where cash_sheet_id=?";
		
		List<Object> params = getParamsCashInfo(companyCashInfo);
		params.add(companyCashInfo.getCash_sheet_id());
		
		getJdbcTemplate().update(sql, params.toArray());
	}

	
	/**
	 * 根据cash_sheet_id 删除一条信息
	 */
	public void removeCompanyCashInfo(String cash_sheet_id) {
		String sql = "delete from company_cash_sheet where cash_sheet_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(cash_sheet_id);
		
		getJdbcTemplate().update(sql, params.toArray());
	}

	/**
	 * 批量添加现金流量信息
	 */
	public void balanceInsert(final List<CompanyCashInfo> cashInfoInsert) {
		String sql = "insert into company_cash_sheet (stock_code,publish_date,end_date,account_date,report_period,sale_cash,tax_return,"
				+ "rec_other_cash,bussiness_cash_total,buy_for_cash,pay_emp_cash,pay_tax,pay_other_cash,bussiness_cash_output,"
				+ "bussiness_cash_netvalue,rec_invest_cash,invest_rec_cash,dispose_asset_netvalue,dispose_other_netvalue,rec_otherinvest_cash,"
				+ "invest_cash_total,buy_asset_cash,invest_pay_cash,loan_net_addvalue,rec_othercompany_cash,pay_otherinvest_cash,"
				+ "invest_cash_output,invest_cash_netvalue,rec_invest_reccash,rec_borrow_cash,publish_rec_cash,rec_other_relatecash,"
				+ "borrow_cash_total,pay_debet_cash,interest_pay_cash,pay_other_relatecash,borrow_cash_outtotal,borrow_cash_netvalue,"
				+ "rate_to_cash,other_to_cash,cash_to_netadd,origin_cash,last_cash,addition,netvalue_to_cash,netvalue,plus_asset_loss,"
				+ "asset_discount,intangible_asset_discount,long_cost_discount,asset_loss,fix_asset_loss,value_change_loss,fin_cost,invest_loss,"
				+ "tax_reduce,debt_reduce,stock_reduce,rec_project_reduce,pay_project_add,other,business_cash_netvalue,non_cash_netvalue,"
				+ "debt_to_capital,debt_one_year,cash_to_asset,cash_change,last_cash_value,reduce_origin_cash,plus_last_cash,reduce_origin_value,"
				+ "plus_other_cash,cash_to_netvalue,custom_to_netvalue,borrow_netvalue,borrow_other_netvalue,rec_insurance_cash,"
				+ "rec_insurance_netvalue,invest_netvalue,dispose_the_cash,rec_interest_cash,cash_netvalue,return_cash_netvalue,custom_netvalue,"
				+ "bank_cash_netvalue,pay_insurance_cash,pay_interest_cash,pay_profit_cash,cash_for_holder,profit_for_holder,house_disount,"
				+ "push_flag) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				CompanyCashInfo companyCashInfo = cashInfoInsert.get(i);
				ps.setString(1, companyCashInfo.getStock_code());
				ps.setString(2, companyCashInfo.getPublish_date());
				ps.setString(3, companyCashInfo.getEnd_date());
				ps.setString(4, companyCashInfo.getAccount_date());
				ps.setInt(5, companyCashInfo.getReport_period());
				ps.setDouble(6, companyCashInfo.getSale_cash());
				ps.setDouble(7, companyCashInfo.getTax_return());
				ps.setDouble(8, companyCashInfo.getRec_other_cash());
				ps.setDouble(9, companyCashInfo.getBussiness_cash_total());
				ps.setDouble(10, companyCashInfo.getBuy_for_cash());
				ps.setDouble(11, companyCashInfo.getPay_emp_cash());
				ps.setDouble(12, companyCashInfo.getPay_tax());
				ps.setDouble(13, companyCashInfo.getPay_other_cash());
				ps.setDouble(14, companyCashInfo.getBussiness_cash_output());
				ps.setDouble(15, companyCashInfo.getBussiness_cash_netvalue());
				ps.setDouble(16, companyCashInfo.getRec_invest_cash());
				ps.setDouble(17, companyCashInfo.getInvest_rec_cash());
				ps.setDouble(18, companyCashInfo.getDispose_asset_netvalue());
				ps.setDouble(19, companyCashInfo.getDispose_other_netvalue());
				ps.setDouble(20, companyCashInfo.getRec_otherinvest_cash());
				ps.setDouble(21, companyCashInfo.getInvest_cash_total());
				ps.setDouble(22, companyCashInfo.getBuy_asset_cash());
				ps.setDouble(23, companyCashInfo.getInvest_pay_cash());
				ps.setDouble(24, companyCashInfo.getLoan_net_addvalue());
				ps.setDouble(25, companyCashInfo.getRec_othercompany_cash());
				ps.setDouble(26, companyCashInfo.getPay_otherinvest_cash());
				ps.setDouble(27, companyCashInfo.getInvest_cash_output());
				ps.setDouble(28, companyCashInfo.getInvest_cash_netvalue());
				ps.setDouble(29, companyCashInfo.getRec_invest_reccash());
				ps.setDouble(30, companyCashInfo.getRec_borrow_cash());
				ps.setDouble(31, companyCashInfo.getPublish_rec_cash());
				ps.setDouble(32, companyCashInfo.getRec_other_relatecash());
				ps.setDouble(33, companyCashInfo.getBorrow_cash_total());
				ps.setDouble(34, companyCashInfo.getPay_debet_cash());
				ps.setDouble(35, companyCashInfo.getInterest_pay_cash());
				ps.setDouble(36, companyCashInfo.getPay_other_relatecash());
				ps.setDouble(37, companyCashInfo.getBorrow_cash_outtotal());
				ps.setDouble(38, companyCashInfo.getBorrow_cash_netvalue());
				ps.setDouble(39, companyCashInfo.getRate_to_cash());
				ps.setDouble(40, companyCashInfo.getOther_to_cash());
				ps.setDouble(41, companyCashInfo.getCash_to_netadd());
				ps.setDouble(42, companyCashInfo.getOrigin_cash());
				ps.setDouble(43, companyCashInfo.getLast_cash());
				ps.setDouble(44, companyCashInfo.getAddition());
				ps.setDouble(45, companyCashInfo.getNetvalue_to_cash());
				ps.setDouble(46, companyCashInfo.getNetvalue());
				ps.setDouble(47, companyCashInfo.getPlus_asset_loss());
				ps.setDouble(48, companyCashInfo.getAsset_discount());
				ps.setDouble(49, companyCashInfo.getIntangible_asset_discount());
				ps.setDouble(50, companyCashInfo.getLong_cost_discount());
				ps.setDouble(51, companyCashInfo.getAsset_loss());
				ps.setDouble(52, companyCashInfo.getFix_asset_loss());
				ps.setDouble(53, companyCashInfo.getValue_change_loss());
				ps.setDouble(54, companyCashInfo.getFin_cost());
				ps.setDouble(55, companyCashInfo.getInvest_loss());
				ps.setDouble(56, companyCashInfo.getTax_reduce());
				ps.setDouble(57, companyCashInfo.getDebt_reduce());
				ps.setDouble(58, companyCashInfo.getStock_reduce());
				ps.setDouble(59, companyCashInfo.getRec_project_reduce());
				ps.setDouble(60, companyCashInfo.getPay_project_add());
				ps.setDouble(61, companyCashInfo.getOther());
				ps.setDouble(62, companyCashInfo.getBusiness_cash_netvalue());
				ps.setDouble(63, companyCashInfo.getNon_cash_netvalue());
				ps.setDouble(64, companyCashInfo.getDebt_to_capital());
				ps.setDouble(65, companyCashInfo.getDebt_one_year());
				ps.setDouble(66, companyCashInfo.getCash_to_asset());
				ps.setDouble(67, companyCashInfo.getCash_change());
				ps.setDouble(68, companyCashInfo.getLast_cash_value());
				ps.setDouble(69, companyCashInfo.getReduce_origin_cash());
				ps.setDouble(70, companyCashInfo.getPlus_last_cash());
				ps.setDouble(71, companyCashInfo.getReduce_origin_value());
				ps.setDouble(72, companyCashInfo.getPlus_other_cash());
				ps.setDouble(73, companyCashInfo.getCash_to_netvalue());
				ps.setDouble(74, companyCashInfo.getCustom_to_netvalue());
				ps.setDouble(75, companyCashInfo.getBorrow_netvalue());
				ps.setDouble(76, companyCashInfo.getBorrow_other_netvalue());
				ps.setDouble(77, companyCashInfo.getRec_insurance_cash());
				ps.setDouble(78, companyCashInfo.getRec_insurance_netvalue());
				ps.setDouble(79, companyCashInfo.getInvest_netvalue());
				ps.setDouble(80, companyCashInfo.getDispose_the_cash());
				ps.setDouble(81, companyCashInfo.getRec_interest_cash());
				ps.setDouble(82, companyCashInfo.getCash_netvalue());
				ps.setDouble(83, companyCashInfo.getReturn_cash_netvalue());
				ps.setDouble(84, companyCashInfo.getCustom_netvalue());
				ps.setDouble(85, companyCashInfo.getBank_cash_netvalue());
				ps.setDouble(86, companyCashInfo.getPay_insurance_cash());
				ps.setDouble(87, companyCashInfo.getPay_interest_cash());
				ps.setDouble(88, companyCashInfo.getPay_profit_cash());
				ps.setDouble(89, companyCashInfo.getCash_for_holder());
				ps.setDouble(90, companyCashInfo.getProfit_for_holder());
				ps.setDouble(91, companyCashInfo.getHouse_disount());
				ps.setDouble(92, companyCashInfo.getPush_flag());
			}
			
			@Override
			public int getBatchSize() {
				return cashInfoInsert.size();
			}
		});
	}

	
	/**
	 * 批量修改
	 */
	public void balanceUpdate(final List<CompanyCashInfo> cashInfoUpdate) {
		String sql = "update company_cash_sheet set stock_code=?,publish_date=?,end_date=?,account_date=?,report_period=?,sale_cash=?,"
				+ "tax_return=?,rec_other_cash=?,bussiness_cash_total=?,buy_for_cash=?,pay_emp_cash=?,pay_tax=?,pay_other_cash=?,"
				+ "bussiness_cash_output=?,bussiness_cash_netvalue=?,rec_invest_cash=?,invest_rec_cash=?,dispose_asset_netvalue=?,"
				+ "dispose_other_netvalue=?,rec_otherinvest_cash=?,invest_cash_total=?,buy_asset_cash=?,invest_pay_cash=?,loan_net_addvalue=?,"
				+ "rec_othercompany_cash=?,pay_otherinvest_cash=?,invest_cash_output=?,invest_cash_netvalue=?,rec_invest_reccash=?,rec_borrow_cash=?,"
				+ "publish_rec_cash=?,rec_other_relatecash=?,borrow_cash_total=?,pay_debet_cash=?,interest_pay_cash=?,pay_other_relatecash=?,"
				+ "borrow_cash_outtotal=?,borrow_cash_netvalue=?,rate_to_cash=?,other_to_cash=?,cash_to_netadd=?,origin_cash=?,last_cash=?,"
				+ "addition=?,netvalue_to_cash=?,netvalue=?,plus_asset_loss=?,asset_discount=?,intangible_asset_discount=?,long_cost_discount=?,"
				+ "asset_loss=?,fix_asset_loss=?,value_change_loss=?,fin_cost=?,invest_loss=?,tax_reduce=?,debt_reduce=?,stock_reduce=?,"
				+ "rec_project_reduce=?,pay_project_add=?,other=?,business_cash_netvalue=?,non_cash_netvalue=?,debt_to_capital=?,debt_one_year=?,"
				+ "cash_to_asset=?,cash_change=?,last_cash_value=?,reduce_origin_cash=?,plus_last_cash=?,reduce_origin_value=?,plus_other_cash=?,"
				+ "cash_to_netvalue=?,custom_to_netvalue=?,borrow_netvalue=?,borrow_other_netvalue=?,rec_insurance_cash=?,rec_insurance_netvalue=?,"
				+ "invest_netvalue=?,dispose_the_cash=?,rec_interest_cash=?,cash_netvalue=?,return_cash_netvalue=?,custom_netvalue=?,"
				+ "bank_cash_netvalue=?,pay_insurance_cash=?,pay_interest_cash=?,pay_profit_cash=?,cash_for_holder=?,profit_for_holder=?,"
				+ "house_disount=?,push_flag=? where cash_sheet_id=?";
		
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				CompanyCashInfo companyCashInfo = cashInfoUpdate.get(i);
				
				ps.setString(1, companyCashInfo.getStock_code());
				ps.setString(2, companyCashInfo.getPublish_date());
				ps.setString(3, companyCashInfo.getEnd_date());
				ps.setString(4, companyCashInfo.getAccount_date());
				ps.setInt(5, companyCashInfo.getReport_period());
				ps.setDouble(6, companyCashInfo.getSale_cash());
				ps.setDouble(7, companyCashInfo.getTax_return());
				ps.setDouble(8, companyCashInfo.getRec_other_cash());
				ps.setDouble(9, companyCashInfo.getBussiness_cash_total());
				ps.setDouble(10, companyCashInfo.getBuy_for_cash());
				ps.setDouble(11, companyCashInfo.getPay_emp_cash());
				ps.setDouble(12, companyCashInfo.getPay_tax());
				ps.setDouble(13, companyCashInfo.getPay_other_cash());
				ps.setDouble(14, companyCashInfo.getBussiness_cash_output());
				ps.setDouble(15, companyCashInfo.getBussiness_cash_netvalue());
				ps.setDouble(16, companyCashInfo.getRec_invest_cash());
				ps.setDouble(17, companyCashInfo.getInvest_rec_cash());
				ps.setDouble(18, companyCashInfo.getDispose_asset_netvalue());
				ps.setDouble(19, companyCashInfo.getDispose_other_netvalue());
				ps.setDouble(20, companyCashInfo.getRec_otherinvest_cash());
				ps.setDouble(21, companyCashInfo.getInvest_cash_total());
				ps.setDouble(22, companyCashInfo.getBuy_asset_cash());
				ps.setDouble(23, companyCashInfo.getInvest_pay_cash());
				ps.setDouble(24, companyCashInfo.getLoan_net_addvalue());
				ps.setDouble(25, companyCashInfo.getRec_othercompany_cash());
				ps.setDouble(26, companyCashInfo.getPay_otherinvest_cash());
				ps.setDouble(27, companyCashInfo.getInvest_cash_output());
				ps.setDouble(28, companyCashInfo.getInvest_cash_netvalue());
				ps.setDouble(29, companyCashInfo.getRec_invest_reccash());
				ps.setDouble(30, companyCashInfo.getRec_borrow_cash());
				ps.setDouble(31, companyCashInfo.getPublish_rec_cash());
				ps.setDouble(32, companyCashInfo.getRec_other_relatecash());
				ps.setDouble(33, companyCashInfo.getBorrow_cash_total());
				ps.setDouble(34, companyCashInfo.getPay_debet_cash());
				ps.setDouble(35, companyCashInfo.getInterest_pay_cash());
				ps.setDouble(36, companyCashInfo.getPay_other_relatecash());
				ps.setDouble(37, companyCashInfo.getBorrow_cash_outtotal());
				ps.setDouble(38, companyCashInfo.getBorrow_cash_netvalue());
				ps.setDouble(39, companyCashInfo.getRate_to_cash());
				ps.setDouble(40, companyCashInfo.getOther_to_cash());
				ps.setDouble(41, companyCashInfo.getCash_to_netadd());
				ps.setDouble(42, companyCashInfo.getOrigin_cash());
				ps.setDouble(43, companyCashInfo.getLast_cash());
				ps.setDouble(44, companyCashInfo.getAddition());
				ps.setDouble(45, companyCashInfo.getNetvalue_to_cash());
				ps.setDouble(46, companyCashInfo.getNetvalue());
				ps.setDouble(47, companyCashInfo.getPlus_asset_loss());
				ps.setDouble(48, companyCashInfo.getAsset_discount());
				ps.setDouble(49, companyCashInfo.getIntangible_asset_discount());
				ps.setDouble(50, companyCashInfo.getLong_cost_discount());
				ps.setDouble(51, companyCashInfo.getAsset_loss());
				ps.setDouble(52, companyCashInfo.getFix_asset_loss());
				ps.setDouble(53, companyCashInfo.getValue_change_loss());
				ps.setDouble(54, companyCashInfo.getFin_cost());
				ps.setDouble(55, companyCashInfo.getInvest_loss());
				ps.setDouble(56, companyCashInfo.getTax_reduce());
				ps.setDouble(57, companyCashInfo.getDebt_reduce());
				ps.setDouble(58, companyCashInfo.getStock_reduce());
				ps.setDouble(59, companyCashInfo.getRec_project_reduce());
				ps.setDouble(60, companyCashInfo.getPay_project_add());
				ps.setDouble(61, companyCashInfo.getOther());
				ps.setDouble(62, companyCashInfo.getBusiness_cash_netvalue());
				ps.setDouble(63, companyCashInfo.getNon_cash_netvalue());
				ps.setDouble(64, companyCashInfo.getDebt_to_capital());
				ps.setDouble(65, companyCashInfo.getDebt_one_year());
				ps.setDouble(66, companyCashInfo.getCash_to_asset());
				ps.setDouble(67, companyCashInfo.getCash_change());
				ps.setDouble(68, companyCashInfo.getLast_cash_value());
				ps.setDouble(69, companyCashInfo.getReduce_origin_cash());
				ps.setDouble(70, companyCashInfo.getPlus_last_cash());
				ps.setDouble(71, companyCashInfo.getReduce_origin_value());
				ps.setDouble(72, companyCashInfo.getPlus_other_cash());
				ps.setDouble(73, companyCashInfo.getCash_to_netvalue());
				ps.setDouble(74, companyCashInfo.getCustom_to_netvalue());
				ps.setDouble(75, companyCashInfo.getBorrow_netvalue());
				ps.setDouble(76, companyCashInfo.getBorrow_other_netvalue());
				ps.setDouble(77, companyCashInfo.getRec_insurance_cash());
				ps.setDouble(78, companyCashInfo.getRec_insurance_netvalue());
				ps.setDouble(79, companyCashInfo.getInvest_netvalue());
				ps.setDouble(80, companyCashInfo.getDispose_the_cash());
				ps.setDouble(81, companyCashInfo.getRec_interest_cash());
				ps.setDouble(82, companyCashInfo.getCash_netvalue());
				ps.setDouble(83, companyCashInfo.getReturn_cash_netvalue());
				ps.setDouble(84, companyCashInfo.getCustom_netvalue());
				ps.setDouble(85, companyCashInfo.getBank_cash_netvalue());
				ps.setDouble(86, companyCashInfo.getPay_insurance_cash());
				ps.setDouble(87, companyCashInfo.getPay_interest_cash());
				ps.setDouble(88, companyCashInfo.getPay_profit_cash());
				ps.setDouble(89, companyCashInfo.getCash_for_holder());
				ps.setDouble(90, companyCashInfo.getProfit_for_holder());
				ps.setDouble(91, companyCashInfo.getHouse_disount());
				ps.setInt(92, companyCashInfo.getPush_flag());
				ps.setLong(93, companyCashInfo.getCash_sheet_id());
			}
			
			@Override
			public int getBatchSize() {
				return cashInfoUpdate.size();
			}
		});
	}
	
}
