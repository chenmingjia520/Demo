package com.lanyoumobility.mobility_webview.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {


    private static SimpleDateFormat startTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdfData1 = new SimpleDateFormat("MM-dd HH:mm");


    public static String getDataTime(String time) {
        try {
            Date startdate = startTimeFormat.parse(time);
            return sdfData1.format(startdate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static int getDayNum(String startDate, String endDate) {
        try {
            Date startdate = startTimeFormat.parse(startDate);
            Date enddate = startTimeFormat.parse(endDate);
            return getDateDayNum(startdate, enddate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int getDateDayNum(Date startDate, Date endDate) {
        try {
            double num = (double) (endDate.getTime() - startDate.getTime()) / (3600 * 24 * 1000);
            return (int) (Math.ceil(num));
        } catch (Exception e) {
        }
        return 0;
    }


    public static String getDatePoor(Date endDate,Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 得到几天前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }


    /**
     * String   转 Date;
     * @param str
     * @return
     */

    public static Date StringToDate(String str){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        Date date= null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    /**
     * String   转 Date;
     * @param str
     * @return
     */

    public static Date StringToDate1(String str){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * Data转String
     * @param date
     * @return
     */
    public static String DateToString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=sdf.format(date);
        return str;
    }


    /**
     * Data转String
     * @param date
     * @return
     */
    public static String definitionDate2(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=sdf.format(date);
        return str;
    }


    /**
     * Data转String
     * @param date
     * @return
     */
    public static String definitionDate1(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日 HH:mm");
        String str=sdf.format(date);
        return str;
    }

}
