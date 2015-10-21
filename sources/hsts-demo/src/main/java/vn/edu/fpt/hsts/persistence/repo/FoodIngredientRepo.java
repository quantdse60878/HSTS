package vn.edu.fpt.hsts.persistence.repo;

import vn.edu.fpt.hsts.persistence.entity.FoodIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by QUYHKSE61160 on 10/21/15.
 */
public interface FoodIngredientRepo extends JpaRepository<FoodIngredient, Integer>   {
}
