/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.User;

public class UserModel extends AbstractKeyModel<User> {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    /**
     * TODO add fields, override fromEntity() method
     */
}
