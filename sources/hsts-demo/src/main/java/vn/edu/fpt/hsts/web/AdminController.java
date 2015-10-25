package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.service.AccountService;
import vn.edu.fpt.hsts.common.IConsts;

/**
 * Created by Aking on 10/25/2015.
 */
@Controller
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;

    /**
     * Create main page admin.
     * @return
     */
    @RequestMapping(value = "adminAccs", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("adminlistuser");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
