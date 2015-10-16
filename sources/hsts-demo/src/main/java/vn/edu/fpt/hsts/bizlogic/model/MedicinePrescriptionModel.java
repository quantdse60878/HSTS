package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class MedicinePrescriptionModel {
    /**
     * Medicine
     */
    private int m;
    /**
     * Medicine Time
     */
    private int mTime;

    /**
     *
     */
    private String unit;

    /**
     * Medicine Quantity
     */
    private int mQuantity;
    /**
     * Medicine Note
     */
    private String mNote;

    public MedicinePrescriptionModel() {
    }

    public MedicinePrescriptionModel(int m, int mTime, int mQuantity, String mNote) {
        this.m = m;
        this.mTime = mTime;
        this.mQuantity = mQuantity;
        this.mNote = mNote;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getmTime() {
        return mTime;
    }

    public void setmTime(int mTime) {
        this.mTime = mTime;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "MedicinePrescriptionModel{" +
                "m='" + m + '\'' +
                ", mTime='" + mTime + '\'' +
                ", mQuantity='" + mQuantity + '\'' +
                ", mNote='" + mNote + '\'' +
                '}';
    }
}
