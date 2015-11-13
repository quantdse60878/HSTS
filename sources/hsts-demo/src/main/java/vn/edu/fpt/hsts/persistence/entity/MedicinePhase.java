/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/6/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MedicinePhase extends AbstractKeyEntity {

    @ManyToOne
    @JoinColumn(name = "phaseId", nullable = false)
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "medicineId", nullable = false)
    private Medicine medicine;

    private int quantitative;

    private int numberOfTime;

    private String advice;

    public MedicinePhase() {
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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

    public int getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(final int quantitative) {
        this.quantitative = quantitative;
    }
}
