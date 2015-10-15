package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class MedicineTreatmentModel implements Serializable {

        private String name;
        private String quantitative;
        private String unit;
        private String advice;
        private int numberOfTime;

    public MedicineTreatmentModel(String name, String quantitative, String unit, String advice, int numberOfTime) {
        this.name = name;
        this.quantitative = quantitative;
        this.unit = unit;
        this.advice = advice;
        this.numberOfTime = numberOfTime;
    }

    public MedicineTreatmentModel() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }
}
