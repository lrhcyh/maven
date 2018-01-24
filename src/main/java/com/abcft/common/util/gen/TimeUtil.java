package com.abcft.common.util.gen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作时间的工具类
 * 
 * @author mike <br>
 *         2015年12月30日
 * @version 1.0
 */
public final class TimeUtil {

	private static final Logger LOG = LoggerFactory.getLogger(TimeUtil.class);
	/**  一秒=1000毫秒 */
	public static final long ONE_SECOND = 1000L;
	/** 通用的格式 */
	public static final String GENERAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 截止到分钟格式 */
	public static final String MINT_PATTERN = "yyyy-MM-dd HH:mm";
	/** 截止到小时格式 */
	public static final String HOUR_PATTERN = "yyyy-MM-dd HH";
	/** 截止到天格式 */
	public static final String DAY_PATTERN = "yyyy-MM-dd";
	/** 只返回时间格式 */
	public static final String TIME_PATTERN = "HH:mm:ss";
	/** 天数差 */
	public static final int DIFF_DAY = 1;
	/** 小时差 */
	public static final int DIFF_HOUR = 2;
	/** 分钟差 */
	public static final int DIFF_MINUTE = 3;
	/** 秒数差 */
	public static final int DIFF_SECOND = 4;
	/** 分钟毫秒数 */
	public static final long MINT_MILLIS = 60 * ONE_SECOND;
	/** 小时毫秒数 */
	public static final long HOUR_MILLIS = 60 * MINT_MILLIS;
	/** 天毫秒数 */
	public static final long DAY_MILLIS = 24 * HOUR_MILLIS;

	private TimeUtil() {
	}

	/**
	 * 转换成秒
	 * 
	 * @param millis
	 * @return long
	 */
	public static long toSecs(long millis) {
		return (long) Math.ceil((double) millis / ONE_SECOND);
	}

	/**
	 * 转换成毫秒
	 * 
	 * @param secs
	 * @return long
	 */
	public static long toMillis(int secs) {
		return secs * ONE_SECOND;
	}

	/**
	 * 获取当前时间
	 * 
	 * @see GENERAL_PATTERN yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return String
	 */
	public static String toCurrentGeneral() {
		return toGeneral(new Date());
	}

	/**
	 * 把时间转为通用的格式的字符串
	 * 
	 * @see GENERAL_PATTERN yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return String
	 */
	public static String toGeneral(Date date) {
		return toPattern(date, GENERAL_PATTERN);
	}

	/**
	 * 时间自定义格式转换成字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static String toPattern(Date date, String pattern) {
		String dateStr = null;
		try {
			SimpleDateFormat noopFormat = new SimpleDateFormat();
			noopFormat.applyPattern(pattern);
			dateStr = noopFormat.format(date);
		} catch (Exception e) {
			LOG.error("date to more pattern format error, pattern:" + pattern, e);
			throw new RuntimeException(e);
		}
		return dateStr;
	}

	/**
	 * 通用时间字符串转换时间
	 * 
	 * @see GENERAL_PATTERN yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return Date
	 */
	public static Date toGeneral(String dateStr) {
		return toPattern(dateStr, GENERAL_PATTERN);
	}

	/**
	 * 自定义时间字符串转换时间
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return Date
	 */
	public static Date toPattern(String dateStr, String pattern) {
		Date date = null;
		try {
			SimpleDateFormat noopFormat = new SimpleDateFormat();
			noopFormat.applyPattern(pattern);
			date = noopFormat.parse(dateStr);
		} catch (ParseException e) {
			LOG.error("date string to more pattern format error, pattern:" + pattern, e);
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 通用根据当前时间戳返回格式化时间字符串
	 * 
	 * @see GENERAL_PATTERN yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String toGeneral(long timestamp) {
		return toPattern(timestamp, GENERAL_PATTERN);
	}

	/**
	 * 根据当前时间戳返回格式化时间字符串
	 * 
	 * @param timestamp
	 * @param pattern
	 * @return String
	 */
	public static String toPattern(long timestamp, String pattern) {
		String dateStr = null;
		try {
			SimpleDateFormat noopFormat = new SimpleDateFormat();
			noopFormat.applyPattern(pattern);
			dateStr = noopFormat.format(new Date(timestamp));
		} catch (Exception e) {
			LOG.error("date timestamp to more pattern format error, pattern:" + pattern, e);
			throw new RuntimeException(e);
		}
		return dateStr;
	}

	/**
	 * 计算两个时间差
	 * 
	 * @param diffType
	 *            1:day 2:hour 3:minute 4:second
	 * 
	 *            start or end time string format @see GENERAL_PATTERN yyyy-MM-dd HH:mm:ss
	 * 
	 * @param start
	 * @param end
	 * @return long
	 */
	public static long diffDate(int diffType, String start, String end) {
		if (DIFF_DAY == diffType)
			return doDiff(start, end, "yyyy-MM-dd", DAY_MILLIS);
		else if (DIFF_HOUR == diffType)
			return doDiff(start, end, "yyyy-MM-dd HH", HOUR_MILLIS);
		else if (DIFF_MINUTE == diffType)
			return doDiff(start, end, "yyyy-MM-dd HH:mm", MINT_MILLIS);
		else if (DIFF_SECOND == diffType)
			return doDiff(start, end, GENERAL_PATTERN, ONE_SECOND);
		return 0;
	}

	/**
	 * 两个时间的秒数差
	 * 
	 * @param start
	 * @param end
	 * @return long
	 */
	private static long doDiff(String start, String end, String pattern, long millis) {
		Date startDate = null;
		Date endDate = null;
		try {
			SimpleDateFormat noopFormat = new SimpleDateFormat();
			noopFormat.applyPattern(pattern);
			startDate = noopFormat.parse(start);
			endDate = noopFormat.parse(end);
		} catch (ParseException e) {
			LOG.error("diff date for diff calc error", e);
			throw new RuntimeException(e);
		}
		return (endDate.getTime() - startDate.getTime()) / millis;
	}

	/**
	 * 测试
	 */
	static class Tester {
		public static void main(String[] args) {
			String dateStr = "2015-12-12 12:00:00";
			String end = "2015-12-12 12:10:12";
			long diff = diffDate(DIFF_SECOND, dateStr, end);
			System.out.println(diff);
		}
	}

}
