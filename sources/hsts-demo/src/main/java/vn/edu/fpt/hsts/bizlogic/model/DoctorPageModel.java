/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/28/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.Doctor;

public class DoctorPageModel extends AbstractPageModel<Doctor, DoctorModel>{

    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public DoctorPageModel(Page<Doctor> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<DoctorModel> getModelClass() {
        return DoctorModel.class;
    }
}
