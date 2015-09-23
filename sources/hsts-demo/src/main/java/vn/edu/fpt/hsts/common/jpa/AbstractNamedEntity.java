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
 * The abstract entity has one primary key and name column.
 */
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractKeyEntity {

    /**
     * The name.
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public AbstractNamedEntity() {
    }
}
