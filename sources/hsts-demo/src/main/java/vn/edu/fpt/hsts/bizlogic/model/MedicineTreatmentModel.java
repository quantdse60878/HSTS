package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class MedicineTreatmentModel implements Serializable {

    private String time;
    private List<Medicine> listMedicine;

    public MedicineTreatmentModel() {
        listMedicine = new ArrayList<Medicine>();
    }

    public MedicineTreatmentModel(String timeUse, List<Medicine> listMedicine) {
        this.time = timeUse;
        this.listMedicine = listMedicine;
    }

    public String getTime() {
        return time;
    }

    public void setTimeUse(String timeUse) {
        this.time = timeUse;
    }

    public List<Medicine> getListMedicine() {
        return listMedicine;
    }

    public void setListMedicine(List<Medicine> listMedicine) {
        this.listMedicine = listMedicine;
    }

    public void addMedicine(String medinineName, float numberOfMedicine, String advice) {
        listMedicine.add(new Medicine(medinineName, numberOfMedicine, advice));
    }

    class Medicine implements Serializable {
        private String name;
        private float quantitative;
        private String advice;

        Medicine() {
        }

        Medicine(String medicineName, float numberOfMedicine, String advice) {
            this.name = medicineName;
            this.quantitative = numberOfMedicine;
            this.advice = advice;
        }

        public String getName() {
            return name;
        }

        public void setMedicineName(String medicineName) {
            this.name = medicineName;
        }

        public float getQuantitative() {
            return quantitative;
        }

        public void setNumberOfMedicine(float numberOfMedicine) {
            this.quantitative = numberOfMedicine;
        }

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }
    }
}
