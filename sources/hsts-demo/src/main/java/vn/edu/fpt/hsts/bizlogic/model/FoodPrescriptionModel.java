package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class FoodPrescriptionModel {
    private String food;
    private String foodTime;
    private String foodQuantity;
    private String foodNote;

    public FoodPrescriptionModel() {
    }

    public FoodPrescriptionModel(String food, String foodTime, String foodQuantity, String foodNote) {
        this.food = food;
        this.foodTime = foodTime;
        this.foodQuantity = foodQuantity;
        this.foodNote = foodNote;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodNote() {
        return foodNote;
    }

    public void setFoodNote(String foodNote) {
        this.foodNote = foodNote;
    }

    @Override
    public String toString() {
        return "FoodPrescriptionModel{" +
                "food='" + food + '\'' +
                ", foodTime='" + foodTime + '\'' +
                ", foodQuantity='" + foodQuantity + '\'' +
                ", foodNote='" + foodNote + '\'' +
                '}';
    }
}
