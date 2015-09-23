/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractNamedDescEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Medicine")
public class Medicine extends AbstractNamedDescEntity {

    /**
     * The brand name.
     */
    private String brandName;

    /**
     * The type.
     */
    private byte type;

    public Medicine() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(final String brandName) {
        this.brandName = brandName;
    }

    public byte getType() {
        return type;
    }

    public void setType(final byte type) {
        this.type = type;
    }
}
