/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
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
import vn.edu.fpt.hsts.bizlogic.model.FoodPageModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticePageModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticePhaseModel;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.bizlogic.service.PracticeService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;

@Controller
public class PracticeController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeController.class);


    /**
     * The {@link PracticeService}.
     */
    @Autowired
    private PracticeService practiceService;

    /**
     * The {@link PhaseService}.
     */
    @Autowired
    private PhaseService phaseService;

    @RequestMapping(value = "/practice/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PracticePageModel findFoods(@RequestParam(value = "name", required = false, defaultValue = EMPTY) final String name,
                                   @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = PAGE_SIZE_5) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return practiceService.findPractices(name, page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/practice/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PracticePhaseModel find(@RequestParam("id") final int practicePhaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("practicePhaseId[{}]", practicePhaseId);
            return phaseService.findPracticePhase(practicePhaseId);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/practice/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPracticeToPhase(@RequestParam("phaseId") final int phaseId,
                                     @RequestParam("practiceId") final int practiceId,
                                     @RequestParam("numberOfTime") final int numberOfTime,
                                     @RequestParam("timeDuration") final String timeDuration,
                                     @RequestParam(value = "advice", required = false, defaultValue = EMPTY) final String advice) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.addPracticeToPhase(phaseId, practiceId, timeDuration, numberOfTime, advice);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/practice/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletePracticeToPhase(@RequestParam("id") final int praticePhaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.deletePracticeToPhase(praticePhaseId);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

//    @RequestMapping(value = "/phase/medicine/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String updatePracticeToPhase(@RequestParam("id") final int id,
//                                        @RequestParam("timeDuration") final String timeDuration,
//                                        @RequestParam("numberOfTime") final int numberOfTime,
//                                        @RequestParam("advice"))
}
