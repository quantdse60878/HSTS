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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Regimen extends AbstractKeyEntity {

    /**
     * The illness name.
     */
    @OneToOne
    @JoinColumn(name = "illnessId", nullable = false)
    private Illness illness;

    /**
     * The update time.
     */
    private Date updateTime;

    /**
     *
     */
    @OneToMany(mappedBy = "regimen", fetch = FetchType.LAZY)
    private List<Phase> phaseList;


    public Regimen() {
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(final Illness illness) {
        this.illness = illness;
    }

    public List<Phase> getPhaseList() {
        return phaseList;
    }

    public void setPhaseList(final List<Phase> phaseList) {
        this.phaseList = phaseList;
    }
}
