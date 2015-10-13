/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.web.session.UserSession;

/**
 * The abstract service, use for implement common method.
 */
@Component
public class AbstractService {

    /**
     * The {@link UserSession}.
     */
    @Autowired
    private UserSession userSession;

    /**
     * The value read from application.properties file
     */
    @Value("${hsts.version}")
    private int version;

    protected int getVersion() {
        return version;
    }

    protected void setVersion(final int version) {
        this.version = version;
    }

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
}
