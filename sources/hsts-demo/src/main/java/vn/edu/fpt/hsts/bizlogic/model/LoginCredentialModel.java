/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/19/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

public class LoginCredentialModel implements Serializable {

    /**
     *
     */
    private int accountId;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String oldPassword;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String status;

    public interface ILoginSatus {
        /**
         *
         */
        String OK = "ok";

        /**
         *
         */
        String FAIL = "fail";

        /**
         *
         */
        String INACTIVE = "inactive";
    }

    @Override
    public String toString() {
        return "LoginCredentialModel{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginCredentialModel(String status) {
        this.status = status;
    }

    public LoginCredentialModel() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
