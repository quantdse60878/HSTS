/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.fpt.hsts.persistence.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    /**
     * Find by username and password, use hql
     * @param roleName
     * @return
     */
    @Query(value = "select r from Role r where roleName = :roleName")
    public Role findByRoleName(@Param(value = "roleName") final String roleName);

}
