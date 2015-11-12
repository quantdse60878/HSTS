/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/12/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.Medicine;

public class MedicinePageModel extends AbstractPageModel<Medicine, MedicineModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public MedicinePageModel(Page<Medicine> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<MedicineModel> getModelClass() {
        return MedicineModel.class;
    }
}
