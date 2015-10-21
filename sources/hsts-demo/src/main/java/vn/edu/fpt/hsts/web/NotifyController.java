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
import vn.edu.fpt.hsts.bizlogic.model.NotifyModel;
import vn.edu.fpt.hsts.bizlogic.model.WebNotifyModel;
import vn.edu.fpt.hsts.bizlogic.service.NotifyService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.Notify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Controller
public class NotifyController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private NotifyService notifyService;

    @RequestMapping(value = "notify", method = RequestMethod.POST)
    @ResponseBody
    public List<NotifyModel> getNotify(@RequestParam("receiverId") final String accountId) {
        List<NotifyModel> listNotifyReceiver = new ArrayList<NotifyModel>();
        int receiverId = Integer.parseInt(accountId);

        List<Notify> listNotify = notifyService.getNotifyByReceiverId(receiverId);
        for(int i = 0; i < listNotify.size(); i++) {
            Notify notifyItem = listNotify.get(i);
            NotifyModel notifyModel = new NotifyModel();
            notifyModel.setSenderId(notifyItem.getSender().getId());
            notifyModel.setReceiverId(notifyItem.getReceiver().getId());
            notifyModel.setStatus(notifyItem.getStatus());
            notifyModel.setType(notifyItem.getType());
            notifyModel.setNotifyId(notifyItem.getId());
            listNotifyReceiver.add(notifyModel);
        }
        LOGGER.info("Notify size--------------------------" + listNotifyReceiver.size());
        return listNotifyReceiver;
    }
    @RequestMapping(value = "hadGetNotify", method = RequestMethod.POST)
    @ResponseBody
    public String hadGetTreatment(@RequestParam("notifyId") final String notifyId) {
        notifyService.hadGetTreatment(Integer.parseInt(notifyId));
        return "success";
    }
    @RequestMapping(value = "sendNotifyToDoctor", method = RequestMethod.POST)
    @ResponseBody
    public String sendNotifyToDoctor(@RequestParam("patientId") final String patientId,
                                     @RequestParam("message") final String message) {

        notifyService.sendNotifyToDoctor(Integer.parseInt(patientId), message);

        return "success";
    }


    @RequestMapping(value = "notifyWeb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<WebNotifyModel> notifyAjax() throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return notifyService.findNotificationData();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "markAllReaded", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Void markAllReaded() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            notifyService.markAllNotificationAsReaded();
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
