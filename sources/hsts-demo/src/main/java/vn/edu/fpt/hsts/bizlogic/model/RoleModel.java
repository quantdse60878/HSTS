/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.Role;

public class RoleModel extends AbstractKeyModel<Role> {

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

}
