package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by QUYHKSE61160 on 10/21/15.
 */
@Repository
public interface FoodIngredientRepo extends JpaRepository<FoodIngredient, Integer>   {
}
