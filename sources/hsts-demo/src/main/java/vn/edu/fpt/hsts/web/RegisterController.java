package vn.edu.fpt.hsts.web;

/**
 * Created by Aking on 9/27/2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.service.UserService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Register controller, for processing Register patient, account.
 */
@Controller
public class RegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    /**
     * The {@link UserService}.
     */
    @Autowired
    private UserService userService;

    /**
     * The register patient page mapping
     *
     * @return
     */
    @RequestMapping(value = "registerPatient", method = RequestMethod.GET)
    public ModelAndView registerPatientPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("registerPatient");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The register patient mapping
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "registerPatient", method = RequestMethod.POST)
    public ModelAndView registerPatient(@RequestParam("username") final String username,
                              @RequestParam("password") final String password, HttpSession session) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("registerPatient");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
