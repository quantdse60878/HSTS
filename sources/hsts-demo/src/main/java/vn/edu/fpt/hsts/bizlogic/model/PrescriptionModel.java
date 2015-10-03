package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/1/2015.
 */
public class PrescriptionModel {
    private String diagnostic;
    private String medical;
    private String medicalNoTPD;
    private String medicalNoQPD;
    private String medicalNote;
    private String food;
    private String foodTime;
    private String foodNote;
    private String pratice;
    private String praticeIntensity;
    private String praticeNote;

    public PrescriptionModel(){

    }

    public PrescriptionModel(String diagnostic,
            String medical,
            String medicalNoTPD,
            String medicalNoQPD,
            String medicalNote,
            String food,
            String foodTime,
            String foodNote,
            String pratice,
            String praticeIntensity,
            String praticeNote){
        this.diagnostic = diagnostic;
        this.medical = medical;
        this.medicalNoTPD = medicalNoTPD;
        this.medicalNoQPD = medicalNoQPD;
        this.medicalNote = medicalNote;
        this.food = food;
        this.foodTime = foodTime;
        this.foodNote = foodNote;
        this.pratice = pratice;
        this.praticeIntensity = praticeIntensity;
        this.praticeNote = praticeNote;
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

    public String getMedicalNoTPD() {
        return medicalNoTPD;
    }

    public void setMedicalNoTPD(String medicalNoTPD) {
        this.medicalNoTPD = medicalNoTPD;
    }

    public String getMedicalNoQPD() {
        return medicalNoQPD;
    }

    public void setMedicalNoQPD(String medicalNoQPD) {
        this.medicalNoQPD = medicalNoQPD;
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

    public String getFoodNote() {
        return foodNote;
    }

    public void setFoodNote(String foodNote) {
        this.foodNote = foodNote;
    }

    public String getPratice() {
        return pratice;
    }

    public void setPratice(String pratice) {
        this.pratice = pratice;
    }

    public String getPraticeIntensity() {
        return praticeIntensity;
    }

    public void setPraticeIntensity(String praticeIntensity) {
        this.praticeIntensity = praticeIntensity;
    }

    public String getPraticeNote() {
        return praticeNote;
    }

    public void setPraticeNote(String praticeNote) {
        this.praticeNote = praticeNote;
    }
}
