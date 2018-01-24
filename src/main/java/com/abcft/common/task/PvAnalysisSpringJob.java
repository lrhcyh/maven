package com.abcft.common.task;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.abcft.system.analysis.AnalysisDao;
import com.abcft.system.analysis.SqlPv;

/**
 * 后台pv分析定时任务
 * @author 武平阳
 *
 */
@Component
public class PvAnalysisSpringJob {

	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private AnalysisDao analysisDao;

	//访问来源分析
	@Scheduled(cron = "0 */10 * * * ?")
	public void run() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String num = "";
		if(day<10) {
			num ="0"+String.valueOf(day);
		} else {
			num = String.valueOf(day);
		}
		//添加pc端流量数据
		addPvAccess(num);
		//截断pv_day_all中status为0channel为2,3的url
		updatePvDayAll();
	}

	private void updatePvDayAll() {
		//查询pv_all的app数据
		List<SqlPv> list = analysisDao.findAllPvList(0);
		for (SqlPv pv : list) {
			//更改状态和h5Url
			analysisDao.updatePvAll(pv.getId(),processH5Url(pv.getUrl_dest()));
		}
	}
	
	//截取h5Url
	private static String processH5Url(String str) {
		String dest = str;
		if (StringUtils.indexOf(dest, "?")>0) {
			dest = StringUtils.substringBefore(dest, "?");
		}
		if (StringUtils.isNotBlank(dest)&&dest.matches("^.*/#.+/.*")) {
			dest = StringUtils.substringBeforeLast(dest, "/");
		}
		return dest;
	}

	/**
	 * @param num
	 */
	private void addPvAccess(String num) {
		//查询新增的pv
		List<SqlPv> list = analysisDao.findPvList(num,0);
		for (SqlPv sqlPv : list) {
			//更新status为1
			analysisDao.updatePvStatus(num,sqlPv.getId());
			//去掉父级url参数
			if(!StringUtils.isEmpty(sqlPv.getUrl_ref())) {
				if(sqlPv.getUrl_ref().contains("?")) {
					sqlPv.setUrl_ref(sqlPv.getUrl_ref().substring(0, sqlPv.getUrl_ref().indexOf("?")-1));
				}
				if(sqlPv.getUrl_ref().contains("#")) {
					sqlPv.setUrl_ref(sqlPv.getUrl_ref().substring(0, sqlPv.getUrl_ref().indexOf("#")-1));
				}
			}
			//根据父级域名判断访问来源
			String ref = sqlPv.getUrl_ref();
			if(ref==null || ref=="") {
				sqlPv.setSource(0);
			} else if(ref.contains("hanxinbank.com")) {
				sqlPv.setSource(1);
			} else {
				sqlPv.setSource(2);
			}
			//判断页面名称
			//去掉参数
			if(!StringUtils.isEmpty(sqlPv.getUrl_dest())) {
				if(sqlPv.getUrl_dest().contains("?")) {
					sqlPv.setUrl_dest(sqlPv.getUrl_dest().substring(0, sqlPv.getUrl_dest().indexOf("?")-1));
				}
				if(sqlPv.getUrl_dest().contains("#")) {
					sqlPv.setUrl_dest(sqlPv.getUrl_dest().substring(0, sqlPv.getUrl_dest().indexOf("#")-1));
				}
			}
			
			sqlPv.setPageName(findPage(sqlPv.getUrl_dest()));
			analysisDao.insertPvAccess(sqlPv);
		}
	}

	//需要统计的页面名称
	private static String findPage(String url_dest) {
		String pageName = "";
		if(!StringUtils.isEmpty(url_dest)) {
			switch (url_dest) {
			case "http://www.aqlicai.cn":
				pageName = "首页";
				break;
			case "http://www.aqlicai.cn/chart/result":
				pageName = "数据图表";
				break;
			case "http://www.aqlicai.cn/company-finance/summary":
				pageName = "个股分析";
				break;
			case "http://www.aqlicai.cn/report-search":
				pageName = "研究报告";
				break;
			case "http://www.aqlicai.cn/notice":
				pageName = "公告";
				break;
			case "http://www.aqlicai.cn/news":
				pageName = "咨询信息";
				break;

			default:
				pageName = "其他";
				break;
			}
		}
		
		return pageName;
	}
	
	
}
