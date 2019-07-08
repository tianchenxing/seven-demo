package com.tong.sevencommon.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 * Created by yc on 2017/3/21.
 */
public class DateKit {
    private static final Logger logger = LoggerFactory.getLogger(DateKit.class);
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDH = "yyyy-MM-dd HH:";
    public static final String YMD = "yyyy-MM-dd";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String HM = "HH:mm";
    public static final String Y = "yyyy";
    private DateKit() {

    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Timestamp nowTs() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String getDateStr(Long millisecond) {
        Date date = new Date(millisecond);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Long getMillSecond(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date.getTime();
    }

    /**
     * 是未来
     *
     * @param date
     * @return
     */
    public static boolean isFuture(Date date) {
        Date now = new Date();
        return now.compareTo(date) < 0;
    }

    /**
     * 是过去
     *
     * @param date
     * @return
     */
    public static boolean isPast(Date date) {
        Date now = new Date();
        return now.compareTo(date) > 0;
    }

    /**
     * 将字符串转为指定格式的Date
     *
     * @param dateStr
     * @return
     */
    public static Date str2Date(String dateStr, String dateFormat) throws ParseException{
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        Date parse = sf.parse(dateStr);
        return  parse;
    }




    /**
     * 增加固定的天数
     *
     * @param currentDate 当前的日期
     * @param addDays     增加的天数
     * @return 增加固定天数后的天数
     */
    public static Date addDays(Date currentDate, int addDays) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, addDays);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(cal.getTime());
        return formatter.parse(dateString);
    }

    /**
     * 时间比较
     *
     * @param nowTime
     * @param endTime
     * @return
     */
    public static boolean compareTime(Date nowTime, Date endTime) {
        return endTime.compareTo(nowTime) >= 0 ? true : false;
    }



    public static String date2Str(Date date) {
        java.text.DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
        String restDateStr = null;
        restDateStr = formate.format(date);
        return restDateStr;
    }

    public static String date2StrFormat(Date date) {
        java.text.DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
        String restDateStr = null;
        restDateStr = formate.format(date);
        return restDateStr;
    }

    public static String dateConvertToStrFormat(Date date, String format) {
        SimpleDateFormat formate = new SimpleDateFormat(format);
        String restDateStr = null;
        restDateStr = formate.format(date);
        return restDateStr;
    }

