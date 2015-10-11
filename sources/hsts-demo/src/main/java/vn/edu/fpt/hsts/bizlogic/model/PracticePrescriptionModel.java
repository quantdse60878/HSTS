package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/11/2015.
 */
public class PracticePrescriptionModel {
    private String practice;
    private String practiceTime;
    private String practiceIntensity;
    private String practiceNote;

    public PracticePrescriptionModel() {
    }

    public PracticePrescriptionModel(String practice, String practiceTime, String practiceIntensity, String practiceNote) {
        this.practice = practice;
        this.practiceTime = practiceTime;
        this.practiceIntensity = practiceIntensity;
        this.practiceNote = practiceNote;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(String practiceTime) {
        this.practiceTime = practiceTime;
    }

    public String getPracticeIntensity() {
        return practiceIntensity;
    }

    public void setPracticeIntensity(String practiceIntensity) {
        this.practiceIntensity = practiceIntensity;
    }

    public String getPracticeNote() {
        return practiceNote;
    }

    public void setPracticeNote(String practiceNote) {
        this.practiceNote = practiceNote;
    }

    @Override
    public String toString() {
        return "PracticePrescriptionModel{" +
                "practice='" + practice + '\'' +
                ", practiceTime='" + practiceTime + '\'' +
                ", practiceIntensity='" + practiceIntensity + '\'' +
                ", practiceNote='" + practiceNote + '\'' +
                '}';
    }
}
