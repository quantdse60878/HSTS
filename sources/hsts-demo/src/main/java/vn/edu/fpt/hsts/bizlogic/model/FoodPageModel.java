/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.Food;

public class FoodPageModel extends AbstractPageModel<Food, FoodModel>{
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public FoodPageModel(Page<Food> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<FoodModel> getModelClass() {
        return FoodModel.class;
    }
}
