package com.abcft.common.util.gen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类型转换工具类
 * 
 * @author mike <br>
 *         2015年12月30日
 * @version 1.0
 */
public final class CaseUtil {

	private static final Logger LOG = LoggerFactory.getLogger(CaseUtil.class);

	private CaseUtil() {
	}

	/**
	 * 
	 * 转换object类型为字符串
	 * 
	 * 默认值为null
	 * 
	 * @param data
	 * @return String
	 */
	public static String toString(Object data) {
		return toString(data, null);
	}

	/**
	 * 转换object类型为字符串
	 * 
	 * @param data
	 * @param def
	 *            (默认值)
	 * @return String
	 */
	public static String toString(Object data, String def) {
		return null != data ? String.valueOf(data) : def;
	}

	/**
	 * 转换object类型为int数值
	 * 
	 * 默认值为0
	 * 
	 * @param data
	 * @return int
	 */
	public static int toInt(Object data) {
		return toInt(data, 0);
	}

	/**
	 * 转换object类型为int数值
	 * 
	 * @param data
	 * @param def
	 * @return int
	 */
	public static int toInt(Object data, int def) {
		try {
			String intValStr = toString(data);
			if (StringUtil.isNotEmpty(intValStr))
				return Integer.parseInt(intValStr);
		} catch (NumberFormatException e) {
			LOG.error("case to int type error", e);
		}
		return def;
	}

	/**
	 * 转换object类型为double数值
	 * 
	 * 默认值为0
	 * 
	 * @param data
	 * @return double
	 */
	public static double toDouble(Object data) {
		return toDouble(data, 0);
	}

	/**
	 * 
	 * 转换object类型为double数值
	 * 
	 * @param data
	 * @param def
	 * @return double
	 */
	public static double toDouble(Object data, double def) {
		try {
			String dobValStr = toString(data);
			if (StringUtil.isNotEmpty(dobValStr))
				return Double.parseDouble(dobValStr);
		} catch (NumberFormatException e) {
			LOG.error("case to double type error", e);
		}
		return def;
	}

	/**
	 * 转换object类型为long数值
	 * 
	 * @param data
	 * @param def
	 * @return long
	 */
	public static long toLong(Object data, long def) {
		try {
			String longValStr = toString(data);
			if (StringUtil.isNotEmpty(longValStr))
				return Long.parseLong(longValStr);
		} catch (NumberFormatException e) {
			LOG.error("case to long type error", e);
		}
		return def;
	}

	/**
	 * 转换object类型为long数值
	 * 
	 * 默认值为0
	 * 
	 * @param data
	 * @return long
	 */
	public static long toLong(Object data) {
		return toLong(data, 0);
	}

	/**
	 * 转换object类型为boolean值
	 * 
	 * @param data
	 * @param def
	 * @return boolean
	 */
	public static boolean toBoolean(Object data, boolean def) {
		try {
			String boolValStr = toString(data);
			if (StringUtil.isNotEmpty(boolValStr))
				return Boolean.parseBoolean(boolValStr);
		} catch (Exception e) {
			LOG.error("case to boolean type error", e);
		}
		return def;
	}

	/**
	 * 转换object类型为boolean值
	 * 
	 * 默认值为false
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean toBoolean(Object data) {
		return toBoolean(data, false);
	}

}
