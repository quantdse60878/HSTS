package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;

import java.io.Serializable;

/**
 * Created by Aking on 10/22/2015.
 */
public class NutritionModel implements Serializable{
    private FoodIngredient fi;
    private Float totalEnergy;
    private Double energy;
    private float breakfastPercent;

    private float breakTimeMorningPercent;

    /**
     *
     */
    private float lunchPercent;

    /**
     *
     */
    private float breakTimeAfternoonPercent;

    /**
     *
     */
    private float dinnerPercent;

    /**
     *
     */
    private float eatLateAtNightPercent;

    /**
     *
     */
    private float starchPercent;

    /**
     *
     */
    private float proteinPercent;

    /**
     *
     */
    private float fatPercent;

    public NutritionModel() {
    }

    public NutritionModel(FoodIngredient fi, Double weight) {
        this.fi = fi;
        totalEnergy = fi.getBreakfast() + fi.getBreakTimeMorning() + fi.getEatLateAtNight()+
                    fi.getLunch() + fi.getBreakTimeAfternoon() + fi.getDinner();
        energy = (totalEnergy / weight)*100;
        breakfastPercent = (fi.getBreakfast() / totalEnergy)*100;
        breakTimeAfternoonPercent = (fi.getBreakTimeAfternoon() / totalEnergy)*100;
        breakTimeMorningPercent = (fi.getBreakTimeMorning() / totalEnergy)*100;
        lunchPercent = (fi.getLunch() / totalEnergy)*100;
        dinnerPercent = (fi.getDinner() / totalEnergy)*100;
        eatLateAtNightPercent = (fi.getEatLateAtNight() / totalEnergy)*100;
    }

    public FoodIngredient getFi() {
        return fi;
    }

    public void setFi(FoodIngredient fi) {
        this.fi = fi;
    }

    public Float getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(Float totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public float getBreakfastPercent() {
        return breakfastPercent;
    }

    public void setBreakfastPercent(float breakfastPercent) {
        this.breakfastPercent = breakfastPercent;
    }

    public float getBreakTimeMorningPercent() {
        return breakTimeMorningPercent;
    }

    public void setBreakTimeMorningPercent(float breakTimeMorningPercent) {
        this.breakTimeMorningPercent = breakTimeMorningPercent;
    }

    public float getLunchPercent() {
        return lunchPercent;
    }

    public void setLunchPercent(float lunchPercent) {
        this.lunchPercent = lunchPercent;
    }

    public float getBreakTimeAfternoonPercent() {
        return breakTimeAfternoonPercent;
    }

    public void setBreakTimeAfternoonPercent(float breakTimeAfternoonPercent) {
        this.breakTimeAfternoonPercent = breakTimeAfternoonPercent;
    }

    public float getDinnerPercent() {
        return dinnerPercent;
    }

    public void setDinnerPercent(float dinnerPercent) {
        this.dinnerPercent = dinnerPercent;
    }

    public float getEatLateAtNightPercent() {
        return eatLateAtNightPercent;
    }

    public void setEatLateAtNightPercent(float eatLateAtNightPercent) {
        this.eatLateAtNightPercent = eatLateAtNightPercent;
    }

    public float getStarchPercent() {
        return starchPercent;
    }

    public void setStarchPercent(float starchPercent) {
        this.starchPercent = starchPercent;
    }

    public float getProteinPercent() {
        return proteinPercent;
    }

    public void setProteinPercent(float proteinPercent) {
        this.proteinPercent = proteinPercent;
    }

    public float getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(float fatPercent) {
        this.fatPercent = fatPercent;
    }
}
