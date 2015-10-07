package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.entity.Role;
import vn.edu.fpt.hsts.persistence.repo.AccountRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordDataRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.PatientRepo;
import vn.edu.fpt.hsts.persistence.repo.RoleRepo;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Service
public class PatientService {

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
     * The {@link RoleRepo}.
     */
    @Autowired
    private RoleRepo roleRepo;

    /**
     * The {@link AccountService}.
     */
    @Autowired
    private AccountService accountService;

    /**
     * The {@link AuthenService}.
     */
    @Autowired
    private AuthenService authenService;


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


    @Transactional(rollbackOn = BizlogicException.class)
    public void createPatient() throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // TODO Create account
            final Account account = new Account();
            final String newUsername = accountService.buildUniqueUsername("Man Huynh Khuong");
            account.setEmail("khuongmh@gmail.com");
            account.setUsername(newUsername);
            account.setGender(IDbConsts.IAccountGender.MALE);
            account.setDateOfBirth(new Date());
            account.setFullname("Man Huynh Khuong");

            final Role role = roleRepo.findOne(IDbConsts.IRoleType.PATIENT);
            account.setRole(role);
            // Account have been not actived yet, require change password
            account.setStatus(IDbConsts.IAccountStatus.IN_ACTIVE);
            account.setPassword(authenService.randomPassword());

            accountRepo.saveAndFlush(account);

            // TODO Create patient

            // TODO create medical record

            // TODO Create appointment

            // TODO Create medical record data

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Create new patient successfully");
            }
        } catch (BizlogicException be) {
            throw be;
        } catch (Exception e) {
            LOGGER.error("Error while create new patient: {}", e.getMessage());
            throw new BizlogicException(e.getMessage());
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
