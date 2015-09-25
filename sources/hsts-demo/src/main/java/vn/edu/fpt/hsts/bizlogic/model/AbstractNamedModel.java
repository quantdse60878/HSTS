/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.jpa.AbstractNamedEntity;

/**
 * The model has primary id and name field
 * @param <E>
 */
public abstract class AbstractNamedModel<E extends AbstractNamedEntity> extends AbstractKeyModel<E> {

    /**
     *
     */
    private String name;

    /**
     * Get the name attribute.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Set the name attribute.
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void fromEntity(final E entity) {
        super.fromEntity(entity);
        this.name = entity.getName();
    }
}