    public static Date stringToDate(String dateStr){
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            logger.error("String类型转换Date类型失败！");
        }
        return date;
    }

    public static Date stringToDate(String dateStr,String format){
        java.text.DateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            logger.error("String类型转换Date类型失败！");
        }
        return date;
    }


    public static int getMonthHour(int month, int year) {
        int daysNum = getDaysByYearMonth(month, year);
        return daysNum * 24;
    }

    public static int getDaysByYearMonth(int month, int year) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }



    /**
     * 获取当前日期月份的第一天日期
     * @param date
     * @return
     */
    public static String getFirstDayOfMonth(Date date) {
        int year = DateKit.getYear(date);
        int month = DateKit.getMonth(date);
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //设置日历中天数为1
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(d);
        return firstDayOfMonth;
    }





    public static int getMonth(Date date){
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        return a.get(Calendar.MONTH)+1;
    }

    public static int getYear(Date date){
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        return a.get(Calendar.YEAR);
    }

    public static int getDay(Date date){
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        return a.get(Calendar.DAY_OF_MONTH);
    }





    /**
     * 获取年的第一天日期
     *
     * @param year
     * @return
     */
    public static String getFirstDateYear(int year) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, 0);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDateOfYear = sdf.format(cal.getTime());

        return firstDateOfYear;
    }

    /**
     * 获取当前日期所在的年份的第一天日期
     *
     * @param
     * @return
     */
    public static String getFirstDateYearFromDate(Date date) {
        int year = DateKit.getYear(date);
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, 0);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDateOfYear = sdf.format(cal.getTime());

        return firstDateOfYear;
    }

    /**
     * 获取某个月有多少天
     */
    public static int getDayOfMonth(String strDate){
        Date date = stringToDate(strDate);
        int year = DateKit.getYear(date);
        int month = DateKit.getMonth(date);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month-1);
        int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }


    /**
     * 获取年月"yyyy-MM"
     * @param date
     * @return
     */
    public static String yearMonth(Date date) {
        java.text.DateFormat formate = new SimpleDateFormat("yyyy-MM");
        String restDateStr = null;
        restDateStr = formate.format(date);
        return restDateStr;
    }
    /**
     *
     * @param date
     * @param hour
     * @return 获取指定  日期小时
     */
    public static Date precateTime(Date date, Integer hour) {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.add(Calendar.HOUR_OF_DAY, hour);
        return currentDate.getTime();
    }

    /**
     * 获取HH时间格式
     * @param date
     * @return
     */
    public static String getHHformat(Date date){
        SimpleDateFormat hh = new SimpleDateFormat("HH");
        return  hh.format(date);
    }

    /**
     * 获取yyyy格式字符串
     * @param date
     * @return
     */
    public static String getYearFormat(Date date) {
        java.text.DateFormat formate = new SimpleDateFormat("yyyy");
        return formate.format(date);
    }
    /**
     * 获取yyyy格式字符串
     * @param date
     * @return
     */
    public static String getHmsFormat(Date date) {
        java.text.DateFormat formate = new SimpleDateFormat("HH:mm:ss");
        return formate.format(date);
    }
    /**
     * 获取mm时间格式
     * @param date
     * @return
     */
    public static Integer getMMformat(Date date){
        SimpleDateFormat mm = new SimpleDateFormat("mm");
        return  Integer.parseInt(mm.format(date));
    }
    /**
     * 获取mm时间格式
     * @param date
     * @return
     */
    public static String getMMString(Date date){
        SimpleDateFormat mm = new SimpleDateFormat("mm");
        return  mm.format(date);
    }

    /**
     * 将long型时间转换为自定义格式的时间类型
     * @param millisecond
     * @return
     */
    public static Date getDate(Long millisecond,String format) throws ParseException {
        Date date = new Date(millisecond);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String sf = simpleDateFormat.format(date);
        return simpleDateFormat.parse(sf);
    }

    /**
     * 将Date转换为目标格式Date
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date dateToDateFormart(Date date,String format) throws ParseException{
        Date d = new Date(date.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String sf = simpleDateFormat.format(d);

        return simpleDateFormat.parse(sf);
    }

    /**
     * 将时间分为0，15,30,60样式的刻钟时间
     *
     * @param dateTime
     */
    public static  Map<String, String> dualQuarterTime(Date dateTime){

        String startTime =  DateKit.dateConvertToStrFormat(dateTime, "yyyy-MM-dd HH:");
        String endTime =  DateKit.dateConvertToStrFormat(dateTime, "yyyy-MM-dd HH:");
        Integer fen = getMMformat(dateTime);
        if(0 <= fen && 15 > fen){
            Date dateT = precateTime(dateTime, -1);
            String s = dateConvertToStrFormat(dateT, "yyyy-MM-dd HH:");
            startTime = s.concat("45:00");
            endTime = endTime.concat("00:00");
        }else if(15 <= fen && 30 >fen){
            startTime =  startTime.concat("00:00");
            endTime =  endTime.concat("15:00");
        }else if(30 <= fen && 45 > fen){
            startTime =  startTime.concat("15:00");
            endTime =  endTime.concat("30:00");
        }else if(45 <= fen){
            startTime =  startTime.concat("30:00");
            endTime =  endTime.concat("45:00");
        }
        Map<String, String> sm = new HashMap<String, String>();
        sm.put("startTime",startTime);
        sm.put("endTime",endTime);
        return sm;
    }
    /**
     *
     * @param date
     * @param minute
     * @return 获取指定分钟后的时间
     */
    public static Date precateMinute(Date date, Integer minute) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);

        currentDate.add(Calendar.MINUTE, minute);
        currentDate.set(Calendar.SECOND, 0);
        String ff = sdf.format(currentDate.getTime());

        return sdf.parse(ff);
    }

    /**
     * 获取当前时间之前的刻钟时间
     * @param dateTime
     * @return
     * @throws ParseException
     */
    public static Date quarterTime(Date dateTime) throws ParseException{
        Integer fen = getMMformat(dateTime);
        String s = null;
        if(0 <= fen && 15 > fen){
            s = dateConvertToStrFormat(dateTime, "yyyy-MM-dd HH:").concat("00:00");
        }else if(15 <= fen && 30 >fen){
            s = dateConvertToStrFormat(dateTime, "yyyy-MM-dd HH:").concat("15:00");
        }else if(30 <= fen && 45 > fen){
            s = dateConvertToStrFormat(dateTime, "yyyy-MM-dd HH:").concat("30:00");
        }else if(45 <= fen){
            s = dateConvertToStrFormat(dateTime, "yyyy-MM-dd HH:").concat("45:00");
        }
        return  str2Date(s,YMDHMS)  ;
    }

    /**
     * 获取时间秒
     * @param date
     * @return
     */
    public static Integer getSeconds(Date date){
        Date d1 = new Date(date.getTime());
        SimpleDateFormat sf = new SimpleDateFormat("ss");
        String ss = sf.format(d1);
        return Integer.parseInt(ss);
    }
    /**
     * 获取下一天0点时间
     * @param date
     * @return
     */
    public static Date getNextDayZero(Date date) {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY,24);
        currentDate.set(Calendar.MINUTE,0);
        currentDate.set(Calendar.SECOND,0);
        return currentDate.getTime();
    }
    /**
     *
     * @param date
     * @param hour
     * @return 获取指定小时后整点时间
     */
    public static Date getHHZero(Date date, Integer hour) {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.add(Calendar.HOUR_OF_DAY, hour);
        currentDate.set(Calendar.MINUTE,0);
        currentDate.set(Calendar.SECOND,0);
        return currentDate.getTime();
    }

    /**
     *
     * @Author mars
     * @Description 获取录入年份有多少天
     */
    public static int getCurrentDayOfYear(int year){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.YEAR, year);
        int dayOfYear = c.getActualMaximum(Calendar.DAY_OF_YEAR);
        return dayOfYear;
    }

    /**
     * 日期格式正则校验：
     * @param timeStr : yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean validateTimeFormmatWithTimeStr(String timeStr) {
        String format = "((19|20)[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) "
                + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 获取前一小时日期
     *
     * @param reportDay
     * @return
     */
    public static Date getOneHourBefore(Date reportDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(reportDay);
        cal.add(Calendar.HOUR_OF_DAY, -1);//1小时前
        return cal.getTime();
    }

    /**
     * 获取当前日期的后一天的日期
     *
     * @param reportDay
     * @return
     */
    public static String getTomorrow(String reportDay) {
        Date t = DateKit.stringToDate(reportDay);
        Calendar cal = Calendar.getInstance();
        cal.setTime(t);
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = sdf.format(cal.getTime());
        return yesterday;
    }

    /**
     * 获取当前日期的前一天的日期
     *
     * @param reportDay
     * @return
     */
    public static String getYesterday(String reportDay) {
        Date t = DateKit.stringToDate(reportDay);
        Calendar cal = Calendar.getInstance();
        cal.setTime(t);
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = sdf.format(cal.getTime());
        return yesterday;
    }


    /**
     * 切换成时分秒的格式
     *
     * @param times
     * @return
     * @throws Exception
     */
    public static String changeDataToHMS(String times) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        String now = new SimpleDateFormat("HH:mm:ss").format(date);
        return now;
    }

    public static String changeDayToHMS(String times) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM").parse(times);
        String now = new SimpleDateFormat("HH:mm:ss").format(date);
        return now;
    }
    public static String changeDayToYmd(Long times) throws Exception {
        Date date = new Date(times);
        String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return now;
    }
    /**
     * 将日期转换成年月格式
     *
     * @param times
     * @return
     * @throws Exception
     */
    public static String changeDataToYearM(String times) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(times);
        String now = new SimpleDateFormat("YYYY-MM").format(date);
        return now;
    }

    /**
     * 转换成年月日的形式
     *
     * @param times
     * @return
     * @throws Exception
     */
    public static String changeDataToYearMD(String times) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        String now = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date);
        return now;
    }

    /**
     * 获取当前月第一天的15:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getFristDayByMonth(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(times);
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.HOUR,15);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(c.getTime());
        return firstDay;
    }
    /**
     *  获取前一天日期的 15:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getBeforDayTimes(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 15:00:00");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY,-24);
        String lastDay = format.format(ca.getTime());
        return lastDay;
    }
    /**
     *  获取当天天日期的 15:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getDayTimes(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 15:00:00");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        String lastDay = format.format(ca.getTime());
        return lastDay;
    }
    /**
     *  获取前一天日期的 20:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getBeforDayTime(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 20:00:00");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY,-24);
        String lastDay = format.format(ca.getTime());
        return lastDay;
    }
    /**
     *  获取当天天日期的 20:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getDayTime(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 20:00:00");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        String lastDay = format.format(ca.getTime());
        return lastDay;
    }
    /**
     *  获取当天天日期的 20:00 的时间戳
     * @param times
     * @return
     * @throws Exception
     */
    public static Long getDayTimeLong(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 20:00:00");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        String lastDay = format.format(ca.getTime());
        Date date1 = format.parse(lastDay);
        long time = date1.getTime();
        return time;
    }
    /**
     *  六个月前的第一天的 00:00:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getBeforSixMonth(String times) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        //获取某月最大天数
        ca.setTime(date);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        ca.set(Calendar.HOUR_OF_DAY,24);
        ca.add(Calendar.MONTH, -6);
        String lastDays = now.format(ca.getTime());
        return lastDays;
    }

    /**
     * 当前月的最后一天的 23:59:59
     * @param times
     * @return
     * @throws Exception
     */
    public static String getMonthLastDay(String times) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        //获取某月最大天数
        ca.setTime(date);
        int lastDay = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
        ca.set(Calendar.MINUTE,59);
        ca.set(Calendar.SECOND,59);
        ca.set(Calendar.HOUR_OF_DAY,23);
        ca.set(Calendar.DAY_OF_MONTH,lastDay);
        String lastDays = now.format(ca.getTime());
        return lastDays;
    }
    /**
     * 当前月的最后一天的 23:59:59
     * @param times
     * @return
     * @throws Exception
     */
    public static Long getMonthLastDayToNum(String times) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        //获取某月最大天数
        ca.setTime(date);
        int lastDay = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
        ca.set(Calendar.MINUTE,59);
        ca.set(Calendar.SECOND,59);
        ca.set(Calendar.HOUR_OF_DAY,23);
        ca.set(Calendar.DAY_OF_MONTH,lastDay);
        String lastDays = now.format(ca.getTime());
        Date date1 = now.parse(lastDays);
        long time = date1.getTime();
        return time;
    }
    /**
     *  获取当前月的最后一天的 15:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getLastDayByMonth(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        ca.set(Calendar.HOUR,15);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDay = format.format(ca.getTime());
        return lastDay;
    }
    /**
     *  获取当前月的最后一天
     * @param times
     * @return
     * @throws Exception
     */
    public static String getLastDayByThisMonth(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDay = format.format(ca.getTime());
        return lastDay;
    }


    /**
     * 获取当前月第一天
     * @param times
     * @return
     * @throws Exception
     */
    public static String getFristDayByThisMonth(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(times);
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(c.getTime());
        return firstDay;
    }

    /**
     *   获取当前日期上个月的最后一天的15:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getLastDayByBeforMonth(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(times);
        //获取当前月第一天：
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.MINUTE,0);
        cale.set(Calendar.SECOND,0);
        cale.set(Calendar.HOUR,15);
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        String beforLastDay = format.format(cale.getTime());
        return beforLastDay;
    }
    /**
     *  获取前29天日期的 20:00
     * @param times
     * @return
     * @throws Exception
     */
    public static String getBeforThirtyDayTimes(String  times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 20:00:00");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(times);
        //获取当前月第一天：
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH,-29);
        String lastDay = format.format(ca.getTime());
        return lastDay;
    }

    public static Date changeStringToDate(String times)throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(times);
        return  date;
    }
    public static void main(String[] args)throws  Exception{

        String  now = DateKit.getBeforDayTimes("2019-10-21 14:09:09");
        System.out.println("===============first:"+now);

        Date  last = DateKit.changeStringToDate("2019-10-21");
        System.out.println("===============first:"+last);

        String lastDay = DateKit.getLastDayByThisMonth("2019-10-21");
        System.out.println("-----2------lastDay:"+lastDay);
    }

}
