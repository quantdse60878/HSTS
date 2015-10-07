package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Notify;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Repository
public interface NotifyRepo extends JpaRepository<Notify, Integer> {
}
