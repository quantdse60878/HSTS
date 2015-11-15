/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;

public class FoodPhasePageModel extends AbstractPageModel<FoodPhase, FoodPhaseModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public FoodPhasePageModel(Page<FoodPhase> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<FoodPhaseModel> getModelClass() {
        return FoodPhaseModel.class;
    }
}
