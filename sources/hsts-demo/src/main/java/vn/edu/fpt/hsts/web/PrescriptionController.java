package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.HisInforDateModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.FoodIngredientService;
import vn.edu.fpt.hsts.bizlogic.service.IllnessService;
import vn.edu.fpt.hsts.bizlogic.service.MedicalRecordDataService;
import vn.edu.fpt.hsts.bizlogic.service.NotifyService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.bizlogic.service.PreventionCheckService;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.PracticeTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.Date;
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

    /**
     * The {@link PatientService}.
     */
    @Autowired
    private PatientService patientService;

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
            PrescriptionModel prescriptionModel = new PrescriptionModel();

            // Set notify as readed
            notifyService.markAllNotifitionRelatedToPatientAsRead(patientID);

            // Find Appointment
            Appointment appointment = appointmentService.findEntryAppointmentByPatientId(patientID);
            mav.addObject("APPOINTMENT", appointment);

            // Initialization Data Prescription
            initDataPrescription(mav, appointment, prescriptionModel);

            // Find old Appointment
            Appointment oldAppointment = appointmentService.findParentOfAppointment(appointment);
            if (oldAppointment != null){
                // Find illness form diagnostic
                Illness illness = oldAppointment.getMedicalRecord().getIllness();
                mav.addObject("DIAGNO", illness);
            }

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
                                         @RequestParam(value = "diagnostic", required = false) final String diagnostic){
        LOGGER.info(IConsts.BEGIN_METHOD);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("makePrescription");
        try {
            LOGGER.info("appointmentId[{}], prescriptionModel[{}], diagnostic[{}]", appointmentId, prescriptionModel, diagnostic);

            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);

            // Initialization Data Prescription
            initDataPrescription(mav, appointment, prescriptionModel);

            // Find illness form diagnostic
            Illness illness = illnessService.findByName(diagnostic);

            if(illness == null) {
                illness = illnessService.createNewIllness(diagnostic);
            } else {
                // Find phase for diagnostic
                Phase phase = illnessService.getPhaseSugestion(appointmentId, illness);
                // illness != null, da kham va chua benh
                if (null != appointment.getMedicalRecord().getIllness()){
                    // neu khac nhau la bac si da doi benh an . sugest voi date moi
                    if (illness.getId() != appointment.getMedicalRecord().getIllness().getId()){
                        Date date = new Date();
                        // Find phase for diagnostic
                        phase = illnessService.getPhaseSugest(date, illness);
                    }
                }


                if (phase == null) {
                    notify(mav, false, "Fail!!! ", "No regimen for suggest treatment");
                } else {
                    mav.addObject("PHASE", phase);
                    mav.addObject("MEDICS", phase.getMedicinePhaseList().size());
                    mav.addObject("FOS", phase.getFoodPhaseList().size());
                    mav.addObject("PRACS", phase.getPracticePhaseList().size());
                }
            }
            mav.addObject("DIAGNOSTIC", illness);

            return mav;
        } catch (Exception e){
            LOGGER.info("Exception: " + e.getMessage());
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }

        return mav;
    }

    /**
     * The Prescription  mapping
     * @param prescriptionModel
     * @return
     */
    @RequestMapping(value="prescription", method=RequestMethod.GET)
    public ModelAndView makePrescription(@RequestParam("appointmentId") final int appointmentId,
                                         @RequestParam(value = "appointmentDate", required = true) final String appointmentDate,
            @ModelAttribute PrescriptionModel prescriptionModel, BindingResult br
                                         ) throws BizlogicException {
//        , BindingResult br
//        for(int i = 0; i < br.getAllErrors().size(); i++) {
//            System.out.println(br.getAllErrors().get(i).getObjectName() + "--++FUCK++--");
//        }
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (br.hasErrors()){
                LOGGER.error("Error while make prescrition");
                for (FieldError fieldError: br.getFieldErrors()) {
                    LOGGER.error("error[{}], data[{}]", fieldError.getField(), fieldError.getRejectedValue());
                }
                // Find patientid
                final int patientId = patientService.findPatientByAppointmentId(appointmentId);
                if (0 < patientId) {
                    String url = "redirect:createPrescription?patientID=" + patientId;
                    return new ModelAndView(url);
                }
            }

            LOGGER.info("appointmentId[{}], appointmentDate[{}]", appointmentId, appointmentDate);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");

            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);

            // Initialization Data Prescription
            initDataPrescription(mav, appointment, prescriptionModel);

            LOGGER.info(prescriptionModel.toString());
            boolean result = doctorService.makePrescription(prescriptionModel, appointmentId, appointmentDate);

            // Find illness form diagnostic
            Illness illness = illnessService.findByName(prescriptionModel.getDiagnostic());
            mav.addObject("DIAGNOSTIC", illness);

            // Create notify
            if (result){
                notify(mav, result, "Make Prescription", "Success!!!");

                // Find treatment form appointment
                Treatment treatment = treatmentService.findTreatmentByAppointmentID(appointmentId);
                List<MedicineTreatment> medicineTreatments = treatmentService.getAllMedicineTreatmentFromTreatment(treatment);
                treatment.setMedicineTreatmentList(medicineTreatments);
                List<FoodTreatment> foodTreatments = treatmentService.getAllFoodTreatmentFromTreatment(treatment);
                treatment.setFoodTreatmentList(foodTreatments);
                List<PracticeTreatment> practiceTreatments = treatmentService.getAllPracticeTreatmentFromTreatment(treatment);
                treatment.setPracticeTreatmentList(practiceTreatments);
                mav.addObject("TREATMENT", treatment);
            } else {
                notify(mav, result, "Make Prescription", "Fail!!!");
            }
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/makePrescription2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HisInforDateModel make(@RequestBody PrescriptionModel prescriptionModel) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info(prescriptionModel.toString());
            boolean result = doctorService.makePrescription(prescriptionModel, prescriptionModel.getAppointmentId(),
                    prescriptionModel.getAppointmentDate());

            HisInforDateModel hisInforDateModel = null;
            if (result){
                hisInforDateModel = treatmentService.findInforByAppoitmentID(prescriptionModel.getAppointmentId());
            }


            return hisInforDateModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/finishTreatment", method = RequestMethod.POST)
    @ResponseBody
    public boolean finishTreatment(@RequestParam("appointmentId") final int appointmentId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointmentId[{}]", appointmentId);
            boolean result = doctorService.finishMakePrescription(appointmentId);

            return result;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
