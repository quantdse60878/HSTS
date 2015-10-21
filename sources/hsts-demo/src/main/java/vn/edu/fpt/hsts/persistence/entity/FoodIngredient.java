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
public class FoodIngredient extends AbstractKeyEntity {

    /**
     *
     */
    @OneToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

    /**
     *
     */
    private float breakfast;


    /**
     *
     */
    private float breakTimeMorning;

    /**
     *
     */
    private float lunch;

    /**
     *
     */
    private float breakTimeAfternoon;

    /**
     *
     */
    private float dinner;

    /**
     *
     */
    private float eatLateAtNight;

    /**
     *
     */
    private float starch;

    /**
     *
     */
    private float protein;

    /**
     *
     */
    private float fat;

    /**
     *
     */
    private float calcium;

    /**
     *
     */
    private float sodium;

    /**
     *
     */
    private float iron;

    /**
     *
     */
    private float zinc;

    /**
     *
     */
    private float vitaminB;

    /**
     *
     */
    private float vitaminC;

    /**
     *
     */
    private float fiber;

    public FoodIngredient() {
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(final Appointment appointment) {
        this.appointment = appointment;
    }

    public float getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(final float breakfast) {
        this.breakfast = breakfast;
    }

    public float getBreakTimeMorning() {
        return breakTimeMorning;
    }

    public void setBreakTimeMorning(final float breakTimeMorning) {
        this.breakTimeMorning = breakTimeMorning;
    }

    public float getLunch() {
        return lunch;
    }

    public void setLunch(final float lunch) {
        this.lunch = lunch;
    }

    public float getBreakTimeAfternoon() {
        return breakTimeAfternoon;
    }

    public void setBreakTimeAfternoon(final float breakTimeAfternoon) {
        this.breakTimeAfternoon = breakTimeAfternoon;
    }

    public float getDinner() {
        return dinner;
    }

    public void setDinner(final float dinner) {
        this.dinner = dinner;
    }

    public float getEatLateAtNight() {
        return eatLateAtNight;
    }

    public void setEatLateAtNight(final float eatLateAtNight) {
        this.eatLateAtNight = eatLateAtNight;
    }

    public float getStarch() {
        return starch;
    }

    public void setStarch(final float starch) {
        this.starch = starch;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(final float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(final float fat) {
        this.fat = fat;
    }

    public float getCalcium() {
        return calcium;
    }

    public void setCalcium(final float calcium) {
        this.calcium = calcium;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(final float sodium) {
        this.sodium = sodium;
    }

    public float getIron() {
        return iron;
    }

    public void setIron(final float iron) {
        this.iron = iron;
    }

    public float getZinc() {
        return zinc;
    }

    public void setZinc(final float zinc) {
        this.zinc = zinc;
    }

    public float getVitaminB() {
        return vitaminB;
    }

    public void setVitaminB(final float vitaminB) {
        this.vitaminB = vitaminB;
    }

    public float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(final float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public float getFiber() {
        return fiber;
    }

    public void setFiber(final float fiber) {
        this.fiber = fiber;
    }
}
