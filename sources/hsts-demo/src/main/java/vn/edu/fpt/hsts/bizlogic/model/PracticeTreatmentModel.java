package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class PracticeTreatmentModel implements Serializable {

        private String name;
        private String quantitative;
        private String advice;
        private int numberOfTime;

    public PracticeTreatmentModel(String name, String quantitative, String advice, int numberOfTime) {
        this.name = name;
        this.quantitative = quantitative;
        this.advice = advice;
        this.numberOfTime = numberOfTime;
    }

    public PracticeTreatmentModel() {
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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
