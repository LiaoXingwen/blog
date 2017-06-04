package cc.bblog.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateKit {

	/**
	 * 将一个普通是Date类转义语化的字符串
	 * @param date
	 * @return
	 */
	public static String DateToSimpleFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}


	/**
	 * 将一个现在的时间转义语化的字符串
	 * @param date
	 * @return
	 */
	public static String DateToSimpleFormat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 将一个普通义语化的字符串转成long
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static long SimpleFormatToLong(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.parse(date).getTime();
	}

	/**
	 * 将一个普通是long时间转义语化的字符串
	 * @param date
	 * @return
	 */
	public static String LongToSimpleFormat(long date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(date));
	}

	/**
	 * 将时间转到0点的long
	 * 
	 * @param @param time
	 * @param @return
	 * @param @throws ParseException    
	 * @return long   
	 * 
	 */
	public static long toZeroTime()  {

		try {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(calendar.YEAR);
			int m = calendar.get(calendar.MONTH) ;
			int day = calendar.get(calendar.DAY_OF_MONTH);
			String format = "yyyy-MM-dd HH:mm:ss";
			String timesString = year +"-"+m +"-"+day +" 00:00:00";
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(timesString).getTime();
		} catch (ParseException e) {
		}
		return 0;
	} 
	
	/**
	 * 转化当天零点的时间
	 * @return
	 */
	public static Date toZeroDate() {
		return new Date(toZeroTime());
	}

	/**
	 * 将时间转到0点的字符串
	 * 
	 * @param @param time
	 * @param @return
	 * @param @throws ParseException    
	 * @return long   
	 * 
	 */
	public static String toZeroTimeString()  {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(calendar.YEAR);
		int m = calendar.get(calendar.MONTH)+1 ;
		int day = calendar.get(calendar.DAY_OF_MONTH);
		String timesString = year +"-"+m +"-"+day +" 00:00:00";
		return timesString;
	} 
}
