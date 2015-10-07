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
import javax.persistence.OneToOne;

/**
 * The Patient entity.
 */
@Entity
public class Patient extends AbstractKeyEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

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
}
