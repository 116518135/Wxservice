package com.wxservice.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtil {
	public final static String detailFmt = "yyyy/MM/dd HH:mm:ss";

	public final static String simpleFmt = "yyyy/MM/dd";

	/**
	 * 指定月的当前时间
	 * 
	 * @param month
	 * @return
	 */
	public static Date getTimeByMonth(Integer month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, month - 1);
		return cal.getTime();
	}

	/**
	 * 一个月的开始第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 一个月的结束第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal
				.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1, 0,
						0, 0);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);

		return cal.getTime();
	}

	/**
	 * 格式化指定时间
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null)
			return "";
		try {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		} catch (Exception e) {
		}
		return "";

	}

	/**
	 * 以yyyy/MM/dd方式格式化指定时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatSimplyDate(Date date) {
		return format(date, simpleFmt);
	}

	/**
	 * 以yyyy/MM/dd方式格式化指定时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDetailDate(Date date) {
		return format(date, detailFmt);
	}

	/**
	 * 解释日期
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static Date parse(String str, String pattern) {
		Date myDate = null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			myDate = df.parse(str);
		} catch (Exception e) {
			SystemLogger.error("格式化时间错误", e);
		}
		return myDate;
	}
	

	/**
	 * 解析日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date parse(String str) {
		return parse(str, simpleFmt);
	}

	/**
	 * 得到一天的开始时间，返回String
	 * 
	 * @return
	 */
	public static String formatCurrentDayStart() {
		SimpleDateFormat df = new SimpleDateFormat(simpleFmt);
		return df.format(new Date());
	}

	/**
	 * 得到一天的结束时间，返回String
	 * 
	 * @return
	 */
	public static String formatCurrentDayEnd() {
		StringBuffer str = new StringBuffer();
		str.append(formatCurrentDayStart());
		str.append(" 23:59:59");
		return str.toString();
	}
	
	public static String formatDateDayEnd(Date date) {
		StringBuffer str = new StringBuffer();
		str.append(formatSimplyDate(date));
		str.append(" 23:59:59");
		return str.toString();
	}
	public static String formatDateDayEnd(String date) {
		StringBuffer str = new StringBuffer();
		str.append(date);
		str.append(" 23:59:59");
		return str.toString();
	}

	/**
	 * 得到当天开始日期 ，不带小时分秒
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Date getCurrentDayStart() {
		return parse(formatCurrentDayStart());
	}
	

	public static Date getToday() {
		return parse(formatCurrentDayStart());
	}
	
	/**
	 * 得到当天结束日期 ，不带小时分秒
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Date getCurrentDayend() {
		return parse(formatCurrentDayEnd(), detailFmt);
	}

	/**
	 * 得到现在的时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 判断日期是否是当天时间
	 * 
	 * @param date
	 * @return
	 */
	public static boolean judgeDateIsCurrentDay(Date date) {

		try {
			long time = date.getTime();
			long Daystart = getCurrentDayStart().getTime();
			long Dayend = getCurrentDayend().getTime();
			return Daystart <= time && time <= Dayend;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 比较日期字符串返回天数
	 * 
	 * @param strBegin
	 *            格式：yyyy-MM-dd
	 * @param strEnd
	 *            格式：yyyy-MM-dd
	 * @return
	 * @throws Exception
	 */
	public static int getDifferDays(String strBegin, String strEnd)
			throws Exception {

		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();

		calst.setTime(parse(strBegin.concat(" 00:00:00"), detailFmt));
		caled.setTime(parse(strEnd.concat(" 00:00:00"), detailFmt));

		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;

		return days;

	}

	/**
	 * 比较日期返回天数
	 * 
	 * @param strBegin
	 * @param strEnd
	 * @return
	 */
	public static final int getDifferDays(Date strBegin, Date strEnd) {
		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();
		calst.setTime(strBegin);
		caled.setTime(strEnd);
		// 设置时间为0时
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}
	
    /**  
     * 在指定的日期增加天数  
     * @param initDate  
     * @param diff  
     * @return  
     */  
    public static Date addDay2Date(Date initDate , int diff){   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(initDate);   
        cal.add(Calendar.DATE, diff);      
        return new Date(cal.getTimeInMillis());   
    }   

    
}
