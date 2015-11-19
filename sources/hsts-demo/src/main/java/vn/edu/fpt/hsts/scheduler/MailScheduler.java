/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/13/2015.
 */
package vn.edu.fpt.hsts.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.edu.fpt.hsts.bizlogic.service.MailService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.DateUtils;

import java.util.Date;

@Component
public class MailScheduler {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MailScheduler.class);

    /**
     * The {@link MailService}.
     */
    @Autowired
    private MailService mailService;

    @Scheduled(fixedDelay = 300000)
    private void schedulerTask() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("Starting mail scheduler at : {}", DateUtils.formatDate(new Date(), DateUtils.DATE_PATTERN_4));
            // Call service
            mailService.mailingScheduler();
            LOGGER.info("End mail scheduler at : {}", DateUtils.formatDate(new Date(), DateUtils.DATE_PATTERN_4));
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
