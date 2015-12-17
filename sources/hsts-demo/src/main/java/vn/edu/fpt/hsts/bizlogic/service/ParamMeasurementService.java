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

    @Autowired
    private DeviceService deviceService;

    public List<ParamMeasurement> findParamMeasurementByDevice(Device device) {
        return paramMeasurementRepo.findParamMeasurementByDeviceId(device.getId());
    }

    public void createNewMeasure(String brandName,String name, String type, String position, String measurementUUID) {
        ParamMeasurement paramMeasurement = new ParamMeasurement();
        paramMeasurement.setDevice(deviceService.findDeviceByBrandName(brandName));
        paramMeasurement.setUuid(measurementUUID);
        paramMeasurement.setPositionHaveValue(Integer.parseInt(position));
        paramMeasurement.setMeasurementType(type);
        paramMeasurement.setMeasurementName(name);
        paramMeasurement.setMeasurementMaxRange(-1);
        paramMeasurement.setMeasurementMinRange(-1);
        paramMeasurement.setType((byte) 1);
        paramMeasurementRepo.save(paramMeasurement);
    }

    public void deleteMeasurement(Device device, String measurementUUID) {
        ParamMeasurement paramMeasurement = paramMeasurementRepo.findParamMeasurementByDeviceIdAndUUID(device.getId(),measurementUUID);
        paramMeasurementRepo.delete(paramMeasurement);
    }

    public void deleteAllParamMeasurementByDevice(Device device) {
        List<ParamMeasurement> list = paramMeasurementRepo.findParamMeasurementByDeviceId(device.getId());
        for(ParamMeasurement paramMeasurement : list){
            paramMeasurementRepo.delete(paramMeasurement);
        }
    }
}
