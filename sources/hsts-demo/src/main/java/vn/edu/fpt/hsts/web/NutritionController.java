package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.FoodIngredientModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AccountService;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.FoodIngredientService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.bizlogic.service.PreventionCheckService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

/**
 * Created by Aking on 10/21/2015.
 */
@Controller
public class NutritionController extends AbstractController{
    private static final Logger LOGGER = LoggerFactory.getLogger(NutritionController.class);
    @Autowired
    AccountService accountService;

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    FoodIngredientService foodIngredientService;

    @Autowired
    PreventionCheckService preventionCheckService;

    @RequestMapping(value = "createNutrition", method = RequestMethod.GET)
    public ModelAndView createNutritionPage(@RequestParam("patientID") final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutrition");
            Patient patient = patientService.getPatientByID(patientID);
            mav.addObject("PATIENT", patient);
            mav.addObject("model", new FoodIngredientModel());

            // Find Appointment
            Appointment appointment = appointmentService.findEntryAppointmentByPatientId(patientID);
            mav.addObject("APPOINTMENT", appointment);

            // Find PreventionCheck
            PreventionCheck preventionCheck = preventionCheckService.findLastPreventionCheckFromAppointment(appointment);
            mav.addObject("PREVENTIONCHECK", preventionCheck);

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "nutrition", method = RequestMethod.GET)
    public ModelAndView createNutrition(@RequestParam(value = "appointmentId") final int appointmentId,
                                        @ModelAttribute FoodIngredientModel foodIngredientModel) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutrition");
            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);
            mav.addObject("model", foodIngredientModel);
            LOGGER.info(foodIngredientModel.toString());
            boolean result = foodIngredientService.insertNewFoodIngredient(appointment,foodIngredientModel);
            if (result){
                notify(mav,result,"Create Nutrition", "Success");
            } else {
                notify(mav,result,"createNutrition", "Fail");
            }

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
