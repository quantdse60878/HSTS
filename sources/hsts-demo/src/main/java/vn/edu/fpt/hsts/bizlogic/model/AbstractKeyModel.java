/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import java.io.Serializable;

/**
 * The model has one primary key field
 * @param <E>
 */
public abstract class AbstractKeyModel<E extends AbstractKeyEntity> implements Serializable {
    /**
     *
     */
    private int id;

    /**
     * Get the id attribute.
     * @return the id
     */
    public int getPk() {
        return id;
    }
    /**
     * Set the pk attribute.
     * @param id the pk to set
     */
    public void setPk(final int id) {
        this.id = id;
    }

    /**
     * <p>
     * Convert an entity to this model.
     * </p>
     * @param entity E
     */
    public void fromEntity(final E entity) {
        this.id = entity.getId();
    }

    /**
     * <p>Get the class of an entity.</p>
     *
     * @return {@link Class}
     */
    protected abstract Class<E> getEntityClass();
}
