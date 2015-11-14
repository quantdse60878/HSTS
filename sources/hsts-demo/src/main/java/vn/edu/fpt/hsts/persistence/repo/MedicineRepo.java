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
import vn.edu.fpt.hsts.persistence.entity.Medicine;

import java.util.List;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Integer> {

    @Query("select m from Medicine m where lower(name) = lower(:name)")
    public Medicine findByName(@Param("name") final String name);

    @Query("select i.name from Medicine i")
    List<String> findAllByName();

    @Query("select m from Medicine m where lower(name) like lower(:name)")
    public Page<Medicine> findByNameLike(@Param("name") final String name, final Pageable pageable);
}
