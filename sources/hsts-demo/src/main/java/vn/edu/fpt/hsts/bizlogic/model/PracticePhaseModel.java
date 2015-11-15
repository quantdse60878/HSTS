/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.bizlogic.model.regimen.PhaseModel;
import vn.edu.fpt.hsts.persistence.entity.PracticePhase;

public class PracticePhaseModel extends AbstractKeyModel<PracticePhase> {
    @Override
    protected Class<PracticePhase> getEntityClass() {
        return PracticePhase.class;
    }

    /**
     *
     */
    private PhaseModel phase;

    /**
     *
     */
    private PracticeModel practice;


    /**
     *
     */
    private String timeDuration;

    /**
     *
     */
    private int numberOfTime;

    /**
     *
     */
    private String advice;

    public PhaseModel getPhase() {
        return phase;
    }

    public void setPhase(PhaseModel phase) {
        this.phase = phase;
    }

    public PracticeModel getPractice() {
        return practice;
    }

    public void setPractice(PracticeModel practice) {
        this.practice = practice;
    }

    public int getNumberOfTime() {
        return numberOfTime;
    }

    public void setNumberOfTime(int numberOfTime) {
        this.numberOfTime = numberOfTime;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public void fromEntity(PracticePhase entity) {
        super.fromEntity(entity);
        phase = new PhaseModel();
        setShortModel(entity.getPhase(), phase);
        practice = new PracticeModel();
        setShortModel(entity.getPractice(), practice);
        practice.setIntensity(entity.getPractice().getIntensity());
        timeDuration = entity.getTimeDuration();
        numberOfTime = entity.getNumberOfTime();
        advice = entity.getAdvice();
    }
}
