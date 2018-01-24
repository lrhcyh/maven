package com.abcft.common.util.gen;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 有效验证
 * 
 * @author mike <br>
 *         2015年12月31日
 * @version 1.0
 */
public final class ValidUtil {

	private ValidUtil() {
	}

	/**
	 * 
	 * 判断一个数据对象是否为空
	 * 
	 * @param data
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object data) {
		if (null == data)
			return true;
		if (data instanceof String) {
			return String.valueOf(data).trim().isEmpty();
		} else if (data instanceof Map) {
			return ((Map) data).isEmpty();
		} else if (data instanceof Collection) {
			Collection coll = (Collection) data;
			return coll.isEmpty();
		} else if (data.getClass().isArray()) {
			return Array.getLength(data) == 0;
		} else if (data instanceof Long) {
			Long l = (Long) data;
			return l == 0;
		}
		return false;
	}

	/**
	 * 判断一个数据对象不会空
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object data) {
		return !isEmpty(data);
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param collection
	 * @return boolean
	 */
	public static boolean collEmpty(Collection<?> coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * 判断 Map 是否非空
	 * 
	 * @param map
	 * @return boolean
	 */
	public static boolean mapEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 是否是int类型的数字
	 * 
	 * @param text
	 * @return boolean
	 */
	public static boolean isInt(String text) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(text);
		if (isNum.matches()) {
			return true;
		}
		return false;
	}

}
