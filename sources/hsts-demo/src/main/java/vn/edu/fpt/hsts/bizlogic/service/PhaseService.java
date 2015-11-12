package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;
import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.PracticePhase;
import vn.edu.fpt.hsts.persistence.entity.Regimen;
import vn.edu.fpt.hsts.persistence.repo.FoodPhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicinePhaseRepo;
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

    public Phase findPhaseByIllnessID(final int illnessID){
        return phaseRepo.findPhaseByIllnessID(illnessID);
    }

    public Phase findByID(final int phaseID){
        return phaseRepo.getOne(phaseID);
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
}
