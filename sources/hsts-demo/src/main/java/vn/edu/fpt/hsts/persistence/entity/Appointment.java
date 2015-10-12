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

/**
 * The Appointment entity.
 */
@Entity
public class Appointment extends AbstractKeyEntity {

    /**
     * The medical record.
     */
    @ManyToOne
    @JoinColumn(name ="medicalRecordId", nullable = false)
    private MedicalRecord medicalRecord;

    /**
     * The meeting date.
     */
    @Column(name = "appointmentDateTime")
    private Date meetingDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
    private List<Treatment> treatmentList;

    /**
     * The patient height.
     */
    private double height;

    /**
     * The patient weight.
     */
    private double weight;
    /**
     * The status.
     */
    private byte status;

    /**
     *
     */
    @OneToOne
    @JoinColumn(name = "nextAppointment")
    private Appointment nextAppointment;

    public Appointment() {
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weight) {
        this.weight = weight;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(final Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(final byte status) {
        this.status = status;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(final MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Appointment getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(final Appointment nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(final List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }
}
