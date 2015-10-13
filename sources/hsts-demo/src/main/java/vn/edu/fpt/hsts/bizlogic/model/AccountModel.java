/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;

import java.util.Date;

public class AccountModel extends AbstractKeyModel<Account> {
    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    /**
     * The username.
     */
    private String username;

    /**
     * The password.
     */
    private String password;

    /**
     * The full name.
     */
    private String fullName;

    /**
     * The email.
     */
    private String email;

    /**
     * The birthday.
     */
    private String birthday;


    /**
     * Gender
     */
    private String gender;

    /***
     * Role
     */
    private RoleModel role;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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

    public RoleModel getRole() {
        return role;
    }

    public void setRole(final RoleModel role) {
        this.role = role;
    }

    @Override
    public void fromEntity(Account entity) {
        super.fromEntity(entity);
        setUsername(entity.getUsername());
        setPassword(entity.getPassword());
        setFullName(entity.getFullName());
        setEmail(entity.getEmail());
        String formatDate = DateUtils.formatDate(entity.getDateOfBirth(), DateUtils.DATE_PATTERN_3);
        this.birthday = formatDate;
        if(entity.getGender() == IDbConsts.IAccountGender.MALE) {
            this.gender = "MALE";
        } else {
            this.gender = "FEMALE";
        }
        final RoleModel role = new RoleModel();
        role.fromEntity(entity.getRole());
        setRole(role);
    }
}
