package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Illness;
import vn.edu.fpt.hsts.persistence.repo.IllnessRepo;

import java.util.List;

/**
 * Created by Aking on 10/9/2015.
 */
@Service
public class IllnessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IllnessService.class);

    @Autowired
    private IllnessRepo illnessRepo;

    public List<Illness> getAllIllness(){
        return illnessRepo.findAll();
    }

    public Illness findByID(final int illnessID) {
        return illnessRepo.getOne(illnessID);
    }
}
