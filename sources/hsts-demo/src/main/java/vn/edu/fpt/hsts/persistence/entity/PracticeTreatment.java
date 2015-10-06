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
    @JoinColumn(name = "practiceTimeId", nullable = false)
    private PracticeTime practiceTime;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practiceId", nullable = false)
    private Practice practice;

    /**
     *
     */
    private int timeDuration;
}
