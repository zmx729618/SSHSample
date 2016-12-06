package org.nercita.bcp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 功能描述：
 * 
 * @author
 */
public class DateUtil {

	public static Date date = null;

	public static DateFormat dateFormat = null;

	public static Calendar calendar = null;

	
	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期
	 * @param format
	 *            String 格式
	 * @return Date 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			dateFormat = new SimpleDateFormat(format);
//			String dt = dateStr.replaceAll("-", "/");
//			if ((!dt.equals("")) && (dt.length() < format.length())) {
//				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
//						"0");
//			}
			date = (Date) dateFormat.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date parseDateWithException(String dateStr, String format) throws ParseException {
		dateFormat = new SimpleDateFormat(format);
		date = (Date) dateFormat.parse(dateStr);
		return date;
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期：YYYY-MM-DD 格式
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 功能描述：
	 * 
	 * @param date
	 *            Date 日期
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回输入年份的当前和以后10年
	 * 
	 * @param String
	 *            curYear 输入年份
	 * @return 返回年份
	 */
	public static List<Object[]> getBeforeAndAfterBYCurYear(String curYear) {
		int curYearInt;
		List<Object[]> yearList = new ArrayList<Object[]>();
		if (curYear == null || curYear.equals("") || Integer.parseInt(curYear) < 10) {
			calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			curYearInt = calendar.get(Calendar.YEAR);
		}else {
			curYearInt = Integer.parseInt(curYear);
		}
		Object[] obj = new Object[2] ;
		for (int i = -10; i < 10; i++) {
			obj[0]="year";
			obj[1]=curYearInt+i;
			yearList.add(obj);
		}
		System.out.println("====value:"+yearList.toString());
		return yearList;
	}
	
	/**
	 * 功能描述：返回当前年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 功能描述：返回当前年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static String getCurYearStr() {
		Date date = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期 yyyy/MM/dd 格式
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型时间 HH:mm:ss 格式
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}
	
	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd-HH:mm:ss 格式
	 */
	public static String getCurDateTime() {
		return format(new Date(), "yyyy-MM-dd_HH:mm:ss");
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date
	 *            Date 日期
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}
	/**
	 * 功能描述：两个日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            int 天数
	 * @return 返回相减后的Date日期
	 */
	public static Date minusDate(Date date,int day) {
		long millis = (getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(String strdate) {
		date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}
	
	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 默认yyyy/MM/dd格式
	 */
	public static Date getMonthBegin(String datestr,String format) {
		Date date;
		if(StringUtils.isNotBlank(format))
			date=parseDate(datestr, format);
		else
			date=parseDate(datestr);
		
		return getMonthBegin(date);
	}
	
	public static Date getMonthBegin(Date date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(format(date, "yyyy-MM") + "-01");
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static Date getMonthEnd(Date date) {
		date = getMonthBegin(date);
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		calendar.add(Calendar.HOUR, 23);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 默认yyyy/MM/dd格式
	 */
	public static Date getMonthEnd(String datestr,String format) {
		Date date;
		if(StringUtils.isNotBlank(format))
			date=parseDate(datestr, format);
		else
			date=parseDate(datestr);
		
		return getMonthEnd(date);
	}
	

	/**
	 * 功能描述：常用的格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return String 日期字符串
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	

	@SuppressWarnings("static-access")
	public static Date firstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH),
				calendar.get(calendar.DATE), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	@SuppressWarnings("static-access")
	public static Date lastDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate(date));
		calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH),
				calendar.get(calendar.DATE), 23, 59, 59);

		return calendar.getTime();
	}
	
	public static Date parseMBeginByYM(int year, int month) {
		Date date = null;
		if(month > 0 && month < 10) {
			String beginStr = String.valueOf(year) + "0" + String.valueOf(month) + "01";
			date = DateUtil.parseDate(beginStr, "yyyyMMdd");
		}
		if(month >= 10 && month <= 12){
			String beginStr = String.valueOf(year) + String.valueOf(month) + "01";
			date = DateUtil.parseDate(beginStr, "yyyyMMdd");
		}
		return date;
	}
	
	
	public static Date parseMEndByYM(int year, int month) {
		Date date = parseMBeginByYM(year, month);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(month > 0 && month < 10) {
			String beginStr = String.valueOf(year) + "0" + String.valueOf(month) + String.valueOf(days);
			date = DateUtil.parseDate(beginStr, "yyyyMMdd");
		}
		if(month >= 10 && month <= 12){
			String beginStr = String.valueOf(year) + String.valueOf(month) + String.valueOf(days);
			date = DateUtil.parseDate(beginStr, "yyyyMMdd");
		}
		return date;
	}
	
	/**
	 * 获得日期0点时间
	 */
	public static Date getTimesMorning(Date date) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0); 
		c.set(Calendar.SECOND, 0); 
		c.set(Calendar.MINUTE, 0); 
		c.set(Calendar.MILLISECOND, 0); 
		return c.getTime();
	}
	
	/**
	 * 获得该年的最小时间
	 */
	public static Date getBeginForYear(String year) {
		return parseDate(year+"-01-01","yyyy-MM-dd");
	}
	/**
	 * 获得该年的最大时间
	 */
	public static Date getEndForYear(String year) {
		return parseDate(year+"/12/31 235959","yyyy/MM/dd HHmmss");
	}
	
 	/**
      * @return List<Date> 里面的每一项是从开始月份到结束月份排列 ()
      */  
	public static List<Date> getAllMonthsBetween(Date starttime, Date endtime) {  
			if(endtime.before(starttime)){//后面时间比前面时间大则交换
				Date dtemp=starttime;
				starttime=endtime;
				endtime=dtemp;
			}
	         List<Date> datalist = new ArrayList<Date>();  
	         if(compare(endtime, starttime)){//相等直接返回
	        	 datalist.add(starttime);
	        	 return datalist;
	         }
	         Calendar calendar=Calendar.getInstance();
	         calendar.setTime(starttime);
	         datalist.add(calendar.getTime());
	         calendar.add(Calendar.MONTH, 1);
	         Date temp=calendar.getTime();
	         while(!compare(endtime, temp)){
	        	 datalist.add(temp);
	        	 calendar.add(Calendar.MONTH, 1);
	        	 temp=calendar.getTime();
	         }
	         datalist.add(endtime);
	         return datalist;  
	     }  
	/**
     *  
     * @return List<string> 比较两个日期（年月相等就表示相等）  
     */  
	public static boolean compare(Date starttime, Date endtime){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(starttime);
		int syear=calendar.get(Calendar.YEAR);
		int smonth=calendar.get(Calendar.MONTH);
		calendar.setTime(endtime);
		int eyear=calendar.get(Calendar.YEAR);
		int emonth=calendar.get(Calendar.MONTH);
		if(syear==eyear&&smonth==emonth)
				return true;
		return false;
	}
	
	/**
	 *  
	 * @return List<Integer> 里面的每一项是从开始年份到结束年份排列 
	 */  
	public static List<Integer> getAllYearBetween(Date starttime, Date endtime) {
		List<Integer> datalist = new ArrayList<Integer>();  
		int syear=getYear(starttime);
		int eyear=getYear(endtime);
		if(eyear<syear){
			int t=syear;
			syear=eyear;
			eyear=t;
		}
		datalist.add(syear);
		if(syear==eyear)
			return datalist;
		syear+=1;
		while(syear<=eyear){
			datalist.add(syear);
			syear++;
		}
		return datalist;  
	}  
 	/**
     *  
     * @return List里面的每一项是从开始年份到结束年份排列 
     */  
	public static List<Integer> gethistoryTenYearsToNow() {
		List<Integer> datalist = new ArrayList<Integer>();  
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		datalist.add(calendar.get(Calendar.YEAR));
		for(int i=1;i<10;i++){
			calendar.add(Calendar.YEAR, -1);
			datalist.add(calendar.get(Calendar.YEAR));
		}
		return datalist;
	  }
	
	/**
     * @return int 两个日期相差月数, 开始在1日，结束在月末，加一个月   
     */  
	public static int getMonthDays(Date starttime, Date endtime) {  
		Calendar dayBegin = Calendar.getInstance();
		Calendar dayEnd = Calendar.getInstance();
		dayBegin.setTime(starttime); 
		dayEnd.setTime(endtime);
		
        //初始计算         
        int result= dayEnd.get(Calendar.MONTH) +dayEnd.get(Calendar.YEAR)*12  
       	    -dayBegin.get(Calendar.MONTH) - dayBegin.get(Calendar.YEAR)*12;     
         
        //赋值结束日期的月末                
        Calendar lastDayInEndMonth=Calendar.getInstance();  
        lastDayInEndMonth.set(dayEnd.get(Calendar.YEAR),  
                dayEnd.get(Calendar.MONTH),1);  
        lastDayInEndMonth.add(Calendar.MONTH,1);  
        lastDayInEndMonth.add(Calendar.DATE,-1);      
          
        // 开始在1日，结束在月末，加一个月   
        if((lastDayInEndMonth.get(Calendar.DATE)==dayEnd.get(Calendar.DATE))&&(dayBegin.get(Calendar.DATE)==1)){  
            result += 1;          
        }  
        // 如果结束的日期+1<开始日期   并且结束的日期不为月底就扣除一个月   
        if((dayEnd.get(Calendar.DATE)+1 < dayBegin.get(Calendar.DATE))&& !(lastDayInEndMonth.get(Calendar.DATE)==dayEnd.get(Calendar.DATE))){  
            result -=1;  
        }  
        return result;       
	} 
	
	/**
	 * 功能描述：日期增加毫秒
	 * 
	 * @param date
	 *            Date 日期
	 * @param millis
	 *            int 毫秒数
	 * @return 返回相加后的日期
	 */
	public static Date addMillis(Date date, int millis) {
		calendar = Calendar.getInstance();
		long target = getMillis(date) + millis;
		calendar.setTimeInMillis(target);
		return calendar.getTime();
	}
	
	/**
	 * 将kendo日期控件获取的日期字符串转为Date数据
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parseKendoDate(String dateStr) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
		Date date=sdf.parse(dateStr);
		//加八小时
		Calendar ca=Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.HOUR_OF_DAY,8);
		return ca.getTime();
	}
	
	/**
	 * 获取当前日期前六个月的日期
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("static-access")
	public static Date sixMonthBeforDate(Date date){
		//Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -6);  //设置为前6月
		dBefore = calendar.getTime();   //得到前6月的时间

		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前6月的时间
		String defaultEndDate = sdf.format(dNow); //格式化当前时间

		System.out.println("前6个月的时间是：" + defaultStartDate);
		System.out.println("生成的时间是：" + defaultEndDate);*/
		return dBefore;
	}
	
	
	public static void main(String[] args) {
//		Date d = new Date();
//		
//		// System.out.println(d.toString());
////		System.out.println(formatDate(d).toString());
//		// System.out.println(getMonthBegin(formatDate(d).toString()));
//		// System.out.println(getMonthBegin("2008/07/19"));
//		// System.out.println(getMonthEnd("2008/07/19"));
////		System.out.println(parseDate("2000").getYear());
//		System.out.println(getBeginForYear("2009"));
//		System.out.println(getEndForYear("2009"));
//		System.out.println(parseDate("2010-10","yyyy-mm"));
//		System.out.println(getMonthEnd("2010-10","yyyy-MM"));
//		boolean[] ss=new boolean[10];
//		int[] a=new int[10];
//		Boolean[] boolean1=new Boolean[10];
//		System.out.println(boolean1[0]);
//		System.out.println(a[0]);
//		System.out.println(ss[0]);
//		List<Integer>list=gethistoryTenYearsToNow();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Integer name = (Integer) iterator.next();
//			System.out.println(name);
//			
//		}
		System.out.println(firstDate(new Date()));
	}
}
