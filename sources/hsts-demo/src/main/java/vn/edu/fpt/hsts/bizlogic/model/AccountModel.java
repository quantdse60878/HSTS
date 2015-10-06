/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

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
    private String fullname;

    /**
     * The email.
     */
    private String email;

    /**
     * The birthday.
     */
    private Date birthday;


    /**
     * Gender
     */
    private byte gender;

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(final byte gender) {
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
        setFullname(entity.getFullname());
        setEmail(entity.getEmail());
        setBirthday(entity.getDateOfBirth());
        setGender(entity.getGender());
        final RoleModel role = new RoleModel();
        role.fromEntity(entity.getRole());
        setRole(role);
    }
}
