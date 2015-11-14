/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.Practice;

public class PracticeModel extends AbstractKeyModel<Practice> {
    @Override
    protected Class<Practice> getEntityClass() {
        return Practice.class;
    }

    private String name;

    /**
     * The intensity.
     */
    private int intensity;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(final int intensity) {
        this.intensity = intensity;
    }

    @Override
    public void fromEntity(Practice entity) {
        super.fromEntity(entity);
        this.name = entity.getName();
        this.intensity = entity.getIntensity();
    }
}
