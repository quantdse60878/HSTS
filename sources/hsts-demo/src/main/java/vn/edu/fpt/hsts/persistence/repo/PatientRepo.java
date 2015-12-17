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
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.Patient;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    @Query(value = "select patient.* from Patient patient join MedicalRecord mr on patient.id = mr.patientId join Appointment a on mr.id = a.medicalRecordId where a.appointmentDateTime = :appointmentDateTime and a.status = 3 and (mr.status = 1 or mr.status = 3)", nativeQuery = true)
    public List<Patient> findByAppoinmentDate(@Param(value = "appointmentDateTime") final Date date);

    @Query("select p from Patient p where lower(account.fullName) like lower(:name)")
    public Page<Patient> findByNameLike(@Param("name") final String name, final Pageable pageable);

    @Query("select p from Patient p where account.id = :accountId")
    public Patient findByAccountId(@Param("accountId") final int accountId);

    @Query("select p from Patient p where lower(account.username) like lower(:keyword) or lower(account.fullName) like lower(:keyword) or lower(barcode) like lower(:keyword)")
    public Page<Patient> findByNameOrBarcodeLike(@Param("keyword") final String keyword, final Pageable pageable);

    @Query("select distinct p from Patient p where lower(barcode) = lower(:barcode)")
    public Patient findPatientByBarcode(@Param("barcode") final String barcode);

    @Query(value = "select patient.* from Patient patient join MedicalRecord mr on patient.id = mr.patientId join Appointment a on mr.id = a.medicalRecordId join Doctor d on mr.doctorId = d.id join Account acc on d.accountId = acc.id where a.appointmentDateTime = :appointmentDateTime and a.status = 3 and mr.status in (1,3) and acc.id = :accountId", nativeQuery = true)
    List<Patient> findByAppoinmentDateAndAcc(@Param(value = "appointmentDateTime") final Date currentDate, @Param(value = "accountId") final int accountId);
}
