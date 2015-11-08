package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 11/6/2015.
 */
public class HisPractice {
    private String name;
    private int times;
    private String quantity;
    private String note;

    public HisPractice() {
    }

    public HisPractice(String name, int times, String quantity, String note) {
        this.name = name;
        this.times = times;
        this.quantity = quantity;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
