package com.xxcb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作公共类
 * @author zhangpeng
 *
 */
public class DateUtil {

	// 日期格式，年份，例如：2004，2008
	public static final String DATE_FORMAT_YYYY = "yyyy";
	
	// 日期格式，年份，例如：03，04
	public static final String DATE_FORMAT_MM = "MM";
	
	// 日期格式，年份，例如：22，21
	public static final String DATE_FORMAT_DD = "dd";
	
	// 日期格式，年份和月份，例如：200707，200808
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

	// 日期格式，年月日，例如：20050630，20080808
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	// 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	// 日期格式，年月日时分秒，例如：20001230120000，20080808200808
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSS = "yyyyMMddHHmmssS";
	// 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开，
	// 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

	// 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开，
	// 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
	public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI_SS = "yyyyMMdd HH:mm:ss";

	public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";

	public static final String DATE_TIME_HH_MI = "HH:mm";
	

    
	/**
	 * 字符串转换为日期
	 * 
	 * @author limin
	 * @param String
	 *            strDate：日期的字符串形式
	 * @param String
	 *            format：转换格式
	 * @return String
	 * @throws
	 */
	public static Date strToDate(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}
		return date;
	}

	/**
	 * 字符串转换为日期时间
	 * 
	 * @author limin
	 * @param String
	 *            strDateTime：日期时间的字符串形式
	 * @param String
	 *            format：转换格式
	 * @return String
	 * @throws
	 */
	public static Date strToDateTime(String strDateTime, String fromat) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat(fromat);
		Date dateTime = null;
		try {
			dateTime = dateTimeFormat.parse(strDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}
		return dateTime;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @author limin
	 * @param Date
	 *            date：需要转换的日期
	 * @param String
	 *            format：转换格式
	 * @return String
	 * @throws
	 */
	public static String dateToStr(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 日期时间转换为字符串
	 * 
	 * @author limin
	 * @param Date
	 *            date：需要转换的日期
	 * @param String
	 *            format：转换格式
	 * @return String
	 * @throws
	 */
	public static String dateTimeToStr(Date date, String format) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat(format);
		return dateTimeFormat.format(date);
	}

	/**
	 * 得到当天的最后时间,today是字符串类型"yyyy-mm-dd", 返回是日期类型"yyyy-mm-dd 23:59:59"
	 * 
	 * @author limin
	 * @param String
	 *            today
	 * @return Date
	 * @throws
	 */
	public static Date getTodayLastTime(String today) {
		String todayLastTime = today + " 23:59:59";
		return strToDateTime(todayLastTime,
				DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
	}

	/**
	 * 得到当前时间 返回是日期类型"yyyy-mm-dd 23:59:59"
	 * 
	 * @author wangsw
	 */
	public static String getNowTime() {
		return dateToStr(new Date(),DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
	}

	/**
	 * 得到当前日期
	 * 
	 * @author wangsw
	 */
	public static Date getTodayDate() {
		return new Date();
	}

	/**
	 * 得到当前日期 返回是日期类型"yyyy-mm-dd"
	 * 
	 * @author wangsw
	 */
	public static String getTodayStr() {
		return dateToStr(new Date(),DateUtil.DATE_FORMAT_YYYY_MM_DD);
	}

	
	public static String sqlBetweenDate(String column,String starttime,String endtime,String type){
		if (type.equals("d")){
			return column +" >=to_date('"+starttime+" 00:00:00','yyyy-mm-dd hh24:mi:ss') and " +
				" "+column+" <=to_date('"+endtime+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
		}else{
			return column +" >=to_date('"+starttime+"','yyyy-mm') and " +
			" "+column+" <=to_date('"+endtime+"','yyyy-mm') ";
		}
	}

	public static String sqlTocharDate(String column,String result,String type){
		if (type.equals("d")){
			if (result!=null && !result.equals("")) {
				return "TO_CHAR("+column+",'YYYY-MM-DD') as "+result;
			}else{
				return "TO_CHAR("+column+",'YYYY-MM-DD')";
			}
		}else{
			if (result!=null && !result.equals("")) {
				return "TO_CHAR("+column+",'YYYY-MM') as "+result;
			}else{
				return "TO_CHAR("+column+",'YYYY-MM')";
			}
		}
	}
	//闰年
	public static boolean isLeapYear(int year)
	{

	return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ;
	}

	public static void main(String [] args)
	{
		System.out.println(getNowTime().replace("-", "").replace(" ", "").replace(":", ""));
		System.out.println(String.valueOf(Long.valueOf(getNowTime().replace("-", "").replace(" ", "").replace(":", ""))+(long)320));
	}
}
