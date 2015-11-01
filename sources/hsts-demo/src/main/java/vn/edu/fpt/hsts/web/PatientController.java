/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/1/2015.
 */
package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedPageModel;
import vn.edu.fpt.hsts.bizlogic.service.PatientService;
import vn.edu.fpt.hsts.common.IConsts;

@Controller
public class PatientController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    /**
     * The {@link PatientService}.
     */
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/patient/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientExtendedPageModel patientList(@RequestParam(value = "keyword", required = false, defaultValue = EMPTY) final String keyword,
                                                @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = PAGE_SIZE_5) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("keyword[{}], page[{}], pageSize[{}]", keyword, page, pageSize);
            return patientService.findPatients(keyword, page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/patient/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientExtendedModel getPatient(@RequestParam(value = "barcode") final String barcode) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("barcode[{}]", barcode);
            return patientService.findPatientByBarcode(barcode);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
