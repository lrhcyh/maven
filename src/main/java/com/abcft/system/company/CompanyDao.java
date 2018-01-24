package com.abcft.system.company;

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
import com.abcft.common.util.StringUtils;

/**
 * 系统用户   	数据库操作
 * @author Administrator
 *
 */
@Repository
@Transactional
public class CompanyDao extends RsglBaseDao {
	

	private static final Logger logger = LogManager.getLogger(CompanyDao.class);

	/**
	 * 查询公司信息
	 * @param start
	 * @param end
	 * @param stockCode
	 * @param stockName
	 * @param keyword
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyBasicinfo> findCompanyList(long start, long end, String stockCode, String stockName, String keyword) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		if (keyword != null && "distinct".equals(keyword)) {
			sql = new StringBuffer(" select * from  company_basicinfo group by stock_code having count(stock_code) > 1 ");
		} else {
			sql = new StringBuffer(" select * from  company_basicinfo  where 1=1 ");
			if(!StringUtils.isEmpty(stockCode)) {
				sql.append("  AND stock_code like '%"+ stockCode +"%' ");
			}
			if(!StringUtils.isEmpty(stockName)) {
				sql.append(" AND stock_name like '%"+stockName+"%'  ");
			}
			
			sql.append(" limit ?,? ");  
			params.add(start);
			params.add(end);
		}
		
		logger.info(sql);
		logger.info(params);
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(CompanyBasicinfo.class), params.toArray());
	}

	//获得总条数
	@SuppressWarnings("deprecation")
	public Long getTotalRole(String stockCode,String stockName, String keyword) {
		StringBuffer sql = new StringBuffer();
		if (keyword != null && "distinct".equals(keyword)) {
			sql = new StringBuffer(" select count(*) from (select * from company_basicinfo group by stock_code having count(stock_code) > 1) T ");
		} else {
			sql = new StringBuffer(" select count(*) from (select * from  company_basicinfo  where 1=1 ");
			
			if(!StringUtils.isEmpty(stockCode)) {
				sql.append("  AND stock_code like '%"+ stockCode +"%' ");
			}
			if(!StringUtils.isEmpty(stockName)) {
				sql.append(" AND stock_name like '%" + stockName + "%'  ");
			}
			sql.append(" ) T ");
		}
		
		return getJdbcTemplate().queryForLong(sql.toString());
	}

	public int remove(String id) {
		String sql = "delete  from company_basicinfo  where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return getJdbcTemplate().update(sql,params.toArray());
		
	}

	/**
	 * 插入公司信息
	 * @param companyInfo
	 */
	@SuppressWarnings("deprecation")
	public Long insertCompanyBasicinfo(CompanyBasicinfo companyInfo) {
		String sql = " insert into company_basicinfo (stock_code,stock_category,stock_name,stock_alias,company_name,"
				+ "company_ename,industry_name_csrc,industry_code_csrc,industry_code,industry_name,found_date,"
				+ "register_capital,legal_man,stock_type,ipo_date,ipo_main_saler,concept,province,city,company_character,"
				+ "directors,emp_sum,register_number,register_address,office_address,majo_rproduct_type,major_product_name,"
				+ "phone,zipcode,fax,website,email,link_man,company_brief,business,wind_code,closing_price,price_date,"
				+ "board_chair_men,register_addressCopy)  "
				+ "values (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?);";
		
		List<Object> list = new ArrayList<Object>();
		list.add(StringUtils.parseValue(companyInfo.getStock_code()));
		list.add(StringUtils.parseValue(companyInfo.getStock_category()));
		list.add(StringUtils.parseValue(companyInfo.getStock_name()));
		list.add(StringUtils.parseValue(StringUtils.parseValue(companyInfo.getStock_alias())));
		list.add(StringUtils.parseValue(companyInfo.getCompany_name()));
		list.add(StringUtils.parseValue(companyInfo.getCompany_ename()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_name_csrc()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_code_csrc()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_code()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_name()));
		list.add(DateUtils.parseDate(companyInfo.getFound_date()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_capital()));
		list.add(StringUtils.parseValue(companyInfo.getLegal_man()));
		list.add(StringUtils.parseValue(companyInfo.getStock_type()));
		list.add(DateUtils.parseDate(companyInfo.getIpo_date()));
		list.add(StringUtils.parseValue(companyInfo.getIpo_main_saler()));
		list.add(StringUtils.parseValue(companyInfo.getConcept()));
		list.add(StringUtils.parseValue(companyInfo.getProvince()));
		list.add(StringUtils.parseValue(companyInfo.getCity()));
		list.add(StringUtils.parseValue(companyInfo.getCompany_character()));
		list.add(StringUtils.parseValue(companyInfo.getDirectors()));
		list.add(StringUtils.parseValue(companyInfo.getEmp_sum()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_number()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_address()));
		list.add(StringUtils.parseValue(companyInfo.getOffice_address()));
		list.add(StringUtils.parseValue(companyInfo.getMajor_product_type()));
		list.add(StringUtils.parseValue(companyInfo.getMajor_product_name()));
		list.add(StringUtils.parseValue(companyInfo.getPhone()));
		list.add(StringUtils.parseValue(companyInfo.getZipcode()));
		list.add(StringUtils.parseValue(companyInfo.getFax()));
		list.add(StringUtils.parseValue(companyInfo.getWebsite()));
		list.add(StringUtils.parseValue(companyInfo.getEmail()));
		list.add(StringUtils.parseValue(companyInfo.getLink_main()));
		list.add(StringUtils.parseValue(companyInfo.getCompany_brief()));
		list.add(StringUtils.parseValue(companyInfo.getBusiness()));
		list.add(StringUtils.parseValue(companyInfo.getWind_code()));
		list.add(StringUtils.parseValue(companyInfo.getClosing_price()));
		list.add(DateUtils.parseDate(companyInfo.getPrice_date()));
		list.add(StringUtils.parseValue(companyInfo.getBoard_chair_men()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_addressCopy()));
		
		getJdbcTemplate().update(sql.toString(), list.toArray());
		
		String sql2 = "SELECT ID FROM company_basicinfo WHERE ID=@@IDENTITY";
		
		return getJdbcTemplate().queryForLong(sql2);
	}

