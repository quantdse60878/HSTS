/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.common.jpa;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.springframework.context.annotation.Configuration;

public class EntityNamingStrategy extends DefaultNamingStrategy{

    @Override
    public String classToTableName(String className) {
        return super.classToTableName(className);
    }
}
