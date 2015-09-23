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
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class MedicalRecord extends AbstractKeyEntity {

    /**
     * The doctor.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    /**
     * The patient.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    /**
     * The regimen.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regimenId", referencedColumnName = "id")
    private StandardRegimen standardRegimen;

    /**
     * The start time.
     */
    private Date startTime;

    /**
     * The update time.
     */
    private Date updateTime;

    /**
     * The symptoms.
     */
    private String symptoms;

    /**
     * The status.
     */
    private byte status;

    public MedicalRecord() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(final Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    public StandardRegimen getStandardRegimen() {
        return standardRegimen;
    }

    public void setStandardRegimen(final StandardRegimen standardRegimen) {
        this.standardRegimen = standardRegimen;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(final String symptoms) {
        this.symptoms = symptoms;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(final byte status) {
        this.status = status;
    }
}
