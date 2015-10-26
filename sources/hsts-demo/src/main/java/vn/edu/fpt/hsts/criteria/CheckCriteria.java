/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/21/2015.
 */
package vn.edu.fpt.hsts.criteria;

public class CheckCriteria extends SearchCriteria {

    /**
     *
     */
    private int height;

    /**
     *
     */
    private int weight;

    /**
     *
     */
    private int heartBeat;

    /**
     *
     */
    private int bloodPressure;

    /**
     *
     */
    private float bmi;

    /**
     *
     */
    private int waists;

    /**
     *
     */
    private float bodyFat;

    /**
     *
     */
    private byte visceralFat;

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

    public CheckCriteria() {
    }

    public float getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(final float bodyFat) {
        this.bodyFat = bodyFat;
    }

    public byte getVisceralFat() {
        return visceralFat;
    }

    public void setVisceralFat(final byte visceralFat) {
        this.visceralFat = visceralFat;
    }

    public float getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(final float muscleMass) {
        this.muscleMass = muscleMass;
    }

    public float getPhaseAngle() {
        return phaseAngle;
    }

    public void setPhaseAngle(final float phaseAngle) {
        this.phaseAngle = phaseAngle;
    }

    public float getBodyWater() {
        return bodyWater;
    }

    public void setBodyWater(final float bodyWater) {
        this.bodyWater = bodyWater;
    }

    public int getImpedance() {
        return impedance;
    }

    public void setImpedance(final int impedance) {
        this.impedance = impedance;
    }

    public int getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate(final int basalMetabolicRate) {
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(int hearthBeat) {
        this.heartBeat = hearthBeat;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public int getWaists() {
        return waists;
    }

    public void setWaists(int waists) {
        this.waists = waists;
    }
}
