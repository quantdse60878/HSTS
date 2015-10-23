package vn.edu.fpt.hsts.web;

/**
 * Created by Aking on 9/27/2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.DoctorModel;
import vn.edu.fpt.hsts.bizlogic.service.AccountService;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.criteria.PatientCriteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * Register controller, for processing Register patient, account.
 */
@Controller
public class RegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;


    /**
     * The {@link PatientService}
     */
    @Autowired
    private PatientService patientService;

    /**
     * The {@link DoctorService}.
     */
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/validateNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> validateData(final HttpServletRequest request) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Enumeration<String> params = request.getParameterNames();
            // TODO implement method for checking dynamic value for all common variable in system
            while(params.hasMoreElements()) {
                final String p = params.nextElement();
                if(p.equals("weight")) {
                    final float data = Float.parseFloat(request.getParameter(p));
                    if(data > 200){
                        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
                    }
                    LOGGER.info("data[{}]", data);
                }
            }
            return new ResponseEntity<String>(HttpStatus.OK);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
