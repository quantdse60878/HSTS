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
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.DoctorModel;
import vn.edu.fpt.hsts.bizlogic.model.FoodPrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticePrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Doctor;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Notify;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.Practice;
import vn.edu.fpt.hsts.persistence.entity.PracticeTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.DoctorRepo;
import vn.edu.fpt.hsts.persistence.repo.FoodRepo;
import vn.edu.fpt.hsts.persistence.repo.FoodTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.IllnessRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.NotifyRepo;
import vn.edu.fpt.hsts.persistence.repo.PracticeRepo;
import vn.edu.fpt.hsts.persistence.repo.PracticeTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.TreatmentRepo;

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
                for (Doctor doctor: doctors) {
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
            final Appointment appointment = appointmentRepo.findOne(appointmentId);
            if (null == appointment) {
                LOGGER.error("Appointment with id[{}] is not found", appointmentId);
                return false;
            }
            appointment.setMeetingDate(new Date());
            appointment.setStatus(IDbConsts.IAppointmentStatus.FINISHED);
            Date toDate = null;
            final MedicalRecord medicalRecord = appointment.getMedicalRecord();
            final String dianostic = prescription.getDiagnostic();
            if (StringUtils.isNotEmpty(dianostic)) {
                final Illness illness = illnessRepo.findOne(Integer.parseInt(dianostic));
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
            }

            // Create next appointment
            LOGGER.info("Create next appointment : Start");
            Appointment nextAppointment = new Appointment();
            nextAppointment.setStatus(IDbConsts.IAppointmentStatus.WATTING);
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
            if(null != oldAppointment) {
                final List<Treatment> lastTreatments = oldAppointment.getTreatmentList();
                if (null != lastTreatments && !lastTreatments.isEmpty()) {
                    for(Treatment treatment: lastTreatments) {
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

                    for(MedicinePrescriptionModel medicineModel: mPresModels) {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug(medicineModel.toString());
                        }
                        if (medicineModel.getM() <= 0) {
                            continue;
                        }
                        Medicine medicine = medicineRepo.findOne(medicineModel.getM());
                        if (null == medicine) {
                            LOGGER.info("Medicine with id[{}] is not found", null, medicineModel.getM());
                            return false;
                        }
                        MedicineTreatment medicineTreatment = new MedicineTreatment();
                        medicineTreatment.setMedicine(medicine);
                        medicineTreatment.setNumberOfTime(medicineModel.getmTime());
                        medicineTreatment.setQuantitative(medicineModel.getmQuantity());
                        medicineTreatment.setAdvice(medicineModel.getmNote());
                        medicineTreatment.setTreatment(newTreatment);
                        medicineTreatmentRepo.save(medicineTreatment);
                    }
                }
                LOGGER.info("Create new MedicineTreatment : End");

                // Food
                LOGGER.info("Create new FoodTreatment : Start");
                final List<FoodPrescriptionModel> fPresModels = prescription.getfPresModels();
                if(null != fPresModels && !fPresModels.isEmpty()) {
                    for (FoodPrescriptionModel foodModel: fPresModels) {
                        if (foodModel.getF() <= 0) {
                            continue;
                        }
                        Food food = foodRepo.findOne(foodModel.getF());
                        if (null == food) {
                            LOGGER.info("Food with id[{}] is not found", null, foodModel.getF());
                            return false;
                        }
                        FoodTreatment foodTreatment = new FoodTreatment();
                        foodTreatment.setFood(food);
                        foodTreatment.setTreatment(newTreatment);
                        foodTreatment.setNumberOfTime(foodModel.getfTime());
                        foodTreatment.setQuantitative(foodModel.getfQuantity());
                        foodTreatment.setAdvice(foodModel.getfNote());
                        foodTreatmentRepo.save(foodTreatment);
                    }
                }
                LOGGER.info("Create new FoodTreatment : End");

                // Practice
                LOGGER.info("Create new PracticeTreatment : Start");
                final List<PracticePrescriptionModel> pPresModels = prescription.getpPresModels();
                if (null != pPresModels && !pPresModels.isEmpty()) {

                    for(PracticePrescriptionModel practiceModel: pPresModels) {
                        if (practiceModel.getP() <= 0) {
                            continue;
                        }
                        final Practice practice = practiceRepo.findOne(practiceModel.getP());
                        if (null == practice) {
                            LOGGER.info("Practice with name[{}] is not found", null, practiceModel.getP());
                            return false;
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
                LOGGER.info("Create new PracticeTreatment : End");
            }
            LOGGER.info("Create new treatment : End");

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
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public List<MedicinePhaseModel> getMedicines(final int appointmentId, final int diagnostic) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Find phase for diagnostic
            final Phase phase = illnessService.getPhaseSugestion(appointmentId, diagnostic);
            if(null == phase) {
                return Collections.emptyList();
            }
            final List<MedicinePhase> medicinePhases = phase.getMedicinePhaseList();
            if(null != medicinePhases && !medicinePhases.isEmpty()) {
                if (LOGGER.isDebugEnabled()){
                   LOGGER.debug("Got {} records", medicinePhases.size());
                }
                final List<MedicinePhaseModel> listData = new ArrayList<MedicinePhaseModel>();
                for (MedicinePhase medicinePhase: medicinePhases) {
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
}
