/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/10/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model.regimen;

import vn.edu.fpt.hsts.bizlogic.model.AbstractKeyModel;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.entity.Phase;

public class PhaseModel extends AbstractKeyModel<Phase> {
    @Override
    protected Class<Phase> getEntityClass() {
        return Phase.class;
    }

    /**
     *
     */
    private RegimenModel regimen;

    /**
     *
     */
    private int numberOfDay;

    /**
     *
     */
    private String updateTime;

    public RegimenModel getRegimen() {
        return regimen;
    }

    public void setRegimen(final RegimenModel regimen) {
        this.regimen = regimen;
    }

    public int getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(final int numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public void fromEntity(Phase entity) {
        super.fromEntity(entity);
        regimen = new RegimenModel();
        setShortModel(entity.getRegimen(), regimen);
        numberOfDay = entity.getNumberOfDay();
        if (null != entity.getUpdateTime()) {
            updateTime = DateUtils.formatDate(entity.getUpdateTime(), DateUtils.DATE_PATTERN_3);
        }
    }


}
