package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

/**
 * Created by Aking on 10/11/2015.
 */
public class MedicinePrescriptionModel implements Serializable{
    /**
     * Medicine
     */
    private int m;
    /**
     * Medicine Time
     */
    private int mTime;


    /**
     * Medicine Quantity
     */
    private int mQuantity = 1;

    /**
     * Medicine Unit
     */
    private String mUnit;

    /**
     * Medicine Note
     */
    private String mNote;

    public MedicinePrescriptionModel() {
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

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public boolean isValid(){
        if (m <= 0){
            return false;
        } else if (mTime <= 0){
            return false;
        } else if (mQuantity <= 0 && mQuantity > 10){
            return false;
        } else if (mUnit.isEmpty() || null == mUnit){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MedicinePrescriptionModel{" +
                "m=" + m +
                ", mTime=" + mTime +
                ", mQuantity=" + mQuantity +
                ", mNote='" + mNote + '\'' +
                '}';
    }

}
