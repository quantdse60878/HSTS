package vn.edu.fpt.hsts.persistence.repo;

/**
 * Created by QUYHKSE61160 on 10/21/15.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

import java.util.List;

@Repository
public interface PreventionCheckRepo extends JpaRepository<PreventionCheck, Integer>  {

    @Query(value = "SELECT * FROM preventioncheck WHERE appointmentId = ?1", nativeQuery = true)
    PreventionCheck findPreventionCheckByAppointmentId(int appointmentId);

    @Query("select p from PreventionCheck p where appointment = :appointment ORDER BY id DESC")
    List<PreventionCheck> findPreventionCheckByAppointment(@Param("appointment")final Appointment appointment);

    @Query("select distinct p from PreventionCheck p where appointment.id = :appointmentId ORDER BY id DESC")
    public List<PreventionCheck> findPreventionCheckByAppointment(@Param("appointmentId") final int appointmentId);
}
