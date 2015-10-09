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

@Entity
public class MedicineTreatment extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "medicineId", nullable = false)
    private Medicine medicine;

    /**
     *
     */
    private String advice;

    /**
     *
     */
    private String quantitative;

    /**
     *
     */
    private int numberOfTime;

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(final Medicine medicine) {
        this.medicine = medicine;
    }


    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(final Treatment treatment) {
        this.treatment = treatment;
    }

    public String getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(final String quantitative) {
        this.quantitative = quantitative;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(final int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }
}
