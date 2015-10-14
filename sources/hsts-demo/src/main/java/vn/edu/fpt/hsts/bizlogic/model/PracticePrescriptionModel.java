package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class PracticePrescriptionModel {
    /**
     * Practice
     */
    private int p;
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

    public PracticePrescriptionModel(int p, int pTime, String pIntensity, String pNote) {
        this.p = p;
        this.pTime = pTime;
        this.pIntensity = pIntensity;
        this.pNote = pNote;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
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

    @Override
    public String toString() {
        return "PracticePrescriptionModel{" +
                "p='" + p + '\'' +
                ", pTime='" + pTime + '\'' +
                ", pIntensity='" + pIntensity + '\'' +
                ", pNote='" + pNote + '\'' +
                '}';
    }
}
