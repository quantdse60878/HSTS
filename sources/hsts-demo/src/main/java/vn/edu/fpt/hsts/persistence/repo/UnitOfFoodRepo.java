package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.UnitOfFood;

import java.util.List;

/**
 * Created by Aking on 11/3/2015.
 */
@Repository
public interface UnitOfFoodRepo extends JpaRepository<UnitOfFood, Integer> {

    @Query("select u.unitName from UnitOfFood u where food.id = :foodId")
    public List<String> findByFoodId(@Param("foodId") final int foodId);
}
