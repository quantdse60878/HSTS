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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalRecordId", nullable = false)
    private MedicalRecord medicalRecord;

    /**
     * Collected time.
     */
    private Date collectedTime;

    /**
     * The patient height.
     */
    private double height;

    /**
     * The patient weight.
     */
    private double weight;

    /**
     * Number of steps.
     */
    private int numberOfStep;

    /**
     * Calories burned.
     */
    private int caloriesBurned;

    public MedicalRecordData() {
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(final MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Date getCollectedTime() {
        return collectedTime;
    }

    public void setCollectedTime(final Date collectedTime) {
        this.collectedTime = collectedTime;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weight) {
        this.weight = weight;
    }

    public int getNumberOfStep() {
        return numberOfStep;
    }

    public void setNumberOfStep(final int numberOfStep) {
        this.numberOfStep = numberOfStep;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(final int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
