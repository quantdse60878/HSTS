/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/10/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.PracticePhase;

@Repository
public interface PracticePhaseRepo extends JpaRepository<PracticePhase, Integer> {
}
