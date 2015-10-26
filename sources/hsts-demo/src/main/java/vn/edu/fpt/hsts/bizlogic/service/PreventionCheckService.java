package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.App;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.PreventionCheckRepo;

import java.util.List;

/**
 * Created by Aking on 10/22/2015.
 */
@Service
public class PreventionCheckService {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PreventionCheckService.class);

    /**
     * The {@link PreventionCheckRepo}.
     */
    @Autowired
    private PreventionCheckRepo preventionCheckRepo;

    /**
     * The {@link AppointmentRepo}.
     */
    @Autowired
    private AppointmentRepo appointmentRepo;

    public PreventionCheck findLastPreventionCheckFromAppointment(final Appointment appointment){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointment[{}]", appointment);
            List<PreventionCheck> preventionChecks = preventionCheckRepo.findPreventionCheckByAppointment(appointment);
            if (null != preventionChecks && !preventionChecks.isEmpty()){
                return preventionChecks.get(0);
            }
            return null;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PreventionCheck findLastPreventionCheckByPatientId(final int patientId){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            final List<Appointment> lastAppointments = appointmentRepo.findLastAppointmentByPatient(patientId, new PageRequest(0, 1));
            if (null != lastAppointments && !lastAppointments.isEmpty()) {
                final Appointment appointment = lastAppointments.get(0);
                List<PreventionCheck> preventionCheckList = preventionCheckRepo.findPreventionCheckByAppointment(appointment.getId());
                if (null != preventionCheckList && !preventionCheckList.isEmpty()) {
                   return preventionCheckList.get(preventionCheckList.size() -1);
                }
            }
            return null;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
