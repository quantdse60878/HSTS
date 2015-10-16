package vn.edu.fpt.hsts.web;

/**
 * Created by Aking on 9/28/2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedPageModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
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
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.Practice;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.List;

/**
 * Doctor controller, for processing
 * search patients, make prescription,
 * make appointment, etc.
 */
@Controller
public class DoctorController extends AbstractController{
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);

    /**
     * The {@link PatientService}.
     */
    @Autowired
    private PatientService patientService;

    /**
     * The {@link MedicalRecordService}.
     */
    @Autowired
    private MedicalRecordService medicalRecordService;

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

    /**
     * The {@link TreatmentService}.
     */
    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private IllnessService illnessService;

    @Autowired
    private PhaseService phaseService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private PracticeService practiceService;

    /**
     * The {@link NotifyService}.
     */
    @Autowired
    private NotifyService notifyService;

    /**
     * The doctor patients page mapping
     * @return
     */
    @RequestMapping(value = "doctorPatients", method = RequestMethod.GET)
    public ModelAndView doctorPatientsPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("doctorPatients");

            // Get list patients
            List<Patient> patientList = patientService.getPatientByApponitmentDate();
            LOGGER.info("listpatiens: " + patientList.size());
            mav.addObject("LISTPATIENTS", patientList);

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * Create page prescription
     * @param patientID
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
            if (0 < notificationId) {
                notifyService.markAsRead(notificationId);
            }

            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByPatientID(patientID);

            mav.addObject("APPOINTMENT", appointment);
            mav.addObject("MEDICS",  2);
            mav.addObject("FOS", 2);
            mav.addObject("PRACS", 2);
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
                                         @RequestParam(value = "diagnostic", required = false) final int diagnostic) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {

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

            mav.addObject("MEDICS", phase.getMedicinePhaseList().size() + 1);
            mav.addObject("FOS", phase.getFoodPhaseList().size() + 1);
            mav.addObject("PRACS", phase.getPracticePhaseList().size() + 1);
            // Find illness form diagnostic
            Illness illness = illnessService.findByID(diagnostic);
            mav.addObject("DIAGNOSTIC", illness);

            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);

            mav.addObject("model", new PrescriptionModel());
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
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");

            // Initialization Data Prescription
            initDataPrescription(mav);

            mav.addObject("model", prescriptionModel);

            LOGGER.info(prescriptionModel.toString());
            doctorService.makePrescription(prescriptionModel, appointmentId, appointmentDate);
            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);
            LOGGER.info("APPOINTMENTlist : " + appointment.getMedicalRecord().getAppointmentList().size());
            mav.addObject("MEDICS",  2);
            mav.addObject("FOS", 2);
            mav.addObject("PRACS", 2);
            // Create notify
            notify(mav, true, "Make Prescription", "Success");
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * Make new appointment
     * @param recordID
     * @param appointmentDate
     * @return
     */
    @RequestMapping(value = "makeAppointment", method = RequestMethod.GET)
    public ModelAndView makeAppointment(@RequestParam("recordID") final int recordID,
                                        @RequestParam("appointmentDate") final String appointmentDate) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("historyTreatment");
//            patientService.makeAppointment(recordID, appointmentDate);
            //create notify
            notify(mav, true, "Make Appointment", "Success");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The view Prescription page mapping
     * @param appointmentId
     * @return
     */
    @RequestMapping(value = "viewPrescription", method = RequestMethod.GET)
    public ModelAndView makePrescriptionPage(@RequestParam("appointmentId") final int appointmentId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");
            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);
            // Check appointment status
            if (appointment.getStatus() == 1){
                // Initialization Data Prescription
                initDataPrescription(mav);

                mav.addObject("model", new PrescriptionModel());
            } else if (appointment.getStatus() == 2){
                // Find treatment form appointment
                Treatment treatment = treatmentService.findTreatmentByAppointmentID(appointmentId);
                mav.addObject("TREATMENT", treatment);
                mav.addObject("model", new PrescriptionModel());
            }

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * not yet
     * @return
     */
    @RequestMapping(value = "medicalRecord", method = RequestMethod.GET)
    public ModelAndView medicalRecordPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("suggestTreatment");
            // Initialization Data Prescription
            initDataPrescription(mav);
            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(23);
            mav.addObject("APPOINTMENT", appointment);
            mav.addObject("model", new PrescriptionModel());

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "medicineList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MedicinePhaseModel> medicineList(@RequestParam(value = "appointmentId") final int appointmentId,
                                                 @RequestParam(value = "diagnostic", required = false) final int illnessId) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return doctorService.getMedicines(appointmentId, illnessId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "medicalHistory", method = RequestMethod.GET)
    public ModelAndView medicalHistoryPage(@RequestParam("patientID") final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("medicalHistory");
            Patient patient = patientService.getPatientByID(patientID);
            List<MedicalRecord> medicalRecords = medicalRecordService.findMedicalRecordByPatientId(patientID);
            mav.addObject("PATIENT", patient);
            mav.addObject("MEDICALRECORDS", medicalRecords);
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The history treatment page mapping
     * @param recordID
     * @return
     */
    @RequestMapping(value = "historyTreatment", method = RequestMethod.GET)
    public ModelAndView historyTreatmentPage(@RequestParam("recordID") final int recordID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("historyTreatment");
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByID(recordID);
            mav.addObject("MEDICALRECORD", medicalRecord);
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
