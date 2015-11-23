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

            /**
             * Integer type param names
             */
            if(null != intParams) {
                this.configIntParamNames();
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
                // For case Integer parsing value wrong
                Object vals = null;
                Float paramValue = null;
                if (isIntParam(paramName)) {
                    vals = Integer.parseInt(request.getParameter(paramName));
                } else {
                    vals = Float.parseFloat(request.getParameter(paramName));
                }
                if (null == vals) {
                    return false;
                }
                if (vals instanceof Float) {
                    paramValue = (Float) vals;
                } else {
                    paramValue = Float.parseFloat(vals.toString());
                }

                // Select model attribute name with list index, ex: mPresModel[2].mQuantity
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

                // TODO Huge value for testing, remove later
                if (paramValue >= 9999) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        } catch (Exception e) {
            return false;
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

    private void configureMinValue() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            // Nurse register
            minVals.put("weight", (float) 20);
            minVals.put("height", (float) 100);
            minVals.put("hearthBeat", (float) 60);
            minVals.put("bloodPressure", (float) 70);
            minVals.put("waists", (float) 50);

            minVals.put("bodyFat", (float) 1);
            minVals.put("visceralFat", (float) 1);
            minVals.put("muscleMass", (float) 1);
            minVals.put("bodyWater", (float) 1);
            minVals.put("phaseAngle", (float) 1);
            minVals.put("impedance", (float) 1);
            minVals.put("basalMetabolicRate", (float) 1);

            // Prescription model attribute
            minVals.put("mQuantity", (float) 1);
            minVals.put("kcalRequire", (float) 0);

            // Nutrition model attribute
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    private void configMaxValue() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            // Nurse register
            maxVals.put("weight", (float) 300);
            maxVals.put("height", (float) 250);
            maxVals.put("hearthBeat", (float) 120);
            maxVals.put("bloodPressure", (float) 190);
            maxVals.put("waists", (float) 300);

            maxVals.put("bodyFat", (float) 100);
            maxVals.put("visceralFat",(float) 100);
            maxVals.put("muscleMass", (float) 100);
            maxVals.put("bodyWater", (float) 100);
            maxVals.put("phaseAngle", (float) 100);
            maxVals.put("impedance", (float) 5000);
            maxVals.put("basalMetabolicRate", (float) 5000);

            // Prescription Model attribute
            maxVals.put("mQuantity", (float) 10);
            maxVals.put("kcalRequire", (float) 1000);

            // Nutrition model attribute
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    private void configIntParamNames() {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            // Nurse register
            intParams.add("weight");
            intParams.add("height");
            intParams.add("impedance");
            intParams.add("basalMetabolicRate");
            intParams.add("bloodPressure");
            intParams.add("heartbeat");
            intParams.add("waists");

            // Prescription Model attribute
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

    private boolean isIntParam(final String paramName) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("paramName[{}]", paramName);
            }
            return intParams.contains(paramName);
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }

    }
}
