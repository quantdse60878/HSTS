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
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;

import java.util.List;

@Repository
public interface MedicineTreatmentRepo extends JpaRepository<MedicineTreatment, Integer>{

    @Query(value = "SELECT * FROM medicinetreatment WHERE medicineTimeId = ?1", nativeQuery = true)
    List<MedicineTreatment> findMedicineTreatmentByMedicineTimeId(int medicineTimeId);

}
