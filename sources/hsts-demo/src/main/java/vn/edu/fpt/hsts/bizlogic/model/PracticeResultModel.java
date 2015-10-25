package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 10/25/2015.
 */
public class PracticeResultModel {
    private int avgKcalConsumed;
    private int kcalEstimate;
    private int status;
    private float ratioCompletePractice;

    public PracticeResultModel() {
    }

    public float getRatioCompletePractice() {
        return ratioCompletePractice;
    }

    public void setRatioCompletePractice(float ratioCompletePractice) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PracticeResultModel{" +
                "avgKcalConsumed=" + avgKcalConsumed +
                ", kcalEstimate=" + kcalEstimate +
                ", status=" + status +
                ", ratioCompletePractice=" + ratioCompletePractice +
                '}';
    }
}
