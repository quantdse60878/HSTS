/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/12/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.Patient;

public class PatientExtendedPageModel extends AbstractPageModel<Patient, PatientExtendedModel>{
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public PatientExtendedPageModel(final Page<Patient> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<PatientExtendedModel> getModelClass() {
        return PatientExtendedModel.class;
    }
}
