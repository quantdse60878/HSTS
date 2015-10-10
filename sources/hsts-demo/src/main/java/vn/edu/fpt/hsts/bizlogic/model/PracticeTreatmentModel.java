package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class PracticeTreatmentModel implements Serializable {

        private String name;
        private int quantitative;
        private int numberOfTime;

    public PracticeTreatmentModel(String name, int quantitative, int numberOfTime) {
        this.name = name;
        this.quantitative = quantitative;
        this.numberOfTime = numberOfTime;
    }

    public PracticeTreatmentModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(int quantitative) {
        this.quantitative = quantitative;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }
}
