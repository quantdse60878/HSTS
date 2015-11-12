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


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
    private List<FoodIngredient> foodIngredientListList;
    /**
     *
     */
    @OneToOne
    @JoinColumn(name = "nextAppointment")
    private Appointment nextAppointment;

    /**
     * The status.
     */
    private byte status;

    private String appointmentMessage;


    public Appointment() {
    }


    public String getAppointmentMessage() {
        return appointmentMessage;
    }

    public void setAppointmentMessage(String appointmentMessage) {
        this.appointmentMessage = appointmentMessage;
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
