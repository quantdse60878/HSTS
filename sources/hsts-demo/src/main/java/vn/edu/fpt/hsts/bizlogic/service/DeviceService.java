package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Device;
import vn.edu.fpt.hsts.persistence.entity.ParamMeasurement;
import vn.edu.fpt.hsts.persistence.repo.DeviceRepo;
import vn.edu.fpt.hsts.persistence.repo.ParamMeasurementRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/28/15.
 */
@Service
public class DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private ParamMeasurementRepo paramMeasurementRepo;
    @Autowired
    private DeviceRepo deviceRepo;

    public List<String> getListParamFromBrandName(String brandName) {
        List<String> result = new ArrayList<String>();
        Device device = deviceRepo.findDeviceByBrandName(brandName);
        List<ParamMeasurement> paramMeasurementList = paramMeasurementRepo.findParamMeasurementByDeviceId(device.getId());
        for(ParamMeasurement paramMeasurement : paramMeasurementList) {
            result.add(paramMeasurement.getUuid() + "," + paramMeasurement.getPositionHaveValue() + "," + paramMeasurement.getMeasurementName());
        }
        return  result;
    }
}
