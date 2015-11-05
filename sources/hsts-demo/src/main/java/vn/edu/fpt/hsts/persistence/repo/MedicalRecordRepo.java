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
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface MedicalRecordRepo extends JpaRepository<MedicalRecord, Integer> {

    @Query("SELECT m FROM MedicalRecord m WHERE m.patient.id = ?1 AND m.status = 3")
    public List<MedicalRecord> findMedicalRecordByPatientId(int patientId);

    @Query("select m.medicalHistory from MedicalRecord m where m.patient.id = :patientId")
    public List<String> findMedicalHistoryByPatientId(@Param("patientId") final int patientId);

    @Query("select m from MedicalRecord m where m.patient.id = :patientId order by id desc")
    public List<MedicalRecord> findByPatientId(@Param("patientId") final int patientId, final Pageable pageable);
}
