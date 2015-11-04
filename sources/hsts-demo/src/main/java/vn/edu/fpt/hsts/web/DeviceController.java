package vn.edu.fpt.hsts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fpt.hsts.bizlogic.service.DeviceService;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/28/15.
 */
@Controller
public class DeviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "sendUuidToAndroid", method = RequestMethod.POST)
    @ResponseBody
    public List<String> sendUuidToAndroid(@RequestParam("brandName") final String brandName) {

        String[] listArray = brandName.split(",");
        byte[] listByteData = new byte[listArray.length];
        for (int i = 0; i < listArray.length; i++) {
            listByteData[i] = Byte.parseByte(listArray[i]);
        }
        String listData = new String(listByteData);
        listData = listData.replaceAll("\u0000", " ");
        return deviceService.getListParamFromBrandName(listData);
    }

}
