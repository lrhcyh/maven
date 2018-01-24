package com.abcft.common.core;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
/**
 * 项目枚举定义
 * @author inning
 * @DateTime 2015-7-20 上午10:30:56
 *
 */
public final class Global {
	
	public static final int PAGE_ROW = 12;

	public static final int VALIDATE_DAY = 3;

	public static String WEB_DIR = null;

	public static String WEBAPP_PATH = null;

	public static String REQUEST_PATH = null;

	public static final int UPLOAD_MAX_SIZE = 1024000;

	public static final int FILE_IO_BUFFER = 1024;

	public static final String EXP_SPLIT = ",";

	public static final String EXP_ROWNUM = "50000";

	public static final String UPLOAD_DIRECTORY = "Upload";

	public static final String IEA_DIRECTORY = "IEAIMAGE";

	public static final String UPLOAD_FILE_TYPE = "|xls|doc|bak|rar|";

	public static final String UPLOAD_FILE_TYPE_ANTI = "|exe|com|bat|";

	public static final String[] UPLOAD_FILE_TYPE_TOKEN = { "doc", "xls" };

	public static final SimpleDateFormat CHS_DATE = new SimpleDateFormat(
			"yyyy年MM月dd日");

	public static final SimpleDateFormat SIM_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final SimpleDateFormat SIM_TIME = new SimpleDateFormat(
			"HH:mm:ss");

	public static final DecimalFormat INT_DECIMAL = new DecimalFormat("#");

	public static final DecimalFormat FLOAT_DECIMAL = new DecimalFormat("#0.00");

	public static final DecimalFormat FLOAT_DECIMAL2 = new DecimalFormat("#0.0");

	public static final DecimalFormat DOUBLE_DECIMAL = new DecimalFormat(
			"#0.00");

	public static final DecimalFormat MONEY_DECIMAL = new DecimalFormat(
			"###,##0.00");

	public static final String[] WEEK_ENUM = { "星期日", "星期一", "星期二", "星期三",
			"星期四", "星期五", "星期六" };

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	public static final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HHmmss");

	public static final SimpleDateFormat noseparatordatetimeFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static final String MESSAGE_SAVESUCCESS = "保存操作成功！";

	public static final String MESSAGE_DELSUCCESS = "删除操作成功！";

	public static final String MESSAGE_SAVEERROR = "保存操作失败！";

	public static final String MESSAGE_DELERROR = "删除操作失败！";

	public static final Integer UNSAVEDID = null;

	public static final String ERROR_WANTPROPERTY = "error.wantproperty";

	public static final String ERROR_DATAACCESS = "error.dataaccess";

	public static final String ERROR_AUTHORITY = "error.authority";

	public static final String ERROR_NOSESSION = "error.nosession";

	public static final String ERROR_FILETYPE = "error.filetype";

	public static final String ERROR_FILEDOWNLOAD = "error.filedownload";

	public static final String ERROR_FILENOTFOUND = "error.filenotfound";

	public static final String ERROR_FILENAMEEXIST = "error.filenameexist";

	public static final String ERROR_FILEUPLOAD = "error.fileupload";

	public static final String ERROR_IO = "error.io";

	public static final String ERROR_PARAMETERS = "error.parameters";

	public static final String ERROR_LOGIN = "error.login";

	public static final String ERROR_LOGINYOUXIAO = "error.loginyouxiao";

	public static final String ERROR_YONGHUMING = "error.yonghuming";

	public static final String ERROR_MIMA = "error.mima";

	public static final String ERROR_FILEDATA = "error.filedata";

	public static final String ERROR_COULDNOTDELETE = "error.couldnotdelete";

	public static final String ERROR_APPINIT = "error.appinit";

	public static final String ERROR_NOTOF = "error.notof";

	public static final String ERROR_NOTDICO0 = "error.notdico0";

	public static final String ERROR_NOTCADOT = "error.notcadot";

	public static final String ERROR_PRINT = "error.print";

	public static final String ERROR_PRINT1 = "error.print1";

	public static final String ERROR_CREATEIMG = "error.createimg";

	public static final String ERROR_NOTEXISTSOF = "error.notexistsof";

	public static final String ERROR_VFLAG = "error.vflag";

	public static final String APPNAME = "朵儿网运营平台";
	
