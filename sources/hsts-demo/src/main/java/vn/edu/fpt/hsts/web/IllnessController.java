/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/18/2015.
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
import vn.edu.fpt.hsts.bizlogic.model.IllnessModel;
import vn.edu.fpt.hsts.bizlogic.service.DoctorService;
import vn.edu.fpt.hsts.bizlogic.service.IllnessService;
import vn.edu.fpt.hsts.common.IConsts;

import java.util.List;

@Controller
public class IllnessController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IllnessController.class);

    /**
     * The {@link IllnessService}.
     */
    @Autowired
    private IllnessService illnessService;

    @Autowired
    private DoctorService doctorService;


    @RequestMapping(value = "/illnessName/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> illnessList(@RequestParam(value = "searchString", required = false, defaultValue = EMPTY) final String searchString,
                                    @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = UNLIMIT_PAGE_SIZE) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Create No illness if not
            doctorService.createNoIllness();

            return illnessService.findAllIllnessName(searchString, page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/clinicalSymptom/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> symptomList() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return illnessService.findDefaultClinicalSymptom();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

}
