package com.abcft.system.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abcft.common.base.EntityMapper;
import com.abcft.common.util.StringUtils;


/**
 * 网站访问量统计数据访问层
 * @author 黄茜
 *
 */
@Repository
public class AnalysisDao {
	
	@Resource(name="pvjdbcTemplate")
	private JdbcTemplate pvjdbcTemplate;
	
	private static Logger logger = LogManager.getLogger(AnalysisDao.class);
	
	//在pv_day中查询未统计（status为0）的pv数据
	public List<SqlPv> findPvList(String day,int status) {
		/*StringBuffer sql = new StringBuffer("  SELECT visitorid,visit_datetime,visit_hour,ip_long,userid, CONCAT(domain_dest,'/',script_dest)  AS url_dest,url_ref FROM pv_day_"+day+" WHERE 1 = 1");*/
		StringBuffer sql = new StringBuffer("  SELECT * FROM pv_day_"+day+" WHERE channel = 1");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(status)) {
			sql.append(" AND status = ? ");
			params.add(status);
			
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(SqlPv.class),params.toArray());
	}



	//将pv-day中的数据标为已统计(status置为1)
	public void updatePvStatus(String day,Integer id) {
		StringBuffer sql = new StringBuffer(" UPDATE pv_day_"+day+" SET status=1 WHERE id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		
		logger.info(sql.toString());
		logger.info(params);
		pvjdbcTemplate.update(sql.toString(),params.toArray());
	}

	//将pv_day查到的数据插入pv_access(统计pc端的流量数据)
	public void insertPvAccess(SqlPv sqlPv) {
		StringBuffer sql = new StringBuffer(" INSERT INTO pv_access VALUES(NULL,?,?,?,?,?,?,?,?,?)");
		List<Object> params = new ArrayList<Object>();
		params.add(sqlPv.getSource());
		params.add(sqlPv.getPageName());
		params.add(sqlPv.getVisitorid());
		params.add(sqlPv.getVisit_datetime());
		params.add(sqlPv.getVisit_hour());
		params.add(sqlPv.getIp_long());
		params.add(sqlPv.getUserid());
		params.add(sqlPv.getUrl_dest());
		params.add(sqlPv.getUrl_ref());
		
		logger.info(sql.toString());
		logger.info(params);
		pvjdbcTemplate.update(sql.toString(),params.toArray());
		
	}

	//统计页面来源
	public List<AccessSource> getAccessData(String beginDate, String endDate) {
		StringBuffer sql = new StringBuffer(" SELECT  CASE source WHEN 0 THEN '直接进入' WHEN 1 THEN '站内链接' ELSE '站外链接' END  AS name,source,COUNT(*) AS VALUE FROM pv_access ");
		sql.append(" WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(beginDate)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(beginDate);
		}
		if(!StringUtils.isEmpty(endDate)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endDate);
		}
		sql.append(" GROUP BY source");
		
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(AccessSource.class),params.toArray());
	}

	//每小时用户访问量
	public int getHourData(int year,int month,int day,int hour, int source) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM pv_access WHERE  YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		
		sql.append(" AND source = ? ");
		
		params.add(source);
		
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//不同来源的访问流量统计
	public List<AccessGrid> findAccessGrid(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT  id,CASE source WHEN 0 THEN '直接访问' WHEN 1 THEN '站内链接' ELSE '站外链接' END  AS name, ");
		sql.append(" COUNT(*) AS pv,COUNT(DISTINCT visitorid) AS uv , COUNT(DISTINCT ip_long) AS ipNum, FORMAT(AVG(visit_hour) ,2) AS accessTime ,source FROM pv_access WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		sql.append(" GROUP BY source");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(AccessGrid.class),params.toArray());
	}
	
	
	
	public List<MapGrid> findChinaGridForChinaPie(String starttime,String endtime) {
		List paramList = new ArrayList();
		StringBuffer sql = new StringBuffer(" SELECT t.* FROM (SELECT province  province, ");
		sql.append(" COUNT(*) AS pv,COUNT(DISTINCT visitorid) AS uv , COUNT(DISTINCT ip_long) AS ipNum, FORMAT(AVG(visit_hour) ,2) AS accessTime,SUM( CASE  WHEN url_dest='https://passport.hanxinbank.com/register.html' THEN 1 ELSE 0 END ) registVisitNum ,SUM( CASE  WHEN url_dest='https://passport.hanxinbank.com/register3.html' THEN 1 ELSE 0 END ) registNum  FROM pv_day_all where 1=1 and province is not null ");
		if (starttime!=null && !starttime.equals("")){
			sql.append(" and visit_datetime >=?");
			paramList.add(starttime+" 00:00:00");
		}
		if (endtime!=null && !endtime.equals("")){
			sql.append(" and visit_datetime <=?");
			paramList.add(endtime +" 23:59:59");
		}
		sql.append(" GROUP BY province  ) t ORDER BY t.pv DESC  LIMIT 0,10 ");
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(MapGrid.class),paramList.toArray());
	}
	public int findChinaGridForChinaPieTotal(String starttime,String endtime) {
		List paramList = new ArrayList();
		StringBuffer sql = new StringBuffer(" SELECT  COUNT(DISTINCT ip_long) AS ipNum   FROM pv_day_all where 1=1 ");
		if (starttime!=null && !starttime.equals("")){
			sql.append(" and visit_datetime >=?");
			paramList.add(starttime+" 00:00:00");
		}
		if (endtime!=null && !endtime.equals("")){
			sql.append(" and visit_datetime <=?");
			paramList.add(endtime +" 23:59:59");
		}
		return pvjdbcTemplate.queryForInt(sql.toString(), paramList.toArray());
	}
	public List<MapGrid> findChinaGrid(String starttime,String endtime) {
		List paramList = new ArrayList();
		StringBuffer sql = new StringBuffer(" SELECT t.* FROM (SELECT province  province, ");
		sql.append(" COUNT(*) AS pv,COUNT(DISTINCT visitorid) AS uv , COUNT(DISTINCT ip_long) AS ipNum, FORMAT(AVG(visit_hour) ,2) AS accessTime,SUM(CASE  WHEN h5Url ='https://passport.hanxinbank.com/register2.html' THEN 1 WHEN h5Url='注册' THEN 1 WHEN h5Url='http://m.hanxinbank.com/#signup' THEN 1  ELSE 0 END) registVisitNum ,SUM( CASE  WHEN h5Url='https://passport.hanxinbank.com/register3.html' THEN 1 WHEN h5Url='http://m.hanxinbank.com/#signupSuccess' THEN 1 WHEN h5Url='注册成功' THEN 1 ELSE 0 END ) registNum  FROM pv_day_all where 1=1 and province is not null ");
		if (starttime!=null && !starttime.equals("")){
			sql.append(" and visit_datetime >=?");
			paramList.add(starttime+" 00:00:00");
		}
		if (endtime!=null && !endtime.equals("")){
			sql.append(" and visit_datetime <=?");
			paramList.add(endtime +" 23:59:59");
		}
		sql.append(" GROUP BY province  ) t ORDER BY t.pv DESC");
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(MapGrid.class),paramList.toArray());
	}

	public int findChinaGridSkip(String starttime,String endtime,String province) {
		int num=0;
		
		StringBuffer sql = new StringBuffer(" SELECT count(*) cu FROM pv_day_all where province=? ");
		List paramList = new ArrayList();
		paramList.add(province);
		if (starttime!=null && !starttime.equals("")){
			sql.append(" and visit_datetime >=?");
			paramList.add(starttime+" 00:00:00");
		}
		if (endtime!=null && !endtime.equals("")){
			sql.append(" and visit_datetime <=?");
			paramList.add(endtime +" 23:59:59");
		}
		sql.append(" group by  visitorid ");
		List list = pvjdbcTemplate.queryForList(sql.toString(), paramList.toArray());
		for (int i=0;i<list.size();i++){
			int cu = Integer.parseInt(((Map)list.get(i)).get("cu").toString());
			if (cu>=2){
				num=num+1;
			}
		}
		return num;
	}
	

	//单个页面的流量统计
	public List<AccessGrid> findAccessGridBySource(Integer source,String begin,String end) {

		StringBuffer sql = new StringBuffer(" SELECT  id, url_dest AS url_dest, COUNT(*) AS pv,COUNT(DISTINCT visitorid) AS uv , COUNT(DISTINCT ip_long) AS ipNum, ");
		sql.append(" FORMAT(AVG(visit_hour) ,2) AS accessTime ,source FROM pv_access WHERE source=?  ");
		List<Object> params = new ArrayList<Object>();
		params.add(source);
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		sql.append(" GROUP BY url_dest ");
		
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(AccessGrid.class),params.toArray());
	}

	//不同访问来源的页面的二跳率
	public String getSkipBySource(Integer source,String url,String begin,String end) {
		StringBuffer sql = new StringBuffer("SELECT CONCAT(IFNULL(FORMAT(SUM(case when num >= 2 then 1 else 0 end)/COUNT(*)*100,0),0),'%') AS skip FROM (");
		sql.append(" SELECT COUNT(*) AS num,visitorid FROM pv_access WHERE source=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(source);
		if(!StringUtils.isEmpty(url)) {
			sql.append(" AND url_dest = ? ");
			params.add(url);
		}
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		sql.append("GROUP BY visitorid)t");
		
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
	}

	//查询pc页面排行
	public List<PageGrid> findPageGrid(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT pageName,CASE pageName WHEN '其他' THEN '' ELSE url_dest END AS url_dest  ,COUNT(DISTINCT visitorid) AS uv ,COUNT(*) AS pv , FORMAT(AVG(visit_hour),2) AS accessTime FROM  pv_access  WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		sql.append(" GROUP BY pageName ORDER BY UV DESC ");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(PageGrid.class),params.toArray());
	}

	//查询pv
	public int getPvData(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//查询uv
	public int getUvData(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//查询一段时间的访问ip数量
	public int getIpNum(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT ip_long) FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//查询一段时间的二跳率
	public String getSkipData(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT CONCAT(IFNULL(FORMAT(SUM(case when num >= 2 then 1 else 0 end)/COUNT(*)*100,2),0),'%')  AS skip FROM ( ");
		sql.append(" SELECT COUNT(*) AS num,visitorid FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		sql.append("  GROUP BY visitorid)t ");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
	}

	//查询一段时间的跳失率
	public String getLostData(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT CONCAT(IFNULL(FORMAT(SUM(case when num = 1 then 1 else 0 end)/COUNT(visitorid)*100,2),0),'%') AS skip FROM ( ");
		sql.append(" SELECT COUNT(*) AS num,visitorid FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		sql.append("  GROUP BY visitorid)t ");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
	}

	//根据时间查询平均访问时间
	public String getAccessTime(String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT IFNULL(AVG(visit_hour),0)  FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
	}

	//每小时的pv量
	public Integer gePvtHourData(int year,int month,int day,int hour) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM pv_day_all WHERE  YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//每小时的uv量
	public Integer geUvtHourData(int year,int month,int day,int hour) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//每小时的ip数
	public Integer geIpNumtHourData(int year,int month,int day,int hour) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(DISTINCT ip_long) FROM pv_day_all  WHERE YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//每小时的跳失率
	public String geLostHourData(int year,int month,int day,int hour) {
		StringBuffer sql = new StringBuffer(" SELECT IFNULL(FORMAT(SUM(case when num = 1 then 1 else 0 end)/SUM(num),2),0) AS skip FROM ( ");
		sql.append(" SELECT COUNT(*) AS num,visitorid FROM pv_day_all WHERE YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		sql.append("  GROUP BY visitorid)t ");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
		
	}

	//平均每小时访问时间
	public String geAccessTimeHourData(int year,int month,int day,int hour) {
		StringBuffer sql = new StringBuffer(" SELECT IFNULL(AVG(visit_hour),0)  FROM pv_day_all WHERE YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
	}

	//访问渠道
	public List<AccessSource> findChannelData(String starttime, String endtime) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM ( SELECT COUNT(DISTINCT ip_long) AS value,channel AS name FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		sql.append("  GROUP BY channel ) T");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(AccessSource.class),params.toArray());
	}

	//一段时间内的新增注册用户
	public int getNewUserData(String starttime, String endtime) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE h5Url IN ('https://passport.hanxinbank.com/register3.html', ");
		sql.append(" '注册成功','http://m.hanxinbank.com/#signupSuccess') ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//一段时间内的注册页面点击量
	public int getClickRateData(String starttime, String endtime) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM pv_day_all WHERE h5Url IN ('https://passport.hanxinbank.com/register2.html' , ");
		sql.append(" '注册','http://m.hanxinbank.com/#signup') ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//一段时间的注册成功率
	public String getSuccessRate(String starttime, String endtime) {
		StringBuffer sql = new StringBuffer(" SELECT CONCAT(FORMAT(IFNULL(SUM(CASE WHEN h5Url IN('https://passport.hanxinbank.com/register3.html','注册成功','http://m.hanxinbank.com/#signupSuccess') THEN 1 ELSE 0 END)/SUM(CASE WHEN h5Url IN ('https://passport.hanxinbank.com/register.html','注册','http://m.hanxinbank.com/#signup') THEN 1 ELSE 0 END)*100,0 ),2),'%') FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
	}

	
	//未登录的访客数量
	public int getNoLoginUser(String starttime, String endtime) {
		//StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE userid IS NULL ");
		StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//一段时间的注册页面的平均停留时间
	public String getRegisterTime(String starttime, String endtime) {
		StringBuffer sql = new StringBuffer(" SELECT IFNULL(FORMAT(AVG(visit_hour),2),0)FROM pv_day_all WHERE visitorid IN (  ");
		sql.append(" SELECT DISTINCT visitorid FROM pv_day_all WHERE h5Url IN ('https://passport.hanxinbank.com/register3.html'  , ");
		sql.append(" 'http://m.hanxinbank.com/#signupSuccess' ,'注册成功')) AND h5Url IN ('https://passport.hanxinbank.com/register3.html' ,  'https://passport.hanxinbank.com/register.html' , ");
		sql.append(" 'https://passport.hanxinbank.com/register2.html','https://passport.hanxinbank.com/register.html',  'http://m.hanxinbank.com/#signup' ,'http://m.hanxinbank.com/#signupSuccess' ,'注册','注册成功') ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForObject(sql.toString(), String.class,params.toArray());
	}

	//每小时的新增用户量
	public Integer getNewUserHourData(int year,int month,int day,int hour) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE h5Url IN ('https://passport.hanxinbank.com/register3.html', ");
		sql.append(" '注册成功','http://m.hanxinbank.com/#signupSuccess') ");
		sql.append(" AND YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//每小时注册页的点击量
	public Integer getClickRateHourData(int year,int month,int day,int hour) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM pv_day_all WHERE h5Url IN ('https://passport.hanxinbank.com/register2.html' , ");
		sql.append(" '注册','http://m.hanxinbank.com/#signup') ");
		sql.append(" AND YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}

	//排行前五的页面访问量
	public List<AccessSource> findPagePvData(String starttime, String endtime) {
		StringBuffer sql = new StringBuffer("SELECT * FROM ( SELECT pageName AS name,count(*) AS value FROM pv_access WHERE pageName != '其他' ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		sql.append("  GROUP BY pageName  ORDER BY value DESC LIMIT 0,5 )T ");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(AccessSource.class),params.toArray());
	}

	//总访问量
	public int findAllPvData(String starttime, String endtime) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM pv_access WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(starttime)) {
			sql.append(" AND visit_datetime >= ? ");
			params.add(starttime);
		}
		if(!StringUtils.isEmpty(endtime)) {
			sql.append(" AND visit_datetime <= ? ");
			params.add(endtime);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(), params.toArray());
	}

	//安卓、ios、H5的页面统计
	public List<PageGrid> findChannelPageGrid(String channel, String begin, String end) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM ( SELECT COUNT(*) AS pv,h5url AS url_dest,COUNT(DISTINCT visitorid) AS uv , FORMAT(AVG(visit_hour),2) AS accessTime FROM pv_day_all WHERE status=1 AND  channel = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(channel);
		if(!StringUtils.isEmpty(begin)) {
			sql.append(" AND visit_datetime >= ?");
			params.add(begin);
		}
		if(!StringUtils.isEmpty(end)) {
			sql.append(" AND visit_datetime <= ?");
			params.add(end);
		}
		sql.append("  GROUP BY h5Url ORDER BY uv DESC ) T");
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(PageGrid.class),params.toArray());
	}


	//查询所有的status为0的ios和安卓的流量
	public List<SqlPv> findAllPvList(int status) {
		StringBuffer sql = new StringBuffer("  SELECT * FROM pv_day_all WHERE channel IN(2,3,4)");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtils.isEmpty(status)) {
			sql.append(" AND status = ? ");
			params.add(status);
			
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.query(sql.toString(), new EntityMapper(SqlPv.class),params.toArray());
	}



	public void updatePvAll(Integer id,String h5Url) {
		StringBuffer sql = new StringBuffer(" UPDATE pv_day_all SET status=1,h5Url=? WHERE id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(h5Url);
		params.add(id);
		logger.info(sql.toString());
		logger.info(params);
		pvjdbcTemplate.update(sql.toString(),params.toArray());
		
	}


	//每小时的陌生访客数量
	public Integer getNoLoginUserHourData(int year,int month,int day,int hour) {
		//StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE userid IS NULL ");
		StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT visitorid) FROM pv_day_all WHERE 1=1  ");
		sql.append(" AND YEAR(visit_datetime) = ? ");
		sql.append(" AND MONTH(visit_datetime) = ? ");
		sql.append(" AND DAY(visit_datetime) = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		params.add(day);
		if(hour!=-1) {
			sql.append(" AND HOUR(visit_datetime) = ? ");
			params.add(hour);
		}
		logger.info(sql.toString());
		logger.info(params);
		return pvjdbcTemplate.queryForInt(sql.toString(),params.toArray());
	}
	
	

}
