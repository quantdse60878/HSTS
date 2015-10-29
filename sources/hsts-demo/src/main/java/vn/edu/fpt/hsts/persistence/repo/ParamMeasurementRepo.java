package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.fpt.hsts.persistence.entity.ParamMeasurement;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/27/15.
 */
public interface ParamMeasurementRepo extends JpaRepository<ParamMeasurement, Integer> {

    @Query(value = "SELECT * FROM parammeasurement WHERE measurementName = ?1", nativeQuery = true)
    public ParamMeasurement findParamMeasurementByMeasurementName(String measurementName);

    @Query(value = "SELECT * FROM parammeasurement WHERE deviceId = ?1 AND type = 1", nativeQuery = true)
    public List<ParamMeasurement> findParamMeasurementByDeviceId(int deviceId);

}

