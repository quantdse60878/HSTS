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
}
