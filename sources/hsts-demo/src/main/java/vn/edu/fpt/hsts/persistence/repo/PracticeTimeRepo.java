package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.hsts.persistence.entity.MedicineTime;
import vn.edu.fpt.hsts.persistence.entity.PracticeTime;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/8/15.
 */
@Repository
public interface PracticeTimeRepo extends JpaRepository<PracticeTime, Integer> {
    @Query(value = "SELECT * FROM practicetime WHERE treatmentId = ?1", nativeQuery = true)
    List<PracticeTime> findPracticeTimeByTreatmentId(int treatmentId);
}
