package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class FoodTreatmentModel implements Serializable {

        private String name;
        private String quantitative;
        private int numberOfTime;

    public FoodTreatmentModel(String name, String quantitative, int numberOfTime) {
        this.name = name;
        this.quantitative = quantitative;
        this.numberOfTime = numberOfTime;
    }

    public FoodTreatmentModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(String quantitative) {
        this.quantitative = quantitative;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }
}
