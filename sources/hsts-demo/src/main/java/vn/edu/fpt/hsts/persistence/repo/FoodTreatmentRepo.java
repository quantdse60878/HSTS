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
import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.List;

@Repository
public interface FoodTreatmentRepo extends JpaRepository<FoodTreatment, Integer> {

    @Query(value = "SELECT * FROM foodtreatment WHERE treatmentId = ?1", nativeQuery = true)
    public List<FoodTreatment> findFoodTreatmentTreatmentId(int treatmentId);

    @Query(value = "SELECT * FROM foodtreatment WHERE foodId = ?1", nativeQuery = true)
    public List<FoodTreatment> findFoodTreatmentByFoodId(int foodId);

    @Query("select distinct t from FoodTreatment t where treatment = :treatment")
    public List<FoodTreatment> getAllFoodTreatmentFromTreatment(@Param("treatment") final Treatment treatment);

    @Query("select t from FoodTreatment t where treatment.appointment.id = :appointmentId")
    public List<FoodTreatment> findFoodTreatmentByAppointmentId(@Param("appointmentId") final int appointmentId);

}
