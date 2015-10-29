package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Device;

/**
 * Created by QUYHKSE61160 on 10/28/15.
 */
@Repository
public interface DeviceRepo extends JpaRepository<Device, Integer> {

    @Query(value = "SELECT * FROM device WHERE brandName = ?1", nativeQuery = true)
    public Device findDeviceByBrandName(String brandName);

}
