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
import vn.edu.fpt.hsts.persistence.repo.FoodIngredientRepo;

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

    protected FoodIngredient isValid(final FoodIngredientModel foodIngredientModel){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            FoodIngredient foodIngredient = new FoodIngredient();
            Float tmp = Float.parseFloat(foodIngredientModel.getBreakfast());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setBreakfast(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getBreakTimeMorning());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setBreakTimeMorning(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getLunch());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setLunch(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getBreakTimeAfternoon());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setBreakTimeAfternoon(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getDinner());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setDinner(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getEatLateAtNight());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setEatLateAtNight(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getStarch());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setStarch(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getProtein());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setProtein(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getFat());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setFat(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getAnimalProtein());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setAnimalProtein(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getAnimalFat());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setAnimalFat(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getCalcium());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setCalcium(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getSodium());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setSodium(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getIron());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setIron(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getZinc());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setZinc(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminB1());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminB1(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminC());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminC(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminB2());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminB2(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getVitaminPP());
            if (tmp < 0) {
                return null;
            } else {
                foodIngredient.setVitaminPP(tmp);
            }
            tmp = Float.parseFloat(foodIngredientModel.getFiber());
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
}
