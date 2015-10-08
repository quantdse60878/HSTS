package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.FoodTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicineTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticeTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.TreatmentModel;
import vn.edu.fpt.hsts.persistence.entity.*;
import vn.edu.fpt.hsts.persistence.repo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/6/15.
 */
@Service
public class TreatmentService {

    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private TreatmentRepo treatmentRepo;

    @Autowired
    private MedicineTimeRepo medicineTimeRepo;
    @Autowired
    private MedicineTreatmentRepo medicineTreatmentRepo;

    @Autowired
    private MealRepo mealRepo;
    @Autowired
    private FoodTreatmentRepo foodTreatmentRepo;

    @Autowired
    private PracticeTimeRepo practiceTimeRepo;
    @Autowired
    private PracticeTreatmentRepo practiceTreatmentRepo;

    public List<TreatmentModel> getTreatmentOfPatient(final int patientId) {
        List<TreatmentModel> treatmentModels = new ArrayList<TreatmentModel>();


        List<MedicalRecord> listMedicalRecordsPatient = medicalRecordRepo.findMedicalRecordByPatientId(patientId);


        for(int i = 0; i < listMedicalRecordsPatient.size(); i++) {
            TreatmentModel treatmentOfPatient = new TreatmentModel();

            MedicalRecord medicalRecord = listMedicalRecordsPatient.get(i);
            treatmentOfPatient.setIllnessName(medicalRecord.getIllness().getName());

            Appointment appointment = appointmentRepo.findAppointmentByMedicalRecordId(medicalRecord.getId());
            treatmentOfPatient.setAppointmentId(appointment.getId());
            treatmentOfPatient.setNextAppointment(appointment.getNextAppointment().getMeetingDate().toString());

            Treatment treatment = treatmentRepo.findTreatmentByAppointmentId(appointment.getId());

            treatmentOfPatient.setFromDate(treatment.getFromDate().toString());
            treatmentOfPatient.setToDate(treatment.getToDate().toString());
            treatmentOfPatient.setAdviseFood(treatment.getAdviseFood());
            treatmentOfPatient.setAdvicePractice(treatment.getAdvisePractice());
            treatmentOfPatient.setAdviseMedicine(treatment.getAdviseMedicine());

            List<MedicineTreatmentModel> medicineTreatmentModels = new ArrayList<MedicineTreatmentModel>();
            List<FoodTreatmentModel> foodTreatmentModels = new ArrayList<FoodTreatmentModel>();
            List<PracticeTreatmentModel> practiceTreatmentModels = new ArrayList<PracticeTreatmentModel>();

            List<MedicineTime> medicineTimes = medicineTimeRepo.findMedicineTimeByTreatmentId(treatment.getId());
            for(int j = 0; j < medicineTimes.size(); j++) {
                MedicineTreatmentModel medicineTreatmentItem = new MedicineTreatmentModel();
                MedicineTime medicineTimeItem = medicineTimes.get(j);
                medicineTreatmentItem.setTimeUse(medicineTimeItem.getTimeUse().toString());

                List<MedicineTreatment> medicineTreatments = medicineTreatmentRepo.findMedicineTreatmentByMedicineTimeId(medicineTimeItem.getId());

                for(MedicineTreatment medicineTreatment : medicineTreatments)  {
                    medicineTreatmentItem.addMedicine(medicineTreatment.getMedicine().getName(), medicineTreatment.getNumberOfMedicine(), medicineTreatment.getAdvice());
                }

                medicineTreatmentModels.add(medicineTreatmentItem);
            }
            List<Meal> foodTimes = mealRepo.findMealByTreatmentId(treatment.getId());
            for(int j = 0; j < foodTimes.size(); j++) {
                FoodTreatmentModel foodTreatmentItem = new FoodTreatmentModel();
                Meal mealItem = foodTimes.get(j);
                foodTreatmentItem.setTimeEat(mealItem.getTimeStart().toString());

                List<FoodTreatment> foodTreatments = foodTreatmentRepo.findFoodTreatmentByMealId(mealItem.getId());
                for(FoodTreatment foodTreatment : foodTreatments) {
                    foodTreatmentItem.addFood(foodTreatment.getFood().getName(), foodTreatment.getQuantitative());
                }
                foodTreatmentModels.add(foodTreatmentItem);

            }
            List<PracticeTime> practiceTimes = practiceTimeRepo.findPracticeTimeByTreatmentId(treatment.getId());
            for(int j = 0; j < practiceTimes.size(); j++) {
                PracticeTreatmentModel practiceTreatmentItem = new PracticeTreatmentModel();
                PracticeTime practiceTimeItem = practiceTimes.get(j);
                practiceTreatmentItem.setTimePractice(practiceTimeItem.getTimeStart().toString());

                List<PracticeTreatment> practiceTreatments = practiceTreatmentRepo.findPracticeTreatmentByPracticeTimeId(practiceTimeItem.getId());
                for(PracticeTreatment practiceTreatment : practiceTreatments) {
                    practiceTreatmentItem.addPractice(practiceTreatment.getPractice().getName(), practiceTreatment.getTimeDuration());
                }
                practiceTreatmentModels.add(practiceTreatmentItem);
            }
            treatmentOfPatient.setListFoodTreatment(foodTreatmentModels);
            treatmentOfPatient.setListMedicineTreatment(medicineTreatmentModels);
            treatmentOfPatient.setListPracticeTreatment(practiceTreatmentModels);
            System.out.println("!!!!");


        treatmentModels.add(treatmentOfPatient);


        }

        return treatmentModels;
    }

}
