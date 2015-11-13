package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.common.IConsts;

/**
 * Created by Aking on 10/28/2015.
 */
@Controller
public class DrManagerController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DrManagerController.class);

    @RequestMapping(value = "regimens", method = RequestMethod.GET)
    public ModelAndView regimensPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("regimens");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


}
