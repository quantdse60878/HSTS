package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/21/15.
 */
@Repository
public interface FoodIngredientRepo extends JpaRepository<FoodIngredient, Integer>   {

    @Query("select f from FoodIngredient f where appointment = :appointment ORDER BY id DESC")
    public List<FoodIngredient> findLastFoodIngredientByAppointment(@Param("appointment") final Appointment appointment);
}
