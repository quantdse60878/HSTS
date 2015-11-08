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
import vn.edu.fpt.hsts.bizlogic.model.FoodUnitModel;
import vn.edu.fpt.hsts.bizlogic.model.HisInforDateModel;
import vn.edu.fpt.hsts.bizlogic.service.AppointmentService;
import vn.edu.fpt.hsts.bizlogic.service.FoodService;
import vn.edu.fpt.hsts.bizlogic.service.MedicineService;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;

import java.util.List;

/**
 * Created by Aking on 11/1/2015.
 */
@Controller
public class AjaxController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping(value = "/medicineName/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> medicineList() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return medicineService.findAllMedicine();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "getFoodUnits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FoodUnitModel> getFoodUnits(@RequestParam("foodId") final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("foodId[{}]", foodId);
            if (foodId <= 0){
                return null;
            }
            return foodService.findUnitsByFoodId(foodId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "inforOfAppointmentDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HisInforDateModel inforOfAppointmentDate(@RequestParam("appointmentId") final int appointmentId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appoitmentID[{}]", appointmentId);
            if (appointmentId <= 0){
                return null;
            }
            HisInforDateModel hisInforDateModel = treatmentService.findInforByAppoitmentID(appointmentId);

            return hisInforDateModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
