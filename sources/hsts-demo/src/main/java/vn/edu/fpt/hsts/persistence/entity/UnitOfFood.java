package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by QUYHKSE61160 on 11/3/15.
 */
@Entity
public class UnitOfFood extends AbstractKeyEntity {

    // EAGER ?oi
    @ManyToOne
    @JoinColumn(name = "foodId", nullable = false)

    private Food food;

    private String unitName;

    private float caloriesEstimate;

    private String listElementNutritionName;

    private String listElementNutritionValue;

    public UnitOfFood() {
    }

    public String getListElementNutritionName() {
        return listElementNutritionName;
    }

    public void setListElementNutritionName(String listElementNutritionName) {
        this.listElementNutritionName = listElementNutritionName;
    }

    public String getListElementNutritionValue() {
        return listElementNutritionValue;
    }

    public void setListElementNutritionValue(String listElementNutritionValue) {
        this.listElementNutritionValue = listElementNutritionValue;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public float getCaloriesEstimate() {
        return caloriesEstimate;
    }

    public void setCaloriesEstimate(float caloriesEstimate) {
        this.caloriesEstimate = caloriesEstimate;
    }
}
