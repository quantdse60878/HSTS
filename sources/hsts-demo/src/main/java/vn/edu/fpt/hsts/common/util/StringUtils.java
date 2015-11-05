/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.fpt.hsts.common.IConsts;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The extesion of Apache StringUtils
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private static char[] SPECIAL_CHARACTERS = {
            'á','à','ả','ã','ạ','ă','ắ','ặ','ằ','ẳ','ẵ','â','ấ','ầ','ẩ','ẫ','ậ',
            'Á','À','Ả','Ã','Ạ','Ă','Ắ','Ặ','Ằ','Ẳ','Ẵ','Â','Ấ','Ầ','Ẩ','Ẫ','Ậ',
            'đ',
            'Đ',
            'é','è','ẻ','ẽ','ẹ','ê','ế','ề','ể','ễ','ệ',
            'É','È','Ẻ','Ẽ','Ẹ','Ê','Ế','Ề','Ể','Ễ','Ệ',
            'í','ì','ỉ','ĩ','ị',
            'Í','Ì','Ỉ','Ĩ','Ị',
            'ó','ò','ỏ','õ','ọ','ô','ố','ồ','ổ','ỗ','ộ','ơ','ớ','ờ','ở','ỡ','ợ',
            'Ó','Ò','Ỏ','Õ','Ọ','Ô','Ố','Ồ','Ổ','Ỗ','Ộ','Ơ','Ớ','Ờ','Ở','Ỡ','Ợ',
            'ú','ù','ủ','ũ','ụ','ư','ứ','ừ','ử','ữ','ự',
            'Ú','Ù','Ủ','Ũ','Ụ','Ư','Ứ','Ừ','Ử','Ữ','Ự',
            'ý','ỳ','ỷ','ỹ','ỵ',
            'Ý','Ỳ','Ỷ','Ỹ','Ỵ',
    };

    private static char[] REPLACEMENTS = {
            'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a',
            'A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A',
            'd',
            'D',
            'e','e','e','e','e','e','e','e','e','e','e',
            'E','E','E','E','E','E','E','E','E','E','E',
            'i','i','i','i','i',
            'I','I','I','I','I',
            'o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o',
            'O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O',
            'u','u','u','u','u','u','u','u','u','u','u',
            'U','U','U','U','U','U','U','U','U','U','U',
            'y','y','y','y','y',
            'Y','Y','Y','Y','Y',
    };

    /**
     * Remove accent by character
     * @param ch
     * @return
     */
    public static char removeAcients(char ch) {
        int index = -1;

        for (int i = 0; i < SPECIAL_CHARACTERS.length; ++i) {
            if (SPECIAL_CHARACTERS[i] == ch) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            ch = REPLACEMENTS[index];
        }
        return ch;
    }

    /**
     * Remove accent by String
     * @param s
     * @return
     */
    public static String removeAcients(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAcients(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static String randomString(final int lenght) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            String random = RandomStringUtils.random(lenght, true, true);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(random);
            }
            return random;
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }
}
