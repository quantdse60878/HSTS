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
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.repo.AccountRepo;
import vn.edu.fpt.hsts.web.session.UserSession;

import java.util.Arrays;
import java.util.List;

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
                userSession.setRole(account.getRole().getName());
                userSession.setEmail(account.getEmail());
                return account;
            }
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public Account changePassword(final String username, final String oldPassword, final String newPassword){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("username[{}], oldPassword[{}], newPassword[{}]", username, oldPassword, newPassword);
            final Account account = accountRepo.findByUsernameAndPassword(username, oldPassword);
            if(null != account) {
                account.setPassword(newPassword);
                accountRepo.save(account);
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

    public String buildUniqueUsername(final String fullname) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("fullname", fullname);

            if (StringUtils.isEmpty(fullname)) {
                // Throws wrong username
                final String message = String.format("The input fullname[{}] is wrong format", fullname);
                throw new BizlogicException(message);
            }
            final String[] tmp = fullname.split(" ");
            final List<String> parts = Arrays.asList(tmp);

            // Build result
            final StringBuilder result = new StringBuilder();

            // Append lastname to result
            result.append(parts.get(parts.size() - 1));
            for (int i = 0; i < parts.size() - 1; i++) {
                char c = parts.get(i).charAt(0);
                result.append(c);
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("result[{}]", result.toString());
            }

            // Find  username same prefix in db
            List<Account> accounts = accountRepo.findByUsernameStartWith(result.toString() + "%");
            // If contain username same prefix, apppend an integer to result
            if (null != accounts && !accounts.isEmpty()) {
                result.append(accounts.size() + 1);
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("New account[{}]", result.toString());
            }
            return result.toString();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
