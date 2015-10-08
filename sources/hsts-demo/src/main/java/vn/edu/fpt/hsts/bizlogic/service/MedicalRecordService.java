package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;

import java.util.List;

/**
 * Created by Aking on 10/8/2015.
 */
@Service
public class MedicalRecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordService.class);
    /**
     * The {@link MedicalRecordRepo}
     */
    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepo.findAll();
    }

    public MedicalRecord findMedicalRecordByID(int recordID) {
        return medicalRecordRepo.findOne(recordID);
    }

    public List<MedicalRecord> findMedicalRecordByPatientId(int patientID){
        return medicalRecordRepo.findMedicalRecordByPatientId(patientID);
    }
}
