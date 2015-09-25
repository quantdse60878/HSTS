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
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.UserModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.User;
import vn.edu.fpt.hsts.persistence.repo.UserRepo;

import javax.transaction.Transactional;

@Service
public class UserService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * The {@link UserRepo}.
     */
    @Autowired
    private UserRepo userRepo;

    public User checkLogin(final String username, final String password) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("username[{}], password[{}]", username, password);
            final User user = userRepo.findByUsernameAndPassword(username, password);
            if(null != user) {
                return user;
            }
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public UserModel loginRest(final String username, final String password) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("username[{}], password[{}]", username, password);
            final User user = userRepo.findByUsernameAndPassword(username, password);
            if(null == user) {
                return null;
            }
            LOGGER.debug("Login OK with email[{}]", user.getEmail());
            final UserModel model = new UserModel();
            model.fromEntity(user);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
