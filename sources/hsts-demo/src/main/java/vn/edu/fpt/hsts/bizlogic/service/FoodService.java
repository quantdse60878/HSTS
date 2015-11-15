package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.FoodPageModel;
import vn.edu.fpt.hsts.bizlogic.model.FoodUnitModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;
import vn.edu.fpt.hsts.persistence.repo.FoodRepo;
import vn.edu.fpt.hsts.persistence.repo.UnitOfFoodRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aking on 10/10/2015.
 */
@Service
public class FoodService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodService.class);

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private UnitOfFoodRepo unitOfFoodRepo;

    public List<Food> getAllFood(){
        return foodRepo.findAll();
    }

    public List<FoodUnitModel> findUnitsByFoodId(final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("foodId[{}]", foodId);
            final List<UnitOfFood> list = unitOfFoodRepo.findByFoodId(foodId);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Got {} record", list.size());
            }
            UnitOfFood tmp;
            List<FoodUnitModel> foodUnitModels = new ArrayList<FoodUnitModel>();
            FoodUnitModel fum;
            for (int i = 0; i < list.size(); i++) {
                tmp = list.get(i);
                fum = new FoodUnitModel(tmp.getId(), tmp.getUnitName());
                foodUnitModels.add(fum);
            }
            return foodUnitModels;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public FoodPageModel findFoods(final String name, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("name[{}], page[{}], pageSize[{}]", name, page, pageSize);
            final String formatName = "%" + name + "%";
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            final Page<Food> foodPage = foodRepo.findByNameLike(name, pageRequest);
            final FoodPageModel pageModel = new FoodPageModel(foodPage);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public List<String> findUnitName(final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("foodId[{}]", foodId);
            return unitOfFoodRepo.findByFood(foodId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
