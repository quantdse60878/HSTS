/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/9/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service.processor;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.DateUtils;

import java.util.Date;

@Component
public class MedicalOrderNumberProcessor extends AbstractLockProcessor {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalOrderNumberProcessor.class);

    /**
     * Cache.
     */
    private ImmutablePair<Date, Integer> orderNumber =
            new ImmutablePair<Date, Integer>(DateUtils.roundDate(new Date(), false), 1);

    public Integer nextOrderNumber() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            lockRead();
            if (isExpired()) {
                resetCounter();
            }
            final int result = orderNumber.getRight();
            orderNumber = new ImmutablePair<Date, Integer>(DateUtils.roundDate(new Date(), false), result + 1);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            unlockRead();
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    private boolean isExpired() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            final Date currentDate = DateUtils.roundDate(new Date(), false);
            final Date cache = orderNumber.getLeft();
            if (currentDate.equals(cache)) {
                return false;
            }
            return true;
        }finally {
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }


    private void resetCounter() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            lockRead();
            orderNumber = new ImmutablePair<Date, Integer>(DateUtils.roundDate(new Date(), false), 1);
        } finally {
            unlockRead();
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }
}
