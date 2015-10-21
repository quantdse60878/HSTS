package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AccountService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;

/**
 * Created by Aking on 10/21/2015.
 */
@Controller
public class NutritionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NutritionController.class);
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "createNutrition", method = RequestMethod.GET)
    public ModelAndView createNutritionPage(@RequestParam("patientID") final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutrition");


            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "createNutrition", method = RequestMethod.POST)
    public ModelAndView createNutrition(@RequestParam("patientID") final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutrition");


            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
