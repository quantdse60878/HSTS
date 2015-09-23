/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.common.jpa;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * The entity has one id primary key
 */
@MappedSuperclass
public abstract class AbstractKeyEntity implements Serializable {
    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Default constructor.
     */
    protected AbstractKeyEntity() {
    }

    /**
     *
     * @param id int
     */
    protected AbstractKeyEntity(final int id) {
        this.id = id;
    }

    /**
     * Get the id attribute.
     * @return the id
     */
    public final int getId() {
        return id;
    }
    /**
     * Set the id attribute.
     * @param id the id to set
     */
    public final void setId(final int id) {
        this.id = id;
    }
}
