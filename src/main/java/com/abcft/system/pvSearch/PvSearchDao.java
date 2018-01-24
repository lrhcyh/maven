package com.abcft.system.pvSearch;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.util.StringUtils;

@Repository
@Transactional
public class PvSearchDao {
	
	@Resource(name = "pvjdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(PvSearchDao.class);
	
	/**
	 * 查询搜索列表
	 * @param start
	 * @param end
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PvSearchResult> findList(long start, long end, String startTime, String endTime, String type) {
		StringBuffer sql = new StringBuffer(
				" select serchName,count(serchName) as serchCount from  pv_serch where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(startTime)) {
			sql.append("  AND create_date >= '" + startTime + "' ");
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql.append("  AND create_date <= '" + endTime + "' ");
		}
		if (!StringUtils.isEmpty(type)) {
			sql.append("  AND type = '" + type + "' ");
		}
		
		sql.append(" group by serchName order by serchCount desc");
		sql.append(" limit ?,? ");
		params.add(start);
		params.add(end);
		logger.info(sql);
		logger.info(params);
		return jdbcTemplate.query(sql.toString(),
				new EntityMapper(PvSearchResult.class), params.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public List<PvSearchResult> findListAll( String startTime, String endTime, String type) {
		StringBuffer sql = new StringBuffer(
				" select serchName,count(serchName) as serchCount from  pv_serch where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(startTime)) {
			sql.append("  AND create_date >= '" + startTime + "' ");
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql.append("  AND create_date <= '" + endTime + "' ");
		}
		if (!StringUtils.isEmpty(type)) {
			sql.append("  AND type = '" + type + "' ");
		}
		
		sql.append(" group by serchName order by serchCount desc");
		
		logger.info(sql);
		logger.info(params);
		return jdbcTemplate.query(sql.toString(),
				new EntityMapper(PvSearchResult.class), params.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public List<PvSearchInfo> findList() {
		StringBuffer sql = new StringBuffer(
				" select userId from  pv_serch where 1=1  and userId is not null and userId != '' group by userId ");
		
		logger.info(sql);
		return jdbcTemplate.query(sql.toString(), new EntityMapper(PvSearchInfo.class));
	}
	
	public int updatePvSearchByUserId(String userId, String userName) {
		String sql = "update pv_serch set userName=? where userId=? ";
		
		List<Object> params = new ArrayList<>();
		params.add(userName);
		params.add(userId);
		
		return jdbcTemplate.update(sql, params.toArray());
	}
	
	/**
	 * 获得总条数
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Long getTotalRole(String startTime, String endTime, String type) {
		StringBuffer sql = new StringBuffer(
				"select count(serch.serchName) from (select serchName from pv_serch   where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(startTime)) {
			sql.append("  AND create_date >= '" + startTime + "' ");
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql.append("  AND create_date <= '" + endTime + "' ");
		}
		if (!StringUtils.isEmpty(type)) {
			sql.append("  AND type = '" + type + "' ");
		}
		
		sql.append("group by serchName) as serch");

		return jdbcTemplate.queryForLong(sql.toString(), params.toArray());
	}


	@SuppressWarnings("unchecked")
	public List<PvSearchInfo> findDetailList(long start, long end,
			String startTime, String endTime, String serchName,
			String searchResult, String userName, String type) {
		StringBuffer sql = new StringBuffer(
				" select id,serchName,date_format(create_date, '%Y-%m-%d %h:%i:%s') as create_date,ip_long,type,searchResult,userName from  pv_serch where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(startTime)) {
			sql.append("  AND create_date >= '" + startTime + "' ");
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql.append("  AND create_date <= '" + endTime + "' ");
		}
		if (!StringUtils.isEmpty(type)) {
			sql.append("  AND type like '%" + type + "%' ");
		}
		if (!StringUtils.isEmpty(userName)) {
			sql.append("  AND userName like '%" + userName + "%' ");
		}
		if (!StringUtils.isEmpty(serchName)) {
			sql.append("  AND serchName like '%" + serchName + "%' ");
		}
		if (!StringUtils.isEmpty(searchResult)) {
			sql.append("  AND searchResult like '%" + searchResult + "%' ");
		}
		
		sql.append(" order by create_date desc ");
		sql.append(" limit ?,? ");
		params.add(start);
		params.add(end);
		
		logger.info(sql);
		logger.info(params);
		
		return jdbcTemplate.query(sql.toString(), new EntityMapper(PvSearchInfo.class), params.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public List<PvSearchInfo> findDetailListAll(String startTime, String endTime, String serchName,
			String searchResult, String userName, String type) {
		StringBuffer sql = new StringBuffer(
				" select id,serchName,date_format(create_date, '%Y-%m-%d %h:%i:%s') as "
				+ " create_date,ip_long,type,searchResult,userName from  pv_serch where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(startTime)) {
			sql.append("  AND create_date >= '" + startTime + "' ");
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql.append("  AND create_date <= '" + endTime + "' ");
		}
		if (!StringUtils.isEmpty(type)) {
			sql.append("  AND type like '%" + type + "%' ");
		}
		if (!StringUtils.isEmpty(userName)) {
			sql.append("  AND userName like '%" + userName + "%' ");
		}
		if (!StringUtils.isEmpty(serchName)) {
			sql.append("  AND serchName like '%" + serchName + "%' ");
		}
		if (!StringUtils.isEmpty(searchResult)) {
			sql.append("  AND searchResult like '%" + searchResult + "%' ");
		}
		
		sql.append(" order by create_date desc ");
		
		logger.info(sql);
		logger.info(params);
		
		return jdbcTemplate.query(sql.toString(), new EntityMapper(PvSearchInfo.class), params.toArray());
	}

	/**
	 * 统计搜索详情总条数
	 * @param startTime
	 * @param endTime
	 * @param serchName
	 * @param serchResult
	 * @param userName
	 * @param type
	 * @return
	 */
	public Long getDetailTotalRole(String startTime, String endTime,
			String serchName, String searchResult, String userName, String type) {
		StringBuffer sql = new StringBuffer(
				"select count(serchName) from pv_serch  where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(startTime)) {
			sql.append("  AND create_date >= '" + startTime + "' ");
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql.append("  AND create_date <= '" + endTime + "' ");
		}
		if (!StringUtils.isEmpty(type)) {
			sql.append("  AND type like '%" + type + "%' ");
		}
		if (!StringUtils.isEmpty(userName)) {
			sql.append("  AND userName like '%" + userName + "%' ");
		}
		if (!StringUtils.isEmpty(serchName)) {
			sql.append("  AND serchName like '%" + serchName + "%' ");
		}
		if (!StringUtils.isEmpty(searchResult)) {
			sql.append("  AND searchResult like '%" + searchResult + "%' ");
		}
		
		return jdbcTemplate.queryForLong(sql.toString(), params.toArray());
	}
}
