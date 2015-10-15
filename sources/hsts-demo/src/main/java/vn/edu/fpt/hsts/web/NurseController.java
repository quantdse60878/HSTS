/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/9/2015.
 */
package vn.edu.fpt.hsts.web;

import net.sf.jasperreports.engine.JRException;
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
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedPageModel;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.criteria.PatientCriteria;
import vn.edu.fpt.hsts.persistence.entity.Patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
     *
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
    @RequestMapping(value = "registerNew", method = RequestMethod.POST)
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
            notify(mav, true, "Register Patient", "Success");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "searchPatient", method = RequestMethod.GET)
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

    @RequestMapping(value = "patientList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientExtendedPageModel findPatients(@RequestParam(value = "name", required = false, defaultValue = EMPTY) final String name,
                                                 @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = UNLIMIT_PAGE_SIZE) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("name[{}], page[{}], pageSize[{}]", name, page, pageSize);
            return patientService.findPatients(name, page, pageSize);
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
    public ModelAndView registerPatient(@RequestParam("patientId") final int patientId,
                                        @RequestParam("weight") final int weight,
                                        @RequestParam("height") final int height,
                                        @RequestParam("doctorId") final int doctorId,
                                        @RequestParam(value = "medicalHistory") final String medicalHistory,
                                        @RequestParam(value = "symptoms") final String symptoms,
                                        @RequestParam(value = "isNewMedicalRecord", required = false, defaultValue = "false")
                                        final boolean isNewMedicalRecord) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("weight[{}], height[{}], doctorId[{}], medicalHistory[{}], symptoms[{}], isNewMedicalRecord[{}]",
                    weight, height, doctorId, medicalHistory, symptoms, isNewMedicalRecord);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("updatePatient");
            /**
             * Update patient
             */
            PatientCriteria criteria = new PatientCriteria();
            criteria.setId(patientId);
            criteria.setWeight(weight);
            criteria.setHeight(height);
            criteria.setDoctorId(doctorId);
            criteria.setMedicalHistory(medicalHistory);
            criteria.setSymptom(symptoms);

            patientService.updatePatient(criteria, isNewMedicalRecord);

            Patient patient = patientService.getPatientByID(patientId);
            mav.addObject("PATIENT", patient);

            //create notify
            notify(mav, true, "Update Patient's Profile", "Success");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    @ResponseBody
    public void printPrescription(@RequestParam("patientId") final int patientId,
            final HttpServletResponse response) throws IOException, BizlogicException, JRException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId", patientId);
            //TODO re-format filename
            Date toDayTime = new Date();
            String timeDownload = toDayTime.getTime() + "";
            response.setHeader("Content-disposition", "attachment; filename="
                    + "Don-thuoc" + timeDownload + ".pdf");
            patientService.print(patientId, response);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
