/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.apache.commons.lang3.tuple.ImmutablePair;
import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The model has one primary key field
 * @param <E>
 */
public abstract class AbstractKeyModel<E extends AbstractKeyEntity> implements Serializable {
    /**
     *
     */
    private int id;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
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

    /**
     *
     * @param entity
     * @param model
     */
    public <ET extends AbstractKeyEntity> void setShortModel(final ET entity, final AbstractKeyModel<ET> model) {
        final Map<String, ImmutablePair<String, Class<?>>> methods = new HashMap<String, ImmutablePair<String, Class<?>>>();
        methods.put("getId", new ImmutablePair<String, Class<?>>("setId", int.class));
        methods.put("getName", new ImmutablePair<String, Class<?>>("setName", String.class));
        methods.put("getFullName", new ImmutablePair<String, Class<?>>("setFullName", String.class));
        methods.put("getDescription", new ImmutablePair<String, Class<?>>("setDescription", String.class));

        for (Map.Entry<String, ImmutablePair<String, Class<?>>> method : methods.entrySet()) {
            try {
                final Object val = entity.getClass().getMethod(method.getKey()).invoke(entity);
                model.getClass().getMethod(method.getValue().getLeft(),
                        method.getValue().getRight()).invoke(model, val);
            } catch (Exception ignore) {
                continue;
            }
        }
    }
}
