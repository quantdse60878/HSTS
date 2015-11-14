/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Doctor;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    /**
     * Find doctor by account id.
     * @param accountId account id
     * @return {@link Doctor}
     */
    @Query("select d from Doctor d where d.account.id = :accountId")
    public Doctor findByAccountId(@Param("accountId") final int accountId);

    /**
     * Find doctor by unique username.
     * @param username
     * @return {@link Doctor}
     */
    @Query("select d from Doctor d where d.account.username = :username")
    public Doctor findByUsername(@Param("username") final String username);

    @Query("select d from Doctor d where lower(account.fullName) like lower(:criteria) and account.status = :status order by account.fullName")
    public Page<Doctor> findByNameLike(@Param("criteria")final String searchCriteria, @Param("status") final byte status, final Pageable pageable);

    @Query("select d from Doctor d where lower(account.fullName) like lower(:criteria) and account.status = :status and account.role.id = :roleId order by account.fullName")
    public Page<Doctor> findByRoleAndNameLike(@Param("criteria") final String searchCriteria, @Param("status") final byte status, @Param("roleId") final int roleId, final Pageable pageable);
}
