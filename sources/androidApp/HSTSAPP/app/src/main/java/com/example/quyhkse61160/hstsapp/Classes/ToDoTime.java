package com.example.quyhkse61160.hstsapp.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 10/6/2015.
 */
public class ToDoTime {
    private String name = "";
    private String quantitative = "";
    private List<String> numberOfTime = new ArrayList<>();
    private String advice = "";
    private String unit = "";

    public ToDoTime() {
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

    public List<String> getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(List<String> numberOfTime) {
        this.numberOfTime = numberOfTime;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
