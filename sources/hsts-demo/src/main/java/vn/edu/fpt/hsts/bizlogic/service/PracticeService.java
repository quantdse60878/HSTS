package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Practice;
import vn.edu.fpt.hsts.persistence.repo.PracticeRepo;

import java.util.List;

/**
 * Created by Aking on 10/10/2015.
 */
@Service
public class PracticeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeService.class);

    @Autowired
    private PracticeRepo practiceRepo;

    public List<Practice> getAllPractice(){
        return practiceRepo.findAll();
    }
}
