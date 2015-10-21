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
    private float animalProtein;

    /**
     *
     */
    private float animalFat;

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
    private float vitaminB1;

    /**
     *
     */
    private float vitaminC;

    /**
     *
     */
    private float vitaminB2;

    /**
     *
     */
    private float vitaminPP;

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

    public float getAnimalProtein() {
        return animalProtein;
    }

    public void setAnimalProtein(float animalProtein) {
        this.animalProtein = animalProtein;
    }

    public float getAnimalFat() {
        return animalFat;
    }

    public void setAnimalFat(float animalFat) {
        this.animalFat = animalFat;
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

    public float getVitaminB1() {
        return vitaminB1;
    }

    public void setVitaminB1(float vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }

    public float getVitaminB2() {
        return vitaminB2;
    }

    public void setVitaminB2(float vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }

    public float getVitaminPP() {
        return vitaminPP;
    }

    public void setVitaminPP(float vitaminPP) {
        this.vitaminPP = vitaminPP;
    }

    @Override
    public String toString() {
        return "FoodIngredient{" +
                "appointment=" + appointment +
                ", breakfast=" + breakfast +
                ", breakTimeMorning=" + breakTimeMorning +
                ", lunch=" + lunch +
                ", breakTimeAfternoon=" + breakTimeAfternoon +
                ", dinner=" + dinner +
                ", eatLateAtNight=" + eatLateAtNight +
                ", starch=" + starch +
                ", protein=" + protein +
                ", fat=" + fat +
                ", animalProtein=" + animalProtein +
                ", animalFat=" + animalFat +
                ", calcium=" + calcium +
                ", sodium=" + sodium +
                ", iron=" + iron +
                ", zinc=" + zinc +
                ", vitaminB1=" + vitaminB1 +
                ", vitaminC=" + vitaminC +
                ", vitaminB2=" + vitaminB2 +
                ", vitaminPP=" + vitaminPP +
                ", fiber=" + fiber +
                '}';
    }
}
