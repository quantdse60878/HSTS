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
    private int fromDate;

    /**
     *
     */
    private int toDate;

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

    public int getFromDate() {
        return fromDate;
    }

    public void setFromDate(final int fromDate) {
        this.fromDate = fromDate;
    }

    public int getToDate() {
        return toDate;
    }

    public void setToDate(final int toDate) {
        this.toDate = toDate;
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
        // TODO
        fromDate = 1;
        toDate = 100;
        if (null != entity.getUpdateTime()) {
            updateTime = DateUtils.formatDate(entity.getUpdateTime(), DateUtils.DATE_PATTERN_3);
        }
    }
}
