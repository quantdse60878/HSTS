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
import vn.edu.fpt.hsts.bizlogic.model.NutritionModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.FoodIngredientService;
import vn.edu.fpt.hsts.bizlogic.service.FoodService;
import vn.edu.fpt.hsts.bizlogic.service.IllnessService;
import vn.edu.fpt.hsts.bizlogic.service.MedicalRecordService;
import vn.edu.fpt.hsts.bizlogic.service.MedicineService;
import vn.edu.fpt.hsts.bizlogic.service.NotifyService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.bizlogic.service.PracticeService;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.List;

/**
 * Created by Aking on 10/22/2015.
 */
@Controller
public class PrescriptionController extends AbstractController{
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionController.class);

    /**
     * The {@link AppointmentService}.
     */
    @Autowired
    private AppointmentService appointmentService;

    /**
     * The {@link DoctorService}.
     */
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private IllnessService illnessService;

    /**
     * The {@link NotifyService}.
     */
    @Autowired
    private NotifyService notifyService;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private FoodIngredientService foodIngredientService;

    /**
     * Create Prescription Page
     * @param patientID
     * @param notificationId
     * @return
     */
    @RequestMapping(value = "createPrescription", method = RequestMethod.GET)
    public ModelAndView createPrescriptionPage(@RequestParam("patientID") final int patientID,
                                               @RequestParam(value = "notificationId", required = false, defaultValue = ZERO) final int notificationId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}], notificationId[{}]", patientID, notificationId);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");

            // Set notify as readed
            notifyService.markAllNotifitionRelatedToPatientAsRead(patientID);

            // Find Appointment
            Appointment appointment = appointmentService.findEntryAppointmentByPatientId(patientID);
            mav.addObject("APPOINTMENT", appointment);

            // Find FoodIngredient
            FoodIngredient foodIngredient = foodIngredientService.findFoodIngredientByAppoiment(appointment);
            if (null != foodIngredient){
                NutritionModel nutritionModel = new NutritionModel(foodIngredient,appointment.getWeight());
                mav.addObject("FOODINGREDIENT", nutritionModel);
            }

            // Set entry patient
            mav.addObject("PATIENT", appointment.getMedicalRecord().getPatient());

            // Find List Appointment
            List<Appointment> appointments = appointmentService.getAllAppointmentToCurrentDateOfPatient(patientID);
            mav.addObject("APPOINTMENTS", appointments);

            // Initialization Data Prescription
            initDataPrescription(mav);

            mav.addObject("model", new PrescriptionModel());
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * Suggest Treatment for doctor
     * @param appointmentId
     * @param diagnostic
     * @return
     * @throws BizlogicException
     */
    @RequestMapping(value = "suggestTreatment", method = RequestMethod.GET)
    public ModelAndView suggestTreatment(@RequestParam(value = "appointmentId") final int appointmentId,
                                         @ModelAttribute PrescriptionModel prescriptionModel,
                                         @RequestParam(value = "diagnostic", required = false) final int diagnostic) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointmentId[{}], prescriptionModel[{}], diagnostic[{}]", appointmentId, prescriptionModel, diagnostic);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");
            // Initialization Data Prescription
            initDataPrescription(mav);

            // Find phase for diagnostic
            final Phase phase = illnessService.getPhaseSugestion(appointmentId, diagnostic);
            mav.addObject("PHASE", phase);
            if (phase == null){
                notify(mav, false, "Fail", "No regimen for suggest treatment");
            }

            mav.addObject("MEDICS", phase.getMedicinePhaseList().size());
            mav.addObject("FOS", phase.getFoodPhaseList().size());
            mav.addObject("PRACS", phase.getPracticePhaseList().size());

            // Find illness form diagnostic
            Illness illness = illnessService.findByID(diagnostic);
            mav.addObject("DIAGNOSTIC", illness);

            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);

            // Find FoodIngredient
            FoodIngredient foodIngredient = foodIngredientService.findFoodIngredientByAppoiment(appointment);
            if (null != foodIngredient){
                NutritionModel nutritionModel = new NutritionModel(foodIngredient,appointment.getWeight());
                mav.addObject("FOODINGREDIENT", nutritionModel);
            }

            // Set entry patient
            mav.addObject("PATIENT", appointment.getMedicalRecord().getPatient());

            // Find List Appointment
            List<Appointment> appointments = appointmentService.getAllAppointmentToCurrentDateOfPatient(appointment.getMedicalRecord().getPatient().getId());
            mav.addObject("APPOINTMENTS", appointments);

            mav.addObject("model", prescriptionModel);
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The Prescription  mapping
     * @param prescriptionModel
     * @return
     */
    @RequestMapping(value="prescription", method=RequestMethod.GET)
    public ModelAndView makePrescription(@ModelAttribute PrescriptionModel prescriptionModel,
                                         @RequestParam("appointmentId") final int appointmentId,
                                         @RequestParam(value = "appointmentDate", required = true) final String appointmentDate) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointmentId[{}], appointmentDate[{}]", appointmentId, appointmentDate);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");

            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);

            // Find FoodIngredient
            FoodIngredient foodIngredient = foodIngredientService.findFoodIngredientByAppoiment(appointment);
            if (null != foodIngredient){
                NutritionModel nutritionModel = new NutritionModel(foodIngredient,appointment.getWeight());
                mav.addObject("FOODINGREDIENT", nutritionModel);
            }

            // Find List Appointment
            List<Appointment> appointments = appointmentService.getAllAppointmentToCurrentDateOfPatient(appointment.getMedicalRecord().getPatient().getId());
            mav.addObject("APPOINTMENTS", appointments);

            // Set entry patient
            mav.addObject("PATIENT", appointment.getMedicalRecord().getPatient());

            LOGGER.info(prescriptionModel.toString());
            boolean result = doctorService.makePrescription(prescriptionModel, appointmentId, appointmentDate);

            // Create notify
            if (result){
                notify(mav, result, "Make Prescription", "Success");

                // Find treatment form appointment
                Treatment treatment = treatmentService.findTreatmentByAppointmentID(appointmentId);
                mav.addObject("TREATMENT", treatment);
                LOGGER.info("Medicine: " + treatment.getMedicineTreatmentList().size());
                mav.addObject("model", new PrescriptionModel());
            } else {
                notify(mav, result, "Make Prescription", "Fail");

                // Initialization Data Prescription
                initDataPrescription(mav);
                mav.addObject("model", new PrescriptionModel());
            }

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
