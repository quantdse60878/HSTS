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
import java.sql.Time;

@Entity
public class PracticeTime extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;

    /**
     *
     */
    private Time timeStart;

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(final Treatment treatment) {
        this.treatment = treatment;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(final Time timeStart) {
        this.timeStart = timeStart;
    }
}
