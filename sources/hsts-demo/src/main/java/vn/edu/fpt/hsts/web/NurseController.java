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
import vn.edu.fpt.hsts.criteria.CheckCriteria;
import vn.edu.fpt.hsts.criteria.RegistrationCriteria;
import vn.edu.fpt.hsts.persistence.entity.Patient;

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
     */
    @RequestMapping(value = "registerNew", method = RequestMethod.POST)
    public ModelAndView registerPatient(
            // Tab 1 param
            @RequestParam("patientName") final String patientName,
            @RequestParam("email") final String email,
            @RequestParam("birthday") final String birthday,
            @RequestParam("gender") final byte gender,
            // Tab 2 param
            @RequestParam("weight") final int weight,
            @RequestParam("height") final int height,
            @RequestParam("hearthBeat") final int hearthBeat,
            @RequestParam("bloodPressure") final int bloodPressure,
            @RequestParam("waists") final int waists,
            @RequestParam("bmi") final float bmi,
            // Tab 3 param
            @RequestParam("bodyFat") final float bodyFat,
            @RequestParam("visceralFat") final byte visceralFat,
            @RequestParam("muscleMass") final float muscleMass,
            @RequestParam("bodyWater") final float bodyWater,
            @RequestParam("phaseAngle") final float phaseAngle,
            @RequestParam("impedance") final int impedance,
            @RequestParam("basalMetabolicRate") final int basalMetabolicRate,
            // Tab 4 param
            @RequestParam("doctorId") final int doctorId,
            @RequestParam(value = "medicalHistory") final String medicalHistory,
            @RequestParam(value = "symptoms") final String symptoms)
            throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // TODO reg input log

            // Tab 1 criteria
            final PatientCriteria pCriteria = new PatientCriteria();
            pCriteria.setPatientName(patientName);
            pCriteria.setEmail(email);
            pCriteria.setBirthday(birthday);
            pCriteria.setGender(gender);
            pCriteria.setDoctorId(doctorId);
            pCriteria.setMedicalHistory(medicalHistory);
            pCriteria.setSymptom(symptoms);

            // Tab 2_3 criteria
            final CheckCriteria checkCriteria = new CheckCriteria();
            checkCriteria.setBodyFat(bodyFat);
            checkCriteria.setVisceralFat(visceralFat);
            checkCriteria.setMuscleMass(muscleMass);
            checkCriteria.setBodyWater(bodyWater);
            checkCriteria.setPhaseAngle(phaseAngle);
            checkCriteria.setImpedance(impedance);
            checkCriteria.setBasalMetabolicRate(basalMetabolicRate);
            checkCriteria.setWeight(weight);
            checkCriteria.setHeight(height);
            checkCriteria.setHearthBeat(hearthBeat);
            checkCriteria.setBloodPressure(bloodPressure);
            checkCriteria.setWaists(waists);
            checkCriteria.setBmi(bmi);

            // Tab 4 criteria
            RegistrationCriteria rCriteria = new RegistrationCriteria();
            rCriteria.setDoctorId(doctorId);
            rCriteria.setMedicalHistory(medicalHistory);
            rCriteria.setSymptom(symptoms);

            patientService.createPatient(pCriteria, rCriteria, checkCriteria);

            // Response view
            ModelAndView mav = new ModelAndView();
            mav.setViewName("registerPatient");
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

            // TODO
            LOGGER.info("weight[{}], height[{}], doctorId[{}], medicalHistory[{}], symptoms[{}], isNewMedicalRecord[{}]",
                    weight, height, doctorId, medicalHistory, symptoms, isNewMedicalRecord);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("updatePatient");
            /**
             * Update patient
             */
            PatientCriteria criteria = new PatientCriteria();
            criteria.setId(patientId);
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
                    + "Don-thuoc" + timeDownload + ".docx");
            patientService.print(patientId, response);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
