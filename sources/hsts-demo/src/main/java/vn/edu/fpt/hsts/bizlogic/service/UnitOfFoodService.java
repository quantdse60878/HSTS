package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;
import vn.edu.fpt.hsts.persistence.repo.UnitOfFoodRepo;

import java.util.List;

/**
 * Created by Man Huynh Khuong on 11/7/2015.
 */

@Service
public class UnitOfFoodService {

    /**
     * The {@link FoodService}.
     */
    @Autowired
    private UnitOfFoodRepo unitOfFoodRepo;

    public List<UnitOfFood> findAllUnitOfFoods(){
        return unitOfFoodRepo.findAll();
    }

    public void createUnitOfFood(Food food, String Kcal,String unit,String animalFat,String animalProtein,String calcium,
                                 String lipid, String starch,String protein,String fiber,String iron,String sodium,String vitaminB1,
                                 String vitaminB2,String vitaminC,String vitaminPP,String zinc) {
        UnitOfFood unitOfFood = new UnitOfFood();
        unitOfFood.setFood(food);
        unitOfFood.setCaloriesEstimate(Float.parseFloat(Kcal));
        unitOfFood.setUnitName(unit);
        unitOfFood.setListElementNutritionName("animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc");
        String nutrition = "";
        nutrition += animalFat + ",";
        nutrition += animalProtein + ",";
        nutrition += calcium + ",";
        nutrition += lipid + ",";
        nutrition += starch + ",";
        nutrition += protein + ",";
        nutrition += fiber + ",";
        nutrition += iron + ",";
        nutrition += sodium + ",";
        nutrition += vitaminB1 + ",";
        nutrition += vitaminB2 + ",";
        nutrition += vitaminC + ",";
        nutrition += vitaminPP + ",";
        nutrition += zinc;
        unitOfFood.setListElementNutritionValue(nutrition);
        unitOfFoodRepo.save(unitOfFood);
    }

    public List<UnitOfFood> findUnitOfFoodsByFoodID(int id) {
        return unitOfFoodRepo.findByFoodId(id);
    }

    public void updateUnitOfFood(Food food, String kcal, String unit, String animalFat, String animalProtein, String calcium,
                                 String lipid, String starch, String protein, String fiber, String iron, String sodium,
                                 String vitaminB1, String vitaminB2, String vitaminC, String vitaminPP, String zinc) {
        List<UnitOfFood> unitOfFoods = unitOfFoodRepo.findByFoodId(food.getId());
        String nutrition = "";
        nutrition += animalFat + ",";
        nutrition += animalProtein + ",";
        nutrition += calcium + ",";
        nutrition += lipid + ",";
        nutrition += starch + ",";
        nutrition += protein + ",";
        nutrition += fiber + ",";
        nutrition += iron + ",";
        nutrition += sodium + ",";
        nutrition += vitaminB1 + ",";
        nutrition += vitaminB2 + ",";
        nutrition += vitaminC + ",";
        nutrition += vitaminPP + ",";
        nutrition += zinc;
        for(UnitOfFood item : unitOfFoods){
            if(item.getUnitName().equalsIgnoreCase(unit)){
                item.setFood(food);
                item.setCaloriesEstimate(Float.parseFloat(kcal));
                item.setListElementNutritionName("animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc");
                item.setListElementNutritionValue(nutrition);
                unitOfFoodRepo.save(item);
                return;
            }
        }
        createUnitOfFood(food, kcal, unit, animalFat, animalProtein, calcium, lipid, starch, protein, fiber, iron,
                sodium, vitaminB1, vitaminB2, vitaminC, vitaminPP, zinc);
    }
}
