/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPageModel<E extends AbstractKeyEntity, M extends AbstractKeyModel> {
    /**
     * The page number (zero-based).
     */
    private int pageNumber = 0;
    /**
     * The total pages.
     */
    private int totalPages = 0;
    /**
     * The total elements.
     */
    private long totalElements = 0;
    /**
     * The page size.
     */
    private int pageSize = 0;
    /**
     * The number of elements.
     */
    private int numberOfElements = 0;
    /**
     * The elements.
     */
    private List<M> dataList;

    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public AbstractPageModel(final Page<E> pageEntities) {
        if (null != pageEntities) {
            try {
                this.fromPageEntities(pageEntities);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            dataList = new ArrayList<M>();
        }
    }
    /**
     * Get the pageNumber attribute.
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }
    /**
     * Get the totalPages attribute.
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }
    /**
     * Get the totalElements attribute.
     * @return the totalElements
     */
    public long getTotalElements() {
        return totalElements;
    }
    /**
     * Get the pageSize attribute.
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    /**
     * Get the numberOfElements attribute.
     * @return the numberOfElements
     */
    public int getNumberOfElements() {
        return numberOfElements;
    }
    /**
     * Get the dataList attribute.
     * @return the dataList
     */
    public List<M> getDataList() {
        return dataList;
    }
    /**
     * <p>
     * Get model class.
     * </p>
     * @return {@link Class}
     */
    protected abstract Class<M> getModelClass();
    /**
     * <p>
     * Set page summary.
     * </p>
     * @param page {@link Page}
     * @param <EXT> EXT
     */
    public <EXT extends AbstractKeyEntity> void setPageSummary(final Page<EXT> page) {
        this.pageNumber = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageSize = page.getSize();
        this.numberOfElements = page.getNumberOfElements();
    }
    /**
     * <p>
     * Convert a page of entities to this page.
     * </p>
     * @param page {@link Page}
     * @throws InstantiationException ie
     * @throws IllegalAccessException iae
     */
    protected void fromPageEntities(final Page<E> page) throws InstantiationException, IllegalAccessException {
        this.setPageSummary(page);
        final List<E> entities = page.getContent();
        this.dataList = new ArrayList<M>();
        if (null != entities && !entities.isEmpty()) {
            for (E entity : entities) {
                final M model = (M) getModelClass().newInstance();
                model.fromEntity(entity);
                this.dataList.add(model);
            }
        }
    }

    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setNumberOfElements(final int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setDataList(final List<M> dataList) {
        this.dataList = dataList;
    }
}
