/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Util class for working with date, timestamp type
 */
public class DateUtils {

    public static final String DATE_PATTERN_1 = "dd/MM/yyyy";

    public static final String DATE_PATTERN_2 = "dd-MM-yyyy";

    public static String formatDate(final Date date, final String datePattern) {
        final DateFormat dateFormat = new SimpleDateFormat(datePattern);
        return dateFormat.format(date);
    }

    public static Date formatDate(final Date date, final boolean isEndOfDay) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(isEndOfDay) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
        return calendar.getTime();
    }
}
