/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.web.config;

public interface ControllerParam {

    /**
     *
     */
    final String DEFAULT_PAGE = "0";

    /**
     *
     */
    final String DEFAULT_PAGE_SIZE = "50";

    /**
     *
     */
    final String EMPTY = "";

    /**
     * Un-limit page size.
     * It is maximum value of integer.
     */
    String UNLIMIT_PAGE_SIZE = "2147483647";

    /**
     *
     */
    String OK_STATUS = "{\"status\":\"ok\"}";

    /**
     *
     */
    String FAIL_STATUS = "{\"status\":\"fail\"}";
}
