package vn.edu.fpt.hsts.bizlogic.service;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.PhaseRepo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Aking on 10/9/2015.
 */
@Service
public class IllnessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IllnessService.class);

    /**
     * The {@link IllnessRepo}.
     */
    @Autowired
    private IllnessRepo illnessRepo;

    /**
     * The {@link MedicalRecordRepo}.
     */
    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

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

    public List<Illness> getAllIllness(){
        return illnessRepo.findAll();
    }

    public Illness findByID(final int illnessID) {
        return illnessRepo.findOne(illnessID);
    }

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
}
