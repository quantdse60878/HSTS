/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/15/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;

public class MedicinePhaseModel extends AbstractKeyModel<MedicinePhase> {
    @Override
    protected Class<MedicinePhase> getEntityClass() {
        return MedicinePhase.class;
    }

    private MedicineModel medicine;

    //TODO
    private int numberOfTime = 4;

    //TODO
    private int quantityPerTime = 3;

    public MedicineModel getMedicine() {
        return medicine;
    }

    public void setMedicine(final MedicineModel medicine) {
        this.medicine = medicine;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(final int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }

    public int getQuantityPerTime() {
        return quantityPerTime;
    }

    public void setQuantityPerTime(final int quantityPerTime) {
        this.quantityPerTime = quantityPerTime;
    }

    @Override
    public void fromEntity(MedicinePhase entity) {
        super.fromEntity(entity);
        medicine = new MedicineModel();
        medicine.fromEntity(entity.getMedicine());
    }
}
