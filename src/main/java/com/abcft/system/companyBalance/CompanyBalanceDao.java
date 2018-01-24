package com.abcft.system.companyBalance;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.base.RsglBaseDao;
import com.abcft.common.util.StringUtils;

@Repository
@Transactional
public class CompanyBalanceDao extends RsglBaseDao {
	private static Logger logger = LogManager.getLogger(CompanyBalanceDao.class);
	
	/**
	 *  根据相应的条件返回相应的数据进行展示
	 * @param start 开始条数
	 * @param end 查询条数
	 * @param stockCode 查询参数
	 * @return 数据集合
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyBalanceInfo> findList(long start, long end, String stockCode, String end_date_start, String end_date_end, String keyWord){
		StringBuffer sql = new StringBuffer(
				"select balance_sheet_id,stock_code,date_format(publish_date, '%Y-%m-%d') as publish_date,date_format(end_date, '%Y-%m-%d') as end_date,"+
				"date_format(account_date, '%Y-%m-%d') as account_date,report_period,cash,trading_fin_asset,rec_note,rec_account,prepay,"+
				"other_rec_account,rec_relate_account,rec_interest,rec_dividend,stock,consume_asset,non_current_asset,other_current_asset,"+
				"total_current_asset,available_sale_asset,held_investment,long_rec_account,long_equity_investment,invest_house,fix_asset,"+
				"building,project_material,fix_asset_dispose,product_asset,oil_asset,intangible_asset,develop_cost,goodwill,long_defer_cost,"+
				"tax_asset,other_noncurrent_asset,noncurrent_asset_total,asset_total,short_borrow,trading_fin_borrow,pay_note,pay_account,"+
				"prepay_account,pay_salary,pay_tax,pay_interest,pay_dividend,other_pay_account,pay_relate_account,non_current_borrow,"+
				"other_current_borrow,current_borrow_total,long_borrow,pay_bonds,long_pay_account,term_pay_account,pre_bonds,tax_bonds,"+
				"other_noncurrent_bonds,noncurrent_bonds_total,bonds_total,rec_capital,capital_reserve,earn_reserve,reduce_share,"+
				"nopay_profit,monority_holder_equity,exchange_difference,profit_adjust,equity_total,all_total,parent_equity,total_depre_amor,"+
				"push_flag from company_balance_sheet where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(keyWord) && "keyWord".equals(keyWord)){
			sql.append(" group by stock_code,publish_date  having count(stock_code)>1 and count(publish_date)>1");
		}else{
			if(!StringUtils.isEmpty(stockCode)){
				sql.append(" and stock_code like '%" + stockCode + "%' ");
			}
			if(!StringUtils.isEmpty(end_date_start)){
				sql.append(" and end_date>=?");
				params.add(end_date_start);
			}
			if(!StringUtils.isEmpty(end_date_end)){
				sql.append(" and end_date<=?");
				params.add(end_date_end);
			}
		}
		
		sql.append(" limit ?,?");
		params.add(start);
		params.add(end);
		logger.info(sql);
		logger.info(params);
		
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(CompanyBalanceInfo.class), params.toArray());
	}

	/**
	 * 统计数据总量
	 * @param stockCode 查询条件
	 * @return 数量值
	 */
	public Long getTotalRole(String stockCode, String end_date_start, String end_date_end, String keyWord) {
		StringBuffer sql = new StringBuffer("select count(*) from company_balance_sheet where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(keyWord) && "keyWord".equals(keyWord)){
			sql = new StringBuffer("select count(*) from (select count(*) from company_balance_sheet"
					+ " group by stock_code,publish_date  having count(stock_code)>1 and count(publish_date)>1) T");
		}else{
			if(!StringUtils.isEmpty(stockCode)){
				sql.append(" and stock_code like '%" + stockCode + "%' ");
			}
			if(!StringUtils.isEmpty(end_date_start)){
				sql.append(" and end_date>=?");
				params.add(end_date_start);
			}
			if(!StringUtils.isEmpty(end_date_end)){
				sql.append(" and end_date<=?");
				params.add(end_date_end);
			}
		}
		
		return getJdbcTemplate().queryForLong(sql.toString(), params.toArray());
	}
	
