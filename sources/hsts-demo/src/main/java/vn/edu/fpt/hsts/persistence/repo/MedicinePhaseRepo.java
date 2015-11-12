/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/10/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.MedicinePhase;

import java.util.List;

@Repository
public interface MedicinePhaseRepo extends JpaRepository<MedicinePhase, Integer> {

    @Query("select m from MedicinePhase m where phase.regimen.id = :regimenId")
    public List<MedicinePhase> findByRegimenId(@Param("regimenId") final int regimenId);

    @Query("select m from MedicinePhase m where phase.id = :phaseId")
    public List<MedicinePhase> findByPhaseId(@Param("phaseId") final int phaseId);
}
