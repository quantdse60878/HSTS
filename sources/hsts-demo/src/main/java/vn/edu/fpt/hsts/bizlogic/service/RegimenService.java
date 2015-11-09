/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/9/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.edu.fpt.hsts.bizlogic.model.regimen.PhaseModel;
import vn.edu.fpt.hsts.bizlogic.model.regimen.PhasePageModel;
import vn.edu.fpt.hsts.bizlogic.model.regimen.RegimenModel;
import vn.edu.fpt.hsts.bizlogic.model.regimen.RegimenPageModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;
import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.PracticePhase;
import vn.edu.fpt.hsts.persistence.entity.Regimen;
import vn.edu.fpt.hsts.persistence.repo.FoodPhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.IllnessRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicinePhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.PhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.PracticePhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.RegimenRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegimenService extends AbstractService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RegimenService.class);

    /**
     * The {@link RegimenRepo}.
     */
    @Autowired
    private RegimenRepo regimenRepo;

    /**
     * The {@link IllnessRepo}.
     */
    @Autowired
    private IllnessRepo illnessRepo;

    /**
     * The {@link PhaseRepo}.
     */
    @Autowired
    private PhaseRepo phaseRepo;

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

    public RegimenPageModel regimens(final String name, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("name[{}], page[{}], pageSize[{}]", name, page, pageSize);
            Page<Regimen> pageEntities;
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            if (StringUtils.isNotEmpty(name)) {
                final String formatName = "%" + name + "%";
               pageEntities = regimenRepo.findByIllnessNameLike(formatName, pageRequest);
            } else {
                pageEntities = regimenRepo.findAll(pageRequest);
            }
            if (pageEntities.hasContent()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Got {} records ", pageEntities.getNumberOfElements());
                }
            }
            final RegimenPageModel pageModel = new RegimenPageModel(pageEntities);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public PhasePageModel phases(final int regimenId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("regimenId[{}]", regimenId);
            final PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE);
            final Page<Phase> pageEntities = phaseRepo.findByRegimenId(regimenId, pageRequest);
            if (pageEntities.hasContent()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Got {} records ", pageEntities.getNumberOfElements());
                }
            }
            final PhasePageModel pageModel = new PhasePageModel(pageEntities);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public RegimenModel regimenInfo(final int regimenId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("regimenId[{}]", regimenId);
            final Regimen regimen = regimenRepo.findOne(regimenId);
            final RegimenModel model = new RegimenModel();
            if (null != regimen) {
                model.fromEntity(regimen);
            }
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void deleteRegimen(final int regimenId) throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("regimenId[{}]", regimenId);
            final Regimen regimen = regimenRepo.findOne(regimenId);
            if (null == regimen) {
                throw new BizlogicException("Regimen is not found");
            }
            // Delete reference data: phase, phase food, phase medicine, phase practice
            List<FoodPhase> foodPhases = foodPhaseRepo.findByRegimenId(regimenId);
            if (!CollectionUtils.isEmpty(foodPhases)) {
                foodPhaseRepo.delete(foodPhases);
            }
            List<MedicinePhase> medicinePhases = medicinePhaseRepo.findByRegimenId(regimenId);
            if (!CollectionUtils.isEmpty(medicinePhases)) {
                medicinePhaseRepo.delete(medicinePhases);
            }
            List<PracticePhase> practicePhases = practicePhaseRepo.findByRegimenId(regimenId);
            if (!CollectionUtils.isEmpty(practicePhases)) {
                practicePhaseRepo.delete(practicePhases);
            }
            // Delete phase list
            List<Phase> phases = phaseRepo.findByRegimenId(regimenId);
            if (!CollectionUtils.isEmpty(phases)) {
                phaseRepo.delete(phases);
            }
            // Delete regimen self
            regimenRepo.delete(regimen);

            // Flush all
            foodPhaseRepo.flush();
            medicinePhaseRepo.flush();
            practicePhaseRepo.flush();
            phaseRepo.flush();
            regimenRepo.flush();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Delete regimen[{}] succesfully", regimenId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
