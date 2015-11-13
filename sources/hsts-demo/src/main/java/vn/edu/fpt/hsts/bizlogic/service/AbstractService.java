/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.web.session.UserSession;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The abstract service, use for implement common method.
 */
@Component
public class AbstractService {

    /**
     * The logger.
     */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    /**
     * The {@link UserSession}.
     */
    @Autowired
    private UserSession userSession;

    /**
     * The min value map.
     */
    protected static Map<String, Float> minVals = new HashMap<String, Float>();

    /**
     * The max value map.
     */
    protected static Map<String, Float> maxVals = new HashMap<String, Float>();

    /**
     * The string list contains all name of parameter will be convert to int.
     */
    protected static List<String> intParams = new ArrayList<String>();

    /**
     * Get the userSession attribute.
     * @return the userSession
     */
    protected UserSession getUserSession() {
        return userSession;
    }

    /**
     * Set the userSession attribute.
     * @param userSession the userSession to set
     */
    @Autowired
    protected void setUserSession(final UserSession userSession) {
        this.userSession = userSession;
    }

    /**
     * <p>
     * Get current account.
     * </p>
     * @return {@link Account}
     */
    protected Account getCurrentAccount() {
        final Account account = new Account();
        account.setId(userSession.getId());
        account.setUsername(userSession.getUsername());
        return account;
    }

    protected String getUploadDirectory() {
        return System.getProperty("user.dir") + File.separator + "uploadFiles";
    }
}
