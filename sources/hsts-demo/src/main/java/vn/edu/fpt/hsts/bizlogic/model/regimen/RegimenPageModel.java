/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/13/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model.regimen;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.bizlogic.model.AbstractPageModel;
import vn.edu.fpt.hsts.persistence.entity.Regimen;

public class RegimenPageModel extends AbstractPageModel<Regimen, RegimenModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public RegimenPageModel(final Page<Regimen> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<RegimenModel> getModelClass() {
        return RegimenModel.class;
    }
}
