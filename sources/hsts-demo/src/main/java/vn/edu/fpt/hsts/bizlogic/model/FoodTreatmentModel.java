package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class FoodTreatmentModel implements Serializable {

    private String time;
    private List<Food> listFood;

    public FoodTreatmentModel() {
        listFood = new ArrayList<Food>();
    }

    public FoodTreatmentModel(String timeEat, List<Food> listFood) {
        this.time = timeEat;
        this.listFood = listFood;
    }

    public String getTimeEat() {
        return time;
    }

    public void setTimeEat(String timeEat) {
        this.time = timeEat;
    }

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }


    public void addFood(String foodName, String quantitative) {
        listFood.add(new Food(foodName, quantitative));
    }

    class Food implements Serializable {
        private String foodName;
        private String quantitative;

        Food() {
        }

        Food(String foodName, String quantitative) {
            this.foodName = foodName;
            this.quantitative = quantitative;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getQuantitative() {
            return quantitative;
        }

        public void setQuantitative(String quantitative) {
            this.quantitative = quantitative;
        }
    }

}