	/** 
	 * @Fields PAG_CURRENT : 当前页面
	 */ 
	public static final String PAG_CURRENT="currentPage";
	/** 
	 * @Fields PAG_MAX : 最大页面
	 */ 
	public static final String PAG_MAX="maxPage";
	/** 
	 * @Fields PAG_SUM : 页面总记录数
	 */ 
	public static final String PAG_SUM="pageSum";
	/** 
	 * @Fields PAG_SIG : 读取页面标识
	 */ 
	public static final String PAG_SIG="pageStyle";
	/** 
	 * @Fields PAG_PREVIOUS : 上一页
	 */ 
	public static final String PAG_PREVIOUS="previousPage";
	
	/** 
	 * @Fields PAG_NEXT : 下一页
	 */ 
	public static final String PAG_NEXT="nextPage";
	
	/** 
	 * @Fields PAG_LOAD : 加载初始数据
	 */ 
	public static final String PAG_LOAD="load";
	
	/** 
	 * @Fields PAG_RELOAD : 重新加载当前页面数据
	 */ 
	public static final String PAG_RELOAD="reload";
	
	/**
	 * 配置每次发送条数
	 */
	public static final Integer RECORD_INTEGER = 500;
	
	/**
	 * 定制状态	
	 */
	public static final String CUSTOMIZESTATE_YES="A";
	public static final String CUSTOMIZESTATE_NO="TA";
	/**
	 * 短信发送结果
	 */
	public static final String SENDMESSAGE_SUCCESS="发送成功！";
	public static final String SENDMESSAGE_FAIL="后台出错,发送失败！";
	/**
	 * 缴费状态
	 */
	public static final String PAYMENTSTATE_YES="正常缴费";
	public static final String PAYMENTSTATE_NO="未正常缴费";
	/*
	 * 数据有效性
	 */
	public static final String VALIDATE_TRUE="有效数据";
	public static final String VALIDATE_FALSE="无效数据";
	
	/*
	 * 新增服务错误提示信息
	 */
	public static final String ERROR_OUT = "定制表短信发送服务和定制发送短信详细记录导出服务的时间差必须大于10天小于20天";
	public static final String ERROR_UPDATE = "定制表短信发送服务和定制表发送状态变更服务的时间相差应为3天";
	public static final String ERROR_NULLNUMBER="定制发送短信详细信息导出服务和定制表身份证变更通知的时间差必须大于5天小于25天";
	
	/**
	 * 人员状态
	 */
	public static final String PERSONWORK_STATE_11="在职";
	public static final String PERSONWORK_STATE_21="退休";
	public static final String PERSONWORK_STATE_31="离休类";
	public static final String PERSONWORK_STATE_41="居民";
	public static final String PERSONWORK_STATE_NO="未知状态";

	// 存放自动运行接口
	public static final HashMap<String, Object> SYSRESOURCES = new HashMap<String, Object>();
	
	/**
	 * 短信种类，下行短信
	 */
	public static final String MESSAGE_SEND="SEND";
	/**
	 * 短信种类，上行短信
	 */
	public static final String MESSAGE_UP="UP";
	
	
	/**
	 * 上传车牌信息
	 */
	public static final String UPLOAD_PLATE="PLATE";
	/**
	 * 上传文件并保存
	 */
	public static final String UPLOAD_SAVE="SAVE";
	/**
	 * 上传驾驶证信息
	 */
	public static final String UPLOAD_LICENSE="LICENSE";
	/**
	 * 上传的定制用户信息
	 */
	public static final String UPLOAD_PERSONVERIFY="PERSONVERIFY";
	
	/**
	 * 地区名称
	 */
	public static final String ADDRESS_TIANMEN="天门";
	public static final String ADDRESS_WUHAN="武汉";
	
	/**
	 * 返回值
	 */
	public static final String RETURN_SUCCESS="SUC";
	public static final String RETURN_FAL="FAL";
	
	
	
	public static final String CHANGE_SUCCESS="变更成功";
	
	public static final String CHANGE_ERR="变更失败";
	
	/**
	 * 状态(0：无效，1：有效)
	 */
	public static final String STATUS_VALID = "1";
	public static final String STATUS_INVALID = "0";
	
	/** 
	 * @Fields STATUS_ISHAVE : 已经绑定，需要变更产品
	 */ 
	public static final String STATUS_ISHAVE="2";
	/** 
	 * @Fields STATUS_NOTEM : 余额不足
	 */ 
	public static final String STATUS_NOTEM="3";
	
	public static final String NEWPASSWORD = "123456";
	
}
