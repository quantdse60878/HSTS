/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;
import vn.edu.fpt.hsts.common.jpa.AbstractNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Practice extends AbstractKeyEntity {


    @Column(name = "practiceName")
    private String name;

    /**
     * The intensity.
     */
    private int intensity;

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(final int intensity) {
        this.intensity = intensity;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Practice() {
    }
}
