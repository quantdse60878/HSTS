/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/13/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model.regimen;

import vn.edu.fpt.hsts.bizlogic.model.AbstractKeyModel;
import vn.edu.fpt.hsts.bizlogic.model.IllnessModel;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.entity.Regimen;

import java.util.Date;

public class RegimenModel extends AbstractKeyModel<Regimen> {
    @Override
    protected Class<Regimen> getEntityClass() {
        return Regimen.class;
    }

    /**
     *
     */
    private IllnessModel illness;

    /**
     *
     */
    private String updateTime;

    /**
     *
     */
    private int numberOfPhase;

    public IllnessModel getIllness() {
        return illness;
    }

    public void setIllness(final IllnessModel illness) {
        this.illness = illness;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public void fromEntity(Regimen entity) {
        super.fromEntity(entity);
        illness = new IllnessModel();
        setShortModel(entity.getIllness(), illness);
        final Date updateTime = entity.getUpdateTime();
        if (null != updateTime) {
            final String date = DateUtils.formatDate(updateTime, DateUtils.DATE_PATTERN_3);
            this.updateTime = date;
        }
        numberOfPhase = entity.getPhaseList().size();
    }

    public int getNumberOfPhase() {
        return numberOfPhase;
    }

    public void setNumberOfPhase(final int numberOfPhase) {
        this.numberOfPhase = numberOfPhase;
    }
}
