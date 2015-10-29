package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecordData;
import vn.edu.fpt.hsts.persistence.entity.ParamMeasurement;
import vn.edu.fpt.hsts.persistence.entity.PropertyRecord;
import vn.edu.fpt.hsts.persistence.repo.*;

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
    @Autowired
    private PropertyRecordRepo propertyRecordRepo;
    @Autowired
    private ParamMeasurementRepo paramMeasurementRepo;

    public boolean sendMedicalData(final int appointmentId, final int numberOfStep, final Date collectDate) {

        MedicalRecordData medicalRecordData = new MedicalRecordData();
        medicalRecordData.setType(IDbConsts.IMedicalRecordDataType.UN_CALCULATED);
//        medicalRecordData.setNumberOfStep(numberOfStep);

        Appointment appointment = appointmentRepo.findOne(appointmentId);
        medicalRecordData.setAppointment(appointment);
        medicalRecordData.setCollectedDate(collectDate);
        medicalRecordDataRepo.save(medicalRecordData);

        ParamMeasurement paramMeasurement = paramMeasurementRepo.findOne(1);

        PropertyRecord propertyRecord = new PropertyRecord();
        propertyRecord.setParamMeasurementValue(numberOfStep + "");
        propertyRecord.setMedicalRecordData(medicalRecordData);
        propertyRecord.setParamMeasurement(paramMeasurement);
        propertyRecordRepo.save(propertyRecord);



        return true;
    }

}
