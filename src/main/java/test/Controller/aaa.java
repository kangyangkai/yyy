
package test.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;


public class aaa {
	public static void main(String[] args) {
		System.err.println(isFirstYear("20111231"));
	}
		/**
		 * 当前操作系统日期 Calendar
		 */
		private static Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
		/**
		 * 日期格式 默认：yyyyMMdd
		 */
		private static String pattern = "yyyyMMdd";
		/**
		 * 时间格式 默认：HH:mm:ss
		 */
		private static String timePattern = "HH:mm:ss";
		
		/**
		 * 年
		 */
		private static int year = 0;
		/**
		 * 月
		 */
		private static int month = 0;
		/**
		 * 日
		 */
		private static int day = 0;
		/**
		 * 时
		 */
		private static int hour = 0;
		/**
		 * 分
		 */
		private static int minute = 0;
		/**
		 * 秒
		 */
		private static int second = 0;
		
		/**
		 * 静态初始化（默认系统当前日期和时间）
		 */
		static {
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			day = calendar.get(Calendar.DAY_OF_MONTH);
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
		}
		/**
		 * 
		 * 功能描述：
		 * @param millis
		 * @return
		 * @ tianming 
		 * @ 2015-8-5
		 */
		public static String getDateStr(long millis) {
		 Date date = new Date(millis);
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		 return format.format(date);
		} 
	
		//获得系统当前时间
		public static String getSysTime() {
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		    String fmt_date = sdf.format(date);	
		    date = null;
		    sdf = null;
		    return  fmt_date;
		}
		//获得系统当前时间
		public static String getFormatSysTime() {
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    String fmt_date = sdf.format(date);	
		    date = null;
		    sdf = null;
		    return  fmt_date;	     
		}
		
		/**
		 * 功能描述：自定义系统时间。（谨慎使用）
		 * 
		 * @param strdate
		 *            自定义日期字符串，格式：yyyymmdd
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static void setSysDate(String strdate) {
			if (isDateStr(strdate)) {
				calendar = parseCalendar(strdate);

				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH) + 1;
				day = calendar.get(Calendar.DAY_OF_MONTH);
				hour = calendar.get(Calendar.HOUR_OF_DAY);
				minute = calendar.get(Calendar.MINUTE);
				second = calendar.get(Calendar.SECOND);
			}
		}

		/**
		 * 功能描述： 初始化系统日期(当前系统日期)调用setSysDate()后会用到次方法重新初始化系统日期时间
		 * 			为当前日期时间
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static void initSys() {
			calendar = new GregorianCalendar(TimeZone.getDefault());

			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			day = calendar.get(Calendar.DAY_OF_MONTH);

			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
		}

		/**
		 * 功能描述：获取系统当前日期---年
		 * 
		 * @return int 年
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static int getYear() {
			return year;
		}

		/**
		 * 功能描述：获取系统当前日期---年
		 * 
		 * @return String 年
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static String getStrYear() {
			return String.valueOf(year);
		}

		/**
		 * 功能描述：获取系统当前日期---月
		 * 
		 * @return int 月
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static int getMonth() {
			return month;
		}

		/**
		 * 功能描述：获取系统当前日期---月
		 * 
		 * @return String 月
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static String getStrMonth() {
			return month >= 10 ? String.valueOf(month) : "0"
					+ String.valueOf(month);
		}

		/**
		 * 功能描述：获取系统当前日期---日
		 * 
		 * @return int 日
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static int getDay() {
			return day;
		}

		/**
		 * 功能描述：获取系统当前日期---日
		 * 
		 * @return String 日
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static String getStrDay() {
			return day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
		}
		/**
		 * 
		 * 功能描述：获取系统时间--小时
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @修改日志：1.0
		 */
		public static int getHour(){
			return hour;
		}
		/**
		 * 
		 * 功能描述：获取系统时间--分钟
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @修改日志：
		 */
		public static int getMinute(){
			return minute;
		}
		/**
		 * 
		 * 功能描述：获取系统时间--秒
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @修改日志：
		 */
		public static int getSecond(){
			return second;
		}

