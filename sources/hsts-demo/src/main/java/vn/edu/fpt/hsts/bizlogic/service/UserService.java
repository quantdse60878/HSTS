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
import vn.edu.fpt.hsts.bizlogic.model.UserModel;
import vn.edu.fpt.hsts.bizlogic.model.UserPageModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.User;
import vn.edu.fpt.hsts.persistence.repo.UserRepo;
import vn.edu.fpt.hsts.web.session.UserSession;

@Service
public class UserService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    /**
     * The {@link UserSession}.
     */
    @Autowired
    private UserSession userSession;

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
                userSession.setId(user.getId());
                userSession.setUsername(user.getUsername());
                userSession.setRole(user.getRole().getName());
                userSession.setEmail(user.getEmail());
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

    public UserPageModel paging(final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("page[{}], pageSize[{}]", page, pageSize);
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            final Page<User> userPage = userRepo.findAll(pageRequest);
            final UserPageModel pageModel = new UserPageModel(userPage);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
