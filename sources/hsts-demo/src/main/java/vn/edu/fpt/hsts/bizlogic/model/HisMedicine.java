package vn.edu.fpt.hsts.bizlogic.model;

/**
 * Created by Aking on 11/6/2015.
 */
public class HisMedicine {
    private String name;
    private int times;
    private int quantity;
    private String unit;
    private String note;

    public HisMedicine(String name, int times, int quantity, String unit, String note) {
        this.name = name;
        this.times = times;
        this.quantity = quantity;
        this.unit = unit;
        this.note = note;
    }

    public HisMedicine() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
