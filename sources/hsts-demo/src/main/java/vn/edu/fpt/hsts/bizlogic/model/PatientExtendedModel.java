/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/12/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Patient;

public class PatientExtendedModel extends AbstractKeyModel<Patient>{
    @Override
    protected Class<Patient> getEntityClass() {
        return Patient.class;
    }

    /**
     *
     */
    private AccountModel account;

    /**
     *
     */
    private String barcode;

    @Override
    public void fromEntity(Patient entity) {
        super.fromEntity(entity);
        account = new AccountModel();
        account.fromEntity(entity.getAccount());
        barcode = entity.getBarcode();
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(final AccountModel account) {
        this.account = account;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
