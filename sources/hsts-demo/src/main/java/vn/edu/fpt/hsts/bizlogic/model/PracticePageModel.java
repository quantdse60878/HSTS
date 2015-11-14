/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.Practice;

public class PracticePageModel extends AbstractPageModel<Practice, PracticeModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public PracticePageModel(Page<Practice> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<PracticeModel> getModelClass() {
        return PracticeModel.class;
    }
}
