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

    public String getTime() {
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
        private String name;
        private int quantitative;

        public String getName() {
            return name;
        }

        public void setPracticeName(String practiceName) {
            this.name = practiceName;
        }

        public int getQuantitative() {
            return quantitative;
        }

        public void setDuration(int duration) {
            this.quantitative = duration;
        }

        Practice(String practiceName, int duration) {

            this.name = practiceName;
            this.quantitative = duration;
        }

        Practice() {

        }
    }


}
