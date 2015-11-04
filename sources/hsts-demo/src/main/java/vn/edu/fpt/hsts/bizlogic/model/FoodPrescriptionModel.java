package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class FoodPrescriptionModel {
    /**
     * Food
     */
    private int f;
    /**
     * Food Time
     */
    private int fTime;
    /**
     * Food Quantity
     */
    private int fQuantity;
    /**
     * Food Unit
     */
    private String fUnit;
    /**
     * Food Note
     */
    private String fNote;

    public FoodPrescriptionModel() {
    }

    public String getfUnit() {
        return fUnit;
    }

    public void setfUnit(String fUnit) {
        this.fUnit = fUnit;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getfTime() {
        return fTime;
    }

    public void setfTime(int fTime) {
        this.fTime = fTime;
    }

    public int getfQuantity() {
        return fQuantity;
    }

    public void setfQuantity(int fQuantity) {
        this.fQuantity = fQuantity;
    }

    public String getfNote() {
        return fNote;
    }

    public void setfNote(String fNote) {
        this.fNote = fNote;
    }

    public boolean isValid(){
        if (f <= 0){
            return false;
        } else if (fTime <= 0){
            return false;
        } else if (fQuantity <= 0){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodPrescriptionModel{" +
                "f=" + f +
                ", fTime=" + fTime +
                ", fQuantity='" + fQuantity + '\'' +
                ", fUnit=" + fUnit +
                ", fNote='" + fNote + '\'' +
                '}';
    }
}