		/**
		 * 功能描述：获取系统当前日期---年月日 （格式：yyyymmdd）
		 * 
		 * @return String 年月日
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static String getDate() {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * 功能描述：根据预定格式取系统当前日期---年月日
		 * 
		 * @param ptn
		 *            日期格式
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static String getDate(String ptn) {
			SimpleDateFormat format = new SimpleDateFormat(ptn);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * 功能描述：获取系统时间 格式：yyyymmdd hh:mm:ss
		 * 
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static String getDateTime() {
			SimpleDateFormat format = new SimpleDateFormat(pattern + " "
					+ timePattern);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * 
		 * 功能描述：获取预定义格式的系统时间
		 * 
		 * @param datePtn
		 *            日期格式
		 * @param timePtn
		 *            时间格式
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @修改日志：1.0
		 */
		public static String getDateTime(String datePtn, String timePtn) {
			SimpleDateFormat format = new SimpleDateFormat(datePtn + " " + timePtn);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * 功能描述：判断给定日期（格式yyyymmdd）是否在系统日期之前，是（或等于）：true，否：false
		 * 
		 * @param strdate
		 *            给定日期
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static boolean isBefore(String strdate) {
			Calendar cal = parseCalendar(strdate);
			return cal.before(calendar);
		}
		/**
		 * 
		 * 功能描述：判断给定的两个日期的前后。strdate1在strdate2之前（或同一天），返回true，反之，false
		 * @param strdate1	日期1
		 * @param strdate2	日期2
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-25
		 * @修改日志：1.0
		 */
		public static boolean isBefore(String strdate1, String strdate2){
			Calendar cal1 = parseCalendar(strdate1);
			Calendar cal2 = parseCalendar(strdate2);
			return cal1.before(cal2);
		}
		/**
		 * 
		 * 功能描述：计算在当前系统日期增加或减少 n 天后的日期
		 * 
		 * @param days
		 *            增加或减少的天数，正数增加，反之减少
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @修改日志：
		 */
		public static String addByDay(int days) {
			calendar.add(Calendar.DATE, days);
			return getDate();
		}

		/**
		 * 
		 * 功能描述：计算在给定的日期加上或减去 n 天后的日期
		 * 
		 * @param datestr
		 *            给定的日期
		 * @param days
		 *            正数增加，反之减少
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @修改日志：
		 */
		public static String addByDay(String datestr, int days) {
			Calendar cal = parseCalendar(datestr);
			cal.add(Calendar.DATE, days);
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = cal.getTime();
			return format.format(date);
		}

		/**
		 * 
		 * 功能描述：获得给定日期与系统当前日期之间的天数
		 * 
		 * @param strdate
		 *            给定的日期字符串
		 * @return long 天数
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static long getDays(String strdate) {
			Calendar cal = parseCalendar(strdate);
			Calendar cal1 = parseCalendar(getDate());
			long millis = Math.abs(cal.getTimeInMillis() - cal1.getTimeInMillis());
			return millis / (24 * 60 * 60 * 1000);
		}

		/**
		 * 
		 * 功能描述：获得给定的两个日期之间相差的天数（日期不分前后）
		 * 
		 * @param fromdate
		 *            日期字符串 格式：yyyymmdd
		 * @param todate
		 *            日期字符串 格式：yyyymmdd
		 * @return long
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static long getDaysBetween(String fromdate, String todate) {
			Calendar from = parseCalendar(fromdate);
			Calendar to = parseCalendar(todate);
			long millis = Math.abs(from.getTimeInMillis() - to.getTimeInMillis());
			return millis / (24 * 60 * 60 * 1000);
		}

		/**
		 * 
		 * 功能描述：获得给定日期与系统当前日期之间的月数，不记天数
		 * 
		 * @param strdate
		 *            给定的日期字符串
		 * @return long 月数
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：待定
		 */
		private static long getMonths(String strdate) {
			long months = getMonth() - Integer.parseInt(strdate.substring(4, 6));
			long years = getYear() - Integer.parseInt(strdate.substring(0, 4));
			if (!isBefore(strdate)) {
				months = -months;
				years = -years;
			}
			if (months >= 0) {
				return years * 12 + months;
			} else {
				return (years - 1) * 12 + months + 12;
			}
		}
		/**
		 * 
		 * 功能描述：获得两个日期之间的月差数，不记天数
		 * @param strdate1
		 * @param strdate2
		 * @return long
		 * @author wangshanfang
		 * @date 2008-11-25
		 * @修改日志：待定
		 */
		public static long getMonths(String strdate1, String strdate2){
			long m = 0;
			setSysDate(strdate1);
			m = getMonths(strdate2);
			initSys();
			return m;
		}
		/**
		 * 
		 * 功能描述：获得给定日期与系统当前日期之间的月数和天数
		 * 
		 * @param strdate
		 *            给定的日期字符串
		 * @return long[] 下标0月数，1天数
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：待定
		 */
		public static long[] getMonthsAndDays(String strdate) {
			long m = getMonths(strdate);
			int d = getDay() - Integer.parseInt(strdate.substring(6, 8));
			String date = "";
			if(!isBefore(strdate)){
				d = -d;
				date = strdate;
			}else{
				date = getDate();
			}
			while(d<0){
				m--;
				d+=getDaysOfMonth(date);
			}
			long[] md = { m, d };
			return md;
		}
		/**
		 * 
		 * 功能描述：获得给定两个日期之间的月数和天数
		 * @param strdate1
		 * @param strdate2
		 * @return long[] 下标0月数，1天数
		 * @author wangshanfang
		 * @date 2008-11-25
		 * @修改日志：
		 */
		public static long[] getMonthsAndDays(String strdate1, String strdate2){
			long m = getMonths(strdate1,strdate2);
			int d = Integer.parseInt(strdate1.substring(6, 8))-Integer.parseInt(strdate2.substring(6, 8));
			String date = "";
			if(!isBefore(strdate1,strdate2)){			
				date = strdate1;
			}else{
				d = -d;
				date = strdate2;
			}
			while(d<0){
				m--;
				d+=getDaysOfMonth(date);
			}
			long[] md = { m, d };
			return md;
		}
		/**
		 * 功能描述：判断字符串是否可以转换为日期型 是：true，否：false
		 * 
		 * @param strdate
		 *            预转换字符串
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static boolean isDateStr(String strdate) {
			if (strdate.length() != 8) {
				return false;
			}

			String reg = "^(\\d{4})((0([1-9]{1}))|(1[012]))((0[1-9]{1})|([1-2]([0-9]{1}))|(3[0|1]))$";

			if (Pattern.matches(reg, strdate)) {
				return getDaysOfMonth(strdate) >= Integer.parseInt(strdate
						.substring(6, 8));
			} else {
				return false;
			}
		}
		/**
		 * 
		 * 功能描述：功能描述：判断字符串是否可以转换为日期型 是：true，否：false
		 * @param i_date
		 * @return
		 * @author zhangjiqing
		 * @date 2011-5-13 上午11:03:31
		 */
		public static boolean isDateStr(int i_date) {
			
			Calendar calendar = parseCalendar(String.valueOf(i_date));
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}else return true;
		}
		/**
		 * 
		 * 功能描述：返回指定格式的日期 
		 * @param s_date 需要格式化的字符串，如20110330
		 * @param s_fmt 返回的格式 如 yyyy-mm-dd;yyyy/mm/dd;mm/dd/yyyy等
		 * @return
		 * @author zhaizhihui
		 * @date 2011-5-13 上午11:12:08
		 * 释放内存，未测试---李世杰
		 */
		public static String getFormatDate(String s_date, String s_fmt)  {
			DateFormat getDateFormat = new SimpleDateFormat("yyyymmdd");//输入日期的格式
			DateFormat putDateFormat = null; //要得到的日期的格式
			String formatDate = null; //格式化后的日期
			try {
				Date d = getDateFormat.parse(s_date);
				putDateFormat = new SimpleDateFormat(s_fmt);
				formatDate = putDateFormat.format(d);
			} catch (ParseException e) {
				System.out.println("输入的不是正确的日期");
				e.printStackTrace();
			}finally{
				getDateFormat = null;
				putDateFormat = null;
			}
			return formatDate;
		}

		
		/**
		 * 功能描述：判断是否是闰年（年限1000--9999）是：true，否：false
		 * 
		 * @param strdate
		 *            预判断年 格式yyyymmdd 或 yyyy
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static boolean isLeapYear(String strdate) {
			int y = Integer.parseInt(strdate.substring(0, 4));
			if (y <= 999) {
				return false;
			}
			if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 功能描述：获取某一月份的天数
		 * 
		 * @param strdate
		 *            日期 格式：yyyymmdd 或 yyyymm
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static int getDaysOfMonth(String strdate) {
			int m = Integer.parseInt(strdate.substring(4, 6));
			switch (m) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if (isLeapYear(strdate)) {
					return 29;
				} else {
					return 28;
				}
			default:
				return 0;
			}
		}

		/**
		 * 功能描述：把字符串转换为Calendar
		 * 
		 * @param strdate
		 *            预转换的字符串
		 * @return Calendar
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 */
		public static Calendar parseCalendar(String strdate) {
			if (isDateStr(strdate)) {
				int year = Integer.parseInt(strdate.substring(0, 4));
				int month = Integer.parseInt(strdate.substring(4, 6)) - 1;
				int day = Integer.parseInt(strdate.substring(6, 8));
				return new GregorianCalendar(year, month, day);
			} else {
				return null;
			}
		}

