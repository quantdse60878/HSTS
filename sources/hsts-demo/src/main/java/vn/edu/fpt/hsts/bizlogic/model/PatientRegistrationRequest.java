/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/8/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

public class PatientRegistrationRequest implements Serializable {

    /**
     *
     */
    private int patientId;

    /**
     *
     */
    private boolean isNewMedicalRecord;

    /**
     *
     */
    private PatientCriteria patient;

    /**
     *
     */
    private CheckCriteria check;

    /**
     *
     */
    private RegistrationCriteria registration;

    public PatientCriteria getPatient() {
        return patient;
    }

    public PatientRegistrationRequest() {
    }

    public void setPatient(PatientCriteria patient) {
        this.patient = patient;
    }

    public CheckCriteria getCheck() {
        return check;
    }

    public void setCheck(CheckCriteria check) {
        this.check = check;
    }

    public RegistrationCriteria getRegistration() {
        return registration;
    }

    public void setRegistration(RegistrationCriteria registration) {
        this.registration = registration;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public boolean isNewMedicalRecord() {
        return isNewMedicalRecord;
    }

    public void setIsNewMedicalRecord(boolean isNewMedicalRecord) {
        this.isNewMedicalRecord = isNewMedicalRecord;
    }
}
