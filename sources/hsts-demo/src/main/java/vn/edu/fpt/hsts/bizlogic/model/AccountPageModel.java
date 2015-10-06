/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.Account;

public class AccountPageModel extends AbstractPageModel<Account, AccountModel>{
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public AccountPageModel(final Page<Account> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<AccountModel> getModelClass() {
        return AccountModel.class;
    }


}
