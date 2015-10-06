/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/6/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;

@Entity
public class Device extends AbstractKeyEntity {

    /**
     *
     */
    private String brandName;

    /**
     *
     */
    private String brandUuid;

    /**
     *
     */
    private String numberOfStepUuid;

    /**
     *
     */
    private String positionNumberOfStep;

    public Device() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(final String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUuid() {
        return brandUuid;
    }

    public void setBrandUuid(final String brandUuid) {
        this.brandUuid = brandUuid;
    }

    public String getNumberOfStepUuid() {
        return numberOfStepUuid;
    }

    public void setNumberOfStepUuid(final String numberOfStepUuid) {
        this.numberOfStepUuid = numberOfStepUuid;
    }

    public String getPositionNumberOfStep() {
        return positionNumberOfStep;
    }

    public void setPositionNumberOfStep(final String positionNumberOfStep) {
        this.positionNumberOfStep = positionNumberOfStep;
    }
}
