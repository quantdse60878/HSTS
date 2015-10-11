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
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.DoctorModel;
import vn.edu.fpt.hsts.bizlogic.model.FoodPrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticePrescriptionModel;
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Doctor;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
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
import vn.edu.fpt.hsts.persistence.repo.PracticeRepo;
import vn.edu.fpt.hsts.persistence.repo.PracticeTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.TreatmentRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public void makePrescription(final PrescriptionModel prescription,
                                 final int appointmentId,
                                 final String appointmentDate) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("prescription[{}], appointmentId[{}], appointmentDate[{}]", prescription, appointmentId, appointmentDate);
            // Find appointment by id
            final Appointment appointment = appointmentRepo.findOne(appointmentId);
            if (null == appointment) {
                LOGGER.error("Appointment with id[{}] is not found", appointmentId);
                throw new BizlogicException("Appointment with id[{}] is not found", null, appointmentId);
            }
            appointment.setStatus(IDbConsts.IAppointmentStatus.FINISHED);
            Date toDate = null;
            if (StringUtils.isNotEmpty(appointmentDate)) {
                toDate = DateUtils.parseDate(appointmentDate, DateUtils.DATE_PATTERN_1);
                if (null == toDate) {
                    throw new BizlogicException("Wrong input date format: {}", null, appointmentDate);
                }
                // Create next appointment
                Appointment nextAppointment = new Appointment();
                nextAppointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
                nextAppointment.setMeetingDate(toDate);
                nextAppointment.setMedicalRecord(appointment.getMedicalRecord());

                appointmentRepo.save(nextAppointment);

                // Set old appointment link to new appointment
                appointment.setNextAppointment(nextAppointment);

            } else {
                // Finish medical record
                final MedicalRecord medicalRecord = appointment.getMedicalRecord();
                medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.FINISHED);
                medicalRecord.setEndTime(new Date());
                medicalRecordRepo.saveAndFlush(medicalRecord);
            }

            appointmentRepo.save(appointment);

            // Set old treatment to DONE
            final Treatment treatment = treatmentRepo.findLastTreatmenByAppointmentId(appointmentId);
            if (null != treatment) {
                treatment.setStatus(IDbConsts.ITreatmentStatus.FINISHED);
                treatment.setToDate(new Date());
                treatmentRepo.save(treatment);
            }
            if (null != prescription) {
                // dianose
                final String diagnostic = prescription.getDiagnostic();
                final Illness illness = illnessRepo.findByName(diagnostic);
                appointment.getMedicalRecord().setIllness(illness);


                // create new treatment
                final Treatment newTreatment = new Treatment();
                newTreatment.setStatus(IDbConsts.ITreatmentStatus.ON_TREATING);
//                newTreatment.setAdviseFood(prescription.getFoodNote());
//                newTreatment.setAdviseMedicine(prescription.getMedicalNote());
//                newTreatment.setAdvisePractice(prescription.getPracticeNote());
                newTreatment.setAppointment(appointment);
                newTreatment.setFromDate(new Date());
                if (null != toDate) {
                    newTreatment.setToDate(toDate);
                }
                newTreatment.setToDate(new Date());
                treatmentRepo.save(newTreatment);

                // TODO implement for medicine, food, practice and multiple row


                // Medicine
                final MedicinePrescriptionModel medicineModel = prescription.getmPresModel();
                if (null != medicineModel) {
                    // Split string
                    final String[] medicineNames = medicineModel.getM().split(",");
                    final String[] mTime = medicineModel.getmTime().split(",");
                    final String[] mQuantity = medicineModel.getmQuantity().split(",");
//                    final String[] mNote = medicineModel.getmNote().split(",");
                    for (int i = 0; i< medicineNames.length; i++) {
                        Medicine medicine = medicineRepo.findByName(medicineNames[i]);
                        if (null == medicine) {
                            throw new BizlogicException("Medicine with name[{}] is not found", null, medicineNames[i]);
                        }
                        MedicineTreatment medicineTreatment = new MedicineTreatment();
                        medicineTreatment.setMedicine(medicine);
                        medicineTreatment.setNumberOfTime(Integer.parseInt(mTime[i]));
                        medicineTreatment.setQuantitative(mQuantity[i]);
                        medicineTreatment.setTreatment(newTreatment);
//                        medicineTreatment.setAdvice(mNote[i]);
                        medicineTreatmentRepo.save(medicineTreatment);
                    }
                }

                // Food
                final FoodPrescriptionModel foodModel = prescription.getfPresModel();
                if(null != foodModel) {
                    // Split string
                    final String[] foodNames = foodModel.getF().split(",");
                    final String[] foodQuantity = foodModel.getfQuantity().split(",");
                    final String[] foodAdvice = foodModel.getfNote().split(",");
                    final String[] fTime = foodModel.getfTime().split(",");
                    for(int i = 0; i< foodNames.length; i++) {
                        Food food = foodRepo.findByName(foodNames[i]);
                        if (null == food) {
                            throw new BizlogicException("Food with name[{}] is not found", null, foodNames[i]);
                        }
                        FoodTreatment foodTreatment = new FoodTreatment();
                        foodTreatment.setFood(food);
                        foodTreatment.setTreatment(newTreatment);
                        foodTreatment.setNumberOfTime(Integer.parseInt(fTime[i]));
                        foodTreatment.setQuantitative(foodQuantity[i]);
                        foodTreatmentRepo.save(foodTreatment);
                    }
                }

                // Practice
                final PracticePrescriptionModel practiceModel = prescription.getpPresModel();
                if (null != practiceModel) {
                    // Split string
                    final String[] practiceNames = practiceModel.getP().split(",");
                    final String[] practiceIntensity = practiceModel.getpIntensity().split(",");
                    final String[] practiceTimes = practiceModel.getpTime().split(",");
                    final String[] practiceNotes = practiceModel.getpNote().split(",");
                    for (int i = 0; i < practiceNames.length; i++) {
                        final Practice practice = practiceRepo.findByName(practiceNames[i]);
                        if (null == practice) {
                            throw new BizlogicException("Practice with name[{}] is not found", null, practiceNames[i]);
                        }
                        PracticeTreatment practiceTreatment = new PracticeTreatment();
                        practiceTreatment.setTreatment(newTreatment);
                        practiceTreatment.setNumberOfTime(Integer.parseInt(practiceTimes[i]));
                        practiceTreatment.setPractice(practice);
                        practiceTreatmentRepo.save(practiceTreatment);
                    }
                }
            }

            // flush all change to db
            appointmentRepo.flush();
            treatmentRepo.flush();
            medicineTreatmentRepo.flush();
            foodTreatmentRepo.flush();
            practiceTreatmentRepo.flush();

        } catch (BizlogicException e) {
            throw e;
        } catch (Exception e) {
            throw new BizlogicException("Error while making new prescription: {}", null, e.getMessage());
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

}
