/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/21/2015.
 */
package vn.edu.fpt.hsts.criteria;

public class RegistrationCriteria extends SearchCriteria {

    /**
     *
     */
    private int doctorId;

    /**
     *
     */
    private String medicalHistory;

    /**
     *
     */
    private String symptom;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(final int doctorId) {
        this.doctorId = doctorId;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(final String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(final String symptom) {
        this.symptom = symptom;
    }
}
