package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.AppointmentPageModel;
import vn.edu.fpt.hsts.bizlogic.model.HisInforDateModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aking on 10/8/2015.
 */
@Service
public class AppointmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepo appointmentRepo;

    public Appointment findAppointmentByID(int appointmentID) {
        return appointmentRepo.findOne(appointmentID);
    }

    public Appointment findEntryAppointmentByPatientId(final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}]", patientId);
            final byte[] statuses = {IDbConsts.IAppointmentStatus.WATTING};
            final List<Appointment> appointmentList = appointmentRepo.findLastAppointmentByPatientId(patientId, statuses);
            if (null != appointmentList && !appointmentList.isEmpty()) {
                return appointmentList.get(0);
            }
            return null;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public List<Appointment> getAllAppointmentToCurrentDateOfPatient(final int patientID){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            Date date = new Date();
            List<Appointment> appointments = appointmentRepo.getAllAppointmentToDate(date, patientID, IDbConsts.IAppointmentStatus.ENTRY);
            return appointments;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public Appointment findAppointmentDate(final String appointmentDate, final int patientId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointmentDate[{}], patientId[{}]", appointmentDate, patientId);
            Date date = DateUtils.parseDate(appointmentDate, DateUtils.DATE_PATTERN_3);
            return appointmentRepo.findAppointmentByDate(date, patientId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public Appointment findParentOfAppointment(final Appointment appointment) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointment[{}]", appointment);
            return appointmentRepo.findParentAppointment(appointment.getId());
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public AppointmentPageModel findAppointmentByPatientId(final int patientId, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("patientId[{}], page[{}], pageSize[{}]", patientId, page, pageSize);
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            final Page<Appointment> appointmentPage = appointmentRepo.findHistoryByPatientId(IDbConsts.IAppointmentStatus.FINISHED, patientId,
                    pageRequest);
            if (appointmentPage.hasContent()) {
               if (LOGGER.isDebugEnabled()) {
                   LOGGER.debug("Got {} records", appointmentPage.getNumberOfElements());
               }
            }
            final AppointmentPageModel pageModel = new AppointmentPageModel(appointmentPage);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
