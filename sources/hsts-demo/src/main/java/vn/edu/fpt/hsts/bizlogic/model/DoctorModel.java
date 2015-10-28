/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/8/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;


import vn.edu.fpt.hsts.persistence.entity.Doctor;

public class DoctorModel extends AbstractKeyModel<Doctor>{
    @Override
    protected Class<Doctor> getEntityClass() {
        return Doctor.class;
    }

    /**
     *
     */
    private AccountModel account;

    /**
     *
     */
    private String department;

    /**
     *
     */
    private String major;

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(final AccountModel account) {
        this.account = account;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(final String major) {
        this.major = major;
    }

    @Override
    public void fromEntity(Doctor entity) {
        super.fromEntity(entity);
        account = new AccountModel();
        setShortModel(entity.getAccount(), account);
        department = entity.getDepartment();
        major = entity.getMajor();
    }
}
