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

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * The extesion of Apache StringUtils
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    public static String normalizeString(final String str) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            // TODO research
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("str[{}]", str);
            }
            String s1 = Normalizer.normalize(str, Normalizer.Form.NFKD);
            String regex = Pattern.quote("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");
            String s2 = new String(s1.replaceAll(regex, "").getBytes("ascii"), "ascii");
            return s2;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error while normalizing string[{}]", str);
            return null;
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }
}
