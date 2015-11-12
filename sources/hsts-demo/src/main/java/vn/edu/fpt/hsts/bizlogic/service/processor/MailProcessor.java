/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/13/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class MailProcessor extends AbstractLockProcessor {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MailProcessor.class);

    /**
     * The queue of account must be sending email.
     */
    private Queue<Account> queue = new LinkedList<Account>();

    public void offer(final Account account) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            queue.offer(account);
        } finally {
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    public Account poll() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            return queue.poll();
        } finally {
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    public Account peek() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            return queue.peek();
        } finally {
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    public void remove(final Account account) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            queue.remove(account);
        } finally {
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    public boolean isEmpty() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            lockWrite();
            return queue.isEmpty();
        } finally {
            unlockWrite();
            LOGGER.debug(IConsts.END_METHOD);
        }
    }
}
