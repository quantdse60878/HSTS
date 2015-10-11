package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class PracticePrescriptionModel {
    /**
     * Practice
     */
    private String p;
    /**
     * Practice Time
     */
    private String pTime;
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

    public PracticePrescriptionModel(String p, String pTime, String pIntensity, String pNote) {
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

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
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
