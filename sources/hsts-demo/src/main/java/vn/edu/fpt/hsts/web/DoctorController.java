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
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;
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

    /**
     * The history treatment page mapping
     * @param patientID
     * @return
     */
    @RequestMapping(value = "historyTreatment", method = RequestMethod.GET)
    public ModelAndView historyTreatmentPage(@RequestParam("patientID") final String patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("historyTreatment");
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
    public ModelAndView makeAppointment(@RequestParam("recordID") final String recordID,
                                        @RequestParam("appointmentDate") final String appointmentDate) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("historyTreatment");
            patientService.makeAppointment(recordID, appointmentDate);
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
     * @param patientID
     * @return
     */
    @RequestMapping(value = "makePrescription", method = RequestMethod.GET)
    public ModelAndView makePrescriptionPage(@RequestParam("patientID") final String patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");
            mav.addObject("model", new PrescriptionModel());
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The Prescription  mapping
     * @param PrescriptionModel
     * @return
     */
    @RequestMapping(value="prescription", method=RequestMethod.GET)
    public ModelAndView makePrescription(@ModelAttribute PrescriptionModel PrescriptionModel) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("makePrescription");
            mav.addObject("model", PrescriptionModel);

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
