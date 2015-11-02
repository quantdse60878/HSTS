package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class MedicinePrescriptionModel {
    /**
     * Medicine
     */
    private String m;
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

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
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

    public boolean isValid(){
        if (null == m || m.isEmpty()){
            return false;
        } else if (mTime <= 0){
            return false;
        } else if (mQuantity <= 0 && mQuantity > 15){
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
