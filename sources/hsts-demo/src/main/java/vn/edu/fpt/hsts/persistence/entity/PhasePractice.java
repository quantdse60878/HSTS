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
import javax.persistence.Table;

@Entity
@Table(name = "PhasePractice")
public class PhasePractice extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phaseId", nullable = false)
    private Phase phase;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practiceId", nullable = false)
    private Practice practice;

    public PhasePractice() {
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
