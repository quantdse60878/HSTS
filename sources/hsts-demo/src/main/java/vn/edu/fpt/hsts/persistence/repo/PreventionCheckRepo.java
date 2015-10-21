package vn.edu.fpt.hsts.persistence.repo;

/**
 * Created by QUYHKSE61160 on 10/21/15.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

@Repository
public interface PreventionCheckRepo extends JpaRepository<PreventionCheck, Integer>  {

    @Query(value = "SELECT * FROM preventioncheck WHERE appointmentId = ?1", nativeQuery = true)
    PreventionCheck findPreventionCheckByAppointmentId(int appointmentId);

}
