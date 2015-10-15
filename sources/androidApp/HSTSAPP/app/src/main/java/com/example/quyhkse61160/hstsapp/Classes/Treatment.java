package com.example.quyhkse61160.hstsapp.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 10/8/2015.
 */
public class Treatment {
    private String illnessName ="";
    private String nextAppointment ="";
    private String fromDate ="";
    private String toDate ="";
    private String caloriesBurnEveryday = "";
    private List<ToDoTime> listFoodTreatment = new ArrayList<>();
    private List<ToDoTime> listMedicineTreatment = new ArrayList<>();
    private List<ToDoTime> listPracticeTreatment = new ArrayList<>();

    public Treatment() {
    }

    public String getIllnessName() {
        return illnessName;
    }

    public void setIllnessName(String illnessName) {
        this.illnessName = illnessName;
    }

    public String getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(String nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<ToDoTime> getListFoodTreatment() {
        return listFoodTreatment;
    }

    public void setListFoodTreatment(List<ToDoTime> listFoodTreatment) {
        this.listFoodTreatment = listFoodTreatment;
    }

    public List<ToDoTime> getListMedicineTreatment() {
        return listMedicineTreatment;
    }

    public void setListMedicineTreatment(List<ToDoTime> listMedicineTreatment) {
        this.listMedicineTreatment = listMedicineTreatment;
    }

    public List<ToDoTime> getListPracticeTreatment() {
        return listPracticeTreatment;
    }

    public void setListPracticeTreatment(List<ToDoTime> listPracticeTreatment) {
        this.listPracticeTreatment = listPracticeTreatment;
    }

    public String getCaloriesBurnEveryday() {
        return caloriesBurnEveryday;
    }

    public void setCaloriesBurnEveryday(String caloriesBurnEveryday) {
        this.caloriesBurnEveryday = caloriesBurnEveryday;
    }
}
