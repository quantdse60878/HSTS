package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

/**
 * Created by Aking on 10/11/2015.
 */
public class PracticePrescriptionModel implements Serializable{
    /**
     * Practice
     */
    private String p;
    /**
     * Practice Time
     */
    private int pTime;
    /**
     * Practice Intensity
     */
    private String pIntensity;
    /**
     * Practice Note
     */
    private String pNote;

    public PracticePrescriptionModel() {
    }

    public PracticePrescriptionModel(String p, int pTime, String pIntensity, String pNote) {
        this.p = p;
        this.pTime = pTime;
        this.pIntensity = pIntensity;
        this.pNote = pNote;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public int getpTime() {
        return pTime;
    }

    public void setpTime(int pTime) {
        this.pTime = pTime;
    }

    public String getpIntensity() {
        return pIntensity;
    }

    public void setpIntensity(String pIntensity) {
        this.pIntensity = pIntensity;
    }

    public String getpNote() {
        return pNote;
    }

    public void setpNote(String pNote) {
        this.pNote = pNote;
    }

    public boolean isValid(){
        if (null == p || p.isEmpty()){
            return false;
        } else if (pTime <= 0){
            return false;
        } else if (null == pIntensity || pIntensity.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PracticePrescriptionModel{" +
                "p='" + p + '\'' +
                ", pTime=" + pTime +
                ", pIntensity='" + pIntensity + '\'' +
                ", pNote='" + pNote + '\'' +
                '}';
    }
}
