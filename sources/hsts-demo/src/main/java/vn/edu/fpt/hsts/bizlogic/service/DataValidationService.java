/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/25/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * The service for define dynamic range value for input parameter, check the value in range.
 */
@Service
public class DataValidationService extends AbstractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataValidationService.class);

    @Autowired
    private void initParam() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            // Min vals
            minVals.put("weight", (float) 20);
            minVals.put("height", (float) 110);
            minVals.put("bodyFat", (float) 1);
            minVals.put("visceralFat", (float) 1);
            minVals.put("muscleMass", (float) 1);
            minVals.put("bodyWater", (float) 1);
            minVals.put("phaseAngle", (float) 1);
            minVals.put("impedance", (float) 1);
            minVals.put("basalMetabolicRate", (float) 1);
            minVals.put("mQuantity", (float) 1);

            // Max vals
            maxVals.put("weight", (float) 200);
            maxVals.put("height", (float) 250);
            maxVals.put("bodyFat", (float) 100);
            maxVals.put("visceralFat",(float) 100);
            maxVals.put("muscleMass", (float) 100);
            maxVals.put("bodyWater", (float) 100);
            maxVals.put("phaseAngle", (float) 100);
            maxVals.put("impedance", (float) 5000);
            maxVals.put("basalMetabolicRate", (float) 5000);
            maxVals.put("mQuantity", (float) 10);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }


    /**
     * Common method to check min and max value of input param.
     * @param request {@link HttpServletRequest}
     * @return
     */
    public boolean validateRequestParam(final HttpServletRequest request) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                final float paramValue = Float.parseFloat(request.getParameter(paramName));

                // For case validate model attribute with list index, ex: mPresModel[2].mQuantity
                if (paramName.contains(".")) {
                    // Get the lastest string to use as paramName
                    final int index = paramName.lastIndexOf(".");
                    paramName = paramName.substring(index + 1);
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{}[{}]", paramName, paramValue);
                }
                // Check min value
                final Float minVal = minVals.get(paramName);
                if(null != minVal) {
                    if (paramValue < minVal) {
                        return false;
                    }
                }
                // Check max value
                final Float maxVal = maxVals.get(paramName);
                if(null != maxVal) {
                    if(paramValue > maxVal) {
                        return false;
                    }
                }
            }
            return true;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
