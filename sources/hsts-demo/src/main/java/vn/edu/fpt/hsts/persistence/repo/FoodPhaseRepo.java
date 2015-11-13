/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/10/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.FoodPhase;

import java.util.List;

@Repository
public interface FoodPhaseRepo extends JpaRepository<FoodPhase, Integer> {

    @Query(value = "select f from FoodPhase f where phase.regimen.id = :regimenId")
    public List<FoodPhase> findByRegimenId(@Param("regimenId") final int regimenId);

    @Query(value = "select f from FoodPhase f where phase.id = :phaseId")
    public List<FoodPhase> findByPhaseId(@Param("phaseId") final int phaseId);
}
