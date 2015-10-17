package vn.edu.fpt.hsts.bizlogic.service;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.IllnessRepo;
import vn.edu.fpt.hsts.persistence.repo.PhaseRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Aking on 10/9/2015.
 */
@Service
public class IllnessService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IllnessService.class);

    /**
     * The {@link IllnessRepo}.
     */
    @Autowired
    private IllnessRepo illnessRepo;

    /**
     * The {@link PhaseRepo}.
     */
    @Autowired
    private PhaseRepo phaseRepo;

    /**
     * The {@link AppointmentRepo}.
     */
    @Autowired
    private AppointmentRepo appointmentRepo;


    /**
     * The default clinical symptoms.
     */
    @Value("${hsts.default.clinical.symptoms}")
    private String defaultSymptoms;

    /**
     * Find all illness.
     * @return {@link java.util.List}
     */
    public List<Illness> getAllIllness(){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final List<Illness> illnesses = illnessRepo.findAll();
            if(null != illnesses) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Got {} records", illnesses.size());
                }
            }
            return illnesses;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }

    }

    /**
     * Find Illness by an ID
     * @param illnessID int
     * @return {@link Illness}
     */
    public Illness findByID(final int illnessID) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            Illness illness = illnessRepo.findOne(illnessID);
            if (null != illness) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Result: name[{}], description[{}]", illness.getName(), illness.getDescription());
                }
            }
            return illness;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }

    }

    /**
     * Find the phase to suggest prescription
     * @param appointmentId int
     * @param diagnostic int
     * @return {@link Phase}
     * @throws BizlogicException
     */
    public Phase getPhaseSugestion(final int appointmentId, final int diagnostic) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info(" appointmentId[{}], dianostic[{}]", appointmentId, diagnostic);

            final Appointment appointment = appointmentRepo.findOne(appointmentId);
            if (null == appointment) {
                LOGGER.error("Appointment with id[{}] not found", appointmentId);
                throw new BizlogicException("Appointment with id[{}] not found", null, appointmentId);
            }

            final MedicalRecord medicalRecord = appointment.getMedicalRecord();

            final Illness illness = illnessRepo.findOne(diagnostic);
            if (null == illness) {
                LOGGER.error("Illness with id[{}] not found", diagnostic);
                throw new BizlogicException("Illness with id[{}] not found", null, diagnostic);
            }

            // Find the different between 2 day
            Date startDate = medicalRecord.getStartTime();

            Date today = new Date();
            today = DateUtils.roundDate(today, false);

            DateTime dt1 = new DateTime(startDate);
            DateTime dt2 = new DateTime(today);

            int numberOfDay = Days.daysBetween(dt1, dt2).getDays();
            LOGGER.info("Between day: {}", numberOfDay);

            if(0 > numberOfDay) {
                numberOfDay = Math.abs(numberOfDay);
            }
            // Select suitable phase
            return phaseRepo.findSuitablePhase(illness.getId(), numberOfDay);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * Find all illness names
     * @return {@link List}
     */
    public List<String> findAllIllnessName() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final List<String> illnessNames = illnessRepo.findAllNames();
            if (null != illnessNames) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Illness list size: {}", illnessNames.size());
                }
                return illnessNames;
            }
            return Collections.emptyList();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    /**
     * Find all default clinical symptoms
     * @return
     */
    public List<String> findDefaultClinicalSymptom() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (null == defaultSymptoms) {
                return Collections.emptyList();
            }
            final String[] arr = defaultSymptoms.split(",");
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Got {} records", arr.length);
            }
            return Arrays.asList(arr);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
