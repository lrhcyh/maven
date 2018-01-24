package com.abcft.common.util.gen;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public final class StringUtil {

	static class Tester {
		public static void main(String[] args) {
			String str = "dsdfs:" + SEPARATOR + "-sdfsdfsdf";
			System.out.println(str);
			String arr[] = split(str, SEPARATOR);
			for (String string : arr) {
				System.out.println(string);
			}
		}
	}

	private StringUtil() {
	}

	/**
	 * 字符串分隔符
	 */
	public static final String SEPARATOR = String.valueOf((char) 29);

	/**
	 * 判断为空
	 * 
	 * true : 为空 false: 不为空
	 * 
	 * @param text
	 * @return boolean
	 */
	public static boolean isEmpty(String text) {
		if (null == text || "".equals(text) || text.trim().isEmpty())
			return true;
		return false;
	}

	/**
	 * 判断不为空
	 * 
	 * @param text
	 * @return boolean
	 */
	public static boolean isNotEmpty(String text) {
		return !isEmpty(text);
	}

	/**
	 * 根据separator分割字符串为字符串数组
	 * 
	 * @param text
	 * @param separator
	 * @return String[]
	 */
	public static String[] split(String text, String separator) {
		return StringUtils.splitByWholeSeparator(text, separator);
	}

	/**
	 * 替换字符串中regex匹配所有的内容为replace
	 * 
	 * @param text
	 * @param regex
	 * @param replace
	 * @return String
	 */
	public static String replaceAll(String text, String regex, String replace) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, replace);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 获得包含中文的字符串长度方法(一个中文为2个字符)
	 * 
	 * @param text
	 * @return int
	 */
	public static int len(String text) {
		if (isEmpty(text))
			return 0;
		int len = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) > 127 || text.charAt(i) == 94) {
				len += 2;
			} else {
				len++;
			}
		}
		return len;
	}

	/**
	 * 带有默认值的为空判断
	 * 
	 * @param text
	 * @param def
	 * @return
	 */
	public static String defIfEmpty(String text, String def) {
		return isEmpty(text) ? def : text;
	}

	/**
	 * 替换{}中的内容
	 * 
	 * @param sourceString
	 * @param object
	 * @return
	 */
	public static String replaceBrackets(final String sourceString, Object[] object) {
		String temp = sourceString;
		for (int i = 0; i < object.length; i++) {
			String[] result = (String[]) object[i];
			Pattern pattern = Pattern.compile(result[0]);
			Matcher matcher = pattern.matcher(temp);
			temp = matcher.replaceAll(result[1]);
		}
		return temp;
	}

	public static String[] getConditionField(String condition) {
		String[] fileds = new String[3];
		if (condition.contains("<=")) {
			fileds[0] = condition.substring(0, condition.lastIndexOf("<="));
			fileds[1] = "<=";
			fileds[2] = condition.substring(condition.lastIndexOf("<=") + 2);

		} else if (condition.contains(">=")) {
			fileds[0] = condition.substring(0, condition.lastIndexOf(">="));
			fileds[1] = ">=";
			fileds[2] = condition.substring(condition.lastIndexOf(">=") + 2);

		} else if (condition.contains("=")) {
			fileds[0] = condition.substring(0, condition.lastIndexOf("="));
			fileds[1] = "=";
			fileds[2] = condition.substring(condition.lastIndexOf("=") + 1);

		} else if (condition.contains("<")) {
			fileds[0] = condition.substring(0, condition.lastIndexOf("<"));
			fileds[1] = "<";
			fileds[2] = condition.substring(condition.lastIndexOf("<") + 1);

		} else if (condition.contains(">")) {
			fileds[0] = condition.substring(0, condition.lastIndexOf(">"));
			fileds[1] = ">";
			fileds[2] = condition.substring(condition.lastIndexOf(">") + 1);
		} else if (condition.contains("in")) {

		} else if (condition.contains("not")) {

		}

		return fileds;

	}

	/**
	 * 解析工作留的公文 {Y} ：表示年{M} ：表示月{D}：表示日
	 * 
	 * {H} ：表示时{I} ：表示分{S}：表示秒
	 * 
	 * {F} ：表示流程名{U} ：表示用户姓名{R}：表示角色
	 * 
	 * {FS}：表示流程分类名称{SD}：表示部门
	 * 
	 * {RUN}：表示流水号
	 * 
	 * {N} ：表示编号，通过编号计数器取值并自动增加计数值
	 * 
	 * 
	 * @param flowAutoName
	 * @return
	 */
	public static String dealFlowSymbol(String flowAutoName,String flowName, String userName,String roleName,String sortName,String deptName,String runId,String num) {
		Calendar cal = Calendar.getInstance();
		String[][] symbols = { 
				new String[] { "\\{Y\\}", String.valueOf(cal.get(Calendar.YEAR)) }, 
				new String[] { "\\{M\\}", String.valueOf(cal.get(Calendar.MONTH) + 1) }, 
				new String[] { "\\{D\\}", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) }, 
				new String[] { "\\{H\\}", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) }, 
				new String[] { "\\{I\\}", String.valueOf(cal.get(Calendar.MINUTE)) }, 
				new String[] { "\\{S\\}", String.valueOf(cal.get(Calendar.SECOND)) },
				new String[] { "\\{F\\}", flowName },
				new String[] { "\\{U\\}", userName },
				new String[] { "\\{R\\}", roleName },
				new String[] { "\\{FS\\}", sortName },
				new String[] { "\\{SD\\}", deptName },
				new String[] { "\\{RUN\\}", runId },
				new String[] { "\\{N\\}", num }
				
		};
		return replaceBrackets(flowAutoName, symbols);

	}

	public static void main(String[] args) {
		String str = "Java目前的发展史是由{Y}年-{M}月{D}{H}{I}{S}{F}{U}{R}{FS}{SD}{RUN}{N}";
		String[][] object = { new String[] { "\\{0\\}", "1995" }, new String[] { "\\{1\\}", "2007" } };
		//System.out.println(replaceBrackets(str, object));

		/*
		 * String prcs_in =
		 * "姓名=as,起始时间>=2016-10-14,起始时间<=2016-10-14,起始时间>2016-10-14,起始时间<2016-10-14";
		 * String[] ss = prcs_in.split(","); for (String field : ss) { String[]
		 * fs = getConditionField(field);
		 * System.out.println(Arrays.toString(fs)); }
		 */

		//System.out.println(dealFlowSymbol(str));
		System.out.println(dealFlowSymbol(str,"flowName", "zssa", "role", "sort", "dept", "121", "010"));

	}

}
