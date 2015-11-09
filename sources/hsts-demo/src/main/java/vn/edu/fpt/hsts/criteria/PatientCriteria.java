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
    private String email;

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
        sb.append(", email=").append(email);
        return sb.toString();
    }
}
