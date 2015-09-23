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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Phase")
public class Phase extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regimenId", referencedColumnName = "id", nullable = false)
    private StandardRegimen standardRegimen;

    /**
     *
     */
    private int numberOfDate;

    /**
     *
     */
    private Date updateTime;

    public StandardRegimen getStandardRegimen() {
        return standardRegimen;
    }

    public void setStandardRegimen(final StandardRegimen standardRegimen) {
        this.standardRegimen = standardRegimen;
    }

    public int getNumberOfDate() {
        return numberOfDate;
    }

    public void setNumberOfDate(final int numberOfDate) {
        this.numberOfDate = numberOfDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
}
