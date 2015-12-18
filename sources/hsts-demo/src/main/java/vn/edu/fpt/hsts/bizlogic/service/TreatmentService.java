package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.edu.fpt.hsts.bizlogic.model.FoodTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.HisFood;
import vn.edu.fpt.hsts.bizlogic.model.HisInforDateModel;
import vn.edu.fpt.hsts.bizlogic.model.HisMedicine;
import vn.edu.fpt.hsts.bizlogic.model.HisPractice;
import vn.edu.fpt.hsts.bizlogic.model.MedicineTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticeTreatmentModel;
import vn.edu.fpt.hsts.bizlogic.model.TimesModel;
import vn.edu.fpt.hsts.bizlogic.model.TreatmentModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.PracticeTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.FoodTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.PracticeTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.TreatmentRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
            treatmentOfPatient.setIllnessName(medicalRecord.getIllness().getDescription());

            Appointment appointment = appointmentRepo.findAppointmentByMedicalRecordId(medicalRecord.getId());
            treatmentOfPatient.setAppointmentId(appointment.getId());
            if(appointment.getNextAppointment()!=null) {
                treatmentOfPatient.setNextAppointment(appointment.getNextAppointment().getMeetingDate().toString());
            }

            Treatment treatment = treatmentRepo.findTreatmentByAppointmentId(appointment.getId());
            if(treatment!=null) {
                treatmentOfPatient.setFromDate(treatment.getFromDate().toString());
                treatmentOfPatient.setToDate(treatment.getToDate().toString());
                treatmentOfPatient.setCaloriesBurnEveryday(treatment.getCaloriesBurnEveryday() + "");
                List<MedicineTreatmentModel> medicineTreatmentModels = new ArrayList<MedicineTreatmentModel>();
                List<FoodTreatmentModel> foodTreatmentModels = new ArrayList<FoodTreatmentModel>();
                List<PracticeTreatmentModel> practiceTreatmentModels = new ArrayList<PracticeTreatmentModel>();

                List<MedicineTreatment> medicineTreatment = medicineTreatmentRepo.findMedicineTreatmentByTreatmentId(treatment.getId());
                for(int j = 0; j < medicineTreatment.size(); j++) {
                    MedicineTreatment mItem = medicineTreatment.get(j);
                    // TODO change entity, comment code -> check again
                    MedicineTreatmentModel item = new MedicineTreatmentModel(mItem.getMedicine().getName(), mItem.getQuantitative() + "", mItem.getMedicine().getUnit(), mItem.getAdvice(), mItem.getNumberOfTime());
                    medicineTreatmentModels.add(item);
                }
                List<FoodTreatment> foodTreatments = foodTreatmentRepo.findFoodTreatmentTreatmentId(treatment.getId());
                for(int j = 0; j < foodTreatments.size(); j++) {
                    FoodTreatment fItem = foodTreatments.get(j);
                    FoodTreatmentModel item = new FoodTreatmentModel(fItem.getFood().getName(), fItem.getQuantitative() + fItem.getUnitName(), fItem.getAdvice(), fItem.getNumberOfTime());
                    foodTreatmentModels.add(item);
                }
                List<PracticeTreatment> practiceTreatments = practiceTreatmentRepo.findPracticeTreatmentByTreatmentId(treatment.getId());
                // TODO change entity, comment code -> check again
                for(int j = 0; j < practiceTreatments.size(); j++) {
                    PracticeTreatment pItem = practiceTreatments.get(j);
                    PracticeTreatmentModel item = new PracticeTreatmentModel(pItem.getPractice().getName(), pItem.getTimeDuration(), pItem.getAdvice(), pItem.getNumberOfTime());
                    practiceTreatmentModels.add(item);
                }
                treatmentOfPatient.setListFoodTreatment(foodTreatmentModels);
                treatmentOfPatient.setListMedicineTreatment(medicineTreatmentModels);
                treatmentOfPatient.setListPracticeTreatment(practiceTreatmentModels);
            }

            System.out.println("!!!!");


            treatmentModels.add(treatmentOfPatient);


        }

        return treatmentModels;
    }


    public List<TimesModel> getMedicineTimeConfig() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("medicineTimes[{}]", medicineTimes);
            }
            List<TimesModel> timesModels = new ArrayList<TimesModel>();
            if (StringUtils.isNotEmpty(medicineTimes)) {
                String[] list = medicineTimes.split(",");
                TimesModel timesModel;
                for (int i = 0; i < list.length; i++) {
                    timesModel = new TimesModel(Integer.parseInt(list[i]));
                    timesModels.add(timesModel);
                }
                return timesModels;
            }
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public Treatment findTreatmentByAppointmentID(final int appointmentId) {
        LOGGER.info("appointmentId[{}]", appointmentId);
        return treatmentRepo.findLastTreatmenByAppointmentId(appointmentId).get(0);
    }

    public List<MedicineTreatment> getAllMedicineTreatmentFromTreatment(final Treatment treatment) {
        LOGGER.info("treatment[{}]", treatment);
        return medicineTreatmentRepo.getAllMedicineTreatmentFromTreatment(treatment);
    }

    public List<FoodTreatment> getAllFoodTreatmentFromTreatment(Treatment treatment) {
        LOGGER.info("treatment[{}]", treatment);
        return foodTreatmentRepo.getAllFoodTreatmentFromTreatment(treatment);
    }

    public List<PracticeTreatment> getAllPracticeTreatmentFromTreatment(Treatment treatment) {
        LOGGER.info("treatment[{}]", treatment);
        return practiceTreatmentRepo.getAllPracticeTreatmentFromTreatment(treatment);
    }

    public String findLastMedicines(final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            // Find last treatment
            final List<Treatment> lastTreatment = treatmentRepo.findLastTreatmenByPatientId(patientId, new PageRequest(0, 1));
            if (null != lastTreatment && !lastTreatment.isEmpty()) {
                final Treatment treatment = lastTreatment.get(0);
                final List<MedicineTreatment> medicineTreatments = medicineTreatmentRepo.findMedicineTreatmentByTreatmentId(treatment.getId());
                if (null != medicineTreatments && !medicineTreatments.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (MedicineTreatment mt: medicineTreatments) {
                        sb.append(mt.getMedicine().getName()).append(",");
                    }
                    final int index = sb.lastIndexOf(",");
                    String result = sb.toString().substring(0, index);
                    return result;
                }
            }
            return "";
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public HisInforDateModel findInforByAppoitmentID(final int appoitmentID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appoitmentID[{}]", appoitmentID);
            final List<Treatment> list = treatmentRepo.findLastTreatmenByAppointmentId(appoitmentID);
            if (CollectionUtils.isEmpty(list)) {
                return null;
            }
            Treatment treatment = list.get(0);
            // Find treatment form appointment
            List<MedicineTreatment> medicineTreatments = medicineTreatmentRepo.getAllMedicineTreatmentFromTreatment(treatment);
            List<FoodTreatment> foodTreatments = foodTreatmentRepo.getAllFoodTreatmentFromTreatment(treatment);
            List<PracticeTreatment> practiceTreatments = practiceTreatmentRepo.getAllPracticeTreatmentFromTreatment(treatment);

            List<HisMedicine> hisMedicines = new ArrayList<HisMedicine>();
            List<HisFood> hisFoods = new ArrayList<HisFood>();
            List<HisPractice> hisPractices = new ArrayList<HisPractice>();

            // Create list hisMedicines
            MedicineTreatment mt;
            HisMedicine hm;
            for (int i = 0; i < medicineTreatments.size(); i++) {
                mt = medicineTreatments.get(i);
                hm = new HisMedicine(mt.getMedicine().getName(),mt.getNumberOfTime(),
                        mt.getQuantitative(),mt.getMedicine().getUnit(),mt.getAdvice());
                hisMedicines.add(hm);
            }

            // Create list hisFoods
            FoodTreatment ft;
            HisFood hf;
            for (int i = 0; i < foodTreatments.size(); i++) {
                ft = foodTreatments.get(i);
                hf = new HisFood(ft.getFood().getName(),ft.getNumberOfTime(),ft.getQuantitative(),
                        ft.getUnitName(),ft.getAdvice());
                hisFoods.add(hf);
            }

            // Create list hisPractices
            PracticeTreatment pt;
            HisPractice hp;
            for (int i = 0; i < practiceTreatments.size(); i++) {
                pt = practiceTreatments.get(i);
                hp = new HisPractice(pt.getPractice().getName(),pt.getNumberOfTime(),pt.getTimeDuration(),pt.getAdvice());
                hisPractices.add(hp);
            }

            HisInforDateModel hisInforDateModel = new HisInforDateModel(hisMedicines, hisFoods, hisPractices);
            // Get date info
            Date date = treatment.getAppointment().getMeetingDate();
            hisInforDateModel.setDateInfor(DateUtils.formatDate(date, DateUtils.DATE_PATTERN_3));

            return hisInforDateModel;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
