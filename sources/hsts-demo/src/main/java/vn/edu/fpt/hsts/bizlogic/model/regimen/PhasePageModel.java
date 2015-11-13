/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/10/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model.regimen;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.bizlogic.model.AbstractPageModel;
import vn.edu.fpt.hsts.persistence.entity.Phase;

public class PhasePageModel extends AbstractPageModel<Phase, PhaseModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public PhasePageModel(Page<Phase> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<PhaseModel> getModelClass() {
        return PhaseModel.class;
    }


}
