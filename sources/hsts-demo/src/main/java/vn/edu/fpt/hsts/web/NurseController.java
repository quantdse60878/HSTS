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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.DoctorModel;
import vn.edu.fpt.hsts.bizlogic.model.FileUploadModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedPageModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientRegistrationModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientRegistrationRequest;
import vn.edu.fpt.hsts.bizlogic.service.AnalyticFood;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.bizlogic.service.PreventionCheckService;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.criteria.CheckCriteria;
import vn.edu.fpt.hsts.criteria.PatientCriteria;
import vn.edu.fpt.hsts.criteria.RegistrationCriteria;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

import java.io.IOException;
import java.util.List;

@Controller
public class NurseController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NurseController.class);

    /**
     * The {@link PatientService}.
     */
    @Autowired
    PatientService patientService;


    /**
     * The {@link AnalyticFood}.
     */
    @Autowired
    AnalyticFood analyticFood;

    /**
     * The {@link DoctorService}.
     */
    @Autowired
    DoctorService doctorService;

    /**
     * The {@link PreventionCheckService}.
     */
    @Autowired
    private PreventionCheckService preventionCheckService;

    /**
     * The {@link TreatmentService}.
     */
    @Autowired
    private TreatmentService treatmentService;

    /**
     * The {@link AppointmentService}.
     */
    @Autowired
    private AppointmentService appointmentService;

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


    /**
     * This method to register new patient via AJAX request.
     * @param request
     * @return
     */
    @RequestMapping(value = "/registerPatient", method = RequestMethod.POST,
                        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientRegistrationModel registerPatient(@RequestBody final PatientRegistrationRequest request) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Tab 1
            final PatientCriteria pCriteria = request.getPatient();
            // Tab 2
            final CheckCriteria cCriteria = request.getCheck();
            float tmp =  ((float)cCriteria.getHeight()/100) * ((float)cCriteria.getHeight()/100);
            tmp = cCriteria.getWeight()/tmp;
            cCriteria.setBmi(tmp);
            // Tab 3
            final RegistrationCriteria rCriteria = request.getRegistration();

            /**
             * The wrong order of criteria array may cause the program fail
             * The criteria array should be order by: patient info -> registration -> check condition
             */
            return patientService.register(0, pCriteria, rCriteria, cCriteria);
        } catch (Exception e) {
            // error showing
            return new PatientRegistrationModel(false);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    /**
     * This method to update patient medical registration via AJAX request.
     * Include case that new medical record
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePatient", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientRegistrationModel updatePatient(@RequestBody final PatientRegistrationRequest request) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Tab 1: OLD DATA, not use

            // Tab 2
            final CheckCriteria cCriteria = request.getCheck();
            float tmp =  ((float)cCriteria.getHeight()/100) * ((float) cCriteria.getHeight()/100);
            tmp = cCriteria.getWeight()/tmp;
            cCriteria.setBmi(tmp);
            // Tab 3
            final RegistrationCriteria rCriteria = request.getRegistration();

            boolean newMedicalRecord = request.isNewMedicalRecord();
            if (newMedicalRecord) {
                /**
                 * The wrong order of criteria array may cause the program fail
                 * The criteria array should be order by: patient info -> registration -> check condition
                 */
                return patientService.register(request.getPatientId(), rCriteria, cCriteria);
            } else {
                return patientService.updatePatient(request.getPatientId(), rCriteria, cCriteria);
            }
        } catch (Exception e) {
            // error showing
            return new PatientRegistrationModel(false);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    @RequestMapping(value = "bindingOld", method = RequestMethod.GET)
    public ModelAndView bindingOld(@RequestParam("patientId") final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("oldMeasurement");

            Patient patient = patientService.getPatientByID(patientId);
            mav.addObject("PATIENT", patient);

            // Find last old preventcheck
            final PreventionCheck pc = preventionCheckService.findLastPreventionCheckByPatientId(patientId);
            mav.addObject("PC", pc);


            // Find last old medicine treatment
            final String oldMedicine = treatmentService.findLastMedicines(patientId);
            LOGGER.info("OLD: {}", oldMedicine);
            mav.addObject("OLDMEDICINE", oldMedicine);

            // Find old medical history
            final String oldMedical = patientService.getOldMedicalHistory(patientId);
            mav.addObject("OLDMEDICAL", oldMedical);

            // Find old symptom
            final String oldSymptom = patientService.getOldSymtoms(patientId);
            mav.addObject("OLDSYMPTOMS", oldSymptom);

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    @RequestMapping(value = "/print", method = RequestMethod.GET)
    @ResponseBody
    public byte[] print(@RequestParam("patientId") final int patientId) throws IOException, BizlogicException, JRException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId", patientId);
            Patient patient = patientService.findPatient(patientId);
            if(null == patient) {
                throw new BizlogicException("Patient with id[{}] is not found", null, patientId);
            }
            return patientService.print(patientId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }



    @RequestMapping(value = "/printPrescriptionPage", method = RequestMethod.GET)
    public ModelAndView printPrescription(@RequestParam("patientId") final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("viewer");
            final String pdfOutputLink = "/print?patientId=" + patientId;
            modelAndView.addObject("PDF_DIRECT_LINK", pdfOutputLink);
            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/checkPrescription", method = RequestMethod.GET)
    @ResponseBody
    public String checkPrescription(@RequestParam("patientId") final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            final boolean result = patientService.checkPrescription(patientId);
            if (result) {
                return OK_STATUS;
            }
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FileUploadModel upload(@RequestParam(value = "fileUploader", required = false) final MultipartFile multipartFile) throws IOException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (null != multipartFile && multipartFile.getBytes().length > 0) {
                LOGGER.info("file name[{}]", multipartFile.getOriginalFilename());
            }
            return patientService.saveMedicalImage(multipartFile);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/lastDoctor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DoctorModel findLastDoctor(@RequestParam("patientId") final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            return appointmentService.findLastDoctorWithPatientId(patientId);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