	/**
	 * 添加负债资产信息
	 */
	public void insertCompanyBalanceInfo(CompanyBalanceInfo companyBalanceInfo) {
		String sql = "insert into company_balance_sheet (stock_code,publish_date,end_date,account_date,report_period,"+
			"cash,trading_fin_asset,rec_note,rec_account,prepay,other_rec_account,rec_relate_account,rec_interest,rec_dividend,stock,"+
			"consume_asset,non_current_asset,other_current_asset,total_current_asset,available_sale_asset,held_investment,long_rec_account,"+
			"long_equity_investment,invest_house,fix_asset,building,project_material,fix_asset_dispose,product_asset,oil_asset,intangible_asset"
			+ ",develop_cost,goodwill,long_defer_cost,tax_asset,other_noncurrent_asset,noncurrent_asset_total,asset_total,short_borrow,"+
			"trading_fin_borrow,pay_note,pay_account,prepay_account,pay_salary,pay_tax,pay_interest,pay_dividend,other_pay_account,"+
			"pay_relate_account,non_current_borrow,other_current_borrow,current_borrow_total,long_borrow,pay_bonds,long_pay_account,"+
			"term_pay_account,pre_bonds,tax_bonds,other_noncurrent_bonds,noncurrent_bonds_total,bonds_total,rec_capital,capital_reserve,"+
			"earn_reserve,reduce_share,nopay_profit,monority_holder_equity,exchange_difference,profit_adjust,equity_total,all_total,"+
			"parent_equity,total_depre_amor,push_flag) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+
				"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> params = getCompanyBalanceInfo(companyBalanceInfo);
		
		getJdbcTemplate().update(sql, params.toArray());
	}
	
