/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/7/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class AuthenService extends AbstractService{

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenService.class);

    public String randomPassword() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        String random = RandomStringUtils.random(8, true, true);
        LOGGER.info(random);
        try {
            return random;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public String hashMD5(final String password) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("password[{}]", password);
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }
}
