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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * The Appointment entity.
 */
@Entity
public class Appointment extends AbstractKeyEntity {

    /**
     * The medical record.
     */
    @ManyToOne
    @JoinColumn(name ="medicalRecordId")
    private MedicalRecord medicalRecord;

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

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(final MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
