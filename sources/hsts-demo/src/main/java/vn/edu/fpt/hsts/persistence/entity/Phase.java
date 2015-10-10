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
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Phase extends AbstractKeyEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regimenId", referencedColumnName = "id", nullable = false)
    private Regimen regimen;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phase")
    private List<MedicinePhase> medicinePhaseList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phase")
    private List<PracticePhase> practicePhaseList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phase")
    private List<FoodPhase> foodPhaseList;

    /**
     *
     */
    private int numberOfDate;

    /**
     *
     */
    private Date updateTime;

    public List<MedicinePhase> getMedicinePhaseList() {
        return medicinePhaseList;
    }

    public void setMedicinePhaseList(List<MedicinePhase> medicinePhaseList) {
        this.medicinePhaseList = medicinePhaseList;
    }

    public List<PracticePhase> getPracticePhaseList() {
        return practicePhaseList;
    }

    public void setPracticePhaseList(List<PracticePhase> practicePhaseList) {
        this.practicePhaseList = practicePhaseList;
    }

    public List<FoodPhase> getFoodPhaseList() {
        return foodPhaseList;
    }

    public void setFoodPhaseList(List<FoodPhase> foodPhaseList) {
        this.foodPhaseList = foodPhaseList;
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public void setRegimen(final Regimen regimen) {
        this.regimen = regimen;
    }

    public int getNumberOfDate() {
        return numberOfDate;
    }

    public void setNumberOfDate(final int numberOfDate) {
        this.numberOfDate = numberOfDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
}
