/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/9/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Patient;

public class PatientRegistrationModel extends AbstractKeyModel<Patient>{
    @Override
    protected Class<Patient> getEntityClass() {
        return Patient.class;
    }

    /**
     *
     */
    private boolean result;

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
    private String email;

    /**
     *
     */
    private String gender;

    /**
     *
     */
    private String barcode;

    /**
     *
     */
    private String date;

    /**
     *
     */
    private int orderNumber;

    /**
     *
     */
    private String doctor;

    public PatientRegistrationModel() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(final String barcode) {
        this.barcode = barcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(final String doctor) {
        this.doctor = doctor;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(final int orderNumber) {
        this.orderNumber = orderNumber;
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(final boolean result) {
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public void fromEntity(Patient entity) {
        super.fromEntity(entity);
        patientName = entity.getAccount().getFullName();
        birthday = DateUtils.formatDate(entity.getAccount().getDateOfBirth(), DateUtils.DATE_PATTERN_3);
        barcode = entity.getBarcode();
        email = entity.getAccount().getEmail();
        if (entity.getAccount().getGender() == IDbConsts.IAccountGender.MALE) {
            gender = "MALE";
        } else {
            gender = "FEMALE";
        }
        result = true;
    }

    public PatientRegistrationModel(final boolean result) {
        this.result = result;
    }
}
