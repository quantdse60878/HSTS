package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fpt.hsts.bizlogic.model.UserModel;
import vn.edu.fpt.hsts.bizlogic.model.UserPageModel;
import vn.edu.fpt.hsts.bizlogic.service.UserService;
import vn.edu.fpt.hsts.common.IConsts;

/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/16/2015.
 */
@Controller
public class UserController extends AbstractController {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * The {@link UserService}.
     */
    @Autowired
    private UserService userService;

    /**
     * The login mapping
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("username") final String username,
                     @RequestParam("password") final String password) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("username[{}], password[{}]", username, password);
            if (null != userService.checkLogin(username, password)) {
               return "ok";
            }
            return "fail";
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/rest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserModel restful(@RequestParam("username") final String username,
                             @RequestParam("password") final String password) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("username[{}], password[{}]", username, password);
            return userService.loginRest(username, password);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserPageModel paging(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("page[{}], pageSize[{}]", page, pageSize);
            return userService.paging(page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
