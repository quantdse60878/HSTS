package vn.edu.fpt.hsts.bizlogic.model;


import vn.edu.fpt.hsts.persistence.entity.Food;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 11/7/2015.
 */
public class UnitOfFoodModel implements Serializable  {

    private String foodName;
    private String foodUnit;
    private List<String> foodNutritionName;
    private List<String> foodNutritionValue;

    public UnitOfFoodModel() {
    }

    public UnitOfFoodModel(String foodName, String foodUnit, List<String> foodNutritionName, List<String> foodNutritionValue) {
        this.foodName = foodName;
        this.foodUnit = foodUnit;
        this.foodNutritionName = foodNutritionName;
        this.foodNutritionValue = foodNutritionValue;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(String foodUnit) {
        this.foodUnit = foodUnit;
    }

    public List<String> getFoodNutritionName() {
        return foodNutritionName;
    }

    public void setFoodNutritionName(List<String> foodNutritionName) {
        this.foodNutritionName = foodNutritionName;
    }

    public List<String> getFoodNutritionValue() {
        return foodNutritionValue;
    }

    public void setFoodNutritionValue(List<String> foodNutritionValue) {
        this.foodNutritionValue = foodNutritionValue;
    }
}
