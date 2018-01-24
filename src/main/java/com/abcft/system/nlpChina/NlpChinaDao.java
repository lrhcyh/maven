package com.abcft.system.nlpChina;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.base.MysqlQuery;
import com.abcft.common.util.StringUtils;

@Repository
@Transactional
public class NlpChinaDao {

	@Resource(name = "pvjdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LogManager
			.getLogger(NlpChinaDao.class);
	
	@SuppressWarnings("unchecked")
	public List<NlpCountryInfo> findList(long start, long end,
			String name) {
		StringBuffer sql = new StringBuffer(
				" select id,name,date_format(createTime, '%Y-%m-%d %h:%i:%s') as createTime from  dic_basic  where 1=1");
		
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(name)) {
			sql.append("  AND name like '%" + name + "%' ");
		}
		

		sql.append(" limit ?,? ");
		params.add(start);
		params.add(end);
		logger.info(sql);
		logger.info(params);
		return jdbcTemplate.query(sql.toString(),
				new EntityMapper(NlpCountryInfo.class), params.toArray());
	}
	
	/**
	 * 获得总条数
	 * @param stockCode
	 * @param stockName
	 * @return
	 */
	public Long getTotalRole(String name) {
		StringBuffer sql = new StringBuffer(
				"select count(*) from dic_basic  where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(name)) {
			sql.append("  AND name like '%" + name + "%' ");
		}
		

		return jdbcTemplate.queryForLong(sql.toString(), params.toArray());
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int remove(String id) {
		String sql = "delete  from dic_basic  where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return jdbcTemplate.update(sql,params.toArray());
		
	}

	/**
	 * 更新同义词
	 * @param nlpSynonymInfo
	 */
	public void updateNlpSynonym(NlpCountryInfo nlpSynonymInfo) {
		String sql = " update dic_basic set name=? where id=?;";
		
		List<Object> list = new ArrayList<Object>();
		list.add(nlpSynonymInfo.getName());
		list.add(nlpSynonymInfo.getId());
		
		jdbcTemplate.update(sql.toString(), list.toArray());
	}
	
	/**
	 * 插入同义词
	 * @param nlpSynonymInfo
	 */
	public void insertNlpSynonym(NlpCountryInfo nlpSynonymInfo) {
		String sql = " insert into dic_basic (name,type,createTime) "
				+ " values (?,?,now())";
		
		List<Object> list = parseNlpSynonym(nlpSynonymInfo);
		
		jdbcTemplate.update(sql.toString(), list.toArray());
	}
	
	private List<Object> parseNlpSynonym(NlpCountryInfo nlpSynonymInfo) {
		List<Object> list = new ArrayList<Object>();
		
		if (null == nlpSynonymInfo) {
			return list;
		}
		list.add(nlpSynonymInfo.getName());
		list.add(2);
		
		
		return list;
	}

	
}
