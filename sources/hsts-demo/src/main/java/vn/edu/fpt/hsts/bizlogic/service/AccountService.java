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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.AccountModel;
import vn.edu.fpt.hsts.bizlogic.model.AccountPageModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.repo.AccountRepo;
import vn.edu.fpt.hsts.web.session.UserSession;

@Service
public class AccountService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);


    /**
     * The {@link UserSession}.
     */
    @Autowired
    private UserSession userSession;

    /**
     * The {@link AccountRepo}.
     */
    @Autowired
    private AccountRepo accountRepo;

    public Account checkLogin(final String username, final String password) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("username[{}], password[{}]", username, password);
            final Account account = accountRepo.findByUsernameAndPassword(username, password);
            if(null != account) {
                userSession.setId(account.getId());
                userSession.setUsername(account.getUsername());
                userSession.setRole(account.getRole().getRoleName());
                userSession.setEmail(account.getEmail());
                return account;
            }
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public AccountModel loginRest(final String username, final String password) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("username[{}], password[{}]", username, password);
            final Account account = accountRepo.findByUsernameAndPassword(username, password);
            if(null == account) {
                return null;
            }
            LOGGER.debug("Login OK with email[{}]", account.getEmail());
            final AccountModel model = new AccountModel();
            model.fromEntity(account);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public AccountPageModel paging(final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("page[{}], pageSize[{}]", page, pageSize);
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            final Page<Account> accountPage = accountRepo.findAll(pageRequest);
            final AccountPageModel pageModel = new AccountPageModel(accountPage);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
