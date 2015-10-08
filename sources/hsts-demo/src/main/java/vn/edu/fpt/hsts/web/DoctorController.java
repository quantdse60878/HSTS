package vn.edu.fpt.hsts.web;

/**
 * Created by Aking on 9/28/2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.MedicalRecordService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Patient;

import java.util.List;

/**
 * Doctor controller, for processing
 * search patients, make prescription,
 * make appointment, etc.
 */
@Controller
public class DoctorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

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
            List<Patient> patientList = patientService.getPatientByApponitmentDate();
            LOGGER.info("listpatiens: " + patientList.size());
            mav.addObject("LISTPATIENTS", patientList);
            return mav;
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
            //set name of action
            mav.addObject("METHOD", "Make Appointment");
            //set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "info");
            //set message notify
            mav.addObject("MESSAGE", "Success");
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The make Prescription page mapping
     * @param appointmentID
     * @return
     */
    @RequestMapping(value = "makePrescription", method = RequestMethod.GET)
    public ModelAndView makePrescriptionPage(@RequestParam("appointmentID") final int appointmentID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");
            Appointment appointment = appointmentService.findAppointmentByID(appointmentID);
            mav.addObject("APPOINTMENT", appointment);
            mav.addObject("model", new PrescriptionModel());
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "createPrescription", method = RequestMethod.GET)
    public ModelAndView createPrescriptionPage(@RequestParam("patientID") final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");
//            Appointment appointment = appointmentService.findAppointmentByID(appointmentID);
//            mav.addObject("APPOINTMENT", appointment);
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
                                         @RequestParam("appointmentID") final int appointmentID,
                                         @RequestParam(value = "appointmentDate", required = false) final String appointmentDate) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");
            mav.addObject("model", prescriptionModel);
            LOGGER.info(prescriptionModel.toString());
            doctorService.makePrescription(prescriptionModel, appointmentID, appointmentDate);
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
