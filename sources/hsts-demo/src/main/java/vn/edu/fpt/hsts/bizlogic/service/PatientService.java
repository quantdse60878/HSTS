package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Doctor;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.Role;
import vn.edu.fpt.hsts.persistence.repo.AccountRepo;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.DoctorRepo;
import vn.edu.fpt.hsts.persistence.repo.IllnessRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordDataRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.PatientRepo;
import vn.edu.fpt.hsts.persistence.repo.RoleRepo;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Service
public class PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    /**
     * The {@link AccountRepo}.
     */
    @Autowired
    private AccountRepo accountRepo;

    /**
     * The {@link PatientRepo}
     */
    @Autowired
    private PatientRepo patientRepo;

    /**
     * The {@link MedicalRecordRepo}
     */
    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    /**
     * The {@link MedicalRecordDataRepo}.
     */
    @Autowired
    private MedicalRecordDataRepo medicalRecordDataRepo;

    /**
     * The {@link RoleRepo}.
     */
    @Autowired
    private RoleRepo roleRepo;

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;

    /**
     * The {@link AuthenService}.
     */
    @Autowired
    private AuthenService authenService;

    /**
     * The {@link DoctorRepo}.
     */
    @Autowired
    private DoctorRepo doctorRepo;

    /**
     * The {@link IllnessRepo}.
     */
    @Autowired
    private IllnessRepo illnessRepo;

    /**
     * The {@link AppointmentRepo}.
     */
    @Autowired
    private AppointmentRepo appointmentRepo;

    public Patient getPatient(final int accountId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        Patient patient = new Patient();
        final List<Patient> listPatient = patientRepo.findAll();
        for(int i = 0; i < listPatient.size(); i++) {
            if(listPatient.get(i).getAccount().getId() == accountId) {
                patient = listPatient.get(i);
                break;
            }
        }
        LOGGER.info(IConsts.END_METHOD);
        if(patient != null) {
            return patient;
        } else return null;
    }

    public Patient getPatientByID(final int patientID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        Patient patient = new Patient();
        final List<Patient> listPatient = patientRepo.findAll();
        for(int i = 0; i < listPatient.size(); i++) {
            if(listPatient.get(i).getId() == patientID) {
                patient = listPatient.get(i);
                break;
            }
        }
        LOGGER.info(IConsts.END_METHOD);
        if(patient != null) {
            return patient;
        } else return null;
    }


    @Transactional(rollbackOn = BizlogicException.class)
    public void createPatient() throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            Date currentDate = new Date();
            currentDate = DateUtils.formatDate(currentDate, false);
            // TODO Create account
            final Account account = new Account();
            final String newUsername = accountService.buildUniqueUsername("Man Huynh Khuong");
            account.setEmail("khuongmh@gmail.com");
            account.setUsername(newUsername);
            account.setGender(IDbConsts.IAccountGender.MALE);
            account.setDateOfBirth(new Date());
            account.setFullname("Man Huynh Khuong");
            account.setUpdateTime(currentDate);
            final Role role = roleRepo.findOne(IDbConsts.IRoleType.PATIENT);
            account.setRole(role);
            // Account have been not actived yet, require change password
            account.setStatus(IDbConsts.IAccountStatus.IN_ACTIVE);
            account.setPassword(authenService.randomPassword());
            accountRepo.saveAndFlush(account);

            // TODO Create patient
            final Patient patient = new Patient();
            patient.setAccount(account);
            patientRepo.saveAndFlush(patient);

            // TODO create medical record
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.WAITING_FOR_EXAMINATION);

            Doctor doctor = doctorRepo.findByUsername("bacsi");
            if(null == doctor) {
                throw new BizlogicException("Doctor with username[bacsi] is not found");
            }
            medicalRecord.setDoctor(doctor);
            medicalRecord.setPatient(patient);
            medicalRecord.setSymptoms("Béo quá độ");
            medicalRecord.setStartTime(currentDate);

            medicalRecord.setMedicalHistory("Medical history");
            medicalRecordRepo.saveAndFlush(medicalRecord);

            // TODO Create appointment
            final Appointment appointment = new Appointment();
            appointment.setMedicalRecord(medicalRecord);
            appointment.setWeight(100);
            appointment.setHeight(170);
            appointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
            appointment.setMeetingDate(currentDate);
            appointmentRepo.saveAndFlush(appointment);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Create new patient[{}] successfully", account.getUsername());
            }
        } catch (BizlogicException be) {
            throw be;
        } catch (Exception e) {
            LOGGER.error("Error while create new patient: {}", e.getMessage());
            throw new BizlogicException(e.getMessage());
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public List<Patient> getPatientByApponitmentDate() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            Date currentDate = new Date();
            currentDate = DateUtils.formatDate(currentDate, false);
            LOGGER.info("currentDate[{}]", currentDate);
            final List<Patient> patients = patientRepo.findByAppoinmentDate(currentDate);
            if (null != patients && patients.isEmpty()) {
                if (LOGGER.isDebugEnabled()) {
                   LOGGER.debug("Got {} records", patients.size());
                }
            }
            return patients;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void makeAppointment(final int medicalRecordId, final String nextAppointmentDate) throws BizlogicException {
        //TODO parse INT recordID.
        //TODO find appointmentDate from recordID with appointmentDateChild = null
        //TODO new appointmentDate record from appointmentDate
        //TODO set appointmentDateChild with new appointmentDate record

        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("medicalRecordId[{}], nextAppointmentDate[{}]", medicalRecordId, nextAppointmentDate);

            // Find last appointment
            final Appointment appointment = appointmentRepo.findLastAppointmentByMedicalRecordId(medicalRecordId);
            if (null == appointment) {
                throw new BizlogicException("Appointment with medicalRecordId[{}] not found", null, medicalRecordId);
            } else if (appointment.getStatus() == IDbConsts.IAppointmentStatus.ENTRY) {
                throw new BizlogicException("Appointment[{}] has been finished", null, appointment.getId());
            }
            // Create new appointment -> set to old appointment
            final Appointment newAppointment = new Appointment();
            final Date appointmentDate = DateUtils.parseDate(nextAppointmentDate, DateUtils.DATE_PATTERN_1);
            if (null == appointmentDate) {
               throw new BizlogicException("Wrong input date data: {}, output: {}", null, nextAppointmentDate, appointmentDate);
            }
            newAppointment.setMeetingDate(appointmentDate);
            newAppointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
            newAppointment.setMedicalRecord(appointment.getMedicalRecord());
            newAppointment.setMesssage("ABCDEREFFDF");
            appointmentRepo.save(newAppointment);

            // Finish old appointment, link to next appointment
            appointment.setNextAppointment(newAppointment);
            appointment.setStatus(IDbConsts.IAppointmentStatus.FINISHED);
            appointmentRepo.save(appointment);

            // Flush data
            appointmentRepo.flush();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Create new appointment with medicalRecordId[{}] successfully", medicalRecordId);
            }
        } catch (BizlogicException be) {
            throw be;
        } catch (Exception e) {
            LOGGER.error("Error while make new appointment: {}", e.getMessage());
            throw new BizlogicException(e.getMessage());
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }


    }
}