		/**
		 * 功能描述：将字符串转换为Date型日期 日期格式yyyymmdd
		 * 
		 * @param strdate
		 *            预转换的字符串
		 * @return Date
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @修改日志：1.0
		 * 释放内存，未测试---李世杰
		 */
		public static Date parseDate(String strdate) {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date d = null;
			try {
				d = format.parse(strdate);
			} catch (Exception pe) {
				pe.printStackTrace();
			}
			format = null;
			return d;
		}
		/**
		 * 功能描述：取得服务器日期的年
		 * @return
		 * @ tianming 
		 * @ 2015-8-5
		 */
		public static String getCurrentYear() {
		    Calendar c = Calendar.getInstance();
		    int year = c.get(Calendar.YEAR);
		    return String.valueOf(year);
		}
		/**
		 * 功能描述：取得服务器日期的月
		 * @return
		 * @ tianming 
		 * @ 2015-8-5
		 */
		public static String getCurrentMonth() {
		    Calendar c = Calendar.getInstance();
		    int year = c.get(Calendar.MONTH) + 1;
		    return String.valueOf(year);
		}
		/**
		 * 功能描述：取得服务器日期的年
		 * @return 日期
		 * @ tianming 
		 * @ 2015-8-5
		 */
		public static String getCurrentDay() {
			Calendar c = Calendar.getInstance();
			int day = c.get(Calendar.DATE);
			return String.valueOf(day);
		}
				    
	    /**
		 * 功能描述：获得给定两个日期之间的会计天数
		 * @param strdate1：起始日期；strdate2:终止日期
		 * @return long
		 * @author fangchengbing
		 * @date 2010-07-10
		 * @修改日志：加一个字符格式错误异常
		 */
		public static long getAccoutingDays(String strdate1,String strdate2)throws StringIndexOutOfBoundsException {
			long m = getMonths(strdate1, strdate2);
			int d = Integer.parseInt(strdate1.substring(6, 8))
					- Integer.parseInt(strdate2.substring(6, 8));
			if (!isBefore(strdate1, strdate2)) {
			} else {
				d = -d;
			}
			long md = m * 30 + d;
			return md;
		}

