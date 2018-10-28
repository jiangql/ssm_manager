package com.qinglinJiang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String Date2String(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = sdf.format(date);
        return str;
    }

    public static Date String2Date(String dateString) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(dateString);
        return date;
    }
}
