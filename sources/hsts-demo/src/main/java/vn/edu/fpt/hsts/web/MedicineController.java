/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/12/2015.
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
import vn.edu.fpt.hsts.bizlogic.model.MedicinePageModel;
import vn.edu.fpt.hsts.bizlogic.service.MedicineService;
import vn.edu.fpt.hsts.common.IConsts;

@Controller
public class MedicineController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineController.class);

    /**
     * The {@link MedicineService}.
     */
    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = "/medicine/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicinePageModel findMedicines(
            @RequestParam(value = "name", required = false, defaultValue = EMPTY) final String name,
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = PAGE_SIZE_5) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("name[{}], page[{}], pageSize[{}]", name, page, pageSize);
            return medicineService.findMedicine(name, page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
