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
import java.util.Date;

@Entity
@Table(name = "Treatment")
public class Treatment extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalRecordId", nullable = false)
    private MedicalRecord medicalRecord;

    /**
     *
     */
    private String suggestion;

    /**
     *
     */
    private int minCalories;

    /**
     *
     */
    private int maxCalories;

    /**
     *
     */
    private Date fromDate;

    /**
     *
     */
    private Date toDate;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private byte status;


    public Treatment() {
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(final MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(final String suggestion) {
        this.suggestion = suggestion;
    }

    public int getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(final int minCalories) {
        this.minCalories = minCalories;
    }

    public int getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(final int maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(final Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(final Date toDate) {
        this.toDate = toDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(final byte status) {
        this.status = status;
    }
}
