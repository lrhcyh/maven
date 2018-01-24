package com.abcft.common.base;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 基础查询 
 * @author inning
 * @DateTime 2015-7-20 上午11:35:17
 *
 */
public abstract class BaseQuery {
	
	protected JdbcTemplate jdbcTemplate;
	
	protected Class<?> entityClass;
	
	protected long first;
	
	protected long max;
	
	protected String sql;		
			
	public BaseQuery setFirst(long first) {
		this.first = first;
		return this;
	}
	public BaseQuery setMax(long max) {
		this.max = max;
		return this;
	}

	public abstract <T>T getSingleResult();
	
	public abstract <T>List<T> getResultList() ;
		
	public abstract BaseQuery setParameter(Object arg1);
	
	public abstract int getTotalRows();

}
