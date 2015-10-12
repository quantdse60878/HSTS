/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.service.FoodService;
import vn.edu.fpt.hsts.bizlogic.service.IllnessService;
import vn.edu.fpt.hsts.bizlogic.service.MedicineService;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.bizlogic.service.PracticeService;
import vn.edu.fpt.hsts.bizlogic.service.TreatmentService;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.Practice;
import vn.edu.fpt.hsts.web.config.ControllerParam;

import java.util.List;

public class AbstractController implements ControllerParam {
    /**
     * The {@link TreatmentService}.
     */
    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private IllnessService illnessService;

    @Autowired
    private PhaseService phaseService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private PracticeService practiceService;

    public void initDataPrescription(ModelAndView mav){
        // Get config time
        final String[] timeArr = treatmentService.getMedicineTimeConfig();
        mav.addObject("TIMES", timeArr);
        // get illnessList
        List<Illness> illnessList = illnessService.getAllIllness();
        mav.addObject("ILLNESSES", illnessList);
        //get medicineList
        List<Medicine> medicineList = medicineService.getAllMedicine();
        mav.addObject("MEDICINES", medicineList);
        //get foodList
        List<Food> foodList = foodService.getAllFood();
        mav.addObject("FOODS", foodList);
        //get practiceList
        List<Practice> practiceList = practiceService.getAllPractice();
        mav.addObject("PRACTICES", practiceList);
    }

    public void notify(ModelAndView mav, Boolean result,String method, String mess){
        // Set name of action
        mav.addObject("METHOD", method);
        // Set message notify
        mav.addObject("MESSAGE", mess);
        if (result){
            // Set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "info");
        }else {
            // Set type. sussces TYPE = info, fail TYPE = danger
            mav.addObject("TYPE", "danger");
        }
    }
}
