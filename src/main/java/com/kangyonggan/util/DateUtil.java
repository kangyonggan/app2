package com.kangyonggan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public class DateUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parse(String source) throws Exception {
        return dateFormat.parse(source);
    }

    public static Date next(String source) throws Exception {
        Date date = parse(source);
        date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
        return date;
    }

}
