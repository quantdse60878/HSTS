package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import vn.edu.fpt.hsts.persistence.repo.FoodIngredientRepo;

import javax.transaction.Transactional;

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

    protected boolean isValid(final FoodIngredient foodIngredient){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (foodIngredient.getBreakfast() < 0){
                return false;
            }
            if (foodIngredient.getBreakTimeMorning() < 0){
                return false;
            }
            if (foodIngredient.getLunch() < 0){
                return false;
            }
            if (foodIngredient.getBreakTimeAfternoon() < 0){
                return false;
            }
            if (foodIngredient.getDinner() < 0){
                return false;
            }
            if (foodIngredient.getEatLateAtNight() < 0){
                return false;
            }
            if (foodIngredient.getStarch() < 0){
                return false;
            }
            if (foodIngredient.getProtein() < 0){
                return false;
            }
            if (foodIngredient.getFat() < 0){
                return false;
            }
            if (foodIngredient.getAnimalProtein() < 0){
                return false;
            }
            if (foodIngredient.getAnimalFat() < 0){
                return false;
            }
            if (foodIngredient.getCalcium() < 0){
                return false;
            }
            if (foodIngredient.getSodium() < 0){
                return false;
            }
            if (foodIngredient.getIron() < 0){
                return false;
            }
            if (foodIngredient.getZinc() < 0){
                return false;
            }
            if (foodIngredient.getVitaminB1() < 0){
                return false;
            }
            if (foodIngredient.getVitaminC() < 0){
                return false;
            }
            if (foodIngredient.getVitaminB2() < 0){
                return false;
            }
            if (foodIngredient.getVitaminPP() < 0){
                return false;
            }
            if (foodIngredient.getFiber() < 0){
                return false;
            }
            return true;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public boolean insertNewFoodIngredient(final Appointment appointment,
                                        final FoodIngredient foodIngredient){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointment[{}], foodIngredient[{}]", appointment, foodIngredient);
            if (isValid(foodIngredient)){
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
}
