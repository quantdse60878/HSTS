package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class FoodTreatmentModel {

    private String timeEat;
    private List<Food> listFood;

    public FoodTreatmentModel() {
    }

    public FoodTreatmentModel(String timeEat, List<Food> listFood) {
        this.timeEat = timeEat;
        this.listFood = listFood;
    }

    public String getTimeEat() {
        return timeEat;
    }

    public void setTimeEat(String timeEat) {
        this.timeEat = timeEat;
    }

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }

    class Food {
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
