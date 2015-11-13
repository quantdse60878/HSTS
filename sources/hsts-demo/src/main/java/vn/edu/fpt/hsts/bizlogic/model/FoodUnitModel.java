package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 11/5/2015.
 */
public class FoodUnitModel {
    private int foodUnitId;
    private String unitName;

    public FoodUnitModel(int foodUnitId, String unitName) {
        this.foodUnitId = foodUnitId;
        this.unitName = unitName;
    }

    public FoodUnitModel() {
    }

    public int getFoodUnitId() {
        return foodUnitId;
    }

    public void setFoodUnitId(int foodUnitId) {
        this.foodUnitId = foodUnitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
