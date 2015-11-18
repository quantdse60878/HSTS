package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aking on 10/1/2015.
 */
public class PrescriptionModel implements Serializable {

    private int appointmentId;
    private String appointmentDate;
    private String diagnostic;
    private String kcalRequire;
    private String note;
    private List<MedicinePrescriptionModel> mPresModels;
    private List<FoodPrescriptionModel> fPresModels;
    private List<PracticePrescriptionModel> pPresModels;

    public PrescriptionModel() {
        this.kcalRequire = "0";
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "appointmentId=" + appointmentId +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", diagnostic='" + diagnostic + '\'' +
                ", kcalRequire=" + kcalRequire +
                ", note='" + note + '\'' +
                ", mPresModels=" + mPresModels +
                ", fPresModels=" + fPresModels +
                ", pPresModels=" + pPresModels +
                '}';
    }
}
