/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 12/17/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;

public class MedicalRecordPageModel extends AbstractPageModel<MedicalRecord, MedicalRecordModel>{
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public MedicalRecordPageModel(Page<MedicalRecord> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<MedicalRecordModel> getModelClass() {
        return MedicalRecordModel.class;
    }
}
