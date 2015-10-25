package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/25/2015.
 */
public class PracticeResultModel {
    private int avgKcalConsumed;
    private int kcalEstimate;
    private String status;
    private int ratioCompletePractice;

    public PracticeResultModel() {
    }

    public PracticeResultModel(int avgKcalConsumed, int kcalEstimate, String status, int ratioCompletePractice) {
        this.avgKcalConsumed = avgKcalConsumed;
        this.kcalEstimate = kcalEstimate;
        this.status = status;
        this.ratioCompletePractice = ratioCompletePractice;
    }

    public int getAvgKcalConsumed() {
        return avgKcalConsumed;
    }

    public void setAvgKcalConsumed(int avgKcalConsumed) {
        this.avgKcalConsumed = avgKcalConsumed;
    }

    public int getKcalEstimate() {
        return kcalEstimate;
    }

    public void setKcalEstimate(int kcalEstimate) {
        this.kcalEstimate = kcalEstimate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRatioCompletePractice() {
        return ratioCompletePractice;
    }

    public void setRatioCompletePractice(int ratioCompletePractice) {
        this.ratioCompletePractice = ratioCompletePractice;
    }

    @Override
    public String toString() {
        return "PracticeResultModel{" +
                "avgKcalConsumed=" + avgKcalConsumed +
                ", kcalEstimate=" + kcalEstimate +
                ", status='" + status + '\'' +
                ", ratioCompletePractice=" + ratioCompletePractice +
                '}';
    }
}
