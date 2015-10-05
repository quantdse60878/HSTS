package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class MedicineTreatmentModel {

    private String timeUse;
    private List<Medicine> listMedicine;

    public MedicineTreatmentModel() {
    }

    public MedicineTreatmentModel(String timeUse, List<Medicine> listMedicine) {
        this.timeUse = timeUse;
        this.listMedicine = listMedicine;
    }

    public String getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(String timeUse) {
        this.timeUse = timeUse;
    }

    public List<Medicine> getListMedicine() {
        return listMedicine;
    }

    public void setListMedicine(List<Medicine> listMedicine) {
        this.listMedicine = listMedicine;
    }

    class Medicine {
        private String medicineName;
        private float numberOfMedicine;

        Medicine() {
        }

        Medicine(String medicineName, float numberOfMedicine) {
            this.medicineName = medicineName;
            this.numberOfMedicine = numberOfMedicine;
        }

        public String getMedicineName() {
            return medicineName;
        }

        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        public float getNumberOfMedicine() {
            return numberOfMedicine;
        }

        public void setNumberOfMedicine(float numberOfMedicine) {
            this.numberOfMedicine = numberOfMedicine;
        }
    }
}
