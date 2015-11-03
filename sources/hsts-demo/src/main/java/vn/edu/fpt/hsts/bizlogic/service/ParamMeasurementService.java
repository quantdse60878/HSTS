package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Device;
import vn.edu.fpt.hsts.persistence.entity.ParamMeasurement;
import vn.edu.fpt.hsts.persistence.repo.ParamMeasurementRepo;

import java.util.List;

/**
 * Created by Man Huynh Khuong on 10/29/2015.
 */
@Service
public class ParamMeasurementService extends AbstractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private ParamMeasurementRepo paramMeasurementRepo;

    public List<ParamMeasurement> findParamMeasurementByDevice(Device device) {
        return paramMeasurementRepo.findParamMeasurementByDeviceId(device.getId());
    }
}
