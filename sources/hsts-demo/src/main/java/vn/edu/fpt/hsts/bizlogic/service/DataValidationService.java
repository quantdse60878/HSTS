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

            // Max vals
            maxVals.put("weight", (float) 200);
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
                final String paramName = params.nextElement();
                final float paramValue = Float.parseFloat(request.getParameter(paramName));
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
