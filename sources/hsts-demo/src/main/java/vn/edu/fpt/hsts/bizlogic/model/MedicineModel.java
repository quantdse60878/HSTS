/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/15/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.Medicine;

public class MedicineModel extends AbstractKeyModel<Medicine> {
    @Override
    protected Class<Medicine> getEntityClass() {
        return Medicine.class;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void fromEntity(Medicine entity) {
        super.fromEntity(entity);
        this.name = entity.getName();
    }
}
