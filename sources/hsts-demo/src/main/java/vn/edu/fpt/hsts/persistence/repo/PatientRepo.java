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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Patient;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    @Query(value = "select patient.* from Patient patient join MedicalRecord mr on patient.id = mr.patientId join Appointment a on mr.id = a.medicalRecordId where a.appointmentDateTime = :appointmentDateTime and a.status = 1", nativeQuery = true)
    public List<Patient> findByAppoinmentDate(@Param(value = "appointmentDateTime") final Date date);

    @Query("select p from Patient p where lower(name) like lower(:name)")
    public Page<Patient> findByNameLike(@Param("name") final String name, final Pageable pageable);
}
