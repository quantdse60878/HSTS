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

@Entity
public class PhaseFood extends AbstractKeyEntity {

    /**
     * The phase.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phaseId", nullable = false)
    private Phase phase;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodId", nullable = false)
    private Food food;

    /**
     *
     */
    private byte time;

    public PhaseFood() {
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(final Phase phase) {
        this.phase = phase;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(final Food food) {
        this.food = food;
    }

    public byte getTime() {
        return time;
    }

    public void setTime(final byte time) {
        this.time = time;
    }
}
