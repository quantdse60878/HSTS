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

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    @Override
    public void fromEntity(Medicine entity) {
        super.fromEntity(entity);
        this.name = entity.getName();
        this.unit = entity.getUnit();
    }

    @Override
    public Medicine toEntity() throws InstantiationException, IllegalAccessException {
        Medicine medicine = super.toEntity();
        medicine.setName(this.name);
        medicine.setUnit(this.unit);
        return medicine;
    }
}
