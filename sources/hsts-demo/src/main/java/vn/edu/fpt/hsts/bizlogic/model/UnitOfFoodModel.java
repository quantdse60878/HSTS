package vn.edu.fpt.hsts.bizlogic.model;


import vn.edu.fpt.hsts.persistence.entity.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 11/7/2015.
 */
public class UnitOfFoodModel implements Serializable  {

    private String foodName;
    private String foodUnit;
    private float caloriesEstimate;
    private String foodNutritionName;
    private String foodNutritionValue;

    public UnitOfFoodModel() {
    }

    public void setFoodNutritionName(String foodNutritionName) {
        this.foodNutritionName = foodNutritionName;
    }

    public void setFoodNutritionValue(String foodNutritionValue) {
        this.foodNutritionValue = foodNutritionValue;
    }

    public float getCaloriesEstimate() {
        return caloriesEstimate;
    }

    public void setCaloriesEstimate(float caloriesEstimate) {
        this.caloriesEstimate = caloriesEstimate;
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

    public String getFoodNutritionName() {
        return foodNutritionName;
    }

    public String getFoodNutritionValue() {
        return foodNutritionValue;
    }
}
