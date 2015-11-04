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

    @ManyToOne
    @JoinColumn(name = "foodId", nullable = false)
    private Food food;
    private String unitName;
    private int caloriesEstimate;

    public UnitOfFood() {
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

    public int getCaloriesEstimate() {
        return caloriesEstimate;
    }

    public void setCaloriesEstimate(int caloriesEstimate) {
        this.caloriesEstimate = caloriesEstimate;
    }
}
