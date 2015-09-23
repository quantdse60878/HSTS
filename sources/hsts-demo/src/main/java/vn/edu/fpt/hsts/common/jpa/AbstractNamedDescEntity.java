/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.common.jpa;

import javax.persistence.MappedSuperclass;

/**
 * The entity has one primary key and column name, description.
 */
@MappedSuperclass
public abstract class AbstractNamedDescEntity {

    /**
     * The description.
     */
    private String description;
}
