package test.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class DateUtil {

	
	
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
//					int date1 = Integer.parseInt(str.substring(6,8));
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
}
