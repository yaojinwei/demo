package com.yaojinwei.framework.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类
 * @author jinwei.yjw
 * @date 2018/3/16 17:13
 */
public class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_CN = "yyyy年M月d日";
    private static final ThreadLocal<Map<String, DateFormat>> dateFormatTL = new ThreadLocal<Map<String, DateFormat>>();

    public static DateFormat getDateFormat(){
        return getDateFormat(null);
    }

    public static DateFormat getDateFormat(String format){
        Map<String, DateFormat> map = dateFormatTL.get();

        if (map == null) {
            map = new HashMap<String, DateFormat>();
            dateFormatTL.set(map);
        }

        if (StringUtil.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }

        DateFormat ret = map.get(format);

        if (ret == null) {
            ret = new SimpleDateFormat(format);
            map.put(format, ret);
        }

        return ret;
    }

    //时间格式化
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return getDateFormat().format(date);
    }

    public static String formatDate(Date date, String template) {
        if (date == null || template == null) {
            return "";
        }
        return getDateFormat(template).format(date);
    }

    public static Date parse(String dateStr, String format){
        try {
            Date date = new SimpleDateFormat(format).parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取两个时间间隔的天数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     *  startDate和endDate之间的天数，精确到毫秒
     */
    public static int getDaysBetween(Date startDate, Date endDate){
        if (null == startDate || null == endDate) {
            return -1;
        }
        return getDaysBetween(endDate.getTime() , startDate.getTime());
    }

    public static int getDaysBetween(long startMills, long endMills){
        long intervalMilli = Math.abs(endMills-startMills);
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }
    /**
     * 在给定时间的基础上移动响应的时间刻度
     * @param date 给定时间
     * @param scale  刻度大小
     * @param interval 刻度值
     * @return
     */
    private static Date add(Date date, int scale, int interval){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(scale, interval);
        return cal.getTime();
    }

    public static Date addDays(Date date, int interval){
        return add(date,Calendar.DATE, interval);
    }

    public static Date addHours(Date date, int interval){
        return add(date, Calendar.HOUR, interval);
    }

    public static Date addMinutes(Date date,  int interval){
        return add(date, Calendar.MINUTE, interval);
    }

    public static Date addSeconds(Date date, int interval){
        return add(date, Calendar.SECOND, interval);
    }

    public static Date addMonths(Date date, int interval){
        return add(date, Calendar.MONTH, interval);
    }

    public static Date addYears(Date date, int interval){
        return add(date, Calendar.YEAR, interval);
    }
}
