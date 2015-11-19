/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.apache.commons.collections.CollectionUtils;
import vn.edu.fpt.hsts.persistence.entity.Food;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;

import java.util.ArrayList;
import java.util.List;

public class FoodModel extends AbstractKeyModel<Food> {
    @Override
    protected Class<Food> getEntityClass() {
        return Food.class;
    }

    /**
     *
     */
    private String name;

    private List<UnitOfFoodModel> units;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void fromEntity(Food entity) {
        super.fromEntity(entity);
        name = entity.getName();
        final List<UnitOfFood> units = entity.getUnitOfFoodList();
        if (!CollectionUtils.isEmpty(units)) {
            this.units = new ArrayList<UnitOfFoodModel>();
            for (UnitOfFood unitOfFood: units) {
                final UnitOfFoodModel model = new UnitOfFoodModel();
                model.fromEntity(unitOfFood);
                this.units.add(model);
            }
        }
    }

    public List<UnitOfFoodModel> getUnits() {
        return units;
    }

    public void setUnits(List<UnitOfFoodModel> units) {
        this.units = units;
    }
}
