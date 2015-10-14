package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by Aking on 10/1/2015.
 */
public class PrescriptionModel {
    private String diagnostic;
    private String kcalRequire;
    private List<MedicinePrescriptionModel> mPresModels;
    private List<FoodPrescriptionModel> fPresModels;
    private List<PracticePrescriptionModel> pPresModels;


    public PrescriptionModel() {
    }

    public PrescriptionModel(String diagnostic,
                             String kcalRequire,
                             List<MedicinePrescriptionModel> mPresModels,
                             List<FoodPrescriptionModel> fPresModels,
                             List<PracticePrescriptionModel> pPresModels) {
        this.diagnostic = diagnostic;
        this.kcalRequire = kcalRequire;
        this.mPresModels = mPresModels;
        this.fPresModels = fPresModels;
        this.pPresModels = pPresModels;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getKcalRequire() {
        return kcalRequire;
    }

    public void setKcalRequire(String kcalRequire) {
        this.kcalRequire = kcalRequire;
    }

    public List<MedicinePrescriptionModel> getmPresModels() {
        return mPresModels;
    }

    public void setmPresModels(List<MedicinePrescriptionModel> mPresModels) {
        this.mPresModels = mPresModels;
    }

    public List<FoodPrescriptionModel> getfPresModels() {
        return fPresModels;
    }

    public void setfPresModels(List<FoodPrescriptionModel> fPresModels) {
        this.fPresModels = fPresModels;
    }

    public List<PracticePrescriptionModel> getpPresModels() {
        return pPresModels;
    }

    public void setpPresModels(List<PracticePrescriptionModel> pPresModels) {
        this.pPresModels = pPresModels;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "diagnostic='" + diagnostic + '\'' +
                ", kcalRequire='" + kcalRequire + '\'' +
                ", mPresModels=" + mPresModels +
                ", fPresModels=" + fPresModels +
                ", pPresModels=" + pPresModels +
                '}';
    }
}
