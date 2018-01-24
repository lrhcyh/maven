package com.abcft.workflow.db;
    
import java.sql.ResultSet;

/**
 * 
 * 描述:数据集映射类
 * @author inning
 * @DateTime 2015-7-20 上午11:41:04
 */
public interface RowMapper<T> {

	/**
	 * 描述:行记录集映射方法
	 * @param	rs: 结果集对象
	 * @return	T:	Bean对象
	 * **/
	public T mapRow(ResultSet rs);
	
}
