/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/15/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model.prescription;

import java.util.List;

public class MedicineListWraper {

    private List<PrintingMedicineModel> items;

    public List<PrintingMedicineModel> getItems() {
        return items;
    }

    public void setItems(final List<PrintingMedicineModel> items) {
        this.items = items;
    }

    public MedicineListWraper(List<PrintingMedicineModel> items) {
        this.items = items;
    }

    public MedicineListWraper() {
    }
}
