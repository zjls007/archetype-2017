package com.cy.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zxj on 2018/2/2.
 */
public class DateUtil {

    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyy-MM-dd";

    /**
     * 只比较日期
     * @param d1
     * @param d2
     * @return
     */
    public static boolean dateEqual(Date d1 , Date d2) {
        d1 = d1 == null ? getYMDDate("1970-01-01") : d1;
        d2 = d2 == null ? getYMDDate("1970-01-01") : d2;
        return getDate(d1).compareTo(getDate(d2)) == 0;
    }

    public static String getYMDStr(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(YMD);
        return df.format(date);
    }

    public static boolean dateTimeEqual(Date d1 , Date d2) {
        d1 = d1 == null ? getYMDDate("1970-01-01 00:00:00") : d1;
        d2 = d2 == null ? getYMDDate("1970-01-01 00:00:00") : d2;
        return d1.compareTo(d2) == 0;
    }

    public static Date addDay(Date d1 , int days) {
        if (d1 == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Date getYMDDate(String dateStr){
        DateFormat df = new SimpleDateFormat(YMD);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date getDate(Date dateTime) {
        if (dateTime == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
