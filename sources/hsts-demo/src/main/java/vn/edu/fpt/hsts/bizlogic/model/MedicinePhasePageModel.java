/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;

public class MedicinePhasePageModel extends AbstractPageModel<MedicinePhase, MedicinePhaseModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public MedicinePhasePageModel(Page<MedicinePhase> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<MedicinePhaseModel> getModelClass() {
        return MedicinePhaseModel.class;
    }
}
