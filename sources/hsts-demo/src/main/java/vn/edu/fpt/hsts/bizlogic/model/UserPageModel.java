/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.User;

public class UserPageModel extends AbstractPageModel<User, UserModel>{
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public UserPageModel(final Page<User> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<UserModel> getModelClass() {
        return UserModel.class;
    }


}
