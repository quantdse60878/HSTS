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
import vn.edu.fpt.hsts.bizlogic.model.regimen.RegimenPageModel;
import vn.edu.fpt.hsts.bizlogic.service.RegimenService;
import vn.edu.fpt.hsts.common.IConsts;

/**
 * Created by Aking on 10/28/2015.
 */
@Controller
public class DrManagerController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DrManagerController.class);

    /**
     * The {@link RegimenService}.
     */
    @Autowired
    private RegimenService regimenService;


    @RequestMapping(value = "regimens", method = RequestMethod.GET)
    public ModelAndView regimensPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("regimens");

            return mav;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/regimen/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RegimenPageModel getRegimens(
            @RequestParam(value = "name", required = false, defaultValue = EMPTY) final String name,
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) final int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = UNLIMIT_PAGE_SIZE) final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("name[{}], page[{}], pageSize[{}]", name, page, pageSize);
            return regimenService.regimens(name, page, pageSize);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
