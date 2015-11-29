package vn.edu.fpt.hsts.bizlogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 11/29/2015.
 */
public class FoodNutritionModel {
    private String foodName;
    private List<String> unitName = new ArrayList<String>();
    private List<Float> kCal = new ArrayList<Float>();
    private String valueString = "animalFat,animalProtein,calcium,fat,starch,protein,fiber,iron,sodium,vitaminB1,vitaminB2,vitaminC,vitaminPP,zinc";
    private List<String> value = new ArrayList<String>();

    public FoodNutritionModel() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public List<String> getUnitName() {
        return unitName;
    }

    public void setUnitName(List<String> unitName) {
        this.unitName = unitName;
    }

    public List<Float> getkCal() {
        return kCal;
    }

    public void setkCal(List<Float> kCal) {
        this.kCal = kCal;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