		/**
		 * 
		 * 功能描述：获得给定两个日期之间月数
		 * 
		 * @param strdate1
		 * @param strdate2
		 * @return long 
		 * @author fangchengbing
		 * @date 2010-07-10
		 * @修改日志：
		 */
		public static long KaiMonths (String strdate1, String strdate2)throws StringIndexOutOfBoundsException {
			if (isBefore(strdate2, strdate1)) {
				String temp=strdate1;
				strdate1=strdate2;
				strdate2=temp;
			}
			
			long m = getMonths(strdate1, strdate2);
			System.out.println("m"+"="+m);
			int d = Integer.parseInt(strdate2.substring(6, 8))-Integer.parseInt(strdate1.substring(6, 8));
			System.out.println("d"+"="+d);
			 if (d > 0)
				return m+1;
			else
				return m;
				
		}

		/**
		 * 
		 * 功能描述：获得给定两个日期之间的年数（以开年的实际天数计算当月天数，其他月份依次按所在月的实际天数计算，不足一个月 的按一个月算）
		 * 
		 * @param strdate1
		 * @param strdate2
		 * @return long[] 下标0月数，1天数
		 * @author wangshanfang
		 * @date 2010-07-10
		 * @修改日志：
		 */
		public static long kaiYears(String strdate1, String strdate2) throws StringIndexOutOfBoundsException{
			long m = KaiMonths(strdate1, strdate2);
			m = m / 12;
			long m1 = m % 12;
			if (m1 > 0)
				m1 = 1;
			else
				m1 = 0;
			return m + m1;
		}
		/**
		 * 功能描述：就算两个日期之间的月数（以日对日的方式计算，不足一个月的按一个月算）
		 * 函数思路：在起始日期的月份加一个月，如果大于该月份应有的天数，则把应有应有的天数赋给起始日期，月份加一；
		 * 比较起始日期与终止日期，如果小于终止日期继续计算，直到大于等于终止日期为止
		 * @param strdate1：起始日期；strdate2：终止日期；           
		 * @return int
		 * @author fangchengbing
		 * @date 2010-7-10
		 * @修改日志：
		 */
	    public static long dayToDayMonths(String strdate1,String strdate2)throws StringIndexOutOfBoundsException{
		 if(isBefore(strdate2,strdate1))
		 {
			String temp=strdate1;
			strdate2=strdate1;
			strdate2=temp;
		 }
		 long s=0;
		 int month1=0;
		 String date1=strdate1;
		 String date2=strdate2;
		 String ye=strdate1.substring(0, 4);
		 int year1=Integer.parseInt(strdate1.substring(0, 4));
		 do{
			 month1=Integer.parseInt(date1.substring(4, 6));
			 ye=date1.substring(0,4);
			 month1++;	 
			 if(month1==13){
				 month1=1;
				 year1++;
				  ye=Integer.toString(year1);
			 }
			 String mon1=Integer.toString(month1);
			 if(mon1.length()<2)
					mon1="0"+mon1;
			 date1=ye+mon1+date1.substring(6, 8);
			 int dayOfMonth1=getDaysOfMonth(date1);
			 int day=Integer.parseInt(date1.substring(6, 8));
			 if(day>dayOfMonth1)
				 day=dayOfMonth1;			 			 
			 String dom=Integer.toString(day);
			 if(dom.length()<2)
					dom="0"+dom;
			 date1=date1.substring(0, 6)+dom;
			 s++;
		   }while(isBefore(date1,date2));
	      return s;
	    	
	    }
	    /**
		 * 功能描述：计算两个日期之间的季数（以日对日的方式计算，不足一个季的按一个季算）
		 * @param strdate1：起始日期；strdate2：终止日期；           
		 * @return int
		 * @author fangchengbing
		 * @date 2010-7-10
		 * @修改日志：
		 */
	    public static long dayToDaySeasons(String strdate1,String strdate2)throws StringIndexOutOfBoundsException{
	    	long m=dayToDayMonths(strdate1,strdate2);
	    	m = m / 3;
			long m1 = m % 3;
			if (m1 > 0)
				m1 = 1;
			else
				m1 = 0;
	    	return m+m1;
	    }
	    /**
		 * 功能描述：就算两个日期之间的年数（以日对日的方式计算，不足一个年的按一个年算）
		 * @param strdate1：起始日期；strdate2：终止日期；           
		 * @return int
		 * @author fangchengbing
		 * @date 2010-7-10
		 * @修改日志：
		 */
	    public static long dayToDayYears(String strdate1,String strdate2)throws StringIndexOutOfBoundsException{
	    	long m=dayToDayMonths(strdate1,strdate2);
	    	m = m / 12;
			long m1 = m % 12;
			if (m1 > 0)
				m1 = 1;
			else
				m1 = 0;
	    	return m+m1;
	    }
		/**
		 * 
		 * 功能描述：获得给定两个日期之间的季数（以开季的实际天数计算当月天数，其他月份依次按所在月的实际天数计算，不足一个月 的按一个月算）
		 * 
		 * @param strdate1
		 * @param strdate2
		 * @return long
		 * @author fangchengbing
		 * @date 2010-07-10
		 * @修改日志：
		 */
	    public static long KaiSeasons(String strdate1, String strdate2)throws StringIndexOutOfBoundsException {
			long m = KaiMonths(strdate1, strdate2);
			m = m / 3;
			System.out.println(m);
			long m1 = m % 3;
			if (m1 > 0)
				m1 = 1;
			else
				m1 = 0;
	      return m+m1;
		}
		
