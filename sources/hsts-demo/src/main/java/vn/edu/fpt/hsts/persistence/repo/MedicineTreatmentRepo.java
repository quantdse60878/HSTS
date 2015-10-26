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
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.List;

@Repository
public interface MedicineTreatmentRepo extends JpaRepository<MedicineTreatment, Integer>{

    @Query(value = "SELECT * FROM medicinetreatment WHERE treatmentId = ?1", nativeQuery = true)
    List<MedicineTreatment> findMedicineTreatmentByTreatmentId(int treatmentId);

    @Query("select m from MedicineTreatment m where treatment = :treatment")
    public List<MedicineTreatment> getAllMedicineTreatmentFromTreatment(@Param("treatment") final Treatment treatment);

    @Query("select m from MedicineTreatment m where treatmentId = :treatmentId")
    public List<MedicineTreatment> getMedicineTreatmentById(@Param("treatment") final Treatment treatment);
}
