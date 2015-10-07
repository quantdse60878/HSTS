package vn.edu.fpt.hsts.bizlogic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class TreatmentModel {

    private String illnessName;
    private Date nextAppointment = new Date();
    private Date fromDate = new Date();
    private Date toDate = new Date();
    private String advice;
    private List<FoodTreatmentModel> listFoodTreatment = new ArrayList<FoodTreatmentModel>();
    private List<MedicineTreatmentModel> listMedicineTreatment = new ArrayList<MedicineTreatmentModel>();
    private List<PracticeTreatmentModel> listPracticeTreatment = new ArrayList<PracticeTreatmentModel>();

    public TreatmentModel() {
    }

    public TreatmentModel(String illnessName, Date nextAppointment, Date fromDate, Date toDate, String advice, List<FoodTreatmentModel> listFoodTreatment, List<MedicineTreatmentModel> listMedicineTreatment, List<PracticeTreatmentModel> listPracticeTreatment) {
        this.illnessName = illnessName;
        this.nextAppointment = nextAppointment;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.advice = advice;
        this.listFoodTreatment = listFoodTreatment;
        this.listMedicineTreatment = listMedicineTreatment;
        this.listPracticeTreatment = listPracticeTreatment;
    }

    public String getIllnessName() {
        return illnessName;
    }

    public void setIllnessName(String illnessName) {
        this.illnessName = illnessName;
    }

    public Date getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(Date nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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
