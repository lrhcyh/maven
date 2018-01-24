package com.abcft.common.base;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * mysql操作数据库
 * @author inning
 * @DateTime 2015-7-20 上午11:36:41
 *
 */
public class MysqlQuery extends BaseQuery {
	
	private static final Logger log = LogManager.getLogger(MysqlQuery.class);
	
	public MysqlQuery(JdbcTemplate jdbcTemplate, String sql, Class<?> entityClass) {
		super.sql = sql;
		super.jdbcTemplate = jdbcTemplate;
		super.entityClass = entityClass;
	}
	
	protected List<Object> para = new ArrayList<Object>();	
	
	protected Class<?> entity;
		
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getSingleResult() {
		if (log.isDebugEnabled()) {
			log.debug(sql);
			log.debug(para);
		}
		if (entityClass.equals(Long.class)) {
			return (T)new Long(jdbcTemplate.queryForLong(sql, para.toArray()));
		}
		else if (entityClass.equals(Integer.class)) {
			return (T)new Integer(jdbcTemplate.queryForInt(sql, para.toArray()));
		} else if(entityClass.equals(String.class)){
			return (T)jdbcTemplate.queryForObject(sql, para.toArray(), String.class);
		}
		else {
			//return (T)jdbcTemplate.queryForObject(sql, para.toArray(), new EntityMapper(entityClass));
			try{
				//return (T)jdbcTemplate.queryForObject(sql, para.toArray(), new EntityMapper(entityClass));
				List<Object> list = jdbcTemplate.query(sql, para.toArray(), new EntityMapper(entityClass));
				if (list == null || list.isEmpty()) {
					return null;
				} else {
					return (T)list.get(0);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 查询mysql分页
	 * */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getResultList() {
		StringBuffer exeSql = new StringBuffer();
		exeSql.append(sql);
//		if (max != 0) {
//			exeSql.append(" limit ?,? ");
//			para.add(first);
//			para.add(max);
//		}
		if (log.isDebugEnabled()) {
			log.debug(exeSql);
			log.debug(para);
		}
		//exeSql=exeSql.replace(8, 9, " top  "+first+"  ");
		
		log.debug("=========查询列表=========" + exeSql);
		for (int i = 0; i < para.toArray().length; i++) {
			log.debug("参数" + (i + 1) + ":" + para.toArray()[i]);
		}
		return jdbcTemplate.query(exeSql.toString(), para.toArray(), new EntityMapper(entityClass));
	}
	
//	/**
//	 * 查询mysql分页
//	 * */
//	@Override
//	@SuppressWarnings("all")
//	public <T> List<T> getResultList() {
//		StringBuffer exeSql = new StringBuffer(sql);
//		//如果传入的entity不为空，则需要拼接SQL
//		StringBuffer conditionSql = new StringBuffer();
//		if(entity !=null){
//			Method[] method = entity.getDeclaredMethods();
//            for (Method m : method) {
//            	String methodName = m.getName();
//            	if(methodName.indexOf("get")==0){
//            		try {
//    	            	if(m.invoke(entity.newInstance())!=null){
//    	            		if (m.isAnnotationPresent(Column.class)) {
//    	    					Column col = m.getAnnotation(Column.class);
//    	    					conditionSql.append(" AND ");
//    	    					conditionSql.append(col.name());
//    	    					conditionSql.append(" =? ");
//    	    					para.add(m.invoke(entity));
//    	            		}
//    	            	}
//                	}catch(Exception e){
//                		e.printStackTrace();
//                		log.error("方法错误！");
//                	}
//            	}
//            }
//            
//            String tmpstr = "";
//            if(conditionSql.toString()!=null 
//            		&& !conditionSql.toString().equals("")){
//            	tmpstr = conditionSql.toString().substring(4,conditionSql.toString().length()-5);
//            	tmpstr = " WHERE " + tmpstr;
//            	
//            }
//            exeSql.append(tmpstr);
//		}
//		String recordSql = " SELECT COUNT(1) FROM ( " + exeSql.toString() +" ) AS T_R";
//		int totalNumber = new Integer(jdbcTemplate.queryForInt(recordSql, para.toArray()));
//		try{
//			Class superClass = entity.getSuperclass();
//			//查询总记录数,讲记录写入
//			Method setTotalNumberMethod = superClass.getDeclaredMethod("setTotalRows",Integer.class);
//			setTotalNumberMethod.invoke(entity.newInstance(), totalNumber);
//			
//			//if (first != 0 && max != 0) {
//			exeSql.append(" limit ?,? ").toString();
//			
//			//通过反射获取传入的分页的参数
//			Method getFirstRowMethod = superClass.getDeclaredMethod("getFirstRow");
//			Method getPageRowsMethod = superClass.getDeclaredMethod("getPageRows");
//			int firstRow =(Integer)getFirstRowMethod.invoke(entity.newInstance());
//			int pageRows = (Integer)getPageRowsMethod.invoke(entity.newInstance());
//			int endRow=firstRow + pageRows;
//			//写入总页数
//			Method setTotalRowsMethod = superClass.getDeclaredMethod("setTotalRows",Integer.class);
//			setTotalRowsMethod.invoke(entity.newInstance(), (Integer)(totalNumber/pageRows+1));
//			para.add(firstRow);
//			para.add(endRow);
//		}catch(Exception e){
//			e.printStackTrace();
//			log.error("分页参数解析失败");
//		}
//		if (log.isDebugEnabled()) {
//			log.debug(exeSql.toString());
//			log.debug(para);
//		}
//		System.out.println("=========查询列表========="+exeSql);
//		for(int i=0;i<para.toArray().length;i++){
//			System.out.println("参数"+(i+1)+":"+para.toArray()[i]);
//		}
//		return jdbcTemplate.query(exeSql.toString(), para.toArray(), new EntityMapper(entityClass));
//	}

	@Override
	public BaseQuery setParameter(Object obj) {
		if (obj != null && Date.class.equals(obj.getClass())) {
			long t = ((Date)obj).getTime();
			obj = new Timestamp(t);
		}
		if(obj.getClass().getSuperclass().equals(BaseEntity.class)){
			entity = obj.getClass();
		}else{
			para.add(obj);
		}
		
		return this;
	}

	@Override
	public int getTotalRows() {
		String totalSql = this.sql;
		int index = sql.indexOf("order by");
		if (index > 0) {
			totalSql = sql.substring(0, index);
		}
		String exeSql = "select count(1) from (" + totalSql + ") as T_R";
		if (log.isDebugEnabled()) {
			log.debug(exeSql);
			log.debug(para.size());
		}
		//System.out.println("=======查询总记录数======="+exeSql);
		return jdbcTemplate.queryForInt(exeSql, para.toArray());
	}
}
