package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.fpt.hsts.persistence.entity.PropertyRecord;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/27/15.
 */
public interface PropertyRecordRepo extends JpaRepository<PropertyRecord, Integer> {
    @Query(value = "SELECT * FROM propertyrecord WHERE medicalRecordDataId = :medicalRecordDataId AND paramMeasurementId = :paramMeasurementId", nativeQuery = true)
    public PropertyRecord findPropertyRecordByMrdAndpm(@Param(value = "medicalRecordDataId") final int medicalRecordDataId, @Param("paramMeasurementId") final int paramMeasurementId);

    @Query(value = "SELECT * FROM propertyrecord WHERE medicalRecordDataId = :medicalRecordDataId AND paramMeasurementId = :paramMeasurementId", nativeQuery = true)
    public List<PropertyRecord> findAllPropertyRecordByMrdAndpm(@Param(value = "medicalRecordDataId") final int medicalRecordDataId, @Param("paramMeasurementId") final int paramMeasurementId);
}
