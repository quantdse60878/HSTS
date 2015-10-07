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
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("historyTreatment");
            return modelAndView;
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
