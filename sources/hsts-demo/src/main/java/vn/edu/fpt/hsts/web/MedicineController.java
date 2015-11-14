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
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhaseModel;
import vn.edu.fpt.hsts.bizlogic.service.MedicineService;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;

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

    /**
     * The {@link PhaseService}.
     */
    @Autowired
    private PhaseService phaseService;


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

    @RequestMapping(value = "/phase/medicine/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addMedicineToPhase(@RequestParam("phaseId") final int phaseId,
                                     @RequestParam("medicineId") final int medicineId,
                                     @RequestParam("numberOfTime") final int numberOfTime,
                                     @RequestParam("quantitative") final int quantitative,
                                     @RequestParam(value = "advice", required = false, defaultValue = EMPTY) final String advice) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.addMedicineToPhase(phaseId, medicineId, numberOfTime, quantitative, advice);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/medicine/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateMedicineToPhase(@RequestParam("id") final int id,
                                        @RequestParam("numberOfTime") final int numberOfTime,
                                        @RequestParam("quantitative") final int quantitative,
                                        @RequestParam(value = "advice", required = false, defaultValue = EMPTY) final String advice) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.updateMedicineToPhase(id, numberOfTime, quantitative, advice);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/medicine/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteMedicineToPhase(@RequestParam("id") final int medicinePhaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.deleteMedicineToPhase(medicinePhaseId);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/medicine/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicinePhaseModel find(@RequestParam("id") final int medicinePhaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return phaseService.find(medicinePhaseId);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
