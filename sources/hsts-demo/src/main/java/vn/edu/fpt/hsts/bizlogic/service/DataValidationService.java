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
import vn.edu.fpt.hsts.common.util.StringUtils;

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
            /**
             * Min vals
             */
            if (null != minVals) {
               this.configureMinValue();
            }

            /**
             * Max params.
             */
            if (null != maxVals) {
                this.configMaxValue();
            }
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


    public boolean validateRequestString(final HttpServletRequest request) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                String paramValue = request.getParameter(paramName).trim();
                // Check for specific validation
                if(StringUtils.isEmpty(paramValue)) {
                    return false;
                }
                boolean result =  this.validateString(paramName, paramValue);
                if (result) {
                    continue;
                }
                return false;
            }
            return true;
        }  finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public void configureMinValue() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            // Nurse register
            minVals.put("weight", (float) 20);
            minVals.put("height", (float) 110);
            minVals.put("hearthBeat", (float) 20);
            minVals.put("bloodPressure", (float) 10);
            minVals.put("waists", (float) 50);
            minVals.put("bmi", (float) 10);

            minVals.put("bodyFat", (float) 1);
            minVals.put("visceralFat", (float) 1);
            minVals.put("muscleMass", (float) 1);
            minVals.put("bodyWater", (float) 1);
            minVals.put("phaseAngle", (float) 1);
            minVals.put("impedance", (float) 1);
            minVals.put("basalMetabolicRate", (float) 1);

            // Prescription model attribute
            minVals.put("mQuantity", (float) 1);

            // Nutrition model attribute
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    public void configMaxValue() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            // Nurse register
            maxVals.put("weight", (float) 200);
            maxVals.put("height", (float) 250);
            maxVals.put("hearthBeat", (float) 200);
            maxVals.put("bloodPressure", (float) 200);
            maxVals.put("waists", (float) 300);
            maxVals.put("bmi", (float) 50);

            maxVals.put("bodyFat", (float) 100);
            maxVals.put("visceralFat",(float) 100);
            maxVals.put("muscleMass", (float) 100);
            maxVals.put("bodyWater", (float) 100);
            maxVals.put("phaseAngle", (float) 100);
            maxVals.put("impedance", (float) 5000);
            maxVals.put("basalMetabolicRate", (float) 5000);

            // Prescription Model attribute
            maxVals.put("mQuantity", (float) 10);

            // Nutrition model attribute
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    public boolean validateString(final String paramName, final String paramValue){
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("paramName[{}], paramValue[{}]", paramName, paramValue);
            }
            // For specical case for each parameter, implement here
            if("GOOD".equals(paramName)) {
                LOGGER.debug("Good");
                return true;
            } else if("BAAD".equals(paramName)) {
                return false;
            }
            return true;
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }
}
