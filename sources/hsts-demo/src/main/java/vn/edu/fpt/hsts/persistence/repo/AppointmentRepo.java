/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/25/2015.
 */
package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.App;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    @Query(value = "SELECT * FROM appointment WHERE medicalRecordId = ?1 AND status = 2 ORDER BY appointmentDateTime DESC LIMIT 1", nativeQuery = true)
    public Appointment findAppointmentByMedicalRecordId(int medicalRecordId);


    @Query(value = "select a from Appointment a where medicalRecord.id = :medicalRecordId ORDER BY meetingDate DESC")
    public Appointment findLastAppointmentByMedicalRecordId(@Param("medicalRecordId") final int medicalRecordId);
}
