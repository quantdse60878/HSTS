package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;
import vn.edu.fpt.hsts.persistence.repo.UnitOfFoodRepo;

import java.util.List;

/**
 * Created by Man Huynh Khuong on 11/7/2015.
 */

@Service
public class UnitOfFoodService {

    /**
     * The {@link FoodService}.
     */
    @Autowired
    private UnitOfFoodRepo unitOfFoodRepo;

    public List<UnitOfFood> findAllUnitOfFoods(){
        return unitOfFoodRepo.findAll();
    }
}
