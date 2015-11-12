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
import vn.edu.fpt.hsts.bizlogic.model.UnitOfFoodModel;
import vn.edu.fpt.hsts.bizlogic.service.*;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

import java.util.List;

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

    @Autowired
    AnalyticFood analyticFood;

    @RequestMapping(value = "nutriPatients", method = RequestMethod.GET)
    public ModelAndView nutriPatientsPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutriPatients");

            // Get list patients
            List<Patient> patientList = patientService.getPatientByApponitmentDate();
            LOGGER.info("listpatiens: " + patientList.size());
            mav.addObject("LISTPATIENTS", patientList);

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "createNutrition", method = RequestMethod.GET)
    public ModelAndView createNutritionPage(@RequestParam("patientID") final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutrition");
            Patient patient = patientService.getPatientByID(patientID);
            mav.addObject("PATIENT", patient);

            // Find Appointment
            Appointment appointment = appointmentService.findEntryAppointmentByPatientId(patientID);
            mav.addObject("APPOINTMENT", appointment);

            FoodIngredientModel foodIngredientModel = foodIngredientService.createFoodIngredientModelFromData(appointment);
            mav.addObject("model", foodIngredientModel);

            // Find PreventionCheck
            PreventionCheck preventionCheck = preventionCheckService.findLastPreventionCheckFromAppointment(appointment);
            mav.addObject("PREVENTIONCHECK", preventionCheck);

            // Get Medicalhistory
            String medicalHistory = patientService.getPatientHistory(appointment.getMedicalRecord().getMedicalHistory());
            mav.addObject("MEDI_HIS", medicalHistory);

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "createNutritionWithVoice", method = RequestMethod.GET)
    public ModelAndView createNutritionWithVoice(@RequestParam("patientID") final int patientID,
                                                 @RequestParam("bf") final String breakfast,
                                                 @RequestParam("btm") final String breaktimeMorning,
                                                 @RequestParam("lunch") final String lunch,
                                                 @RequestParam("bta") final String breaktimeAfternoon,
                                                 @RequestParam("dinner") final String dinner,
                                                 @RequestParam("en") final String eatLateAtNight,
                                                 @RequestParam("animalFat") final float animalFat,
                                                 @RequestParam("animalProtein") final float animalProtein,
                                                 @RequestParam("calcium") final float calcium,
                                                 @RequestParam("lipid") final float lipid,
                                                 @RequestParam("starch") final float starch,
                                                 @RequestParam("protein") final float protein,
                                                 @RequestParam("fiber") final float fiber,
                                                 @RequestParam("iron") final float iron,
                                                 @RequestParam("sodium") final float sodium,
                                                 @RequestParam("vitaminB1") final float vitaminB1,
                                                 @RequestParam("vitaminB2") final float vitaminB2,
                                                 @RequestParam("vitaminC") final float vitaminC,
                                                 @RequestParam("vitaminPP") final float vitaminPP,
                                                 @RequestParam("zinc") final float zinc) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutrition");
            Patient patient = patientService.getPatientByID(patientID);
            mav.addObject("PATIENT", patient);

            // Find Appointment
            Appointment appointment = appointmentService.findEntryAppointmentByPatientId(patientID);
            mav.addObject("APPOINTMENT", appointment);

            FoodIngredientModel foodIngredientModel = foodIngredientService.createFoodIngredientModelFromData(appointment);
            mav.addObject("model", foodIngredientModel);

            // Find PreventionCheck
            PreventionCheck preventionCheck = preventionCheckService.findLastPreventionCheckFromAppointment(appointment);
            mav.addObject("PREVENTIONCHECK", preventionCheck);

            // Get Medicalhistory
            String medicalHistory = patientService.getPatientHistory(appointment.getMedicalRecord().getMedicalHistory());
            mav.addObject("MEDI_HIS", medicalHistory);

            FoodIngredientModel voiceModel = new FoodIngredientModel();
            voiceModel.setBreakfast(breakfast);
            voiceModel.setBreakTimeMorning(breaktimeMorning);
            voiceModel.setLunch(lunch);
            voiceModel.setBreakTimeAfternoon(breaktimeAfternoon);
            voiceModel.setDinner(dinner);
            voiceModel.setEatLateAtNight(eatLateAtNight);
            voiceModel.setFat(lipid + "");
            voiceModel.setStarch(starch + "");
            voiceModel.setProtein(protein + "");
            voiceModel.setFiber(fiber + "");
            voiceModel.setAnimalFat(animalFat + "");
            voiceModel.setAnimalProtein(animalProtein + "");
            voiceModel.setCalcium(calcium + "");
            voiceModel.setIron(iron + "");
            voiceModel.setSodium(sodium + "");
            voiceModel.setVitaminB1(vitaminB1 + "");
            voiceModel.setVitaminB2(vitaminB2 + "");
            voiceModel.setVitaminC(vitaminC + "");
            voiceModel.setVitaminPP(vitaminPP + "");
            voiceModel.setZinc(zinc + "");
            mav.addObject("voiceModel", voiceModel);
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "createNutritionByVoice", method = RequestMethod.GET)
    public ModelAndView createNutritionVoicePage(@RequestParam("patientID") final int patientID){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nutritionReviewNutrition");

            Patient patient = patientService.getPatientByID(patientID);
            mav.addObject("PATIENT", patient);

            mav.addObject("listUnitOfFood", analyticFood.getAllDataOfFood());

            // Find Appointment
            Appointment appointment = appointmentService.findEntryAppointmentByPatientId(patientID);
            mav.addObject("APPOINTMENT", appointment);

            // Find PreventionCheck
            PreventionCheck preventionCheck = preventionCheckService.findLastPreventionCheckFromAppointment(appointment);
            mav.addObject("PREVENTIONCHECK", preventionCheck);

            // Get Medicalhistory
            String medicalHistory = patientService.getPatientHistory(appointment.getMedicalRecord().getMedicalHistory());
            mav.addObject("MEDI_HIS", medicalHistory);
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

            // Get Medicalhistory
            String medicalHistory = patientService.getPatientHistory(appointment.getMedicalRecord().getMedicalHistory());
            mav.addObject("MEDI_HIS", medicalHistory);

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
