package com.jhy.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 * Created by JHy on 2019/04/25
 */
public class DateUtils {

    /**
     * 根据年龄判断年代
     * @param age
     * @return
     */
    public static String getYearbaseByAge(String age) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(new Date());
        canlendar.add(Calendar.YEAR, -Integer.valueOf(age));
        Date newdate = canlendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String newdateString = dateFormat.format(newdate);
        Integer newdateInteger = Integer.valueOf(newdateString);

        String yearbasetype = "未知";
        if (newdateInteger >= 1940 && newdateInteger < 1950) {
            yearbasetype = "40后";
        } else if (newdateInteger >= 1950 && newdateInteger < 1960) {
            yearbasetype = "50后";
        } else if (newdateInteger >= 1960 && newdateInteger < 1970) {
            yearbasetype = "60后";
        } else if (newdateInteger >= 1970 && newdateInteger < 1980) {
            yearbasetype = "70后";
        } else if (newdateInteger >= 1980 && newdateInteger < 1990) {
            yearbasetype = "80后";
        } else if (newdateInteger >= 1990 && newdateInteger < 2000) {
            yearbasetype = "90后";
        } else if (newdateInteger >= 2000 && newdateInteger < 2010) {
            yearbasetype = "00后";
        } else if (newdateInteger >= 2010) {
            yearbasetype = "10后";
        }
        return yearbasetype;
    }

    /**
     * 计算两个时间之间相差的天数
     * @param starttime
     * @param endTime
     * @param dateFormatstring
     * @return
     * @throws ParseException
     */
    public static int getDaysBetweenbyStartAndend(String starttime,String endTime,String dateFormatstring) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatstring);
        Date start = dateFormat.parse(starttime);
        Date end = dateFormat.parse(endTime);
        Calendar startcalendar = Calendar.getInstance();
        Calendar endcalendar = Calendar.getInstance();
        startcalendar.setTime(start);
        endcalendar.setTime(end);
        int days = 0;
        while(startcalendar.before(endcalendar)){
            startcalendar.add(Calendar.DAY_OF_YEAR,1);
            days += 1;
        }
        return days;
    }

    /**
     * 通过时间字符串获取其中的小时字符串
     * @param timevalue
     * @return
     * @throws ParseException
     */
    public static String gethoursbydate(String timevalue) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd hhmmss");
        Date time = dateFormat.parse(timevalue);
        dateFormat = new SimpleDateFormat("hh");
        String resulthour = dateFormat.format(time);
        return resulthour;
    }

}


