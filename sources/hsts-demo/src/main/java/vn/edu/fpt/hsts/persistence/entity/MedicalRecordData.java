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
import java.util.Date;

@Entity
public class MedicalRecordData extends AbstractKeyEntity {

    /**
     * The medical record.
     */
    @ManyToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

    /**
     * Collected time.
     */
    @JoinColumn(name = "dateCollectData")
    private Date collectedDate;


    /**
     * Number of steps.
     */
    private int numberOfStep;

    /**
     * Calories burned.
     */
    private int calories;

    /**
     *
     */
    private float distance;


    private int ratioCompletePractice;

    /**
     *
     */
    private byte type;

    public MedicalRecordData() {
    }

    public int getRatioCompletePractice() {
        return ratioCompletePractice;
    }

    public void setRatioCompletePractice(int ratioCompletePractice) {
        this.ratioCompletePractice = ratioCompletePractice;
    }

    public int getNumberOfStep() {
        return numberOfStep;
    }

    public void setNumberOfStep(final int numberOfStep) {
        this.numberOfStep = numberOfStep;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(final int calories) {
        this.calories = calories;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(final Appointment appointment) {
        this.appointment = appointment;
    }

    public Date getCollectedDate() {
        return collectedDate;
    }

    public void setCollectedDate(final Date collectedDate) {
        this.collectedDate = collectedDate;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(final float distance) {
        this.distance = distance;
    }

    public byte getType() {
        return type;
    }

    public void setType(final byte type) {
        this.type = type;
    }
}
