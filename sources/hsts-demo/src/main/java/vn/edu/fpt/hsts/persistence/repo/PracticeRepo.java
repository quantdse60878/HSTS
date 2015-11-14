package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Practice;

import java.util.List;

/**
 * Created by Aking on 10/10/2015.
 */
@Repository
public interface PracticeRepo extends JpaRepository<Practice, Integer> {

    @Query("select p from Practice p where lower(name) = lower(:name)")
    public Practice findByName(@Param("name") final String name);

    @Query("select p.name from Practice p")
    public List<String> findAllName();

    @Query("select p from Practice p where lower(name) like lower(:name)")
    public Page<Practice> findByNameLike(@Param("name") final String name, final Pageable pageable);
}
