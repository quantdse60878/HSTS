package vn.edu.fpt.hsts.bizlogic.service;

import net.sf.jasperreports.engine.JRException;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sun.util.resources.CalendarData_sr_Latn_RS;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedPageModel;
import vn.edu.fpt.hsts.bizlogic.model.prescription.MedicineListWraper;
import vn.edu.fpt.hsts.bizlogic.model.prescription.PrescriptionWrapperModel;
import vn.edu.fpt.hsts.bizlogic.model.prescription.PrintingMedicineModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.criteria.PatientCriteria;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Doctor;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Notify;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.Role;
import vn.edu.fpt.hsts.persistence.entity.Treatment;
import vn.edu.fpt.hsts.persistence.repo.AccountRepo;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.DoctorRepo;
import vn.edu.fpt.hsts.persistence.repo.IllnessRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordDataRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.NotifyRepo;
import vn.edu.fpt.hsts.persistence.repo.PatientRepo;
import vn.edu.fpt.hsts.persistence.repo.RoleRepo;
import vn.edu.fpt.hsts.persistence.repo.TreatmentRepo;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Service
public class PatientService extends AbstractService {

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

    /**
     * The {@link MailService}.
     */
    @Autowired
    private MailService mailService;

    /**
     * The {@link NotifyRepo}.
     */
    @Autowired
    private NotifyRepo notifyRepo;

    /**
     * The {@link TreatmentRepo}.
     */
    @Autowired
    private TreatmentRepo treatmentRepo;


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
    public void createPatient(final PatientCriteria criteria) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("criteria[{}]", criteria);

            Date currentDate = new Date();
            currentDate = DateUtils.roundDate(currentDate, false);
            // TODO Create account
            final Account account = new Account();
            String normalizeName = StringUtils.removeAcients(criteria.getPatientName().toLowerCase());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Formatted name: {}", normalizeName);
            }
            final String newUsername = accountService.buildUniqueUsername(normalizeName);
            account.setEmail(criteria.getEmail());
            account.setUsername(newUsername);
            account.setGender(criteria.getGender());
            account.setFullName(criteria.getPatientName());
            final Date birthdate = DateUtils.parseDate(criteria.getBirthday(), DateUtils.DATE_PATTERN_3);
            account.setDateOfBirth(birthdate);
            account.setFullName(criteria.getPatientName());
            account.setUpdateTime(currentDate);
            final Role role = roleRepo.findOne(IDbConsts.IRoleType.PATIENT);
            account.setRole(role);
            // Account have been not actived yet, require change password
            account.setStatus(IDbConsts.IAccountStatus.IN_ACTIVE);
            account.setPassword(authenService.randomPassword());
            account.setUpdateTime(new Date());
            accountRepo.saveAndFlush(account);

            // TODO Create patient
            final Patient patient = new Patient();
            patient.setAccount(account);
            patientRepo.saveAndFlush(patient);

            // TODO create medical record
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.WAITING_FOR_EXAMINATION);

            Doctor doctor = doctorRepo.findOne(criteria.getDoctorId());
            if(null == doctor) {
                throw new BizlogicException("Doctor with username[{}] is not found", null, criteria.getDoctorId());
            }
            medicalRecord.setDoctor(doctor);
            medicalRecord.setPatient(patient);
            medicalRecord.setSymptoms(criteria.getSymptom());
            medicalRecord.setStartTime(currentDate);

            medicalRecord.setMedicalHistory(criteria.getMedicalHistory());
            medicalRecordRepo.saveAndFlush(medicalRecord);

            // TODO Create appointment
            final Appointment appointment = new Appointment();
            appointment.setMedicalRecord(medicalRecord);
            appointment.setWeight(criteria.getWeight());
            appointment.setHeight(criteria.getHeight());
            appointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
            appointment.setMeetingDate(currentDate);
            appointmentRepo.saveAndFlush(appointment);

            // TODO send email with creditial information to patient
