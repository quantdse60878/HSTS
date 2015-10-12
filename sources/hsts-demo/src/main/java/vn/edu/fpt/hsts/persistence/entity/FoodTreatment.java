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
public class FoodTreatment extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodId", nullable =  false)
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;

    /**
     *
     */
    private String quantitative;

    /**
     *
     */
    private int numberOfTime;

    private String advice;

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(final Food food) {
        this.food = food;
    }

    public String getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(final String quantitative) {
        this.quantitative = quantitative;
    }

    public FoodTreatment() {
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(final Treatment treatment) {
        this.treatment = treatment;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(final int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }
}
