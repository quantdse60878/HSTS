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
import vn.edu.fpt.hsts.bizlogic.model.PrescriptionModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Doctor;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.DoctorRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineTreatmentRepo;
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

//    @Transactional(rollbackOn = BizlogicException.class)
//    public void makePrescription(final PrescriptionModel prescription,
//                                 final int appointmentId,
//                                 final String appointmentDate) throws BizlogicException {
//        LOGGER.info(IConsts.BEGIN_METHOD);
//        try {
//            LOGGER.info("prescription[{}], appointmentId[{}], appointmentDate[{}]", prescription, appointmentId, appointmentDate);
//            // Find appointment by id
//            final Appointment appointment = appointmentRepo.findOne(appointmentId);
//            if (null == appointment) {
//                LOGGER.error("Appointment with id[{}] is not found", appointmentId);
//                throw new BizlogicException("Appointment with id[{}] is not found", null, appointmentId);
//            }
//            appointment.setStatus(IDbConsts.IAppointmentStatus.FINISHED);
//            Date toDate = null;
//            if (StringUtils.isNotEmpty(appointmentDate)) {
//                toDate = DateUtils.parseDate(appointmentDate, DateUtils.DATE_PATTERN_1);
//                if (null == toDate) {
//                    throw new BizlogicException("Wrong input date format: {}", null, appointmentDate);
//                }
//                // Create next appointment
//                Appointment nextAppointment = new Appointment();
//                nextAppointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
//                nextAppointment.setMeetingDate(toDate);
//                nextAppointment.setMedicalRecord(appointment.getMedicalRecord());
//
//                appointmentRepo.save(nextAppointment);
//
//                // Set old appointment link to new appointment
//                appointment.setNextAppointment(nextAppointment);
//
//            } else {
//                // Finish medical record
//                final MedicalRecord medicalRecord = appointment.getMedicalRecord();
//                medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.FINISHED);
//                medicalRecord.setEndTime(new Date());
//                medicalRecordRepo.saveAndFlush(medicalRecord);
//            }
//
//            appointmentRepo.save(appointment);
//
//            // Set old treatment to DONE
//            final Treatment treatment = treatmentRepo.findLastTreatmenByAppointmentId(appointmentId);
//            if (null != treatment) {
//                treatment.setStatus(IDbConsts.ITreatmentStatus.FINISHED);
//                treatmentRepo.save(treatment);
//            }
//            if (null != prescription) {
//                // create new treatment
//                final Treatment newTreatment = new Treatment();
//                newTreatment.setStatus(IDbConsts.ITreatmentStatus.ON_TREATING);
////                newTreatment.setAdviseFood(prescription.getFoodNote());
////                newTreatment.setAdviseMedicine(prescription.getMedicalNote());
////                newTreatment.setAdvisePractice(prescription.getPracticeNote());
//                newTreatment.setAppointment(appointment);
//                newTreatment.setFromDate(new Date());
////                if (null != toDate) {
////                    newTreatment.setToDate(toDate);
////                }
//                newTreatment.setToDate(new Date());
//                treatmentRepo.save(newTreatment);
//
//                // TODO implement for medicine, food, practice and multiple row
//                Medicine medicine = medicineRepo.findByName(prescription.getMedical());
//                if (null == medicine) {
//                    LOGGER.error("Medicine[{}] is not found", prescription.getMedical());
//                    throw new BizlogicException("Medicine[{}] with name is not found", null, prescription.getMedical());
//                }
//                String medicineTime = prescription.getMedicalTime();
//                if (StringUtils.isEmpty(medicineTime)) {
//                    LOGGER.error("MedicineTime[{}] is not wrong input", prescription.getMedicalTime());
//                    throw new BizlogicException("MedicineTime[{}] is wrong format", null, prescription.getMedicalTime());
//                }
//                String[] parts = medicineTime.split(",");
//                for (String part : parts) {
//                    // Save medicine time
//                    // Save detail
//                    MedicineTreatment medicineTreatment = new MedicineTreatment();
//                    medicineTreatment.setTreatment(newTreatment);
////                    medicineTreatment.setNumberOfMedicine(Integer.parseInt(prescription.getMedicalQuantity()));
//                    medicineTreatment.setAdvice(null);
//                    medicineTreatment.setMedicine(medicine);
//                    medicineTreatmentRepo.saveAndFlush(medicineTreatment);
//                }
//            }
//            // flush all change to db
//            appointmentRepo.flush();
//            treatmentRepo.flush();
//
//            //new appoitment form appointmentDate, appointmentDate can be null.
//        } catch (BizlogicException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new BizlogicException("Error while making new prescription: {}", null, e.getMessage());
//        }finally {
//            LOGGER.info(IConsts.END_METHOD);
//        }
//    }
}
