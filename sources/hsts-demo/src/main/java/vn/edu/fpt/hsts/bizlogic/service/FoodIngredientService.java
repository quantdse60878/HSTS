package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.FoodIngredientModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.FoodIngredientRepo;
import vn.edu.fpt.hsts.persistence.repo.FoodTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.UnitOfFoodRepo;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Aking on 10/21/2015.
 */
@Service
public class FoodIngredientService {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodIngredientService.class);

    @Autowired
    FoodIngredientRepo foodIngredientRepo;

    @Autowired
    private FoodTreatmentRepo foodTreatmentRepo;

    @Autowired
    private UnitOfFoodRepo unitOfFoodRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    protected FoodIngredient isValid(final FoodIngredientModel foodIngredientModel){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            FoodIngredient foodIngredient = new FoodIngredient();
            Float tmp = Float.parseFloat(foodIngredientModel.getBreakfast());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setBreakfast(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getBreakTimeMorning());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setBreakTimeMorning(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getLunch());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setLunch(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getBreakTimeAfternoon());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setBreakTimeAfternoon(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getDinner());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setDinner(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getEatLateAtNight());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setEatLateAtNight(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getStarch());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setStarch(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getProtein());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setProtein(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getFat());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setFat(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getAnimalProtein());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setAnimalProtein(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getAnimalFat());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setAnimalFat(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getCalcium());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setCalcium(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getSodium());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setSodium(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getIron());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setIron(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getZinc());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setZinc(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminB1());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminB1(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminC());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminC(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminB2());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminB2(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminPP());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminPP(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getFiber());
            LOGGER.info("tmp[{}]", tmp);
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setFiber(tmp);
            }
            return foodIngredient;
        }catch (Exception e){
            LOGGER.info("Parse Exception: {}", null, e.getMessage());
            return null;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public boolean insertNewFoodIngredient(final Appointment appointment,
                                        final FoodIngredientModel foodIngredientModel){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointment[{}], foodIngredientModel[{}]", appointment, foodIngredientModel);
            FoodIngredient foodIngredient = isValid(foodIngredientModel);
            LOGGER.info("foodIngredient[{}]",foodIngredient);
            if (foodIngredient != null){
                foodIngredient.setAppointment(appointment);
                foodIngredientRepo.save(foodIngredient);
                // flush to db
                foodIngredientRepo.flush();
                return true;
            } else {
              return false;
            }
        } catch (Exception e) {
            LOGGER.info("Error while making new FoodIngredient: {}", null, e.getMessage());
            return false;
        }finally {
            LOGGER.info(IConsts.END_METHOD);

        }
    }

    public FoodIngredient findFoodIngredientByAppoiment(Appointment appointment) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try{
            LOGGER.info("appointment[{}]", appointment);
            List<FoodIngredient> foodIngredients = foodIngredientRepo.findLastFoodIngredientByAppointment(appointment);
            if (null != foodIngredients && !foodIngredients.isEmpty()) {
                return foodIngredients.get(0);
            }
            return null;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public FoodIngredientModel createFoodIngredientModelFromData(final Appointment appointment){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointment[{}]", appointment);
            FoodIngredientModel foodIngredientModel = new FoodIngredientModel();
            Appointment oldAppointment = appointmentRepo.findParentAppointment(appointment.getId());
            if (null != oldAppointment){
                List<FoodTreatment> foodTreatments = foodTreatmentRepo.findFoodTreatmentByAppointmentId(oldAppointment.getId());
                FoodTreatment foodTreatment;
                UnitOfFood unitOfFood;
                float totalBre = 0;
                float totalLun = 0;
                float totalDin = 0;
                float totalBreTiM = 0;
                float totalBreTiA = 0;
                float totalBreTiN = 0;
                for (int i = 0; i < foodTreatments.size(); i++) {
                    foodTreatment = foodTreatments.get(i);
                    unitOfFood = unitOfFoodRepo.findByFoodIdAndUnit(foodTreatment.getFood(), foodTreatment.getUnitName());
                    switch (foodTreatment.getNumberOfTime()){
                        case 1: totalBre = totalBre + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            break;
                        case 2: totalBre = totalBre + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalLun = totalLun + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            break;
                        case 3: totalBre = totalBre + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalLun = totalLun + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalDin = totalDin + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            break;
                        case 4: totalBre = totalBre + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalLun = totalLun + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalDin = totalDin + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalBreTiM = totalBreTiM + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            break;
                        case 5: totalBre = totalBre + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalLun = totalLun + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalDin = totalDin + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalBreTiM = totalBreTiM + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalBreTiA = totalBreTiA + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            break;
                        case 6: totalBre = totalBre + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalLun = totalLun + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalDin = totalDin + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalBreTiM = totalBreTiM + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalBreTiA = totalBreTiA + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            totalBreTiN = totalBreTiN + foodTreatment.getQuantitative()*unitOfFood.getCaloriesEstimate();
                            break;
                    }
                }
                foodIngredientModel.setBreakfast(String.valueOf(totalBre));
                foodIngredientModel.setBreakTimeAfternoon(String.valueOf(totalBreTiA));
                foodIngredientModel.setBreakTimeMorning(String.valueOf(totalBreTiM));
                foodIngredientModel.setLunch(String.valueOf(totalLun));
                foodIngredientModel.setDinner(String.valueOf(totalDin));
                foodIngredientModel.setEatLateAtNight(String.valueOf(totalBreTiN));
            }

            return foodIngredientModel;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
