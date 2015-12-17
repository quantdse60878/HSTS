package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aking on 10/21/2015.
 */
public class FoodIngredientModel {

    /**
     *
     */
    private String breakfast;

    /**
     *
     */
    private String breakTimeMorning;

    /**
     *
     */
    private String lunch;

    /**
     *
     */
    private String breakTimeAfternoon;

    /**
     *
     */
    private String dinner;

    /**
     *
     */
    private String eatLateAtNight;

    /**
     *
     */
    private String starch;

    /**
     *
     */
    private String protein;

    /**
     *
     */
    private String fat;

    /**
     *
     */
    private String animalProtein;

    /**
     *
     */
    private String animalFat;

    /**
     *
     */
    private String calcium;

    /**
     *
     */
    private String sodium;

    /**
     *
     */
    private String iron;

    /**
     *
     */
    private String zinc;

    /**
     *
     */
    private String vitaminB1;

    /**
     *
     */
    private String vitaminC;

    /**
     *
     */
    private String vitaminB2;

    /**
     *
     */
    private String vitaminPP;

    /**
     *
     */
    private String fiber;

    /**
     *
     */
    private List<String> foodBreakfast = new ArrayList<String>();

    /**
     *
     */
    private List<String> foodBreaktimeMorning = new ArrayList<String>();

    /**
     *
     */
    private List<String> foodLunch = new ArrayList<String>();

    /**
     *
     */
    private List<String> foodBreaktimeAfternoon = new ArrayList<String>();

    /**
     *
     */
    private List<String> foodDinner = new ArrayList<String>();

    /**
     *
     */
    private List<String> foodEatLate = new ArrayList<String>();

    public FoodIngredientModel() {
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getBreakTimeMorning() {
        return breakTimeMorning;
    }

    public void setBreakTimeMorning(String breakTimeMorning) {
        this.breakTimeMorning = breakTimeMorning;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getBreakTimeAfternoon() {
        return breakTimeAfternoon;
    }

    public void setBreakTimeAfternoon(String breakTimeAfternoon) {
        this.breakTimeAfternoon = breakTimeAfternoon;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getEatLateAtNight() {
        return eatLateAtNight;
    }

    public void setEatLateAtNight(String eatLateAtNight) {
        this.eatLateAtNight = eatLateAtNight;
    }

    public String getStarch() {
        return starch;
    }

    public void setStarch(String starch) {
        this.starch = starch;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getAnimalProtein() {
        return animalProtein;
    }

    public void setAnimalProtein(String animalProtein) {
        this.animalProtein = animalProtein;
    }

    public String getAnimalFat() {
        return animalFat;
    }

    public void setAnimalFat(String animalFat) {
        this.animalFat = animalFat;
    }

    public String getCalcium() {
        return calcium;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getIron() {
        return iron;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }

    public String getZinc() {
        return zinc;
    }

    public void setZinc(String zinc) {
        this.zinc = zinc;
    }

    public String getVitaminB1() {
        return vitaminB1;
    }

    public void setVitaminB1(String vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }

    public String getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(String vitaminC) {
        this.vitaminC = vitaminC;
    }

    public String getVitaminB2() {
        return vitaminB2;
    }

    public void setVitaminB2(String vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }

    public String getVitaminPP() {
        return vitaminPP;
    }

    public void setVitaminPP(String vitaminPP) {
        this.vitaminPP = vitaminPP;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public List<String> getFoodBreakfast() {
        return foodBreakfast;
    }

    public void setFoodBreakfast(List<String> foodBreakfast) {
        this.foodBreakfast = foodBreakfast;
    }

    public List<String> getFoodBreaktimeMorning() {
        return foodBreaktimeMorning;
    }

    public void setFoodBreaktimeMorning(List<String> foodBreaktimeMorning) {
        this.foodBreaktimeMorning = foodBreaktimeMorning;
    }

    public List<String> getFoodLunch() {
        return foodLunch;
    }

    public void setFoodLunch(List<String> foodLunch) {
        this.foodLunch = foodLunch;
    }

    public List<String> getFoodBreaktimeAfternoon() {
        return foodBreaktimeAfternoon;
    }

    public void setFoodBreaktimeAfternoon(List<String> foodBreaktimeAfternoon) {
        this.foodBreaktimeAfternoon = foodBreaktimeAfternoon;
    }

    public List<String> getFoodDinner() {
        return foodDinner;
    }

    public void setFoodDinner(List<String> foodDinner) {
        this.foodDinner = foodDinner;
    }

    public List<String> getFoodEatLate() {
        return foodEatLate;
    }

    public void setFoodEatLate(List<String> foodEatLate) {
        this.foodEatLate = foodEatLate;
    }

    @Override
    public String toString() {
        return "FoodIngredientModel{" +
                "breakfast='" + breakfast + '\'' +
                ", breakTimeMorning='" + breakTimeMorning + '\'' +
                ", lunch='" + lunch + '\'' +
                ", breakTimeAfternoon='" + breakTimeAfternoon + '\'' +
                ", dinner='" + dinner + '\'' +
                ", eatLateAtNight='" + eatLateAtNight + '\'' +
                ", starch='" + starch + '\'' +
                ", protein='" + protein + '\'' +
                ", fat='" + fat + '\'' +
                ", animalProtein='" + animalProtein + '\'' +
                ", animalFat='" + animalFat + '\'' +
                ", calcium='" + calcium + '\'' +
                ", sodium='" + sodium + '\'' +
                ", iron='" + iron + '\'' +
                ", zinc='" + zinc + '\'' +
                ", vitaminB1='" + vitaminB1 + '\'' +
                ", vitaminC='" + vitaminC + '\'' +
                ", vitaminB2='" + vitaminB2 + '\'' +
                ", vitaminPP='" + vitaminPP + '\'' +
                ", fiber='" + fiber + '\'' +
                '}';
    }
}
