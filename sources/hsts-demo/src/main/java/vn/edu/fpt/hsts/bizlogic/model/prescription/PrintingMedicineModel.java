/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/15/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model.prescription;

import java.io.Serializable;

public class PrintingMedicineModel implements Serializable {

    /**
     *
     */
    private String number;

    /**
     *
     */
    private String medicineName;

    /**
     *
     */
    private String qty;

    /**
     *
     */
    private String unit;

    /**
     *
     */
    private String usage;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getQty() {
        return qty;
    }

    public void setQty(final String qty) {
        this.qty = qty;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(final String usage) {
        this.usage = usage;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public PrintingMedicineModel() {
        this.number = "";
        this.medicineName = "";
        this.qty = "";
        this.unit = "";
        this.usage = "";
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}
