/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class MedicalRecord extends AbstractKeyEntity {

    /**
     * The doctor.
     */
    @OneToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    /**
     * The patient.
     */
    @OneToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    /**
     * The illness.
     */
    @ManyToOne
    @JoinColumn(name = "illnessId", referencedColumnName = "id")
    private Illness illness;

    /**
     * The start time.
     */
    private Date startTime;

    /**
     * The update time.
     */
    private Date endTime;

    /**
     * The symptoms.
     */
    @Column(name = "clinicalSymptoms")
    private String symptoms;

    /**
     *
     */
    private String medicalHistory;

    /**
     * The status.
     */
    private byte status;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medicalRecord")
    private List<Appointment> appointmentList;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
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

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(final Illness illness) {
        this.illness = illness;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(final String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }


    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(final List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
