package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.NotifyWebPageModel;
import vn.edu.fpt.hsts.bizlogic.model.WebNotifyModel;
import vn.edu.fpt.hsts.bizlogic.service.NotifyService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Patient;

import java.util.List;

/**
 * Created by Aking on 10/12/2015.
 */
@Controller
public class AjaxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    NotifyService notifyService;

    @RequestMapping(value = "notifyWeb", method = RequestMethod.GET)
    @ResponseBody
    public List<WebNotifyModel> notifyAjax() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // TODO
            NotifyWebPageModel pageModel = new NotifyWebPageModel();
            pageModel.setData(WebNotifyModel.testData());
            pageModel.setSize(pageModel.getData().size());
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
