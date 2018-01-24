package com.abcft.system.companyManager;

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

/**
 * 系统用户   	数据库操作
 * @author Administrator
 *
 */
@Repository
@Transactional
public class companyManagerDao extends RsglBaseDao {
	

	private static final Logger logger = LogManager.getLogger(companyManagerDao.class);

	/**
	 * 查询员工列表
	 * @param currentPage
	 * @param pageSize
	 * @param roleId 
	 * @param fullName 姓名
	 * @param userName 工号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyManagerInfo> findList(long start, long end, String stockCode, 
			String managerName, String keyword) {
		StringBuffer sql = null;
		List<Object> params = new ArrayList<Object>();
		if (keyword != null && "distinct".equals(keyword)) {
			sql = new StringBuffer(" select * from company_manager_info manager where "
					+ " (manager.stock_code,manager.manager_name) in (select stock_code,manager_name "
					+ " from company_manager_info group by stock_code,manager_name having count(*) > 1) ");
		} else {
			sql = new StringBuffer(" select * from  company_manager_info  where 1=1");
			
			if(!StringUtils.isEmpty(stockCode)) {
				sql.append("  AND stock_code like '%" + stockCode + "%' ");
			}
			if(!StringUtils.isEmpty(managerName)) {
				sql.append(" AND manager_name like '%" + managerName + "%'  ");
			}
			
			sql.append(" limit ?,? ");  
			params.add(start);
			params.add(end);
		}
		
		logger.info(sql);
		logger.info(params);
		return getJdbcTemplate().query(sql.toString(), new EntityMapper(CompanyManagerInfo.class), params.toArray());
	}

	//获得总条数
		@SuppressWarnings("deprecation")
		public Long getTotalRole(String stockCode,String stockName, String keyword) {
			StringBuffer sql = null;
			if (keyword != null && "distinct".equals(keyword)) {
				sql = new StringBuffer(" select count(*) from (select * from company_manager_info manager where "
						+ " (manager.stock_code,manager.manager_name) in (select stock_code,manager_name "
						+ " from company_manager_info group by stock_code,manager_name having count(*) > 1)) T");
			} else {
				sql = new StringBuffer("select count(*) from company_manager_info  where 1=1");
				
				if(!StringUtils.isEmpty(stockCode)) {
					sql.append("  AND stock_code like '%"+stockCode+"%' ");
				}
				if(!StringUtils.isEmpty(stockName)) {
					sql.append(" AND stock_name like '%"+stockName+"%'  ");
				}
			}
			
			return getJdbcTemplate().queryForLong(sql.toString());
		}

		public int remove(String id) {
			String sql = "delete  from company_basicinfo  where id=? ";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			return getJdbcTemplate().update(sql,params.toArray());
			
		}
		
		public void updateCompanyManager(CompanyManagerInfo managerInfo) {
			String sql = " update company_manager_info set  stock_code=?,idcard=?,manager_name=?,post_name=?,post_type=?,"
					+ " begin_date=?,end_date=?,sex=?,country=?,education=?,birth_year=?,work_experience=?,created_at=? "
					+ "where manager_info_id=? ";
			
			List<Object> list = new ArrayList<Object>();
			list.add(managerInfo.getStock_code());
			list.add(StringUtils.parseValue(managerInfo.getIdcard()));
			list.add(StringUtils.parseValue(managerInfo.getManager_name()));
			list.add(StringUtils.parseValue(managerInfo.getPost_name()));
			list.add(NumberUtil.parseValue(managerInfo.getPost_type()));
			list.add(DateUtils.parseDate(managerInfo.getBegin_date()));
			list.add(DateUtils.parseDate(managerInfo.getEnd_date()));
			list.add(StringUtils.parseValue(managerInfo.getSex()));
			list.add(StringUtils.parseValue(managerInfo.getCountry()));
			list.add(StringUtils.parseValue(managerInfo.getEducation()));
			list.add(DateUtils.parseDate(managerInfo.getBirth_year()));
			list.add(StringUtils.parseValue(managerInfo.getWork_experience()));
			list.add(DateUtils.parseDate(managerInfo.getCreated_at()));
			list.add(managerInfo.getManager_info_id());
			
			getJdbcTemplate().update(sql.toString(), list.toArray());
		}

		/**
		 * 批量插入公司高管信息
		 * @param companyManagerInsert
		 * @throws SQLException 
		 */
		public void batchInsertCompanyManager(List<CompanyManagerInfo> companyManagerInsert) throws SQLException {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = getJdbcTemplate().getDataSource().getConnection(); 
				conn.setAutoCommit(false);
				
				String sql = " insert into company_manager_info (stock_code,idcard,manager_name,post_name,post_type,"
						+ " begin_date,end_date,sex,country,education,birth_year,work_experience,created_at) "
						+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				ps = conn.prepareStatement(sql);
				for (CompanyManagerInfo m : companyManagerInsert) {
					ps.setObject(1, m.getStock_code());
					ps.setObject(2, m.getIdcard());
					ps.setObject(3, m.getManager_name());
					ps.setObject(4, m.getPost_name());
					ps.setObject(5, m.getPost_type());
					ps.setObject(6, m.getBegin_date());
					ps.setObject(7, m.getEnd_date());
					ps.setObject(8, m.getSex());
					ps.setObject(9, m.getCountry());
					ps.setObject(10, m.getEducation());
					ps.setObject(11, m.getBirth_year());
					ps.setObject(12, m.getWork_experience());
					ps.setObject(13, m.getCreated_at());
					
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
		
		/**
		 * 批量更新公司高管信息
		 * @param companyManagerInsert
		 * @throws SQLException 
		 */
		public void batchUpdateCompanyManager(List<CompanyManagerInfo> companyManagerInsert) throws SQLException {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = getJdbcTemplate().getDataSource().getConnection(); 
				conn.setAutoCommit(false);
				
				String sql = " update company_manager_info set  stock_code=?,idcard=?,manager_name=?,post_name=?,post_type=?,"
						+ " begin_date=?,end_date=?,sex=?,country=?,education=?,birth_year=?,work_experience=?,created_at=? "
						+ "where manager_info_id=? ";
				
				ps = conn.prepareStatement(sql);
				for (CompanyManagerInfo m : companyManagerInsert) {
					ps.setObject(1, m.getStock_code());
					ps.setObject(2, m.getIdcard());
					ps.setObject(3, m.getManager_name());
					ps.setObject(4, m.getPost_name());
					ps.setObject(5, m.getPost_type());
					ps.setObject(6, m.getBegin_date());
					ps.setObject(7, m.getEnd_date());
					ps.setObject(8, m.getSex());
					ps.setObject(9, m.getCountry());
					ps.setObject(10, m.getEducation());
					ps.setObject(11, m.getBirth_year());
					ps.setObject(12, m.getWork_experience());
					ps.setObject(13, m.getCreated_at());
					ps.setObject(14, m.getManager_info_id());
					
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
