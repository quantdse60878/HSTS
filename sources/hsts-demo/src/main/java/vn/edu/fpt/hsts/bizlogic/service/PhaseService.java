package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.entity.Regimen;
import vn.edu.fpt.hsts.persistence.repo.PhaseRepo;
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
}