		/**
		 * 
		 * 功能描述：判断是否是周末（周日）
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isSunday(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：根据年判断是否是闰年
		 * @param year 如2010
		 * @return
		 * @author lifeng
		 * @date 2010-7-16
		 * @修改日志：
		 */
		public static boolean isLeapYear(int year){
			if (year <= 999 ) {
				return false;
			} 
			if ((year%400 == 0) || (year%4 == 0 && year%100 !=0)) {
				return true;
			} else {
				return false;
			}
		}
		
		
		/**
		 * 
		 * 功能描述：根据年月计算当月天数
		 * @param year 如2010
		 * @param month 如7
		 * @return
		 * @author lifeng
		 * @date 2010-7-16
		 * @修改日志：
		 */
		public static int getDaysOfMonth(int year,int month) {
			switch(month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if(isLeapYear(year)) {
					return 29;
				} else {
					return 28;
				}
			default:
				return 0;
		}
		}
		
		/**
		 * 
		 * 功能描述：判断日期是否是旬末 （每月10号、20号或者每月最后一天）
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isLateLast(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int day = calendar.get(Calendar.DATE);
//						int date1 = Integer.parseInt(str.substring(6,8));
			int dayOfMonth = getDaysOfMonth(date);
			if (day == 10 || day == 20 || day == dayOfMonth) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * 判断日期是否是旬初
		 * @param date
		 * @return
		 */
		public static boolean isFirstLast(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int day = calendar.get(Calendar.DATE);
			if (day == 1 || day == 11 || day == 21) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：判断日期是否是月末
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isLastDayOfMonth(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int day = calendar.get(Calendar.DATE);
			int dayOfMonth = getDaysOfMonth(date);
			if (day == dayOfMonth) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：判断日期是否是月初
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isFirstDayOfMonth(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int day = calendar.get(Calendar.DATE);
			if (day == 1) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：判断是否是季末
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isQuarter(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			switch (month) {
			case 3:
				if (day == 31) {
					return true;
				} else {
					return false;
				}
			case 6:
				if (day == 30) {
					return true;
				} else {
					return false;
				}
			case 9:
				if (day == 30) {
					return true;
				} else {
					return false;
				}
			case 12:
				if (day == 31) {
					return true;
				} else {
					return false;
				}
			default:
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：判断是否是季初
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isFirstQuarter(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			switch (month) {
			case 1:
				if (day == 1) {
					return true;
				} else {
					return false;
				}
			case 4:
				if (day == 1) {
					return true;
				} else {
					return false;
				}
			case 7:
				if (day == 1) {
					return true;
				} else {
					return false;
				}
			case 10:
				if (day == 1) {
					return true;
				} else {
					return false;
				}
			default:
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：判断是否是年末（12月31号）
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isEndYear(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if(month==12 && day==31){
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：判断是否是年初（12月31号）
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static boolean isFirstYear(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("日期格式不对");
				return false;
			}
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if(month==1 && day==1){
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * 
		 * 功能描述：计算指定日期的周的最后一天
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static String getEndDayOfWeek(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//表示日期格式不对
				return "00000000";
			}
			String answer = null;
			if(isSunday(date)) {
				answer = date;
			} else {
				//day:本周第几天
				int day = calendar.get(Calendar.DAY_OF_WEEK);
				//flag:本月的天数 + 到周末的天数差
				int flag = calendar.get(Calendar.DAY_OF_MONTH) + (8-day);
//							System.out.println("flag=" + flag);
				//sunDay:本月总天数
				int sumDay = getDaysOfMonth(date);
//							System.out.println("sumDay=" + sumDay);
				//本月
				int month = calendar.get(Calendar.MONTH) + 1;
				//本年
				int year = calendar.get(Calendar.YEAR);
				if(flag > sumDay) {
					day = flag - sumDay;
					month = month + 1;
					if(month > 12) {
						year += 1;
						month = 1;
						answer = intToString(year,month,day) ;
					} else {
						answer = intToString(year,month,day) ;
					}
				} else {
					answer = intToString(year,month,flag) ;
				}
			}
			return answer;
		}
		/**
		 * 
		 * 功能描述：计算指定日期的旬的最后一天
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static String getDateOfMonth(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//表示日期格式不对
				return "00000000";
			}
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int sumDay = getDaysOfMonth(date);
			if (day <= 10) {
				return intToString(year, month, 10);
			} else if (day <= 20) {
				return intToString(year, month, 20);
			} else {
				return intToString(year, month, sumDay);
			}
		}
		
		/**
		 * 
		 * 功能描述：计算指定日期的月的最后一天
		 * @param date 字符串日期 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static String getLatDateOfMonth(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//表示日期格式不对
				return "00000000";
			}
			return intToString(calendar.get(Calendar.YEAR),(calendar.get(Calendar.MONTH) + 1),
					getDaysOfMonth(date));
					
		}
		
		/**
		 * 
		 * 功能描述：计算指定日期的季的最后一天
		 * @param date 指定日期的字符串表示 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static String getLastDateOfSeason(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//表示日期格式不正确
				return "00000000";
			}
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			if(month <= 3) {
				return intToString(year, 3, 31);
			} else if(month <= 6) {
				return intToString(year, 6, 30);
			} else if (month <= 9) {
				return intToString(year, 9, 30);
			} else {
				return intToString(year, 12, 31);
			}
		}
		
		/**
		 * 
		 * 功能描述：计算指定日期的年的最后一天
		 * @param date 指定日期的字符串表示 "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static String getLastDateOfYear(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//表示日期格式不正确
				return "00000000";
			}
			return intToString(calendar.get(Calendar.YEAR),12,31);
		}
		
		/**
		 * 
		 * 功能描述：将数字形式的年月日转换成字符串形式 如：20100714
		 * @param year 数字年
		 * @param month 数字月
		 * @param day 数字日
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @修改日志：
		 */
		public static String intToString(int year,int month,int day) {
			String month1 = null;
			String day1 = null;
			if(month <= 9) {
				month1 = "0" + month;
			} else {
				month1 = String.valueOf(month);
			}
			if(day <=9) {
				day1 = "0" + day;
			} else {
				day1 = String.valueOf(day);
			}
			return String.valueOf(year) + month1 + day1;
		}
	    /**
		 * 
		 * 功能描述：	判断一个日期是一旬中的第几天
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	天数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getDayOfTenDays(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			
				int date = cal.get(Calendar.DAY_OF_MONTH);
				
				if(date > 20){
					date = date-20;
				}else if(date>10 && date <= 20){
					date = date-10;
				}
				return date;
			}
			

		/**
		 * 
		 * 功能描述：判断一个日期是一个月中的第几天
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	天数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getDayOfMonth(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			return dayOfMonth;
		}
		/**
		 * 
		 * 功能描述：判断是一季的第几天
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	天数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getDayOfSeason(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int year = cal.get(Calendar.YEAR);
			String[] beginDayOfSeason = {year+"0101",year+"0401",year+"0701",year+"1001"};
			int seasonOfYear = getSeasonOfYear(formatDate);
			int dayOfSeason = 0;
			dayOfSeason = (int) (getDaysBetween(formatDate, beginDayOfSeason[seasonOfYear - 1]) + 1);
			
			return dayOfSeason;	
		}
		/**
		 * 
		 * 功能描述：判断是一年的第几天
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	天数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getDayOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			return dayOfYear;
		}
		/**
		 * 
		 * 功能描述：判断是一个月中的第几周
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	周数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getWeekOfMonth(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int year = cal.get(Calendar.YEAR);//得到当前年份
			int month = cal.get(Calendar.MONTH) + 1;//得到当前月数
			int dayOfMonth = getDayOfMonth(formatDate);//当前日期是这个月的第几天
			String monthStr = month >= 10 ? String.valueOf(month) : "0"+String.valueOf(month);
			String beginingOfMonth = year+""+monthStr+"01";//月初
			int dayOfWeek = getDayOfWeek(beginingOfMonth);//得到当前月1号为星期几
			
			int week = dayOfMonth / 7;
			int rest_day = dayOfMonth % 7;
			
			if((rest_day + dayOfWeek - 1) > 7){
				week =week + 2; 
			}else if((rest_day + dayOfWeek - 1) <= 7 && (rest_day + dayOfWeek - 1) != 0){
				  week = week + 1;                                                                 
			}
			
			return week;
		}
		/**
		 * 
		 * 功能描述：判断是一个季度中的第几周
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	周数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getWeekOfSeason(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int year = cal.get(Calendar.YEAR);//得到当前年份
			int dayOfSeason = getDayOfSeason(formatDate);//当前日期是这个季的第几天
			int seasonOfYear = getSeasonOfYear(formatDate);
			String[] beginingOfSeason = {year + "0101",year + "0401",year + "0701",year + "1001"};
			int dayOfWeek = getDayOfWeek(beginingOfSeason[seasonOfYear - 1]);//得到当季1号为星期几
			
			int week = dayOfSeason / 7;
			int restDay = dayOfSeason % 7;
			
			if((restDay + dayOfWeek - 1) > 7){
				week =week + 2; 
			}else if((restDay + dayOfWeek - 1) <= 7 && (restDay + dayOfWeek - 1) != 0){
				  week = week + 1;                                                                 
			}
			
			
			return week;
		}
		/**
		 * 
		 * 功能描述：判断是一年中的第几周
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	周数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getWeekOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int year = cal.get(Calendar.YEAR);//得到当前年份
			int dayOfYear = getDayOfYear(formatDate);//当前日期是这年的第几天
			String begining_of_year = year + "0101";
			int dayOfWeek = getDayOfWeek(begining_of_year);//得到当年1月1号为星期几
			
			int week = dayOfYear / 7;
			int restDay = dayOfYear % 7;
			
			if((restDay + dayOfWeek - 1) > 7){
				week =week + 2; 
			}else if((restDay + dayOfWeek - 1) <= 7 && (restDay + dayOfWeek - 1) != 0){
				  week = week + 1;                                                                 
			}
			
			return week;
		}
		/**
		 * 
		 * 功能描述：判断是月的第几旬
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	旬数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getTenDaysOfMouth(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int tenDays = 0;
			if(day <= 10){
				tenDays = 1;
			}else if(day > 10 && day <= 20){
				tenDays = 2;
			}else{
				tenDays = 3;
			}
			return tenDays;
		}
		/**
		 * 
		 * 功能描述：判断是季的第几旬
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	旬数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getTenDaysOfSeason(String formatDate){
			int season = getSeasonOfYear(formatDate)- 1;//已过去的季数
			int tenDaysOfYear = getTenDaysOfYear(formatDate);
			int tenDayOfSeason = tenDaysOfYear -(season * 3 * 3);
			
			return tenDayOfSeason;
		}
		/**
		 * 
		 * 功能描述：判断是年的第几旬
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	旬数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getTenDaysOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH);//已过去的月数
			int tenDaysOfMonth = getTenDaysOfMouth(formatDate);//当前月的旬数
			int tenDaysOfYear = month * 3 + tenDaysOfMonth;
			
			return tenDaysOfYear;
		}
		/**
		 * 
		 * 功能描述：判断是季的第几月
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	月数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getMonthOfSeason(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;
			int seasonOfYear = getSeasonOfYear(formatDate);//当前的季度
			int monthOfSeason = month - (seasonOfYear - 1) * 3;
			return monthOfSeason;
		
		}
		/**
		 * 
		 * 功能描述：判断是年的第几月
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	月数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getMonthOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;
			
			return month;
		}
		/**
		 * 
		 * 功能描述：判断是半年的第几个季度
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	季度数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getSeasonOfHalfAYear(String formatDate){
			Calendar cal =parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;//当前月份值
			int seasonOfHalfAYear = 0;
			if( month <= 6){
				//上半年
				if( month <= 3){
					seasonOfHalfAYear = 1;
				}else{
					seasonOfHalfAYear = 2;
				}
			}else{
				//下半年
				if( month <= 9){
					seasonOfHalfAYear = 1;
				}else{
					seasonOfHalfAYear = 2;
				}
				
			}
			return seasonOfHalfAYear;
			
		}
		/**
		 * 
		 * 功能描述：判断是年的第几个季度
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	季度数
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @修改日志：
		 */
		public static int getSeasonOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;
			int seasonOfYear = ((month - 1)/3) + 1;
			return seasonOfYear;
			
		}
		/**
		 * 
		 * 功能描述：判断所给的日期是星期几
		 * @param formatDate	格式化的日期字符串		格式形如：	"yyyymmdd"
		 * @return	int型变量		1-7分别代表星期一-星期天
		 * @author dhcc zhushuai
		 * @date 2010-7-17
		 * @修改日志：
		 */
		public static int getDayOfWeek(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int dayOfWeek =cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(0 == dayOfWeek){
				dayOfWeek = 7;
			}
			return dayOfWeek;
			
		}
		
		 /**
	     * 功能描述：计算指定日期+指定天数的实际日期    指定天数可为负数
	     * @param dateTime 给定当前日期
	     * @param days 正数即增加，负数为减少
	     * @return String 字符串类型 
	     * @author donbiyong
	     * @date 2010-7-8
	     * 
	     */
		public static String getByDays(String dateTime, int days) {
			
			Calendar calendar = parseCalendar(dateTime);
			
			if(calendar == null){
				return null;
			}
			
			calendar.add(Calendar.DATE, days);
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = calendar.getTime();
			
			return format.format(date);
		}
		
		/**
		 * 功能描述：计算指定日期+指定月数的实际日期   指定月数可为负数
		 * @param dateTime 指定日期
		 * @param months 正数为增加，负数为减少
		 * @return String 
		 * @author donbiyong
		 * @date 2010-7-8
		 */
	    public static String getByMonths(String dateTime, int months) {
	    	
	    	Calendar calendar = parseCalendar(dateTime);

		    String strMon="00000000";//初始的非合法日期
	    	if(calendar == null){
				return strMon;
			}
	    	
	    	int year1 = calendar.get(Calendar.YEAR);
	    	int month1 = calendar.get(Calendar.MONTH) + 1 + months;
	    	int day1 = calendar.get(Calendar.DATE);
	    	
	    	int nYears = month1/12;
	    	int nMonths = month1%12;
	    	int monthNow = 0;
	    	
	    	if(nMonths < 0) {
	    		monthNow = nMonths + 12;
	    		calendar.set(Calendar.MONTH, monthNow-1);
	    		calendar.set(Calendar.YEAR, (year1+nYears-1));
	    	}else if(nMonths > 0) {
		    	monthNow = nMonths;
		    	calendar.set(Calendar.YEAR, (year1+nYears));
			    calendar.set(Calendar.MONTH, monthNow-1);
	    	}else {
	    		monthNow = 12;
		    	calendar.set(Calendar.YEAR, (year1+nYears-1));
		    	calendar.set(Calendar.MONTH, monthNow-1);
		    }
	    	
		    if(monthNow < 10 ) {
		    	 strMon = "0" + String.valueOf(monthNow);
		    }else {
		    	strMon = String.valueOf(monthNow);
		   	}
	    	
		    StringBuffer sb_date = new StringBuffer("");
		    sb_date.append(year1+nYears).append(strMon).append("01");
		    
		   	if(getDaysOfMonth(sb_date.toString()) >= day1) {
		   		calendar.set(Calendar.DATE, day1);
		   	}else {
		   		calendar.set(Calendar.DATE, getDaysOfMonth(sb_date.toString()));
		   	}
	    	
		    SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = calendar.getTime();
			sb_date = null;
			return format.format(date);
	    }
	    
	    /**
	     * 功能描述：计算指定日期+指定季数的实际日期   指定季数可为负数
	     * @param dateTime 指定的日期
	     * @param seasons 季数 正数为增加，负数为减少
	     * @return String 日期字符串
	     * @author donbiyong
	     * @date 2010-7-9
	     */
	    public static String getByseansons(String dateTime, int seasons) {
	    	
	    	String str = getByMonths(dateTime, seasons*3);
	    	if(str.equals("00000000")){
				return "00000000";
			}
	    	return str;
	    }
	    
	    /**
	     * 功能描述：计算指定日期+指定年的实际日期  指定年数可为负数
	     * @param dateTime 指定的日期
	     * @param years 指定的年数 正数为增加，负数为减少
	     * @return String 返回日期字符串
	     * @author donbiyong
	     * @date 2010-7-9
	     */
	    public static String getByYears(String dateTime, int years) {
	    	
	    	String str = getByMonths(dateTime, years*12);
	    	if(str.equals("00000000")){
				return "00000000";
			}
	    	return str;
	    }
	    /**
		 * @功能描述： 返回日期格式 yyyy-mm-dd
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 下午09:19:40
		 *
		 */
		public static String getFmDate1(String date) {
			return date!=null&&date.length()==8?date.substring(0, 4)+"-"+date.substring(4,6)+"-"+date.substring(6,8):"";
		}
		/**
		 * 
		 * @功能描述：返回日期格式 yyyy年mm月dd日
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 下午09:56:15
		 *
		 */
		public static String getFmDate2(String date) {
			return date!=null&&date.length()==8?date.substring(0, 4)+"年"+date.substring(4,6)+"月"+date.substring(6,8) + "日":"";
		}
		/**
		 * @功能描述： 返回日期格式 yyyy/MM/dd
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 下午09:22:47
		 *
		 */
		public static String getFmDate3(String date) {
			return date!=null&&date.length()==8?date.substring(0, 4)+"/"+date.substring(4,6)+"/"+date.substring(6,8):"";
		}
		/**
		 * 
		 * @功能描述： 返回日期格式 MM/dd/yyyy
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 下午09:22:47
		 *
		 */
		public static String getFmDate4(String date) {
			return date!=null&&date.length()==8?date.substring(4,6)+"/"+date.substring(6,8) + "/" + date.substring(0, 4):"";
		}
		/**
		 * 
		 * 功能描述：判断日期是否存在
		 * @param date
		 * @return
		 * @author lifeng
		 * @date 2013-1-9 下午11:07:09
		 */
		public static boolean isExsit(String date){
			if(date == null||"".equals(date)||"null".equals(date)||"0".equals(date)||date.length()!=8){
				return false;
			}
			int year = Integer.valueOf(date.substring(0, 4)); // 截住传入的日期的年
			int month = Integer.valueOf(date.substring(4, 6)); // 截住传入的日期的月
			int day = Integer.valueOf(date.substring(6, 8)); // 截住传入的日期的日
			int days = getDaysOfMonth(year, month);// 获取传入的日期所属年月的月天数
			if(days==0){
				return false;
			}
			if(day>days){
				return false;
			}
			return true;
		}
		
		/**
		 * 获取当前日期所在年的实际天数
		 * @param date--当前日期格式:20151201
		 * @return day
		 */
		public static int getDaysOfYear(int date){
			
			int day = 0;
			if(isLeapYear(String.valueOf(date))){
				day = 366;
			}else{
				day = 365;
			}
			return day;
			
		}
		
		/**
		 * 
		 * 功能描述：计算两个日期之间的整年数
		 * @param date1,date2
		 * @return
		 * @author zhangjibing
		 * @date 2016-3-21
		 */
		public static int getIntYear(String date1, String date2){
			
			String min_date = "";
			String max_date = "";
			int year = 0;
			
			if(isBefore(date1, date2)){
				min_date = date1;
				max_date = date2;
			}else{
				min_date = date2;
				max_date = date1;
			}
			
			int temp = Integer.valueOf(max_date.substring(0, 4)) - Integer.valueOf(min_date.substring(0, 4));//
			
			/**
			 * 实现过程:最小日期+(n+1)年 直到 结果>=最大日期，n即时整年
			 **/
			for(int i=0;i<=temp;i++){
				if(isBefore(max_date, getByYears(min_date, i+1))){
					year = i;
					break;
				}
			}
			return year;
		}
	}
