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
import vn.edu.fpt.hsts.persistence.entity.Regimen;

@Repository
public interface RegimenRepo extends JpaRepository<Regimen, Integer> {

    @Query("select r from Regimen r where lower(illness.name) like lower(:name)")
    Page<Regimen> findByIllnessNameLike(@Param("name") final String name, final Pageable pageable);
}
