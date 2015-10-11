package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class MedicinePrescriptionModel {
    private String medical;
    private String medicalTime;
    private String medicalQuantity;
    private String medicalNote;

    public MedicinePrescriptionModel() {
    }

    public MedicinePrescriptionModel(String medical, String medicalTime, String medicalQuantity, String medicalNote) {
        this.medical = medical;
        this.medicalTime = medicalTime;
        this.medicalQuantity = medicalQuantity;
        this.medicalNote = medicalNote;
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

    @Override
    public String toString() {
        return "MedicinePrescriptionModel{" +
                "medical='" + medical + '\'' +
                ", medicalTime='" + medicalTime + '\'' +
                ", medicalQuantity='" + medicalQuantity + '\'' +
                ", medicalNote='" + medicalNote + '\'' +
                '}';
    }
}
