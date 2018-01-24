package com.abcft.common.base;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.abcft.common.pagemodel.DataGrid;
import com.abcft.common.util.gen.ArrayUtil;
import com.abcft.common.util.gen.ValidUtil;

/**
 * 
 * 继承JdbcDaoSupport,加上insert, update, find, delete对实体的映谢
 * 
 * @author inning
 * @DateTime 2015-7-20 上午11:34:23
 *
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDao extends JdbcDaoSupport {

	private static final Logger log = LogManager.getLogger(BaseDao.class);

	@Resource(name="jdbcTemplate")
	public void setB2bJdbcTemplate(JdbcTemplate b2bJdbcTemplate) {
		setJdbcTemplate(b2bJdbcTemplate);
	}

	// 新增
	protected Object insert(Object obj) {
		Class<?> entityClass = obj.getClass();

		List<String> colNamelist = new ArrayList<String>();
		List<Object> objList = new ArrayList<Object>();
		Object idObj = null;
		try {
			Method[] method = entityClass.getDeclaredMethods();
			for (Method m : method) {
				// if (m.isAnnotationPresent(Column.class)) {
				// 判断insertable属性
				if (!m.getAnnotation(Column.class).insertable())
					continue;
				// 如果id不是自增字段则不需要判断
				if (!m.getAnnotation(Column.class).name().equalsIgnoreCase("id")) {

					colNamelist.add(m.getAnnotation(Column.class).name());
					Object o = m.invoke(obj);
					if (m.isAnnotationPresent(Id.class) && m.isAnnotationPresent(SequenceGenerator.class)) {
						if (o != null) {
							objList.add(o);
							continue;
						}
						// 当数据库为oracle的时候
						// StringBuffer sql = new StringBuffer("select ");
						// sql.append(m.getAnnotation(SequenceGenerator.class).sequenceName());
						// sql.append(".nextVal from dual");
						// 当数据库为mysql子增长时
						StringBuffer sql = new StringBuffer("SELECT Auto_increment FROM information_schema.tables  WHERE table_name='");
						sql.append(m.getAnnotation(SequenceGenerator.class).name());
						sql.append("' limit 0,1");
						if (log.isDebugEnabled()) {
							log.debug(sql);
						}
						idObj = getJdbcTemplate().queryForLong(sql.toString());
						objList.add(idObj);
					} else {
						objList.add(o);
					}

				}

			}
			// }
		} catch (Exception e) {
			getExceptionTranslator().translate(null, null, new SQLException(e));
		}

		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(entityClass.getAnnotation(Table.class).name());
		sql.append("(");
		sql.append(colNamelist.toString().replace("[", "").replace("]", ""));
		sql.append(") values ");
		sql.append(getQuestionStr(colNamelist.size()));

		if (log.isDebugEnabled()) {
			log.debug(sql);
			log.debug(objList);
		}
		getJdbcTemplate().update(sql.toString(), objList.toArray());
		return idObj;
	}

	private String getQuestionStr(int size) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < size; i++) {
			b.append(",?");
		}
		return b.toString().replaceFirst(",", "(") + ")";
	}

	// 修改
	protected int update(Object obj) {
		Class<?> entityClass = obj.getClass();

		List<String> colNamelist = new ArrayList<String>();
		List<Object> objList = new ArrayList<Object>();
		List<String> idNameList = new ArrayList<String>();
		List<Object> idObjList = new ArrayList<Object>();
		try {
			Method[] method = entityClass.getDeclaredMethods();
			for (Method m : method) {
				if (m.isAnnotationPresent(Column.class)) {
					// 判断updatable属性
					if (!m.getAnnotation(Column.class).updatable())
						continue;
					Column colomn = m.getAnnotation(Column.class);
					Object o = m.invoke(obj);
					if (m.isAnnotationPresent(Id.class)) {
						idNameList.add(colomn.name() + "=?");
						idObjList.add(o);
					} else {
						if (o != null) {
							colNamelist.add(colomn.name() + "=?");
							objList.add(o);
						}
					}
				}

			}
		} catch (Exception e) {
			getExceptionTranslator().translate(null, null, new SQLException(e));
		}
		objList.addAll(idObjList);

		StringBuffer sql = new StringBuffer("update ");
		sql.append(entityClass.getAnnotation(Table.class).name());
		sql.append(" set ").append(colNamelist.toString().replace("[", "").replace("]", ""));
		sql.append(" where ").append(idNameList.toString().replace("[", "").replace("]", "").replaceAll(",", " and "));
		if (log.isDebugEnabled()) {
			log.debug(sql);
			log.debug(objList);
		}
		log.info(sql);
		log.info(objList);
		return getJdbcTemplate().update(sql.toString(), objList.toArray());
	}

	// 查询单个实体
	public <T> T find(Class<T> entityClass, Object idObj) {
		String idName = "";
		try {
			Method[] method = entityClass.getDeclaredMethods();
			for (Method m : method) {
				if (m.isAnnotationPresent(Id.class)) {
					idName = m.getAnnotation(Column.class).name();
				}
			}
		} catch (Exception e) {
			getExceptionTranslator().translate(null, null, new SQLException(e));
		}

		StringBuffer sql = new StringBuffer("select * from ");
		sql.append(entityClass.getAnnotation(Table.class).name());
		sql.append(" where ").append(idName).append("=?");
		if (log.isDebugEnabled()) {
			log.debug(sql);
			log.debug(idObj);
		}

		Object[] arg = { idObj };

		try {
			// return (T)jdbcTemplate.queryForObject(sql, para.toArray(), new
			// EntityMapper(entityClass));
			List<Object> list = getJdbcTemplate().query(sql.toString(), arg, new EntityMapper(entityClass));
			if (list == null || list.isEmpty()) {
				return null;
			} else {
				return (T) list.get(0);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

	// 删除单个实体
	public int delete(Class<?> entityClass, Object idObj) {
		String idName = "";
		try {
			Method[] method = entityClass.getDeclaredMethods();
			for (Method m : method) {
				if (m.isAnnotationPresent(Id.class)) {
					idName = m.getAnnotation(Column.class).name();
				}
			}
		} catch (Exception e) {
			getExceptionTranslator().translate(null, null, new SQLException(e));
		}

		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(entityClass.getAnnotation(Table.class).name());
		sql.append(" where ").append(idName).append("=?");
		if (log.isDebugEnabled()) {
			log.debug(sql);
			log.debug(idObj);
		}

		Object[] arg = { idObj };
		return getJdbcTemplate().update(sql.toString(), arg);
	}

	// 批量删除
	public int batchDelete(Class<?> entityClass, Object[] idObj) {
		String idName = "";
		try {
			Method[] method = entityClass.getDeclaredMethods();
			for (Method m : method) {
				if (m.isAnnotationPresent(Id.class)) {
					idName = m.getAnnotation(Column.class).name();
				}
			}
		} catch (Exception e) {
			getExceptionTranslator().translate(null, null, new SQLException(e));
		}

		StringBuffer idsSb = new StringBuffer();
		for (int i = 0; i < idObj.length; i++) {
			idsSb.append(idObj[i]).append(",");
		}
		if (idsSb.indexOf(",") != -1) {
			idsSb.deleteCharAt(idsSb.lastIndexOf(","));
		}

		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(entityClass.getAnnotation(Table.class).name());
		// sql.append(" where ").append(idName).append(" in (?) ");
		sql.append(" where ").append(idName).append(" in (").append(idsSb).append(")");// in子集不能超过1000
		if (log.isDebugEnabled()) {
			log.debug(sql);
			log.debug(idsSb);
		}

		// Object[] arg = idObj;
		Object[] arg = null;
		return getJdbcTemplate().update(sql.toString(), arg);
	}

	protected BaseQuery createQuery(String sql, Class<?> cla) {
		return new MysqlQuery(getJdbcTemplate(), sql, cla);
	}

	/**
	 * 
	 * mssql分页
	 * 
	 * @param sql
	 *            (基础sql语句，拼接好要代入的条件信息，参数以数组的形式传人，对应好位置，class
	 *            是bean可以传入，其他的皆为null)
	 * @param currentPage
	 * @param pageSize
	 * @param clazz
	 * @param objs
	 * @return
	 * @date 2016年3月18日
	 * @author rwrwd7
	 */
	public DataGrid findPageForMSSQL(String sql, long currentPage, long pageSize, Class clazz, Object... objs) {
		DataGrid dg = new DataGrid();
		long count = 0;
		boolean isHaveParam = false;
		if (ArrayUtil.isNotEmpty(objs) && !ValidUtil.isEmpty(objs[0]))
			isHaveParam = true;

		String countSql = "SELECT COUNT(*) FROM(" + sql + ") t1";

		if (isHaveParam)
			count = getJdbcTemplate().queryForInt(countSql, objs);
		else
			count = getJdbcTemplate().queryForInt(countSql);

		List list = new ArrayList();
		if (count > 0) {
			currentPage = (currentPage - 1) >= 0 ? (currentPage - 1) : 0;
			String pageSql = "SELECT TOP (" + pageSize + ") *" + " FROM (" + sql + " ) tab " + " WHERE tab.rownumber > " + (pageSize * currentPage) + " ";
			

			if (isHaveParam) {
				if (!ValidUtil.isEmpty(clazz))
					list = getJdbcTemplate().query(pageSql, objs, new EntityMapper(clazz));
				else
					list = getJdbcTemplate().queryForList(pageSql, objs);
			} else {
				if (!ValidUtil.isEmpty(clazz))
					list = getJdbcTemplate().query(pageSql, new EntityMapper(clazz));
				else
					list = getJdbcTemplate().queryForList(pageSql);
			}
		}
		dg.setRows(list);
		dg.setTotal(count);
		return dg;
	}

}
