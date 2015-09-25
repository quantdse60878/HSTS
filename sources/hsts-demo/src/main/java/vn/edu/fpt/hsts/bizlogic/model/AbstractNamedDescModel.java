/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.jpa.AbstractNamedDescEntity;

/**
 * The model has primary id, name and desciption field
 * @param <E>
 */
public abstract class AbstractNamedDescModel<E extends AbstractNamedDescEntity> extends AbstractNamedModel<E>{

    /**
     *
     */
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public void fromEntity(final E entity) {
        super.fromEntity(entity);
        this.description = entity.getDescription();
    }
}
