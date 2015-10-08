/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/8/2015.
 */
package vn.edu.fpt.hsts.criteria;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

    /**
     *
     */
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SearchCriteria() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        return sb.toString();
    }
}
