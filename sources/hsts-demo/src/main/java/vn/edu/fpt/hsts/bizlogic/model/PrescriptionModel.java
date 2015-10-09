package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by Aking on 10/1/2015.
 */
public class PrescriptionModel {
    private String diagnostic;
    private String medical;
    private String medicalTime;
    private String medicalQuantity;
    private String medicalNote;
    private String food;
    private String foodTime;
    private String foodQuantity;
    private String foodNote;
    private String practice;
    private String practiceTime;
    private String practiceIntensity;
    private String practiceNote;

    public PrescriptionModel() {
    }

    public PrescriptionModel(String diagnostic,
                             String medical,
                             String medicalTime,
                             String medicalQuantity,
                             String medicalNote,
                             String food,
                             String foodTime,
                             String foodQuantity,
                             String foodNote,
                             String practice,
                             String practiceTime,
                             String practiceIntensity,
                             String practiceNote) {
        this.diagnostic = diagnostic;
        this.medical = medical;
        this.medicalTime = medicalTime;
        this.medicalQuantity = medicalQuantity;
        this.medicalNote = medicalNote;
        this.food = food;
        this.foodTime = foodTime;
        this.foodQuantity = foodQuantity;
        this.foodNote = foodNote;
        this.practice = practice;
        this.practiceTime = practiceTime;
        this.practiceIntensity = practiceIntensity;
        this.practiceNote = practiceNote;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getMedicalTime() {
        return medicalTime;
    }

    public void setMedicalTime(String medicalTime) {
        this.medicalTime = medicalTime;
    }

    public String getMedicalQuantity() {
        return medicalQuantity;
    }

    public void setMedicalQuantity(String medicalQuantity) {
        this.medicalQuantity = medicalQuantity;
    }

    public String getMedicalNote() {
        return medicalNote;
    }

    public void setMedicalNote(String medicalNote) {
        this.medicalNote = medicalNote;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodNote() {
        return foodNote;
    }

    public void setFoodNote(String foodNote) {
        this.foodNote = foodNote;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(String practiceTime) {
        this.practiceTime = practiceTime;
    }

    public String getPracticeIntensity() {
        return practiceIntensity;
    }

    public void setPracticeIntensity(String practiceIntensity) {
        this.practiceIntensity = practiceIntensity;
    }

    public String getPracticeNote() {
        return practiceNote;
    }

    public void setPracticeNote(String practiceNote) {
        this.practiceNote = practiceNote;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "diagnostic='" + diagnostic + '\'' +
                ", medical='" + medical + '\'' +
                ", medicalNoTPD='" + medicalTime + '\'' +
                ", medicalNoQPD='" + medicalQuantity + '\'' +
                ", medicalNote='" + medicalNote + '\'' +
                ", food='" + food + '\'' +
                ", foodTime='" + foodTime + '\'' +
                ", foodQuantity='" + foodQuantity + '\'' +
                ", foodNote='" + foodNote + '\'' +
                ", practice='" + practice + '\'' +
                ", practiceTime='" + practiceTime + '\'' +
                ", practiceIntensity='" + practiceIntensity + '\'' +
                ", practiceNote='" + practiceNote + '\'' +
                '}';
    }
}
