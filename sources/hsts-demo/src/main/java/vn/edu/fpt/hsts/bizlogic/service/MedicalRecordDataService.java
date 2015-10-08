package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecordData;
import vn.edu.fpt.hsts.persistence.repo.AppointmentRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordDataRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;

import java.util.Date;

/**
 * Created by QUYHKSE61160 on 10/8/15.
 */
@Service
public class MedicalRecordDataService {

    @Autowired
    private MedicalRecordDataRepo medicalRecordDataRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;

    public boolean sendMedicalData(final int appointmentId, final int numberOfStep, final Date collectDate) {

        MedicalRecordData medicalRecordData = new MedicalRecordData();
        medicalRecordData.setType(IDbConsts.IMedicalRecordDataType.UN_CALCULATED);
        medicalRecordData.setNumberOfStep(numberOfStep);

        Appointment appointment = appointmentRepo.findOne(appointmentId);

        medicalRecordData.setAppointment(appointment);
        medicalRecordData.setCollectedDate(collectDate);
        medicalRecordDataRepo.save(medicalRecordData);

        return true;
    }

}
