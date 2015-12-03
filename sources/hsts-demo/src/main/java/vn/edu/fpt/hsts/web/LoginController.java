package vn.edu.fpt.hsts.web;

/**
 * Created by Aking on 9/26/2015.
 */

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
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.LoginCredentialModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientModel;
import vn.edu.fpt.hsts.bizlogic.service.AccountService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.FormulaService;
import vn.edu.fpt.hsts.bizlogic.service.MailService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.web.session.UserSession;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Login controller, for processing login, logout.
 */
@Controller
public class LoginController extends AbstractController {


    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;

    @Autowired
    private PatientService patientService;

    /**
     * The {@link MailService}.
     */
    @Autowired
    private MailService mailService;


    @Autowired
    private FormulaService formulaService;

    /**
     * The {@link DoctorService}.
     */
    @Autowired
    DoctorService doctorService;


    /**
     * The {@link UserSession}.
     */
    @Autowired
    private UserSession userSession;

    /**
     * The login page mapping
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/loginRest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LoginCredentialModel loginRest(@RequestBody LoginCredentialModel loginCredential, HttpSession session) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("loginCredential[{}]", loginCredential);
            Account user = accountService.checkLogin(loginCredential.getUsername(), loginCredential.getPassword());
            if (null != user && user.getRole().getId() != IDbConsts.IRoleType.PATIENT
                    && user.getStatus() != IDbConsts.IAccountStatus.BLOCKED) {
                session.setAttribute("USER", user);
                LoginCredentialModel model = new LoginCredentialModel();
                model.setAccountId(user.getId());
                if (user.getStatus() == IDbConsts.IAccountStatus.ACTIVE) {
                    model.setStatus(LoginCredentialModel.ILoginSatus.OK);
                } else {
                    model.setStatus(LoginCredentialModel.ILoginSatus.INACTIVE);
                }
                return model;
            } else {
                return new LoginCredentialModel(LoginCredentialModel.ILoginSatus.FAIL);
            }
        } catch (Exception e){
            return new LoginCredentialModel(LoginCredentialModel.ILoginSatus.FAIL);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * The login mapping
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "navigate", method = RequestMethod.GET)
    public ModelAndView login(HttpSession session) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            final Account user = (Account) session.getAttribute("USER");
            if (user != null && user.getStatus() != IDbConsts.IAccountStatus.BLOCKED) {

                mav.setViewName("home");

                if (user.getStatus() == IDbConsts.IAccountStatus.ACTIVE){
                    if (user.getRole().getId() == IDbConsts.IRoleType.DOCTOR) {
                        mav.setViewName("doctorPatients");
                        List<Patient> patientList = patientService.getPatientByApponitmentDateOfDoctor(user.getId());
                        LOGGER.info("listpatiens: " + patientList.size());
                        mav.addObject("LISTPATIENTS", patientList);
                    } else if (user.getRole().getId() == IDbConsts.IRoleType.NURSE) {
                        mav.setViewName("registerPatient");
                    } else if (user.getRole().getId() == IDbConsts.IRoleType.STAFF) {
                        mav.setViewName("stafflistdevice");
                        List<String> temp = new ArrayList<String>();
                        temp.add("111");
                        temp.add("222");
                        temp.add("333");
                        temp.add("444");
                        mav.addObject("temp",temp);
//                    List<String> listPrevention1 = formulaService.getListFieldOfPreventionCheck();
//                    List<String> listPrevention = new ArrayList<String>();
//                    List<String> listMedicalRecordData1 = formulaService.getListFieldOfMedicalRecordData();
//                    List<String> listMedicalRecordData = new ArrayList<String>();
//                    for (String p : listPrevention1) {
//                        if(!p.equals("appointment")) {
//                            listPrevention.add(p.substring(0, 1).toUpperCase() + p.substring(1));
//                        }
//                    }
//                    for (String m : listMedicalRecordData1) {
//                        if(!m.equals("appointment")) {
//                            listMedicalRecordData.add(m.substring(0, 1).toUpperCase() + m.substring(1));
//                        }
//                    }
//                    List<Variable> listVariables = new ArrayList<Variable>();
//                    for(int i = 0; i < AnalyticDataTask.variable.size(); i++) {
//                        listVariables.add(new Variable(AnalyticDataTask.variable.get(i), AnalyticDataTask.valueVariable.get(i).split(",")[1]));
//                    }
//                    mav.addObject("DISTANCEFORMULA", AnalyticDataTask.FORMULA_CALCULATE_DISTANCE);
//                    mav.addObject("CALORIESFORMULA", AnalyticDataTask.FORMULA_CALCULATE_CALORIES);
//                    mav.addObject("LISTVARIABLE", listVariables);
//                    mav.addObject("LISTPREVENTION", listPrevention);
//                    mav.addObject("LISTMEDICALRECORDDATA", listMedicalRecordData);

                    } else if (user.getRole().getId() == IDbConsts.IRoleType.ADMIN){
                        mav.setViewName("adminlistuser");
                    } else if (user.getRole().getId() == IDbConsts.IRoleType.NUTRITION){
                        mav.setViewName("nutriPatients");
                        List<Patient> patientList = patientService.getPatientByApponitmentDate();
                        LOGGER.info("listpatiens: " + patientList.size());
                        mav.addObject("LISTPATIENTS", patientList);
                    } else if (user.getRole().getId() == IDbConsts.IRoleType.DOCTOR_MANAGER) {
                        mav.setViewName("regimens");
                    }
//                else if (user.getRole().getName().equals("Staff")){
//                    mav.setViewName("stafflistdevice");
//                }
                } else if (user.getStatus() == IDbConsts.IAccountStatus.IN_ACTIVE){
//                    session.invalidate();
                    mav.setViewName("newPassword");
                    mav.addObject("USER", user);
                }

                return mav;
            }
            mav.setViewName("login");
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "loginMobile", method = RequestMethod.POST)
    @ResponseBody
    public PatientModel loginMobile(@RequestParam("username") final String username,
                                    @RequestParam("password") final String password) {
        LOGGER.info("login mobile");
        PatientModel patient = new PatientModel();
        Account userLogin = new Account();
        userLogin = accountService.checkLogin(username, password);
        if (userLogin != null) {

            Patient patientLogin = new Patient();
            patientLogin = patientService.getPatient(userLogin.getId());

            patient.setAccountId(userLogin.getId());
            patient.setPatientId(patientLogin.getId());
            patient.setEmail(userLogin.getEmail());
            patient.setFullname(userLogin.getFullName());
            patient.setStatus(userLogin.getStatus() + "");
        }
        return patient;
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    public String changePassword(@RequestParam("username") final String username,
                                 @RequestParam("newPassword") final String newPassword) {
        Account userLogin = accountService.changePassword(username, newPassword);
        if (userLogin != null) {
            return "200";
        }
        return "202";
    }


    /**
     * The logout mapping
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (null != session) {
                session.invalidate();
            }
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/createNewPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createNewPassword(@RequestBody LoginCredentialModel model,
                                          HttpSession session) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("model[{}]", model);
            Account account = accountService.changePassword(model);
            if (null != account){
                session.invalidate();
                return OK_STATUS;
            }
            return FAIL_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    @RequestMapping(value = "/invalidate")
    @ResponseBody
    public String invalidateSession(HttpSession httpSession) {
        httpSession.invalidate();
        return "OK";
    }

    @RequestMapping(value = "mail")
    @ResponseBody
    public String mailTo() throws UnsupportedEncodingException, MessagingException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            mailService.sendMail("kool.spammer@gmail.com", "New mail", "XUÂN YEEU DAU'");
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
        return "OK";
    }

    public class Variable {
        private String key;
        private String value;

        private Variable(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
