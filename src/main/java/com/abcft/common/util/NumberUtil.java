package com.abcft.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * 
 * <p>
 * 数字工具类，包含Java代码中的工具
 * </p>
 * 
 */
public class NumberUtil {

	private static final Logger logger = Logger.getLogger(NumberUtil.class);
	/**
	 *  金额分转元，必须带2位小数，含有千分位 ‘,’
	 * @param value
	 * @return
	 */
	public static String formatCurrencyInstance(Object value){
		BigDecimal temp;
		if(value==null || "".equals(value)){
			temp = new BigDecimal(0);
		}else{
			temp=new BigDecimal(value.toString());
		}
		return NumberUtil.formatMoneyByDouble(temp);
	}
	
	public static String formatYuanCurrencyInstance(Object value){
        BigDecimal temp;
        if(value==null || "".equals(value)){
            temp = new BigDecimal(0);
        }else{
            temp=new BigDecimal(value.toString());
        }
        return NumberUtil.formatMoneyByDouble(temp);
    }
	
	public static String formatCurrencyInstanceNoPoint(Object value){
        BigDecimal temp;
        if(value==null || "".equals(value)){
            temp = new BigDecimal(0);
        }else{
            temp=new BigDecimal(value.toString());
        }
        return NumberUtil.formatMoneyByDoubleNoPoint(temp);
    }
	
	/**
	 * 减法后金额分转元，必须带2位小数，含有千分位 ‘,’
	 * @param value1
	 * @return
	 */
	public static String subtractAndFormat(Object value1,Object value2){
		BigDecimal temp1;
		BigDecimal temp2;
		BigDecimal temp = new BigDecimal(0);
		if(value1==null || "".equals(value1)){
			temp1 = new BigDecimal(0);
		}else{
			temp1=new BigDecimal(value1.toString());
		}
		if(value2==null || "".equals(value2)){
			temp2 = new BigDecimal(0);
		}else{
			temp2=new BigDecimal(value2.toString());
		}
		if(temp1.compareTo(temp2)>0){
			temp=temp1.subtract(temp2);
		}
		temp = temp.divide(new BigDecimal(100));
		//return NumberFormat.getCurrencyInstance().format(value);
		return NumberUtil.formatMoneyByDouble(temp);
	}
	
	/**
     *  金额分转元，必须带2位小数，不含有千分位 ‘,’
     * @param value
     * @return
     */
    public static String formatCurrencyInstanceNoThousands(Object value){
        BigDecimal temp;
        if(value==null || "".equals(value)){
            temp = new BigDecimal(0);
        }else{
            temp=new BigDecimal(value.toString());
        }
        temp = temp.divide(new BigDecimal(100));
        //return NumberFormat.getCurrencyInstance().format(value);
        return NumberUtil.formatMoneyByDoubleNoThousands(temp);
    }
    
