/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.fpt.hsts.common.IConsts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Util class for working with date, timestamp type
 */
public class DateUtils {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

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

    public static Date parseDate(final String dateString, final String datePattern) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("dateString[{}], pattern[{}]", dateString, datePattern);
            }
            final DateFormat dateFormat = new SimpleDateFormat(datePattern);
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            LOGGER.error("Error while parsing date data: {}", e.getMessage());
            return null;
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    /**
     * <p>
     * Plus/minus date a value of Calendar type(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, etc).
     * If minus date, amount should be a negative value.
     * </p>
     * @param date long
     * @param type int
     * @param amount int
     * @return {@link java.util.Date}
     */
    public static Date plusDateTime(final Date date, final int type, final int amount) throws Exception {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        try {
            cal.add(type, amount);
            return new java.sql.Date(cal.getTimeInMillis());
        } catch (Exception e) {
            throw e;
        }
    }
}
