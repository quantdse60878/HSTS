package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by Aking on 10/1/2015.
 */
public class PrescriptionModel {
    private String diagnostic;
    private MedicinePrescriptionModel medicinePrescriptionModel;
    private FoodPrescriptionModel foodPrescriptionModel;
    private PracticePrescriptionModel practicePrescriptionModel;

    public PrescriptionModel() {
    }

    public PrescriptionModel(String diagnostic,
                             MedicinePrescriptionModel medicinePrescriptionModel,
                             FoodPrescriptionModel foodPrescriptionModel,
                             PracticePrescriptionModel practicePrescriptionModel) {
        this.diagnostic = diagnostic;
        this.medicinePrescriptionModel = medicinePrescriptionModel;
        this.foodPrescriptionModel = foodPrescriptionModel;
        this.practicePrescriptionModel = practicePrescriptionModel;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public MedicinePrescriptionModel getMedicinePrescriptionModel() {
        return medicinePrescriptionModel;
    }

    public void setMedicinePrescriptionModel(MedicinePrescriptionModel medicinePrescriptionModel) {
        this.medicinePrescriptionModel = medicinePrescriptionModel;
    }

    public FoodPrescriptionModel getFoodPrescriptionModel() {
        return foodPrescriptionModel;
    }

    public void setFoodPrescriptionModel(FoodPrescriptionModel foodPrescriptionModel) {
        this.foodPrescriptionModel = foodPrescriptionModel;
    }

    public PracticePrescriptionModel getPracticePrescriptionModel() {
        return practicePrescriptionModel;
    }

    public void setPracticePrescriptionModel(PracticePrescriptionModel practicePrescriptionModel) {
        this.practicePrescriptionModel = practicePrescriptionModel;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "diagnostic='" + diagnostic + '\'' +
                ", medicinePrescriptionModel=" + medicinePrescriptionModel +
                ", foodPrescriptionModel=" + foodPrescriptionModel +
                ", practicePrescriptionModel=" + practicePrescriptionModel +
                '}';
    }
}
