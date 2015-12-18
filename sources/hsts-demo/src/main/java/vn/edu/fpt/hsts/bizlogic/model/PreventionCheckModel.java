/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 12/18/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

public class PreventionCheckModel extends AbstractKeyModel<PreventionCheck> {
    @Override
    protected Class<PreventionCheck> getEntityClass() {
        return PreventionCheck.class;
    }

    /**
     *
     */
    private float bodyFat;

    /**
     *
     */
    private byte visceralFat;

    /**
     * The patient height.
     */
    private double height;

    /**
     * The patient weight.
     */
    private double weight;

    /**
     *
     */
    private float bmi;

    /**
     *
     */
    private float muscleMass;

    /**
     *
     */
    private float bodyWater;

    /**
     *
     */
    private float phaseAngle;

    /**
     *
     */
    private int impedance;

    /**
     *
     */
    private int basalMetabolicRate;


    /**
     *
     */
    private int bloodPressure;

    /**
     *
     */
    private int heartBeat;

    /**
     *
     */
    private int waists;

    public float getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(float bodyFat) {
        this.bodyFat = bodyFat;
    }

    public byte getVisceralFat() {
        return visceralFat;
    }

    public void setVisceralFat(byte visceralFat) {
        this.visceralFat = visceralFat;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public float getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(float muscleMass) {
        this.muscleMass = muscleMass;
    }

    public float getBodyWater() {
        return bodyWater;
    }

    public void setBodyWater(float bodyWater) {
        this.bodyWater = bodyWater;
    }

    public float getPhaseAngle() {
        return phaseAngle;
    }

    public void setPhaseAngle(float phaseAngle) {
        this.phaseAngle = phaseAngle;
    }

    public int getImpedance() {
        return impedance;
    }

    public void setImpedance(int impedance) {
        this.impedance = impedance;
    }

    public int getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate(int basalMetabolicRate) {
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(int heartBeat) {
        this.heartBeat = heartBeat;
    }

    public int getWaists() {
        return waists;
    }

    public void setWaists(int waists) {
        this.waists = waists;
    }

    @Override
    public void fromEntity(PreventionCheck entity) {
        super.fromEntity(entity);
        this.basalMetabolicRate = entity.getBasalMetabolicRate();
        this.bloodPressure = entity.getBloodPressure();
        this.bmi = entity.getBmi();
        this.bodyFat = entity.getBodyFat();
        this.bodyWater = entity.getBodyWater();
        this.heartBeat = entity.getHeartBeat();
        this.height = entity.getHeight();
        this.impedance = entity.getImpedance();
        this.muscleMass = entity.getMuscleMass();
        this.visceralFat = entity.getVisceralFat();
        this.waists = entity.getWaists();
        this.weight = entity.getWeight();
    }
}
