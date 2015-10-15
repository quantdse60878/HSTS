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
    private int caloriesBurnEveryday;
    private List<FoodTreatmentModel> listFoodTreatment = new ArrayList<FoodTreatmentModel>();
    private List<MedicineTreatmentModel> listMedicineTreatment = new ArrayList<MedicineTreatmentModel>();
    private List<PracticeTreatmentModel> listPracticeTreatment = new ArrayList<PracticeTreatmentModel>();

    public TreatmentModel() {
    }

    public TreatmentModel(String illnessName, String nextAppointment, String fromDate, String toDate, List<FoodTreatmentModel> listFoodTreatment, List<MedicineTreatmentModel> listMedicineTreatment, List<PracticeTreatmentModel> listPracticeTreatment) {
        this.illnessName = illnessName;
        this.nextAppointment = nextAppointment;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.listFoodTreatment = listFoodTreatment;
        this.listMedicineTreatment = listMedicineTreatment;
        this.listPracticeTreatment = listPracticeTreatment;
    }

    public int getCaloriesBurnEveryday() {
        return caloriesBurnEveryday;
    }

    public void setCaloriesBurnEveryday(int caloriesBurnEveryday) {
        this.caloriesBurnEveryday = caloriesBurnEveryday;
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
