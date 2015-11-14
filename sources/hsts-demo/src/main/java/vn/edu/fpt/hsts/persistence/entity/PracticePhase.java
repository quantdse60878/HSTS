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
public class PracticePhase extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "phaseId", nullable = false)
    private Phase phase;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "practiceId", nullable = false)
    private Practice practice;

    private String timeDuration;

    private int numberOfTime;

    private String advice;

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
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

    public PracticePhase() {
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(final Phase phase) {
        this.phase = phase;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(final Practice practice) {
        this.practice = practice;
    }
}
