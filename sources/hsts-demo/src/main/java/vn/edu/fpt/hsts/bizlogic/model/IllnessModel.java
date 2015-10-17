/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/18/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.Illness;

public class IllnessModel extends AbstractNamedDescModel<Illness> {
    @Override
    protected Class<Illness> getEntityClass() {
        return Illness.class;
    }
}
