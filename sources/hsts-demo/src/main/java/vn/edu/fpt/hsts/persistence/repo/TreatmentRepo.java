/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.Treatment;

import java.util.List;

@Repository
public interface TreatmentRepo extends JpaRepository<Treatment, Integer>{

    @Query(value = "SELECT * FROM treatment WHERE appointmentId = ?1 AND status = 1", nativeQuery = true)
    Treatment findTreatmentByAppointmentId(int appointmentId);

    @Query("select distinct t from Treatment t where appointment.id = :appointmentId ORDER BY fromDate DESC, id DESC")
    List<Treatment> findLastTreatmenByAppointmentId(@Param("appointmentId") final int appointmentId);

    @Query("select distinct t from Treatment t where appointment.medicalRecord.patient.id = :patientId and status = :status ORDER BY id DESC")
    public List<Treatment> findLastTreatmenByPatientId(@Param("patientId") final int patientId, @Param("status") final byte status);

    @Query("select distinct t from Treatment t where appointment.medicalRecord.patient.id = :patientId ORDER BY id DESC")
    public List<Treatment> findLastTreatmenByPatientId(@Param("patientId") final int patientId, final Pageable pageable);
}
