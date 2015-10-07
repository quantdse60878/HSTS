/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/7/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;

@Service
public class AuthenService extends AbstractService{

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenService.class);

    public String randomPassword() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // TODO random password
            return "ABCDEFGHI";
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
