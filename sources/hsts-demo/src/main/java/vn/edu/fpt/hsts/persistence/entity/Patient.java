/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * The Patient entity.
 */
@Entity
public class Patient extends AbstractKeyEntity {

    @OneToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    /**
     *
     */
    private String barcode;

    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecords;

    public Patient() {
    }

    /**
     * The user.
     */
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(final List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(final String barcode) {
        this.barcode = barcode;
    }
}
