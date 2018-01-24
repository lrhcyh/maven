package com.abcft.system.nlpSynonym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.base.MysqlQuery;
import com.abcft.common.util.StringUtils;

@Repository
@Transactional
public class NlpSynonymDao {

	@Resource(name = "pvjdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LogManager
			.getLogger(NlpSynonymDao.class);
	
	@SuppressWarnings("unchecked")
	public List<NlpSynonymInfo> findList(long start, long end,
			String name, String name_text, String type) {
		StringBuffer sql = new StringBuffer(
				" select id,name,parentId,date_format(createTime, '%Y-%m-%d %h:%i:%s') as createTime,type,name_text,stock_name,stock_code,stock_abc_code from  dic_synonyms  where 1=1");
		
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(name)) {
			sql.append("  AND name like '%" + name + "%' ");
		}
		if (!StringUtils.isEmpty(name_text)) {
			sql.append(" AND name_text like '%"+ name_text +"%'  ");
		}
		if (!StringUtils.isEmpty(type)) {
			sql.append(" AND type = '"+ type +"'  ");
		}

		sql.append(" limit ?,? ");
		params.add(start);
		params.add(end);
		logger.info(sql);
		logger.info(params);
		return jdbcTemplate.query(sql.toString(),
				new EntityMapper(NlpSynonymInfo.class), params.toArray());
	}
	
