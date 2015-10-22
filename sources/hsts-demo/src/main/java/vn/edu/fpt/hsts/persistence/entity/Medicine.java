/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;
import vn.edu.fpt.hsts.common.jpa.AbstractNamedDescEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Medicine extends AbstractKeyEntity {

    /**
     * The name.
     */
    @Column(name = "medicineName")
    private String name;

    private String unit;

    public Medicine() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
