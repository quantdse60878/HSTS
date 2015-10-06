/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MedicineTreatment extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicineTimeId", nullable = false)
    private MedicineTime medicineTime;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicineId", nullable = false)
    private Medicine medicine;

    /**
     *
     */
    private float numberOfMedicine;

    public MedicineTime getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(final MedicineTime medicineTime) {
        this.medicineTime = medicineTime;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(final Medicine medicine) {
        this.medicine = medicine;
    }

    public float getNumberOfMedicine() {
        return numberOfMedicine;
    }

    public void setNumberOfMedicine(final float numberOfMedicine) {
        this.numberOfMedicine = numberOfMedicine;
    }
}
