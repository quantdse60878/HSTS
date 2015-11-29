/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.hsts.bizlogic.model.*;
import vn.edu.fpt.hsts.bizlogic.service.FoodService;
import vn.edu.fpt.hsts.bizlogic.service.PhaseService;
import vn.edu.fpt.hsts.bizlogic.service.UnitOfFoodService;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodController extends AbstractController {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

    /**
     * The {@link FoodService}.
     */
    @Autowired
    private FoodService foodService;

    /**
     * The {@link PhaseService}.
     */
    @Autowired
    private PhaseService phaseService;

    @RequestMapping(value = "/food/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FoodPageModel> findFoods() {
        List<Food> foods = foodService.findFoods();
        List<FoodPageModel> foodPageModels = new ArrayList<FoodPageModel>();
        for (Food item : foods){
            FoodPageModel model = new FoodPageModel();
            model.setId(item.getId());
            model.setName(item.getName());
            String temp = "";
            List<UnitOfFood> unitOfFoods = item.getUnitOfFoodList();
            for (int i=0;i<unitOfFoods.size();i++){
                if(i==0){
                    temp = unitOfFoods.get(i).getUnitName();
                } else {
                    temp += ", " +unitOfFoods.get(i).getUnitName();
                }
            }
            model.setUnit(temp);
            foodPageModels.add(model);
        }
        return foodPageModels;
    }

    @RequestMapping(value = "/phase/food/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FoodPhaseModel detail(@RequestParam("id") final int foodPhaseId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("foodPhaseId[{}]", foodPhaseId);
            return phaseService.findFoodPhase(foodPhaseId);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/phase/food/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addFoodToPhase(@RequestParam("phaseId") final int phaseId,
                                 @RequestParam("foodId") final int foodId,
                                 @RequestParam("numberOfTime") final int numberOfTime,
                                 @RequestParam("quantitative") final int quantitative,
                                 @RequestParam(value = "advice", required = false, defaultValue = EMPTY) final String advice,
                                 @RequestParam("unitName") final String unitName) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.addFoodToPhase(phaseId, foodId, numberOfTime, quantitative, advice, unitName);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "phase/food/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateFoodToPhase(@RequestParam("id") final int id,
                                    @RequestParam("numberOfTime") final int numberOfTime,
                                    @RequestParam("quantitative") final int quantitative,
                                    @RequestParam(value = "advice", required = false, defaultValue = EMPTY) final String advice,
                                    @RequestParam("unitName") final String unitName) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.updateFoodToPhase(id, numberOfTime, quantitative, advice, unitName);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "phase/food/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteFoodToPhase(@RequestParam("id") final int id) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            phaseService.deleteFoodToPhase(id);
            return OK_STATUS;
        } catch (BizlogicException e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/foodUnit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UnitOfFoodModel> findUnitByFoodName(@RequestParam("foodId") final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return foodService.findUnitName(foodId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "foods")
    public ModelAndView foodPage() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final ModelAndView modelAndView = new ModelAndView("foods");
            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "food")
    public ModelAndView foodDetail(@RequestParam("id") final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final ModelAndView modelAndView = new ModelAndView("foodDetail");

            final FoodModel foodModel = foodService.findFood(foodId);
            modelAndView.addObject("FOOD", foodModel);

            return modelAndView;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/unitOfFood/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UnitOfFoodModel unitDetail(@RequestParam("id") final int unitOfFoodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return foodService.unitDetail(unitOfFoodId);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/unitOfFood/update", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateUnit(@RequestBody UnitOfFoodModel model) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            foodService.updateUnit(model);
            return OK_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/unitOfFood/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createUnit(@RequestBody UnitOfFoodModel model) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            foodService.createUnit(model);
            return OK_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/food/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createFood(@RequestBody FoodModel model) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            foodService.createFood(model);
            return OK_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/food/update", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createFood(@RequestParam("foodId") final int foodId,
                             @RequestParam("foodName") final String foodName) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            foodService.updateFood(foodId, foodName);
            return OK_STATUS;
        } catch (Exception e) {
            return FAIL_STATUS;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/food/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FoodModel detailFood(@RequestParam("id") final int foodId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            return foodService.detailFood(foodId);
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @RequestMapping(value = "/food/createFood", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createFood(@RequestParam("foodName") final String nameFood,
                             @RequestParam("unitOfFood") final String unit,
                             @RequestParam("Kcal") final String kcal,
                             @RequestParam("animalFat") final String animalFat,
                             @RequestParam("animalProtein") final String animalProtein,
                             @RequestParam("calcium") final String calcium,
                             @RequestParam("lipid") final String lipid,
                             @RequestParam("starch") final String starch,
                             @RequestParam("protein") final String protein,
                             @RequestParam("fiber") final String fiber,
                             @RequestParam("iron") final String iron,
                             @RequestParam("sodium") final String sodium,
                             @RequestParam("vitaminB1") final String vitaminB1,
                             @RequestParam("vitaminB2") final String vitaminB2,
                             @RequestParam("vitaminC") final String vitaminC,
                             @RequestParam("vitaminPP") final String vitaminPP,
                             @RequestParam("zinc") final String zinc){
        foodService.createFood(nameFood, kcal, unit, animalFat, animalProtein, calcium, lipid, starch, protein, fiber,
                iron, sodium, vitaminB1, vitaminB2, vitaminC, vitaminPP, zinc);
        return "200";
    }

    @RequestMapping(value = "/food/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FoodNutritionModel specificFood(@RequestParam("foodId") final String foodId){
        return foodService.getFood(Integer.parseInt(foodId));
    }

    @RequestMapping(value = "/food/updateFood", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateFood(@RequestParam("foodId") final String foodId,
                             @RequestParam("foodName") final String nameFood,
                             @RequestParam("unitOfFood") final String unit,
                             @RequestParam("Kcal") final String Kcal,
                             @RequestParam("animalFat") final String animalFat,
                             @RequestParam("animalProtein") final String animalProtein,
                             @RequestParam("calcium") final String calcium,
                             @RequestParam("lipid") final String lipid,
                             @RequestParam("starch") final String starch,
                             @RequestParam("protein") final String protein,
                             @RequestParam("fiber") final String fiber,
                             @RequestParam("iron") final String iron,
                             @RequestParam("sodium") final String sodium,
                             @RequestParam("vitaminB1") final String vitaminB1,
                             @RequestParam("vitaminB2") final String vitaminB2,
                             @RequestParam("vitaminC") final String vitaminC,
                             @RequestParam("vitaminPP") final String vitaminPP,
                             @RequestParam("zinc") final String zinc){
        foodService.updateFood(foodId, nameFood, Kcal, unit, animalFat, animalProtein, calcium, lipid, starch, protein, fiber,
                iron, sodium, vitaminB1, vitaminB2, vitaminC, vitaminPP, zinc);
        return "200";
    }

    @RequestMapping(value = "/food/deleteFood", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteFood(@RequestParam("foodId") final String foodId){
        return foodService.deleteFood(Integer.parseInt(foodId));
    }
}
