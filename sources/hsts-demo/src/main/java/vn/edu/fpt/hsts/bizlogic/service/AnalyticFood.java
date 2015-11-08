package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.UnitOfFoodModel;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;
import vn.edu.fpt.hsts.persistence.repo.UnitOfFoodRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 11/7/2015.
 */

@Service
public class AnalyticFood {

    /**
     * The {@link FoodService}.
     */
    @Autowired
    private FoodService foodService;

    /**
     * The {@link UnitOfFoodService}.
     */
    @Autowired
    private UnitOfFoodService unitOfFoodService;

    public List<UnitOfFoodModel> getAllDataOfFood(){
        List<UnitOfFoodModel> list = new ArrayList<UnitOfFoodModel>();
        List<Food> foods = foodService.getAllFood();
        List<UnitOfFood> unitOfFoods = unitOfFoodService.findAllUnitOfFoods();
        for(Food food : foods){
            UnitOfFoodModel model = new UnitOfFoodModel();
            model.setFoodName(food.getName());
            for(UnitOfFood unitOfFood : unitOfFoods){
                if(food.getId() == unitOfFood.getFood().getId()){
                    model.setFoodUnit(unitOfFood.getUnitName());
                    model.setCaloriesEstimate(unitOfFood.getCaloriesEstimate());
                    model.getFoodNutritionName().add(unitOfFood.getListElementNutritionName());
                    model.getFoodNutritionValue().add(unitOfFood.getListElementNutritionValue());
                }
            }
            list.add(model);
        }
        return list;
    }

}