	/**
	 * 更新公司信息
	 * @param companyInfo
	 */
	public void updateCompanyBasicinfo(CompanyBasicinfo companyInfo) {
		String sql = " update company_basicinfo set stock_code=?,stock_category=?,stock_name=?,stock_alias=?,company_name=?,"
				+ "company_ename=?,industry_name_csrc=?,industry_code_csrc=?,industry_code=?,industry_name=?,found_date=?,"
				+ "register_capital=?,legal_man=?,stock_type=?,ipo_date=?,ipo_main_saler=?,concept=?,province=?,city=?,company_character=?,"
				+ "directors=?,emp_sum=?,register_number=?,register_address=?,office_address=?,majo_rproduct_type=?,major_product_name=?,"
				+ "phone=?,zipcode=?,fax=?,website=?,email=?,link_man=?,company_brief=?,business=?,wind_code=?,closing_price=?,price_date=?,"
				+ "board_chair_men=?,register_addressCopy=? "
				+ "where id=?;";
		
		List<Object> list = new ArrayList<Object>();
		list.add(StringUtils.parseValue(companyInfo.getStock_code()));
		list.add(StringUtils.parseValue(companyInfo.getStock_category()));
		list.add(StringUtils.parseValue(companyInfo.getStock_name()));
		list.add(StringUtils.parseValue(companyInfo.getStock_alias()));
		list.add(StringUtils.parseValue(companyInfo.getCompany_name()));
		list.add(StringUtils.parseValue(companyInfo.getCompany_ename()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_name_csrc()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_code_csrc()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_code()));
		list.add(StringUtils.parseValue(companyInfo.getIndustry_name()));
		list.add(DateUtils.parseDate(companyInfo.getFound_date()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_capital()));
		list.add(StringUtils.parseValue(companyInfo.getLegal_man()));
		list.add(StringUtils.parseValue(companyInfo.getStock_type()));
		list.add(DateUtils.parseDate(companyInfo.getIpo_date()));
		list.add(StringUtils.parseValue(companyInfo.getIpo_main_saler()));
		list.add(StringUtils.parseValue(companyInfo.getConcept()));
		list.add(StringUtils.parseValue(companyInfo.getProvince()));
		list.add(StringUtils.parseValue(companyInfo.getCity()));
		list.add(StringUtils.parseValue(companyInfo.getCompany_character()));
		list.add(StringUtils.parseValue(companyInfo.getDirectors()));
		list.add(StringUtils.parseValue(companyInfo.getEmp_sum()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_number()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_address()));
		list.add(StringUtils.parseValue(companyInfo.getOffice_address()));
		list.add(StringUtils.parseValue(companyInfo.getMajor_product_type()));
		list.add(StringUtils.parseValue(companyInfo.getMajor_product_name()));
		list.add(StringUtils.parseValue(companyInfo.getPhone()));
		list.add(StringUtils.parseValue(companyInfo.getZipcode()));
		list.add(StringUtils.parseValue(companyInfo.getFax()));
		list.add(StringUtils.parseValue(companyInfo.getWebsite()));
		list.add(StringUtils.parseValue(companyInfo.getEmail()));
		list.add(StringUtils.parseValue(companyInfo.getLink_main()));
		list.add(StringUtils.parseValue(companyInfo.getCompany_brief()));
		list.add(StringUtils.parseValue(companyInfo.getBusiness()));
		list.add(StringUtils.parseValue(companyInfo.getWind_code()));
		list.add(StringUtils.parseValue(companyInfo.getClosing_price()));
		list.add(DateUtils.parseDate(companyInfo.getPrice_date()));
		list.add(StringUtils.parseValue(companyInfo.getBoard_chair_men()));
		list.add(StringUtils.parseValue(companyInfo.getRegister_addressCopy()));
		list.add(StringUtils.parseValue(companyInfo.getId()));
		
		getJdbcTemplate().update(sql.toString(), list.toArray());
	}
	
