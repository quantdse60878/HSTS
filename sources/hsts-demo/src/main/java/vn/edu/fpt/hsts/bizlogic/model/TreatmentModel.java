package vn.edu.fpt.hsts.bizlogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */
public class TreatmentModel {

    private List<FoodTreatmentModel> listFoodTreatment = new ArrayList<FoodTreatmentModel>();
    private List<MedicineTreatmentModel> listMedicineTreatment = new ArrayList<MedicineTreatmentModel>();
    private List<PracticeTreatmentModel> listPracticeTreatment = new ArrayList<PracticeTreatmentModel>();

    public TreatmentModel() {
    }

    public TreatmentModel(List<FoodTreatmentModel> listFoodTreatment, List<MedicineTreatmentModel> listMedicineTreatment, List<PracticeTreatmentModel> listPracticeTreatment) {
        this.listFoodTreatment = listFoodTreatment;
        this.listMedicineTreatment = listMedicineTreatment;
        this.listPracticeTreatment = listPracticeTreatment;
    }

    public List<FoodTreatmentModel> getListFoodTreatment() {
        return listFoodTreatment;
    }

    public void setListFoodTreatment(List<FoodTreatmentModel> listFoodTreatment) {
        this.listFoodTreatment = listFoodTreatment;
    }

    public List<MedicineTreatmentModel> getListMedicineTreatment() {
        return listMedicineTreatment;
    }

    public void setListMedicineTreatment(List<MedicineTreatmentModel> listMedicineTreatment) {
        this.listMedicineTreatment = listMedicineTreatment;
    }

    public List<PracticeTreatmentModel> getListPracticeTreatment() {
        return listPracticeTreatment;
    }

    public void setListPracticeTreatment(List<PracticeTreatmentModel> listPracticeTreatment) {
        this.listPracticeTreatment = listPracticeTreatment;
    }
}
