/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Account;

/**
 * The repository for Account entity.
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

    /**
     * Find by username and password, use hql
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select a from Account a where username = :username and password = :password")
    public Account findByUsernameAndPassword(@Param(value = "username") final String username, @Param("password") final String password);

    /**
     * Find Account by username and password, use native query
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select * from Account a where username = :username and password = :password", nativeQuery = true)
    public Account findByUsernameAndPasswordWithNative(@Param(value = "username") final String username, @Param("password") final String password);
}
