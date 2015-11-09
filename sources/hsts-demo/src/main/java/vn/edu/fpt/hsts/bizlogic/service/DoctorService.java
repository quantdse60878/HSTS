/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/8/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.DoctorModel;
import vn.edu.fpt.hsts.bizlogic.model.DoctorPageModel;
import vn.edu.fpt.hsts.bizlogic.model.FoodPrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticePrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticeResultModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.*;
import vn.edu.fpt.hsts.persistence.repo.*;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DoctorService extends AbstractService {

    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);

    /**
     * The {@link DoctorRepo}.
     */
    @Autowired
    private DoctorRepo doctorRepo;

    /**
     * The {@link AppointmentRepo}.
     */
    @Autowired
    private AppointmentRepo appointmentRepo;

    /**
     * The {@link TreatmentRepo}.
     */
    @Autowired
    private TreatmentRepo treatmentRepo;

    /**
     * The {@link MedicineTreatmentRepo}.
     */
    @Autowired
    private MedicineTreatmentRepo medicineTreatmentRepo;

    /**
     * The {@link MedicineRepo}.
     */
    @Autowired
    private MedicineRepo medicineRepo;

    /**
     * The {@link MedicalRecordRepo}.
     */
    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    /**
     * The {@link IllnessRepo}.
     */
    @Autowired
    private IllnessRepo illnessRepo;

    /**
     * The {@link FoodRepo}.
     */
    @Autowired
    private FoodRepo foodRepo;

    /**
     * The {@link FoodTreatmentRepo}.
     */
    @Autowired
    private FoodTreatmentRepo foodTreatmentRepo;

    /**
     * The {@link PracticeTreatmentRepo}.
     */
    @Autowired
    private PracticeTreatmentRepo practiceTreatmentRepo;

    /**
     * The {@link PracticeRepo}.
     */
    @Autowired
    private PracticeRepo practiceRepo;

    /**
     * The {@link NotifyRepo}.
     */
    @Autowired
    private NotifyRepo notifyRepo;

    /**
     * The {@link IllnessService}.
     */
    @Autowired
    private IllnessService illnessService;

    @Autowired
    private MedicalRecordDataRepo medicalRecordDataRepo;

    @Autowired
    private PropertyRecordRepo propertyRecordRepo;

    @Autowired
    private UnitOfFoodRepo unitOfFoodRepo;

    @Value("${hsts.default.treatment.long}")
    private int treatmentLong;

    public int getTreatmentLong() {
        return treatmentLong;
    }

    public void setTreatmentLong(int treatmentLong) {
        this.treatmentLong = treatmentLong;
    }

    public List<DoctorModel> findAll() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final List<Doctor> doctors = doctorRepo.findAll();
            if (null != doctors && !doctors.isEmpty()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Got {} records", doctors.size());
                }
                final List<DoctorModel> modelList = new ArrayList<DoctorModel>();
                for (Doctor doctor : doctors) {
                    DoctorModel model = new DoctorModel();
                    model.fromEntity(doctor);
                    modelList.add(model);
                }
                return modelList;
            }
            return Collections.emptyList();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public boolean makePrescription(final PrescriptionModel prescription,
                                    final int appointmentId,
                                    final String appointmentDate) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("prescription[{}], appointmentId[{}], appointmentDate[{}]", prescription, appointmentId, appointmentDate);
            // Find appointment by id
            final Appointment appointment = appointmentRepo.getOne(appointmentId);
            if (null == appointment) {
                LOGGER.error("Appointment with id[{}] is not found", appointmentId);
                return false;
            }
            appointment.setMeetingDate(new Date());
            appointment.setStatus(IDbConsts.IAppointmentStatus.FINISHED);
            Date toDate = null;
            MedicalRecord medicalRecord = appointment.getMedicalRecord();
            final String dianostic = prescription.getDiagnostic();
            if (StringUtils.isNotEmpty(dianostic)) {
                Illness illness = illnessRepo.findByName(dianostic);
                if (null == illness) {
                    illness = new Illness();
                    illness.setName(dianostic);
                    illness.setDescription(dianostic);
                    illnessRepo.saveAndFlush(illness);
                }
                if (medicalRecord.getIllness().getId() != illness.getId()){
                    // Close medicalrecord
                    Doctor doctor = medicalRecord.getDoctor();
                    Patient patient = medicalRecord.getPatient();
                    String symptoms = medicalRecord.getSymptoms();
                    String medicalHistory = medicalRecord.getMedicalHistory();
                    Date curDate = new Date();
                    medicalRecord.setEndTime(curDate);
                    medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.FINISHED);
                    medicalRecordRepo.saveAndFlush(medicalRecord);
                    // Create new medicalrecord
                    medicalRecord = new MedicalRecord();
                    medicalRecord.setStartTime(curDate);
                    medicalRecord.setDoctor(doctor);
                    medicalRecord.setPatient(patient);
                    medicalRecord.setSymptoms(symptoms);
                    medicalRecord.setMedicalHistory(medicalHistory);
                }
                medicalRecord.setIllness(illness);
            }
            medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.ON_TREATING);
            if (StringUtils.isNotEmpty(appointmentDate)) {
                toDate = DateUtils.parseDate(appointmentDate, DateUtils.DATE_PATTERN_3);
                if (null == toDate) {
                    LOGGER.info("Wrong input date format: {}", null, appointmentDate);
                    return false;
                }
            } else {
                // Set to next 7 day
                toDate = DateUtils.plusDateTime(new Date(), Calendar.DATE, treatmentLong);
                toDate = DateUtils.roundDate(toDate, false);
            }

            // Create next appointment
            LOGGER.info("Create next appointment : Start");
            Appointment nextAppointment = new Appointment();
            nextAppointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
            nextAppointment.setMeetingDate(toDate);
            nextAppointment.setMedicalRecord(appointment.getMedicalRecord());

            appointmentRepo.save(nextAppointment);
            LOGGER.info("Create next appointment : End");

            // Set old appointment link to new appointment
            LOGGER.info("Set old appointment link to new appointment : Start");
            appointment.setNextAppointment(nextAppointment);

            medicalRecordRepo.saveAndFlush(medicalRecord);
            appointmentRepo.save(appointment);
            LOGGER.info("Set old appointment link to new appointment : End");

            // Set old treatment to DONE
            LOGGER.info("Set old treatment to DONE : Start");
            // Find old nearest parent appointment
            LOGGER.info("Find old nearest parent appointment : Start");
            final Appointment oldAppointment = appointmentRepo.findParentAppointment(appointmentId);
            if (null != oldAppointment) {
                final List<Treatment> lastTreatments = oldAppointment.getTreatmentList();
                if (null != lastTreatments && !lastTreatments.isEmpty()) {
                    for (Treatment treatment : lastTreatments) {
                        treatment.setStatus(IDbConsts.ITreatmentStatus.FINISHED);
                        treatment.setToDate(new Date());
                        treatmentRepo.save(treatment);
                    }
                }
            }
            LOGGER.info("Find old nearest parent appointment : End");

            if (null != prescription) {
                // create new treatment
                LOGGER.info("Create new treatment : Start");
                final Treatment newTreatment = new Treatment();
                newTreatment.setStatus(IDbConsts.ITreatmentStatus.ON_TREATING);
                newTreatment.setAppointment(appointment);
                newTreatment.setFromDate(new Date());
                newTreatment.setCaloriesBurnEveryday(prescription.getKcalRequire());
                newTreatment.setToDate(toDate);
                treatmentRepo.save(newTreatment);
                LOGGER.info("Create new treatment : End");
                // TODO implement for medicine, food, practice and multiple row, validate data


                // Medicine
                LOGGER.info("Create new MedicineTreatment : Start");
                final List<MedicinePrescriptionModel> mPresModels = prescription.getmPresModels();
                if (null != mPresModels && !mPresModels.isEmpty()) {

                    for (MedicinePrescriptionModel medicineModel : mPresModels) {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug(medicineModel.toString());
                        }
                        if (medicineModel.isValid()) {
                            LOGGER.info("Medicine valid");
                            Medicine medicine = medicineRepo.findOne(medicineModel.getM());
                            if (null == medicine) {
                                LOGGER.info("Medicine with id[{}] is not found", null, medicineModel.getM());
                                // Create new medicine
//                                medicine = new Medicine();
//                                medicine.setName(medicineModel.getM());
//                                medicine.setUnit(medicineModel.getmUnit());
//                                medicineRepo.saveAndFlush(medicine);
//                                LOGGER.info("Create new medicine", medicine.getName());
                            }
                            LOGGER.info("Create MedicineTreatment");
                            MedicineTreatment medicineTreatment = new MedicineTreatment();
                            medicineTreatment.setMedicine(medicine);
                            medicineTreatment.setNumberOfTime(medicineModel.getmTime());
                            medicineTreatment.setQuantitative(medicineModel.getmQuantity());
                            medicineTreatment.setAdvice(medicineModel.getmNote());
                            medicineTreatment.setTreatment(newTreatment);
                            medicineTreatmentRepo.save(medicineTreatment);
                        }
                    }

                }
                LOGGER.info("Create new MedicineTreatment : End");

                // Food
                LOGGER.info("Create new FoodTreatment : Start");
                final List<FoodPrescriptionModel> fPresModels = prescription.getfPresModels();
                if (null != fPresModels && !fPresModels.isEmpty()) {
                    for (FoodPrescriptionModel foodModel : fPresModels) {
                        if (foodModel.isValid()) {
                            Food food = foodRepo.findOne(foodModel.getF());
                            if (null == food) {
                                LOGGER.info("Food with id[{}] is not found", null, foodModel.getF());
                                return false;
                            }
                            UnitOfFood unitOfFood = unitOfFoodRepo.findOne(foodModel.getfUnit());
                            if (null == unitOfFood) {
                                LOGGER.info("unitOfFood with id[{}] is not found", null, foodModel.getfUnit());
                                return false;
                            }
                            FoodTreatment foodTreatment = new FoodTreatment();
                            foodTreatment.setFood(food);
                            foodTreatment.setTreatment(newTreatment);
                            foodTreatment.setNumberOfTime(foodModel.getfTime());
                            foodTreatment.setQuantitative(foodModel.getfQuantity());
                            foodTreatment.setUnitName(unitOfFood.getUnitName());
                            foodTreatment.setAdvice(foodModel.getfNote());
                            foodTreatmentRepo.save(foodTreatment);
                        }
                    }

                }
                LOGGER.info("Create new FoodTreatment : End");

                // Practice
                LOGGER.info("Create new PracticeTreatment : Start");
                final List<PracticePrescriptionModel> pPresModels = prescription.getpPresModels();
                if (null != pPresModels && !pPresModels.isEmpty()) {

                    for (PracticePrescriptionModel practiceModel : pPresModels) {
                        if (practiceModel.isValid()) {
                            Practice practice = practiceRepo.findByName(practiceModel.getP());
                            if (null == practice) {
                                LOGGER.info("Practice with name[{}] is not found", null, practiceModel.getP());
                                // Create new practice
                                practice = new Practice();
                                practice.setName(practiceModel.getP());
                                practiceRepo.saveAndFlush(practice);
                                LOGGER.info("Create new practice", practice.getName());
                            }
                            PracticeTreatment practiceTreatment = new PracticeTreatment();
                            practiceTreatment.setTreatment(newTreatment);
                            practiceTreatment.setNumberOfTime(practiceModel.getpTime());
                            practiceTreatment.setPractice(practice);
                            practiceTreatment.setAdvice(practiceModel.getpNote());
                            practiceTreatment.setTimeDuration(practiceModel.getpIntensity());
                            practiceTreatmentRepo.save(practiceTreatment);
                        }
                    }
                }
                LOGGER.info("Create new PracticeTreatment : End");
            }
            LOGGER.info("Create new treatment : End");

            //TODO change status notify of doctor
            // Create notify to patient
            LOGGER.info("Create notify to patient : Start");
            final Notify notify = new Notify();
            notify.setSender(getCurrentAccount());
            notify.setReceiver(medicalRecord.getPatient().getAccount());
            notify.setType(IDbConsts.INotifyType.DOCTOR_PATIENT_PRESCRIPTION);
            notify.setStatus(IDbConsts.INotifyStatus.UNCOMPLETED);
            final int patientId = medicalRecord.getPatient().getId();
            notify.setMessage(String.valueOf(patientId));
            notifyRepo.saveAndFlush(notify);
            LOGGER.info("Create notify to patient : End");

            // flush all change to db
            LOGGER.info("Flush all change to db");
            appointmentRepo.flush();
            treatmentRepo.flush();
            medicineTreatmentRepo.flush();
            foodTreatmentRepo.flush();
            practiceTreatmentRepo.flush();

            return true;
        } catch (BizlogicException e) {
            LOGGER.info("BizlogicException: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            LOGGER.info("Error while making new prescription: {}", e.getMessage());
            return false;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public List<MedicinePhaseModel> getMedicines(final int appointmentId, final String diagnostic) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Find Illness
            Illness illness = illnessRepo.findByName(diagnostic);

            // Find phase for diagnostic
            final Phase phase = illnessService.getPhaseSugestion(appointmentId, illness);
            if (null == phase) {
                return Collections.emptyList();
            }
            final List<MedicinePhase> medicinePhases = phase.getMedicinePhaseList();
            if (null != medicinePhases && !medicinePhases.isEmpty()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Got {} records", medicinePhases.size());
                }
                final List<MedicinePhaseModel> listData = new ArrayList<MedicinePhaseModel>();
                for (MedicinePhase medicinePhase : medicinePhases) {
                    MedicinePhaseModel model = new MedicinePhaseModel();
                    model.fromEntity(medicinePhase);
                    listData.add(model);
                }
                return listData;
            }
            return Collections.emptyList();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PracticeResultModel getInfoPracticeDataOfPatient(final Appointment appointment) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointment[{}]", appointment);
            PracticeResultModel resultModel = new PracticeResultModel();
            Appointment oldAppointment = appointmentRepo.findParentAppointment(appointment.getId());

            if (null != oldAppointment) {
                Treatment treatment = treatmentRepo.findLastTreatmenByAppointmentId(oldAppointment.getId()).get(0);
                List<MedicalRecordData> medicalRecordDatas = medicalRecordDataRepo.findRecordDataByAppointment(oldAppointment, oldAppointment.getMeetingDate(), appointment.getMeetingDate());
                LOGGER.info("medicalRecordDatas: " + medicalRecordDatas.size());
                if (medicalRecordDatas.size() > 0) {
                    int kcalEstimate = treatment.getCaloriesBurnEveryday();
                    resultModel.setKcalEstimate(kcalEstimate);
                    int kcalConsumed = 0;
                    int count = medicalRecordDatas.size();
                    for (int i = 0; i < count; i++) {

                        PropertyRecord propertyRecord = propertyRecordRepo.findPropertyRecordByMrdAndpm(medicalRecordDatas.get(i).getId(), 2);
                        kcalConsumed += Integer.parseInt(propertyRecord.getParamMeasurementValue());
                    }
                    kcalConsumed = kcalConsumed / count;
                    resultModel.setAvgKcalConsumed(kcalConsumed);
                    float ratioCompletePractice = ((float) kcalConsumed / (float) kcalEstimate) * 100;
                    resultModel.setRatioCompletePractice(ratioCompletePractice);
                    if (ratioCompletePractice >= 130) {
                        resultModel.setStatus(3);
                    } else if (ratioCompletePractice < 130 && ratioCompletePractice >= 85) {
                        resultModel.setStatus(2);
                    } else if (ratioCompletePractice < 85) {
                        resultModel.setStatus(1);
                    }
                    LOGGER.info(resultModel.toString());
                    return resultModel;
                }
            }
            return null;
        } catch (Exception e) {
            LOGGER.info("Exception: " + e.getMessage());
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public DoctorPageModel getDoctors(final String nameSearch, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("nameSearch[{}], page[{}], pageSize[{}]", nameSearch, page, pageSize);
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            final String searchCond = "%" + nameSearch + "%";
            Page<Doctor> doctors = doctorRepo.findByNameLike(searchCond, IDbConsts.IAccountStatus.ACTIVE, pageRequest);
            final DoctorPageModel pageModel = new DoctorPageModel(doctors);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
