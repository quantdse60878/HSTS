package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Practice;

/**
 * Created by Aking on 10/10/2015.
 */
@Repository
public interface PracticeRepo extends JpaRepository<Practice, Integer> {
}