	/**
	 * 提取相同部分，方便代码的重用
	 */
	private List<Object> getCompanyBalanceInfo(CompanyBalanceInfo companyBalanceInfo){
		List<Object> list = new ArrayList<Object>();
		
		list.add(companyBalanceInfo.getStock_code());
		list.add(companyBalanceInfo.getPublish_date());
		list.add(companyBalanceInfo.getEnd_date());
		list.add(companyBalanceInfo.getAccount_date());
		list.add(companyBalanceInfo.getReport_period());
		list.add(companyBalanceInfo.getCash());
		list.add(companyBalanceInfo.getTrading_fin_asset());
		list.add(companyBalanceInfo.getRec_note());
		list.add(companyBalanceInfo.getRec_account());
		list.add(companyBalanceInfo.getPrepay());
		list.add(companyBalanceInfo.getOther_rec_account());
		list.add(companyBalanceInfo.getRec_relate_account());
		list.add(companyBalanceInfo.getRec_interest());
		list.add(companyBalanceInfo.getRec_dividend());
		list.add(companyBalanceInfo.getStock());
		list.add(companyBalanceInfo.getConsume_asset());
		list.add(companyBalanceInfo.getNon_current_asset());
		list.add(companyBalanceInfo.getOther_current_asset());
		list.add(companyBalanceInfo.getTotal_current_asset());
		list.add(companyBalanceInfo.getAvailable_sale_asset());
		list.add(companyBalanceInfo.getHeld_investment());
		list.add(companyBalanceInfo.getLong_rec_account());
		list.add(companyBalanceInfo.getLong_equity_investment());
		list.add(companyBalanceInfo.getInvest_house());
		list.add(companyBalanceInfo.getFix_asset());
		list.add(companyBalanceInfo.getBuilding());
		list.add(companyBalanceInfo.getProject_material());
		list.add(companyBalanceInfo.getFix_asset_dispose());
		list.add(companyBalanceInfo.getProduct_asset());
		list.add(companyBalanceInfo.getOil_asset());
		list.add(companyBalanceInfo.getIntangible_asset());
		list.add(companyBalanceInfo.getDevelop_cost());
		list.add(companyBalanceInfo.getGoodwill());
		list.add(companyBalanceInfo.getLong_defer_cost());
		list.add(companyBalanceInfo.getTax_asset());
		list.add(companyBalanceInfo.getOther_noncurrent_asset());
		list.add(companyBalanceInfo.getNoncurrent_asset_total());
		list.add(companyBalanceInfo.getAsset_total());
		list.add(companyBalanceInfo.getShort_borrow());
		list.add(companyBalanceInfo.getTrading_fin_borrow());
		list.add(companyBalanceInfo.getPay_note());
		list.add(companyBalanceInfo.getPay_account());
		list.add(companyBalanceInfo.getPrepay_account());
		list.add(companyBalanceInfo.getPay_salary());
		list.add(companyBalanceInfo.getPay_tax());
		list.add(companyBalanceInfo.getPay_interest());
		list.add(companyBalanceInfo.getPay_dividend());
		list.add(companyBalanceInfo.getOther_pay_account());
		list.add(companyBalanceInfo.getPay_relate_account());
		list.add(companyBalanceInfo.getNon_current_borrow());
		list.add(companyBalanceInfo.getOther_current_borrow());
		list.add(companyBalanceInfo.getCurrent_borrow_total());
		list.add(companyBalanceInfo.getLong_borrow());
		list.add(companyBalanceInfo.getPay_bonds());
		list.add(companyBalanceInfo.getLong_pay_account());
		list.add(companyBalanceInfo.getTerm_pay_account());
		list.add(companyBalanceInfo.getPre_bonds());
		list.add(companyBalanceInfo.getTax_bonds());
		list.add(companyBalanceInfo.getOther_noncurrent_bonds());
		list.add(companyBalanceInfo.getNoncurrent_bonds_total());
		list.add(companyBalanceInfo.getBonds_total());
		list.add(companyBalanceInfo.getRec_capital());
		list.add(companyBalanceInfo.getCapital_reserve());
		list.add(companyBalanceInfo.getEarn_reserve());
		list.add(companyBalanceInfo.getReduce_share());
		list.add(companyBalanceInfo.getNopay_profit());
		list.add(companyBalanceInfo.getMonority_holder_equity());
		list.add(companyBalanceInfo.getExchange_difference());
		list.add(companyBalanceInfo.getProfit_adjust());
		list.add(companyBalanceInfo.getEquity_total());
		list.add(companyBalanceInfo.getAll_total());
		list.add(companyBalanceInfo.getParent_equity());
		list.add(companyBalanceInfo.getTotal_depre_amor());
		list.add(companyBalanceInfo.getPush_flag());
		
		return list;
	}
	
	/**
	 * 修改负债资产信息
	 */
	public void updateCompanyBalanceInfo(CompanyBalanceInfo companyBalanceInfo) {
		String sql = "update company_balance_sheet set stock_code=?,publish_date=?,end_date=?,account_date=?,report_period=?,cash=?,"+
			"trading_fin_asset=?,rec_note=?,rec_account=?,prepay=?,other_rec_account=?,rec_relate_account=?,rec_interest=?,rec_dividend=?,"+
			"stock=?,consume_asset=?,non_current_asset=?,other_current_asset=?,total_current_asset=?,available_sale_asset=?,"+
			"held_investment=?,long_rec_account=?,long_equity_investment=?,invest_house=?,fix_asset=?,building=?,project_material=?,"+
			"fix_asset_dispose=?,product_asset=?,oil_asset=?,intangible_asset=?,develop_cost=?,goodwill=?,long_defer_cost=?,tax_asset=?,"+
			"other_noncurrent_asset=?,noncurrent_asset_total=?,asset_total=?,short_borrow=?,trading_fin_borrow=?,pay_note=?,"+
			"pay_account=?,prepay_account=?,pay_salary=?,pay_tax=?,pay_interest=?,pay_dividend=?,other_pay_account=?,pay_relate_account=?,"+
			"non_current_borrow=?,other_current_borrow=?,current_borrow_total=?,long_borrow=?,pay_bonds=?,long_pay_account=?,"+
			"term_pay_account=?,pre_bonds=?,tax_bonds=?,other_noncurrent_bonds=?,noncurrent_bonds_total=?,bonds_total=?,rec_capital=?,"+
			"capital_reserve=?,earn_reserve=?,reduce_share=?,nopay_profit=?,monority_holder_equity=?,exchange_difference=?,profit_adjust=?,"+
			"equity_total=?,all_total=?,parent_equity=?,total_depre_amor=?,push_flag=? where balance_sheet_id=?;";
		
		List<Object> list = getCompanyBalanceInfo(companyBalanceInfo);
		list.add(companyBalanceInfo.getBalance_sheet_id());
		getJdbcTemplate().update(sql, list.toArray());
	}
	
