package vn.edu.fpt.hsts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fpt.hsts.bizlogic.service.MedicalRecordDataService;

import java.util.Date;

/**
 * Created by QUYHKSE61160 on 10/8/15.
 */
@Controller
public class MedicalRecordDataController {

    @Autowired
    private MedicalRecordDataService medicalRecordDataService;

    @RequestMapping(value = "sendMedicalData", method = RequestMethod.POST)
    @ResponseBody
    public String sendMedicalData(@RequestParam("appointmentId") final String appointmentId,
                                  @RequestParam("numberOfStep") final String numberofstep,
                                  @RequestParam("collectDate") final Date collectDate) {

        int appointmentIdINT = Integer.parseInt(appointmentId);
        int numberofstepINT = Integer.parseInt(numberofstep);

        medicalRecordDataService.sendMedicalData(appointmentIdINT, numberofstepINT, collectDate);

        return "success";
    }

}