//            mailService.sendMail(criteria.getEmail(), "Credential email", account.getPassword());

            // Create notify to doctor
            final Notify notify = new Notify();
            notify.setSender(getCurrentAccount());
            notify.setReceiver(doctor.getAccount());
            notify.setType(IDbConsts.INotifyType.NURSE_DOCTOR);
            notify.setStatus(IDbConsts.INotifyStatus.UNCOMPLETED);
            notify.setMessage(String.valueOf(patient.getId()));
            notifyRepo.saveAndFlush(notify);

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
            currentDate = DateUtils.roundDate(currentDate, false);
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
            final Date appointmentDate = DateUtils.parseDate(nextAppointmentDate, DateUtils.DATE_PATTERN_2);
            if (null == appointmentDate) {
               throw new BizlogicException("Wrong input date data: {}, output: {}", null, nextAppointmentDate, appointmentDate);
            }
            newAppointment.setMeetingDate(appointmentDate);
            newAppointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
            newAppointment.setMedicalRecord(appointment.getMedicalRecord());
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

    public List<Patient> getAllPatients() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return patientRepo.findAll();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }

    }

    public void updatePatient(final PatientCriteria criteria, final boolean isNewMedicalRecord) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("criteria[{}], isNewMedicalRecord[{}]", criteria, isNewMedicalRecord);
            Date currentDate = new Date();
            currentDate = DateUtils.roundDate(currentDate, false);
            if (isNewMedicalRecord) {
                // Patient re-exam after a long time

                // TODO create medical record
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.WAITING_FOR_EXAMINATION);

                Doctor doctor = doctorRepo.findOne(criteria.getDoctorId());
                if(null == doctor) {
                    throw new BizlogicException("Doctor with username[{}] is not found", null, criteria.getDoctorId());
                }
                medicalRecord.setDoctor(doctor);
                Patient patient = patientRepo.findOne(criteria.getId());
                medicalRecord.setPatient(patient);
                medicalRecord.setSymptoms(criteria.getSymptom());
                medicalRecord.setStartTime(currentDate);

                medicalRecord.setMedicalHistory(criteria.getMedicalHistory());
                medicalRecordRepo.saveAndFlush(medicalRecord);

                // TODO Create appointment
                final Appointment appointment = new Appointment();
                appointment.setMedicalRecord(medicalRecord);
                appointment.setWeight(criteria.getWeight());
                appointment.setHeight(criteria.getHeight());
                appointment.setStatus(IDbConsts.IAppointmentStatus.ENTRY);
                appointment.setMeetingDate(currentDate);
                appointmentRepo.saveAndFlush(appointment);

                // Create notify to doctor
                final Notify notify = new Notify();
                notify.setSender(getCurrentAccount());
                notify.setReceiver(doctor.getAccount());
                notify.setType(IDbConsts.INotifyType.NURSE_DOCTOR);
                notify.setStatus(IDbConsts.INotifyStatus.UNCOMPLETED);
                notify.setMessage(String.valueOf(patient.getId()));
                notifyRepo.saveAndFlush(notify);

            } else {
                /**
                 * TODO find last appointment with status ENTRY
                 * note that for case patient re-exam after a long time, system should implement a scheduler to mark all out-of-date appointment
                 */
                final Appointment appointment = appointmentRepo.findLastAppointmentByPatientId(criteria.getId(),
                        IDbConsts.IAppointmentStatus.ENTRY);
                if(null == appointment) {
                    // throw ex
                }
                // TODO update meeting date
                appointment.setMeetingDate(currentDate);
                appointment.setHeight(criteria.getHeight());
                appointment.setWeight(criteria.getWeight());

                final MedicalRecord medicalRecord = appointment.getMedicalRecord();
                if (medicalRecord.getDoctor().getId() != criteria.getDoctorId()) {
                   LOGGER.debug("Nurse assign patient[{}] to new doctor[{}]", criteria.getDoctorId());
                    final Doctor doctor = doctorRepo.findOne(criteria.getDoctorId());
                    medicalRecord.setDoctor(doctor);
                }
                appointmentRepo.saveAndFlush(appointment);
                medicalRecordRepo.saveAndFlush(medicalRecord);

                // Create notify to doctor
                final Notify notify = new Notify();
                notify.setSender(getCurrentAccount());
                final Account doctorAccount = medicalRecord.getDoctor().getAccount();
                notify.setReceiver(doctorAccount);
                notify.setType(IDbConsts.INotifyType.NURSE_DOCTOR);
                notify.setStatus(IDbConsts.INotifyStatus.UNCOMPLETED);
                final int patientId = medicalRecord.getPatient().getId();
                notify.setMessage(String.valueOf(patientId));
                notifyRepo.saveAndFlush(notify);
            }

        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PatientExtendedPageModel findPatients(final String name, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("name[{}], page[{}], pageSize[{}]", name, page, pageSize);
            Page<Patient> pageEntitties = null;
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            if (StringUtils.isNotEmpty(name)) {
                String searchName = "%" + name + "%";
                pageEntitties = patientRepo.findByNameLike(searchName, pageRequest);
            } else {
                pageEntitties = patientRepo.findAll(pageRequest);
            }
            PatientExtendedPageModel pageModel = new PatientExtendedPageModel(pageEntitties);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public void print(final int patientId, final HttpServletResponse response) throws BizlogicException, IOException, JRException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Find last prescription with patient id
            Patient patient = patientRepo.findOne(patientId);
            if (null == patient) {
                throw new BizlogicException("Patient with id[{}] is not found", null, patientId);
            }

            final Treatment lastTreatment = treatmentRepo.findLastTreatmenByPatientId(patientId, IDbConsts.ITreatmentStatus.ON_TREATING);
            if(null == lastTreatment) {
                throw new BizlogicException("Last treatment with patientId[{}] is not found", null, patientId);
            }
            final List<MedicineTreatment> medicineTreatments = lastTreatment.getMedicineTreatmentList();
            List<PrintingMedicineModel> listMedicine = new ArrayList<PrintingMedicineModel>();

            DateTime dt1 = new DateTime(lastTreatment.getFromDate());
            DateTime dt2 = new DateTime(lastTreatment.getToDate());

            int numberOfDay = Days.daysBetween(dt1, dt2).getDays();
            if (null != medicineTreatments) {
                int count = 1;
                for (MedicineTreatment mt: medicineTreatments) {
                    PrintingMedicineModel model = new PrintingMedicineModel();
                    model.setNumber(String.valueOf(count++));
                    model.setMedicineName(mt.getMedicine().getName());
                    int qty = numberOfDay * mt.getNumberOfTime() * mt.getQuantitative();
                    model.setQty(String.valueOf(qty));
                    model.setUnit(mt.getUnit());
                    String usage = "Ngày uống " + mt.getNumberOfTime() + " lần, mỗi lần " + mt.getQuantitative() + " " + mt.getUnit();
                    model.setUsage(usage);
                    listMedicine.add(model);
                }
            }
            final MedicineListWraper wraper = new MedicineListWraper();
            wraper.setItems(listMedicine);

            final PrescriptionWrapperModel prescription = new PrescriptionWrapperModel();
            prescription.setPatientName(patient.getAccount().getFullName());
            prescription.setBirthday(DateUtils.formatDate(patient.getAccount().getDateOfBirth(), DateUtils.DATE_PATTERN_3));
            String diagnose = lastTreatment.getAppointment().getMedicalRecord().getIllness().getDescription();
            prescription.setDiagnose(diagnose);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            final String address = "TP. Hồ Chí Minh ngày " + calendar.get(Calendar.DATE) + " tháng " + calendar.get(Calendar.MONTH) + " năm " + calendar.get(Calendar.YEAR);
            prescription.setAddress(address);
            // TODO
            prescription.setAdvice(lastTreatment.getAdviseMedicine());
            final String appointmentDate = DateUtils.formatDate(lastTreatment.getAppointment().getNextAppointment().getMeetingDate(), DateUtils.DATE_PATTERN_3);
            prescription.setAppointmentDate(appointmentDate);
            prescription.setDoctorName(lastTreatment.getAppointment().getMedicalRecord().getDoctor().getAccount().getFullName());
            prescription.setTableData(wraper);

            final OutputStream out = response.getOutputStream();
            prescription.toPdf(out);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
