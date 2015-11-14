package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.edu.fpt.hsts.bizlogic.PracticePhasePageModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePageModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePhasePageModel;
import vn.edu.fpt.hsts.bizlogic.model.regimen.PhaseModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.PracticePhase;
import vn.edu.fpt.hsts.persistence.entity.Regimen;
import vn.edu.fpt.hsts.persistence.repo.FoodPhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicinePhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicineRepo;
import vn.edu.fpt.hsts.persistence.repo.PhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.PracticePhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.RegimenRepo;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Aking on 10/9/2015.
 */
@Service
public class PhaseService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PhaseService.class);

    /**
     * The {@link PhaseRepo}.
     */
    @Autowired
    private PhaseRepo phaseRepo;

    /**
     * The {@link RegimenRepo}.
     */
    @Autowired
    private RegimenRepo regimenRepo;

    /**
     * The {@link FoodPhaseRepo}.
     */
    @Autowired
    private FoodPhaseRepo foodPhaseRepo;

    /**
     * The {@link MedicinePhaseRepo}.
     */
    @Autowired
    private MedicinePhaseRepo medicinePhaseRepo;

    /**
     * The {@link PracticePhaseRepo}.
     */
    @Autowired
    private PracticePhaseRepo practicePhaseRepo;

    /**
     * The {@link MedicineRepo}.
     */
    @Autowired
    private MedicineRepo medicineRepo;


    public Phase findPhaseByIllnessID(final int illnessID){
        return phaseRepo.findPhaseByIllnessID(illnessID);
    }

    public Phase findByID(final int phaseID){
        return phaseRepo.findOne(phaseID);
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void createNewPhase(final int regimenId, final int numberDay) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("regimenId[{}], numberDay[{}]", regimenId, numberDay);
            final Regimen regimen = regimenRepo.findOne(regimenId);
            if (null == regimen) {
                throw new BizlogicException("Regimen not found");
            }
            final Phase phase = new Phase();
            phase.setNumberOfDay(numberDay);
            phase.setRegimen(regimen);
            phase.setUpdateTime(new Date());
            final int count = phaseRepo.countByRegimenId(regimenId);
            phase.setPhaseOrder(count + 1);

            phaseRepo.saveAndFlush(phase);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Create new phase for regimen[{}] success", regimenId);
            }
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void deletePhase(final int phaseId) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId", phaseId);
            // delete reference data
            final Phase phase = phaseRepo.findOne(phaseId);
            if (null == phase) {
                throw new BizlogicException("NULL");
            }
            List<FoodPhase> foodPhases = foodPhaseRepo.findByPhaseId(phaseId);
            if (!CollectionUtils.isEmpty(foodPhases)) {
                foodPhaseRepo.delete(foodPhases);
            }
            final PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE);
            List<MedicinePhase> medicinePhases = medicinePhaseRepo.findByPhaseId(phaseId);
            if (!CollectionUtils.isEmpty(medicinePhases)) {
                medicinePhaseRepo.delete(medicinePhases);
            }
            List<PracticePhase> practicePhases = practicePhaseRepo.findByPhaseId(phaseId);
            if (!CollectionUtils.isEmpty(practicePhases)) {
                practicePhaseRepo.delete(practicePhases);
            }
            phaseRepo.delete(phase);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Phase[{}] delete successfully", phaseId);
            }
        } catch (Exception e) {
            throw new BizlogicException("Error");
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void reorderingPhase(final int regimenId) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("regimenId[{}]", regimenId);

            final PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE);
            Page<Phase> phases = phaseRepo.findByRegimenId(regimenId, pageRequest);
            if (phases.hasContent()) {
                int count = 1;
                for (Phase phase: phases.getContent())  {
                    phase.setPhaseOrder(count++);
                    phase.setUpdateTime(new Date());
                    phaseRepo.saveAndFlush(phase);
                }
            }
        } catch (Exception e) {
            throw new BizlogicException("Error");
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public MedicinePhasePageModel getMedicinesByPhase(final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            final PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE);
            final Page<MedicinePhase> medicinePhases = medicinePhaseRepo.findByPhaseId(phaseId, pageRequest);
            final MedicinePhasePageModel pageModel = new MedicinePhasePageModel(medicinePhases);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PracticePhasePageModel getPracticesByPhase(final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}]", phaseId);
            final PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE);
            final Page<PracticePhase> phasePage = practicePhaseRepo.findByPhaseId(phaseId, pageRequest);
            final PracticePhasePageModel pageModel = new PracticePhasePageModel(phasePage);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    @Transactional(rollbackOn = BizlogicException.class)
    public void addMedicineToPhase(final int phaseId, final int medicineId,
                                   final int numberOfTime, final int quantitative, final String advice)
            throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Phase phase = phaseRepo.findOne(phaseId);
            if (null == phase) {
                throw new BizlogicException("Null");
            }
            Medicine medicine = medicineRepo.findOne(medicineId);
            final MedicinePhase medicinePhase = new MedicinePhase();
            medicinePhase.setAdvice(advice);
            medicinePhase.setMedicine(medicine);
            medicinePhase.setPhase(phase);
            medicinePhase.setQuantitative(quantitative);
            medicinePhase.setNumberOfTime(numberOfTime);
            medicinePhaseRepo.saveAndFlush(medicinePhase);
        } catch (Exception e) {
            throw new BizlogicException("Error");
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void updateMedicineToPhase(final int id,
                                   final int numberOfTime, final int quantitative, final String advice)
            throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final MedicinePhase medicinePhase = medicinePhaseRepo.findOne(id);
            medicinePhase.setAdvice(advice);
            medicinePhase.setQuantitative(quantitative);
            medicinePhase.setNumberOfTime(numberOfTime);
            medicinePhaseRepo.saveAndFlush(medicinePhase);
        } catch (Exception e) {
            throw new BizlogicException("Error");
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void deleteMedicineToPhase(final int id)
            throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            medicinePhaseRepo.delete(id);
        } catch (Exception e) {
            throw new BizlogicException("Error");
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public MedicinePhaseModel find(final int medicinePhaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            MedicinePhase entity = medicinePhaseRepo.findOne(medicinePhaseId);
            final MedicinePhaseModel model = new MedicinePhaseModel();
            model.fromEntity(entity);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PhaseModel findPhase(final int phaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            Phase phase = phaseRepo.findOne(phaseId);
            PhaseModel model = new PhaseModel();
            model.fromEntity(phase);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void updatePhase(final int phaseId, final int numberDay) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("phaseId[{}], numberDay[{}]", phaseId, numberDay);

            final Phase phase = phaseRepo.findOne(phaseId);
            phase.setNumberOfDay(numberDay);
            phaseRepo.saveAndFlush(phase);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
