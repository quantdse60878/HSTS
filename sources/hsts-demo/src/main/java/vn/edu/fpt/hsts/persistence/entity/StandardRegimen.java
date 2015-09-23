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
import java.util.Date;

@Entity
public class StandardRegimen extends AbstractKeyEntity {

    /**
     * The illness name.
     */
    private String illnessName;

    /**
     * The update time.
     */
    private Date updateTime;

    public StandardRegimen() {
    }

    public String getIllnessName() {
        return illnessName;
    }

    public void setIllnessName(final String illnessName) {
        this.illnessName = illnessName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
}
