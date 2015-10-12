/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Treatment")
public class Treatment extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "treatment")
    private List<MedicineTreatment> medicineTreatmentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "treatment")
    private List<FoodTreatment> foodTreatmentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "treatment")
    private List<PracticeTreatment> practiceTreatmentList;

    /**
     *
     */
    private Date fromDate;

    /**
     *
     */
    private Date toDate;


    /**
     *
     */
    private String adviseFood;

    /**
     *
     */
    private String adviseMedicine;

    /**
     *
     */
    private String advisePractice;

    /**
     *
     */
    private byte status;


    private int caloriesBurnEveryday;

    public Treatment() {
    }

    public int getCaloriesBurnEveryday() {
        return caloriesBurnEveryday;
    }

    public void setCaloriesBurnEveryday(int caloriesBurnEveryday) {
        this.caloriesBurnEveryday = caloriesBurnEveryday;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(final Appointment appointment) {
        this.appointment = appointment;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(final Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(final Date toDate) {
        this.toDate = toDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(final byte status) {
        this.status = status;
    }

    public String getAdviseFood() {
        return adviseFood;
    }

    public void setAdviseFood(final String adviseFood) {
        this.adviseFood = adviseFood;
    }

    public String getAdviseMedicine() {
        return adviseMedicine;
    }

    public void setAdviseMedicine(final String adviseMedicine) {
        this.adviseMedicine = adviseMedicine;
    }

    public String getAdvisePractice() {
        return advisePractice;
    }

    public void setAdvisePractice(final String advisePractice) {
        this.advisePractice = advisePractice;
    }

    public List<MedicineTreatment> getMedicineTreatmentList() {
        return medicineTreatmentList;
    }

    public void setMedicineTreatmentList(List<MedicineTreatment> medicineTreatmentList) {
        this.medicineTreatmentList = medicineTreatmentList;
    }

    public List<FoodTreatment> getFoodTreatmentList() {
        return foodTreatmentList;
    }

    public void setFoodTreatmentList(List<FoodTreatment> foodTreatmentList) {
        this.foodTreatmentList = foodTreatmentList;
    }

    public List<PracticeTreatment> getPracticeTreatmentList() {
        return practiceTreatmentList;
    }

    public void setPracticeTreatmentList(List<PracticeTreatment> practiceTreatmentList) {
        this.practiceTreatmentList = practiceTreatmentList;
    }
}
