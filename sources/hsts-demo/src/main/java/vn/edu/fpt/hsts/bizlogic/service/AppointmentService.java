package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;

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

    public Appointment findAppointmentByPatientID(int patientID) {

        return null;
    }
}
