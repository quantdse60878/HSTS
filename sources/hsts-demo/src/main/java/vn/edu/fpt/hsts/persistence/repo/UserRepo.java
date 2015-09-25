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
import vn.edu.fpt.hsts.persistence.entity.User;

/**
 * The repository for User entity.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    /**
     * Find by username and password, use hql
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select u from User u where username = :username and password = :password")
    public User findByUsernameAndPassword(@Param(value = "username") final String username, @Param("password") final String password);

    /**
     * Find User by username and password, use native query
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select * from User u where username = :username and password = :password", nativeQuery = true)
    public User findByUsernameAndPasswordWithNative(@Param(value = "username") final String username, @Param("password") final String password);
}
