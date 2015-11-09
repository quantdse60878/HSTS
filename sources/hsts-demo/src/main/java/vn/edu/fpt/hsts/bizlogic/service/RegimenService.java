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
import vn.edu.fpt.hsts.bizlogic.model.regimen.RegimenPageModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.entity.Regimen;
import vn.edu.fpt.hsts.persistence.repo.FoodPhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.IllnessRepo;
import vn.edu.fpt.hsts.persistence.repo.MedicinePhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.PhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.PracticePhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.RegimenRepo;

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
}
