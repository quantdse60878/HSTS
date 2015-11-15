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
import vn.edu.fpt.hsts.bizlogic.PracticePhasePageModel;
import vn.edu.fpt.hsts.bizlogic.model.FoodPhasePageModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhasePageModel;
import vn.edu.fpt.hsts.bizlogic.model.regimen.PhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.regimen.PhasePageModel;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.bizlogic.service.RegimenService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Phase;

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
    public ModelAndView phaseDetail(@RequestParam("id") final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("detailPhase");

            // Set info data
            final Phase phase = phaseService.findByID(phaseId);
            final PhaseModel model = new PhaseModel();
            model.fromEntity(phase);

            mav.addObject("PHASE", model);
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

    @RequestMapping(value = "/phase/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String update(@RequestParam("phaseId") final int phaseId,
                              @RequestParam("numberDay") final int numberDay) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}], numberDay[{}]", phaseId, numberDay);
            try {
                phaseService.updatePhase(phaseId, numberDay);
            } catch (Exception e) {
                return FAIL_STATUS;
            }
            return OK_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PhaseModel findPhase(@RequestParam("phaseId") final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            return phaseService.findPhase(phaseId);
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
    public String deletePhase(@RequestParam("id") final int phaseId,
                              @RequestParam("regimenId") final int regimenId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}], regimenId[{}] ", phaseId, regimenId);
            phaseService.deletePhase(phaseId);
            phaseService.reorderingPhase(regimenId);
            return OK_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/medicines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicinePhasePageModel findMedicinesByPhaseId(@RequestParam("phaseId") final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            return phaseService.getMedicinesByPhase(phaseId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    @RequestMapping(value = "/phase/practices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PracticePhasePageModel findPracticesByPhaseId(@RequestParam("phaseId") final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            return phaseService.getPracticesByPhase(phaseId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/foods", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FoodPhasePageModel findFoodsByPhaseId(@RequestParam("phaseId") final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            return phaseService.getFoodsByPhase(phaseId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
