package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.repo.PatientRepo;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Service
public class PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepo patientRepo;


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


}
