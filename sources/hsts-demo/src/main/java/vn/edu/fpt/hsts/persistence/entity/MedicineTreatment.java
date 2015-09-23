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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicineId", nullable = false)
    private Medicine medicine;

    /**
     *
     */
    private int timesPerDay;

    /**
     *
     */
    private int quantityPerTime;

    public MedicineTreatment() {
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(final Treatment treatment) {
        this.treatment = treatment;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(final Medicine medicine) {
        this.medicine = medicine;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(final int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }

    public int getQuantityPerTime() {
        return quantityPerTime;
    }

    public void setQuantityPerTime(final int quantityPerTime) {
        this.quantityPerTime = quantityPerTime;
    }
}
