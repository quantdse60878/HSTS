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
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecordData;

import java.util.List;

@Repository
public interface MedicalRecordDataRepo extends JpaRepository<MedicalRecordData, Integer> {

    @Query(value = "SELECT * FROM medicalrecorddata WHERE type = 2", nativeQuery = true)
    public List<MedicalRecordData> findRecordDataNotUpdate();

}
