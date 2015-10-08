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
import vn.edu.fpt.hsts.bizlogic.service.AccountService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;

/**
 * Register controller, for processing Register patient, account.
 */
@Controller
public class RegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;


    /**
     * The {@link PatientService}
     */
    @Autowired
    private PatientService patientService;

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
     * @param patientName
     * @param birthday
     * @param gender
     * @param weight
     * @param height
     * @param status
     * @param doctor
     * @return
     */
    @RequestMapping(value = "registerPatient", method = RequestMethod.POST)
    public ModelAndView registerPatient(/*@RequestParam("patientName") final String patientName,
                                        @RequestParam("birthday") final String birthday,
                                        @RequestParam("gender") final String gender,
                                        @RequestParam("weight") final int weight,
                                        @RequestParam("height") final int height,
                                        @RequestParam("doctor") final String doctor,
                                        @RequestParam("medicalHistory") final String medicalHistory,
                                        @RequestParam("symptoms") final String symptoms*/) throws BizlogicException, BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
//            LOGGER.info("patientName[{}], birthday[{}], gender[{}], weight[{}], height[{}], doctor[{}], medicalHistory[{}], symptoms[{}]");

            ModelAndView mav = new ModelAndView();
            mav.setViewName("registerPatient");
            /**
             * Create new patient
             */
            patientService.createPatient();

            //create notify
            //set name of action
            mav.addObject("METHOD", "Register Patient");
            //set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "info");
            //set message notify
            mav.addObject("MESSAGE","Success");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