	public void batchInsertCompanyBasicinfo(final List<CompanyBasicinfo> companyBasicinfos) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getJdbcTemplate().getDataSource().getConnection(); 
			conn.setAutoCommit(false);
			
			String sql = " insert into company_basicinfo (stock_code,stock_category,stock_name,stock_alias,company_name,"
					+ "company_ename,industry_name_csrc,industry_code_csrc,industry_code,industry_name,found_date,"
					+ "register_capital,legal_man,stock_type,ipo_date,ipo_main_saler,concept,province,city,company_character,"
					+ "directors,emp_sum,register_number,register_address,office_address,majo_rproduct_type,major_product_name,"
					+ "phone,zipcode,fax,website,email,link_man,company_brief,business,wind_code,closing_price,price_date,"
					+ "board_chair_men,register_addressCopy)  "
					+ "values (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?);";
			
			ps = conn.prepareStatement(sql);
			for (CompanyBasicinfo companyInfo : companyBasicinfos) {
				ps.setObject(1, StringUtils.parseValue(companyInfo.getStock_code()));
				ps.setObject(2, StringUtils.parseValue(companyInfo.getStock_category()));
				ps.setObject(3, StringUtils.parseValue(companyInfo.getStock_name()));
				ps.setObject(4, StringUtils.parseValue(companyInfo.getStock_alias()));
				ps.setObject(5, StringUtils.parseValue(companyInfo.getCompany_name()));
				ps.setObject(6, StringUtils.parseValue(companyInfo.getCompany_ename()));
				ps.setObject(7, StringUtils.parseValue(companyInfo.getIndustry_name_csrc()));
				ps.setObject(8, StringUtils.parseValue(companyInfo.getIndustry_code_csrc()));
				ps.setObject(9, StringUtils.parseValue(companyInfo.getIndustry_code()));
				ps.setObject(10, StringUtils.parseValue(companyInfo.getIndustry_name()));
				ps.setObject(11, DateUtils.parseDate(companyInfo.getFound_date()));
				ps.setObject(12, StringUtils.parseValue(companyInfo.getRegister_capital()));
				ps.setObject(13, StringUtils.parseValue(companyInfo.getLegal_man()));
				ps.setObject(14, StringUtils.parseValue(companyInfo.getStock_type()));
				ps.setObject(15, DateUtils.parseDate(companyInfo.getIpo_date()));
				ps.setObject(16, StringUtils.parseValue(companyInfo.getIpo_main_saler()));
				ps.setObject(17, StringUtils.parseValue(companyInfo.getConcept()));
				ps.setObject(18, StringUtils.parseValue(companyInfo.getProvince()));
				ps.setObject(19, StringUtils.parseValue(companyInfo.getCity()));
				ps.setObject(20, StringUtils.parseValue(companyInfo.getCompany_character()));
				ps.setObject(21, StringUtils.parseValue(companyInfo.getDirectors()));
				ps.setObject(22, StringUtils.parseValue(companyInfo.getEmp_sum()));
				ps.setObject(23, StringUtils.parseValue(companyInfo.getRegister_number()));
				ps.setObject(24, StringUtils.parseValue(companyInfo.getRegister_address()));
				ps.setObject(25, StringUtils.parseValue(companyInfo.getOffice_address()));
				ps.setObject(26, StringUtils.parseValue(companyInfo.getMajor_product_type()));
				ps.setObject(27, StringUtils.parseValue(companyInfo.getMajor_product_name()));
				ps.setObject(28, StringUtils.parseValue(companyInfo.getPhone()));
				ps.setObject(29, StringUtils.parseValue(companyInfo.getZipcode()));
				ps.setObject(30, StringUtils.parseValue(companyInfo.getFax()));
				ps.setObject(31, StringUtils.parseValue(companyInfo.getWebsite()));
				ps.setObject(32, StringUtils.parseValue(companyInfo.getEmail()));
				ps.setObject(33, StringUtils.parseValue(companyInfo.getLink_main()));
				ps.setObject(34, StringUtils.parseValue(companyInfo.getCompany_brief()));
				ps.setObject(35, StringUtils.parseValue(companyInfo.getBusiness()));
				ps.setObject(36, StringUtils.parseValue(companyInfo.getWind_code()));
				ps.setObject(37, StringUtils.parseValue(companyInfo.getClosing_price()));
				ps.setObject(38, DateUtils.parseDate(companyInfo.getPrice_date()));
				ps.setObject(39, StringUtils.parseValue(companyInfo.getBoard_chair_men()));
				ps.setObject(40, StringUtils.parseValue(companyInfo.getRegister_addressCopy()));
				
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
	
	public void batchUpdateCompanyBasicinfo(final List<CompanyBasicinfo> companyBasicinfos) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getJdbcTemplate().getDataSource().getConnection(); 
			conn.setAutoCommit(false);
			
			String sql = " update company_basicinfo set stock_code=?,stock_category=?,stock_name=?,stock_alias=?,company_name=?,"
					+ "company_ename=?,industry_name_csrc=?,industry_code_csrc=?,industry_code=?,industry_name=?,found_date=?,"
					+ "register_capital=?,legal_man=?,stock_type=?,ipo_date=?,ipo_main_saler=?,concept=?,province=?,city=?,company_character=?,"
					+ "directors=?,emp_sum=?,register_number=?,register_address=?,office_address=?,majo_rproduct_type=?,major_product_name=?,"
					+ "phone=?,zipcode=?,fax=?,website=?,email=?,link_man=?,company_brief=?,business=?,wind_code=?,closing_price=?,price_date=?,"
					+ "board_chair_men=?,register_addressCopy=? "
					+ "where id=? ";
			
			ps = conn.prepareStatement(sql);
			for (CompanyBasicinfo companyInfo : companyBasicinfos) {
				ps.setObject(1, StringUtils.parseValue(companyInfo.getStock_code()));
				ps.setObject(2, StringUtils.parseValue(companyInfo.getStock_category()));
				ps.setObject(3, StringUtils.parseValue(companyInfo.getStock_name()));
				ps.setObject(4, StringUtils.parseValue(companyInfo.getStock_alias()));
				ps.setObject(5, StringUtils.parseValue(companyInfo.getCompany_name()));
				ps.setObject(6, StringUtils.parseValue(companyInfo.getCompany_ename()));
				ps.setObject(7, StringUtils.parseValue(companyInfo.getIndustry_name_csrc()));
				ps.setObject(8, StringUtils.parseValue(companyInfo.getIndustry_code_csrc()));
				ps.setObject(9, StringUtils.parseValue(companyInfo.getIndustry_code()));
				ps.setObject(10, StringUtils.parseValue(companyInfo.getIndustry_name()));
				ps.setObject(11, DateUtils.parseDate(companyInfo.getFound_date()));
				ps.setObject(12, StringUtils.parseValue(companyInfo.getRegister_capital()));
				ps.setObject(13, StringUtils.parseValue(companyInfo.getLegal_man()));
				ps.setObject(14, StringUtils.parseValue(companyInfo.getStock_type()));
				ps.setObject(15, DateUtils.parseDate(companyInfo.getIpo_date()));
				ps.setObject(16, StringUtils.parseValue(companyInfo.getIpo_main_saler()));
				ps.setObject(17, StringUtils.parseValue(companyInfo.getConcept()));
				ps.setObject(18, StringUtils.parseValue(companyInfo.getProvince()));
				ps.setObject(19, StringUtils.parseValue(companyInfo.getCity()));
				ps.setObject(20, StringUtils.parseValue(companyInfo.getCompany_character()));
				ps.setObject(21, StringUtils.parseValue(companyInfo.getDirectors()));
				ps.setObject(22, StringUtils.parseValue(companyInfo.getEmp_sum()));
				ps.setObject(23, StringUtils.parseValue(companyInfo.getRegister_number()));
				ps.setObject(24, StringUtils.parseValue(companyInfo.getRegister_address()));
				ps.setObject(25, StringUtils.parseValue(companyInfo.getOffice_address()));
				ps.setObject(26, StringUtils.parseValue(companyInfo.getMajor_product_type()));
				ps.setObject(27, StringUtils.parseValue(companyInfo.getMajor_product_name()));
				ps.setObject(28, StringUtils.parseValue(companyInfo.getPhone()));
				ps.setObject(29, StringUtils.parseValue(companyInfo.getZipcode()));
				ps.setObject(30, StringUtils.parseValue(companyInfo.getFax()));
				ps.setObject(31, StringUtils.parseValue(companyInfo.getWebsite()));
				ps.setObject(32, StringUtils.parseValue(companyInfo.getEmail()));
				ps.setObject(33, StringUtils.parseValue(companyInfo.getLink_main()));
				ps.setObject(34, StringUtils.parseValue(companyInfo.getCompany_brief()));
				ps.setObject(35, StringUtils.parseValue(companyInfo.getBusiness()));
				ps.setObject(36, StringUtils.parseValue(companyInfo.getWind_code()));
				ps.setObject(37, StringUtils.parseValue(companyInfo.getClosing_price()));
				ps.setObject(38, DateUtils.parseDate(companyInfo.getPrice_date()));
				ps.setObject(39, StringUtils.parseValue(companyInfo.getBoard_chair_men()));
				ps.setObject(40, StringUtils.parseValue(companyInfo.getRegister_addressCopy()));
				ps.setObject(41, StringUtils.parseValue(companyInfo.getId()));
				
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
