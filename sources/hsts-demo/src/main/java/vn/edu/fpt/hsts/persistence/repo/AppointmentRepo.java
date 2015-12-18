/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Appointment;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    @Query(value = "SELECT * FROM appointment WHERE medicalRecordId = ?1 AND status = 2 ORDER BY appointmentDateTime DESC LIMIT 1", nativeQuery = true)
    public Appointment findAppointmentByMedicalRecordId(int medicalRecordId);


    @Query(value = "select a from Appointment a where medicalRecord.id = :medicalRecordId ORDER BY meetingDate DESC")
    public Appointment findLastAppointmentByMedicalRecordId(@Param("medicalRecordId") final int medicalRecordId);

    @Query(value = "select a from Appointment a where medicalRecord.patient.id = :patientId ORDER BY meetingDate DESC")
    public Appointment findLastAppointmentByPatientId(@Param("patientId") final int patientId);

    @Query("select distinct a from Appointment a where medicalRecord.patient.id = :patientId and status in (:statuses) ORDER BY meetingDate DESC")
    public List<Appointment> findLastAppointmentByPatientId(@Param("patientId") final int patientId, @Param("statuses") final byte[] status);

    @Query("select a from Appointment a where nextAppointment.id = :nextAppointmentId")
    public Appointment findParentAppointment(@Param("nextAppointmentId") final int appointmentId);

    @Query("select a from Appointment a where status > :status and meetingDate <= :date and medicalRecord.patient.id = :patientId ORDER BY id DESC")
    List<Appointment> getAllAppointmentToDate(@Param("date")final Date date, @Param("patientId") final int patientId, @Param("status") final byte status);

    @Query(value = "select a from Appointment a where medicalRecord.patient.id = :patientId and status in (:statuses) ORDER BY id DESC")
    public List<Appointment> findLastAppointmentByPatient(@Param("patientId") final int patientId, @Param("statuses") final byte[] statuses, final Pageable pageable);

    @Query("select a from Appointment a where meetingDate = :date and medicalRecord.patient.id = :patientId")
    public Appointment findAppointmentByDate(@Param("date") final Date date, @Param("patientId") final int patientId);


    @Query("select a from Appointment a where status  = :status and medicalRecord.patient.id = :patientId ORDER BY id DESC")
    public Page<Appointment> findHistoryByPatientId(@Param("status") final byte status, @Param("patientId") final int patientId, final Pageable pageable);

    @Modifying
    @Query("update Appointment a set a.status = :status where a.medicalRecord.id = :medicalRecordId")
    public void setStatusByMedicalRecordId(@Param("status") final byte status, @Param("medicalRecordId") final int medicalRecordId);

    @Query("select a from Appointment a where medicalRecord.id = :medicalRecordId ORDER BY meetingDate ASC")
    public List<Appointment> findHistoryByMedicalRecordId(@Param("medicalRecordId") final int medicalRecordId);
}
