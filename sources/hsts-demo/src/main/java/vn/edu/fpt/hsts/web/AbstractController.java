/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.NutritionModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticeResultModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.FoodIngredientService;
import vn.edu.fpt.hsts.bizlogic.service.FoodService;
import vn.edu.fpt.hsts.bizlogic.service.IllnessService;
import vn.edu.fpt.hsts.bizlogic.service.MedicalRecordDataService;
import vn.edu.fpt.hsts.bizlogic.service.MedicineService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.bizlogic.service.PracticeService;
import vn.edu.fpt.hsts.bizlogic.service.PreventionCheckService;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.Practice;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;
import vn.edu.fpt.hsts.web.config.ControllerParam;

import java.util.List;

public class AbstractController implements ControllerParam {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);
    /**
     * The {@link TreatmentService}.
     */
    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private IllnessService illnessService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PreventionCheckService preventionCheckService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private FoodIngredientService foodIngredientService;

    public void initDataPrescription(ModelAndView mav, Appointment appointment, PrescriptionModel prescriptionModel){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("mav[{}], appointment[{}], prescriptionModel[{}]", mav, appointment, prescriptionModel);

            mav.addObject("MEDICS", 1);
            mav.addObject("FOS", 1);
            mav.addObject("PRACS", 1);

            // Set next appointent date
            mav.addObject("NEXTAPPOINTMENTDATE", doctorService.getTreatmentLong());

            // Get config time
            final String[] timeArr = treatmentService.getMedicineTimeConfig();
            mav.addObject("TIMES", timeArr);

            // get illnessList
            List<Illness> illnessList = illnessService.getAllIllness();
            mav.addObject("ILLNESSES", illnessList);

            //get medicineList
            List<Medicine> medicineList = medicineService.getAllMedicine();
            mav.addObject("MEDICINES", medicineList);

            //get foodList
            List<Food> foodList = foodService.getAllFood();
            mav.addObject("FOODS", foodList);

            //get practiceList
            List<Practice> practiceList = practiceService.getAllPractice();
            mav.addObject("PRACTICES", practiceList);

            // Get practice name list
            List<String> practiceNameList = practiceService.getAllPracticeName();
            mav.addObject("PRACTICENAMES", practiceNameList);

            // Find PreventionCheck
            PreventionCheck preventionCheck = preventionCheckService.findLastPreventionCheckFromAppointment(appointment);
            mav.addObject("PREVENTIONCHECK", preventionCheck);

            // Find FoodIngredient
            FoodIngredient foodIngredient = foodIngredientService.findFoodIngredientByAppoiment(appointment);
            if (null != foodIngredient){
                NutritionModel nutritionModel = new NutritionModel(foodIngredient,preventionCheck.getWeight());
                mav.addObject("FOODINGREDIENT", nutritionModel);
                LOGGER.info("nutritionModel[{}]", nutritionModel);
                int kcalEstimate = (int) (nutritionModel.getTotalEnergy() - preventionCheck.getBasalMetabolicRate());
                prescriptionModel.setKcalRequire(kcalEstimate);
            }

            // Set entry patient
            mav.addObject("PATIENT", appointment.getMedicalRecord().getPatient());

            // Find List Appointment
            List<Appointment> appointments = appointmentService.getAllAppointmentToCurrentDateOfPatient(appointment.getMedicalRecord().getPatient().getId());
            mav.addObject("APPOINTMENTS", appointments);

            // Get PracticeResultModel
            PracticeResultModel practiceResultModel = doctorService.getInfoPracticeDataOfPatient(appointment);
            mav.addObject("DATAPRACS", practiceResultModel);
            LOGGER.info("practiceResultModel[{}]", practiceResultModel);

            // Get Medicalhistory img
            List<String> imgs = patientService.getPatientHistoryImage(appointment.getMedicalRecord().getMedicalHistory());
            mav.addObject("MEDI_IMGS", imgs);

            // Get Medicalhistory
            String medicalHistory = patientService.getPatientHistory(appointment.getMedicalRecord().getMedicalHistory());
            mav.addObject("MEDI_HIS", medicalHistory);

            // Add model
            mav.addObject("model", prescriptionModel);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public void notify(ModelAndView mav, Boolean result,String method, String mess){
        // Set name of action
        mav.addObject("METHOD", method);

        // Set message notify
        mav.addObject("MESSAGE", mess);

        if (result){
            // Set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "info");
        }else {
            // Set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "danger");
        }
    }
}
