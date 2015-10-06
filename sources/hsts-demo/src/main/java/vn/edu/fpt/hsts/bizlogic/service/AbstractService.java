/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The abstract service, use for implement common method.
 */
@Component
public class AbstractService {

    /**
     * The value read from application.properties file
     */
    @Value("${hsts.version}")
    private int version;

    protected int getVersion() {
        return version;
    }

    protected void setVersion(final int version) {
        this.version = version;
    }
}
