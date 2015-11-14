/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/15/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.bizlogic.model.regimen.PhaseModel;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;
import vn.edu.fpt.hsts.persistence.entity.Phase;

public class MedicinePhaseModel extends AbstractKeyModel<MedicinePhase> {
    @Override
    protected Class<MedicinePhase> getEntityClass() {
        return MedicinePhase.class;
    }

    /**
     *
     */
    private PhaseModel phase;

    /**
     *
     */
    private MedicineModel medicine;

    /**
     *
     */
    private int quantitative;

    /**
     *
     */
    private int numberOfTime;

    /**
     *
     */
    private String advice;


    public MedicineModel getMedicine() {
        return medicine;
    }

    public void setMedicine(final MedicineModel medicine) {
        this.medicine = medicine;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    @Override
    public void fromEntity(MedicinePhase entity) {
        super.fromEntity(entity);
        medicine = new MedicineModel();
        setShortModel(entity.getMedicine(), medicine);
        medicine.setUnit(entity.getMedicine().getUnit());
        phase = new PhaseModel();
        setShortModel(entity.getPhase(), phase);
        numberOfTime = entity.getNumberOfTime();
        quantitative = entity.getQuantitative();
        advice = entity.getAdvice();
    }

    @Override
    public MedicinePhase toEntity() throws InstantiationException, IllegalAccessException {
        MedicinePhase entity  = super.toEntity();
        Phase phase = this.phase.toEntity();
        entity.setPhase(phase);
        Medicine medicine = this.medicine.toEntity();
        entity.setMedicine(medicine);
        entity.setNumberOfTime(numberOfTime);
        entity.setQuantitative(quantitative);
        entity.setAdvice(advice);
        return entity;
    }

    public PhaseModel getPhase() {
        return phase;
    }

    public void setPhase(PhaseModel phase) {
        this.phase = phase;
    }

    public int getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(int quantitative) {
        this.quantitative = quantitative;
    }

    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