	/**
     *  金额分转元，不带小数，不含有千分位 ‘,’
     * @param value
     * @return
     */
    public static String formatCurrencyInstanceNoThousandsAndPoint(Object value){
        BigDecimal temp;
        if(value==null || "".equals(value)){
            temp = new BigDecimal(0);
        }else{
            temp=new BigDecimal(value.toString());
        }
        temp = temp.divide(new BigDecimal(100));
        //return NumberFormat.getCurrencyInstance().format(value);
        
        BigDecimal targetValue = null;
        if (value != null && StringUtils.isNotBlank(value.toString())) {
            targetValue = new BigDecimal(temp.toString());
        } else {
            return "";
        }
        String formatStr = "###0";
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);// 设置使用美国数字格式(千分位)
        df.applyPattern(formatStr);// 设置应用金额格式
        return df.format(targetValue);
        
//        return NumberUtils.formatMoneyByDoubleNoThousands(temp);
    }
	

	public static String formatNumberL(Long targetValue, String formatStr) {
		if (targetValue == null) {
			targetValue = Long.valueOf(0);
		}
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);// 设置使用美国数字格式(千分位)
		df.applyPattern(formatStr);// 设置应用金额格式
		return df.format(targetValue);
	}
	
	public static String formatNumber(BigDecimal targetValue, String formatStr) {
		if (targetValue == null) {
			targetValue = BigDecimal.valueOf(0);
		}
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);// 设置使用美国数字格式(千分位)
		df.applyPattern(formatStr);// 设置应用金额格式
		return df.format(targetValue);
	}

	public static String formatMoney(Object value) {
		BigDecimal targetValue = null;
		if (value != null) {
			targetValue = new BigDecimal(value.toString());
		} else {
			return "";
		}
		String formatStr = "#,###.######";
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);// 设置使用美国数字格式(千分位)
		df.applyPattern(formatStr);// 设置应用金额格式
		logger.info("金额千分位:"+df.format(targetValue));
		return df.format(targetValue);
	}

	/**
	 * 将页面的费率乘以100作为数据库存储数据
	 * 
	 * @param rate
	 * @return
	 */
	public static BigDecimal rateForDigitDB(String rate) {
		BigDecimal result = null;
		try {
			if (StringUtils.isNotEmpty(rate)) {
				BigDecimal dRate = new BigDecimal(rate);
				BigDecimal factor = new BigDecimal(100);
				result = dRate.multiply(factor);
				return result;
			}
			return result;
		} catch (Exception e) {
			logger.error("费率格式化异常", e);
			return result;
		}
	}

	/**
	 * 将页面的费率除以100
	 * 
	 * @param rate
	 * @return
	 */
	public static String rateForDigitVM(BigDecimal rate) {
		String result = "";
		try {
			if (rate != null) {
				BigDecimal factor = new BigDecimal(100);
				return formatNumber(rate.divide(factor), "####.######");
			}
			return result;
		} catch (Exception e) {
			logger.error("费率格式化异常", e);
			return result;
		}
	}

	/**
	 * 将页面的费率除以100
	 * 
	 * @param rate
	 * @return
	 */
	public static String rateForDigitVM(String rate) {
		BigDecimal b = new BigDecimal(rate);
		String result = "";
		try {
			if (rate != null) {
				BigDecimal factor = new BigDecimal(100);
				return formatNumber(b.divide(factor), "####.######");
			}
			return result;
		} catch (Exception e) {
			logger.error("费率格式化异常", e);
			return result;
		}
	}

	/**
	 * 将页面的费率乘以10000
	 * 
	 * @param rate
	 * @return
	 */
	public static Long rateForLongDB(String rate) {
		Long result = null;
		try {
			if (StringUtils.isNotEmpty(rate)) {
				Double dRate = new Double(rate);
				Double factor = new Double(10000);
				Double temp = dRate * factor;
				return temp.longValue();
			}
			return result;
		} catch (Exception e) {
			logger.error("费率格式化异常", e);
			return result;
		}
	}
	
	public static int tranferStringToInt(String str){
		int result = 0;
		try {
			if(StringUtils.isNotEmpty(str)){
				return Integer.parseInt(str);
			}
		} catch (Exception e) {
			logger.error("格式化异常", e);
			return result;
		}
		return result;
	}

	/**
	 * 将页面的费率除以100
	 * 
	 * @param rate
	 * @return
	 */
	public static String rateForLongVM(Long rate) {
		String result = "";
		try {
			if (rate != null) {
				BigDecimal dRate = new BigDecimal(rate);
				BigDecimal factor = new BigDecimal(10000);
				return formatNumber(dRate.divide(factor), "####.######");
			}
			return result;
		} catch (Exception e) {
			logger.error("费率格式化异常", e);
			return result;
		}
	}
	
	public static boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){  
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		}

	/**
	 * 将页面的费率除以10000
	 * 
	 * @param rate
	 * @return
	 */
	public static String rateForString(Long rate) {

		// return formatNumber(rate.divide(factor), "####.######");
		String result = "";
		try {
			if (rate != null) {
				BigDecimal bRate = new BigDecimal(rate);
				BigDecimal factor = new BigDecimal(10000);
				return formatNumber(bRate.divide(factor), "####.######");
			}
			return result;
		} catch (Exception e) {
			logger.error("费率格式化异常", e);
			return result;
		}
	}

	/**
	 * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式
	 */

	public static String digitUppercase(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };
		String head = n < 0 ? "负" : "";
		n = Math.abs(n);
		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
					.replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);
		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]
					+ s;
		}
		return head
				+ s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
						.replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}
	
	public static String calPercent(BigDecimal numerator , BigDecimal denominator){
		BigDecimal percent =  numerator.divide(denominator, RoundingMode.HALF_DOWN).multiply(new BigDecimal(100));
		String fmPercent = formatMoneyByDouble(percent) + "%";
		return fmPercent;
	}
	
	
	public static String formatMoneyByDoubleNoThousands(BigDecimal value) {
        BigDecimal targetValue = null;
        if (value != null) {
            targetValue = new BigDecimal(value.toString());
        } else {
            return "";
        }
        String formatStr = "###0.00";
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);// 设置使用美国数字格式(千分位)
        df.applyPattern(formatStr);// 设置应用金额格式
        return df.format(targetValue);
    }
	
	
	public static String formatMoneyByDouble(BigDecimal value) {
		BigDecimal targetValue = null;
		if (value != null) {
			targetValue = new BigDecimal(value.toString());
		} else {
			return "";
		}
		String formatStr = "#,##0.00";
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);// 设置使用美国数字格式(千分位)
		df.applyPattern(formatStr);// 设置应用金额格式
		return df.format(targetValue);
	}
	public static String formatMoneyByDoubleNoPoint(BigDecimal value) {
		BigDecimal targetValue = null;
		if (value != null) {
			targetValue = new BigDecimal(value.toString());
		} else {
			return "";
		}
		String formatStr = "#,##0";
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);// 设置使用美国数字格式(千分位)
		df.applyPattern(formatStr);// 设置应用金额格式
		return df.format(targetValue);
	}
	public boolean bgDecimalEqual(BigDecimal arg1, BigDecimal arg2) {
		if (arg1 == null && arg2 == null) return true;
		if (arg1 == null && arg2 != null) return false;
		if (arg1 != null && arg2 == null) return false;
		if (arg1 != null && arg2!= null && arg1.compareTo(arg2)==0) return true;
		return false;
	}
	public String replace(String str, int start, int end, String replacement) {
		if (StringUtils.isBlank(str)) return null;
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<str.length();i++) {
			if (i>=start&&i<=end) {
				sb.append(replacement);
			} else {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 2数相乘，最终四舍五入取精度
	 * BigDecimal.ROUND_HALF_EVEN 四舍五入
	 * @param val1 数值1
	 * @param val2数值2
	 * @param scale 小数位数精度
	 * @return
	 */
	public static String calculateNumber(String val1,String val2 ,int scale){
		BigDecimal b1=new BigDecimal(val1);
		BigDecimal b2=new BigDecimal(val2);
		BigDecimal result = b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		return result.toString();
	}
	/**
	 * 2数比较，如果val1>val2则返回1，如果val1=val2，则返回0，如果val1<val2，则返回-1
	 * @param val1 数值1
	 * @param val2 val2数值2
	 * @return
	 */
	public static int compare(String val1,String val2){
		if (val1 == null || val2 == null || val1.equals("") || val2.equals("")) return -1;
		BigDecimal b1=new BigDecimal(val1);
		BigDecimal b2=new BigDecimal(val2);
		return b1.compareTo(b2);
	}
	
	
	
	/**
	 * 2数相除
	 * BigDecimal.ROUND_HALF_EVEN 四舍五入
	 * @param val1 数值1
	 * @param val2数值2
	 * @param scale 小数位数精度
	 * @return
	 */
	public static String divideNumber(String val1,String val2 ,int scale){
		BigDecimal b1=new BigDecimal(val1);
		BigDecimal b2=new BigDecimal(val2);
		BigDecimal result = b1.divide(b2).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		return result.toString();
	}
	/**
	 * 2数相减
	 * BigDecimal.ROUND_HALF_EVEN 四舍五入
	 * @param val1 数值1
	 * @param val2数值2
	 * @param scale 小数位数精度
	 * @return
	 */
	public static String subtractNumber(String val1,String val2 ,int scale){
		BigDecimal b1=new BigDecimal(val1);
		BigDecimal b2=new BigDecimal(val2);
		BigDecimal result = b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		return result.toString();
	}
	
	
	/**
	 * 2数相加
	 * BigDecimal.ROUND_HALF_EVEN 四舍五入
	 * @param val1 数值1
	 * @param val2数值2
	 * @param scale 小数位数精度
	 * @return
	 */
	public static String addNumber(String val1,String val2 ,int scale){
		BigDecimal b1=new BigDecimal(val1);
		BigDecimal b2=new BigDecimal(val2);
		BigDecimal result = b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		return result.toString();
	}
	
	public static String transNumberToRmb(String val1){
		
		
		if (val1.length()>=9){
			String temp1 = val1.substring(0,val1.length()-8);
			String temp2 = val1.substring(val1.length()-8, val1.length()-4);
			String temp3 = val1.substring(val1.length()-4);
			if (temp2.equals("0000")){
				temp2 ="";
			}else{
				temp2 = temp2+"万";
			}
			if (temp3.equals("0000")){
				temp3="";
			}else{
				temp3 = temp3+"元";
			}
			val1 = temp1+"亿"+temp2+temp3;
		}else if (val1.length()>=5){
			String temp2 = val1.substring(0, val1.length()-4);
			String temp3 = val1.substring(val1.length()-4);
			if (temp3.equals("0000")){
				temp3="";
			}else{
				temp3 = temp3+"元";
			}
			val1 = temp2+"万"+temp3;
		}else{
			val1 = val1+"元";
		}
		
		return val1;
	}
	
	/**
	 * 解析传入参数是否为数字类型，不是则返回空
	 * @param obj
	 * @return
	 */
	public static String parseValue(Object obj) {
		if (obj == null) {
			return null;
		}
		
		if (obj instanceof String) {
			if (((String) obj).length() > 0&&!((String) obj).equals("null")&&!((String) obj).trim().equals("")) {
				return String.valueOf(obj);
			} else {
				return null;
			}
		}
		
		return String.valueOf(obj);
	}
	
	public static void main(String[] args){
		System.out.println("############################");
		System.out.println(transNumberToRmb("800200"));
		
		
		System.out.println(parseValue(""));
	}
	
}
