package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class FoodPrescriptionModel {
    /**
     * Food
     */
    private String f;
    /**
     * Food Time
     */
    private String fTime;
    /**
     * Food Quantity
     */
    private String fQuantity;
    /**
     * Food Note
     */
    private String fNote;

    public FoodPrescriptionModel() {
    }

    public FoodPrescriptionModel(String f, String fTime, String fQuantity, String fNote) {
        this.f = f;
        this.fTime = fTime;
        this.fQuantity = fQuantity;
        this.fNote = fNote;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getfTime() {
        return fTime;
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    public String getfQuantity() {
        return fQuantity;
    }

    public void setfQuantity(String fQuantity) {
        this.fQuantity = fQuantity;
    }

    public String getfNote() {
        return fNote;
    }

    public void setfNote(String fNote) {
        this.fNote = fNote;
    }

    @Override
    public String toString() {
        return "FoodPrescriptionModel{" +
                "f='" + f + '\'' +
                ", fTime='" + fTime + '\'' +
                ", fQuantity='" + fQuantity + '\'' +
                ", fNote='" + fNote + '\'' +
                '}';
    }
}
