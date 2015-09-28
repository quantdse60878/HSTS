package vn.edu.fpt.hsts.web;

/**
 * Created by Aking on 9/26/2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.service.UserService;
import vn.edu.fpt.hsts.common.IConsts;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.fpt.hsts.persistence.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Login controller, for processing login, logout.
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * The {@link UserService}.
     */
    @Autowired
    private UserService userService;

    /**
     * The login page mapping
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
    /**
     * The login mapping
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") final String username,
                              @RequestParam("password") final String password, HttpSession session) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            LOGGER.info("username[{}], password[{}]", username, password);
            User user = userService.checkLogin(username, password);
            if (user != null) {
                session.setAttribute("USER", user);
                mav.setViewName("home");
                return mav;
            }
            mav.setViewName("login");
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The logout mapping
     * @param session
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            session.invalidate();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
