package vn.edu.fpt.hsts.web;

/**
 * Created by Aking on 9/28/2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.DoctorPageModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.NutritionModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.FoodIngredientService;
import vn.edu.fpt.hsts.bizlogic.service.MedicalRecordService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.bizlogic.service.PreventionCheckService;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;
import vn.edu.fpt.hsts.persistence.entity.Treatment;
import vn.edu.fpt.hsts.web.session.UserSession;

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
    private UserSession userSession;


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
            List<Patient> patientList = patientService.getPatientByApponitmentDateOfDoctor(userSession.getId());
            LOGGER.info("listpatiens: " + patientList.size());
            mav.addObject("LISTPATIENTS", patientList);

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
            LOGGER.info("appointmentId[{}]", appointmentId);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");

            // Find Appointment
            Appointment appointment = appointmentService.findAppointmentByID(appointmentId);
            mav.addObject("APPOINTMENT", appointment);

            // Initialization Data Prescription
            initDataPrescription(mav, appointment, new PrescriptionModel());

            // Check appointment status
            if (appointment.getStatus() == IDbConsts.IAppointmentStatus.FINISHED){
                // Find treatment form appointment
                Treatment treatment = treatmentService.findTreatmentByAppointmentID(appointment.getId());
                mav.addObject("TREATMENT", treatment);
            }

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
     * not yet
     * @return
     */
    @RequestMapping(value = "medicalRecord", method = RequestMethod.GET)
    public ModelAndView medicalRecordPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("suggestTreatment");
            mav.addObject("model", new PrescriptionModel());

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "medicineList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MedicinePhaseModel> medicineList(@RequestParam(value = "appointmentId") final int appointmentId,
                                                 @RequestParam(value = "diagnostic", required = false) final String diagnostic) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return doctorService.getMedicines(appointmentId, diagnostic);
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

    @RequestMapping(value = "/doctor/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DoctorPageModel getDoctors(@RequestParam(value = "name", required = false, defaultValue = EMPTY) final String name,
                                      @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "5") final int pageSize,
                                      @RequestParam(value = "roleId", required = false, defaultValue = ZERO) final int roleId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return doctorService.getDoctors(name, roleId, page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/uploadedHistoryImg", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getUploadedImage(@RequestParam(value = "filename") final String fileName) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("fileName[{}]", fileName);
            return patientService.getUploadedHistoryImg(fileName);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "finishMedicalRecord", method = RequestMethod.GET)
    public ModelAndView finishMedicalRecord(@RequestParam("appointmentId") final int appointmentId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            doctorService.finishMedicalRecord(appointmentId);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("doctorPatients");
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

}
