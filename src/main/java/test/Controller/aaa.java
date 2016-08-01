
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
		 * ��ǰ����ϵͳ���� Calendar
		 */
		private static Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
		/**
		 * ���ڸ�ʽ Ĭ�ϣ�yyyyMMdd
		 */
		private static String pattern = "yyyyMMdd";
		/**
		 * ʱ���ʽ Ĭ�ϣ�HH:mm:ss
		 */
		private static String timePattern = "HH:mm:ss";
		
		/**
		 * ��
		 */
		private static int year = 0;
		/**
		 * ��
		 */
		private static int month = 0;
		/**
		 * ��
		 */
		private static int day = 0;
		/**
		 * ʱ
		 */
		private static int hour = 0;
		/**
		 * ��
		 */
		private static int minute = 0;
		/**
		 * ��
		 */
		private static int second = 0;
		
		/**
		 * ��̬��ʼ����Ĭ��ϵͳ��ǰ���ں�ʱ�䣩
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
		 * ����������
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
	
		//���ϵͳ��ǰʱ��
		public static String getSysTime() {
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		    String fmt_date = sdf.format(date);	
		    date = null;
		    sdf = null;
		    return  fmt_date;
		}
		//���ϵͳ��ǰʱ��
		public static String getFormatSysTime() {
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    String fmt_date = sdf.format(date);	
		    date = null;
		    sdf = null;
		    return  fmt_date;	     
		}
		
		/**
		 * �����������Զ���ϵͳʱ�䡣������ʹ�ã�
		 * 
		 * @param strdate
		 *            �Զ��������ַ�������ʽ��yyyymmdd
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
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
		 * ���������� ��ʼ��ϵͳ����(��ǰϵͳ����)����setSysDate()����õ��η������³�ʼ��ϵͳ����ʱ��
		 * 			Ϊ��ǰ����ʱ��
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
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
		 * ������������ȡϵͳ��ǰ����---��
		 * 
		 * @return int ��
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static int getYear() {
			return year;
		}

		/**
		 * ������������ȡϵͳ��ǰ����---��
		 * 
		 * @return String ��
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static String getStrYear() {
			return String.valueOf(year);
		}

		/**
		 * ������������ȡϵͳ��ǰ����---��
		 * 
		 * @return int ��
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static int getMonth() {
			return month;
		}

		/**
		 * ������������ȡϵͳ��ǰ����---��
		 * 
		 * @return String ��
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static String getStrMonth() {
			return month >= 10 ? String.valueOf(month) : "0"
					+ String.valueOf(month);
		}

		/**
		 * ������������ȡϵͳ��ǰ����---��
		 * 
		 * @return int ��
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static int getDay() {
			return day;
		}

		/**
		 * ������������ȡϵͳ��ǰ����---��
		 * 
		 * @return String ��
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static String getStrDay() {
			return day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
		}
		/**
		 * 
		 * ������������ȡϵͳʱ��--Сʱ
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @�޸���־��1.0
		 */
		public static int getHour(){
			return hour;
		}
		/**
		 * 
		 * ������������ȡϵͳʱ��--����
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @�޸���־��
		 */
		public static int getMinute(){
			return minute;
		}
		/**
		 * 
		 * ������������ȡϵͳʱ��--��
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @�޸���־��
		 */
		public static int getSecond(){
			return second;
		}

		/**
		 * ������������ȡϵͳ��ǰ����---������ ����ʽ��yyyymmdd��
		 * 
		 * @return String ������
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static String getDate() {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * ��������������Ԥ����ʽȡϵͳ��ǰ����---������
		 * 
		 * @param ptn
		 *            ���ڸ�ʽ
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static String getDate(String ptn) {
			SimpleDateFormat format = new SimpleDateFormat(ptn);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * ������������ȡϵͳʱ�� ��ʽ��yyyymmdd hh:mm:ss
		 * 
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static String getDateTime() {
			SimpleDateFormat format = new SimpleDateFormat(pattern + " "
					+ timePattern);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * 
		 * ������������ȡԤ�����ʽ��ϵͳʱ��
		 * 
		 * @param datePtn
		 *            ���ڸ�ʽ
		 * @param timePtn
		 *            ʱ���ʽ
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @�޸���־��1.0
		 */
		public static String getDateTime(String datePtn, String timePtn) {
			SimpleDateFormat format = new SimpleDateFormat(datePtn + " " + timePtn);
			Date date = calendar.getTime();
			return format.format(date);
		}

		/**
		 * �����������жϸ������ڣ���ʽyyyymmdd���Ƿ���ϵͳ����֮ǰ���ǣ�����ڣ���true����false
		 * 
		 * @param strdate
		 *            ��������
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static boolean isBefore(String strdate) {
			Calendar cal = parseCalendar(strdate);
			return cal.before(calendar);
		}
		/**
		 * 
		 * �����������жϸ������������ڵ�ǰ��strdate1��strdate2֮ǰ����ͬһ�죩������true����֮��false
		 * @param strdate1	����1
		 * @param strdate2	����2
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-25
		 * @�޸���־��1.0
		 */
		public static boolean isBefore(String strdate1, String strdate2){
			Calendar cal1 = parseCalendar(strdate1);
			Calendar cal2 = parseCalendar(strdate2);
			return cal1.before(cal2);
		}
		/**
		 * 
		 * ���������������ڵ�ǰϵͳ�������ӻ���� n ��������
		 * 
		 * @param days
		 *            ���ӻ���ٵ��������������ӣ���֮����
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @�޸���־��
		 */
		public static String addByDay(int days) {
			calendar.add(Calendar.DATE, days);
			return getDate();
		}

		/**
		 * 
		 * ���������������ڸ��������ڼ��ϻ��ȥ n ��������
		 * 
		 * @param datestr
		 *            ����������
		 * @param days
		 *            �������ӣ���֮����
		 * @return String
		 * @author wangshanfang
		 * @date 2008-11-24
		 * @�޸���־��
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
		 * ������������ø���������ϵͳ��ǰ����֮�������
		 * 
		 * @param strdate
		 *            �����������ַ���
		 * @return long ����
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static long getDays(String strdate) {
			Calendar cal = parseCalendar(strdate);
			Calendar cal1 = parseCalendar(getDate());
			long millis = Math.abs(cal.getTimeInMillis() - cal1.getTimeInMillis());
			return millis / (24 * 60 * 60 * 1000);
		}

		/**
		 * 
		 * ������������ø�������������֮���������������ڲ���ǰ��
		 * 
		 * @param fromdate
		 *            �����ַ��� ��ʽ��yyyymmdd
		 * @param todate
		 *            �����ַ��� ��ʽ��yyyymmdd
		 * @return long
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 */
		public static long getDaysBetween(String fromdate, String todate) {
			Calendar from = parseCalendar(fromdate);
			Calendar to = parseCalendar(todate);
			long millis = Math.abs(from.getTimeInMillis() - to.getTimeInMillis());
			return millis / (24 * 60 * 60 * 1000);
		}

		/**
		 * 
		 * ������������ø���������ϵͳ��ǰ����֮�����������������
		 * 
		 * @param strdate
		 *            �����������ַ���
		 * @return long ����
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־������
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
		 * ���������������������֮����²�������������
		 * @param strdate1
		 * @param strdate2
		 * @return long
		 * @author wangshanfang
		 * @date 2008-11-25
		 * @�޸���־������
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
		 * ������������ø���������ϵͳ��ǰ����֮�������������
		 * 
		 * @param strdate
		 *            �����������ַ���
		 * @return long[] �±�0������1����
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־������
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
		 * ������������ø�����������֮�������������
		 * @param strdate1
		 * @param strdate2
		 * @return long[] �±�0������1����
		 * @author wangshanfang
		 * @date 2008-11-25
		 * @�޸���־��
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
		 * �����������ж��ַ����Ƿ����ת��Ϊ������ �ǣ�true����false
		 * 
		 * @param strdate
		 *            Ԥת���ַ���
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
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
		 * ���������������������ж��ַ����Ƿ����ת��Ϊ������ �ǣ�true����false
		 * @param i_date
		 * @return
		 * @author zhangjiqing
		 * @date 2011-5-13 ����11:03:31
		 */
		public static boolean isDateStr(int i_date) {
			
			Calendar calendar = parseCalendar(String.valueOf(i_date));
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
				return false;
			}else return true;
		}
		/**
		 * 
		 * ��������������ָ����ʽ������ 
		 * @param s_date ��Ҫ��ʽ�����ַ�������20110330
		 * @param s_fmt ���صĸ�ʽ �� yyyy-mm-dd;yyyy/mm/dd;mm/dd/yyyy��
		 * @return
		 * @author zhaizhihui
		 * @date 2011-5-13 ����11:12:08
		 * �ͷ��ڴ棬δ����---������
		 */
		public static String getFormatDate(String s_date, String s_fmt)  {
			DateFormat getDateFormat = new SimpleDateFormat("yyyymmdd");//�������ڵĸ�ʽ
			DateFormat putDateFormat = null; //Ҫ�õ������ڵĸ�ʽ
			String formatDate = null; //��ʽ���������
			try {
				Date d = getDateFormat.parse(s_date);
				putDateFormat = new SimpleDateFormat(s_fmt);
				formatDate = putDateFormat.format(d);
			} catch (ParseException e) {
				System.out.println("����Ĳ�����ȷ������");
				e.printStackTrace();
			}finally{
				getDateFormat = null;
				putDateFormat = null;
			}
			return formatDate;
		}

		
		/**
		 * �����������ж��Ƿ������꣨����1000--9999���ǣ�true����false
		 * 
		 * @param strdate
		 *            Ԥ�ж��� ��ʽyyyymmdd �� yyyy
		 * @return boolean
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
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
		 * ������������ȡĳһ�·ݵ�����
		 * 
		 * @param strdate
		 *            ���� ��ʽ��yyyymmdd �� yyyymm
		 * @return int
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
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
		 * �������������ַ���ת��ΪCalendar
		 * 
		 * @param strdate
		 *            Ԥת�����ַ���
		 * @return Calendar
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
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
		 * �������������ַ���ת��ΪDate������ ���ڸ�ʽyyyymmdd
		 * 
		 * @param strdate
		 *            Ԥת�����ַ���
		 * @return Date
		 * @author wangshanfang
		 * @date 2008-11-21
		 * @�޸���־��1.0
		 * �ͷ��ڴ棬δ����---������
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
		 * ����������ȡ�÷��������ڵ���
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
		 * ����������ȡ�÷��������ڵ���
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
		 * ����������ȡ�÷��������ڵ���
		 * @return ����
		 * @ tianming 
		 * @ 2015-8-5
		 */
		public static String getCurrentDay() {
			Calendar c = Calendar.getInstance();
			int day = c.get(Calendar.DATE);
			return String.valueOf(day);
		}
				    
	    /**
		 * ������������ø�����������֮��Ļ������
		 * @param strdate1����ʼ���ڣ�strdate2:��ֹ����
		 * @return long
		 * @author fangchengbing
		 * @date 2010-07-10
		 * @�޸���־����һ���ַ���ʽ�����쳣
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
		 * ������������ø�����������֮������
		 * 
		 * @param strdate1
		 * @param strdate2
		 * @return long 
		 * @author fangchengbing
		 * @date 2010-07-10
		 * @�޸���־��
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
		 * ������������ø�����������֮����������Կ����ʵ���������㵱�������������·����ΰ������µ�ʵ���������㣬����һ���� �İ�һ�����㣩
		 * 
		 * @param strdate1
		 * @param strdate2
		 * @return long[] �±�0������1����
		 * @author wangshanfang
		 * @date 2010-07-10
		 * @�޸���־��
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
		 * ����������������������֮������������ն��յķ�ʽ���㣬����һ���µİ�һ�����㣩
		 * ����˼·������ʼ���ڵ��·ݼ�һ���£�������ڸ��·�Ӧ�е����������Ӧ��Ӧ�е�����������ʼ���ڣ��·ݼ�һ��
		 * �Ƚ���ʼ��������ֹ���ڣ����С����ֹ���ڼ������㣬ֱ�����ڵ�����ֹ����Ϊֹ
		 * @param strdate1����ʼ���ڣ�strdate2����ֹ���ڣ�           
		 * @return int
		 * @author fangchengbing
		 * @date 2010-7-10
		 * @�޸���־��
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
		 * ����������������������֮��ļ��������ն��յķ�ʽ���㣬����һ�����İ�һ�����㣩
		 * @param strdate1����ʼ���ڣ�strdate2����ֹ���ڣ�           
		 * @return int
		 * @author fangchengbing
		 * @date 2010-7-10
		 * @�޸���־��
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
		 * ����������������������֮������������ն��յķ�ʽ���㣬����һ����İ�һ�����㣩
		 * @param strdate1����ʼ���ڣ�strdate2����ֹ���ڣ�           
		 * @return int
		 * @author fangchengbing
		 * @date 2010-7-10
		 * @�޸���־��
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
		 * ������������ø�����������֮��ļ������Կ�����ʵ���������㵱�������������·����ΰ������µ�ʵ���������㣬����һ���� �İ�һ�����㣩
		 * 
		 * @param strdate1
		 * @param strdate2
		 * @return long
		 * @author fangchengbing
		 * @date 2010-07-10
		 * @�޸���־��
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
		 * �����������ж��Ƿ�����ĩ�����գ�
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isSunday(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �����������������ж��Ƿ�������
		 * @param year ��2010
		 * @return
		 * @author lifeng
		 * @date 2010-7-16
		 * @�޸���־��
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
		 * �����������������¼��㵱������
		 * @param year ��2010
		 * @param month ��7
		 * @return
		 * @author lifeng
		 * @date 2010-7-16
		 * @�޸���־��
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
		 * �����������ж������Ƿ���Ѯĩ ��ÿ��10�š�20�Ż���ÿ�����һ�죩
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isLateLast(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �ж������Ƿ���Ѯ��
		 * @param date
		 * @return
		 */
		public static boolean isFirstLast(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �����������ж������Ƿ�����ĩ
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isLastDayOfMonth(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �����������ж������Ƿ����³�
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isFirstDayOfMonth(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �����������ж��Ƿ��Ǽ�ĩ
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isQuarter(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �����������ж��Ƿ��Ǽ���
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isFirstQuarter(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �����������ж��Ƿ�����ĩ��12��31�ţ�
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isEndYear(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * �����������ж��Ƿ��������12��31�ţ�
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static boolean isFirstYear(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				System.out.println("���ڸ�ʽ����");
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
		 * ��������������ָ�����ڵ��ܵ����һ��
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static String getEndDayOfWeek(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//��ʾ���ڸ�ʽ����
				return "00000000";
			}
			String answer = null;
			if(isSunday(date)) {
				answer = date;
			} else {
				//day:���ܵڼ���
				int day = calendar.get(Calendar.DAY_OF_WEEK);
				//flag:���µ����� + ����ĩ��������
				int flag = calendar.get(Calendar.DAY_OF_MONTH) + (8-day);
//							System.out.println("flag=" + flag);
				//sunDay:����������
				int sumDay = getDaysOfMonth(date);
//							System.out.println("sumDay=" + sumDay);
				//����
				int month = calendar.get(Calendar.MONTH) + 1;
				//����
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
		 * ��������������ָ�����ڵ�Ѯ�����һ��
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static String getDateOfMonth(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//��ʾ���ڸ�ʽ����
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
		 * ��������������ָ�����ڵ��µ����һ��
		 * @param date �ַ������� "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static String getLatDateOfMonth(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//��ʾ���ڸ�ʽ����
				return "00000000";
			}
			return intToString(calendar.get(Calendar.YEAR),(calendar.get(Calendar.MONTH) + 1),
					getDaysOfMonth(date));
					
		}
		
		/**
		 * 
		 * ��������������ָ�����ڵļ������һ��
		 * @param date ָ�����ڵ��ַ�����ʾ "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static String getLastDateOfSeason(String date){
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//��ʾ���ڸ�ʽ����ȷ
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
		 * ��������������ָ�����ڵ�������һ��
		 * @param date ָ�����ڵ��ַ�����ʾ "20100714"
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
		 */
		public static String getLastDateOfYear(String date) {
			Calendar calendar = parseCalendar(date);
			if(calendar == null) {
				//��ʾ���ڸ�ʽ����ȷ
				return "00000000";
			}
			return intToString(calendar.get(Calendar.YEAR),12,31);
		}
		
		/**
		 * 
		 * ������������������ʽ��������ת�����ַ�����ʽ �磺20100714
		 * @param year ������
		 * @param month ������
		 * @param day ������
		 * @return
		 * @author lifeng
		 * @date 2010-7-14
		 * @�޸���־��
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
		 * ����������	�ж�һ��������һѮ�еĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
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
		 * �����������ж�һ��������һ�����еĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getDayOfMonth(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			return dayOfMonth;
		}
		/**
		 * 
		 * �����������ж���һ���ĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
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
		 * �����������ж���һ��ĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getDayOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			return dayOfYear;
		}
		/**
		 * 
		 * �����������ж���һ�����еĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getWeekOfMonth(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int year = cal.get(Calendar.YEAR);//�õ���ǰ���
			int month = cal.get(Calendar.MONTH) + 1;//�õ���ǰ����
			int dayOfMonth = getDayOfMonth(formatDate);//��ǰ����������µĵڼ���
			String monthStr = month >= 10 ? String.valueOf(month) : "0"+String.valueOf(month);
			String beginingOfMonth = year+""+monthStr+"01";//�³�
			int dayOfWeek = getDayOfWeek(beginingOfMonth);//�õ���ǰ��1��Ϊ���ڼ�
			
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
		 * �����������ж���һ�������еĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getWeekOfSeason(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int year = cal.get(Calendar.YEAR);//�õ���ǰ���
			int dayOfSeason = getDayOfSeason(formatDate);//��ǰ������������ĵڼ���
			int seasonOfYear = getSeasonOfYear(formatDate);
			String[] beginingOfSeason = {year + "0101",year + "0401",year + "0701",year + "1001"};
			int dayOfWeek = getDayOfWeek(beginingOfSeason[seasonOfYear - 1]);//�õ�����1��Ϊ���ڼ�
			
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
		 * �����������ж���һ���еĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getWeekOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int year = cal.get(Calendar.YEAR);//�õ���ǰ���
			int dayOfYear = getDayOfYear(formatDate);//��ǰ����������ĵڼ���
			String begining_of_year = year + "0101";
			int dayOfWeek = getDayOfWeek(begining_of_year);//�õ�����1��1��Ϊ���ڼ�
			
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
		 * �����������ж����µĵڼ�Ѯ
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	Ѯ��
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
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
		 * �����������ж��Ǽ��ĵڼ�Ѯ
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	Ѯ��
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getTenDaysOfSeason(String formatDate){
			int season = getSeasonOfYear(formatDate)- 1;//�ѹ�ȥ�ļ���
			int tenDaysOfYear = getTenDaysOfYear(formatDate);
			int tenDayOfSeason = tenDaysOfYear -(season * 3 * 3);
			
			return tenDayOfSeason;
		}
		/**
		 * 
		 * �����������ж�����ĵڼ�Ѯ
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	Ѯ��
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getTenDaysOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH);//�ѹ�ȥ������
			int tenDaysOfMonth = getTenDaysOfMouth(formatDate);//��ǰ�µ�Ѯ��
			int tenDaysOfYear = month * 3 + tenDaysOfMonth;
			
			return tenDaysOfYear;
		}
		/**
		 * 
		 * �����������ж��Ǽ��ĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getMonthOfSeason(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;
			int seasonOfYear = getSeasonOfYear(formatDate);//��ǰ�ļ���
			int monthOfSeason = month - (seasonOfYear - 1) * 3;
			return monthOfSeason;
		
		}
		/**
		 * 
		 * �����������ж�����ĵڼ���
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	����
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getMonthOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;
			
			return month;
		}
		/**
		 * 
		 * �����������ж��ǰ���ĵڼ�������
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	������
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getSeasonOfHalfAYear(String formatDate){
			Calendar cal =parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;//��ǰ�·�ֵ
			int seasonOfHalfAYear = 0;
			if( month <= 6){
				//�ϰ���
				if( month <= 3){
					seasonOfHalfAYear = 1;
				}else{
					seasonOfHalfAYear = 2;
				}
			}else{
				//�°���
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
		 * �����������ж�����ĵڼ�������
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	������
		 * @author dhcc zhushuai
		 * @date 2010-7-15
		 * @�޸���־��
		 */
		public static int getSeasonOfYear(String formatDate){
			Calendar cal = parseCalendar(formatDate);
			int month = cal.get(Calendar.MONTH) + 1;
			int seasonOfYear = ((month - 1)/3) + 1;
			return seasonOfYear;
			
		}
		/**
		 * 
		 * �����������ж����������������ڼ�
		 * @param formatDate	��ʽ���������ַ���		��ʽ���磺	"yyyymmdd"
		 * @return	int�ͱ���		1-7�ֱ��������һ-������
		 * @author dhcc zhushuai
		 * @date 2010-7-17
		 * @�޸���־��
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
	     * ��������������ָ������+ָ��������ʵ������    ָ��������Ϊ����
	     * @param dateTime ������ǰ����
	     * @param days ���������ӣ�����Ϊ����
	     * @return String �ַ������� 
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
		 * ��������������ָ������+ָ��������ʵ������   ָ��������Ϊ����
		 * @param dateTime ָ������
		 * @param months ����Ϊ���ӣ�����Ϊ����
		 * @return String 
		 * @author donbiyong
		 * @date 2010-7-8
		 */
	    public static String getByMonths(String dateTime, int months) {
	    	
	    	Calendar calendar = parseCalendar(dateTime);

		    String strMon="00000000";//��ʼ�ķǺϷ�����
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
	     * ��������������ָ������+ָ��������ʵ������   ָ��������Ϊ����
	     * @param dateTime ָ��������
	     * @param seasons ���� ����Ϊ���ӣ�����Ϊ����
	     * @return String �����ַ���
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
	     * ��������������ָ������+ָ�����ʵ������  ָ��������Ϊ����
	     * @param dateTime ָ��������
	     * @param years ָ�������� ����Ϊ���ӣ�����Ϊ����
	     * @return String ���������ַ���
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
		 * @���������� �������ڸ�ʽ yyyy-mm-dd
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 ����09:19:40
		 *
		 */
		public static String getFmDate1(String date) {
			return date!=null&&date.length()==8?date.substring(0, 4)+"-"+date.substring(4,6)+"-"+date.substring(6,8):"";
		}
		/**
		 * 
		 * @�����������������ڸ�ʽ yyyy��mm��dd��
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 ����09:56:15
		 *
		 */
		public static String getFmDate2(String date) {
			return date!=null&&date.length()==8?date.substring(0, 4)+"��"+date.substring(4,6)+"��"+date.substring(6,8) + "��":"";
		}
		/**
		 * @���������� �������ڸ�ʽ yyyy/MM/dd
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 ����09:22:47
		 *
		 */
		public static String getFmDate3(String date) {
			return date!=null&&date.length()==8?date.substring(0, 4)+"/"+date.substring(4,6)+"/"+date.substring(6,8):"";
		}
		/**
		 * 
		 * @���������� �������ڸ�ʽ MM/dd/yyyy
		 * @param date
		 * @returnString
		 * @author zhangjiqing
		 * @date :2012-8-29 ����09:22:47
		 *
		 */
		public static String getFmDate4(String date) {
			return date!=null&&date.length()==8?date.substring(4,6)+"/"+date.substring(6,8) + "/" + date.substring(0, 4):"";
		}
		/**
		 * 
		 * �����������ж������Ƿ����
		 * @param date
		 * @return
		 * @author lifeng
		 * @date 2013-1-9 ����11:07:09
		 */
		public static boolean isExsit(String date){
			if(date == null||"".equals(date)||"null".equals(date)||"0".equals(date)||date.length()!=8){
				return false;
			}
			int year = Integer.valueOf(date.substring(0, 4)); // ��ס��������ڵ���
			int month = Integer.valueOf(date.substring(4, 6)); // ��ס��������ڵ���
			int day = Integer.valueOf(date.substring(6, 8)); // ��ס��������ڵ���
			int days = getDaysOfMonth(year, month);// ��ȡ����������������µ�������
			if(days==0){
				return false;
			}
			if(day>days){
				return false;
			}
			return true;
		}
		
		/**
		 * ��ȡ��ǰ�����������ʵ������
		 * @param date--��ǰ���ڸ�ʽ:20151201
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
		 * ����������������������֮���������
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
			 * ʵ�ֹ���:��С����+(n+1)�� ֱ�� ���>=������ڣ�n��ʱ����
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
