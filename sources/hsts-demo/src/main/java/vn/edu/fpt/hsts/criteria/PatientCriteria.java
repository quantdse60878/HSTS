/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/8/2015.
 */
package vn.edu.fpt.hsts.criteria;

import java.util.Date;

public class PatientCriteria extends SearchCriteria {

    /**
     *
     */
    private String patientName;

    /**
     *
     */
    private String birthday;

    /**
     *
     */
    private byte gender;

    /**
     *
     */
    private byte type;

    /**
     *
     */
    private int weight;

    /**
     *
     */
    private int height;

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

    /**
     *
     */
    private String email;

    public interface IRegisterPatientType {
        /**
         *
         */
        byte FIRST_REGISTER = 1;

        /**
         *
         */
        byte RE_EXAM = 2;
    }

    public PatientCriteria() {
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(final String patientName) {
        this.patientName = patientName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(final byte gender) {
        this.gender = gender;
    }

    public byte getType() {
        return type;
    }

    public void setType(final byte type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(final int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(super.toString());
        sb.append(", patientName= ").append(patientName);
        sb.append(", birthday= ").append(birthday);
        sb.append(", gender= ").append(gender);
        sb.append(", weight= ").append(weight);
        sb.append(", height= ").append(height);
        sb.append(", medicalHistory= ").append(medicalHistory);
        sb.append(", symptom= ").append(symptom);
        sb.append(", email=").append(email);
        return sb.toString();
    }
}
