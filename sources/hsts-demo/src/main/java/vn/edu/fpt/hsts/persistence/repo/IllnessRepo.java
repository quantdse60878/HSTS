/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/8/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Illness;

import java.util.List;

@Repository
public interface IllnessRepo extends JpaRepository<Illness, Integer> {

    @Query("select i from Illness i where name = :name")
    public Illness findByName(@Param("name") final String name);

    @Query("select distinct name from Illness i")
    public List<String> findAllNames();

    @Query("select i.name from Illness i where lower(name) like lower(:searchCriteria)")
    public List<String> findByName(@Param("searchCriteria") final String searchCriteria, final Pageable pageable);
}
