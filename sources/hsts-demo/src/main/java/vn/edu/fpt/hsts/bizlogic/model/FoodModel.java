/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.Food;

public class FoodModel extends AbstractKeyModel<Food> {
    @Override
    protected Class<Food> getEntityClass() {
        return Food.class;
    }

    /**
     *
     */
    private String name;

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
    }
}
