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
public class FoodPhase extends AbstractKeyEntity {

    /**
     * The phase.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phaseId", nullable = false)
    private Phase phase;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodId", nullable = false)
    private Food food;

    private int numberOfTime;

    private String advice;

    private String unitName;

    public FoodPhase() {
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(final Phase phase) {
        this.phase = phase;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(final Food food) {
        this.food = food;
    }
}
