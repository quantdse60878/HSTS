package vn.edu.fpt.hsts.persistence.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.hsts.persistence.entity.Device;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/28/15.
 */
@Repository
public interface DeviceRepo extends JpaRepository<Device, Integer> {

    @Query(value = "SELECT * FROM device WHERE brandName = ?1", nativeQuery = true)
    public Device findDeviceByBrandName(String brandName);

    @Query(value = "select b.brandName from device b where lower(b.brandName) like lower(?1)", nativeQuery = true)
    public List<String> findByName(final String searchCriteria);
}
