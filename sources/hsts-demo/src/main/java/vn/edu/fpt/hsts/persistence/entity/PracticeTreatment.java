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
public class PracticeTreatment extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "practiceId", nullable = false)
    private Practice practice;

    /**
     *
     */
    private int timeDuration;

    /**
     *
     */
    private int numberOfTime;

    /**
     *
     */
    private String advice;

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(final int timeDuration) {
        this.timeDuration = timeDuration;
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