	/**
	 * 获得总条数
	 * @param stockCode
	 * @param stockName
	 * @return
	 */
	public Long getTotalRole(String name, String name_text, String type) {
		StringBuffer sql = new StringBuffer(
				"select count(*) from dic_synonyms  where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(name)) {
			sql.append("  AND name like '%" + name + "%' ");
		}
		if(!StringUtils.isEmpty(name_text)) {
			sql.append(" AND name_text like '%"+ name_text +"%'  ");
		}
		if(!StringUtils.isEmpty(type)) {
			sql.append(" AND type = '"+ type +"'  ");
		}

		return jdbcTemplate.queryForLong(sql.toString(), params.toArray());
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int remove(String id) {
		String sql = "delete  from dic_synonyms  where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return jdbcTemplate.update(sql,params.toArray());
		
	}

	/**
	 * 更新同义词
	 * @param nlpSynonymInfo
	 */
	public int updateNlpSynonym(NlpSynonymInfo nlpSynonymInfo) {
		String sql = " update dic_synonyms set name=?,parentId=?,createTime=now(),"
				+ "type=?,name_text=?,stock_name=?,stock_code=?,stock_abc_code=? "
				+ " where id=?;";
		
		List<Object> list = parseNlpSynonym(nlpSynonymInfo);
		list.add(nlpSynonymInfo.getId());
		
		return jdbcTemplate.update(sql.toString(), list.toArray());
	}
	
	/**
	 * 插入同义词
	 * @param nlpSynonymInfo
	 */
	@SuppressWarnings("deprecation")
	public Long insertNlpSynonym(NlpSynonymInfo nlpSynonymInfo) {
		String sql = " insert into dic_synonyms (name,parentId,createTime,"
				+ "type,name_text,stock_name,stock_code,stock_abc_code) "
				+ " values (?,?,now(),?,?,?,?,?)";
		
		List<Object> list = parseNlpSynonym(nlpSynonymInfo);
		
		jdbcTemplate.update(sql.toString(), list.toArray());
		
		String sql2 = "SELECT ID FROM dic_synonyms WHERE ID=@@IDENTITY";
		
		return jdbcTemplate.queryForLong(sql2);
	}
	
	private List<Object> parseNlpSynonym(NlpSynonymInfo nlpSynonymInfo) {
		List<Object> list = new ArrayList<Object>();
		
		if (null == nlpSynonymInfo) {
			return list;
		}
		list.add(nlpSynonymInfo.getName());
		list.add(nlpSynonymInfo.getParentId());
		list.add(nlpSynonymInfo.getType());
		list.add(nlpSynonymInfo.getName_text());
		list.add(nlpSynonymInfo.getStock_name());
		list.add(nlpSynonymInfo.getStock_code());
		list.add(nlpSynonymInfo.getStock_abc_code());
		
		return list;
	}

	public List<Map<String, Object>> getSynonymByParentId(String parentId) {
		String sql = " select id,name,parentId,createTime,type,name_text,stock_name,stock_code,stock_abc_code "
				+ " from dic_synonyms where parentId=? ";
		
		List<Object> list = new ArrayList<Object>();
		list.add(parentId);
		
		List<Map<String, Object>> listSynonym = jdbcTemplate.queryForList(sql);
		return listSynonym;
		
	}

	public List<NlpSynonymInfo> getSynonyms() {
		String sql = " select id,name,parentId,createTime,type,name_text,stock_name,stock_code,stock_abc_code "
				+ " from dic_synonyms order by name;";
		
		MysqlQuery mysqlQuery = new MysqlQuery(jdbcTemplate, sql, NlpSynonymInfo.class);
		List<NlpSynonymInfo> list = mysqlQuery.getResultList();
		
		return list;
	}

	public List<NlpSynonymInfo> getSynonymsParent(Long parentId) {
		String sql = " select id,name,parentId,createTime,type,name_text,stock_name,stock_code,stock_abc_code "
				+ " from dic_synonyms   where type=2 and parentId="+parentId+" order by name;";
		
		MysqlQuery mysqlQuery = new MysqlQuery(jdbcTemplate, sql, NlpSynonymInfo.class);
		List<NlpSynonymInfo> list = mysqlQuery.getResultList();
		
		return list;
	}
	
	/**
	 * 批量插入同义词
	 * @param companyBasicinfos
	 * @throws SQLException 
	 */
	public void batchInsertNlpSynonym(final List<NlpSynonymInfo> synonymInfos) throws SQLException {
		String sql = " insert into dic_synonyms (type,name,name_text,parentId,createTime,stock_abc_code)  "
				+ "values (?,?,?,?,now(),?) ";
		
		Connection conn = jdbcTemplate.getDataSource().getConnection();  
		conn.setAutoCommit(false);
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		for (NlpSynonymInfo synonym : synonymInfos) {
			ps.setInt(1, synonym.getType());
			ps.setString(2, synonym.getName());
			ps.setString(3, synonym.getName_text());
			ps.setLong(4, synonym.getParentId());
			ps.setString(5, synonym.getStock_abc_code());
			
			ps.addBatch();
		}
		
		ps.executeBatch();
		conn.commit();
		
		ps.close();
		conn.close();
	}
	
	/**
	 * 批量更新同义词
	 * @param 
	 */
	public void batchUploadNlpSynonym(final List<NlpSynonymInfo> synonymInfos) {
		String sql = " update dic_synonyms set type=?,name=?,name_text=?,parent=?Id,=?createTime=now(),stock_abc_code=? "
				+ "where id=?;";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return synonymInfos.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				NlpSynonymInfo synonym = synonymInfos.get(i);
				ps.setInt(1, synonym.getType());
				ps.setString(2, synonym.getName());
				ps.setString(3, synonym.getName_text());
				ps.setLong(4, synonym.getParentId());
				ps.setString(5, synonym.getStock_abc_code());
			}
		});
	}
	
	/**
	 * 查询同义词信息,name为必需参数，其他参数可为null
	 * @param name
	 * @return
	 */
	public Long selectNlpSynonymByNameAndParentIdAndType(String name, Long parentId, Integer type) {
		StringBuffer sql = new StringBuffer(" select id from dic_synonyms where 1=1 ");
		
		List<Object> params = new ArrayList<>();
		if (!StringUtils.isEmpty(name)) {
			sql.append("  AND name = ? ");
			params.add(name);
		}
		if(parentId != null && parentId.longValue() >= 0) {
			sql.append(" AND parentId = ?  ");
			params.add(parentId);
		}
		if(null != type && type >= 0) {
			sql.append(" AND type = ?  ");
			params.add(type);
		}
		
		List<NlpSynonymInfo> list = jdbcTemplate.query(sql.toString(), new EntityMapper(NlpSynonymInfo.class), 
				params.toArray());
		
		if (null != list && list.size() > 0) {
			return list.get(0).getId();
		}
		return null;
	}
	
	public void deleteNlpSynonymByParentId(Long parentId, int type) {
		String sql = " delete from dic_synonyms where parentId=? and type=? ";
		
		List<Object> params = new ArrayList<>();
		params.add(parentId);
		params.add(type);
		
		jdbcTemplate.update(sql, params.toArray());
	}

}
