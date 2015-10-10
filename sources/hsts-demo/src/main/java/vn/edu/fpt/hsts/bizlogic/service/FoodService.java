package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.repo.FoodRepo;

import java.util.List;

/**
 * Created by Aking on 10/10/2015.
 */
@Service
public class FoodService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodService.class);

    @Autowired
    private FoodRepo foodRepo;

    public List<Food> getAllFood(){
        return foodRepo.findAll();
    }
}
