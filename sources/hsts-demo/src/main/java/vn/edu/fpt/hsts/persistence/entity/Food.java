/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Food extends AbstractKeyEntity {

    @Column(name = "foodName")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
    private List<UnitOfFood> unitOfFoodList;

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UnitOfFood> getUnitOfFoodList() {
        return unitOfFoodList;
    }

    public void setUnitOfFoodList(List<UnitOfFood> unitOfFoodList) {
        this.unitOfFoodList = unitOfFoodList;
    }
}
