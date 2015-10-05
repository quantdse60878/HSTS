package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class PracticeTreatmentModel {

    private String timePractice;
    private List<Practice> listPractice;

    public PracticeTreatmentModel() {
    }

    public PracticeTreatmentModel(String timePractice, List<Practice> listPractice) {
        this.timePractice = timePractice;
        this.listPractice = listPractice;
    }

    public String getTimePractice() {
        return timePractice;
    }

    public void setTimePractice(String timePractice) {
        this.timePractice = timePractice;
    }

    public List<Practice> getListPractice() {
        return listPractice;
    }

    public void setListPractice(List<Practice> listPractice) {
        this.listPractice = listPractice;
    }

    class Practice {
        private String practiceName;
        private String duration;

        public String getPracticeName() {
            return practiceName;
        }

        public void setPracticeName(String practiceName) {
            this.practiceName = practiceName;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        Practice(String practiceName, String duration) {

            this.practiceName = practiceName;
            this.duration = duration;
        }

        Practice() {

        }
    }


}
