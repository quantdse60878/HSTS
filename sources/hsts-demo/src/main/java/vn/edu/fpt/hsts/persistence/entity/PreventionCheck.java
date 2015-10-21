/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/21/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PreventionCheck extends AbstractKeyEntity {

    /**
     *
     */
    @OneToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

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


    public PreventionCheck() {
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(final Appointment appointment) {
        this.appointment = appointment;
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

    public float getBmi() {
        return bmi;
    }

    public void setBmi(final float bmi) {
        this.bmi = bmi;
    }

    public float getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(final float muscleMass) {
        this.muscleMass = muscleMass;
    }

    public float getBodyWater() {
        return bodyWater;
    }

    public void setBodyWater(final float bodyWater) {
        this.bodyWater = bodyWater;
    }

    public float getPhaseAngle() {
        return phaseAngle;
    }

    public void setPhaseAngle(final float phaseAngle) {
        this.phaseAngle = phaseAngle;
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
}
