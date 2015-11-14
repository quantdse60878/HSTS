/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/10/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.PracticePhase;

import java.util.List;

@Repository
public interface PracticePhaseRepo extends JpaRepository<PracticePhase, Integer> {

    @Query("select p from PracticePhase p where phase.regimen.id = :regimenId")
    public List<PracticePhase> findByRegimenId(@Param("regimenId") final int regimenId);

    @Query("select p from PracticePhase p where phase.id = :phaseId")
    public List<PracticePhase> findByPhaseId(@Param("phaseId") final int phaseId);

    @Query("select p from PracticePhase p where phase.id = :phaseId")
    public Page<PracticePhase> findByPhaseId(@Param("phaseId") final int phaseId, final Pageable pageable);
}