	/**
	 * 使用主键ID删除对应的数据
	 */
	public int removeCompanyBalanceInfo(String balance_sheet_id) {
		String sql = "delete from company_balance_sheet where balance_sheet_id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(balance_sheet_id);
		
		return getJdbcTemplate().update(sql, list.toArray());
	}
	
	/**
	 * 判断是否在数据库中已存在相同数据
	 * @param stock_code 股票编号
	 * @param publish_date 公告日期
	 * @return 查询到的数量
	 */
	public List<CompanyBalanceInfo> findIsRepeat(String stock_code, String publish_date) {
		String sql = "select balance_sheet_id from company_balance_sheet where stock_code=? and publish_date=?;";
		List<Object> list = new ArrayList<Object>();
		list.add(stock_code);
		list.add(publish_date);
		
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(CompanyBalanceInfo.class), list.toArray());
	}
	
	/**
	 * 批量添加资产负债数据信息
	 */
	public void balanceInsert(final List<CompanyBalanceInfo> balaceInsert) {
		String sql = "insert into company_balance_sheet (stock_code,publish_date,end_date,account_date,report_period,"+
				"cash,trading_fin_asset,rec_note,rec_account,prepay,other_rec_account,rec_relate_account,rec_interest,rec_dividend,stock,"+
				"consume_asset,non_current_asset,other_current_asset,total_current_asset,available_sale_asset,held_investment,long_rec_account,"+
				"long_equity_investment,invest_house,fix_asset,building,project_material,fix_asset_dispose,product_asset,oil_asset,intangible_asset"
				+ ",develop_cost,goodwill,long_defer_cost,tax_asset,other_noncurrent_asset,noncurrent_asset_total,asset_total,short_borrow,"+
				"trading_fin_borrow,pay_note,pay_account,prepay_account,pay_salary,pay_tax,pay_interest,pay_dividend,other_pay_account,"+
				"pay_relate_account,non_current_borrow,other_current_borrow,current_borrow_total,long_borrow,pay_bonds,long_pay_account,"+
				"term_pay_account,pre_bonds,tax_bonds,other_noncurrent_bonds,noncurrent_bonds_total,bonds_total,rec_capital,capital_reserve,"+
				"earn_reserve,reduce_share,nopay_profit,monority_holder_equity,exchange_difference,profit_adjust,equity_total,all_total,"+
				"parent_equity,total_depre_amor,push_flag) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+
					"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				CompanyBalanceInfo companyBalanceInfo = balaceInsert.get(i);
				ps.setString(1, companyBalanceInfo.getStock_code());
				ps.setString(2, companyBalanceInfo.getPublish_date());
				ps.setString(3, companyBalanceInfo.getEnd_date());
				ps.setString(4, companyBalanceInfo.getAccount_date());
				ps.setLong(5, companyBalanceInfo.getReport_period());
				ps.setDouble(6, companyBalanceInfo.getCash());
				ps.setDouble(7, companyBalanceInfo.getTrading_fin_asset());
				ps.setDouble(8, companyBalanceInfo.getRec_note());
				ps.setDouble(9, companyBalanceInfo.getRec_account());
				ps.setDouble(10, companyBalanceInfo.getPrepay());
				ps.setDouble(11, companyBalanceInfo.getOther_rec_account());
				ps.setDouble(12, companyBalanceInfo.getRec_relate_account());
				ps.setDouble(13, companyBalanceInfo.getRec_interest());
				ps.setDouble(14, companyBalanceInfo.getRec_dividend());
				ps.setDouble(15, companyBalanceInfo.getStock());
				ps.setDouble(16, companyBalanceInfo.getConsume_asset());
				ps.setDouble(17, companyBalanceInfo.getNon_current_asset());
				ps.setDouble(18, companyBalanceInfo.getOther_current_asset());
				ps.setDouble(19, companyBalanceInfo.getTotal_current_asset());
				ps.setDouble(20, companyBalanceInfo.getAvailable_sale_asset());
				ps.setDouble(21, companyBalanceInfo.getHeld_investment());
				ps.setDouble(22, companyBalanceInfo.getLong_rec_account());
				ps.setDouble(23, companyBalanceInfo.getLong_equity_investment());
				ps.setDouble(24, companyBalanceInfo.getInvest_house());
				ps.setDouble(25, companyBalanceInfo.getFix_asset());
				ps.setDouble(26, companyBalanceInfo.getBuilding());
				ps.setDouble(27, companyBalanceInfo.getProject_material());
				ps.setDouble(28, companyBalanceInfo.getFix_asset_dispose());
				ps.setDouble(29, companyBalanceInfo.getProduct_asset());
				ps.setDouble(30, companyBalanceInfo.getOil_asset());
				ps.setDouble(31, companyBalanceInfo.getIntangible_asset());
				ps.setDouble(32, companyBalanceInfo.getDevelop_cost());
				ps.setDouble(33, companyBalanceInfo.getGoodwill());
				ps.setDouble(34, companyBalanceInfo.getLong_defer_cost());
				ps.setDouble(35, companyBalanceInfo.getTax_asset());
				ps.setDouble(36, companyBalanceInfo.getOther_noncurrent_asset());
				ps.setDouble(37, companyBalanceInfo.getNoncurrent_asset_total());
				ps.setDouble(38, companyBalanceInfo.getAsset_total());
				ps.setDouble(39, companyBalanceInfo.getShort_borrow());
				ps.setDouble(40, companyBalanceInfo.getTrading_fin_borrow());
				ps.setDouble(41, companyBalanceInfo.getPay_note());
				ps.setDouble(42, companyBalanceInfo.getPay_account());
				ps.setDouble(43, companyBalanceInfo.getPrepay_account());
				ps.setDouble(44, companyBalanceInfo.getPay_salary());
				ps.setDouble(45, companyBalanceInfo.getPay_tax());
				ps.setDouble(46, companyBalanceInfo.getPay_interest());
				ps.setDouble(47, companyBalanceInfo.getPay_dividend());
				ps.setDouble(48, companyBalanceInfo.getOther_pay_account());
				ps.setDouble(49, companyBalanceInfo.getPay_relate_account());
				ps.setDouble(50, companyBalanceInfo.getNon_current_borrow());
				ps.setDouble(51, companyBalanceInfo.getOther_current_borrow());
				ps.setDouble(52, companyBalanceInfo.getCurrent_borrow_total());
				ps.setDouble(53, companyBalanceInfo.getLong_borrow());
				ps.setDouble(54, companyBalanceInfo.getPay_bonds());
				ps.setDouble(55, companyBalanceInfo.getLong_pay_account());
				ps.setDouble(56, companyBalanceInfo.getTerm_pay_account());
				ps.setDouble(57, companyBalanceInfo.getPre_bonds());
				ps.setDouble(58, companyBalanceInfo.getTax_bonds());
				ps.setDouble(59, companyBalanceInfo.getOther_noncurrent_bonds());
				ps.setDouble(60, companyBalanceInfo.getNoncurrent_bonds_total());
				ps.setDouble(61, companyBalanceInfo.getBonds_total());
				ps.setDouble(62, companyBalanceInfo.getRec_capital());
				ps.setDouble(63, companyBalanceInfo.getCapital_reserve());
				ps.setDouble(64, companyBalanceInfo.getEarn_reserve());
				ps.setDouble(65, companyBalanceInfo.getReduce_share());
				ps.setDouble(66, companyBalanceInfo.getNopay_profit());
				ps.setDouble(67, companyBalanceInfo.getMonority_holder_equity());
				ps.setDouble(68, companyBalanceInfo.getExchange_difference());
				ps.setDouble(69, companyBalanceInfo.getProfit_adjust());
				ps.setDouble(70, companyBalanceInfo.getEquity_total());
				ps.setDouble(71, companyBalanceInfo.getAll_total());
				ps.setDouble(72, companyBalanceInfo.getParent_equity());
				ps.setDouble(73, companyBalanceInfo.getTotal_depre_amor());
				ps.setInt(74, companyBalanceInfo.getPush_flag());
			}
			
			@Override
			public int getBatchSize() {
				return balaceInsert.size();
			}
		});
	}

	/**
	 * 批量修改资产负债信息
	 */
	public void balanceUpdate(final List<CompanyBalanceInfo> balaceUpdate) {
		String sql = "update company_balance_sheet set stock_code=?,publish_date=?,end_date=?,account_date=?,report_period=?,cash=?,"+
				"trading_fin_asset=?,rec_note=?,rec_account=?,prepay=?,other_rec_account=?,rec_relate_account=?,rec_interest=?,rec_dividend=?,"+
				"stock=?,consume_asset=?,non_current_asset=?,other_current_asset=?,total_current_asset=?,available_sale_asset=?,"+
				"held_investment=?,long_rec_account=?,long_equity_investment=?,invest_house=?,fix_asset=?,building=?,project_material=?,"+
				"fix_asset_dispose=?,product_asset=?,oil_asset=?,intangible_asset=?,develop_cost=?,goodwill=?,long_defer_cost=?,tax_asset=?,"+
				"other_noncurrent_asset=?,noncurrent_asset_total=?,asset_total=?,short_borrow=?,trading_fin_borrow=?,pay_note=?,"+
				"pay_account=?,prepay_account=?,pay_salary=?,pay_tax=?,pay_interest=?,pay_dividend=?,other_pay_account=?,pay_relate_account=?,"+
				"non_current_borrow=?,other_current_borrow=?,current_borrow_total=?,long_borrow=?,pay_bonds=?,long_pay_account=?,"+
				"term_pay_account=?,pre_bonds=?,tax_bonds=?,other_noncurrent_bonds=?,noncurrent_bonds_total=?,bonds_total=?,rec_capital=?,"+
				"capital_reserve=?,earn_reserve=?,reduce_share=?,nopay_profit=?,monority_holder_equity=?,exchange_difference=?,profit_adjust=?,"+
				"equity_total=?,all_total=?,parent_equity=?,total_depre_amor=?,push_flag=? where balance_sheet_id=?;";
		
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				CompanyBalanceInfo companyBalanceInfo = balaceUpdate.get(i);
				ps.setString(1, companyBalanceInfo.getStock_code());
				ps.setString(2, companyBalanceInfo.getPublish_date());
				ps.setString(3, companyBalanceInfo.getEnd_date());
				ps.setString(4, companyBalanceInfo.getAccount_date());
				ps.setLong(5, companyBalanceInfo.getReport_period());
				ps.setDouble(6, companyBalanceInfo.getCash());
				ps.setDouble(7, companyBalanceInfo.getTrading_fin_asset());
				ps.setDouble(8, companyBalanceInfo.getRec_note());
				ps.setDouble(9, companyBalanceInfo.getRec_account());
				ps.setDouble(10, companyBalanceInfo.getPrepay());
				ps.setDouble(11, companyBalanceInfo.getOther_rec_account());
				ps.setDouble(12, companyBalanceInfo.getRec_relate_account());
				ps.setDouble(13, companyBalanceInfo.getRec_interest());
				ps.setDouble(14, companyBalanceInfo.getRec_dividend());
				ps.setDouble(15, companyBalanceInfo.getStock());
				ps.setDouble(16, companyBalanceInfo.getConsume_asset());
				ps.setDouble(17, companyBalanceInfo.getNon_current_asset());
				ps.setDouble(18, companyBalanceInfo.getOther_current_asset());
				ps.setDouble(19, companyBalanceInfo.getTotal_current_asset());
				ps.setDouble(20, companyBalanceInfo.getAvailable_sale_asset());
				ps.setDouble(21, companyBalanceInfo.getHeld_investment());
				ps.setDouble(22, companyBalanceInfo.getLong_rec_account());
				ps.setDouble(23, companyBalanceInfo.getLong_equity_investment());
				ps.setDouble(24, companyBalanceInfo.getInvest_house());
				ps.setDouble(25, companyBalanceInfo.getFix_asset());
				ps.setDouble(26, companyBalanceInfo.getBuilding());
				ps.setDouble(27, companyBalanceInfo.getProject_material());
				ps.setDouble(28, companyBalanceInfo.getFix_asset_dispose());
				ps.setDouble(29, companyBalanceInfo.getProduct_asset());
				ps.setDouble(30, companyBalanceInfo.getOil_asset());
				ps.setDouble(31, companyBalanceInfo.getIntangible_asset());
				ps.setDouble(32, companyBalanceInfo.getDevelop_cost());
				ps.setDouble(33, companyBalanceInfo.getGoodwill());
				ps.setDouble(34, companyBalanceInfo.getLong_defer_cost());
				ps.setDouble(35, companyBalanceInfo.getTax_asset());
				ps.setDouble(36, companyBalanceInfo.getOther_noncurrent_asset());
				ps.setDouble(37, companyBalanceInfo.getNoncurrent_asset_total());
				ps.setDouble(38, companyBalanceInfo.getAsset_total());
				ps.setDouble(39, companyBalanceInfo.getShort_borrow());
				ps.setDouble(40, companyBalanceInfo.getTrading_fin_borrow());
				ps.setDouble(41, companyBalanceInfo.getPay_note());
				ps.setDouble(42, companyBalanceInfo.getPay_account());
				ps.setDouble(43, companyBalanceInfo.getPrepay_account());
				ps.setDouble(44, companyBalanceInfo.getPay_salary());
				ps.setDouble(45, companyBalanceInfo.getPay_tax());
				ps.setDouble(46, companyBalanceInfo.getPay_interest());
				ps.setDouble(47, companyBalanceInfo.getPay_dividend());
				ps.setDouble(48, companyBalanceInfo.getOther_pay_account());
				ps.setDouble(49, companyBalanceInfo.getPay_relate_account());
				ps.setDouble(50, companyBalanceInfo.getNon_current_borrow());
				ps.setDouble(51, companyBalanceInfo.getOther_current_borrow());
				ps.setDouble(52, companyBalanceInfo.getCurrent_borrow_total());
				ps.setDouble(53, companyBalanceInfo.getLong_borrow());
				ps.setDouble(54, companyBalanceInfo.getPay_bonds());
				ps.setDouble(55, companyBalanceInfo.getLong_pay_account());
				ps.setDouble(56, companyBalanceInfo.getTerm_pay_account());
				ps.setDouble(57, companyBalanceInfo.getPre_bonds());
				ps.setDouble(58, companyBalanceInfo.getTax_bonds());
				ps.setDouble(59, companyBalanceInfo.getOther_noncurrent_bonds());
				ps.setDouble(60, companyBalanceInfo.getNoncurrent_bonds_total());
				ps.setDouble(61, companyBalanceInfo.getBonds_total());
				ps.setDouble(62, companyBalanceInfo.getRec_capital());
				ps.setDouble(63, companyBalanceInfo.getCapital_reserve());
				ps.setDouble(64, companyBalanceInfo.getEarn_reserve());
				ps.setDouble(65, companyBalanceInfo.getReduce_share());
				ps.setDouble(66, companyBalanceInfo.getNopay_profit());
				ps.setDouble(67, companyBalanceInfo.getMonority_holder_equity());
				ps.setDouble(68, companyBalanceInfo.getExchange_difference());
				ps.setDouble(69, companyBalanceInfo.getProfit_adjust());
				ps.setDouble(70, companyBalanceInfo.getEquity_total());
				ps.setDouble(71, companyBalanceInfo.getAll_total());
				ps.setDouble(72, companyBalanceInfo.getParent_equity());
				ps.setDouble(73, companyBalanceInfo.getTotal_depre_amor());
				ps.setInt(74, companyBalanceInfo.getPush_flag());
				ps.setLong(75, companyBalanceInfo.getBalance_sheet_id());
			}
			
			@Override
			public int getBatchSize() {
				return balaceUpdate.size();
			}
		});
	}
	
}
