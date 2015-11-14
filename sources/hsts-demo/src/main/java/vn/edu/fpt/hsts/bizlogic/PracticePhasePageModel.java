/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.bizlogic.model.AbstractPageModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticePhaseModel;
import vn.edu.fpt.hsts.persistence.entity.PracticePhase;

public class PracticePhasePageModel extends AbstractPageModel<PracticePhase, PracticePhaseModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public PracticePhasePageModel(Page<PracticePhase> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<PracticePhaseModel> getModelClass() {
        return PracticePhaseModel.class;
    }


}
