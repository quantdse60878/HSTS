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
import vn.edu.fpt.hsts.bizlogic.model.FoodPhaseModel;
import vn.edu.fpt.hsts.bizlogic.service.FoodService;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.common.IConsts;

@Controller
public class FoodController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

    /**
     * The {@link FoodService}.
     */
    @Autowired
    private FoodService foodService;

    /**
     * The {@link PhaseService}.
     */
    @Autowired
    private PhaseService phaseService;

    @RequestMapping(value = "/food/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FoodPageModel findFoods(@RequestParam(value = "name", required = false, defaultValue = EMPTY) final String name,
                                    @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = PAGE_SIZE_5) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return foodService.findFoods(name, page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/food/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FoodPhaseModel detail(@RequestParam("id") final int foodPhaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("foodPhaseId[{}]", foodPhaseId);
            return phaseService.findFoodPhase(foodPhaseId);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
