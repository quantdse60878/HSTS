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
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.regimen.PhasePageModel;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.bizlogic.service.RegimenService;
import vn.edu.fpt.hsts.common.IConsts;

@Controller
public class PhaseController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PhaseController.class);

    /**
     * The {@link RegimenService}.
     */
    @Autowired
    private RegimenService regimenService;

    /**
     * The {@link PhaseService}.
     */
    @Autowired
    private PhaseService phaseService;


    @RequestMapping(value = "/detailPhase", method = RequestMethod.GET)
    public ModelAndView phaseDetail(@RequestParam("phaseId") final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("phaseDetail");

            // Set info data
            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PhasePageModel phaseInfo(@RequestParam("regimenId") final int regimenId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("regimenId[{}]", regimenId);
            return regimenService.phases(regimenId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createPhase(@RequestParam("regimenId") final int regimenId,
                              @RequestParam("numberDay") final int numberDay) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("regimenId[{}], numberDay[{}]", regimenId, numberDay);
            phaseService.createNewPhase(regimenId, numberDay);
            return OK_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createPhase(@RequestParam("phaseId") final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            //TODO
            return OK_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
