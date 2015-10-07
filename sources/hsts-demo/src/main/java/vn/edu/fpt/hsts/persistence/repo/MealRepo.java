package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.hsts.persistence.entity.Meal;
import vn.edu.fpt.hsts.persistence.entity.MedicineTime;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Repository
public interface MealRepo extends JpaRepository<Meal, Integer> {

    @Query(value = "SELECT * FROM meal WHERE treatmentId = ?1", nativeQuery = true)
    List<Meal> findMealByTreatmentId(int treatmentId);

}
