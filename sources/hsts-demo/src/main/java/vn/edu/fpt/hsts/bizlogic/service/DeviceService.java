package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
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

    public Device findDeviceByBrandName(String brandName){
        return deviceRepo.findDeviceByBrandName(brandName);
    }

    public List<String> findAllIllnessName(final String searchStr, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("searchStr[{}], page[{}], pageSize[{}]", searchStr, page, pageSize);
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            final String searchCond = "%" + searchStr + "%";
            final List<String> illnessNames = deviceRepo.findByName(searchCond);
            if (null != illnessNames) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Got {} records", illnessNames.size());
                }
            }
//            for(int i = 0; i< 100; i++) {
//                illnessNames.add(new String("test data" + i));
//            }
            return illnessNames;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public void createNewDevice(Device device) {
        deviceRepo.save(device);
    }

    public List<String> getListNameOfDevice() {
        List<Device> devices = deviceRepo.findAll();
        List<String> list = new ArrayList<String>();
        for (Device item: devices){
            list.add(item.getBrandName());
        }
        return list;
    }

    public void deleteDevice(Device device) {
        deviceRepo.delete(device);
    }
}
