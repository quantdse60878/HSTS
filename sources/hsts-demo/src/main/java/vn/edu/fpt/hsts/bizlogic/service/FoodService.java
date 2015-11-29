package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import vn.edu.fpt.hsts.bizlogic.model.*;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;
import vn.edu.fpt.hsts.persistence.repo.FoodPhaseRepo;
import vn.edu.fpt.hsts.persistence.repo.FoodRepo;
import vn.edu.fpt.hsts.persistence.repo.FoodTreatmentRepo;
import vn.edu.fpt.hsts.persistence.repo.UnitOfFoodRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
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
    private UnitOfFoodService unitOfFoodService;

    @Autowired
    private UnitOfFoodRepo unitOfFoodRepo;

    @Autowired
    private FoodPhaseRepo foodPhaseRepo;

    @Autowired
    private FoodTreatmentRepo foodTreatmentRepo;

    public List<Food> getAllFood() {
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

    public List<Food> findFoods() {
        return foodRepo.findAll();
    }

    public List<UnitOfFoodModel> findUnitName(final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("foodId[{}]", foodId);
            final List<UnitOfFood> unitOfFoods = unitOfFoodRepo.findByFoodId(foodId);
            if (!CollectionUtils.isEmpty(unitOfFoods)) {
                final List<UnitOfFoodModel> modelList = new ArrayList<UnitOfFoodModel>();
                for (UnitOfFood u : unitOfFoods) {
                    final UnitOfFoodModel model = new UnitOfFoodModel();
                    model.fromEntity(u);
                    modelList.add(model);
                }
                return modelList;
            }
            return Collections.emptyList();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public FoodModel findFood(final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("foodId[{}]", foodId);
            final Food food = foodRepo.findOne(foodId);
            final FoodModel foodModel = new FoodModel();
            foodModel.fromEntity(food);
            return foodModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public UnitOfFoodModel unitDetail(final int id) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("id[{}]", id);
            final UnitOfFood unitOfFood = unitOfFoodRepo.findOne(id);
            final UnitOfFoodModel model = new UnitOfFoodModel();
            model.fromEntity(unitOfFood);
            FoodNutriValModel foodNutriValModel = new FoodNutriValModel(unitOfFood.getListElementNutritionValue());
            model.setFoodNutriValModel(foodNutriValModel);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void updateUnit(final UnitOfFoodModel model) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("model[{}]", model);
            final UnitOfFood unitOfFood = unitOfFoodRepo.findOne(model.getId());
            unitOfFood.setCaloriesEstimate(model.getCaloriesEstimate());
            unitOfFood.setListElementNutritionName(IDbConsts.IUnitOfFood.listElNutriName);
            unitOfFood.setListElementNutritionValue(model.getFoodNutriValModel().toString());
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void createUnit(final UnitOfFoodModel model) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("model[{}]", model);
            final Food food = foodRepo.findOne(model.getFoodId());
            final UnitOfFood unitOfFood = new UnitOfFood();
            unitOfFood.setFood(food);
            unitOfFood.setUnitName(model.getFoodUnit());
            unitOfFood.setCaloriesEstimate(model.getCaloriesEstimate());
            unitOfFood.setListElementNutritionName(IDbConsts.IUnitOfFood.listElNutriName);
            unitOfFood.setListElementNutritionValue(model.getFoodNutriValModel().toString());
            unitOfFoodRepo.saveAndFlush(unitOfFood);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void createFood(final FoodModel model) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("model[{}],FoodNutriValModel[{}]", model, model.getFoodNutriValModel());
            final Food food = new Food();
            food.setName(model.getName());
            foodRepo.saveAndFlush(food);

            if (!CollectionUtils.isEmpty(model.getUnits())) {
                for (UnitOfFoodModel unitOfFoodModel : model.getUnits()) {
                    UnitOfFood unitOfFood = new UnitOfFood();
                    unitOfFood.setFood(food);
                    unitOfFood.setUnitName(unitOfFoodModel.getFoodUnit());
                    unitOfFood.setCaloriesEstimate(unitOfFoodModel.getCaloriesEstimate());
                    unitOfFood.setListElementNutritionName(IDbConsts.IUnitOfFood.listElNutriName);
                    unitOfFood.setListElementNutritionValue(model.getFoodNutriValModel().toString());
                    unitOfFoodRepo.saveAndFlush(unitOfFood);
                }
            }
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void updateFood(final int foodId, final String foodName) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Food food = foodRepo.findOne(foodId);
            food.setName(foodName);
            foodRepo.saveAndFlush(food);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public FoodModel detailFood(final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Food food = foodRepo.findOne(foodId);
            final FoodModel model = new FoodModel();
            model.fromEntity(food);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public void createFood(String nameFood,String kcal,String unit,String animalFat,String animalProtein,String calcium,
                           String lipid, String starch,String protein,String fiber,String iron,String sodium,String vitaminB1,
                           String vitaminB2,String vitaminC,String vitaminPP,String zinc) {
        Food food = new Food();
        food.setName(nameFood);
        foodRepo.save(food);
        unitOfFoodService.createUnitOfFood(food, kcal, unit, animalFat, animalProtein, calcium, lipid, starch, protein, fiber, iron,
                sodium, vitaminB1, vitaminB2, vitaminC, vitaminPP, zinc);

    }

    public FoodNutritionModel getFood(int foodId) {
        FoodNutritionModel model = new FoodNutritionModel();
        Food food = foodRepo.findOne(foodId);
        model.setFoodName(food.getName());
        List<UnitOfFood> unitOfFoods = unitOfFoodService.findUnitOfFoodsByFoodID(foodId);
        for (UnitOfFood item : unitOfFoods){
            model.getUnitName().add(item.getUnitName());
            model.getkCal().add(item.getCaloriesEstimate());
            model.getValue().add(item.getListElementNutritionValue());
        }
        return model;
    }

    public void updateFood(String foodId, String nameFood, String kcal, String unit, String animalFat, String animalProtein,
                           String calcium, String lipid, String starch, String protein, String fiber, String iron, String sodium,
                           String vitaminB1, String vitaminB2, String vitaminC, String vitaminPP, String zinc) {
        Food food = foodRepo.findOne(Integer.parseInt(foodId));
        food.setName(nameFood);
        foodRepo.save(food);
        unitOfFoodService.updateUnitOfFood(food, kcal, unit, animalFat, animalProtein, calcium, lipid, starch, protein, fiber, iron,
                sodium, vitaminB1, vitaminB2, vitaminC, vitaminPP, zinc);
    }

    public String deleteFood(int foodId) {
        if(checkFoodPhase(foodId) || checkFoodTreatment(foodId)){
            return "500";
        }
        List<UnitOfFood> unitOfFoods = unitOfFoodService.findUnitOfFoodsByFoodID(foodId);
        for(UnitOfFood item : unitOfFoods){
            unitOfFoodRepo.delete(item);
        }
        foodRepo.delete(foodRepo.findOne(foodId));
        return "200";
    }

    public boolean checkFoodPhase(int foodId){
        if(foodPhaseRepo.findByFoodId(foodId).size() != 0) return true;
        return false;
    }

    public boolean checkFoodTreatment(int foodId){
        if(foodTreatmentRepo.findFoodTreatmentByFoodId(foodId) != null) return true;
        return false;
    }
}
