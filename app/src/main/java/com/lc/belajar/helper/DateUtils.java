package com.lc.belajar.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eby on 08/09/16.
 */
public class DateUtils {
    public static String getYear(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }
}
