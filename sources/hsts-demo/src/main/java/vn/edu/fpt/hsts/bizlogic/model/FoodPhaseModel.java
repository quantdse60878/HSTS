/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.bizlogic.model.regimen.PhaseModel;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;

public class FoodPhaseModel extends AbstractKeyModel<FoodPhase> {
    @Override
    protected Class<FoodPhase> getEntityClass() {
        return FoodPhase.class;
    }

    /**
     *
     */
    private PhaseModel phase;

    /**
     *
     */
    private FoodModel food;

    /**
     *
     */
    private int numberOfTime;

    /**
     *
     */
    private String advice;

    /**
     *
     */
    private String unitName;

    /**
     *
     */
    private int quantitative;

    public PhaseModel getPhase() {
        return phase;
    }

    public void setPhase(PhaseModel phase) {
        this.phase = phase;
    }

    public FoodModel getFood() {
        return food;
    }

    public void setFood(FoodModel food) {
        this.food = food;
    }

    public int getNumberOfTime() {
        return numberOfTime;
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

    public int getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(int quantitative) {
        this.quantitative = quantitative;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public void fromEntity(FoodPhase entity) {
        super.fromEntity(entity);
        phase = new PhaseModel();
        setShortModel(entity.getPhase(), phase);
        food = new FoodModel();
        setShortModel(entity.getFood(), food);
        this.advice = entity.getAdvice();
        this.unitName = entity.getUnitName();
        this.quantitative = entity.getQuantitative();
        this.numberOfTime = entity.getNumberOfTime();
    }
}
