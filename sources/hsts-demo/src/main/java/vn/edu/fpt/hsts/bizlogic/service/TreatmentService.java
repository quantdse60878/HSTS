package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.FoodTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicineTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticeTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.TreatmentModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.entity.*;
import vn.edu.fpt.hsts.persistence.repo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/6/15.
 */
@Service
public class TreatmentService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentService.class);

    /**
     *
     */
    @Value("${hsts.medicine.times}")
    private String medicineTimes;

    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private TreatmentRepo treatmentRepo;

//    @Autowired
//    private MedicineTimeRepo medicineTimeRepo;
    @Autowired
    private MedicineTreatmentRepo medicineTreatmentRepo;

//    @Autowired
//    private MealRepo mealRepo;
    @Autowired
    private FoodTreatmentRepo foodTreatmentRepo;

//    @Autowired
//    private PracticeTimeRepo practiceTimeRepo;
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

            List<MedicineTreatment> medicineTreatment = medicineTreatmentRepo.findMedicineTreatmentByTreatmentId(treatment.getId());
            for(int j = 0; j < medicineTreatment.size(); j++) {
                MedicineTreatment mItem = medicineTreatment.get(j);
                MedicineTreatmentModel item = new MedicineTreatmentModel(mItem.getMedicine().getName(), mItem.getQuantitative(), mItem.getAdvice(), mItem.getNumberOfTime());
                medicineTreatmentModels.add(item);
            }
            List<FoodTreatment> foodTreatments = foodTreatmentRepo.findFoodTreatmentTreatmentId(treatment.getId());
            for(int j = 0; j < foodTreatments.size(); j++) {
                FoodTreatment fItem = foodTreatments.get(j);
                FoodTreatmentModel item = new FoodTreatmentModel(fItem.getFood().getName(), fItem.getQuantitative(), fItem.getNumberOfTime());
                foodTreatmentModels.add(item);
            }
            List<PracticeTreatment> practiceTreatments = practiceTreatmentRepo.findPracticeTreatmentByTreatmentId(treatment.getId());
            for(int j = 0; j < practiceTreatments.size(); j++) {
                PracticeTreatment pItem = practiceTreatments.get(j);
                PracticeTreatmentModel item = new PracticeTreatmentModel(pItem.getPractice().getName(), pItem.getTimeDuration(), pItem.getNumberOfTime());
                practiceTreatmentModels.add(item);
            }
            treatmentOfPatient.setListFoodTreatment(foodTreatmentModels);
            treatmentOfPatient.setListMedicineTreatment(medicineTreatmentModels);
            treatmentOfPatient.setListPracticeTreatment(practiceTreatmentModels);
            System.out.println("!!!!");


        treatmentModels.add(treatmentOfPatient);


        }

        return treatmentModels;
    }


    public String[] getMedicineTimeConfig() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("medicineTimes[{}]", medicineTimes);
            }
            if (StringUtils.isNotEmpty(medicineTimes)) {
               return medicineTimes.split(",");
            }
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
