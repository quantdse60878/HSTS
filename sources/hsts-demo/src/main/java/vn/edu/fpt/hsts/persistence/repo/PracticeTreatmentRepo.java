/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.PracticeTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.List;

@Repository
public interface PracticeTreatmentRepo extends JpaRepository<PracticeTreatment, Integer>{
    @Query(value = "SELECT * FROM practicetreatment WHERE treatmentId = ?1", nativeQuery = true)
    public List<PracticeTreatment> findPracticeTreatmentByTreatmentId(int treatmentId);

    @Query("select distinct t from PracticeTreatment t where treatment = :treatment")
    public List<PracticeTreatment> getAllPracticeTreatmentFromTreatment(@Param("treatment") final Treatment treatment);
}
