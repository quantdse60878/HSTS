/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/9/2015.
 */
package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.DoctorModel;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.criteria.PatientCriteria;
import vn.edu.fpt.hsts.persistence.entity.Patient;

import java.util.List;

@Controller
public class NurseController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NurseController.class);

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    /**
     * The register patient page mapping
     *
     * @return
     */
    @RequestMapping(value = "registerPatient", method = RequestMethod.GET)
    public ModelAndView registerPatientPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("registerPatient");

            /**
             * Set reference data
             */
//            final List<DoctorModel> doctors = doctorService.findAll();
//            mav.addObject("DOCTORS", doctors);
            /**
             *
             */

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    /**
     * The register patient mapping
     * @param patientName
     * @param email
     * @param birthday
     * @param gender
     * @param weight
     * @param height
     * @param doctorId
     * @param medicalHistory
     * @param symptoms
     * @return
     * @throws BizlogicException
     * @throws BizlogicException
     */
    @RequestMapping(value = "/nurse/registerNew", method = RequestMethod.POST)
    public ModelAndView registerPatient(@RequestParam("patientName") final String patientName,
                                        @RequestParam("email") final String email,
                                        @RequestParam("birthday") final String birthday,
                                        @RequestParam("gender") final byte gender,
                                        @RequestParam("weight") final int weight,
                                        @RequestParam("height") final int height,
                                        @RequestParam("doctorId") final int doctorId,
                                        @RequestParam(value = "medicalHistory") final String medicalHistory,
                                        @RequestParam(value = "symptoms") final String symptoms) throws BizlogicException, BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientName[{}], email[{}], birthday[{}], gender[{}], weight[{}], height[{}], doctorId[{}], medicalHistory[{}], symptoms[{}]",
                    patientName, email, birthday, gender, weight, height, doctorId, medicalHistory, symptoms);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("registerPatient");
            /**
             * Create new patient
             */
            PatientCriteria criteria = new PatientCriteria();
            criteria.setPatientName(patientName);
            criteria.setEmail(email);
            criteria.setBirthday(birthday);
            criteria.setGender(gender);
            criteria.setWeight(weight);
            criteria.setHeight(height);
            criteria.setDoctorId(doctorId);
            criteria.setMedicalHistory(medicalHistory);
            criteria.setSymptom(symptoms);

            patientService.createPatient(criteria);

            //create notify
            //set name of action
            mav.addObject("METHOD", "Register Patient");
            //set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "info");
            //set message notify
            mav.addObject("MESSAGE","Success");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }



    @RequestMapping(value = "/nurse/updatePatient", method = RequestMethod.GET)
    public ModelAndView nursePatientsPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("nursePatients");
            List<Patient> patientList = patientService.getAllPatients();
            LOGGER.info("listpatiens: " + patientList.size());
            mav.addObject("LISTPATIENTS", patientList);
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "patient", method = RequestMethod.GET)
    public ModelAndView updatePatientPage(@RequestParam("patientID") final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("updatePatient");
            Patient patient = patientService.getPatientByID(patientID);
            mav.addObject("PATIENT", patient);
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "updatePatient", method = RequestMethod.POST)
    public ModelAndView registerPatient(@RequestParam("patientID") final int patientID,
                                        @RequestParam("weight") final int weight,
                                        @RequestParam("height") final int height,
                                        @RequestParam("doctorId") final int doctorId,
                                        @RequestParam(value = "medicalHistory") final String medicalHistory,
                                        @RequestParam(value = "symptoms") final String symptoms) throws BizlogicException, BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
//            LOGGER.info("patientName[{}], email[{}], birthday[{}], gender[{}], weight[{}], height[{}], doctorId[{}], medicalHistory[{}], symptoms[{}]",
//                    patientName, email, birthday, gender, weight, height, doctorId, medicalHistory, symptoms);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("updatePatient");
            Patient patient = patientService.getPatientByID(patientID);
            mav.addObject("PATIENT", patient);
            /**
             * Update patient
             */
            PatientCriteria criteria = new PatientCriteria();
            criteria.setWeight(weight);
            criteria.setHeight(height);
            criteria.setDoctorId(doctorId);
            criteria.setMedicalHistory(medicalHistory);
            criteria.setSymptom(symptoms);

//            patientService.createPatient(criteria);

            // Add doctors to request
            final List<DoctorModel> doctors = doctorService.findAll();
            mav.addObject("DOCTORS", doctors);

            //create notify
            //set name of action
            mav.addObject("METHOD", "Update Patient's Profile");
            //set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "info");
            //set message notify
            mav.addObject("MESSAGE","Success");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
