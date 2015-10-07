package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fpt.hsts.bizlogic.model.TreatmentModel;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Controller
public class TreatmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentController.class);

    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping(value = "getTreatment", method = RequestMethod.POST)
    @ResponseBody
    public List<TreatmentModel> getTreatment(@RequestParam("patientId") final String patientId) {

        List<TreatmentModel> listTreatment = new ArrayList<TreatmentModel>();

        int id = Integer.parseInt(patientId);

        listTreatment = treatmentService.getTreatmentOfPatient(id);

        return listTreatment;
    }

}
