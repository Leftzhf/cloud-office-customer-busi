package com.cloud.office.customer.busi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/13 23:06
 */
public class DateToolUtil {

    public static String getWeekStringByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DateFormat df = new SimpleDateFormat("E"); // 使用E来获取星期几的名称
        return df.format(date);
    }

}
