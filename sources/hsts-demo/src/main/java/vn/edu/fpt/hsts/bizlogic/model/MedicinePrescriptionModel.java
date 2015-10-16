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
    private String mUnit;

    /**
     * Medicine Quantity
     */
    private int mQuantity = 1;
    /**
     * Medicine Note
     */
    private String mNote;

    public MedicinePrescriptionModel() {
    }

    public MedicinePrescriptionModel(int m, int mTime, String mUnit, int mQuantity, String mNote) {
        this.m = m;
        this.mTime = mTime;
        this.mUnit = mUnit;
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

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    @Override
    public String toString() {
        return "MedicinePrescriptionModel{" +
                "m=" + m +
                ", mTime=" + mTime +
                ", mUnit='" + mUnit + '\'' +
                ", mQuantity=" + mQuantity +
                ", mNote='" + mNote + '\'' +
                '}';
    }
}
