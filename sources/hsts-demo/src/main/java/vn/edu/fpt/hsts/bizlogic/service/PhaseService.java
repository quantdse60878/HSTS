package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Phase;
import vn.edu.fpt.hsts.persistence.repo.PhaseRepo;

import java.util.List;

/**
 * Created by Aking on 10/9/2015.
 */
@Service
public class PhaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhaseService.class);

    @Autowired
    private PhaseRepo phaseRepo;

    public Phase findPhaseByIllnessID(final int illnessID){
        return phaseRepo.findPhaseByIllnessID(illnessID);
    }

    public Phase findByID(final int phaseID){
        return phaseRepo.getOne(phaseID);
    }
}
