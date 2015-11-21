package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

/**
 * Created by Aking on 11/21/2015.
 */
public class FoodNutriValModel implements Serializable {
    private float animalFat;
    private float animalProtein;
    private float calcium;
    private float fat;
    private float starch;
    private float protein;
    private float fiber;
    private float iron;
    private float sodium;
    private float vitaminB1;
    private float vitaminB2;
    private float vitaminC;
    private float vitaminPP;
    private float zinc;

    public FoodNutriValModel() {
    }

    public float getAnimalFat() {
        return animalFat;
    }

    public void setAnimalFat(float animalFat) {
        this.animalFat = animalFat;
    }

    public float getAnimalProtein() {
        return animalProtein;
    }

    public void setAnimalProtein(float animalProtein) {
        this.animalProtein = animalProtein;
    }

    public float getCalcium() {
        return calcium;
    }

    public void setCalcium(float calcium) {
        this.calcium = calcium;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getStarch() {
        return starch;
    }

    public void setStarch(float starch) {
        this.starch = starch;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFiber() {
        return fiber;
    }

    public void setFiber(float fiber) {
        this.fiber = fiber;
    }

    public float getIron() {
        return iron;
    }

    public void setIron(float iron) {
        this.iron = iron;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
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

    public float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public float getVitaminPP() {
        return vitaminPP;
    }

    public void setVitaminPP(float vitaminPP) {
        this.vitaminPP = vitaminPP;
    }

    public float getZinc() {
        return zinc;
    }

    public void setZinc(float zinc) {
        this.zinc = zinc;
    }

    @Override
    public String toString() {
        return animalFat +
                "," + animalProtein +
                "," + calcium +
                "," + fat +
                "," + starch +
                "," + protein +
                "," + fiber +
                "," + iron +
                "," + sodium +
                "," + vitaminB1 +
                "," + vitaminB2 +
                "," + vitaminC +
                "," + vitaminPP +
                "," + zinc;
    }
}
