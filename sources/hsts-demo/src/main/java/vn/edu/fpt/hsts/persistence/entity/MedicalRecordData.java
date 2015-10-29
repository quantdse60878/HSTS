/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class MedicalRecordData extends AbstractKeyEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

    @JoinColumn(name = "dateCollectData")
    private Date collectedDate;

    private byte type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "medicalRecordData")
    private List<PropertyRecord> listRecord;

    private int ratioCompletePractice;

    public MedicalRecordData() {
    }

    public int getRatioCompletePractice() {
        return ratioCompletePractice;
    }

    public void setRatioCompletePractice(int ratioCompletePractice) {
        this.ratioCompletePractice = ratioCompletePractice;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public List<PropertyRecord> getListRecord() {
        return listRecord;
    }

    public void setListRecord(List<PropertyRecord> listRecord) {
        this.listRecord = listRecord;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Date getCollectedDate() {
        return collectedDate;
    }

    public void setCollectedDate(Date collectedDate) {
        this.collectedDate = collectedDate;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
