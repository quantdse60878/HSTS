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
public class PhaseMedicine extends AbstractKeyEntity {

    /**
     * The phase.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phaseId", nullable = false)
    private Phase phase;

    /**
     * The medicine.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicineId", nullable = false)
    private Medicine medicine;

    /**
     *
     */
    private int timesPerDay;

    public PhaseMedicine() {
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(final Phase phase) {
        this.phase = phase;
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
}
