package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class TreatmentModel implements Serializable {

    private String illnessName;
    private String nextAppointment;
    private int appointmentId;
    private String fromDate;
    private String toDate;
    private String adviseFood;
    private String adviseMedicine;
    private String advicePractice;
    private List<FoodTreatmentModel> listFoodTreatment = new ArrayList<FoodTreatmentModel>();
    private List<MedicineTreatmentModel> listMedicineTreatment = new ArrayList<MedicineTreatmentModel>();
    private List<PracticeTreatmentModel> listPracticeTreatment = new ArrayList<PracticeTreatmentModel>();

    public TreatmentModel() {
    }

    public TreatmentModel(String illnessName, String nextAppointment, String fromDate, String toDate, String adviseFood, String adviseMedicine, String advicePractice, List<FoodTreatmentModel> listFoodTreatment, List<MedicineTreatmentModel> listMedicineTreatment, List<PracticeTreatmentModel> listPracticeTreatment) {
        this.illnessName = illnessName;
        this.nextAppointment = nextAppointment;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.adviseFood = adviseFood;
        this.adviseMedicine = adviseMedicine;
        this.advicePractice = advicePractice;
        this.listFoodTreatment = listFoodTreatment;
        this.listMedicineTreatment = listMedicineTreatment;
        this.listPracticeTreatment = listPracticeTreatment;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getAdviseFood() {
        return adviseFood;
    }

    public void setAdviseFood(String adviseFood) {
        this.adviseFood = adviseFood;
    }

    public String getAdviseMedicine() {
        return adviseMedicine;
    }

    public void setAdviseMedicine(String adviseMedicine) {
        this.adviseMedicine = adviseMedicine;
    }

    public String getAdvicePractice() {
        return advicePractice;
    }

    public void setAdvicePractice(String advicePractice) {
        this.advicePractice = advicePractice;
    }

    public List<FoodTreatmentModel> getListFoodTreatment() {
        return listFoodTreatment;
    }

    public void setListFoodTreatment(List<FoodTreatmentModel> listFoodTreatment) {
        this.listFoodTreatment = listFoodTreatment;
    }

    public List<MedicineTreatmentModel> getListMedicineTreatment() {
        return listMedicineTreatment;
    }

    public void setListMedicineTreatment(List<MedicineTreatmentModel> listMedicineTreatment) {
        this.listMedicineTreatment = listMedicineTreatment;
    }

    public List<PracticeTreatmentModel> getListPracticeTreatment() {
        return listPracticeTreatment;
    }

    public void setListPracticeTreatment(List<PracticeTreatmentModel> listPracticeTreatment) {
        this.listPracticeTreatment = listPracticeTreatment;
    }
}
