package vn.edu.fpt.hsts.persistence.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.MedicineTime;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Repository
public interface MedicineTimeRepo extends JpaRepository<MedicineTime, Integer> {

    @Query(value = "SELECT * FROM medicinetime WHERE treatmentId = ?1", nativeQuery = true)
    List<MedicineTime> findMedicineTimeByTreatmentId(int treatmentId);
}
