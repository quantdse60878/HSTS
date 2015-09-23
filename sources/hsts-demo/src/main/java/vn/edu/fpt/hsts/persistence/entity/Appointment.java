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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * The Appointment entity.
 */
@Entity
@Table(name = "Appointment")
public class Appointment extends AbstractKeyEntity {

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
     * The meeting date.
     */
    private Date meetingDate;

    /**
     * The appointment message.
     */
    @Column(name = "appointmentMessage")
    private String messsage;

    /**
     * The status.
     */
    private byte status;

    public Appointment() {
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

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(final Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(final String messsage) {
        this.messsage = messsage;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(final byte status) {
        this.status = status;
    }
}
