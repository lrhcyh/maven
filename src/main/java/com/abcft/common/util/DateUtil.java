package com.abcft.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class DateUtil {
	
	private static final Logger log = Logger.getLogger(DateUtil.class);
	private static final String TIME_PATTERN = "HH:mm";
	public DateUtil()
    {
    }
	public static String getDateToString(Date obj)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String str = df.format(obj);
        return str;
    }
	
	public static String getDateToString1(Date obj)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = df.format(obj);
        return str;
    }
	public static String getDateToString2(Date obj)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(obj);
        return str;
    }
	
	public static String getDates(Date obj)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(obj);
        return str;
    }

    public static String getDateToString(String formatPattern, Date obj)
    {
        SimpleDateFormat df = new SimpleDateFormat(formatPattern);
        String str = df.format(obj);
        return str;
    }

    public static Date getStringToDate(String str)
    {
    	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	   Date date = null;
    	   try {
    	    date = format.parse(str);
    	   } catch (ParseException e) {
    	    e.printStackTrace();
    	   }
    	   return date;
    }
    
    public static Date getStringToDate1(String str)
    {
    	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	   Date date = null;
    	   try {
    	    date = format.parse(str);
    	   } catch (ParseException e) {
    	    e.printStackTrace();
    	   }
    	   return date;
    }

    public static Date strToDate(String str, String pattern)
    {
        Date dateTemp = null;
        SimpleDateFormat formater2 = new SimpleDateFormat(pattern);
        try
        {
            dateTemp = formater2.parse(str);
        }
        catch(Exception e)
        {
            log.error("exception in convert string to date!");
        }
        return dateTemp;
    }

    public static String getDatePattern()
    {
        return "yyyy-MM-dd";
    }

    public static String getDateTimePattern()
    {
        return (new StringBuilder(String.valueOf(getDatePattern()))).append(" HH:mm:ss.S").toString();
    }

    public static String getDate(Date aDate)
    {
        String returnValue = "";
        if(aDate != null)
        {
            SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }
        return returnValue;
    }

    public static Date convertStringToDate(String aMask, String strDate)
        throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat(aMask);
        if(log.isDebugEnabled())
            log.debug((new StringBuilder("converting '")).append(strDate).append("' to date with mask '").append(aMask).append("'").toString());
        Date date;
        try
        {
            date = df.parse(strDate);
        }
        catch(ParseException pe)
        {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return date;
    }

    public static String getTimeNow(Date theTime)
    {
        return getDateTime("HH:mm", theTime);
    }

    public static Calendar getToday()
        throws ParseException
    {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }

    public static Calendar getCurrentDay()
        throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        return cal;
    }

    public static String getDateTime(String aMask, Date aDate)
    {
        SimpleDateFormat df = null;
        String returnValue = "";
        if(aDate == null)
        {
            log.error("aDate is null!");
        } else
        {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return returnValue;
    }

    public static String convertDateToString(Date aDate)
    {
        return getDateTime(getDatePattern(), aDate);
    }

    public static Date convertStringToDate(String strDate)
        throws ParseException
    {
        Date aDate = null;
        try
        {
            if(log.isDebugEnabled())
                log.debug((new StringBuilder("converting date with pattern: ")).append(getDatePattern()).toString());
            aDate = convertStringToDate(getDatePattern(), strDate);
        }
        catch(ParseException pe)
        {
            log.error((new StringBuilder("Could not convert '")).append(strDate).append("' to a date, throwing exception").toString());
            log.error("{}", pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return aDate;
    }

    public static String convertDateToString(String pattern, Date aDate)
    {
        return getDateTime(pattern, aDate);
    }

    public static Date getRelativeDate(Date startDate, int day)
    {
        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(startDate);
            calendar.add(5, day);
            return calendar.getTime();
        }
        catch(Exception e)
        {
            log.error("{}", e);
        }
        return startDate;
    }

    public static int getDay(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7) - 1;
    }

    public static int countDays(String beginStr, String endStr, String Foramt)
    {
        Date end = strToDate(endStr, Foramt);
        Date begin = strToDate(beginStr, Foramt);
        long times = end.getTime() - begin.getTime();
        return (int)(times / 60L / 60L / 1000L / 24L);
    }
    
    public static int countDays(Date begin,Date end){
    	long times = end.getTime() - begin.getTime();
        return (int)(times / 60L / 60L / 1000L / 24L);
    }
    
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate)    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate)); 
		}catch (ParseException e) {
			e.printStackTrace();
		}  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    
    /**  
     * 将秒转换为天时分秒格式字符串(如是0天0时0分就显示秒)  
     * @param seconds 秒数  
     * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">@return</a>  返回天时分秒字符串  
     */ 
    public static String getStrOfSeconds(final long seconds) {   
        if(seconds < 0){   
            return "秒数必须大于0";   
        }   
        long one_day = 60 * 60 * 24;   
        long one_hour  = 60 * 60;   
        long one_minute = 60;   
        long day,hour,minute,second = 0L;;   
           
        day = seconds / one_day;   
        hour = seconds % one_day / one_hour ;   
        minute = seconds % one_day % one_hour /  one_minute;   
        second = seconds % one_day % one_hour %  one_minute;   
           
        if(seconds < one_minute){   
            return "1分钟前";   
        }else if(seconds >= one_minute && seconds < one_hour){   
            return minute + "分钟前";   
        }else if (seconds >= one_hour && seconds < one_day){   
            return hour + "小时前";   
        }else{   
            return day + "天前";   
        }   
    }
   public static void main(String[] args) {
	   System.out.println(getStrOfSeconds(200));
   }
}
