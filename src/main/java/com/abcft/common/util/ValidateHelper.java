package com.abcft.common.util;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class ValidateHelper {

	
	
	/**
	 * 判断对象为空字符串或者为null，如果满足其中一个条件，则返回true
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj == null) {
			isEmpty = true;
		} else if (obj instanceof String) {
			isEmpty = ((String) obj).trim().isEmpty();
		} else if (obj instanceof Collection) {
			isEmpty = (((Collection) obj).size() == 0);
		} else if (obj instanceof Map) {
			isEmpty = ((Map) obj).size() == 0;
		} else if (obj.getClass().isArray()) {
			isEmpty = Array.getLength(obj) == 0;
		}
		return isEmpty;
	}
	
	public static String getTimeFileName() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date) + getRandomString(16);
	}
	
	private static int getRandom(int count) {
	    return (int) Math.round(Math.random() * (count));
	    
	}
	 
	private static String string = "a1b2c3d4e5f6g7h8i9j0klmnopqrstuvwxyz";   
	 
	private static String getRandomString(int length){
	    StringBuffer sb = new StringBuffer();
	    int len = string.length();
	    for (int i = 0; i < length; i++) {
	        sb.append(string.charAt(getRandom(len-1)));
	    }
	    return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomString(16));
	}
	
	/**
	 * 检查 email输入是否正确 正确的书写格 式为 username@domain
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkEmail(String text, int length) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && text.length() <= length;
	}

	/**
	 * 检查电话输入 是否正确 正确格 式 012-87654321、0123-87654321、0123－7654321
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkTelephone(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches(
						"(0\\d{2,3}-\\d{7,8})|" + 
						"(0\\d{9,11})|" + 
						"(\\d{7})|" + 
						"(\\d{8})|" + 
						"(4\\d{2}\\d{7})|" + 
						"(4\\d{2}-\\d{7})|" + 
						"(4\\d{2}-\\d{3}-\\d{4})|" + 
						"(4\\d{2}-\\d{4}-\\d{3})");
	}

	/**
	 * 检查手机输入 是否正确
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkMobilephone(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}");
	}

	/**
	 * 检查中文名输 入是否正确
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkChineseName(String text, int length) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("^[\u4e00-\u9fa5]+$") && text.length() <= length;
	}

	/**
	 * 检查字符串中是否有空格，包括中间空格或者首尾空格
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkBlank(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("^\\s*|\\s*$");
	}

	/**
	 * 检查字符串是 否含有HTML标签
	 * 
	 * @param text
	 * @return
	 */

	public static boolean checkHtmlTag(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
	}

	/**
	 * 检查URL是 否合法
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkURL(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("[a-zA-z]+://[^\\s]*");
	}

	/**
	 * 检查IP是否 合法
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkIP(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
	}



	/**
	 * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
	 * 
	 * @param text
	 * @return
	 */

	public static boolean checkQQ(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("[1-9][0-9]{4,13}");
	}

	/**
	 * 检查邮编是否 合法
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkPostCode(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("[1-9]\\d{5}(?!\\d)");
	}

	/**
	 * 检查身份证是 否合法,15位或18位(或者最后一位为X)
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkIDCard(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("\\d{15}|\\d{18}|(\\d{17}[x|X])");
	}

	/**
	 * 检查输入是否 超出规定长度
	 * 
	 * @param length
	 * @param text
	 * @return
	 */
	public static boolean checkLength(String text, int length) {
		return ((ValidateHelper.isNullOrEmpty(text)) ? 0 : text.length()) <= length;
	}

	/**
	 * 判断是否为数字
	 * @param text
	 * @return
	 */
	public static boolean isNumber(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$"); 
	}
	
	public static Integer toInteger(String txt){
		if(!ValidateHelper.isNullOrEmpty(txt))
		 return Integer.valueOf(txt);
		return null;
	}
	
}
