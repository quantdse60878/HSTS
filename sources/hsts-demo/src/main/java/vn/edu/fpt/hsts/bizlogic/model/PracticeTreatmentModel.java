package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class PracticeTreatmentModel implements Serializable {

    private String time;
    private List<Practice> listPractice;

    public PracticeTreatmentModel() {
        listPractice = new ArrayList<Practice>();
    }

    public PracticeTreatmentModel(String timePractice, List<Practice> listPractice) {
        this.time = timePractice;
        this.listPractice = listPractice;
    }

    public String getTimePractice() {
        return time;
    }

    public void setTimePractice(String timePractice) {
        this.time = timePractice;
    }

    public List<Practice> getListPractice() {
        return listPractice;
    }

    public void setListPractice(List<Practice> listPractice) {
        this.listPractice = listPractice;
    }

    public void addPractice(String practiceName, int duration) {
        listPractice.add(new Practice(practiceName, duration));
    }
    class Practice implements Serializable {
        private String practiceName;
        private int duration;

        public String getPracticeName() {
            return practiceName;
        }

        public void setPracticeName(String practiceName) {
            this.practiceName = practiceName;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        Practice(String practiceName, int duration) {

            this.practiceName = practiceName;
            this.duration = duration;
        }

        Practice() {

        }
    }


}
