package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
public class PatientModel implements Serializable {

    private int accountId;
    private int patientId;
    private String fullname;
    private String email;

    public PatientModel() {
    }

    public PatientModel(int accountId, int patientId, String fullname, String email) {
        this.accountId = accountId;
        this.patientId = patientId;
        this.fullname = fullname;
        this.email = email;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
