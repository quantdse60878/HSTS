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
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.hsts.bizlogic.model.FileUploadModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedModel;
import vn.edu.fpt.hsts.bizlogic.model.PatientExtendedPageModel;
import vn.edu.fpt.hsts.bizlogic.model.prescription.MedicineListWraper;
import vn.edu.fpt.hsts.bizlogic.model.prescription.PrescriptionWrapperModel;
import vn.edu.fpt.hsts.bizlogic.model.prescription.PrintingMedicineModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.criteria.CheckCriteria;
import vn.edu.fpt.hsts.criteria.PatientCriteria;
import vn.edu.fpt.hsts.criteria.RegistrationCriteria;
import vn.edu.fpt.hsts.criteria.SearchCriteria;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Doctor;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Notify;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;
import vn.edu.fpt.hsts.persistence.entity.Treatment;
import vn.edu.fpt.hsts.persistence.repo.AccountRepo;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.DoctorRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordDataRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.NotifyRepo;
import vn.edu.fpt.hsts.persistence.repo.PatientRepo;
import vn.edu.fpt.hsts.persistence.repo.PreventionCheckRepo;
import vn.edu.fpt.hsts.persistence.repo.TreatmentRepo;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;

    /**
     * The {@link PreventionCheckRepo}.
     */
    @Autowired
    private PreventionCheckRepo preventionCheckRepo;

    /**
     * The {@link MedicineRepo}.
     */
    @Autowired
    private MedicineRepo medicineRepo;

    /**
     * The {@link MedicineTreatmentRepo}.
     */
    @Autowired
    private MedicineTreatmentRepo medicineTreatmentRepo;

    /**
     * The {@link BarcodeService}.
     */
    @Autowired
    private BarcodeService barcodeService;

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
    public Patient register(final int patientId, final SearchCriteria ... criterias) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            /**
             * TODO reinput log, process for old medicine
             */
            if (null == criterias) {
                return null;
            } else {
                LOGGER.info("Criteria size = {}", criterias.length);
            }
            Date currentDate = new Date();
            currentDate = DateUtils.roundDate(currentDate, false);
            // Identity patient
            Patient patient;
            if (0 < patientId) {
                // Existence patient
                patient = patientRepo.findOne(patientId);
                if (null == patient) {
                    throw new BizlogicException("Patient with id[{}] is not found", null, patientId);
                }
                // Set all old medical record to FINISH
                final List<MedicalRecord> medicalRecordList = patient.getMedicalRecords();
                if (null != medicalRecordList && !medicalRecordList.isEmpty()) {
                    for (MedicalRecord medicalRecord: medicalRecordList) {
                        if(medicalRecord.getStatus() != IDbConsts.IMedicalRecordStatus.NO_ILLNESS) {
                            medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.FINISHED);
                            medicalRecordRepo.saveAndFlush(medicalRecord);
                        }
                    }
                }
            } else {
                // Register new
                patient = new Patient();
            }
            MedicalRecord medicalRecord = new MedicalRecord();
            String medicineHistories = null;
            for (SearchCriteria criteria : criterias) {
                if (criteria instanceof PatientCriteria) {
                    // TODO
                    // casting
                    final PatientCriteria patientCriteria = (PatientCriteria) criteria;
                    final Account newAccount = accountService.initPatientAccount(patientCriteria, currentDate);
                    accountRepo.saveAndFlush(newAccount);

                    // Save patient
                    patient.setAccount(newAccount);
                    patientRepo.saveAndFlush(patient);

                    // Generate new barcode for patient
                    final String barcode = barcodeService.getPatientBarcode(patient.getId());
                    patient.setBarcode(barcode);
                    patientRepo.save(patient);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Create new patient[{}] successfully", newAccount.getUsername());
                    }

                    //  send email with creditial information to patient
                    mailService.pushMail(newAccount);

                } else if (criteria instanceof RegistrationCriteria) {
                    final RegistrationCriteria rCriteria = (RegistrationCriteria) criteria;

                    medicalRecord.setStatus(IDbConsts.IMedicalRecordStatus.WAITING_FOR_EXAMINATION);
                    Doctor doctor = doctorRepo.findOne(rCriteria.getDoctorId());
                    if (null == doctor) {
                        throw new BizlogicException("Doctor with username[{}] is not found", null, rCriteria.getDoctorId());
                    }
                    medicalRecord.setDoctor(doctor);
                    medicalRecord.setPatient(patient);
                    medicalRecord.setSymptoms(rCriteria.getSymptom());
                    medicalRecord.setStartTime(currentDate);

                    medicalRecord.setMedicalHistory(rCriteria.getMedicalHistory());
                    medicalRecordRepo.saveAndFlush(medicalRecord);

                    // Process medicine history
                    medicineHistories = rCriteria.getMedicineHistory();

                } else if (criteria instanceof CheckCriteria) {
                    final CheckCriteria pcCriteria = (CheckCriteria) criteria;
                    // TODO Create appointment
                    final Appointment appointment = new Appointment();
                    appointment.setMedicalRecord(medicalRecord);
                    appointment.setStatus(IDbConsts.IAppointmentStatus.WATTING);
                    appointment.setMeetingDate(currentDate);

                    appointmentRepo.saveAndFlush(appointment);

                    // Create prevention checking

                    PreventionCheck preventionCheck = new PreventionCheck();
                    preventionCheck.setPhaseAngle(pcCriteria.getPhaseAngle());
                    preventionCheck.setAppointment(appointment);
                    preventionCheck.setBasalMetabolicRate(pcCriteria.getBasalMetabolicRate());
                    preventionCheck.setBmi(pcCriteria.getBmi());
                    preventionCheck.setBodyFat(pcCriteria.getBodyFat());
                    preventionCheck.setBodyWater(pcCriteria.getBodyWater());
                    preventionCheck.setHeight(pcCriteria.getHeight());
                    preventionCheck.setWeight(pcCriteria.getWeight());
                    preventionCheck.setMuscleMass(pcCriteria.getMuscleMass());
                    preventionCheck.setVisceralFat(pcCriteria.getVisceralFat());
                    preventionCheck.setImpedance(pcCriteria.getImpedance());
                    preventionCheck.setBloodPressure(pcCriteria.getBloodPressure());
                    preventionCheck.setHeartBeat(pcCriteria.getHeartBeat());
                    preventionCheck.setWaists(pcCriteria.getWaists());
                    preventionCheckRepo.saveAndFlush(preventionCheck);

                    // Process medicine history
                    if (null != medicineHistories) {
                        final String[] tmp = medicineHistories.split(",");
                        if (null != tmp && tmp.length > 0) {
                            final Treatment oldTreatment = new Treatment();
                            oldTreatment.setFromDate(new Date());
                            oldTreatment.setToDate(new Date());
                            oldTreatment.setStatus(IDbConsts.ITreatmentStatus.HISTORY);
                            oldTreatment.setAppointment(appointment);
                            treatmentRepo.saveAndFlush(oldTreatment);
                            for (String oldMedicine: tmp) {
                                final MedicineTreatment mt = new MedicineTreatment();
                                mt.setTreatment(oldTreatment);
                                Medicine m = medicineRepo.findByName(oldMedicine);
                                if(null == m) {
                                    m = new Medicine();
                                    m.setName(oldMedicine);
                                    m.setUnit("");
                                    medicineRepo.saveAndFlush(m);
                                }
                                mt.setMedicine(m);
                                medicineTreatmentRepo.saveAndFlush(mt);
                            }
                        }
                    }
                }
            }

            // Create notify to doctor
            final Notify notify = new Notify();
            notify.setSender(getCurrentAccount());
            notify.setReceiver(medicalRecord.getDoctor().getAccount());
            notify.setType(IDbConsts.INotifyType.NURSE_DOCTOR);
            notify.setStatus(IDbConsts.INotifyStatus.UNCOMPLETED);
            notify.setMessage(String.valueOf(patient.getId()));
            notifyRepo.saveAndFlush(notify);
            return patient;
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

    @Transactional(rollbackOn = BizlogicException.class)
    public Patient updatePatient(final int patientId, final RegistrationCriteria registrationCriteria, final CheckCriteria checkCriteria) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            Date currentDate = new Date();
            currentDate = DateUtils.roundDate(currentDate, false);
            final Patient patient = patientRepo.findOne(patientId);
            if (null == patient) {
                throw new BizlogicException("Patient with id[{}] is not found", null, patientId);
            }
            /**
             * TODO find last appointment with status WATTING or ENTRY
             * note that for case patient re-exam after a long time, system should implement a scheduler to mark all out-of-date appointment
             */
            final byte[] statuses = {IDbConsts.IAppointmentStatus.WATTING, IDbConsts.IAppointmentStatus.ENTRY};
            final List<Appointment> entryAppointmentList = appointmentRepo.findLastAppointmentByPatientId(patient.getId(),
                    statuses);
            if(null == entryAppointmentList || entryAppointmentList.isEmpty()) {
                /*
                *   TODO Throw ex, but should check business logic at higher level before
                *   Make sure that only 1 appointment has status ENTRY at a time
                */
                return null;
            } else {
                for (Appointment appointment: entryAppointmentList) {
                    appointment.setStatus(IDbConsts.IAppointmentStatus.FINISHED);
                    appointmentRepo.save(appointment);
                }
            }
            Appointment appointment = entryAppointmentList.get(0);
            appointment.setStatus(IDbConsts.IAppointmentStatus.WATTING);
            appointment.setMeetingDate(currentDate);

            final MedicalRecord medicalRecord = appointment.getMedicalRecord();
            if (medicalRecord.getDoctor().getId() != registrationCriteria.getDoctorId()) {
               LOGGER.debug("Nurse assign patient[{}] to new doctor[{}]", registrationCriteria.getDoctorId());
                final Doctor doctor = doctorRepo.findOne(registrationCriteria.getDoctorId());
                medicalRecord.setDoctor(doctor);
                medicalRecord.setMedicalHistory(registrationCriteria.getMedicalHistory());
                medicalRecord.setSymptoms(registrationCriteria.getSymptom());
                medicalRecordRepo.save(medicalRecord);
            }

            // Create prevention checking

            PreventionCheck preventionCheck = new PreventionCheck();
            preventionCheck.setPhaseAngle(checkCriteria.getPhaseAngle());
            preventionCheck.setAppointment(appointment);
            preventionCheck.setBasalMetabolicRate(checkCriteria.getBasalMetabolicRate());
            preventionCheck.setBmi(checkCriteria.getBmi());
            preventionCheck.setBodyFat(checkCriteria.getBodyFat());
            preventionCheck.setBodyWater(checkCriteria.getBodyWater());
            preventionCheck.setHeight(checkCriteria.getHeight());
            preventionCheck.setWeight(checkCriteria.getWeight());
            preventionCheck.setMuscleMass(checkCriteria.getMuscleMass());
            preventionCheck.setVisceralFat(checkCriteria.getVisceralFat());
            preventionCheck.setImpedance(checkCriteria.getImpedance());
            preventionCheck.setHeartBeat(checkCriteria.getHeartBeat());
            preventionCheck.setWaists(checkCriteria.getWaists());
            preventionCheck.setBloodPressure(checkCriteria.getBloodPressure());
            preventionCheckRepo.saveAndFlush(preventionCheck);

            appointmentRepo.saveAndFlush(appointment);
            medicalRecordRepo.saveAndFlush(medicalRecord);

            // Create notify to doctor
            final Notify notify = new Notify();
            notify.setSender(getCurrentAccount());
            final Account doctorAccount = medicalRecord.getDoctor().getAccount();
            notify.setReceiver(doctorAccount);
            notify.setType(IDbConsts.INotifyType.NURSE_DOCTOR);
            notify.setStatus(IDbConsts.INotifyStatus.UNCOMPLETED);
            notify.setMessage(String.valueOf(patientId));
            notifyRepo.saveAndFlush(notify);

            return patient;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PatientExtendedPageModel findPatients(final String keyword, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("keyword[{}], page[{}], pageSize[{}]", keyword, page, pageSize);
            Page<Patient> pageEntitties = null;
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            if (StringUtils.isNotEmpty(keyword)) {
                String searchName = "%" + keyword + "%";
                pageEntitties = patientRepo.findByNameOrBarcodeLike(searchName, pageRequest);
            } else {
                pageEntitties = patientRepo.findAll(pageRequest);
            }
            PatientExtendedPageModel pageModel = new PatientExtendedPageModel(pageEntitties);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public byte[] print(final int patientId) throws BizlogicException, IOException, JRException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Find last prescription with patient id
            Patient patient = patientRepo.findOne(patientId);
            if (null == patient) {
                throw new BizlogicException("Patient with id[{}] is not found", null, patientId);
            }

            final List<Treatment> lastTreatmentList = treatmentRepo.findLastTreatmenByPatientId(patientId, IDbConsts.ITreatmentStatus.ON_TREATING);
            if(CollectionUtils.isEmpty(lastTreatmentList)) {
                return null;
            }
            final Treatment lastTreatment = lastTreatmentList.get(0);
            final List<MedicineTreatment> medicineTreatments = lastTreatment.getMedicineTreatmentList();
            List<PrintingMedicineModel> listMedicine = new ArrayList<PrintingMedicineModel>();

            DateTime dt1 = new DateTime(lastTreatment.getFromDate());
            DateTime dt2 = new DateTime(lastTreatment.getToDate());

            int numberOfDay = Days.daysBetween(dt1, dt2).getDays();
            String advice = "";
            if (null != medicineTreatments) {
                int count = 1;
                for (MedicineTreatment mt: medicineTreatments) {
                    PrintingMedicineModel model = new PrintingMedicineModel();
                    model.setNumber(String.valueOf(count++));
                    model.setMedicineName(mt.getMedicine().getName());
                    int qty = numberOfDay * mt.getNumberOfTime() * mt.getQuantitative();
                    model.setQty(String.valueOf(qty));
                    model.setUnit(mt.getMedicine().getUnit()) ;
                    String usage = "Ngày uống " + mt.getNumberOfTime() + " lần, mỗi lần " + mt.getQuantitative() + " " + mt.getMedicine().getUnit();
                    model.setUsage(usage);
                    listMedicine.add(model);

                    // Set advice
                    advice += "- " + model.getMedicineName() + ": " + mt.getAdvice() + "\n";
                }
            }
            LOGGER.info("Size: " + listMedicine.size());
            final MedicineListWraper wraper = new MedicineListWraper();
            wraper.setItems(listMedicine);

            final PrescriptionWrapperModel prescription = new PrescriptionWrapperModel();
            prescription.setPatientName(patient.getAccount().getFullName());
            prescription.setBirthday(DateUtils.formatDate(patient.getAccount().getDateOfBirth(), DateUtils.DATE_PATTERN_3));
            String diagnose = lastTreatment.getAppointment().getMedicalRecord().getIllness().getDescription();
            if (StringUtils.isEmpty(diagnose)) {
                diagnose = lastTreatment.getAppointment().getMedicalRecord().getIllness().getName();
            }
            prescription.setDiagnose(diagnose);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            final String address = "TP. Hồ Chí Minh ngày " + calendar.get(Calendar.DATE) + " tháng " + calendar.get(Calendar.MONTH) + " năm " + calendar.get(Calendar.YEAR);
            prescription.setAddress(address);
            if (StringUtils.isEmpty(advice)) {
                advice = "N/A";
            }
            prescription.setAdvice(advice);
            final String appointmentDate = DateUtils.formatDate(lastTreatment.getAppointment().getNextAppointment().getMeetingDate(), DateUtils.DATE_PATTERN_3);
            prescription.setAppointmentDate(appointmentDate);
            prescription.setDoctorName(lastTreatment.getAppointment().getMedicalRecord().getDoctor().getAccount().getFullName());
            prescription.setTableData(wraper);

            return prescription.toPdf();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public Patient findPatient(final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            return patientRepo.findOne(patientId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public boolean checkPrescription(final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Find last prescription with patient id
            Patient patient = patientRepo.findOne(patientId);
            if (null == patient) {
                return false;
            }

            final List<Treatment> lastTreatment = treatmentRepo.findLastTreatmenByPatientId(patientId, IDbConsts.ITreatmentStatus.ON_TREATING);
            if(CollectionUtils.isEmpty(lastTreatment)) {
                return false;
            }
            final long countMedicines = medicineTreatmentRepo.countByTreatmentId(lastTreatment.get(0).getId());
            if (0 >= countMedicines) {
                return false;
            }
            return true;
        } catch (Exception e) {
            // All error -> false
            return false;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PatientExtendedModel findPatientByBarcode(final String barcode) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("barcode[{}]", barcode);
            Patient patient = patientRepo.findPatientByBarcode(barcode);
            if(null == patient) {
                return null;
            }
            PatientExtendedModel model = new PatientExtendedModel();
            model.fromEntity(patient);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public FileUploadModel saveMedicalImage(final MultipartFile file){
        LOGGER.info(IConsts.BEGIN_METHOD);
        FileOutputStream os = null;
        try {
            LOGGER.info("fileName[{}]", file.getOriginalFilename());

            // Format file name to prevent duplicate name
            String fileName = file.getOriginalFilename();
            final int index = fileName.lastIndexOf(".");
            // Add random char
            String surfix = StringUtils.randomString(6);
            if (index > 0) {
                String prefix = fileName.substring(0, index);
                if (prefix.contains(".")) {
                    prefix = prefix.replace(".", "_");
                }
                if (prefix.contains(",")) {
                    prefix = prefix.replace(",", "_");
                }
                if (prefix.contains("/")) {
                    prefix = prefix.replace("/", "_");
                }
                if (prefix.contains("\\")) {
                    prefix = prefix.replace("\\", "_");
                }
                String postfix = fileName.substring(index);
                fileName = String.format("%s_%s%s", prefix, surfix, postfix);
            } else {
                fileName = String.format("%s_%s", fileName, surfix);
            }

            // Save file to folder
            final File folder = new File(getUploadDirectory());
            if (!folder.exists()) {
                folder.mkdir();
            }
            final String filePath = getUploadDirectory() + File.separator + fileName;
            // {img}ten anh{img},sasas, {img}anh2{img}
            final File newFile = new File(filePath);
            if(!newFile.exists()) {
                newFile.createNewFile();
            }
            os = new FileOutputStream(newFile);
            os.write(file.getBytes());

            final FileUploadModel model = new FileUploadModel();
            final String formatFileName = String.format("{img}%s{img}", fileName);
            model.setFileName(formatFileName);
            model.setFilePath(formatFileName);
            model.setResult(true);
            return model;

        } catch (Exception e) {
            e.printStackTrace();
//            LOGGER.error("Error while uploading new file[{}]", file.getOriginalFilename());
            return new FileUploadModel(false);
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                LOGGER.error("Error while uploading new file[{}]", file.getOriginalFilename());
                return new FileUploadModel(false);
            }
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public String getOldMedicalHistory(final int patientId){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            final List<String> medicalHistories = medicalRecordRepo.findMedicalHistoryByPatientId(patientId);
            if (!CollectionUtils.isEmpty(medicalHistories)) {
                final Set<String> dataSet = new HashSet<String>();
                for (String m: medicalHistories) {
                    final List<String> spliters = Arrays.asList(m.split(","));
                    dataSet.addAll(spliters);
                }

                // return
                final StringBuilder sb = new StringBuilder();
                for(String str: dataSet) {
                    if (StringUtils.isNotEmpty(str)) {
                       sb.append(str).append(",");
                    }
                }
                return sb.toString();
            }
            return "";
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public String getOldSymtoms(final int patientId){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            final PageRequest pageRequest = new PageRequest(0, 1);
            final List<MedicalRecord> medicalRecordList = medicalRecordRepo.findByPatientId(patientId, pageRequest);
            if (!CollectionUtils.isEmpty(medicalRecordList)) {
                final Illness illness = medicalRecordList.get(0).getIllness();
                if (null != illness) {
                    String result = illness.getName();
                    if (StringUtils.isNotEmpty(illness.getDescription())) {
                        result = illness.getDescription();
                    }
                    return result;
                }
            }
            return "";
        } catch (Exception e) {
            return "";
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public List<String> getPatientHistoryImage(final String medicalHistory) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final String[] tmp = medicalHistory.split(",");
            if (null != tmp && tmp.length > 0) {
                final List<String> results = new ArrayList<String>();
                for (String str: tmp) {
                    if (str.startsWith("{img}")) {
                        str = str.replace("{img}", "");
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("original file name: {}", str);
                        }
                        results.add(str);
                    }
                }
                return results;
            }
            return Collections.emptyList();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public byte[] getUploadedHistoryImg(final String fileName){
        LOGGER.info(IConsts.BEGIN_METHOD);
        FileInputStream fis = null;
        try {
            LOGGER.info("fileName[{}]", fileName);
            final File file = new File(getUploadDirectory() + File.separator + fileName);
            if (null != file && file.exists() && file.canRead()) {
                byte[] result = new byte[(int) file.length()];
                fis = new FileInputStream(file);
                fis.read(result);
                return result;
            }
            return new byte[0];
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
