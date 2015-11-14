package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.PracticePhasePageModel;
import vn.edu.fpt.hsts.bizlogic.model.PracticePageModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Practice;
import vn.edu.fpt.hsts.persistence.repo.PracticeRepo;

import java.util.List;

/**
 * Created by Aking on 10/10/2015.
 */
@Service
public class PracticeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeService.class);

    /**
     * The {@link PracticeRepo}.
     */
    @Autowired
    private PracticeRepo practiceRepo;

    public List<Practice> getAllPractice(){
        return practiceRepo.findAll();
    }

    public List<String> getAllPracticeName() {
        return practiceRepo.findAllName();
    }

    public PracticePageModel findPractices(final String name, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            String formatName = "%" + name + "%";
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            final Page<Practice> practicePage = practiceRepo.findByNameLike(formatName, pageRequest);
            final PracticePageModel pageModel = new PracticePageModel(practicePage);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
