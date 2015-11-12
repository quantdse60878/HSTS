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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Phase;

import java.util.List;

@Repository
public interface PhaseRepo extends JpaRepository<Phase, Integer> {

    @Query(value = "select p from Phase p where regimen.illness.id = :illnessID")
    public Phase findPhaseByIllnessID(@Param(value = "illnessID") final int illnessID);

//    @Query("select p from Phase p where regimen.illness.id = :illnessId and fromDate <= :date AND :date <= toDate")
//    public Phase findSuitablePhase(@Param("illnessId") final int illnessId, @Param("date") final int date);

    @Query("select p from Phase p where regimen.id = :regimenId")
    public Page<Phase> findByRegimenId(@Param("regimenId") final int regimenId, final Pageable pageable);

    @Query("select p from Phase p where regimen.id = :regimenId")
    public List<Phase> findByRegimenId(@Param("regimenId") final int regimenId);
}
