package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by Aking on 10/1/2015.
 */
public class PrescriptionModel {
    private String diagnostic;
    private MedicinePrescriptionModel mPresModel;
    private FoodPrescriptionModel fPresModel;
    private PracticePrescriptionModel pPresModel;

    public PrescriptionModel() {
    }

    public PrescriptionModel(String diagnostic,
                             MedicinePrescriptionModel mPresModel,
                             FoodPrescriptionModel fPresModel,
                             PracticePrescriptionModel pPresModel) {
        this.diagnostic = diagnostic;
        this.mPresModel = mPresModel;
        this.fPresModel = fPresModel;
        this.pPresModel = pPresModel;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public MedicinePrescriptionModel getmPresModel() {
        return mPresModel;
    }

    public void setmPresModel(MedicinePrescriptionModel mPresModel) {
        this.mPresModel = mPresModel;
    }

    public FoodPrescriptionModel getfPresModel() {
        return fPresModel;
    }

    public void setfPresModel(FoodPrescriptionModel fPresModel) {
        this.fPresModel = fPresModel;
    }

    public PracticePrescriptionModel getpPresModel() {
        return pPresModel;
    }

    public void setpPresModel(PracticePrescriptionModel pPresModel) {
        this.pPresModel = pPresModel;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "diagnostic='" + diagnostic + '\'' +
                ", mPresModel=" + mPresModel +
                ", fPresModel=" + fPresModel +
                ", pPresModel=" + pPresModel +
                '}';
    }
}
