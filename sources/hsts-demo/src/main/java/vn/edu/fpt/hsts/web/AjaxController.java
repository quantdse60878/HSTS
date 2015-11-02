package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fpt.hsts.bizlogic.service.MedicineService;
import vn.edu.fpt.hsts.common.IConsts;

import java.util.List;

/**
 * Created by Aking on 11/1/2015.
 */
@Controller
public class AjaxController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = "/medicineName/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> medicineList() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return medicineService.findAllMedicine();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
